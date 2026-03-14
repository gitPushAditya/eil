# For Future App Backends

## Create new folder - 

```
cd ~/apps
mkdir my-app-backend
cd my-app-backend
```

---

## Create docker-compose.yml

```
version: '3.8'
services:
  api:
    build: .
    ports:
      - "3001:3001"  # Different port
    environment:
      - DATABASE_URL=postgresql://postgres:password@visionforge-db:5432/my_app_db
    networks:
      - visionforge_visionforge-network  # Connect to main network
    restart: unless-stopped

networks:
  visionforge_visionforge-network:
    external: true
```

```
sudo nano /etc/nginx/sites-available/api-myapp
```

```
server {
    listen 443 ssl http2;
    server_name api.yourdomain.com;
    
    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
    
    location / {
        proxy_pass http://127.0.0.1:3001;
        # ... proxy settings
    }
}
```

---

## Troubleshooting Common Issues

- Website not loading:
```
# Check if containers are running
docker ps

# Check logs
docker-compose logs -f web

# Check Nginx
sudo nginx -t
sudo systemctl status nginx

# Check firewall
sudo ufw status
```

- Database connection issues:

```
# Check if database is running
docker-compose logs postgres

# Test connection
docker-compose exec postgres psql -U postgres -d visionforge
```

- Out of Memory

```
# Check memory
free -h

# Restart containers
docker-compose restart

# Consider upgrading VPS to 4GB RAM
```

---