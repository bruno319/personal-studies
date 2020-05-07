//export GOOGLE_CLOUD_KEYFILE_JSON={{path}}
//export SSH_KEY={{path}}
//export SSH_KEY_PUBLIC={{path}}
//export PROJECT_ID={{id}}

provider "google" {
  credentials = file("packer/credentials/My First Project-1324228b1961.json")
  project = "theta-strata-242418"
  region = "us-central1"
  zone = "us-central1-c"
}

resource "google_compute_instance" "vm_instance" {
  name = "log-access-analytics"
  machine_type = "n1-standard-1"
  tags = ["web"]

  network_interface {
    network = "default"
    access_config {}
  }

  boot_disk {
    initialize_params {
      image = "log-access-analytics-1560540356"
    }
  }

  metadata = {
    ssh-keys = "root:${file("~/.ssh/gcp.pub")}"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo service mongod start",
      "cd project",
      "./gradlew :health-check:run"
    ]
    connection {
      type = "ssh"
      user = "root"
      host = google_compute_instance.vm_instance.network_interface.0.access_config.0.nat_ip
      private_key = file("~/.ssh/gcp")
    }
  }
}

resource "google_compute_network" "vpc_network" {
  name = "log-access-analytics-network"
  auto_create_subnetworks = "true"
}

resource "google_compute_firewall" "allow-http" {
  name = "allow-http"
  network = google_compute_network.vpc_network.name

  allow {
    protocol = "tcp"
    ports    = ["80", "8080", "8082", "8084"]
  }

  source_ranges = ["0.0.0.0/0"]
  target_tags = ["web"]
}

