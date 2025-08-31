# DOM and BOM

## Introduction to DOM and BOM

The **DOM (Document Object Model)** represents the structure of HTML documents as a tree of objects that can be manipulated with JavaScript. The **BOM (Browser Object Model)** provides JavaScript access to browser functionality outside of the web page content.

---

## Document Object Model (DOM)

### DOM Structure

The DOM represents an HTML document as a hierarchical tree structure:

```
Document
└── html
    ├── head
    │   ├── title
    │   └── meta
    └── body
        ├── h1
        ├── p
        └── div
            ├── span
            └── img
```

---

## DOM Selection Methods

### Selecting Elements

```javascript
// By ID
const element = document.getElementById('myId');

// By Class Name
const elements = document.getElementsByClassName('myClass');

// By Tag Name
const paragraphs = document.getElementsByTagName('p');

// Query Selector (CSS-style)
const element = document.querySelector('.myClass');
const elements = document.querySelectorAll('div p');
```

---

## DOM Manipulation

### Creating and Modifying Elements

```javascript
// Create new element
const newDiv = document.createElement('div');
newDiv.textContent = 'Hello World';
newDiv.className = 'my-class';

// Append to DOM
document.body.appendChild(newDiv);

// Modify existing element
const existingElement = document.getElementById('myElement');
existingElement.innerHTML = '<strong>Bold text</strong>';
existingElement.style.color = 'blue';
existingElement.setAttribute('data-id', '123');

// Remove element
existingElement.remove();
```

### Element Properties and Methods

```javascript
const element = document.querySelector('#myElement');

// Content manipulation
element.textContent = 'Plain text';
element.innerHTML = '<em>HTML content</em>';

// Attribute manipulation
element.setAttribute('class', 'new-class');
element.getAttribute('data-id');
element.removeAttribute('title');

// CSS manipulation
element.style.backgroundColor = 'red';
element.classList.add('active');
element.classList.remove('inactive');
element.classList.toggle('visible');
```

---

## DOM Events

### Event Handling

```javascript
// Add event listener
const button = document.querySelector('#myButton');

button.addEventListener('click', function(event) {
    console.log('Button clicked!');
    event.preventDefault(); // Prevent default behavior
});

// Remove event listener
function handleClick(event) {
    console.log('Clicked!');
}

button.addEventListener('click', handleClick);
button.removeEventListener('click', handleClick);
```

### Common Events

```javascript
// Mouse events
element.addEventListener('click', handleClick);
element.addEventListener('mouseenter', handleMouseEnter);
element.addEventListener('mouseleave', handleMouseLeave);

// Keyboard events
document.addEventListener('keydown', handleKeyDown);
document.addEventListener('keyup', handleKeyUp);

// Form events
form.addEventListener('submit', handleSubmit);
input.addEventListener('change', handleInputChange);
input.addEventListener('input', handleInput);

// Window events
window.addEventListener('load', handleWindowLoad);
window.addEventListener('resize', handleResize);
window.addEventListener('scroll', handleScroll);
```

### Event Object

```javascript
function handleEvent(event) {
    console.log('Event type:', event.type);
    console.log('Target element:', event.target);
    console.log('Current target:', event.currentTarget);
    console.log('Mouse position:', event.clientX, event.clientY);
    
    // Stop event propagation
    event.stopPropagation();
    
    // Prevent default behavior
    event.preventDefault();
}
```

---

## Browser Object Model (BOM)

### Window Object

```javascript
// Window properties
console.log(window.innerWidth);
console.log(window.innerHeight);
console.log(window.location.href);

// Window methods
window.alert('Alert message');
window.confirm('Are you sure?');
window.prompt('Enter your name:');

// Navigation
window.open('https://example.com', '_blank');
window.close();
window.back();
window.forward();
```

### Location Object

```javascript
// Current URL information
console.log(location.href);        // Full URL
console.log(location.protocol);    // http: or https:
console.log(location.host);        // domain.com:8080
console.log(location.hostname);    // domain.com
console.log(location.port);        // 8080
console.log(location.pathname);    // /path/page.html
console.log(location.search);      // ?param=value
console.log(location.hash);        // #section

// Navigate to new URL
location.href = 'https://example.com';
location.assign('https://example.com');
location.replace('https://example.com'); // No back button
location.reload(); // Refresh page
```

### History Object

```javascript
// Navigation history
history.back();                    // Go back one page
history.forward();                 // Go forward one page
history.go(-2);                    // Go back 2 pages

// Modern history manipulation (HTML5)
history.pushState({page: 1}, 'Title', '/page1');
history.replaceState({page: 2}, 'Title', '/page2');

// Listen for history changes
window.addEventListener('popstate', function(event) {
    console.log('History changed:', event.state);
});
```

### Navigator Object

```javascript
// Browser information
console.log(navigator.userAgent);
console.log(navigator.platform);
console.log(navigator.language);
console.log(navigator.cookieEnabled);
console.log(navigator.onLine);

// Geolocation
navigator.geolocation.getCurrentPosition(
    function(position) {
        console.log('Latitude:', position.coords.latitude);
        console.log('Longitude:', position.coords.longitude);
    },
    function(error) {
        console.error('Geolocation error:', error.message);
    }
);
```

### Screen Object

```javascript
// Screen properties
console.log(screen.width);
console.log(screen.height);
console.log(screen.availWidth);
console.log(screen.availHeight);
console.log(screen.colorDepth);
```

---

## Timing Functions

```javascript
// setTimeout - Execute once after delay
const timeoutId = setTimeout(function() {
    console.log('Executed after 2 seconds');
}, 2000);

// Clear timeout
clearTimeout(timeoutId);

// setInterval - Execute repeatedly
const intervalId = setInterval(function() {
    console.log('Executed every second');
}, 1000);

// Clear interval
clearInterval(intervalId);
```

---

## Best Practices

1. **Use modern selection methods** - Prefer `querySelector` over older methods
2. **Event delegation** - Use event bubbling for dynamic content
3. **Avoid inline event handlers** - Use `addEventListener` instead
4. **Cache DOM references** - Store frequently accessed elements in variables
5. **Minimize DOM manipulation** - Batch changes to improve performance
6. **Use document fragments** - For adding multiple elements efficiently
7. **Handle errors gracefully** - Check if elements exist before manipulating
8. **Remove event listeners** - Prevent memory leaks in single-page applications

---