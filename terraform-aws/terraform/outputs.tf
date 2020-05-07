output "elb_address" {
  value = aws_elb.calc-elb.dns_name
}

output "ami_name" {
  value = data.aws_ami.calc-ami.name
}

output "ami_id" {
  value = data.aws_ami.calc-ami.id
}