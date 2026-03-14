# Install Certbot

```
sudo apt install certbot python3-certbot-nginx -y
```

## Get SSL certificate (replace with your domain)

```
sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com
```

- Follow prompts:
    - Enter email
    - Agree to terms
    - Choose redirect HTTP to HTTPS (option 2)


Note: Auto-renewal is set up automatically

## Test renewal:
```
sudo certbot renew --dry-run
```

---