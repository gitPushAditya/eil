# Setup Domain & SSL

- Go to your domain registrar (GoDaddy, Namecheap, etc.)
- Find DNS settings

- Add A Record:
```
Name: @ (root domain)
Type: A
Value: YOUR_VPS_IP
TTL: 3600
```

- Add A Record:
```
Name: www
Type: A
Value: YOUR_VPS_IP
TTL: 3600
Wait 5-30 minutes for DNS propagation.
```