# Next.js

## Introduction to Next.js

Next.js is a React framework that provides additional features such as server-side rendering (SSR), static site generation (SSG), API routes, and automatic code splitting. It's designed to make React development more efficient and performant.

---

## Getting Started

### Installation

```bash
npx create-next-app@latest my-app
cd my-app
npm run dev
```

### Project Structure

```
my-app/
├── pages/
│   ├── api/
│   ├── _app.js
│   ├── _document.js
│   └── index.js
├── public/
├── styles/
├── components/
├── next.config.js
└── package.json
```

---

## Pages and Routing

### File-based Routing

```jsx
// pages/index.js - Home page (/)
export default function Home() {
  return <h1>Welcome to Next.js!</h1>;
}

// pages/about.js - About page (/about)
export default function About() {
  return <h1>About Us</h1>;
}

// pages/users/index.js - Users page (/users)
export default function Users() {
  return <h1>Users List</h1>;
}

// pages/users/[id].js - Dynamic route (/users/123)
import { useRouter } from 'next/router';

export default function User() {
  const router = useRouter();
  const { id } = router.query;
  
  return <h1>User ID: {id}</h1>;
}
```

### Dynamic Routes

```jsx
// pages/posts/[slug].js
import { useRouter } from 'next/router';

export default function Post() {
  const router = useRouter();
  const { slug } = router.query;
  
  return <div>Post: {slug}</div>;
}

// pages/posts/[...slug].js - Catch-all routes
export default function Post() {
  const router = useRouter();
  const { slug } = router.query; // slug is an array
  
  return <div>Post path: {slug.join('/')}</div>;
}

// pages/shop/[[...slug]].js - Optional catch-all routes
export default function Shop() {
  const router = useRouter();
  const { slug } = router.query;
  
  return <div>Shop: {slug ? slug.join('/') : 'Shop Home'}</div>;
}
```

---

## Data Fetching

### Static Site Generation (SSG)

```jsx
// pages/posts.js
export default function Posts({ posts }) {
  return (
    <div>
      <h1>Posts</h1>
      {posts.map(post => (
        <div key={post.id}>
          <h2>{post.title}</h2>
          <p>{post.content}</p>
        </div>
      ))}
    </div>
  );
}

// This function gets called at build time
export async function getStaticProps() {
  // Fetch data from external API
  const res = await fetch('https://api.example.com/posts');
  const posts = await res.json();

  return {
    props: {
      posts,
    },
    // Regenerate the page at most once every hour
    revalidate: 3600,
  };
}
```

### Static Generation with Dynamic Routes

```jsx
// pages/posts/[id].js
export default function Post({ post }) {
  return (
    <div>
      <h1>{post.title}</h1>
      <p>{post.content}</p>
    </div>
  );
}

export async function getStaticPaths() {
  // Get the paths we want to pre-render based on posts
  const res = await fetch('https://api.example.com/posts');
  const posts = await res.json();

  const paths = posts.map((post) => ({
    params: { id: post.id.toString() },
  }));

  return {
    paths,
    fallback: false, // or true, 'blocking'
  };
}

export async function getStaticProps({ params }) {
  // Fetch necessary data for the blog post using params.id
  const res = await fetch(`https://api.example.com/posts/${params.id}`);
  const post = await res.json();

  return {
    props: {
      post,
    },
  };
}
```

### Server-Side Rendering (SSR)

```jsx
// pages/dashboard.js
export default function Dashboard({ user, data }) {
  return (
    <div>
      <h1>Dashboard</h1>
      <p>Welcome, {user.name}!</p>
      <div>{/* Render user-specific data */}</div>
    </div>
  );
}

// This gets called on every request
export async function getServerSideProps(context) {
  const { req, res, params, query } = context;
  
  // Get user session/token from request
  const token = req.cookies.token;
  
  if (!token) {
    return {
      redirect: {
        destination: '/login',
        permanent: false,
      },
    };
  }

  // Fetch user-specific data
  const userRes = await fetch('https://api.example.com/user', {
    headers: { Authorization: `Bearer ${token}` },
  });
  const user = await userRes.json();

  const dataRes = await fetch(`https://api.example.com/user/${user.id}/data`);
  const data = await dataRes.json();

  return {
    props: {
      user,
      data,
    },
  };
}
```

---

## API Routes

### Basic API Route

```javascript
// pages/api/hello.js
export default function handler(req, res) {
  res.status(200).json({ message: 'Hello World' });
}
```

### CRUD API Routes

```javascript
// pages/api/users/index.js
import { users } from '../../../data/users'; // Mock data

export default function handler(req, res) {
  const { method } = req;

  switch (method) {
    case 'GET':
      res.status(200).json(users);
      break;
      
    case 'POST':
      const { name, email } = req.body;
      const newUser = {
        id: users.length + 1,
        name,
        email,
        createdAt: new Date().toISOString(),
      };
      users.push(newUser);
      res.status(201).json(newUser);
      break;
      
    default:
      res.setHeader('Allow', ['GET', 'POST']);
      res.status(405).end(`Method ${method} Not Allowed`);
  }
}

// pages/api/users/[id].js
export default function handler(req, res) {
  const { method, query: { id } } = req;

  switch (method) {
    case 'GET':
      const user = users.find(u => u.id === parseInt(id));
      if (!user) {
        return res.status(404).json({ message: 'User not found' });
      }
      res.status(200).json(user);
      break;
      
    case 'PUT':
      const userIndex = users.findIndex(u => u.id === parseInt(id));
      if (userIndex === -1) {
        return res.status(404).json({ message: 'User not found' });
      }
      
      users[userIndex] = { ...users[userIndex], ...req.body };
      res.status(200).json(users[userIndex]);
      break;
      
    case 'DELETE':
      const deleteIndex = users.findIndex(u => u.id === parseInt(id));
      if (deleteIndex === -1) {
        return res.status(404).json({ message: 'User not found' });
      }
      
      users.splice(deleteIndex, 1);
      res.status(204).end();
      break;
      
    default:
      res.setHeader('Allow', ['GET', 'PUT', 'DELETE']);
      res.status(405).end(`Method ${method} Not Allowed`);
  }
}
```

---

## Custom App and Document

### Custom App (_app.js)

```jsx
// pages/_app.js
import '../styles/globals.css';
import { useState, useEffect } from 'react';
import Layout from '../components/Layout';

function MyApp({ Component, pageProps }) {
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    // Global app logic here
    console.log('App mounted');
  }, []);

  return (
    <Layout>
      {loading && <div>Loading...</div>}
      <Component {...pageProps} />
    </Layout>
  );
}

export default MyApp;
```

### Custom Document (_document.js)

```jsx
// pages/_document.js
import { Html, Head, Main, NextScript } from 'next/document';

export default function Document() {
  return (
    <Html lang="en">
      <Head>
        {/* Global meta tags, fonts, etc. */}
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link
          href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
          rel="stylesheet"
        />
      </Head>
      <body>
        <Main />
        <NextScript />
      </body>
    </Html>
  );
}
```

---

## Styling

### CSS Modules

```jsx
// components/Button.module.css
.button {
  background-color: #0070f3;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.button:hover {
  background-color: #0051cc;
}

// components/Button.js
import styles from './Button.module.css';

export default function Button({ children, onClick }) {
  return (
    <button className={styles.button} onClick={onClick}>
      {children}
    </button>
  );
}
```

### Styled JSX

```jsx
export default function StyledComponent() {
  return (
    <div>
      <h1>Styled with JSX</h1>
      <p>This paragraph has custom styles.</p>
      
      <style jsx>{`
        h1 {
          color: #0070f3;
          font-size: 2rem;
        }
        
        p {
          color: #666;
          line-height: 1.6;
        }
      `}</style>
      
      <style jsx global>{`
        body {
          margin: 0;
          font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto;
        }
      `}</style>
    </div>
  );
}
```

---

## Image Optimization

```jsx
import Image from 'next/image';

export default function MyComponent() {
  return (
    <div>
      {/* Optimized image with automatic WebP conversion */}
      <Image
        src="/hero-image.jpg"
        alt="Hero Image"
        width={800}
        height={400}
        priority // Load this image with high priority
      />
      
      {/* Responsive image */}
      <Image
        src="/profile.jpg"
        alt="Profile"
        fill
        style={{ objectFit: 'cover' }}
      />
      
      {/* External image */}
      <Image
        src="https://example.com/image.jpg"
        alt="External Image"
        width={500}
        height={300}
        loader={({ src, width, quality }) => {
          return `https://example.com/${src}?w=${width}&q=${quality || 75}`;
        }}
      />
    </div>
  );
}
```

---

## Middleware

```javascript
// middleware.js (in project root)
import { NextRequest, NextResponse } from 'next/server';

export function middleware(request) {
  // Check if user is authenticated
  const token = request.cookies.get('token');
  
  if (!token && request.nextUrl.pathname.startsWith('/dashboard')) {
    return NextResponse.redirect(new URL('/login', request.url));
  }
  
  // Add custom header
  const response = NextResponse.next();
  response.headers.set('X-Custom-Header', 'Hello from middleware');
  
  return response;
}

export const config = {
  matcher: ['/dashboard/:path*', '/api/protected/:path*'],
};
```

---

## Environment Variables

```bash
# .env.local
DATABASE_URL=mongodb://localhost:27017/myapp
API_SECRET=your-secret-key
NEXT_PUBLIC_API_URL=https://api.example.com
```

```javascript
// next.config.js
/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  env: {
    CUSTOM_KEY: process.env.CUSTOM_KEY,
  },
  images: {
    domains: ['example.com', 'cdn.example.com'],
  },
  async redirects() {
    return [
      {
        source: '/old-page',
        destination: '/new-page',
        permanent: true,
      },
    ];
  },
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'https://api.example.com/:path*',
      },
    ];
  },
};

module.exports = nextConfig;
```

---

## Performance Optimization

### Code Splitting

```jsx
import dynamic from 'next/dynamic';
import { Suspense } from 'react';

// Dynamic import with loading component
const DynamicComponent = dynamic(() => import('../components/HeavyComponent'), {
  loading: () => <p>Loading...</p>,
  ssr: false, // Disable server-side rendering for this component
});

// Dynamic import with Suspense
const LazyComponent = dynamic(() => import('../components/LazyComponent'));

export default function Page() {
  return (
    <div>
      <h1>My Page</h1>
      <DynamicComponent />
      
      <Suspense fallback={<div>Loading lazy component...</div>}>
        <LazyComponent />
      </Suspense>
    </div>
  );
}
```

### Font Optimization

```jsx
// pages/_app.js
import { Inter } from 'next/font/google';
import localFont from 'next/font/local';

const inter = Inter({ subsets: ['latin'] });

const customFont = localFont({
  src: './fonts/CustomFont.woff2',
  display: 'swap',
});

export default function MyApp({ Component, pageProps }) {
  return (
    <main className={inter.className}>
      <Component {...pageProps} />
    </main>
  );
}
```

---

## Deployment

### Vercel Deployment

```bash
# Install Vercel CLI
npm i -g vercel

# Deploy
vercel --prod
```

### Custom Server (Optional)

```javascript
// server.js
const { createServer } = require('http');
const { parse } = require('url');
const next = require('next');

const dev = process.env.NODE_ENV !== 'production';
const hostname = 'localhost';
const port = process.env.PORT || 3000;

const app = next({ dev, hostname, port });
const handle = app.getRequestHandler();

app.prepare().then(() => {
  createServer(async (req, res) => {
    try {
      const parsedUrl = parse(req.url, true);
      const { pathname, query } = parsedUrl;

      if (pathname === '/custom-route') {
        await app.render(req, res, '/custom-page', query);
      } else {
        await handle(req, res, parsedUrl);
      }
    } catch (err) {
      console.error('Error occurred handling', req.url, err);
      res.statusCode = 500;
      res.end('internal server error');
    }
  }).listen(port, (err) => {
    if (err) throw err;
    console.log(`> Ready on http://${hostname}:${port}`);
  });
});
```

---

## Best Practices

1. **Use Static Generation when possible** - Better performance and SEO
2. **Optimize images** - Use Next.js Image component
3. **Implement proper error boundaries** - Handle errors gracefully
4. **Use TypeScript** - Better type safety and developer experience
5. **Optimize fonts** - Use next/font for automatic font optimization
6. **Implement proper SEO** - Use next/head for meta tags
7. **Use middleware sparingly** - Only for critical functionality
8. **Optimize bundle size** - Use dynamic imports for large components
9. **Follow the principle of least privilege** - Only expose necessary API routes
10. **Use environment variables** - For configuration and secrets
11. **Implement proper caching** - Use ISR and API route caching
12. **Monitor performance** - Use Next.js Analytics and Web Vitals

---