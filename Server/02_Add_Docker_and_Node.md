# Docker and Containers

## Install Docker

```
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
```

### Add your user to docker group

```
sudo usermod -aG docker ${USER}
```

### Apply group changes

```
newgrp docker
```

### Install Docker Compose 

```
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

### Verify Installation

```
docker --version
docker-compose --version
```

---

## Install Node

```
curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -
sudo apt-get install -y nodejs
```

### Verify

```
node --version
npm --version
```

---

## Install Git 

```
sudo apt install git -y
git --version
```

---