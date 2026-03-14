# 🚀 Complete VPS Deployment Guide - Vision Forge Studio
**From Zero to Production-Ready Website**

> **Author**: Your AI Assistant  
> **Last Updated**: October 2025  
> **Deployment Stack**: VPS + Docker + Next.js + PostgreSQL + Nginx + SSL  
> **Estimated Time**: 2-4 hours (first time)

---

## 📋 Table of Contents
1. [Should You Use VPS? - Decision Guide](#decision-guide)
2. [Phase 1: VPS Setup & Security](#phase-1)
3. [Phase 2: Install Required Software](#phase-2)
4. [Phase 3: Domain & SSL Setup](#phase-3)
5. [Phase 4: Deploy Application](#phase-4)
6. [Phase 5: Maintenance & Management](#phase-5)
7. [Phase 6: Monitoring & Optimization](#phase-6)
8. [Future: Adding More Backends](#future-backends)
9. [Troubleshooting Guide](#troubleshooting)
10. [Quick Reference Commands](#quick-reference)

---

<a name="decision-guide"></a>
## 🤔 Should You Deploy on VPS? - Honest Analysis

### **Your Situation:**
- ✅ You're an app developer who needs backends regularly
- ✅ You want cost control and no restrictions
- ✅ You're willing to learn server management
- ✅ You need flexibility for multiple projects

### **Cost Comparison (Monthly):**

**Option 1: VPS (Recommended for You)**
- VPS: $5-20/month (DigitalOcean, Linode, Vultr)
- Can host: Website + Multiple app backends + Database + Redis + Monitoring
- **Total: $5-20/month for EVERYTHING**

**Option 2: Vercel + Supabase + Render**
- Vercel (Hobby): $0 (limited) or Pro $20/month
- Supabase: $0 (limited) or $25/month
- Render backends: $7-25 EACH backend
- **Total: $50-100+/month for just a few projects**

### **VPS Pros:**
- 💰 **Massive cost savings** for multiple projects
- 🚀 **Full control** - no vendor restrictions
- 📚 **Learning opportunity** - valuable DevOps skills
- 🔧 **Flexibility** - host anything (websites, APIs, databases, microservices)
- 🎯 **Perfect for app developer** - one server for all your backends

### **VPS Cons:**
- ⏰ **Time investment** - initial setup & learning curve (10-20 hours first time)
- 🛠️ **Maintenance** - you handle security updates, backups, monitoring
- 📊 **Responsibility** - uptime, security, scaling is on you
- 🐛 **Troubleshooting** - need to debug server issues yourself

### **Final Verdict: ⭐⭐⭐⭐⭐ DO IT!**

For your use case (app developer + multiple backends), VPS is the **BEST choice**. You'll save hundreds of dollars yearly and gain invaluable skills.

---

<a name="phase-1"></a>
## 🔧 PHASE 1: VPS Setup & Initial Configuration

### **Step 1: Choose & Purchase VPS**

**Recommended Providers:**

| Provider | Price | Specs | Best For |
|----------|-------|-------|----------|
| **DigitalOcean** | $12/mo | 2GB RAM, 50GB SSD | Beginners (great docs) ⭐ |
| **Linode (Akamai)** | $10/mo | 2GB RAM, 50GB SSD | Performance |
| **Vultr** | $12/mo | 2GB RAM, 50GB SSD | Global coverage |

**What to Select:**
- **OS**: Ubuntu 22.04 LTS (most popular, best support)
- **Size**: 2GB RAM minimum (for website + database + future backends)
- **Location**: Closest to your target audience
- **SSH Key**: We'll create this in Step 2

**Action:**
1. Go to your chosen provider's website
2. Create account
3. Click "Create Droplet/Instance"
4. Select Ubuntu 22.04 LTS
5. Select 2GB RAM plan
6. Select region closest to you
7. Add SSH key (create in next step)
8. Click "Create"

---

### **Step 2: Generate SSH Key (On Your Windows)**

Open **PowerShell** and run:

```powershell
# Generate SSH key
ssh-keygen -t ed25519 -C "your-email@example.com"

# When prompted:
# - Press Enter to save in default location (C:\Users\YourName\.ssh\id_ed25519)
# - Enter a passphrase (optional but recommended for security)

# Copy your public key to clipboard
Get-Content $env:USERPROFILE\.ssh\id_ed25519.pub | clip
```

**Now:**
1. Go back to your VPS provider
2. Find "SSH Keys" section
3. Click "Add SSH Key"
4. Paste the key (Ctrl+V)
5. Give it a name (e.g., "My Windows PC")
6. Save

---

### **Step 3: Connect to Your VPS**

After VPS is created, you'll receive:
- **IP Address**: e.g., `123.45.67.89`
- **Username**: `root`

**Connect via SSH:**

```powershell
# In PowerShell
ssh root@YOUR_VPS_IP

# Example:
# ssh root@123.45.67.89

# Type 'yes' when asked about fingerprint
# Enter SSH key passphrase if you set one
```

🎉 **You're now inside your VPS!**

---

### **Step 4: Initial Server Security Setup**

**4.1: Update System**
```bash
# Update package lists and upgrade packages
apt update && apt upgrade -y

# This takes 2-5 minutes
```

**4.2: Create Non-Root User (Security Best Practice)**
```bash
# Create new user (replace 'yourname' with your desired username)
adduser yourname

# You'll be prompted to:
# - Enter password (choose a strong one!)
# - Enter full name (optional)
# - Other details (just press Enter to skip)

# Give sudo (admin) privileges
usermod -aG sudo yourname
```

**4.3: Setup SSH for New User**
```bash
# Copy SSH keys from root to new user
mkdir -p /home/yourname/.ssh
cp /root/.ssh/authorized_keys /home/yourname/.ssh/
chown -R yourname:yourname /home/yourname/.ssh
chmod 700 /home/yourname/.ssh
chmod 600 /home/yourname/.ssh/authorized_keys

# Exit current session
exit
```

**4.4: Reconnect as New User**
```powershell
# In PowerShell
ssh yourname@YOUR_VPS_IP

# Now you're logged in as yourname (not root)
```

**4.5: Disable Root Login (Critical Security)**
```bash
# Edit SSH config
sudo nano /etc/ssh/sshd_config

# Find these lines (use Ctrl+W to search):
# Change:
#   PermitRootLogin yes
# To:
#   PermitRootLogin no

# Also change:
#   PasswordAuthentication yes
# To:
#   PasswordAuthentication no

# Save and exit:
# Ctrl+O (save), Enter, Ctrl+X (exit)

# Restart SSH service
sudo systemctl restart sshd
```

---

### **Step 5: Setup Firewall**

```bash
# Allow SSH (port 22)
sudo ufw allow OpenSSH

# Allow HTTP (port 80)
sudo ufw allow 80/tcp

# Allow HTTPS (port 443)
sudo ufw allow 443/tcp

# Enable firewall
sudo ufw enable
# Type 'y' and press Enter when prompted

# Check firewall status
sudo ufw status

# Should show:
# Status: active
# To                         Action      From
# --                         ------      ----
# OpenSSH                    ALLOW       Anywhere
# 80/tcp                     ALLOW       Anywhere
# 443/tcp                    ALLOW       Anywhere
```

---

<a name="phase-2"></a>
## 📦 PHASE 2: Install Required Software

### **Step 6: Install Docker & Docker Compose**

**6.1: Install Docker**
```bash
# Download and run Docker installation script
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# Add your user to docker group (avoid using sudo with docker)
sudo usermod -aG docker ${USER}

# Apply group changes
newgrp docker

# Verify Docker installation
docker --version
# Should show: Docker version 24.x.x
```

**6.2: Install Docker Compose**
```bash
# Download Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

# Make it executable
sudo chmod +x /usr/local/bin/docker-compose

# Verify installation
docker-compose --version
# Should show: Docker Compose version v2.x.x
```

---

### **Step 7: Install Node.js & npm**

```bash
# Add NodeSource repository for Node.js 20.x LTS
curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -

# Install Node.js (includes npm)
sudo apt-get install -y nodejs

# Verify installations
node --version
# Should show: v20.x.x

npm --version
# Should show: 10.x.x
```

---

### **Step 8: Install Git**

```bash
# Install Git
sudo apt install git -y

# Verify installation
git --version
# Should show: git version 2.x.x

# Configure Git (optional but recommended)
git config --global user.name "Your Name"
git config --global user.email "your-email@example.com"
```

---

<a name="phase-3"></a>
## 🌐 PHASE 3: Setup Domain & SSL

### **Step 9: Point Domain to VPS**

**If you have a domain (e.g., yourdomain.com):**

1. **Login to your domain registrar** (GoDaddy, Namecheap, Google Domains, etc.)

2. **Navigate to DNS settings** for your domain

3. **Add/Update A Records:**

   **Record 1 (Root domain):**
   - Type: `A`
   - Name: `@` or leave blank
   - Value: `YOUR_VPS_IP`
   - TTL: `3600` (or default)

   **Record 2 (www subdomain):**
   - Type: `A`
   - Name: `www`
   - Value: `YOUR_VPS_IP`
   - TTL: `3600` (or default)

4. **Save changes**

5. **Wait for DNS propagation** (5-30 minutes, sometimes up to 48 hours)

6. **Verify DNS:**
   ```bash
   # Check if domain points to your VPS
   nslookup yourdomain.com
   
   # Should show your VPS IP address
   ```

**If you DON'T have a domain yet:**
- You can use your VPS IP for testing
- **Recommended**: Buy a domain ($10-15/year) from:
  - Namecheap.com
  - Google Domains
  - Cloudflare Registrar
  - Porkbun.com

---

### **Step 10: Install Nginx (Reverse Proxy)**

```bash
# Install Nginx
sudo apt install nginx -y

# Start Nginx
sudo systemctl start nginx

# Enable Nginx to start on boot
sudo systemctl enable nginx

# Check Nginx status
sudo systemctl status nginx
# Should show: active (running)

# Test by visiting your VPS IP in browser
# http://YOUR_VPS_IP
# You should see: "Welcome to nginx!"
```

---

### **Step 11: Install Certbot (Free SSL Certificates)**

```bash
# Install Certbot and Nginx plugin
sudo apt install certbot python3-certbot-nginx -y

# Get SSL certificate for your domain
sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com

# Follow the prompts:
# 1. Enter email address (for renewal notifications)
# 2. Agree to Terms of Service (type 'Y')
# 3. Share email with EFF? (optional, type 'Y' or 'N')
# 4. Choose redirect option:
#    - Option 2: Redirect HTTP to HTTPS (Recommended)

# Certbot will:
# - Obtain SSL certificate from Let's Encrypt
# - Automatically configure Nginx
# - Set up auto-renewal

# Test auto-renewal
sudo certbot renew --dry-run
# Should show: Congratulations, all simulated renewals succeeded
```

**SSL Certificate Details:**
- **Certificates located at**: `/etc/letsencrypt/live/yourdomain.com/`
- **Valid for**: 90 days
- **Auto-renewal**: Configured automatically via systemd timer
- **Manual renewal** (if needed): `sudo certbot renew`

---

<a name="phase-4"></a>
## 🚢 PHASE 4: Deploy Your Application

### **Step 12: Clone Your Repository**

```bash
# Create directory for applications
mkdir -p ~/apps
cd ~/apps

# Clone your Vision Forge Studio repository
git clone https://github.com/YOUR_USERNAME/vision-forge-studio.git
cd vision-forge-studio

# Verify files
ls -la
# Should show: package.json, next.config.ts, etc.
```

---

### **Step 13: Create Environment Variables**

```bash
# Create .env file
nano .env
```

**Paste this configuration** (replace with your actual values):

```env
# Database Configuration (Docker PostgreSQL)
DATABASE_URL="postgresql://postgres:CHANGE_THIS_PASSWORD@postgres:5432/visionforge?schema=public"
DIRECT_URL="postgresql://postgres:CHANGE_THIS_PASSWORD@postgres:5432/visionforge?schema=public"

# NextAuth Configuration
NEXTAUTH_URL=https://yourdomain.com
NEXTAUTH_SECRET=GENERATE_RANDOM_STRING_HERE

# Supabase Configuration (for file storage)
NEXT_PUBLIC_SUPABASE_URL=https://your-project.supabase.co
NEXT_PUBLIC_SUPABASE_ANON_KEY=your_supabase_anon_key_here
SUPABASE_SERVICE_ROLE_KEY=your_supabase_service_role_key_here

# Email Configuration (Optional - for contact forms)
EMAIL_SERVER_HOST=smtp.gmail.com
EMAIL_SERVER_PORT=587
EMAIL_SERVER_USER=your-email@gmail.com
EMAIL_SERVER_PASSWORD=your_gmail_app_password
EMAIL_FROM=noreply@yourdomain.com

# Environment
NODE_ENV=production
```

**Important Security Notes:**

1. **Generate NEXTAUTH_SECRET:**
   ```bash
   openssl rand -base64 32
   ```
   Copy the output and paste it in `.env`

2. **Strong Database Password:**
   ```bash
   openssl rand -base64 24
   ```
   Use this for `CHANGE_THIS_PASSWORD` in DATABASE_URL

3. **Gmail App Password** (if using Gmail):
   - Go to Google Account Settings
   - Security → 2-Step Verification → App passwords
   - Generate app password
   - Use that password (not your Gmail password)

**Save and exit:**
- Press `Ctrl+O` (save)
- Press `Enter`
- Press `Ctrl+X` (exit)

---

### **Step 14: Create Docker Compose Configuration**

```bash
# Create docker-compose.yml
nano docker-compose.yml
```

**Paste this configuration:**

```yaml
version: '3.8'

services:
  # PostgreSQL Database Container
  postgres:
    image: postgres:16-alpine
    container_name: visionforge-db
    restart: unless-stopped
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: CHANGE_THIS_PASSWORD  # Match .env DATABASE_URL password
      POSTGRES_DB: visionforge
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - visionforge-network
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 10s
      timeout: 5s
      retries: 5

  # Next.js Web Application Container
  web:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: visionforge-web
    restart: unless-stopped
    ports:
      - "3000:3000"
    environment:
      - NODE_ENV=production
    env_file:
      - .env
    depends_on:
      postgres:
        condition: service_healthy
    networks:
      - visionforge-network
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:3000"]
      interval: 30s
      timeout: 10s
      retries: 3

# Named volumes for data persistence
volumes:
  postgres_data:
    driver: local

# Private network for container communication
networks:
  visionforge-network:
    driver: bridge
```

**Save and exit** (Ctrl+O, Enter, Ctrl+X)

---

### **Step 15: Create Dockerfile**

```bash
# Create Dockerfile
nano Dockerfile
```

**Paste this configuration:**

```dockerfile
# ============================================
# Build Stage - Compile and build the app
# ============================================
FROM node:20-alpine AS builder

# Set working directory
WORKDIR /app

# Copy dependency files
COPY package*.json ./
COPY prisma ./prisma/

# Install dependencies
RUN npm ci --only=production

# Copy source code
COPY . .

# Generate Prisma client
RUN npx prisma generate

# Build Next.js application
RUN npm run build

# ============================================
# Production Stage - Run the app
# ============================================
FROM node:20-alpine AS runner

# Set working directory
WORKDIR /app

# Set production environment
ENV NODE_ENV=production

# Install curl for healthcheck
RUN apk add --no-cache curl

# Copy built application from builder
COPY --from=builder /app/public ./public
COPY --from=builder /app/.next/standalone ./
COPY --from=builder /app/.next/static ./.next/static
COPY --from=builder /app/prisma ./prisma
COPY --from=builder /app/node_modules/.prisma ./node_modules/.prisma

# Expose application port
EXPOSE 3000

# Start the application
CMD ["node", "server.js"]
```

**Save and exit** (Ctrl+O, Enter, Ctrl+X)

---

### **Step 16: Update next.config.ts for Docker**

```bash
# Edit Next.js configuration
nano next.config.ts
```

**Find the `nextConfig` object and add `output: 'standalone'`:**

```typescript
const nextConfig: NextConfig = {
  output: 'standalone',  // --- ADD THIS LINE ---
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname:
          process.env.NEXT_PUBLIC_SUPABASE_URL?.replace("https://", "") ||
          "thzuvfpeumhtrjvnniwx.supabase.co",
        port: "",
        pathname: "/storage/v1/object/public/**",
      },
    ],
  },
  async headers() {
    return [
      // ...existing headers
    ];
  },
};

export default nextConfig;
```

**Save and exit** (Ctrl+O, Enter, Ctrl+X)

---

### **Step 17: Build and Start Docker Containers**

```bash
# Build Docker images
# This takes 5-10 minutes the first time
docker-compose build

# You'll see output like:
# Building web...
# Step 1/15 : FROM node:20-alpine AS builder
# ...downloading layers...
# Successfully built abc123def456

# Start containers in detached mode
docker-compose up -d

# Check if containers are running
docker ps

# Should show both containers:
# - visionforge-web (port 3000)
# - visionforge-db (no external port)

# View logs (optional)
docker-compose logs -f web
# Press Ctrl+C to stop viewing logs
```

---

### **Step 18: Run Database Migrations**

```bash
# Run Prisma migrations inside the web container
docker-compose exec web npx prisma migrate deploy

# You should see:
# Applying migration `20241011094609_updated_database`
# Database synchronized successfully

# Optional: Seed initial data (if you have seed script)
docker-compose exec web npx prisma db seed
```

---

### **Step 19: Configure Nginx as Reverse Proxy**

**19.1: Remove Default Nginx Configuration**
```bash
# Remove default site
sudo rm /etc/nginx/sites-enabled/default
```

**19.2: Create New Site Configuration**
```bash
# Create config file for Vision Forge Studio
sudo nano /etc/nginx/sites-available/visionforge
```

**Paste this configuration** (replace `yourdomain.com` with your actual domain):

```nginx
# Upstream configuration for Next.js app
upstream visionforge_app {
    server 127.0.0.1:3000;
    keepalive 64;
}

# HTTP server - Redirect to HTTPS
server {
    listen 80;
    listen [::]:80;
    server_name yourdomain.com www.yourdomain.com;

    # Redirect all HTTP traffic to HTTPS
    return 301 https://$server_name$request_uri;
}

# HTTPS server - Main configuration
server {
    listen 443 ssl http2;
    listen [::]:443 ssl http2;
    server_name yourdomain.com www.yourdomain.com;

    # SSL Certificates (Certbot will update these paths)
    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
    
    # SSL Configuration
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;
    ssl_prefer_server_ciphers on;
    
    # Security Headers
    add_header X-Frame-Options "DENY" always;
    add_header X-Content-Type-Options "nosniff" always;
    add_header X-XSS-Protection "1; mode=block" always;
    add_header Referrer-Policy "strict-origin-when-cross-origin" always;
    add_header Permissions-Policy "camera=(), microphone=(), geolocation=()" always;

    # Client body size limit (for file uploads)
    client_max_body_size 10M;

    # Logging
    access_log /var/log/nginx/visionforge_access.log;
    error_log /var/log/nginx/visionforge_error.log;

    # Main proxy location
    location / {
        proxy_pass http://visionforge_app;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_cache_bypass $http_upgrade;
        
        # Timeouts
        proxy_connect_timeout 60s;
        proxy_send_timeout 60s;
        proxy_read_timeout 60s;
    }

    # Cache static Next.js assets
    location /_next/static/ {
        proxy_pass http://visionforge_app;
        add_header Cache-Control "public, max-age=31536000, immutable";
    }

    # Cache public static files
    location /static/ {
        proxy_pass http://visionforge_app;
        add_header Cache-Control "public, max-age=31536000, immutable";
    }

    # Favicon
    location = /favicon.ico {
        proxy_pass http://visionforge_app;
        access_log off;
        log_not_found off;
    }

    # Robots.txt
    location = /robots.txt {
        proxy_pass http://visionforge_app;
        access_log off;
        log_not_found off;
    }
}
```

**Save and exit** (Ctrl+O, Enter, Ctrl+X)

**19.3: Enable Site and Test Configuration**
```bash
# Create symbolic link to enable site
sudo ln -s /etc/nginx/sites-available/visionforge /etc/nginx/sites-enabled/

# Test Nginx configuration for syntax errors
sudo nginx -t

# Should show:
# nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
# nginx: configuration file /etc/nginx/nginx.conf test is successful

# Reload Nginx to apply changes
sudo systemctl reload nginx

# Check Nginx status
sudo systemctl status nginx
```

---

### **Step 20: Test Your Website**

🎉 **Your website should now be live!**

**Visit in your browser:**
- https://yourdomain.com
- https://www.yourdomain.com

**What to check:**
- ✅ Homepage loads correctly
- ✅ SSL certificate is valid (padlock icon in browser)
- ✅ Navigation works
- ✅ Login/authentication works
- ✅ Admin dashboard accessible
- ✅ Database queries work
- ✅ File uploads work (if using Supabase)

**If something doesn't work, check logs:**
```bash
# Docker container logs
docker-compose logs -f web

# Nginx logs
sudo tail -f /var/log/nginx/visionforge_error.log

# System logs
sudo journalctl -u nginx -f
```

---

<a name="phase-5"></a>
## 🔧 PHASE 5: Maintenance & Management

### **Essential Docker Commands**

```bash
# ========================================
# Container Management
# ========================================

# View running containers
docker ps

# View all containers (including stopped)
docker ps -a

# Start containers
docker-compose up -d

# Stop containers
docker-compose down

# Restart containers
docker-compose restart

# Restart specific container
docker-compose restart web

# ========================================
# Logs and Debugging
# ========================================

# View real-time logs (all containers)
docker-compose logs -f

# View logs for specific container
docker-compose logs -f web
docker-compose logs -f postgres

# View last 100 lines
docker-compose logs --tail=100 web

# ========================================
# Execute Commands Inside Containers
# ========================================

# Open shell in web container
docker-compose exec web sh

# Run Prisma commands
docker-compose exec web npx prisma studio
docker-compose exec web npx prisma migrate deploy

# Database shell
docker-compose exec postgres psql -U postgres visionforge

# ========================================
# Resource Management
# ========================================

# View container resource usage (CPU, Memory)
docker stats

# Remove stopped containers
docker container prune

# Remove unused images
docker image prune -a

# Clean up everything (careful!)
docker system prune -a --volumes
```

---

### **Update/Deploy New Code**

**When you push changes to GitHub:**

```bash
# SSH into your VPS
ssh yourname@YOUR_VPS_IP

# Navigate to project
cd ~/apps/vision-forge-studio

# Pull latest code
git pull origin main

# If package.json changed:
docker-compose down
docker-compose build
docker-compose up -d

# If only code changed (no dependencies):
docker-compose restart web

# If database schema changed:
docker-compose exec web npx prisma migrate deploy

# Check if everything works
docker-compose logs -f web
```

---

### **Database Backup Strategy**

**Create Automated Backup Script:**

```bash
# Create backup directory
mkdir -p ~/backups

# Create backup script
nano ~/backups/backup-db.sh
```

**Paste this script:**

```bash
#!/bin/bash

# Configuration
DATE=$(date +%Y%m%d_%H%M%S)
BACKUP_DIR=~/backups
DB_NAME=visionforge
DB_USER=postgres
CONTAINER=visionforge-db
RETENTION_DAYS=7

# Create backup
echo "Starting backup at $(date)"
docker exec $CONTAINER pg_dump -U $DB_USER $DB_NAME | gzip > $BACKUP_DIR/backup_$DATE.sql.gz

# Check if backup was successful
if [ $? -eq 0 ]; then
    echo "Backup completed successfully: backup_$DATE.sql.gz"
    
    # Delete backups older than RETENTION_DAYS
    find $BACKUP_DIR -name "backup_*.sql.gz" -mtime +$RETENTION_DAYS -delete
    echo "Old backups cleaned up"
else
    echo "Backup failed!" >&2
    exit 1
fi
```

**Make script executable:**
```bash
chmod +x ~/backups/backup-db.sh

# Test backup manually
~/backups/backup-db.sh

# Check backup was created
ls -lh ~/backups/
```

**Schedule Automatic Daily Backups:**

```bash
# Open crontab editor
crontab -e

# Add this line (runs daily at 2 AM):
0 2 * * * /home/yourname/backups/backup-db.sh >> /home/yourname/backups/backup.log 2>&1

# Save and exit
```

**Restore from Backup:**

```bash
# List available backups
ls -lh ~/backups/

# Restore specific backup
gunzip < ~/backups/backup_20241015_020000.sql.gz | docker exec -i visionforge-db psql -U postgres visionforge
```

---

### **Security Updates**

**Enable Automatic Security Updates:**

```bash
# Install unattended-upgrades
sudo apt install unattended-upgrades -y

# Configure automatic updates
sudo dpkg-reconfigure --priority=low unattended-upgrades

# Select "Yes" when prompted

# Verify configuration
sudo systemctl status unattended-upgrades
```

**Manual System Updates:**

```bash
# Update package list
sudo apt update

# Upgrade packages
sudo apt upgrade -y

# Clean up
sudo apt autoremove -y
sudo apt autoclean

# Reboot if kernel updated
sudo reboot
```

---

<a name="phase-6"></a>
## 📊 PHASE 6: Monitoring & Optimization

### **System Monitoring Tools**

**Install htop (Interactive Process Viewer):**
```bash
# Install htop
sudo apt install htop -y

# Run htop
htop

# Keyboard shortcuts:
# F10 or q - Exit
# F6 - Sort by column
# / - Search
```

**Monitor System Resources:**

```bash
# Check disk usage
df -h

# Check memory usage
free -h

# Check CPU info
lscpu

# Check running processes
ps aux | grep node
ps aux | grep postgres

# Monitor Docker container stats
docker stats

# Check system uptime
uptime
```

**Monitor Logs:**

```bash
# Real-time Nginx access logs
sudo tail -f /var/log/nginx/visionforge_access.log

# Real-time Nginx error logs
sudo tail -f /var/log/nginx/visionforge_error.log

# System logs
sudo journalctl -f

# Docker logs
docker-compose logs -f
```

---

### **Performance Optimization**

**1. Enable Nginx Gzip Compression:**

```bash
sudo nano /etc/nginx/nginx.conf
```

**Add/uncomment these lines in http block:**

```nginx
http {
    # ...existing config...
    
    # Gzip Settings
    gzip on;
    gzip_vary on;
    gzip_proxied any;
    gzip_comp_level 6;
    gzip_types text/plain text/css text/xml text/javascript application/json application/javascript application/xml+rss application/rss+xml font/truetype font/opentype application/vnd.ms-fontobject image/svg+xml;
    gzip_disable "msie6";
}
```

**Save, test, and reload:**
```bash
sudo nginx -t
sudo systemctl reload nginx
```

**2. Optimize Docker:**

```bash
# Limit container resources (edit docker-compose.yml)
nano ~/apps/vision-forge-studio/docker-compose.yml
```

**Add resource limits:**

```yaml
services:
  web:
    # ...existing config...
    deploy:
      resources:
        limits:
          cpus: '1.0'
          memory: 512M
        reservations:
          cpus: '0.5'
          memory: 256M
          
  postgres:
    # ...existing config...
    deploy:
      resources:
        limits:
          cpus: '0.5'
          memory: 256M
```

---

<a name="future-backends"></a>
## 🚀 Adding Future App Backends (Mobile Apps, APIs)

### **Example: Deploy a New Mobile App Backend**

**1. Create Project Directory:**
```bash
cd ~/apps
mkdir my-mobile-app-api
cd my-mobile-app-api
```

**2. Create docker-compose.yml:**
```bash
nano docker-compose.yml
```

```yaml
version: '3.8'

services:
  api:
    build: .
    container_name: mobile-app-api
    restart: unless-stopped
    ports:
      - "3001:3001"  # Different port from main site
    environment:
      - NODE_ENV=production
      - DATABASE_URL=postgresql://postgres:password@visionforge-db:5432/mobile_app_db
    networks:
      - visionforge_visionforge-network  # Connect to main network
    depends_on:
      - visionforge-db

networks:
  visionforge_visionforge-network:
    external: true  # Use existing network from main app
```

**3. Add Nginx Configuration:**
```bash
sudo nano /etc/nginx/sites-available/mobile-app-api
```

```nginx
upstream mobile_app_api {
    server 127.0.0.1:3001;
}

server {
    listen 443 ssl http2;
    server_name api.yourdomain.com;
    
    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
    
    location / {
        proxy_pass http://mobile_app_api;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
```

```bash
# Enable site
sudo ln -s /etc/nginx/sites-available/mobile-app-api /etc/nginx/sites-enabled/

# Test and reload
sudo nginx -t
sudo systemctl reload nginx
```

**4. Get SSL for Subdomain:**
```bash
sudo certbot --nginx -d api.yourdomain.com
```

**5. Deploy:**
```bash
cd ~/apps/my-mobile-app-api
docker-compose up -d
```

🎉 **Your API is now live at https://api.yourdomain.com**

---

<a name="troubleshooting"></a>
## 🔧 Troubleshooting Guide

### **Website Not Loading**

**Check containers:**
```bash
docker ps
# If containers not running:
docker-compose up -d
```

**Check logs:**
```bash
docker-compose logs -f web
sudo tail -f /var/log/nginx/visionforge_error.log
```

**Check Nginx:**
```bash
sudo nginx -t
sudo systemctl status nginx
sudo systemctl restart nginx
```

**Check firewall:**
```bash
sudo ufw status
# If HTTPS blocked:
sudo ufw allow 443/tcp
```

---

### **Database Connection Errors**

**Check database container:**
```bash
docker-compose logs postgres
docker-compose restart postgres
```

**Test connection:**
```bash
docker-compose exec postgres psql -U postgres -d visionforge

# If successful, you'll see:
# visionforge=#
```

**Verify DATABASE_URL:**
```bash
cat .env | grep DATABASE_URL
# Should be: postgresql://postgres:password@postgres:5432/visionforge
```

---

### **SSL Certificate Issues**

**Check certificate:**
```bash
sudo certbot certificates
```

**Renew manually:**
```bash
sudo certbot renew
sudo systemctl reload nginx
```

**Force renew:**
```bash
sudo certbot renew --force-renewal
```

---

### **Out of Memory/Disk Space**

**Check memory:**
```bash
free -h
```

**Check disk:**
```bash
df -h
```

**Clean up Docker:**
```bash
docker system prune -a
docker volume prune
```

**Clean up system:**
```bash
sudo apt autoremove -y
sudo apt autoclean
```

**If still low, upgrade VPS:**
- 2GB → 4GB RAM plan
- Or add swap space (temporary fix)

---

### **Containers Keep Restarting**

**Check logs for errors:**
```bash
docker-compose logs --tail=100 web
docker-compose logs --tail=100 postgres
```

**Common causes:**
- ❌ Wrong environment variables
- ❌ Database connection failure
- ❌ Port already in use
- ❌ Out of memory

**Fix port conflicts:**
```bash
# Check what's using port 3000
sudo lsof -i :3000

# Kill process if needed
sudo kill -9 <PID>
```

---

<a name="quick-reference"></a>
## 📝 Quick Reference Commands

### **Daily Operations**

```bash
# SSH into server
ssh yourname@YOUR_VPS_IP

# Check website status
docker ps
docker-compose logs --tail=50 web

# Restart website
docker-compose restart web

# View live logs
docker-compose logs -f web

# Update website
cd ~/apps/vision-forge-studio
git pull
docker-compose restart web
```

### **Weekly Maintenance**

```bash
# Update system
sudo apt update && sudo apt upgrade -y

# Clean up Docker
docker system prune -a

# Check disk space
df -h

# Check memory
free -h

# Verify backups
ls -lh ~/backups/
```

### **Emergency Commands**

```bash
# Stop everything
docker-compose down

# Restart everything
docker-compose up -d

# Full rebuild
docker-compose down
docker-compose build --no-cache
docker-compose up -d

# Restore from backup
gunzip < ~/backups/backup_YYYYMMDD_HHMMSS.sql.gz | docker exec -i visionforge-db psql -U postgres visionforge
```

---

## 💰 Cost Breakdown

| Item | Monthly Cost | Annual Cost |
|------|-------------|-------------|
| VPS (2GB RAM) | $10-12 | $120-144 |
| Domain Name | ~$1 | ~$12 |
| SSL Certificate | $0 (Let's Encrypt) | $0 |
| Backups | $0 (on VPS) | $0 |
| **Total** | **$11-13** | **$132-156** |

**Compare to alternatives:**
- Vercel Pro: $20/month = $240/year
- Supabase Pro: $25/month = $300/year
- Render (per backend): $7-25/month each
- **VPS handles unlimited projects!**

---

## 📚 Resources & Learning

### **Documentation:**
- [Docker Docs](https://docs.docker.com/)
- [Docker Compose Docs](https://docs.docker.com/compose/)
- [Nginx Docs](https://nginx.org/en/docs/)
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [Ubuntu Server Guide](https://ubuntu.com/server/docs)

### **Tutorials:**
- [DigitalOcean Community Tutorials](https://www.digitalocean.com/community/tutorials)
- [Linode Guides](https://www.linode.com/docs/)
- [Vultr Docs](https://www.vultr.com/docs/)

### **Tools:**
- [SSL Labs Test](https://www.ssllabs.com/ssltest/) - Test SSL configuration
- [GTmetrix](https://gtmetrix.com/) - Test website performance
- [SecurityHeaders.com](https://securityheaders.com/) - Check security headers

---

## ✅ Final Deployment Checklist

Before going live, verify:

- [ ] VPS purchased and configured
- [ ] SSH key setup, root login disabled
- [ ] Firewall configured (ports 22, 80, 443)
- [ ] Docker & Docker Compose installed
- [ ] Node.js & Git installed
- [ ] Domain pointing to VPS IP
- [ ] SSL certificate installed and auto-renewing
- [ ] Environment variables configured
- [ ] Docker containers running
- [ ] Database migrations applied
- [ ] Nginx configured as reverse proxy
- [ ] Website accessible via HTTPS
- [ ] All features tested (login, uploads, etc.)
- [ ] Automated backups scheduled
- [ ] Monitoring tools installed
- [ ] Security headers verified
- [ ] Performance optimized

---

## 🎉 Congratulations!

You've successfully deployed a production-ready website on your own VPS!

**What you've achieved:**
- ✅ Full control over your infrastructure
- ✅ Professional DevOps skills
- ✅ Massive cost savings
- ✅ Scalable architecture for future projects
- ✅ Industry-standard deployment practices

**Next Steps:**
1. Monitor your website for a few days
2. Set up additional monitoring (optional: Uptime Robot, New Relic)
3. Plan your next backend deployment
4. Keep learning and improving!

---

## 📞 Support

**If you encounter issues:**

1. **Check logs first:**
   ```bash
   docker-compose logs -f
   sudo tail -f /var/log/nginx/visionforge_error.log
   ```

2. **Search for error messages:**
   - Google the error
   - Check Stack Overflow
   - Read DigitalOcean Community

3. **Common solutions:**
   - Restart containers: `docker-compose restart`
   - Rebuild: `docker-compose build --no-cache`
   - Check environment variables
   - Verify file permissions

**Remember:** Every error is a learning opportunity! 🚀

---

**Document Version**: 1.0  
**Last Updated**: October 2025  
**Maintained by**: Vision Forge Studio Team

---

*Save this document for future reference. You'll use these commands repeatedly!*