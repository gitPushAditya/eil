# CSS

## Introduction to CSS

CSS (Cascading Style Sheets) is a style sheet language used to describe the presentation of HTML documents. It controls the layout, colors, fonts, and overall appearance of web pages.

---

## CSS Syntax

```css
selector {
    property: value;
    property: value;
}
```

### Example
```css
h1 {
    color: blue;
    font-size: 24px;
    text-align: center;
}
```

---

## CSS Selectors

### Basic Selectors

- `element` - Element selector (e.g., `p`, `h1`, `div`)
- `.class` - Class selector (e.g., `.navbar`, `.btn`)
- `#id` - ID selector (e.g., `#header`, `#main`)
- `*` - Universal selector

### Combinator Selectors

- `element element` - Descendant selector
- `element > element` - Child selector
- `element + element` - Adjacent sibling selector
- `element ~ element` - General sibling selector

---

## CSS Box Model

```css
.box {
    width: 300px;
    height: 200px;
    padding: 20px;
    border: 2px solid black;
    margin: 10px;
}
```

- **Content** - The actual content
- **Padding** - Space between content and border
- **Border** - Border around the padding
- **Margin** - Space outside the border

---

## CSS Layout

### Flexbox

```css
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
}

.item {
    flex: 1;
}
```

### Grid

```css
.grid-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-gap: 20px;
}
```

---

## CSS Properties

### Text Styling
- `color` - Text color
- `font-family` - Font family
- `font-size` - Font size
- `font-weight` - Font weight
- `text-align` - Text alignment
- `line-height` - Line spacing

### Background
- `background-color` - Background color
- `background-image` - Background image
- `background-size` - Background size
- `background-position` - Background position

---

## Responsive Design

### Media Queries

```css
/* Mobile first approach */
.container {
    width: 100%;
}

@media (min-width: 768px) {
    .container {
        width: 750px;
    }
}

@media (min-width: 1024px) {
    .container {
        width: 1000px;
    }
}
```

---

## CSS Animations

### Transitions

```css
.button {
    transition: background-color 0.3s ease;
}

.button:hover {
    background-color: #007bff;
}
```

### Keyframe Animations

```css
@keyframes slideIn {
    from {
        transform: translateX(-100%);
    }
    to {
        transform: translateX(0);
    }
}

.slide-element {
    animation: slideIn 0.5s ease-in-out;
}
```

---

## Best Practices

1. Use external CSS files for better organization
2. Follow a consistent naming convention (BEM, SMACSS)
3. Use CSS preprocessors (Sass, Less) for complex projects
4. Optimize for performance (minimize CSS, use efficient selectors)
5. Test across different browsers and devices
6. Use CSS Grid and Flexbox for modern layouts

---