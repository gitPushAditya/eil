# DOM and BOM - Document Object Model & Browser Object Model

The DOM (Document Object Model) and BOM (Browser Object Model) provide JavaScript APIs for interacting with web pages and browsers. This guide covers modern DOM manipulation, event handling, and browser APIs.

## Document Object Model (DOM)

### DOM Selection and Traversal

```javascript
// Modern DOM selection methods
const element = document.getElementById('myElement');
const elements = document.getElementsByClassName('myClass');
const tagElements = document.getElementsByTagName('div');

// Query selectors (preferred modern approach)
const singleElement = document.querySelector('.my-class');
const allElements = document.querySelectorAll('.my-class');
const complexSelector = document.querySelector('div.container > p:first-child');

// Advanced selectors
const evenParagraphs = document.querySelectorAll('p:nth-child(even)');
const dataAttributes = document.querySelectorAll('[data-category="electronics"]');
const notElements = document.querySelectorAll('div:not(.excluded)');

// DOM traversal
const parent = element.parentElement;
const children = element.children; // HTMLCollection
const childNodes = element.childNodes; // NodeList (includes text nodes)
const firstChild = element.firstElementChild;
const lastChild = element.lastElementChild;
const nextSibling = element.nextElementSibling;
const previousSibling = element.previousElementSibling;

// Closest method for finding ancestor
const closestForm = element.closest('form');
const closestContainer = element.closest('.container');

// Modern iteration methods
// Convert HTMLCollection to Array for array methods
const childArray = Array.from(element.children);
childArray.forEach(child => console.log(child.tagName));

// NodeList has forEach built-in
document.querySelectorAll('.item').forEach(item => {
    item.style.color = 'blue';
});

// Using for...of loop
for (const item of document.querySelectorAll('.item')) {
    item.classList.add('processed');
}
```

### Element Creation and Manipulation

```javascript
// Creating elements
const div = document.createElement('div');
const textNode = document.createTextNode('Hello World');
const fragment = document.createDocumentFragment();

// Setting content
div.textContent = 'Safe text content'; // Escapes HTML
div.innerHTML = '<strong>HTML content</strong>'; // Parses HTML

// Modern approach with template literals
function createUserCard(user) {
    const card = document.createElement('div');
    card.className = 'user-card';
    card.innerHTML = `
        <img src="${user.avatar}" alt="${user.name}" class="avatar">
        <h3>${user.name}</h3>
        <p>${user.email}</p>
        <button class="btn-contact" data-user-id="${user.id}">Contact</button>
    `;
    return card;
}

// Inserting elements
const container = document.querySelector('.container');

// Insert methods
container.appendChild(div); // Add as last child
container.insertBefore(div, container.firstChild); // Insert before element

// Modern insert methods
container.prepend(div); // Add as first child
container.append(div); // Add as last child
div.before(newElement); // Insert before div
div.after(newElement); // Insert after div
div.replaceWith(newElement); // Replace div with newElement

// Insert adjacent methods
div.insertAdjacentElement('beforebegin', newElement); // Before div
div.insertAdjacentElement('afterbegin', newElement);  // First child of div
div.insertAdjacentElement('beforeend', newElement);   // Last child of div
div.insertAdjacentElement('afterend', newElement);    // After div

div.insertAdjacentHTML('beforeend', '<p>New paragraph</p>');
div.insertAdjacentText('afterbegin', 'Text content');

// Removing elements
element.remove(); // Modern way
parent.removeChild(element); // Traditional way

// Cloning elements
const clone = element.cloneNode(true); // Deep clone (includes children)
const shallowClone = element.cloneNode(false); // Shallow clone

// Document fragments for performance
function createMultipleElements(items) {
    const fragment = document.createDocumentFragment();
    
    items.forEach(item => {
        const element = createUserCard(item);
        fragment.appendChild(element);
    });
    
    // Single DOM operation instead of multiple
    container.appendChild(fragment);
}
```

### Attributes and Properties

```javascript
// HTML attributes vs DOM properties
const input = document.querySelector('input[type="text"]');

// Attributes (as written in HTML)
input.getAttribute('class'); // Returns attribute value as string
input.setAttribute('class', 'new-class');
input.hasAttribute('disabled'); // Returns boolean
input.removeAttribute('disabled');

// Get all attributes
const attributes = Array.from(input.attributes);
attributes.forEach(attr => {
    console.log(`${attr.name}: ${attr.value}`);
});

// Properties (current state)
input.className = 'new-class'; // Property
input.value = 'New value'; // Current value
input.checked = true; // For checkboxes/radio buttons
input.disabled = false; // Boolean property

// Data attributes
input.setAttribute('data-user-id', '123');
const userId = input.dataset.userId; // Camel case conversion
input.dataset.userName = 'john'; // Sets data-user-name

// Working with classes
element.className = 'class1 class2'; // String property
element.classList.add('new-class');
element.classList.remove('old-class');
element.classList.toggle('active');
element.classList.contains('active'); // Returns boolean
element.classList.replace('old-class', 'new-class');

// Multiple class operations
element.classList.add('class1', 'class2', 'class3');
element.classList.remove('class1', 'class2');

// Working with styles
element.style.color = 'red';
element.style.backgroundColor = 'blue';
element.style.fontSize = '16px';

// CSS custom properties
element.style.setProperty('--main-color', '#ff0000');
const mainColor = element.style.getProperty('--main-color');

// Computed styles
const computedStyle = window.getComputedStyle(element);
const fontSize = computedStyle.fontSize;
const backgroundColor = computedStyle.backgroundColor;

// Style utility functions
function setStyles(element, styles) {
    Object.assign(element.style, styles);
}

setStyles(element, {
    color: 'white',
    backgroundColor: 'black',
    padding: '10px',
    borderRadius: '5px'
});

function getStyleValue(element, property) {
    return window.getComputedStyle(element).getPropertyValue(property);
}
```

### Form Handling

```javascript
// Form and input handling
const form = document.querySelector('#myForm');
const inputs = form.querySelectorAll('input, select, textarea');

// Form data collection
function getFormData(form) {
    const formData = new FormData(form);
    const data = {};
    
    for (const [key, value] of formData.entries()) {
        if (data[key]) {
            // Handle multiple values (checkboxes, multi-select)
            if (Array.isArray(data[key])) {
                data[key].push(value);
            } else {
                data[key] = [data[key], value];
            }
        } else {
            data[key] = value;
        }
    }
    
    return data;
}

// Form validation
function validateForm(form) {
    const errors = {};
    const data = getFormData(form);
    
    // Required fields
    const requiredFields = form.querySelectorAll('[required]');
    requiredFields.forEach(field => {
        if (!field.value.trim()) {
            errors[field.name] = 'This field is required';
        }
    });
    
    // Email validation
    const emailInputs = form.querySelectorAll('input[type="email"]');
    emailInputs.forEach(input => {
        if (input.value && !isValidEmail(input.value)) {
            errors[input.name] = 'Please enter a valid email address';
        }
    });
    
    // Custom validation
    const passwordInput = form.querySelector('input[name="password"]');
    const confirmPasswordInput = form.querySelector('input[name="confirmPassword"]');
    
    if (passwordInput && confirmPasswordInput) {
        if (passwordInput.value !== confirmPasswordInput.value) {
            errors.confirmPassword = 'Passwords do not match';
        }
    }
    
    return errors;
}

function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Form submission handling
form.addEventListener('submit', async (event) => {
    event.preventDefault();
    
    const errors = validateForm(form);
    
    if (Object.keys(errors).length > 0) {
        displayFormErrors(errors);
        return;
    }
    
    const formData = getFormData(form);
    
    try {
        const response = await fetch('/api/submit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });
        
        if (response.ok) {
            showSuccessMessage('Form submitted successfully!');
            form.reset();
        } else {
            throw new Error('Submission failed');
        }
    } catch (error) {
        showErrorMessage('Failed to submit form. Please try again.');
    }
});

// Display form errors
function displayFormErrors(errors) {
    // Clear previous errors
    document.querySelectorAll('.error-message').forEach(el => el.remove());
    
    Object.keys(errors).forEach(fieldName => {
        const field = form.querySelector(`[name="${fieldName}"]`);
        if (field) {
            const errorElement = document.createElement('div');
            errorElement.className = 'error-message';
            errorElement.textContent = errors[fieldName];
            field.parentElement.appendChild(errorElement);
            field.classList.add('error');
        }
    });
}

// Real-time validation
inputs.forEach(input => {
    input.addEventListener('blur', () => {
        validateField(input);
    });
    
    input.addEventListener('input', () => {
        // Clear error state on input
        input.classList.remove('error');
        const errorMessage = input.parentElement.querySelector('.error-message');
        if (errorMessage) {
            errorMessage.remove();
        }
    });
});

function validateField(field) {
    const errors = validateForm(form);
    
    if (errors[field.name]) {
        field.classList.add('error');
        
        let errorElement = field.parentElement.querySelector('.error-message');
        if (!errorElement) {
            errorElement = document.createElement('div');
            errorElement.className = 'error-message';
            field.parentElement.appendChild(errorElement);
        }
        errorElement.textContent = errors[field.name];
    } else {
        field.classList.remove('error');
        const errorElement = field.parentElement.querySelector('.error-message');
        if (errorElement) {
            errorElement.remove();
        }
    }
}
```

---

## Event Handling

### Modern Event Handling

```javascript
// Adding event listeners
const button = document.querySelector('#myButton');

// Basic event listener
button.addEventListener('click', function(event) {
    console.log('Button clicked!');
});

// Arrow function event listener
button.addEventListener('click', (event) => {
    console.log('Arrow function event listener');
});

// Event listener with options
button.addEventListener('click', handleClick, {
    once: true,      // Remove after first trigger
    passive: true,   // Never calls preventDefault()
    capture: true    // Trigger during capture phase
});

// Removing event listeners
function handleClick(event) {
    console.log('Clicked!');
}

button.addEventListener('click', handleClick);
button.removeEventListener('click', handleClick);

// Event delegation
const container = document.querySelector('.container');

container.addEventListener('click', (event) => {
    // Check if clicked element matches selector
    if (event.target.matches('.button')) {
        console.log('Button clicked:', event.target.textContent);
    }
    
    if (event.target.matches('.delete-btn')) {
        const item = event.target.closest('.item');
        item.remove();
    }
});

// Event delegation with modern approach
function delegate(container, selector, eventType, handler) {
    container.addEventListener(eventType, (event) => {
        const target = event.target.closest(selector);
        if (target && container.contains(target)) {
            handler.call(target, event);
        }
    });
}

// Usage
delegate(document.body, '.modal-trigger', 'click', function(event) {
    const modalId = this.dataset.modal;
    openModal(modalId);
});

// Custom events
function createCustomEvent(name, detail = {}) {
    return new CustomEvent(name, {
        detail,
        bubbles: true,
        cancelable: true
    });
}

// Dispatching custom events
const customEvent = createCustomEvent('userLogin', {
    userId: 123,
    username: 'john_doe'
});

document.dispatchEvent(customEvent);

// Listening for custom events
document.addEventListener('userLogin', (event) => {
    console.log('User logged in:', event.detail);
});

// Event object properties and methods
button.addEventListener('click', (event) => {
    event.preventDefault(); // Prevent default behavior
    event.stopPropagation(); // Stop event bubbling
    event.stopImmediatePropagation(); // Stop other listeners on same element
    
    console.log('Event type:', event.type);
    console.log('Target element:', event.target);
    console.log('Current target:', event.currentTarget);
    console.log('Mouse position:', event.clientX, event.clientY);
    console.log('Key pressed:', event.key); // For keyboard events
    console.log('Modifiers:', {
        ctrl: event.ctrlKey,
        shift: event.shiftKey,
        alt: event.altKey,
        meta: event.metaKey
    });
});
```

### Specific Event Types

```javascript
// Mouse events
element.addEventListener('mouseenter', (event) => {
    // Mouse enters element (doesn't bubble)
});

element.addEventListener('mouseleave', (event) => {
    // Mouse leaves element (doesn't bubble)
});

element.addEventListener('mouseover', (event) => {
    // Mouse enters element or children (bubbles)
});

element.addEventListener('mouseout', (event) => {
    // Mouse leaves element or children (bubbles)
});

element.addEventListener('mousemove', (event) => {
    console.log(`Mouse at: ${event.clientX}, ${event.clientY}`);
});

// Keyboard events
document.addEventListener('keydown', (event) => {
    console.log('Key pressed:', event.key);
    console.log('Key code:', event.code);
    
    // Handle specific keys
    switch (event.key) {
        case 'Escape':
            closeModal();
            break;
        case 'Enter':
            if (event.ctrlKey) {
                submitForm();
            }
            break;
        case 'ArrowUp':
        case 'ArrowDown':
            event.preventDefault();
            navigateList(event.key === 'ArrowUp' ? -1 : 1);
            break;
    }
});

// Touch events for mobile
element.addEventListener('touchstart', (event) => {
    const touch = event.touches[0];
    console.log('Touch start:', touch.clientX, touch.clientY);
});

element.addEventListener('touchmove', (event) => {
    event.preventDefault(); // Prevent scrolling
    const touch = event.touches[0];
    // Handle touch movement
});

element.addEventListener('touchend', (event) => {
    console.log('Touch end');
});

// Form events
const input = document.querySelector('#myInput');

input.addEventListener('focus', () => {
    console.log('Input focused');
});

input.addEventListener('blur', () => {
    console.log('Input lost focus');
});

input.addEventListener('input', (event) => {
    console.log('Input value changed:', event.target.value);
});

input.addEventListener('change', (event) => {
    console.log('Input change committed:', event.target.value);
});

// Window events
window.addEventListener('resize', (event) => {
    console.log('Window resized:', window.innerWidth, window.innerHeight);
});

window.addEventListener('scroll', (event) => {
    console.log('Scroll position:', window.scrollY);
});

window.addEventListener('beforeunload', (event) => {
    // Warn user before leaving page
    event.preventDefault();
    event.returnValue = ''; // Some browsers require this
});

// Document events
document.addEventListener('DOMContentLoaded', () => {
    console.log('DOM fully loaded');
    initializeApp();
});

window.addEventListener('load', () => {
    console.log('All resources loaded');
});

// Visibility API
document.addEventListener('visibilitychange', () => {
    if (document.hidden) {
        console.log('Page is hidden');
        pauseVideo();
    } else {
        console.log('Page is visible');
        resumeVideo();
    }
});
```

### Event Performance Optimization

```javascript
// Throttling scroll events
function throttle(func, limit) {
    let inThrottle;
    return function() {
        const args = arguments;
        const context = this;
        if (!inThrottle) {
            func.apply(context, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    };
}

const throttledScrollHandler = throttle(() => {
    console.log('Scroll event handled');
}, 100);

window.addEventListener('scroll', throttledScrollHandler);

// Debouncing input events
function debounce(func, delay) {
    let timeoutId;
    return function() {
        const args = arguments;
        const context = this;
        clearTimeout(timeoutId);
        timeoutId = setTimeout(() => func.apply(context, args), delay);
    };
}

const debouncedSearchHandler = debounce((event) => {
    performSearch(event.target.value);
}, 300);

searchInput.addEventListener('input', debouncedSearchHandler);

// Passive event listeners for better performance
element.addEventListener('touchstart', handler, { passive: true });
element.addEventListener('wheel', handler, { passive: true });

// Removing event listeners on cleanup
class ComponentManager {
    constructor(element) {
        this.element = element;
        this.eventListeners = [];
    }
    
    addEventListener(eventType, handler, options) {
        this.element.addEventListener(eventType, handler, options);
        this.eventListeners.push({ eventType, handler, options });
    }
    
    destroy() {
        this.eventListeners.forEach(({ eventType, handler, options }) => {
            this.element.removeEventListener(eventType, handler, options);
        });
        this.eventListeners = [];
    }
}
```

---

## Browser Object Model (BOM)

### Window Object

```javascript
// Window properties and methods
console.log('Window dimensions:', {
    innerWidth: window.innerWidth,
    innerHeight: window.innerHeight,
    outerWidth: window.outerWidth,
    outerHeight: window.outerHeight
});

console.log('Screen information:', {
    width: screen.width,
    height: screen.height,
    availWidth: screen.availWidth,
    availHeight: screen.availHeight,
    colorDepth: screen.colorDepth,
    pixelDepth: screen.pixelDepth
});

// Window methods
const newWindow = window.open('https://example.com', '_blank', 
    'width=800,height=600,scrollbars=yes,resizable=yes');

if (newWindow) {
    newWindow.focus();
    setTimeout(() => {
        newWindow.close();
    }, 5000);
}

// Window alerts and dialogs
window.alert('Alert message');
const confirmed = window.confirm('Are you sure?');
const userInput = window.prompt('Enter your name:', 'Default name');

// Modern alternatives to window dialogs
function showModernAlert(message, type = 'info') {
    const alert = document.createElement('div');
    alert.className = `alert alert-${type}`;
    alert.textContent = message;
    
    // Add to page and auto-remove
    document.body.appendChild(alert);
    setTimeout(() => alert.remove(), 3000);
}

function showModernConfirm(message) {
    return new Promise((resolve) => {
        const modal = document.createElement('div');
        modal.className = 'confirmation-modal';
        modal.innerHTML = `
            <div class="modal-content">
                <p>${message}</p>
                <button class="btn-confirm">Confirm</button>
                <button class="btn-cancel">Cancel</button>
            </div>
        `;
        
        modal.querySelector('.btn-confirm').addEventListener('click', () => {
            modal.remove();
            resolve(true);
        });
        
        modal.querySelector('.btn-cancel').addEventListener('click', () => {
            modal.remove();
            resolve(false);
        });
        
        document.body.appendChild(modal);
    });
}

// Timers
const timeoutId = setTimeout(() => {
    console.log('Timeout executed');
}, 1000);

const intervalId = setInterval(() => {
    console.log('Interval executed');
}, 2000);

// Clear timers
clearTimeout(timeoutId);
clearInterval(intervalId);

// Request Animation Frame
function animate() {
    // Animation logic here
    requestAnimationFrame(animate);
}
requestAnimationFrame(animate);

// Modern timer utilities
class TimerManager {
    constructor() {
        this.timers = new Set();
    }
    
    setTimeout(callback, delay) {
        const id = setTimeout(() => {
            this.timers.delete(id);
            callback();
        }, delay);
        
        this.timers.add(id);
        return id;
    }
    
    setInterval(callback, delay) {
        const id = setInterval(callback, delay);
        this.timers.add(id);
        return id;
    }
    
    clearTimeout(id) {
        clearTimeout(id);
        this.timers.delete(id);
    }
    
    clearInterval(id) {
        clearInterval(id);
        this.timers.delete(id);
    }
    
    clearAll() {
        this.timers.forEach(id => {
            clearTimeout(id);
            clearInterval(id);
        });
        this.timers.clear();
    }
}
```

### Location and History

```javascript
// Location object
console.log('Current URL info:', {
    href: location.href,
    protocol: location.protocol,
    host: location.host,
    hostname: location.hostname,
    port: location.port,
    pathname: location.pathname,
    search: location.search,
    hash: location.hash,
    origin: location.origin
});

// URL manipulation
const url = new URL(location.href);
console.log('URL parameters:', url.searchParams);

// Get specific parameter
const userId = url.searchParams.get('userId');

// Set parameters
url.searchParams.set('page', '2');
url.searchParams.append('filter', 'active');

// Navigation
location.href = 'https://example.com'; // Navigate to URL
location.replace('https://example.com'); // Navigate without history entry
location.reload(); // Reload page
location.reload(true); // Force reload from server

// History API
// Push new state
history.pushState({ page: 1 }, 'Page 1', '/page1');

// Replace current state
history.replaceState({ page: 1, modified: true }, 'Page 1', '/page1');

// Handle back/forward buttons
window.addEventListener('popstate', (event) => {
    console.log('Navigation state:', event.state);
    // Handle state change
    if (event.state) {
        loadPage(event.state.page);
    }
});

// History navigation
history.back(); // Go back
history.forward(); // Go forward
history.go(-2); // Go back 2 pages
history.go(1); // Go forward 1 page

// Modern routing utility
class Router {
    constructor() {
        this.routes = new Map();
        this.currentRoute = null;
        
        window.addEventListener('popstate', (event) => {
            this.handleRoute(location.pathname);
        });
    }
    
    addRoute(path, handler) {
        this.routes.set(path, handler);
    }
    
    navigate(path, state = {}) {
        history.pushState(state, '', path);
        this.handleRoute(path);
    }
    
    replace(path, state = {}) {
        history.replaceState(state, '', path);
        this.handleRoute(path);
    }
    
    handleRoute(path) {
        const handler = this.routes.get(path);
        if (handler) {
            this.currentRoute = path;
            handler(history.state);
        } else {
            console.warn(`No handler found for route: ${path}`);
        }
    }
    
    start() {
        this.handleRoute(location.pathname);
    }
}

// Usage
const router = new Router();
router.addRoute('/', () => loadHomePage());
router.addRoute('/about', () => loadAboutPage());
router.addRoute('/contact', () => loadContactPage());
router.start();
```

### Local Storage and Session Storage

```javascript
// Local Storage (persists until cleared)
localStorage.setItem('username', 'john_doe');
localStorage.setItem('preferences', JSON.stringify({
    theme: 'dark',
    language: 'en'
}));

const username = localStorage.getItem('username');
const preferences = JSON.parse(localStorage.getItem('preferences'));

localStorage.removeItem('username');
localStorage.clear(); // Remove all items

// Session Storage (persists until tab closed)
sessionStorage.setItem('sessionData', 'temporary');
const sessionData = sessionStorage.getItem('sessionData');

// Storage utility class
class StorageManager {
    constructor(storage = localStorage) {
        this.storage = storage;
    }
    
    set(key, value) {
        try {
            this.storage.setItem(key, JSON.stringify(value));
            return true;
        } catch (error) {
            console.error('Storage error:', error);
            return false;
        }
    }
    
    get(key, defaultValue = null) {
        try {
            const item = this.storage.getItem(key);
            return item ? JSON.parse(item) : defaultValue;
        } catch (error) {
            console.error('Storage error:', error);
            return defaultValue;
        }
    }
    
    remove(key) {
        this.storage.removeItem(key);
    }
    
    clear() {
        this.storage.clear();
    }
    
    has(key) {
        return this.storage.getItem(key) !== null;
    }
    
    keys() {
        return Object.keys(this.storage);
    }
    
    // Get all items as object
    getAll() {
        const items = {};
        for (let i = 0; i < this.storage.length; i++) {
            const key = this.storage.key(i);
            items[key] = this.get(key);
        }
        return items;
    }
    
    // Set expiration for items
    setWithExpiry(key, value, expiryMinutes) {
        const item = {
            value,
            expiry: Date.now() + (expiryMinutes * 60 * 1000)
        };
        this.set(key, item);
    }
    
    getWithExpiry(key) {
        const item = this.get(key);
        if (!item) return null;
        
        if (Date.now() > item.expiry) {
            this.remove(key);
            return null;
        }
        
        return item.value;
    }
}

// Usage
const storage = new StorageManager();
storage.set('user', { id: 1, name: 'John' });
storage.setWithExpiry('tempData', 'expires in 30 minutes', 30);

// Listen for storage changes
window.addEventListener('storage', (event) => {
    console.log('Storage changed:', {
        key: event.key,
        oldValue: event.oldValue,
        newValue: event.newValue,
        url: event.url
    });
});
```

### Navigator and Device Information

```javascript
// Navigator object
console.log('Browser info:', {
    userAgent: navigator.userAgent,
    language: navigator.language,
    languages: navigator.languages,
    platform: navigator.platform,
    cookieEnabled: navigator.cookieEnabled,
    onLine: navigator.onLine,
    hardwareConcurrency: navigator.hardwareConcurrency
});

// Feature detection
const features = {
    geolocation: 'geolocation' in navigator,
    serviceWorker: 'serviceWorker' in navigator,
    webGL: !!window.WebGLRenderingContext,
    localStorage: 'localStorage' in window,
    indexedDB: 'indexedDB' in window,
    webSocket: 'WebSocket' in window,
    fileReader: 'FileReader' in window,
    mediaDevices: 'mediaDevices' in navigator,
    clipboard: 'clipboard' in navigator
};

console.log('Supported features:', features);

// Geolocation API
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
        (position) => {
            console.log('Location:', {
                latitude: position.coords.latitude,
                longitude: position.coords.longitude,
                accuracy: position.coords.accuracy
            });
        },
        (error) => {
            console.error('Geolocation error:', error.message);
        },
        {
            enableHighAccuracy: true,
            timeout: 10000,
            maximumAge: 60000
        }
    );
}

// Network status
window.addEventListener('online', () => {
    console.log('Connection restored');
});

window.addEventListener('offline', () => {
    console.log('Connection lost');
});

// Connection API (experimental)
if ('connection' in navigator) {
    const connection = navigator.connection;
    console.log('Connection info:', {
        effectiveType: connection.effectiveType,
        downlink: connection.downlink,
        rtt: connection.rtt,
        saveData: connection.saveData
    });
    
    connection.addEventListener('change', () => {
        console.log('Connection changed:', connection.effectiveType);
    });
}

// Battery API (deprecated but educational)
if ('getBattery' in navigator) {
    navigator.getBattery().then((battery) => {
        console.log('Battery info:', {
            level: battery.level,
            charging: battery.charging,
            chargingTime: battery.chargingTime,
            dischargingTime: battery.dischargingTime
        });
    });
}

// Clipboard API
async function copyToClipboard(text) {
    try {
        if (navigator.clipboard) {
            await navigator.clipboard.writeText(text);
            console.log('Text copied to clipboard');
        } else {
            // Fallback for older browsers
            const textArea = document.createElement('textarea');
            textArea.value = text;
            document.body.appendChild(textArea);
            textArea.select();
            document.execCommand('copy');
            document.body.removeChild(textArea);
            console.log('Text copied using fallback method');
        }
    } catch (error) {
        console.error('Failed to copy text:', error);
    }
}

async function readFromClipboard() {
    try {
        if (navigator.clipboard) {
            const text = await navigator.clipboard.readText();
            console.log('Clipboard content:', text);
            return text;
        }
    } catch (error) {
        console.error('Failed to read clipboard:', error);
    }
}

// Permissions API
async function checkPermission(name) {
    try {
        const permission = await navigator.permissions.query({ name });
        console.log(`${name} permission:`, permission.state);
        return permission.state;
    } catch (error) {
        console.error('Permission check failed:', error);
        return 'unknown';
    }
}

// Check various permissions
checkPermission('geolocation');
checkPermission('notifications');
checkPermission('camera');
checkPermission('microphone');
```

---

## Advanced DOM Techniques

### Intersection Observer

```javascript
// Intersection Observer for lazy loading and animations
const observerOptions = {
    root: null, // Use viewport as root
    rootMargin: '0px 0px -100px 0px', // Trigger 100px before element enters viewport
    threshold: [0, 0.25, 0.5, 0.75, 1] // Multiple thresholds
};

const intersectionObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            // Element is visible
            const element = entry.target;
            
            // Lazy load images
            if (element.dataset.src) {
                element.src = element.dataset.src;
                element.removeAttribute('data-src');
            }
            
            // Trigger animations
            element.classList.add('animate-in');
            
            // Stop observing this element
            intersectionObserver.unobserve(element);
        }
    });
}, observerOptions);

// Observe elements
const lazyImages = document.querySelectorAll('img[data-src]');
const animateElements = document.querySelectorAll('.animate-on-scroll');

[...lazyImages, ...animateElements].forEach(element => {
    intersectionObserver.observe(element);
});

// Infinite scroll implementation
function setupInfiniteScroll(loadMoreCallback) {
    const sentinel = document.createElement('div');
    sentinel.className = 'scroll-sentinel';
    document.body.appendChild(sentinel);
    
    const sentinelObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                loadMoreCallback();
            }
        });
    });
    
    sentinelObserver.observe(sentinel);
    
    return () => {
        sentinelObserver.disconnect();
        sentinel.remove();
    };
}

// Usage
const cleanup = setupInfiniteScroll(async () => {
    const moreData = await fetchMoreData();
    renderData(moreData);
});
```

### Mutation Observer

```javascript
// Mutation Observer for DOM changes
const mutationObserver = new MutationObserver((mutations) => {
    mutations.forEach(mutation => {
        switch (mutation.type) {
            case 'childList':
                // Nodes added or removed
                mutation.addedNodes.forEach(node => {
                    if (node.nodeType === Node.ELEMENT_NODE) {
                        console.log('Element added:', node);
                        initializeNewElement(node);
                    }
                });
                
                mutation.removedNodes.forEach(node => {
                    if (node.nodeType === Node.ELEMENT_NODE) {
                        console.log('Element removed:', node);
                        cleanupElement(node);
                    }
                });
                break;
                
            case 'attributes':
                console.log(`Attribute ${mutation.attributeName} changed on:`, mutation.target);
                break;
                
            case 'characterData':
                console.log('Text content changed:', mutation.target);
                break;
        }
    });
});

// Start observing
mutationObserver.observe(document.body, {
    childList: true,      // Watch for child additions/removals
    subtree: true,        // Watch entire subtree
    attributes: true,     // Watch attribute changes
    attributeOldValue: true, // Include old attribute values
    characterData: true,  // Watch text changes
    characterDataOldValue: true // Include old text values
});

// Auto-initialize components when added to DOM
function initializeNewElement(element) {
    // Initialize components found in new element
    const modals = element.querySelectorAll('.modal');
    modals.forEach(modal => new ModalComponent(modal));
    
    const tooltips = element.querySelectorAll('[data-tooltip]');
    tooltips.forEach(tooltip => new TooltipComponent(tooltip));
}

// Stop observing
// mutationObserver.disconnect();
```

### Resize Observer

```javascript
// Resize Observer for responsive components
const resizeObserver = new ResizeObserver((entries) => {
    entries.forEach(entry => {
        const element = entry.target;
        const { width, height } = entry.contentRect;
        
        console.log(`Element resized: ${width}x${height}`);
        
        // Apply responsive behavior
        if (width < 600) {
            element.classList.add('mobile-layout');
            element.classList.remove('desktop-layout');
        } else {
            element.classList.add('desktop-layout');
            element.classList.remove('mobile-layout');
        }
        
        // Update charts or other components that need to know about size changes
        if (element.chart) {
            element.chart.resize();
        }
    });
});

// Observe elements
const responsiveElements = document.querySelectorAll('.responsive-component');
responsiveElements.forEach(element => {
    resizeObserver.observe(element);
});

// Container queries polyfill using ResizeObserver
class ContainerQuery {
    constructor(element, queries) {
        this.element = element;
        this.queries = queries;
        
        this.resizeObserver = new ResizeObserver(() => {
            this.checkQueries();
        });
        
        this.resizeObserver.observe(element);
        this.checkQueries(); // Initial check
    }
    
    checkQueries() {
        const width = this.element.offsetWidth;
        
        Object.entries(this.queries).forEach(([className, minWidth]) => {
            if (width >= minWidth) {
                this.element.classList.add(className);
            } else {
                this.element.classList.remove(className);
            }
        });
    }
    
    disconnect() {
        this.resizeObserver.disconnect();
    }
}

// Usage
const cardContainer = document.querySelector('.card-container');
new ContainerQuery(cardContainer, {
    'container-sm': 300,
    'container-md': 500,
    'container-lg': 700
});
```

---

## Real-World Example: Interactive Dashboard

```javascript
// Interactive Dashboard with modern DOM/BOM techniques

class Dashboard {
    constructor(container) {
        this.container = container;
        this.widgets = new Map();
        this.observers = new Set();
        this.storage = new StorageManager();
        this.eventBus = new EventTarget();
        
        this.init();
    }
    
    init() {
        this.setupLayout();
        this.setupEventListeners();
        this.setupObservers();
        this.loadState();
        this.startPerformanceMonitoring();
    }
    
    setupLayout() {
        this.container.innerHTML = `
            <header class="dashboard-header">
                <h1>Analytics Dashboard</h1>
                <div class="dashboard-controls">
                    <button class="btn-theme-toggle">🌙</button>
                    <button class="btn-fullscreen">⛶</button>
                    <button class="btn-export">📊</button>
                </div>
            </header>
            <main class="dashboard-content">
                <div class="widget-grid" id="widgetGrid">
                    <!-- Widgets will be added here -->
                </div>
            </main>
            <aside class="dashboard-sidebar">
                <div class="sidebar-content">
                    <h3>Widget Library</h3>
                    <div class="widget-library"></div>
                </div>
            </aside>
        `;
        
        this.widgetGrid = this.container.querySelector('#widgetGrid');
        this.setupDragAndDrop();
    }
    
    setupEventListeners() {
        // Theme toggle
        const themeToggle = this.container.querySelector('.btn-theme-toggle');
        themeToggle.addEventListener('click', () => this.toggleTheme());
        
        // Fullscreen
        const fullscreenBtn = this.container.querySelector('.btn-fullscreen');
        fullscreenBtn.addEventListener('click', () => this.toggleFullscreen());
        
        // Export
        const exportBtn = this.container.querySelector('.btn-export');
        exportBtn.addEventListener('click', () => this.exportData());
        
        // Keyboard shortcuts
        document.addEventListener('keydown', (event) => {
            if (event.ctrlKey || event.metaKey) {
                switch (event.key) {
                    case 's':
                        event.preventDefault();
                        this.saveState();
                        break;
                    case 'f':
                        event.preventDefault();
                        this.toggleFullscreen();
                        break;
                    case 'd':
                        event.preventDefault();
                        this.toggleTheme();
                        break;
                }
            }
        });
        
        // Window events
        window.addEventListener('beforeunload', () => {
            this.saveState();
        });
        
        window.addEventListener('online', () => {
            this.syncData();
        });
        
        window.addEventListener('offline', () => {
            this.showOfflineMessage();
        });
        
        // Visibility change
        document.addEventListener('visibilitychange', () => {
            if (document.hidden) {
                this.pauseUpdates();
            } else {
                this.resumeUpdates();
            }
        });
    }
    
    setupObservers() {
        // Intersection Observer for widget visibility
        const widgetObserver = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                const widget = this.widgets.get(entry.target.id);
                if (widget) {
                    if (entry.isIntersecting) {
                        widget.resume();
                    } else {
                        widget.pause();
                    }
                }
            });
        });
        
        // Resize Observer for responsive widgets
        const resizeObserver = new ResizeObserver((entries) => {
            entries.forEach(entry => {
                const widget = this.widgets.get(entry.target.id);
                if (widget) {
                    widget.handleResize(entry.contentRect);
                }
            });
        });
        
        // Mutation Observer for dynamic content
        const mutationObserver = new MutationObserver((mutations) => {
            mutations.forEach(mutation => {
                mutation.addedNodes.forEach(node => {
                    if (node.nodeType === Node.ELEMENT_NODE && node.classList.contains('widget')) {
                        this.initializeWidget(node);
                        widgetObserver.observe(node);
                        resizeObserver.observe(node);
                    }
                });
            });
        });
        
        mutationObserver.observe(this.widgetGrid, {
            childList: true,
            subtree: true
        });
        
        this.observers.add(widgetObserver);
        this.observers.add(resizeObserver);
        this.observers.add(mutationObserver);
    }
    
    setupDragAndDrop() {
        let draggedElement = null;
        
        this.widgetGrid.addEventListener('dragstart', (event) => {
            if (event.target.classList.contains('widget')) {
                draggedElement = event.target;
                event.target.style.opacity = '0.5';
                event.dataTransfer.effectAllowed = 'move';
            }
        });
        
        this.widgetGrid.addEventListener('dragend', (event) => {
            if (event.target.classList.contains('widget')) {
                event.target.style.opacity = '';
                draggedElement = null;
            }
        });
        
        this.widgetGrid.addEventListener('dragover', (event) => {
            event.preventDefault();
            event.dataTransfer.dropEffect = 'move';
        });
        
        this.widgetGrid.addEventListener('drop', (event) => {
            event.preventDefault();
            
            if (draggedElement) {
                const dropTarget = event.target.closest('.widget');
                if (dropTarget && dropTarget !== draggedElement) {
                    const gridRect = this.widgetGrid.getBoundingClientRect();
                    const dropRect = dropTarget.getBoundingClientRect();
                    
                    if (event.clientY < dropRect.top + dropRect.height / 2) {
                        this.widgetGrid.insertBefore(draggedElement, dropTarget);
                    } else {
                        this.widgetGrid.insertBefore(draggedElement, dropTarget.nextSibling);
                    }
                    
                    this.saveLayout();
                }
            }
        });
    }
    
    addWidget(type, config = {}) {
        const widgetId = `widget-${Date.now()}-${Math.random().toString(36).substr(2, 9)}`;
        const widgetElement = document.createElement('div');
        
        widgetElement.id = widgetId;
        widgetElement.className = 'widget';
        widgetElement.draggable = true;
        widgetElement.dataset.type = type;
        
        widgetElement.innerHTML = `
            <div class="widget-header">
                <h3 class="widget-title">${config.title || 'Widget'}</h3>
                <div class="widget-controls">
                    <button class="btn-widget-settings">⚙️</button>
                    <button class="btn-widget-close">✕</button>
                </div>
            </div>
            <div class="widget-content">
                <div class="widget-loading">Loading...</div>
            </div>
        `;
        
        // Widget controls
        const settingsBtn = widgetElement.querySelector('.btn-widget-settings');
        const closeBtn = widgetElement.querySelector('.btn-widget-close');
        
        settingsBtn.addEventListener('click', () => {
            this.showWidgetSettings(widgetId);
        });
        
        closeBtn.addEventListener('click', () => {
            this.removeWidget(widgetId);
        });
        
        this.widgetGrid.appendChild(widgetElement);
        
        // Create widget instance
        const widget = this.createWidgetInstance(type, widgetElement, config);
        this.widgets.set(widgetId, widget);
        
        // Trigger custom event
        this.eventBus.dispatchEvent(new CustomEvent('widgetAdded', {
            detail: { widgetId, type, config }
        }));
        
        return widgetId;
    }
    
    createWidgetInstance(type, element, config) {
        switch (type) {
            case 'chart':
                return new ChartWidget(element, config);
            case 'table':
                return new TableWidget(element, config);
            case 'metric':
                return new MetricWidget(element, config);
            default:
                return new BaseWidget(element, config);
        }
    }
    
    removeWidget(widgetId) {
        const widget = this.widgets.get(widgetId);
        if (widget) {
            widget.destroy();
            this.widgets.delete(widgetId);
            
            const element = document.getElementById(widgetId);
            if (element) {
                element.remove();
            }
            
            this.saveLayout();
            
            this.eventBus.dispatchEvent(new CustomEvent('widgetRemoved', {
                detail: { widgetId }
            }));
        }
    }
    
    toggleTheme() {
        const currentTheme = document.body.dataset.theme || 'light';
        const newTheme = currentTheme === 'light' ? 'dark' : 'light';
        
        document.body.dataset.theme = newTheme;
        this.storage.set('theme', newTheme);
        
        // Update theme toggle button
        const themeToggle = this.container.querySelector('.btn-theme-toggle');
        themeToggle.textContent = newTheme === 'light' ? '🌙' : '☀️';
    }
    
    async toggleFullscreen() {
        try {
            if (document.fullscreenElement) {
                await document.exitFullscreen();
            } else {
                await this.container.requestFullscreen();
            }
        } catch (error) {
            console.error('Fullscreen error:', error);
        }
    }
    
    exportData() {
        const data = {
            widgets: Array.from(this.widgets.entries()).map(([id, widget]) => ({
                id,
                type: widget.type,
                config: widget.getConfig(),
                data: widget.getData()
            })),
            layout: this.getLayout(),
            theme: document.body.dataset.theme,
            timestamp: new Date().toISOString()
        };
        
        const blob = new Blob([JSON.stringify(data, null, 2)], {
            type: 'application/json'
        });
        
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `dashboard-export-${Date.now()}.json`;
        a.click();
        
        URL.revokeObjectURL(url);
    }
    
    saveState() {
        const state = {
            layout: this.getLayout(),
            widgets: Array.from(this.widgets.entries()).map(([id, widget]) => ({
                id,
                type: widget.type,
                config: widget.getConfig()
            })),
            theme: document.body.dataset.theme
        };
        
        this.storage.set('dashboardState', state);
    }
    
    loadState() {
        const state = this.storage.get('dashboardState');
        if (state) {
            // Load theme
            if (state.theme) {
                document.body.dataset.theme = state.theme;
                const themeToggle = this.container.querySelector('.btn-theme-toggle');
                themeToggle.textContent = state.theme === 'light' ? '🌙' : '☀️';
            }
            
            // Load widgets
            if (state.widgets) {
                state.widgets.forEach(({ type, config }) => {
                    this.addWidget(type, config);
                });
            }
        }
    }
    
    getLayout() {
        return Array.from(this.widgetGrid.children).map(element => ({
            id: element.id,
            type: element.dataset.type,
            order: Array.from(this.widgetGrid.children).indexOf(element)
        }));
    }
    
    saveLayout() {
        this.storage.set('dashboardLayout', this.getLayout());
    }
    
    startPerformanceMonitoring() {
        // Monitor performance
        if ('performance' in window) {
            setInterval(() => {
                const memory = performance.memory;
                if (memory) {
                    console.log('Memory usage:', {
                        used: Math.round(memory.usedJSHeapSize / 1024 / 1024) + ' MB',
                        total: Math.round(memory.totalJSHeapSize / 1024 / 1024) + ' MB',
                        limit: Math.round(memory.jsHeapSizeLimit / 1024 / 1024) + ' MB'
                    });
                }
            }, 30000);
        }
    }
    
    pauseUpdates() {
        this.widgets.forEach(widget => widget.pause());
    }
    
    resumeUpdates() {
        this.widgets.forEach(widget => widget.resume());
    }
    
    showOfflineMessage() {
        const message = document.createElement('div');
        message.className = 'offline-message';
        message.textContent = 'You are offline. Some features may be limited.';
        document.body.appendChild(message);
        
        setTimeout(() => message.remove(), 5000);
    }
    
    async syncData() {
        try {
            // Sync data when back online
            const pendingData = this.storage.get('pendingSync');
            if (pendingData) {
                await this.uploadData(pendingData);
                this.storage.remove('pendingSync');
            }
        } catch (error) {
            console.error('Sync failed:', error);
        }
    }
    
    destroy() {
        // Clean up observers
        this.observers.forEach(observer => observer.disconnect());
        
        // Clean up widgets
        this.widgets.forEach(widget => widget.destroy());
        
        // Save final state
        this.saveState();
    }
}

// Base widget class
class BaseWidget {
    constructor(element, config) {
        this.element = element;
        this.config = config;
        this.type = 'base';
        this.isVisible = true;
        this.updateInterval = null;
        
        this.init();
    }
    
    init() {
        this.render();
        this.startUpdates();
    }
    
    render() {
        const content = this.element.querySelector('.widget-content');
        content.innerHTML = '<p>Base widget content</p>';
    }
    
    startUpdates() {
        if (this.config.autoUpdate && this.config.updateInterval) {
            this.updateInterval = setInterval(() => {
                if (this.isVisible) {
                    this.update();
                }
            }, this.config.updateInterval);
        }
    }
    
    update() {
        // Override in subclasses
    }
    
    pause() {
        this.isVisible = false;
    }
    
    resume() {
        this.isVisible = true;
    }
    
    handleResize(rect) {
        // Override in subclasses
        console.log(`Widget resized: ${rect.width}x${rect.height}`);
    }
    
    getConfig() {
        return { ...this.config };
    }
    
    getData() {
        return {};
    }
    
    destroy() {
        if (this.updateInterval) {
            clearInterval(this.updateInterval);
        }
    }
}

// Usage
const dashboardContainer = document.querySelector('#dashboard');
const dashboard = new Dashboard(dashboardContainer);

// Add some widgets
dashboard.addWidget('chart', {
    title: 'Sales Chart',
    autoUpdate: true,
    updateInterval: 5000
});

dashboard.addWidget('metric', {
    title: 'Total Revenue',
    autoUpdate: true,
    updateInterval: 10000
});

dashboard.addWidget('table', {
    title: 'Recent Orders',
    autoUpdate: true,
    updateInterval: 15000
});
```

---

## Best Practices Summary

### 1. **DOM Manipulation**
- Use modern query selectors (querySelector, querySelectorAll)
- Prefer textContent over innerHTML for security
- Use DocumentFragment for multiple DOM operations
- Cache DOM references when possible

### 2. **Event Handling**
- Use event delegation for dynamic content
- Remove event listeners to prevent memory leaks
- Use passive listeners for better performance
- Implement throttling and debouncing for frequent events

### 3. **Performance**
- Use Intersection Observer for lazy loading and visibility detection
- Implement virtual scrolling for large lists
- Use requestAnimationFrame for smooth animations
- Minimize DOM queries and modifications

### 4. **Browser APIs**
- Feature detection before using modern APIs
- Provide fallbacks for unsupported features
- Handle offline scenarios gracefully
- Use appropriate storage mechanisms (localStorage vs sessionStorage)

### 5. **Accessibility**
- Ensure keyboard navigation works
- Use ARIA attributes appropriately
- Provide focus indicators
- Handle screen reader compatibility

This comprehensive guide covers modern DOM and BOM APIs with practical examples for professional web development, from basic manipulation to advanced techniques like observers and browser feature integration.