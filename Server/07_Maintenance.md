# Maintenance & Management

## Useful Docker Commands

```
# View running containers
docker ps

# View all containers (including stopped)
docker ps -a

# View logs
docker-compose logs -f web          # Website logs
docker-compose logs -f postgres     # Database logs

# Restart containers
docker-compose restart

# Stop containers
docker-compose down

# Start containers
docker-compose up -d

# Rebuild and restart (after code changes)
docker-compose down
git pull origin main
docker-compose build
docker-compose up -d

# Execute commands inside container
docker-compose exec web npm run build
docker-compose exec postgres psql -U postgres visionforge

# Clean up unused Docker resources
docker system prune -a
```

---

## Database Backup Script

```
# Create backup directory
mkdir -p ~/backups

# Create backup script
nano ~/backups/backup-db.sh
```

- Paste

```
#!/bin/bash
DATE=$(date +%Y%m%d_%H%M%S)
BACKUP_DIR=~/backups
DB_NAME=visionforge
DB_USER=postgres
CONTAINER=visionforge-db

# Create backup
docker exec $CONTAINER pg_dump -U $DB_USER $DB_NAME | gzip > $BACKUP_DIR/backup_$DATE.sql.gz

# Keep only last 7 days of backups
find $BACKUP_DIR -name "backup_*.sql.gz" -mtime +7 -delete

echo "Backup completed: backup_$DATE.sql.gz"
```

- Save and make it executable

```
chmod +x ~/backups/backup-db.sh

# Test backup
~/backups/backup-db.sh

# Schedule daily backups with cron
crontab -e
# Add this line (runs daily at 2 AM):
0 2 * * * /home/yourname/backups/backup-db.sh >> /home/yourname/backups/backup.log 2>&1
```

- Update/Deploy New Code

```
cd ~/apps/vision-forge-studio-v2

# Pull latest code
git pull origin main

# Rebuild and restart
docker-compose down
docker-compose build
docker-compose up -d

# Run migrations if schema changed
docker-compose exec web npx prisma migrate deploy
```

---

## Monitoring and Optimization

### Install Monitoring Tools

```
# Install htop (process monitor)
sudo apt install htop -y

# Use it
htop
# Press F10 to exit

# Check disk space
df -h

# Check memory usage
free -h

# Check Docker container stats
docker stats
```

- Setup automatic security updates

```
sudo apt install unattended-upgrades -y
sudo dpkg-reconfigure --priority=low unattended-upgrades
# Select "Yes"
```

---