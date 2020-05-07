variable "region" {
  description = "AWS Region"
  default = "us-east-1"
}

variable "aws_access_key" {
  description = "Your access key on AWS"
}

variable "aws_secret_key" {
  description = "Your secret key on AWS"
}

variable "ami_name" {
  description = "AMI name on AWS"
}

variable "availability_zones" {
  description = "Availability zones from region"
  type        = list(string)
  default     = ["us-east-1e", "us-east-1a"]
}

variable "instance_type" {
  default     = "t2.micro"
  description = "AWS instance type"
}

variable "asg_min" {
  description = "Min numbers of servers in ASG"
  default     = "1"
}

variable "asg_max" {
  description = "Max numbers of servers in ASG"
  default     = "2"
}

variable "asg_desired" {
  description = "Desired numbers of servers in ASG"
  default     = "1"
}
