# Deploy Application

## Create App Directory and Clone Repo

```
mkdir -p ~/apps
cd ~/apps
```

```
git clone https://github.com/gitPushAditya/vision-forge-studio-v2.git
cd vision-forge-studio-v2
```

---

## Create .env

```
nano .env
```

- Paste this (replace with your actual values):

```
# Database - using Docker PostgreSQL
DATABASE_URL="postgresql://postgres:your_strong_password_here@postgres:5432/visionforge?schema=public"
DIRECT_URL="postgresql://postgres:your_strong_password_here@postgres:5432/visionforge?schema=public"

# NextAuth
NEXTAUTH_URL=https://yourdomain.com
NEXTAUTH_SECRET=generate_a_random_32_char_string_here

# Supabase (for file storage only)
NEXT_PUBLIC_SUPABASE_URL=your_supabase_url
NEXT_PUBLIC_SUPABASE_ANON_KEY=your_supabase_key
SUPABASE_SERVICE_ROLE_KEY=your_service_role_key

# Email (optional - for contact forms)
EMAIL_SERVER_HOST=smtp.gmail.com
EMAIL_SERVER_PORT=587
EMAIL_SERVER_USER=your-email@gmail.com
EMAIL_SERVER_PASSWORD=your-app-password
EMAIL_FROM=noreply@yourdomain.com

NODE_ENV=production
```

---

## Generate NEXTAUTH_SECRET

```
openssl rand -base64 32
```

---

## Create Docker Compose File

```
nano docker-compose.yml
```

- Paste this

```
version: '3.8'

services:
  # PostgreSQL Database
  postgres:
    image: postgres:16-alpine
    container_name: visionforge-db
    restart: unless-stopped
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: your_strong_password_here  # Same as in .env
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

  # Next.js Application
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

volumes:
  postgres_data:

networks:
  visionforge-network:
    driver: bridge
```

---

## Create Docker File

```
nano Dockerfile
```

- Paste this

```
# Build stage
FROM node:20-alpine AS builder

WORKDIR /app

# Copy package files
COPY package*.json ./
COPY prisma ./prisma/

# Install dependencies
RUN npm ci

# Copy source code
COPY . .

# Generate Prisma client
RUN npx prisma generate

# Build Next.js app
RUN npm run build

# Production stage
FROM node:20-alpine AS runner

WORKDIR /app

# Set environment
ENV NODE_ENV=production

# Copy necessary files from builder
COPY --from=builder /app/public ./public
COPY --from=builder /app/.next/standalone ./
COPY --from=builder /app/.next/static ./.next/static
COPY --from=builder /app/prisma ./prisma
COPY --from=builder /app/node_modules/.prisma ./node_modules/.prisma

# Expose port
EXPOSE 3000

# Start the application
CMD ["node", "server.js"]
```

---

## Update Next.config

```
nano next.config.ts
```

- Add output: 'standalone' to the config:

```
const nextConfig: NextConfig = {
  output: 'standalone',  // ADD THIS LINE
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: process.env.NEXT_PUBLIC_SUPABASE_URL?.replace('https://', '') || '',
      },
    ],
  },
  // ... rest of your config
};
```

---

## Build and start docker containers

```
# Build Docker images (this takes 5-10 minutes first time)
docker-compose build

# Start containers
docker-compose up -d

# Check if containers are running
docker ps

# View logs
docker-compose logs -f web
# Press Ctrl+C to stop viewing logs
```

--- 


## Run Database Migration

```
# Run migrations inside the web container
docker-compose exec web npx prisma migrate deploy

# Optional: Seed database if you have seed data
docker-compose exec web npx prisma db seed
```

---

## Configure Nginx reverse Proxy

```
# Create Nginx config for your site
sudo nano /etc/nginx/sites-available/visionforge
```

- Paste this 

```
upstream visionforge_app {
    server 127.0.0.1:3000;
}

server {
    listen 80;
    listen [::]:80;
    server_name yourdomain.com www.yourdomain.com;

    # Redirect HTTP to HTTPS
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    listen [::]:443 ssl http2;
    server_name yourdomain.com www.yourdomain.com;

    # SSL certificates (Certbot will add these)
    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
    
    # Security headers
    add_header X-Frame-Options "SAMEORIGIN" always;
    add_header X-Content-Type-Options "nosniff" always;
    add_header X-XSS-Protection "1; mode=block" always;
    add_header Referrer-Policy "strict-origin-when-cross-origin" always;

    # Client max body size for file uploads
    client_max_body_size 10M;

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
    }

    # Cache static assets
    location /_next/static/ {
        proxy_pass http://visionforge_app;
        add_header Cache-Control "public, max-age=31536000, immutable";
    }

    location /static/ {
        proxy_pass http://visionforge_app;
        add_header Cache-Control "public, max-age=31536000, immutable";
    }
}
```

- Save - Ctrl + O, Enter, Ctrl + X

```
# Enable site
sudo ln -s /etc/nginx/sites-available/visionforge /etc/nginx/sites-enabled/

# Remove default Nginx site
sudo rm /etc/nginx/sites-enabled/default

# Test Nginx config
sudo nginx -t

# Reload Nginx
sudo systemctl reload nginx
```
--- 

## Test

Visit https://yourdomain.com - Your website should be live! 🎉

---


