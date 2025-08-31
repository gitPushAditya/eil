# CSS - Cascading Style Sheets

CSS is the language for styling web pages. This guide covers modern CSS3+ features, responsive design, CSS Grid, Flexbox, animations, and production-ready techniques.

## Setup and Environment

### CSS Reset and Normalize

```css
/* Modern CSS Reset */
*, *::before, *::after {
    box-sizing: border-box;
}

* {
    margin: 0;
    padding: 0;
}

html, body {
    height: 100%;
}

body {
    line-height: 1.5;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}

img, picture, video, canvas, svg {
    display: block;
    max-width: 100%;
}

input, button, textarea, select {
    font: inherit;
}

p, h1, h2, h3, h4, h5, h6 {
    overflow-wrap: break-word;
}

#root, #__next {
    isolation: isolate;
}
```

### CSS Custom Properties (Variables)

```css
:root {
    /* Colors */
    --primary-color: #3b82f6;
    --primary-dark: #1e40af;
    --primary-light: #93c5fd;
    --secondary-color: #64748b;
    --success-color: #10b981;
    --warning-color: #f59e0b;
    --error-color: #ef4444;
    
    /* Neutral Colors */
    --white: #ffffff;
    --gray-50: #f9fafb;
    --gray-100: #f3f4f6;
    --gray-200: #e5e7eb;
    --gray-300: #d1d5db;
    --gray-400: #9ca3af;
    --gray-500: #6b7280;
    --gray-600: #4b5563;
    --gray-700: #374151;
    --gray-800: #1f2937;
    --gray-900: #111827;
    --black: #000000;
    
    /* Typography */
    --font-family-sans: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
    --font-family-serif: Georgia, 'Times New Roman', Times, serif;
    --font-family-mono: 'SF Mono', Monaco, 'Cascadia Code', 'Roboto Mono', Consolas, 'Courier New', monospace;
    
    /* Font Sizes */
    --text-xs: 0.75rem;     /* 12px */
    --text-sm: 0.875rem;    /* 14px */
    --text-base: 1rem;      /* 16px */
    --text-lg: 1.125rem;    /* 18px */
    --text-xl: 1.25rem;     /* 20px */
    --text-2xl: 1.5rem;     /* 24px */
    --text-3xl: 1.875rem;   /* 30px */
    --text-4xl: 2.25rem;    /* 36px */
    --text-5xl: 3rem;       /* 48px */
    --text-6xl: 3.75rem;    /* 60px */
    
    /* Spacing */
    --space-1: 0.25rem;     /* 4px */
    --space-2: 0.5rem;      /* 8px */
    --space-3: 0.75rem;     /* 12px */
    --space-4: 1rem;        /* 16px */
    --space-5: 1.25rem;     /* 20px */
    --space-6: 1.5rem;      /* 24px */
    --space-8: 2rem;        /* 32px */
    --space-10: 2.5rem;     /* 40px */
    --space-12: 3rem;       /* 48px */
    --space-16: 4rem;       /* 64px */
    --space-20: 5rem;       /* 80px */
    --space-24: 6rem;       /* 96px */
    
    /* Border Radius */
    --radius-sm: 0.125rem;  /* 2px */
    --radius: 0.25rem;      /* 4px */
    --radius-md: 0.375rem;  /* 6px */
    --radius-lg: 0.5rem;    /* 8px */
    --radius-xl: 0.75rem;   /* 12px */
    --radius-2xl: 1rem;     /* 16px */
    --radius-full: 9999px;
    
    /* Shadows */
    --shadow-sm: 0 1px 2px 0 rgb(0 0 0 / 0.05);
    --shadow: 0 1px 3px 0 rgb(0 0 0 / 0.1), 0 1px 2px -1px rgb(0 0 0 / 0.1);
    --shadow-md: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
    --shadow-lg: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);
    --shadow-xl: 0 20px 25px -5px rgb(0 0 0 / 0.1), 0 8px 10px -6px rgb(0 0 0 / 0.1);
    
    /* Transitions */
    --transition-fast: 150ms ease;
    --transition-base: 250ms ease;
    --transition-slow: 350ms ease;
    
    /* Z-index scale */
    --z-dropdown: 1000;
    --z-sticky: 1020;
    --z-fixed: 1030;
    --z-modal-backdrop: 1040;
    --z-modal: 1050;
    --z-popover: 1060;
    --z-tooltip: 1070;
    --z-toast: 1080;
}

/* Dark mode variables */
@media (prefers-color-scheme: dark) {
    :root {
        --bg-primary: var(--gray-900);
        --bg-secondary: var(--gray-800);
        --text-primary: var(--gray-100);
        --text-secondary: var(--gray-300);
        --border-color: var(--gray-700);
    }
}

/* Light mode variables */
@media (prefers-color-scheme: light) {
    :root {
        --bg-primary: var(--white);
        --bg-secondary: var(--gray-50);
        --text-primary: var(--gray-900);
        --text-secondary: var(--gray-600);
        --border-color: var(--gray-200);
    }
}
```

---

## Modern Layout Techniques

### CSS Grid

```css
/* Basic Grid Layout */
.grid-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: var(--space-6);
    padding: var(--space-6);
}

/* Complex Grid Layout */
.page-layout {
    display: grid;
    grid-template-areas: 
        "header header header"
        "sidebar main aside"
        "footer footer footer";
    grid-template-columns: 250px 1fr 200px;
    grid-template-rows: auto 1fr auto;
    min-height: 100vh;
    gap: var(--space-4);
}

.header { grid-area: header; }
.sidebar { grid-area: sidebar; }
.main { grid-area: main; }
.aside { grid-area: aside; }
.footer { grid-area: footer; }

/* Responsive Grid */
@media (max-width: 768px) {
    .page-layout {
        grid-template-areas:
            "header"
            "main"
            "sidebar"
            "aside"
            "footer";
        grid-template-columns: 1fr;
    }
}

/* Grid with Named Lines */
.advanced-grid {
    display: grid;
    grid-template-columns: 
        [full-start] minmax(var(--space-4), 1fr)
        [content-start] minmax(0, 1200px)
        [content-end] minmax(var(--space-4), 1fr) [full-end];
}

.full-width {
    grid-column: full;
}

.content-width {
    grid-column: content;
}

/* Masonry-like Grid */
.masonry-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    grid-auto-rows: 20px;
    gap: var(--space-4);
}

.masonry-item {
    grid-row-end: span var(--span, 10);
}
```

### Flexbox

```css
/* Flex Container */
.flex-container {
    display: flex;
    gap: var(--space-4);
    align-items: center;
    justify-content: space-between;
}

/* Flex Direction and Wrap */
.flex-column {
    display: flex;
    flex-direction: column;
}

.flex-wrap {
    display: flex;
    flex-wrap: wrap;
}

/* Flex Items */
.flex-grow {
    flex: 1; /* Equivalent to flex: 1 1 0% */
}

.flex-shrink-0 {
    flex-shrink: 0;
}

.flex-none {
    flex: none;
}

/* Common Flex Patterns */
.center-content {
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
}

.space-between {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.stack {
    display: flex;
    flex-direction: column;
    gap: var(--space-4);
}

.cluster {
    display: flex;
    flex-wrap: wrap;
    gap: var(--space-2);
    align-items: center;
}
```

---

## Responsive Design

### Mobile-First Approach

```css
/* Base styles (mobile first) */
.container {
    width: 100%;
    padding: 0 var(--space-4);
    margin: 0 auto;
}

.grid {
    display: grid;
    gap: var(--space-4);
    grid-template-columns: 1fr;
}

/* Tablet styles */
@media (min-width: 768px) {
    .container {
        max-width: 768px;
        padding: 0 var(--space-6);
    }
    
    .grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

/* Desktop styles */
@media (min-width: 1024px) {
    .container {
        max-width: 1024px;
        padding: 0 var(--space-8);
    }
    
    .grid {
        grid-template-columns: repeat(3, 1fr);
    }
}

/* Large desktop styles */
@media (min-width: 1280px) {
    .container {
        max-width: 1280px;
    }
    
    .grid {
        grid-template-columns: repeat(4, 1fr);
    }
}
```

### Container Queries

```css
/* Container Query */
.card-container {
    container-type: inline-size;
    container-name: card;
}

.card {
    padding: var(--space-4);
    background: var(--bg-secondary);
    border-radius: var(--radius-lg);
}

@container card (min-width: 400px) {
    .card {
        display: flex;
        gap: var(--space-4);
    }
    
    .card-content {
        flex: 1;
    }
}

@container card (min-width: 600px) {
    .card {
        padding: var(--space-6);
    }
}
```

### Fluid Typography

```css
/* Fluid typography using clamp() */
.heading-1 {
    font-size: clamp(2rem, 4vw, 3.5rem);
    line-height: 1.2;
}

.heading-2 {
    font-size: clamp(1.5rem, 3vw, 2.5rem);
    line-height: 1.3;
}

.body-text {
    font-size: clamp(1rem, 2vw, 1.125rem);
    line-height: 1.6;
}

/* Responsive spacing */
.section {
    padding-block: clamp(2rem, 8vw, 6rem);
}

.container {
    padding-inline: clamp(1rem, 4vw, 2rem);
}
```

---

## Components and Utilities

### Button Component

```css
.btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    gap: var(--space-2);
    padding: var(--space-3) var(--space-6);
    border: 2px solid transparent;
    border-radius: var(--radius);
    font-size: var(--text-base);
    font-weight: 600;
    line-height: 1;
    text-decoration: none;
    cursor: pointer;
    transition: all var(--transition-base);
    user-select: none;
    
    &:focus-visible {
        outline: 2px solid var(--primary-color);
        outline-offset: 2px;
    }
    
    &:disabled {
        opacity: 0.5;
        cursor: not-allowed;
    }
}

/* Button Variants */
.btn--primary {
    background-color: var(--primary-color);
    color: var(--white);
    
    &:hover:not(:disabled) {
        background-color: var(--primary-dark);
        transform: translateY(-1px);
        box-shadow: var(--shadow-md);
    }
    
    &:active {
        transform: translateY(0);
    }
}

.btn--secondary {
    background-color: transparent;
    color: var(--primary-color);
    border-color: var(--primary-color);
    
    &:hover:not(:disabled) {
        background-color: var(--primary-color);
        color: var(--white);
    }
}

.btn--ghost {
    background-color: transparent;
    color: var(--text-primary);
    
    &:hover:not(:disabled) {
        background-color: var(--gray-100);
    }
}

/* Button Sizes */
.btn--sm {
    padding: var(--space-2) var(--space-4);
    font-size: var(--text-sm);
}

.btn--lg {
    padding: var(--space-4) var(--space-8);
    font-size: var(--text-lg);
}

.btn--full {
    width: 100%;
}
```

### Card Component

```css
.card {
    background: var(--bg-primary);
    border: 1px solid var(--border-color);
    border-radius: var(--radius-lg);
    box-shadow: var(--shadow-sm);
    overflow: hidden;
    transition: all var(--transition-base);
    
    &:hover {
        box-shadow: var(--shadow-md);
        transform: translateY(-2px);
    }
}

.card__header {
    padding: var(--space-6);
    border-bottom: 1px solid var(--border-color);
}

.card__title {
    margin: 0;
    font-size: var(--text-xl);
    font-weight: 600;
    color: var(--text-primary);
}

.card__description {
    margin: var(--space-2) 0 0;
    color: var(--text-secondary);
    font-size: var(--text-sm);
}

.card__content {
    padding: var(--space-6);
}

.card__footer {
    padding: var(--space-4) var(--space-6);
    background: var(--bg-secondary);
    border-top: 1px solid var(--border-color);
}

.card__actions {
    display: flex;
    gap: var(--space-3);
    justify-content: flex-end;
}

/* Card Variants */
.card--elevated {
    box-shadow: var(--shadow-lg);
    border: none;
}

.card--outlined {
    border: 2px solid var(--primary-color);
    box-shadow: none;
}
```

### Form Components

```css
.form-group {
    margin-bottom: var(--space-6);
}

.label {
    display: block;
    margin-bottom: var(--space-2);
    font-size: var(--text-sm);
    font-weight: 600;
    color: var(--text-primary);
}

.input {
    width: 100%;
    padding: var(--space-3);
    border: 2px solid var(--border-color);
    border-radius: var(--radius);
    font-size: var(--text-base);
    background: var(--bg-primary);
    color: var(--text-primary);
    transition: all var(--transition-fast);
    
    &:focus {
        outline: none;
        border-color: var(--primary-color);
        box-shadow: 0 0 0 3px var(--primary-color, 0.1);
    }
    
    &:invalid {
        border-color: var(--error-color);
    }
    
    &::placeholder {
        color: var(--text-secondary);
    }
}

.textarea {
    resize: vertical;
    min-height: 120px;
}

.select {
    appearance: none;
    background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 20 20'%3e%3cpath stroke='%236b7280' stroke-linecap='round' stroke-linejoin='round' stroke-width='1.5' d='m6 8 4 4 4-4'/%3e%3c/svg%3e");
    background-position: right var(--space-3) center;
    background-repeat: no-repeat;
    background-size: 16px 12px;
    padding-right: calc(var(--space-3) + 20px);
}

.checkbox,
.radio {
    width: auto;
    margin-right: var(--space-2);
}

.form-error {
    margin-top: var(--space-1);
    font-size: var(--text-xs);
    color: var(--error-color);
}

.form-help {
    margin-top: var(--space-1);
    font-size: var(--text-xs);
    color: var(--text-secondary);
}
```

---

## Animations and Transitions

### CSS Animations

```css
/* Keyframe Animations */
@keyframes fadeIn {
    from {
        opacity: 0;
        transform: translateY(20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes slideInRight {
    from {
        opacity: 0;
        transform: translateX(-100%);
    }
    to {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes bounce {
    0%, 20%, 53%, 80%, 100% {
        transform: translate3d(0, 0, 0);
    }
    40%, 43% {
        transform: translate3d(0, -30px, 0);
    }
    70% {
        transform: translate3d(0, -15px, 0);
    }
    90% {
        transform: translate3d(0, -4px, 0);
    }
}

@keyframes spin {
    from {
        transform: rotate(0deg);
    }
    to {
        transform: rotate(360deg);
    }
}

/* Animation Classes */
.animate-fade-in {
    animation: fadeIn 0.5s ease-out;
}

.animate-slide-in {
    animation: slideInRight 0.3s ease-out;
}

.animate-bounce {
    animation: bounce 1s infinite;
}

.animate-spin {
    animation: spin 1s linear infinite;
}

/* Hover Animations */
.hover-scale {
    transition: transform var(--transition-base);
}

.hover-scale:hover {
    transform: scale(1.05);
}

.hover-lift {
    transition: all var(--transition-base);
}

.hover-lift:hover {
    transform: translateY(-4px);
    box-shadow: var(--shadow-lg);
}

/* Loading Animations */
.loading-spinner {
    width: 40px;
    height: 40px;
    border: 4px solid var(--gray-200);
    border-top: 4px solid var(--primary-color);
    border-radius: 50%;
    animation: spin 1s linear infinite;
}

.loading-dots {
    display: flex;
    gap: var(--space-1);
}

.loading-dots::before,
.loading-dots::after,
.loading-dots {
    content: '';
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background: var(--primary-color);
    animation: loading-dots 1.4s infinite ease-in-out both;
}

.loading-dots::before {
    animation-delay: -0.32s;
}

.loading-dots::after {
    animation-delay: -0.16s;
}

@keyframes loading-dots {
    0%, 80%, 100% {
        transform: scale(0);
    }
    40% {
        transform: scale(1);
    }
}
```

### Page Transitions

```css
/* Page transition setup */
.page-transition {
    transition: all 0.3s ease-in-out;
}

.page-enter {
    opacity: 0;
    transform: translateX(100%);
}

.page-enter-active {
    opacity: 1;
    transform: translateX(0);
}

.page-exit {
    opacity: 1;
    transform: translateX(0);
}

.page-exit-active {
    opacity: 0;
    transform: translateX(-100%);
}

/* Modal animations */
.modal-backdrop {
    background: rgba(0, 0, 0, 0.5);
    transition: opacity var(--transition-base);
}

.modal-backdrop.entering,
.modal-backdrop.exiting {
    opacity: 0;
}

.modal-backdrop.entered {
    opacity: 1;
}

.modal-content {
    transform: scale(0.9);
    transition: transform var(--transition-base);
}

.modal-content.entering,
.modal-content.exiting {
    transform: scale(0.9);
}

.modal-content.entered {
    transform: scale(1);
}
```

---

## Advanced CSS Features

### CSS Grid Advanced Patterns

```css
/* Auto-fit grid with minimum and maximum sizes */
.responsive-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(min(300px, 100%), 1fr));
    gap: var(--space-6);
}

/* Grid with aspect ratio */
.aspect-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: var(--space-4);
}

.aspect-grid > * {
    aspect-ratio: 16 / 9;
}

/* Subgrid (if supported) */
.main-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: var(--space-4);
}

.subgrid-item {
    display: grid;
    grid-template-rows: subgrid;
    grid-row: span 3;
}

/* Grid line names and areas */
.layout-grid {
    display: grid;
    grid-template-columns: 
        [full-start] 1fr 
        [content-start] minmax(0, 1200px) 
        [content-end] 1fr [full-end];
    grid-template-rows: 
        [header-start] auto [header-end]
        [main-start] 1fr [main-end]
        [footer-start] auto [footer-end];
}

.full-width {
    grid-column: full;
}

.content-width {
    grid-column: content;
}
```

### Modern CSS Functions

```css
/* clamp() for responsive sizing */
.responsive-text {
    font-size: clamp(1rem, 2.5vw, 2rem);
    line-height: clamp(1.4, 1.5, 1.6);
    margin-bottom: clamp(1rem, 3vw, 2rem);
}

/* min() and max() functions */
.flexible-width {
    width: min(100%, 600px);
    padding: max(20px, 5vw);
}

/* calc() for complex calculations */
.dynamic-height {
    height: calc(100vh - 60px); /* Full height minus header */
    width: calc(100% - 2 * var(--space-4)); /* Full width minus padding */
}

/* CSS logical properties */
.logical-spacing {
    margin-block: var(--space-4); /* margin-top and margin-bottom */
    margin-inline: var(--space-6); /* margin-left and margin-right */
    padding-block-start: var(--space-2); /* padding-top */
    padding-inline-end: var(--space-3); /* padding-right */
}

/* Custom property with fallback */
.theme-aware {
    color: var(--text-color, #333);
    background: var(--bg-color, #fff);
}
```

### CSS Filters and Blend Modes

```css
/* Image filters */
.image-filters {
    filter: brightness(1.1) contrast(1.2) saturate(1.1);
    transition: filter var(--transition-base);
}

.image-filters:hover {
    filter: brightness(1.2) contrast(1.3) saturate(1.3);
}

.grayscale-image {
    filter: grayscale(100%);
}

.sepia-image {
    filter: sepia(80%);
}

.blur-image {
    filter: blur(5px);
}

/* Backdrop filters */
.glass-morphism {
    background: rgba(255, 255, 255, 0.1);
    backdrop-filter: blur(10px) saturate(180%);
    border: 1px solid rgba(255, 255, 255, 0.2);
    border-radius: var(--radius-lg);
}

/* Blend modes */
.blend-overlay {
    background: linear-gradient(45deg, #ff6b6b, #4ecdc4);
    mix-blend-mode: overlay;
}

.blend-multiply {
    mix-blend-mode: multiply;
}
```

---

## Performance Optimization

### CSS Performance Best Practices

```css
/* Use transform and opacity for animations (GPU accelerated) */
.optimized-animation {
    will-change: transform, opacity;
    transform: translateZ(0); /* Force hardware acceleration */
}

/* Efficient selectors */
.btn { /* Good: single class */
    /* styles */
}

/* Avoid expensive selectors */
/* Bad: */
/* div > ul > li > a:hover { } */

/* Use containment for better performance */
.performance-container {
    contain: layout style paint;
}

/* Optimize reflows and repaints */
.no-layout-thrashing {
    /* Use transform instead of changing left/top */
    transform: translateX(100px);
    /* Use opacity instead of visibility for transitions */
    opacity: 0;
}

/* Critical CSS pattern */
/* Inline critical styles, load non-critical async */
.above-fold {
    /* Critical styles here */
    display: flex;
    align-items: center;
    background: var(--primary-color);
}

/* Load non-critical styles asynchronously */
.below-fold {
    /* These can be loaded later */
    background-image: url('large-image.jpg');
    animation: complex-animation 2s ease-in-out;
}
```

### CSS Custom Properties for Theming

```css
/* Dark/Light theme system */
:root {
    --theme: light;
    
    /* Light theme colors */
    --bg-primary: #ffffff;
    --bg-secondary: #f8fafc;
    --text-primary: #1a202c;
    --text-secondary: #718096;
    --border-color: #e2e8f0;
}

[data-theme="dark"] {
    --theme: dark;
    
    /* Dark theme colors */
    --bg-primary: #1a202c;
    --bg-secondary: #2d3748;
    --text-primary: #f7fafc;
    --text-secondary: #a0aec0;
    --border-color: #4a5568;
}

/* Automatic theme switching */
@media (prefers-color-scheme: dark) {
    :root:not([data-theme]) {
        --theme: dark;
        --bg-primary: #1a202c;
        --bg-secondary: #2d3748;
        --text-primary: #f7fafc;
        --text-secondary: #a0aec0;
        --border-color: #4a5568;
    }
}

/* Theme-aware components */
.card {
    background: var(--bg-primary);
    color: var(--text-primary);
    border: 1px solid var(--border-color);
    transition: background-color 0.3s ease, border-color 0.3s ease;
}

/* Theme toggle animation */
.theme-transition * {
    transition: background-color 0.3s ease, 
                border-color 0.3s ease, 
                color 0.3s ease !important;
}
```

---

## Real-World Example: E-commerce Product Card

```css
/* Product Card Component */
.product-card {
    --card-padding: var(--space-4);
    --card-radius: var(--radius-lg);
    
    position: relative;
    display: flex;
    flex-direction: column;
    background: var(--bg-primary);
    border: 1px solid var(--border-color);
    border-radius: var(--card-radius);
    box-shadow: var(--shadow-sm);
    overflow: hidden;
    transition: all var(--transition-base);
    cursor: pointer;
    
    &:hover {
        transform: translateY(-4px);
        box-shadow: var(--shadow-lg);
        border-color: var(--primary-color);
    }
    
    &:focus-within {
        outline: 2px solid var(--primary-color);
        outline-offset: 2px;
    }
}

.product-card__badge {
    position: absolute;
    top: var(--space-3);
    left: var(--space-3);
    z-index: 1;
    padding: var(--space-1) var(--space-2);
    background: var(--error-color);
    color: var(--white);
    font-size: var(--text-xs);
    font-weight: 600;
    border-radius: var(--radius);
    text-transform: uppercase;
    letter-spacing: 0.05em;
}

.product-card__image-container {
    position: relative;
    aspect-ratio: 1;
    overflow: hidden;
    background: var(--gray-100);
}

.product-card__image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform var(--transition-slow);
}

.product-card:hover .product-card__image {
    transform: scale(1.05);
}

.product-card__wishlist {
    position: absolute;
    top: var(--space-3);
    right: var(--space-3);
    z-index: 2;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    background: rgba(255, 255, 255, 0.9);
    backdrop-filter: blur(4px);
    border: none;
    border-radius: 50%;
    cursor: pointer;
    transition: all var(--transition-fast);
    
    &:hover {
        background: var(--white);
        transform: scale(1.1);
    }
    
    &:focus {
        outline: 2px solid var(--primary-color);
        outline-offset: 2px;
    }
    
    &.active {
        background: var(--error-color);
        color: var(--white);
    }
}

.product-card__content {
    display: flex;
    flex-direction: column;
    padding: var(--card-padding);
    flex: 1;
}

.product-card__brand {
    font-size: var(--text-xs);
    color: var(--text-secondary);
    text-transform: uppercase;
    letter-spacing: 0.05em;
    margin-bottom: var(--space-1);
}

.product-card__title {
    font-size: var(--text-base);
    font-weight: 600;
    color: var(--text-primary);
    margin-bottom: var(--space-2);
    line-height: 1.4;
    
    /* Truncate text to 2 lines */
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}

.product-card__rating {
    display: flex;
    align-items: center;
    gap: var(--space-1);
    margin-bottom: var(--space-2);
}

.product-card__stars {
    display: flex;
    color: var(--warning-color);
}

.product-card__rating-count {
    font-size: var(--text-xs);
    color: var(--text-secondary);
}

.product-card__price {
    display: flex;
    align-items: baseline;
    gap: var(--space-2);
    margin-bottom: var(--space-4);
}

.product-card__current-price {
    font-size: var(--text-lg);
    font-weight: 700;
    color: var(--text-primary);
}

.product-card__original-price {
    font-size: var(--text-sm);
    color: var(--text-secondary);
    text-decoration: line-through;
}

.product-card__discount {
    font-size: var(--text-xs);
    color: var(--error-color);
    font-weight: 600;
    background: rgba(239, 68, 68, 0.1);
    padding: var(--space-1) var(--space-2);
    border-radius: var(--radius);
}

.product-card__colors {
    display: flex;
    gap: var(--space-1);
    margin-bottom: var(--space-4);
}

.product-card__color {
    width: 20px;
    height: 20px;
    border-radius: 50%;
    border: 2px solid var(--white);
    box-shadow: 0 0 0 1px var(--border-color);
    cursor: pointer;
    transition: transform var(--transition-fast);
    
    &:hover {
        transform: scale(1.2);
    }
    
    &.active {
        box-shadow: 0 0 0 2px var(--primary-color);
    }
}

.product-card__actions {
    display: flex;
    gap: var(--space-2);
    margin-top: auto;
}

.product-card__add-to-cart {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: var(--space-2);
    padding: var(--space-3);
    background: var(--primary-color);
    color: var(--white);
    border: none;
    border-radius: var(--radius);
    font-weight: 600;
    cursor: pointer;
    transition: all var(--transition-fast);
    
    &:hover {
        background: var(--primary-dark);
        transform: translateY(-1px);
    }
    
    &:active {
        transform: translateY(0);
    }
    
    &:disabled {
        opacity: 0.5;
        cursor: not-allowed;
        transform: none;
    }
}

.product-card__quick-view {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 44px;
    background: transparent;
    color: var(--text-primary);
    border: 2px solid var(--border-color);
    border-radius: var(--radius);
    cursor: pointer;
    transition: all var(--transition-fast);
    
    &:hover {
        background: var(--bg-secondary);
        border-color: var(--primary-color);
    }
}

/* Loading state */
.product-card--loading {
    pointer-events: none;
}

.product-card--loading .product-card__image {
    background: linear-gradient(
        90deg,
        var(--gray-200) 25%,
        var(--gray-100) 50%,
        var(--gray-200) 75%
    );
    background-size: 200% 100%;
    animation: shimmer 1.5s infinite;
}

@keyframes shimmer {
    0% {
        background-position: -200% 0;
    }
    100% {
        background-position: 200% 0;
    }
}

/* Responsive adjustments */
@media (max-width: 480px) {
    .product-card {
        --card-padding: var(--space-3);
    }
    
    .product-card__actions {
        flex-direction: column;
    }
    
    .product-card__quick-view {
        width: 100%;
    }
}
```

---

## CSS Architecture and Organization

### BEM Methodology

```css
/* Block */
.card { }

/* Element */
.card__header { }
.card__title { }
.card__content { }
.card__footer { }

/* Modifier */
.card--large { }
.card--featured { }
.card__title--highlighted { }

/* Example usage in HTML:
<div class="card card--large">
    <div class="card__header">
        <h2 class="card__title card__title--highlighted">Title</h2>
    </div>
    <div class="card__content">Content</div>
</div>
*/
```

### CSS Layers

```css
/* CSS Cascade Layers */
@layer reset, base, components, utilities, overrides;

@layer reset {
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }
}

@layer base {
    body {
        font-family: var(--font-family-sans);
        line-height: 1.5;
        color: var(--text-primary);
    }
}

@layer components {
    .btn {
        padding: var(--space-3) var(--space-6);
        border-radius: var(--radius);
        /* component styles */
    }
}

@layer utilities {
    .text-center { text-align: center; }
    .hidden { display: none; }
    .sr-only { /* screen reader only styles */ }
}

@layer overrides {
    /* High-priority overrides */
    .important-override {
        color: red !important;
    }
}
```

---

## Best Practices Summary

### 1. **Modern CSS Features**
- Use CSS Custom Properties for theming
- Implement CSS Grid and Flexbox for layouts
- Utilize logical properties for internationalization
- Apply container queries for component-based responsive design

### 2. **Performance**
- Minimize reflows and repaints
- Use `transform` and `opacity` for animations
- Implement CSS containment
- Optimize selector performance

### 3. **Maintainability**
- Follow consistent naming conventions (BEM)
- Use CSS layers for cascade management
- Organize styles with a clear architecture
- Document custom properties and complex calculations

### 4. **Accessibility**
- Ensure sufficient color contrast
- Provide focus indicators
- Use semantic color meanings
- Support reduced motion preferences

### 5. **Responsive Design**
- Implement mobile-first approach
- Use fluid typography and spacing
- Apply container queries for components
- Test across different devices and screen sizes

This comprehensive CSS guide covers modern techniques and best practices for professional web development, from fundamental layouts to advanced animation and theming systems.