# HTML

## Introduction to HTML

HTML (HyperText Markup Language) is the standard markup language for creating web pages and web applications. It provides the basic structure and content for websites using elements and tags.

---

## Basic HTML Structure

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document Title</title>
</head>
<body>
    <h1>Hello World</h1>
    <p>This is a paragraph.</p>
</body>
</html>
```

---

## HTML Elements and Tags

### Common HTML Tags

- `<h1>` to `<h6>` - Headings
- `<p>` - Paragraphs
- `<div>` - Division/Container
- `<span>` - Inline container
- `<a>` - Links
- `<img>` - Images
- `<ul>`, `<ol>`, `<li>` - Lists
- `<table>`, `<tr>`, `<td>`, `<th>` - Tables

---

## HTML Attributes

### Common Attributes

- `id` - Unique identifier
- `class` - CSS class selector
- `src` - Source for images, scripts
- `href` - Hyperlink reference
- `alt` - Alternative text for images
- `style` - Inline CSS styling

---

## Forms and Input Elements

```html
<form action="/submit" method="POST">
    <input type="text" name="username" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="email" name="email" placeholder="Email">
    <textarea name="message" placeholder="Message"></textarea>
    <select name="country">
        <option value="us">United States</option>
        <option value="ca">Canada</option>
    </select>
    <button type="submit">Submit</button>
</form>
```

---

## Semantic HTML Elements

- `<header>` - Page or section header
- `<nav>` - Navigation links
- `<main>` - Main content area
- `<section>` - Thematic grouping of content
- `<article>` - Independent content
- `<aside>` - Sidebar content
- `<footer>` - Page or section footer

---

## Best Practices

1. Use semantic HTML elements for better accessibility
2. Always include `alt` attributes for images
3. Use proper heading hierarchy (h1, h2, h3, etc.)
4. Validate your HTML code
5. Keep your code clean and well-indented
6. Use meaningful class and id names

---