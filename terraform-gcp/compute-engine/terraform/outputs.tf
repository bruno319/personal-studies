output "load-balancer-ip" {
  value = google_compute_global_forwarding_rule.calc-nlb.ip_address
}

output "calc-service-ip" {
  value = google_compute_instance.calc-instance.network_interface.0.access_config.0.nat_ip
}