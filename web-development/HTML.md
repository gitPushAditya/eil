# HTML - HyperText Markup Language

HTML is the standard markup language for creating web pages and web applications. This guide covers modern HTML5 with semantic elements, accessibility, and production-ready practices.

## Setup and Environment

### Basic HTML5 Document Structure

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Page description for SEO">
    <meta name="keywords" content="relevant, keywords, for, seo">
    <meta name="author" content="Your Name">
    <title>Page Title</title>
    
    <!-- Open Graph for social media -->
    <meta property="og:title" content="Page Title">
    <meta property="og:description" content="Page description">
    <meta property="og:image" content="image-url.jpg">
    <meta property="og:url" content="https://yoursite.com">
    
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="/favicon.ico">
    
    <!-- CSS -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Content goes here -->
    
    <!-- JavaScript -->
    <script src="script.js"></script>
</body>
</html>
```

---

## Semantic HTML5 Elements

### Modern Page Structure

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Semantic HTML Example</title>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#services">Services</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section id="hero">
            <h1>Welcome to Our Website</h1>
            <p>Your journey starts here</p>
        </section>

        <section id="content">
            <article>
                <header>
                    <h2>Article Title</h2>
                    <time datetime="2024-01-15">January 15, 2024</time>
                </header>
                <p>Article content goes here...</p>
                <footer>
                    <p>By <strong>Author Name</strong></p>
                </footer>
            </article>
        </section>

        <aside>
            <h3>Related Links</h3>
            <ul>
                <li><a href="#">Link 1</a></li>
                <li><a href="#">Link 2</a></li>
            </ul>
        </aside>
    </main>

    <footer>
        <p>&copy; 2024 Your Company. All rights reserved.</p>
    </footer>
</body>
</html>
```

---

## Forms and Input Elements

### Modern Form Structure

```html
<form action="/submit" method="POST" novalidate>
    <fieldset>
        <legend>Personal Information</legend>
        
        <div class="form-group">
            <label for="fullName">Full Name *</label>
            <input 
                type="text" 
                id="fullName" 
                name="fullName" 
                required 
                autocomplete="name"
                aria-describedby="nameHelp"
            >
            <small id="nameHelp">Enter your full name</small>
        </div>

        <div class="form-group">
            <label for="email">Email Address *</label>
            <input 
                type="email" 
                id="email" 
                name="email" 
                required 
                autocomplete="email"
                pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
            >
        </div>

        <div class="form-group">
            <label for="phone">Phone Number</label>
            <input 
                type="tel" 
                id="phone" 
                name="phone" 
                autocomplete="tel"
                pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
                placeholder="123-456-7890"
            >
        </div>

        <div class="form-group">
            <label for="birthdate">Date of Birth</label>
            <input 
                type="date" 
                id="birthdate" 
                name="birthdate" 
                min="1900-01-01" 
                max="2024-12-31"
            >
        </div>

        <div class="form-group">
            <label for="country">Country</label>
            <select id="country" name="country" required>
                <option value="">Select a country</option>
                <option value="us">United States</option>
                <option value="ca">Canada</option>
                <option value="uk">United Kingdom</option>
                <option value="de">Germany</option>
            </select>
        </div>

        <div class="form-group">
            <label for="message">Message</label>
            <textarea 
                id="message" 
                name="message" 
                rows="4" 
                cols="50" 
                maxlength="500"
                placeholder="Enter your message here..."
            ></textarea>
        </div>

        <fieldset>
            <legend>Preferences</legend>
            <div class="checkbox-group">
                <input type="checkbox" id="newsletter" name="preferences" value="newsletter">
                <label for="newsletter">Subscribe to newsletter</label>
            </div>
            <div class="checkbox-group">
                <input type="checkbox" id="updates" name="preferences" value="updates">
                <label for="updates">Receive product updates</label>
            </div>
        </fieldset>

        <fieldset>
            <legend>Contact Method</legend>
            <div class="radio-group">
                <input type="radio" id="contactEmail" name="contactMethod" value="email" checked>
                <label for="contactEmail">Email</label>
            </div>
            <div class="radio-group">
                <input type="radio" id="contactPhone" name="contactMethod" value="phone">
                <label for="contactPhone">Phone</label>
            </div>
        </fieldset>
    </fieldset>

    <div class="form-actions">
        <button type="reset">Reset</button>
        <button type="submit">Submit</button>
    </div>
</form>
```

---

## Accessibility (a11y)

### ARIA Labels and Roles

```html
<!-- Skip navigation for screen readers -->
<a href="#main-content" class="skip-link">Skip to main content</a>

<nav role="navigation" aria-label="Main navigation">
    <ul>
        <li><a href="#home" aria-current="page">Home</a></li>
        <li><a href="#about">About</a></li>
        <li>
            <a href="#services" aria-expanded="false" aria-haspopup="true">
                Services
            </a>
            <ul role="menu" aria-label="Services submenu">
                <li role="menuitem"><a href="#web-design">Web Design</a></li>
                <li role="menuitem"><a href="#development">Development</a></li>
            </ul>
        </li>
    </ul>
</nav>

<main id="main-content" role="main">
    <section aria-labelledby="hero-heading">
        <h1 id="hero-heading">Welcome to Our Site</h1>
        <p>This is accessible content</p>
    </section>

    <!-- Modal example -->
    <div 
        role="dialog" 
        aria-labelledby="modal-title" 
        aria-describedby="modal-description"
        aria-modal="true"
        hidden
    >
        <h2 id="modal-title">Confirmation</h2>
        <p id="modal-description">Are you sure you want to proceed?</p>
        <button type="button" aria-label="Close dialog">×</button>
    </div>

    <!-- Live region for dynamic content -->
    <div aria-live="polite" aria-atomic="true" id="status-message"></div>
</main>
```

---

## Multimedia Elements

### Images with Responsive Design

```html
<!-- Responsive image with srcset -->
<img 
    src="image-800w.jpg" 
    srcset="
        image-400w.jpg 400w,
        image-800w.jpg 800w,
        image-1200w.jpg 1200w
    "
    sizes="(max-width: 480px) 400px,
           (max-width: 768px) 800px,
           1200px"
    alt="Descriptive alt text for accessibility"
    loading="lazy"
    decoding="async"
>

<!-- Picture element for art direction -->
<picture>
    <source media="(max-width: 768px)" srcset="mobile-image.jpg">
    <source media="(max-width: 1024px)" srcset="tablet-image.jpg">
    <img src="desktop-image.jpg" alt="Responsive image example">
</picture>

<!-- Figure with caption -->
<figure>
    <img src="chart.jpg" alt="Sales data visualization">
    <figcaption>
        Sales performance for Q4 2023 showing 25% growth
    </figcaption>
</figure>
```

### Video and Audio

```html
<!-- Video with multiple sources and controls -->
<video 
    controls 
    preload="metadata"
    poster="video-poster.jpg"
    width="800"
    height="450"
>
    <source src="video.mp4" type="video/mp4">
    <source src="video.webm" type="video/webm">
    <track 
        kind="subtitles" 
        src="subtitles-en.vtt" 
        srclang="en" 
        label="English"
        default
    >
    <track 
        kind="subtitles" 
        src="subtitles-es.vtt" 
        srclang="es" 
        label="Español"
    >
    <p>Your browser doesn't support video. 
       <a href="video.mp4">Download the video</a>
    </p>
</video>

<!-- Audio with controls -->
<audio controls preload="none">
    <source src="audio.mp3" type="audio/mpeg">
    <source src="audio.ogg" type="audio/ogg">
    <p>Your browser doesn't support audio. 
       <a href="audio.mp3">Download the audio</a>
    </p>
</audio>
```

---

## Data and Interactive Elements

### Tables with Accessibility

```html
<table>
    <caption>Quarterly Sales Report 2023</caption>
    <thead>
        <tr>
            <th scope="col">Product</th>
            <th scope="col">Q1</th>
            <th scope="col">Q2</th>
            <th scope="col">Q3</th>
            <th scope="col">Q4</th>
            <th scope="col">Total</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">Widget A</th>
            <td>$10,000</td>
            <td>$12,000</td>
            <td>$15,000</td>
            <td>$18,000</td>
            <td>$55,000</td>
        </tr>
        <tr>
            <th scope="row">Widget B</th>
            <td>$8,000</td>
            <td>$9,500</td>
            <td>$11,000</td>
            <td>$13,500</td>
            <td>$42,000</td>
        </tr>
    </tbody>
    <tfoot>
        <tr>
            <th scope="row">Total</th>
            <td>$18,000</td>
            <td>$21,500</td>
            <td>$26,000</td>
            <td>$31,500</td>
            <td>$97,000</td>
        </tr>
    </tfoot>
</table>
```

### Interactive Elements

```html
<!-- Details/Summary for collapsible content -->
<details>
    <summary>Click to expand FAQ</summary>
    <div>
        <h4>How do I reset my password?</h4>
        <p>You can reset your password by clicking the "Forgot Password" link on the login page.</p>
    </div>
</details>

<!-- Progress indicators -->
<progress value="70" max="100">70%</progress>
<meter value="0.7" min="0" max="1">70%</meter>

<!-- Data lists for autocomplete -->
<input type="text" list="browsers" name="browser">
<datalist id="browsers">
    <option value="Chrome">
    <option value="Firefox">
    <option value="Safari">
    <option value="Edge">
</datalist>
```

---

## Performance Optimization

### Resource Loading

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Optimized Page</title>
    
    <!-- Preload critical resources -->
    <link rel="preload" href="critical.css" as="style">
    <link rel="preload" href="hero-image.jpg" as="image">
    <link rel="preload" href="font.woff2" as="font" type="font/woff2" crossorigin>
    
    <!-- DNS prefetch for external domains -->
    <link rel="dns-prefetch" href="//fonts.googleapis.com">
    <link rel="dns-prefetch" href="//api.example.com">
    
    <!-- Preconnect to important origins -->
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    
    <!-- Critical CSS inline -->
    <style>
        /* Critical above-the-fold styles */
        body { margin: 0; font-family: Arial, sans-serif; }
        .hero { height: 100vh; background: #f0f0f0; }
    </style>
    
    <!-- Non-critical CSS loaded asynchronously -->
    <link rel="preload" href="styles.css" as="style" onload="this.onload=null;this.rel='stylesheet'">
    <noscript><link rel="stylesheet" href="styles.css"></noscript>
</head>
<body>
    <!-- Content -->
    
    <!-- Lazy load non-critical scripts -->
    <script>
        // Load non-critical JavaScript after page load
        window.addEventListener('load', function() {
            const script = document.createElement('script');
            script.src = 'non-critical.js';
            document.head.appendChild(script);
        });
    </script>
</body>
</html>
```

---

## SEO and Meta Tags

### Complete SEO Setup

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic Meta Tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Comprehensive description of your page content for search engines">
    <meta name="keywords" content="relevant, keywords, separated, by, commas">
    <meta name="author" content="Your Name or Company">
    <meta name="robots" content="index, follow">
    <meta name="language" content="English">
    
    <!-- Title -->
    <title>Your Page Title | Your Brand</title>
    
    <!-- Open Graph (Facebook, LinkedIn) -->
    <meta property="og:type" content="website">
    <meta property="og:title" content="Your Page Title">
    <meta property="og:description" content="Description for social media sharing">
    <meta property="og:image" content="https://yoursite.com/social-image.jpg">
    <meta property="og:url" content="https://yoursite.com/current-page">
    <meta property="og:site_name" content="Your Site Name">
    
    <!-- Twitter Cards -->
    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:site" content="@yourtwitterhandle">
    <meta name="twitter:creator" content="@yourtwitterhandle">
    <meta name="twitter:title" content="Your Page Title">
    <meta name="twitter:description" content="Description for Twitter sharing">
    <meta name="twitter:image" content="https://yoursite.com/twitter-image.jpg">
    
    <!-- Structured Data (JSON-LD) -->
    <script type="application/ld+json">
    {
        "@context": "https://schema.org",
        "@type": "WebPage",
        "name": "Your Page Title",
        "description": "Page description",
        "url": "https://yoursite.com/current-page",
        "author": {
            "@type": "Person",
            "name": "Your Name"
        },
        "datePublished": "2024-01-15",
        "dateModified": "2024-01-15"
    }
    </script>
    
    <!-- Canonical URL -->
    <link rel="canonical" href="https://yoursite.com/current-page">
    
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="/favicon.ico">
    <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
    <link rel="manifest" href="/site.webmanifest">
</head>
<body>
    <!-- Your content -->
</body>
</html>
```

---

## Real-World Example: E-commerce Product Page

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Premium Wireless Headphones - High-quality audio, noise cancellation, 30-hour battery life">
    <title>Premium Wireless Headphones | TechStore</title>
    
    <!-- Open Graph -->
    <meta property="og:title" content="Premium Wireless Headphones">
    <meta property="og:description" content="Experience superior sound quality with our premium wireless headphones">
    <meta property="og:image" content="https://techstore.com/images/headphones-main.jpg">
    <meta property="og:type" content="product">
    
    <!-- Structured Data for Product -->
    <script type="application/ld+json">
    {
        "@context": "https://schema.org/",
        "@type": "Product",
        "name": "Premium Wireless Headphones",
        "image": [
            "https://techstore.com/images/headphones-1.jpg",
            "https://techstore.com/images/headphones-2.jpg"
        ],
        "description": "High-quality wireless headphones with noise cancellation and 30-hour battery life",
        "sku": "TWH-001",
        "brand": {
            "@type": "Brand",
            "name": "TechStore"
        },
        "offers": {
            "@type": "Offer",
            "url": "https://techstore.com/products/premium-headphones",
            "priceCurrency": "USD",
            "price": "299.99",
            "availability": "https://schema.org/InStock",
            "validFrom": "2024-01-01"
        },
        "aggregateRating": {
            "@type": "AggregateRating",
            "ratingValue": "4.5",
            "reviewCount": "127"
        }
    }
    </script>
</head>
<body>
    <header>
        <nav aria-label="Main navigation">
            <a href="/" aria-label="TechStore Home">
                <img src="logo.svg" alt="TechStore">
            </a>
            <ul>
                <li><a href="/products">Products</a></li>
                <li><a href="/support">Support</a></li>
                <li><a href="/cart" aria-label="Shopping cart (2 items)">Cart (2)</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <nav aria-label="Breadcrumb">
            <ol>
                <li><a href="/">Home</a></li>
                <li><a href="/products">Products</a></li>
                <li><a href="/products/audio">Audio</a></li>
                <li aria-current="page">Premium Wireless Headphones</li>
            </ol>
        </nav>

        <article>
            <header>
                <h1>Premium Wireless Headphones</h1>
                <p class="product-code">SKU: TWH-001</p>
            </header>

            <section aria-labelledby="product-images">
                <h2 id="product-images" class="sr-only">Product Images</h2>
                <div class="product-gallery">
                    <img 
                        src="headphones-main.jpg" 
                        alt="Premium wireless headphones in black color"
                        loading="eager"
                    >
                    <div class="thumbnail-gallery">
                        <button type="button" aria-label="View main product image">
                            <img src="headphones-thumb-1.jpg" alt="Front view">
                        </button>
                        <button type="button" aria-label="View side product image">
                            <img src="headphones-thumb-2.jpg" alt="Side view">
                        </button>
                    </div>
                </div>
            </section>

            <section aria-labelledby="product-details">
                <h2 id="product-details">Product Details</h2>
                <div class="price">
                    <span class="current-price">$299.99</span>
                    <span class="original-price">$399.99</span>
                    <span class="discount">25% off</span>
                </div>

                <div class="rating">
                    <div aria-label="4.5 out of 5 stars" role="img">
                        ★★★★☆
                    </div>
                    <a href="#reviews">(127 reviews)</a>
                </div>

                <dl class="product-specs">
                    <dt>Battery Life</dt>
                    <dd>30 hours</dd>
                    <dt>Connectivity</dt>
                    <dd>Bluetooth 5.0, 3.5mm jack</dd>
                    <dt>Weight</dt>
                    <dd>280g</dd>
                    <dt>Warranty</dt>
                    <dd>2 years</dd>
                </dl>

                <form class="add-to-cart-form">
                    <fieldset>
                        <legend>Purchase Options</legend>
                        
                        <div class="form-group">
                            <label for="color">Color</label>
                            <select id="color" name="color" required>
                                <option value="">Choose a color</option>
                                <option value="black">Black</option>
                                <option value="white">White</option>
                                <option value="blue">Blue</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="quantity">Quantity</label>
                            <input 
                                type="number" 
                                id="quantity" 
                                name="quantity" 
                                min="1" 
                                max="5" 
                                value="1"
                                required
                            >
                        </div>
                    </fieldset>

                    <button type="submit" class="add-to-cart-btn">
                        Add to Cart - $299.99
                    </button>
                </form>

                <div class="delivery-info">
                    <p><strong>Free delivery</strong> by Thursday, January 18</p>
                    <p><strong>Return policy:</strong> 30-day returns</p>
                </div>
            </section>

            <section aria-labelledby="product-description">
                <h2 id="product-description">Description</h2>
                <p>Experience premium audio quality with our latest wireless headphones. 
                   Featuring advanced noise cancellation technology and 30-hour battery life, 
                   these headphones are perfect for music lovers and professionals alike.</p>
                
                <h3>Key Features</h3>
                <ul>
                    <li>Active noise cancellation</li>
                    <li>30-hour battery life</li>
                    <li>Quick charge: 15 minutes = 3 hours playback</li>
                    <li>Premium drivers for superior sound</li>
                    <li>Comfortable over-ear design</li>
                </ul>
            </section>

            <section aria-labelledby="reviews" id="reviews">
                <h2 id="reviews">Customer Reviews</h2>
                <div class="review-summary">
                    <div class="average-rating">
                        <span class="rating-number">4.5</span>
                        <div aria-label="4.5 out of 5 stars">★★★★☆</div>
                        <span class="review-count">127 reviews</span>
                    </div>
                </div>

                <article class="review">
                    <header>
                        <h3>Excellent sound quality</h3>
                        <div class="review-meta">
                            <div aria-label="5 out of 5 stars">★★★★★</div>
                            <span>by John D.</span>
                            <time datetime="2024-01-10">January 10, 2024</time>
                        </div>
                    </header>
                    <p>These headphones exceeded my expectations. The noise cancellation 
                       is fantastic and the battery life is exactly as advertised.</p>
                </article>
            </section>
        </article>
    </main>

    <footer>
        <p>&copy; 2024 TechStore. All rights reserved.</p>
    </footer>

    <!-- Live region for cart updates -->
    <div aria-live="polite" aria-atomic="true" id="cart-status" class="sr-only"></div>
</body>
</html>
```

---

## Best Practices Summary

### 1. **Semantic Markup**
- Use appropriate HTML5 semantic elements
- Implement proper heading hierarchy (h1-h6)
- Use landmarks (header, nav, main, aside, footer)

### 2. **Accessibility**
- Include proper ARIA labels and roles
- Ensure keyboard navigation works
- Provide alternative text for images
- Use sufficient color contrast

### 3. **Performance**
- Optimize images with proper formats and sizes
- Use lazy loading for non-critical content
- Minimize HTTP requests
- Implement resource hints (preload, prefetch)

### 4. **SEO**
- Include relevant meta tags
- Use structured data (JSON-LD)
- Implement Open Graph and Twitter Cards
- Provide clean, semantic URLs

### 5. **Modern Standards**
- Use HTML5 input types for better UX
- Implement responsive design principles
- Follow progressive enhancement
- Ensure cross-browser compatibility

This comprehensive guide covers modern HTML development practices suitable for production environments, from basic structure to advanced optimization techniques.