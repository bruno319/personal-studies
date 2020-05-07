provider "google" {
  project     = var.project_id
  credentials = var.account_file
  region      = var.region
  version     = "2.14"
}

resource "google_compute_instance" "calc-instance" {
  name         = "calc-service"
  machine_type = var.machine_type
  zone         = var.zone
  tags         = ["http"]
  boot_disk {
    auto_delete = true
    initialize_params {
      image     = var.image_name
    }
  }
  network_interface {
    network = "default"
    access_config {
    }
  }
}

resource "google_compute_instance_group" "calc-ig" {
  name      = "calc-instance-group"
  instances = [google_compute_instance.calc-instance.self_link]
  zone      = var.zone

  named_port {
    name = "http"
    port = 8282
  }
}

resource "google_compute_firewall" "calc-firewall" {
  name    = "calc-firewall"
  network = "default"

  allow {
    protocol = "tcp"
    ports    = ["80", "8282"]
  }
  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["http"]
}

resource "google_compute_backend_service" "calc-service" {
  name          = "calc-service"
  protocol      = "HTTP"
  port_name     = "http"
  health_checks = [google_compute_http_health_check.nlb-hc.self_link]

  backend {
    group = google_compute_instance_group.calc-ig.self_link
  }
}

resource "google_compute_http_health_check" "nlb-hc" {
  name               = "load-balancer-health-check"
  request_path       = "/calc/history"
  port               = 8282
  check_interval_sec = 10
  timeout_sec        = 3
}

resource "google_compute_target_http_proxy" "calc-proxy" {
  name    = "calc-proxy"
  url_map = google_compute_url_map.calc-url-map.self_link
}

resource "google_compute_global_forwarding_rule" "calc-nlb" {
  name                  = "calc-network-load-balancer"
  target                = google_compute_target_http_proxy.calc-proxy.self_link
  load_balancing_scheme = "EXTERNAL"
  port_range            = "80"
}

resource "google_compute_url_map" "calc-url-map" {
  name            = "calc-url-map"
  default_service = google_compute_backend_service.calc-service.self_link
}