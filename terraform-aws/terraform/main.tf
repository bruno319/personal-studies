provider "aws" {
  region     = var.region
  version    = "2.26"
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

data "aws_ami" "calc-ami" {
  filter {
    name   = "name"
    values = [var.ami_name]
  }

  filter {
    name   = "state"
    values = ["available"]
  }

  owners      = ["self"]
  most_recent = true
}

resource "aws_launch_configuration" "calc-lc" {
  image_id        = data.aws_ami.calc-ami.id
  instance_type   = var.instance_type

  security_groups = [aws_security_group.calc-sg.id]
}

resource "aws_elb" "calc-elb" {
  name               = "calc-elb"
  availability_zones = var.availability_zones
  security_groups    = [aws_security_group.elb-sg.id]

  listener {
    instance_port     = 8282
    instance_protocol = "http"
    lb_port           = 8282
    lb_protocol       = "http"
  }

  health_check {
    healthy_threshold   = 2
    unhealthy_threshold = 2
    timeout             = 3
    target              = "HTTP:8282/calc/history"
    interval            = 30
  }
}

resource "aws_autoscaling_group" "calc-asg" {
  name     = "calc-asg"
  max_size = var.asg_max
  min_size = var.asg_min
  desired_capacity     = var.asg_desired
  launch_configuration = aws_launch_configuration.calc-lc.name
  load_balancers       = [aws_elb.calc-elb.name]
  availability_zones   = var.availability_zones

  tag {
    key   = "ASG_Calc_Go"
    value = "calc-asg"
    propagate_at_launch = "true"
  }
}

resource "aws_security_group" "elb-sg" {
  name = "security_group_for_elb"
  ingress {
    from_port   = 8282
    to_port     = 8282
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "calc-sg" {
  name = "security_group_for_calc_service"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8282
    to_port     = 8282
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}