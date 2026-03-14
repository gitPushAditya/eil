# Setting Up a VPS

## Step 1: Choose and Purchase a VPS

1. **Select a VPS Provider**: Choose a reputable VPS provider such as DigitalOcean, Linode, AWS, or Vultr.
2. **Select a Plan**: Choose a plan that fits your needs based on CPU, RAM, storage, and bandwidth.
3. **Create an Account**: Sign up for an account on the chosen provider's website.
4. **Purchase the VPS**: Follow the provider's instructions to purchase and set up your VPS.

### What to choose :- 

- **OS**: Ubuntu 22.04 LTS(most popular, stable and secure)
- **Size**: 2 GB RAM minimum for website + database + future backends
- **Location**: Closest to your target audience

Note: VPS will ask for ssh key that you can add in profile section(for vultr), we will generate this key next. 

---

## Step 2: Generating SSH key and adding - In your computer

Open powershell and run : 

### Generate the key 

```
ssh-keygen -t ed25519 -C "your-email@example.com"
```

When Prompted: 
- Press Enter to save in default location (C:\Users\YourName\.ssh\id_ed25519)
- Enter a passphrase (optional but recommended, something long and rememberable)

### Copy your public key

```
Get-Content $env:USERPROFILE\.ssh\id_ed25519.pub | clip
```

Now paste this key in your VPS provider's SSH key section - Ctrl + V

---

## Step 3: Connect to your VPS

```
ssh root@YOUR_VPS_IP
```

- Type 'yes' when asked about fingerprint
- Enter SSH key passphrase if you set one

Note: You are now inside your VPS

--- 

## Step 4: Initial Server Security Setup

### Update System

```
apt update && apt upgrade -y
```

### Create a new user(replace yourname)

```
adduser yourname
```

- Enter password and details when prompted

### Give sudo privileges

```
usermod -aG sudo yourname
```

### Setup SSH for new user

```
mkdir -p /home/yourname/.ssh
cp /root/.ssh/authorized_keys /home/yourname/.ssh/
chown -R yourname:yourname /home/yourname/.ssh
chmod 700 /home/yourname/.ssh
chmod 600 /home/yourname/.ssh/authorized_keys
```

### Exit

```
exit
```

### Reconnect as new user

```
ssh yourname@YOUR_VPS_IP
```

### Now disable root login

```
sudo nano /etc/ssh/sshd_config
```

- Find and change these lines 

```
PermitRootLogin no
PasswordAuthentication no
```

Note: If these lines are commented, un-comment them

- Save - Ctrl + O - Enter

- Exit - Ctrl + X

### restart SSH

```
sudo systemctl restart sshd
```

---

## Setup Firewall

- Allow SSH, HTTP, HTTPS

```
sudo ufw allow OpenSSH
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
```

- Enable firewall 

```
sudo ufw enable
```

Note: Type 'y' and Enter

- Check status

```
sudo ufw status
```

---