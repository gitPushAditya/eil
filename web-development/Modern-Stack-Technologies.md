# Modern Web Development Stack - Additional Technologies

This comprehensive guide covers the remaining essential modern web development technologies: Zustand, Spring Boot, Next.js, MongoDB, PostgreSQL, and Prisma.

---

## Zustand - Simple State Management

Zustand is a small, fast, and scalable state management solution for React applications.

### Setup and Basic Usage

```bash
npm install zustand
```

```javascript
// store/userStore.js
import { create } from 'zustand';
import { devtools, persist } from 'zustand/middleware';

const useUserStore = create()(
  devtools(
    persist(
      (set, get) => ({
        // State
        user: null,
        isLoading: false,
        error: null,
        
        // Actions
        setUser: (user) => set({ user }),
        
        login: async (credentials) => {
          set({ isLoading: true, error: null });
          try {
            const response = await fetch('/api/login', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(credentials)
            });
            
            if (!response.ok) throw new Error('Login failed');
            
            const userData = await response.json();
            set({ user: userData, isLoading: false });
          } catch (error) {
            set({ error: error.message, isLoading: false });
          }
        },
        
        logout: () => {
          set({ user: null });
          localStorage.removeItem('token');
        },
        
        updateProfile: (updates) => set((state) => ({
          user: { ...state.user, ...updates }
        }))
      }),
      {
        name: 'user-storage',
        getStorage: () => localStorage
      }
    ),
    { name: 'user-store' }
  )
);

export default useUserStore;
```

### Advanced Patterns

```javascript
// store/todoStore.js
import { create } from 'zustand';
import { immer } from 'zustand/middleware/immer';

const useTodoStore = create()(
  immer((set, get) => ({
    todos: [],
    filter: 'all',
    
    addTodo: (text) => set((state) => {
      state.todos.push({
        id: Date.now(),
        text,
        completed: false,
        createdAt: new Date()
      });
    }),
    
    toggleTodo: (id) => set((state) => {
      const todo = state.todos.find(t => t.id === id);
      if (todo) todo.completed = !todo.completed;
    }),
    
    deleteTodo: (id) => set((state) => {
      const index = state.todos.findIndex(t => t.id === id);
      if (index > -1) state.todos.splice(index, 1);
    }),
    
    setFilter: (filter) => set({ filter }),
    
    // Computed values
    get filteredTodos() {
      const { todos, filter } = get();
      switch (filter) {
        case 'active': return todos.filter(t => !t.completed);
        case 'completed': return todos.filter(t => t.completed);
        default: return todos;
      }
    },
    
    get stats() {
      const todos = get().todos;
      return {
        total: todos.length,
        completed: todos.filter(t => t.completed).length,
        active: todos.filter(t => !t.completed).length
      };
    }
  }))
);

// Usage in components
const TodoApp = () => {
  const { todos, addTodo, toggleTodo, filter, setFilter, stats } = useTodoStore();
  const filteredTodos = useTodoStore(state => state.filteredTodos);
  
  return (
    <div>
      <h1>Todos ({stats.active} active)</h1>
      {/* Component JSX */}
    </div>
  );
};
```

---

## Spring Boot - Java Enterprise Framework

Spring Boot is a framework that makes it easy to create stand-alone, production-grade Spring-based applications.

### Project Setup

```bash
# Using Spring Initializr CLI
spring init --dependencies=web,data-jpa,security,validation,actuator my-app
cd my-app
```

```xml
<!-- pom.xml dependencies -->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Entity and Repository

```java
// User.java
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @JsonIgnore
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}

// UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT u FROM User u WHERE u.createdAt BETWEEN :start AND :end")
    List<User> findUsersCreatedBetween(
        @Param("start") LocalDateTime start, 
        @Param("end") LocalDateTime end
    );
}
```

### Service Layer

```java
// UserService.java
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("User with email already exists");
        }
        
        User user = User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
            
        return userRepository.save(user);
    }
    
    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
    
    public User updateUser(Long id, UpdateUserRequest request) {
        User user = getUserById(id);
        
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateUserException("Email already in use");
            }
            user.setEmail(request.getEmail());
        }
        
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
```

### REST Controller

```java
// UserController.java
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(
        @Valid @RequestBody CreateUserRequest request
    ) {
        User user = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("User created successfully", user));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<User>>> getAllUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<User> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUserRequest request
    ) {
        User user = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", user));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
    }
}

// ApiResponse.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, LocalDateTime.now());
    }
    
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, LocalDateTime.now());
    }
}
```

### Security Configuration

```java
// SecurityConfig.java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
```

---

## Next.js - Full-Stack React Framework

Next.js is a React framework that provides features like server-side rendering, static site generation, and API routes.

### Setup and Project Structure

```bash
npx create-next-app@latest my-app --typescript --tailwind --eslint --app
cd my-app
npm install @next/font lucide-react
```

```
my-app/
├── app/
│   ├── globals.css
│   ├── layout.tsx
│   ├── page.tsx
│   ├── loading.tsx
│   ├── error.tsx
│   ├── not-found.tsx
│   ├── api/
│   │   ├── auth/
│   │   └── users/
│   ├── (auth)/
│   │   ├── login/
│   │   └── register/
│   └── dashboard/
├── components/
├── lib/
├── types/
└── middleware.ts
```

### App Router and Layouts

```tsx
// app/layout.tsx
import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import { ThemeProvider } from '@/components/theme-provider';
import { Toaster } from '@/components/ui/toaster';

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'My Next.js App',
  description: 'A modern web application built with Next.js',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body className={inter.className}>
        <ThemeProvider
          attribute="class"
          defaultTheme="system"
          enableSystem
          disableTransitionOnChange
        >
          {children}
          <Toaster />
        </ThemeProvider>
      </body>
    </html>
  );
}

// app/dashboard/layout.tsx
import { Navbar } from '@/components/navbar';
import { Sidebar } from '@/components/sidebar';

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex h-screen">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Navbar />
        <main className="flex-1 p-6">{children}</main>
      </div>
    </div>
  );
}
```

### API Routes

```typescript
// app/api/users/route.ts
import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';
import bcrypt from 'bcryptjs';
import { z } from 'zod';

const createUserSchema = z.object({
  name: z.string().min(2).max(50),
  email: z.string().email(),
  password: z.string().min(8),
});

export async function GET(request: NextRequest) {
  try {
    const { searchParams } = new URL(request.url);
    const page = parseInt(searchParams.get('page') || '1');
    const limit = parseInt(searchParams.get('limit') || '10');
    const skip = (page - 1) * limit;

    const [users, total] = await Promise.all([
      prisma.user.findMany({
        skip,
        take: limit,
        select: {
          id: true,
          name: true,
          email: true,
          createdAt: true,
        },
        orderBy: { createdAt: 'desc' },
      }),
      prisma.user.count(),
    ]);

    return NextResponse.json({
      users,
      pagination: {
        page,
        limit,
        total,
        pages: Math.ceil(total / limit),
      },
    });
  } catch (error) {
    return NextResponse.json(
      { error: 'Failed to fetch users' },
      { status: 500 }
    );
  }
}

export async function POST(request: NextRequest) {
  try {
    const body = await request.json();
    const { name, email, password } = createUserSchema.parse(body);

    // Check if user exists
    const existingUser = await prisma.user.findUnique({
      where: { email },
    });

    if (existingUser) {
      return NextResponse.json(
        { error: 'User already exists' },
        { status: 409 }
      );
    }

    // Hash password
    const hashedPassword = await bcrypt.hash(password, 12);

    // Create user
    const user = await prisma.user.create({
      data: {
        name,
        email,
        password: hashedPassword,
      },
      select: {
        id: true,
        name: true,
        email: true,
        createdAt: true,
      },
    });

    return NextResponse.json(user, { status: 201 });
  } catch (error) {
    if (error instanceof z.ZodError) {
      return NextResponse.json(
        { error: 'Validation error', details: error.errors },
        { status: 400 }
      );
    }

    return NextResponse.json(
      { error: 'Failed to create user' },
      { status: 500 }
    );
  }
}

// app/api/users/[id]/route.ts
export async function GET(
  request: NextRequest,
  { params }: { params: { id: string } }
) {
  try {
    const user = await prisma.user.findUnique({
      where: { id: params.id },
      select: {
        id: true,
        name: true,
        email: true,
        createdAt: true,
        posts: {
          select: {
            id: true,
            title: true,
            createdAt: true,
          },
        },
      },
    });

    if (!user) {
      return NextResponse.json(
        { error: 'User not found' },
        { status: 404 }
      );
    }

    return NextResponse.json(user);
  } catch (error) {
    return NextResponse.json(
      { error: 'Failed to fetch user' },
      { status: 500 }
    );
  }
}
```

### Server Components and Data Fetching

```tsx
// app/users/page.tsx
import { UserList } from '@/components/user-list';
import { SearchUsers } from '@/components/search-users';
import { Suspense } from 'react';
import { UserListSkeleton } from '@/components/user-list-skeleton';

interface PageProps {
  searchParams: {
    page?: string;
    search?: string;
  };
}

export default function UsersPage({ searchParams }: PageProps) {
  const page = parseInt(searchParams.page || '1');
  const search = searchParams.search || '';

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-3xl font-bold">Users</h1>
        <SearchUsers />
      </div>
      
      <Suspense fallback={<UserListSkeleton />}>
        <UserList page={page} search={search} />
      </Suspense>
    </div>
  );
}

// components/user-list.tsx
async function getUsers(page: number, search: string) {
  const params = new URLSearchParams({
    page: page.toString(),
    limit: '10',
    ...(search && { search }),
  });

  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/api/users?${params}`,
    { next: { revalidate: 3600 } } // Revalidate every hour
  );

  if (!response.ok) {
    throw new Error('Failed to fetch users');
  }

  return response.json();
}

export async function UserList({ page, search }: { page: number; search: string }) {
  const data = await getUsers(page, search);

  return (
    <div className="space-y-4">
      {data.users.map((user: any) => (
        <UserCard key={user.id} user={user} />
      ))}
      
      <Pagination {...data.pagination} />
    </div>
  );
}
```

---

## MongoDB - NoSQL Database

MongoDB is a document-oriented NoSQL database that stores data in flexible, JSON-like documents.

### Connection and Basic Setup

```javascript
// config/database.js
const { MongoClient } = require('mongodb');
const mongoose = require('mongoose');

// Native MongoDB driver
class MongoDBConnection {
  constructor() {
    this.client = null;
    this.db = null;
  }

  async connect() {
    try {
      this.client = new MongoClient(process.env.MONGODB_URI, {
        useUnifiedTopology: true,
      });
      
      await this.client.connect();
      this.db = this.client.db(process.env.DB_NAME);
      console.log('Connected to MongoDB');
    } catch (error) {
      console.error('MongoDB connection error:', error);
      process.exit(1);
    }
  }

  getDB() {
    return this.db;
  }

  async close() {
    if (this.client) {
      await this.client.close();
    }
  }
}

// Mongoose connection
const connectMongoDB = async () => {
  try {
    await mongoose.connect(process.env.MONGODB_URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
    console.log('Connected to MongoDB with Mongoose');
  } catch (error) {
    console.error('MongoDB connection error:', error);
    process.exit(1);
  }
};

module.exports = { MongoDBConnection, connectMongoDB };
```

### Advanced Queries and Aggregations

```javascript
// services/postService.js
class PostService {
  constructor(db) {
    this.collection = db.collection('posts');
  }

  // Find with complex queries
  async findPosts(filters = {}) {
    const query = {};
    
    if (filters.author) {
      query.author = new ObjectId(filters.author);
    }
    
    if (filters.category) {
      query.category = filters.category;
    }
    
    if (filters.tags && filters.tags.length > 0) {
      query.tags = { $in: filters.tags };
    }
    
    if (filters.dateRange) {
      query.createdAt = {
        $gte: new Date(filters.dateRange.start),
        $lte: new Date(filters.dateRange.end)
      };
    }
    
    if (filters.search) {
      query.$text = { $search: filters.search };
    }
    
    const options = {
      sort: { [filters.sortBy || 'createdAt']: filters.sortOrder === 'asc' ? 1 : -1 },
      skip: (filters.page - 1) * filters.limit,
      limit: filters.limit
    };
    
    const [posts, total] = await Promise.all([
      this.collection.find(query, options).toArray(),
      this.collection.countDocuments(query)
    ]);
    
    return { posts, total };
  }

  // Aggregation pipeline
  async getPostStatistics() {
    const pipeline = [
      // Group by category and count
      {
        $group: {
          _id: '$category',
          count: { $sum: 1 },
          avgViews: { $avg: '$views' },
          totalLikes: { $sum: { $size: '$likes' } }
        }
      },
      
      // Sort by count
      { $sort: { count: -1 } },
      
      // Add percentage calculation
      {
        $group: {
          _id: null,
          categories: { $push: '$$ROOT' },
          totalPosts: { $sum: '$count' }
        }
      },
      
      // Calculate percentages
      {
        $project: {
          _id: 0,
          categories: {
            $map: {
              input: '$categories',
              as: 'category',
              in: {
                name: '$$category._id',
                count: '$$category.count',
                avgViews: '$$category.avgViews',
                totalLikes: '$$category.totalLikes',
                percentage: {
                  $multiply: [
                    { $divide: ['$$category.count', '$totalPosts'] },
                    100
                  ]
                }
              }
            }
          }
        }
      }
    ];
    
    const result = await this.collection.aggregate(pipeline).toArray();
    return result[0]?.categories || [];
  }

  // Complex aggregation with lookups
  async getPostsWithAuthorInfo(page = 1, limit = 10) {
    const pipeline = [
      // Match published posts
      { $match: { status: 'published' } },
      
      // Lookup author information
      {
        $lookup: {
          from: 'users',
          localField: 'author',
          foreignField: '_id',
          as: 'authorInfo'
        }
      },
      
      // Unwind author array
      { $unwind: '$authorInfo' },
      
      // Lookup comments
      {
        $lookup: {
          from: 'comments',
          localField: '_id',
          foreignField: 'postId',
          as: 'comments'
        }
      },
      
      // Add computed fields
      {
        $addFields: {
          commentCount: { $size: '$comments' },
          likeCount: { $size: '$likes' },
          author: {
            id: '$authorInfo._id',
            name: '$authorInfo.name',
            avatar: '$authorInfo.avatar'
          }
        }
      },
      
      // Project only needed fields
      {
        $project: {
          title: 1,
          excerpt: 1,
          slug: 1,
          category: 1,
          tags: 1,
          createdAt: 1,
          views: 1,
          likeCount: 1,
          commentCount: 1,
          author: 1
        }
      },
      
      // Sort and paginate
      { $sort: { createdAt: -1 } },
      { $skip: (page - 1) * limit },
      { $limit: limit }
    ];
    
    return await this.collection.aggregate(pipeline).toArray();
  }

  // Update with atomic operations
  async likePost(postId, userId) {
    const result = await this.collection.updateOne(
      { 
        _id: new ObjectId(postId),
        'likes.user': { $ne: new ObjectId(userId) }
      },
      {
        $push: {
          likes: {
            user: new ObjectId(userId),
            createdAt: new Date()
          }
        }
      }
    );
    
    if (result.modifiedCount === 0) {
      // User already liked, so unlike
      await this.collection.updateOne(
        { _id: new ObjectId(postId) },
        {
          $pull: {
            likes: { user: new ObjectId(userId) }
          }
        }
      );
      return { action: 'unliked' };
    }
    
    return { action: 'liked' };
  }

  // Bulk operations
  async bulkUpdatePosts(updates) {
    const bulkOps = updates.map(update => ({
      updateOne: {
        filter: { _id: new ObjectId(update.id) },
        update: { $set: update.data }
      }
    }));
    
    return await this.collection.bulkWrite(bulkOps);
  }
}

module.exports = PostService;
```

---

## PostgreSQL - Advanced SQL Database

PostgreSQL is a powerful, open-source object-relational database system.

### Connection and Pool Setup

```javascript
// config/database.js
const { Pool } = require('pg');

class PostgreSQLDatabase {
  constructor() {
    this.pool = new Pool({
      host: process.env.DB_HOST,
      port: process.env.DB_PORT,
      database: process.env.DB_NAME,
      user: process.env.DB_USER,
      password: process.env.DB_PASSWORD,
      max: 20,
      idleTimeoutMillis: 30000,
      connectionTimeoutMillis: 2000,
    });
    
    this.pool.on('error', (err) => {
      console.error('Unexpected error on idle client', err);
      process.exit(-1);
    });
  }

  async query(text, params) {
    const start = Date.now();
    const res = await this.pool.query(text, params);
    const duration = Date.now() - start;
    console.log('Executed query', { text, duration, rows: res.rowCount });
    return res;
  }

  async getClient() {
    return await this.pool.connect();
  }

  async transaction(callback) {
    const client = await this.getClient();
    try {
      await client.query('BEGIN');
      const result = await callback(client);
      await client.query('COMMIT');
      return result;
    } catch (error) {
      await client.query('ROLLBACK');
      throw error;
    } finally {
      client.release();
    }
  }
}

module.exports = new PostgreSQLDatabase();
```

### Advanced SQL Queries

```sql
-- Complex joins and aggregations
SELECT 
    u.id,
    u.name,
    u.email,
    COUNT(p.id) as post_count,
    AVG(p.views) as avg_views,
    COALESCE(SUM(
        CASE 
            WHEN p.created_at >= NOW() - INTERVAL '30 days' 
            THEN 1 
            ELSE 0 
        END
    ), 0) as recent_posts,
    ARRAY_AGG(DISTINCT c.name) as categories,
    STRING_AGG(DISTINCT t.name, ', ') as tags
FROM users u
LEFT JOIN posts p ON u.id = p.author_id AND p.status = 'published'
LEFT JOIN categories c ON p.category_id = c.id
LEFT JOIN post_tags pt ON p.id = pt.post_id
LEFT JOIN tags t ON pt.tag_id = t.id
WHERE u.is_active = true
GROUP BY u.id, u.name, u.email
HAVING COUNT(p.id) > 0
ORDER BY post_count DESC, u.name
LIMIT 20;

-- Window functions
SELECT 
    id,
    title,
    author_id,
    views,
    created_at,
    ROW_NUMBER() OVER (PARTITION BY author_id ORDER BY views DESC) as author_rank,
    RANK() OVER (ORDER BY views DESC) as global_rank,
    LAG(views) OVER (PARTITION BY author_id ORDER BY created_at) as prev_post_views,
    views - LAG(views) OVER (PARTITION BY author_id ORDER BY created_at) as view_growth
FROM posts
WHERE status = 'published'
ORDER BY author_id, author_rank;

-- Recursive CTE for hierarchical data
WITH RECURSIVE category_tree AS (
    -- Base case: root categories
    SELECT 
        id, 
        name, 
        parent_id, 
        0 as level,
        ARRAY[id] as path
    FROM categories 
    WHERE parent_id IS NULL
    
    UNION ALL
    
    -- Recursive case: child categories
    SELECT 
        c.id, 
        c.name, 
        c.parent_id, 
        ct.level + 1,
        ct.path || c.id
    FROM categories c
    INNER JOIN category_tree ct ON c.parent_id = ct.id
    WHERE ct.level < 10 -- Prevent infinite recursion
)
SELECT 
    id,
    REPEAT('  ', level) || name as indented_name,
    level,
    path
FROM category_tree
ORDER BY path;

-- Full-text search
SELECT 
    p.id,
    p.title,
    p.content,
    ts_rank(to_tsvector('english', p.title || ' ' || p.content), 
            plainto_tsquery('english', $1)) as relevance
FROM posts p
WHERE to_tsvector('english', p.title || ' ' || p.content) 
      @@ plainto_tsquery('english', $1)
ORDER BY relevance DESC
LIMIT 20;
```

```javascript
// services/postService.js
const db = require('../config/database');

class PostService {
  async createPost(postData) {
    return await db.transaction(async (client) => {
      // Insert post
      const postResult = await client.query(`
        INSERT INTO posts (title, content, author_id, category_id, status)
        VALUES ($1, $2, $3, $4, $5)
        RETURNING *
      `, [postData.title, postData.content, postData.authorId, postData.categoryId, postData.status]);
      
      const post = postResult.rows[0];
      
      // Insert tags
      if (postData.tags && postData.tags.length > 0) {
        for (const tagName of postData.tags) {
          // Upsert tag
          const tagResult = await client.query(`
            INSERT INTO tags (name) VALUES ($1)
            ON CONFLICT (name) DO UPDATE SET name = EXCLUDED.name
            RETURNING id
          `, [tagName]);
          
          const tagId = tagResult.rows[0].id;
          
          // Link post to tag
          await client.query(`
            INSERT INTO post_tags (post_id, tag_id) VALUES ($1, $2)
          `, [post.id, tagId]);
        }
      }
      
      return post;
    });
  }

  async getPostsWithStats(filters = {}) {
    let query = `
      WITH post_stats AS (
        SELECT 
          p.id,
          p.title,
          p.slug,
          p.excerpt,
          p.created_at,
          p.views,
          u.name as author_name,
          c.name as category_name,
          COUNT(DISTINCT l.id) as like_count,
          COUNT(DISTINCT cm.id) as comment_count
        FROM posts p
        INNER JOIN users u ON p.author_id = u.id
        INNER JOIN categories c ON p.category_id = c.id
        LEFT JOIN likes l ON p.id = l.post_id
        LEFT JOIN comments cm ON p.id = cm.post_id
        WHERE p.status = 'published'
    `;
    
    const params = [];
    let paramCount = 0;
    
    if (filters.category) {
      paramCount++;
      query += ` AND c.slug = $${paramCount}`;
      params.push(filters.category);
    }
    
    if (filters.author) {
      paramCount++;
      query += ` AND u.id = $${paramCount}`;
      params.push(filters.author);
    }
    
    if (filters.search) {
      paramCount++;
      query += ` AND (p.title ILIKE $${paramCount} OR p.content ILIKE $${paramCount})`;
      params.push(`%${filters.search}%`);
    }
    
    query += `
        GROUP BY p.id, p.title, p.slug, p.excerpt, p.created_at, p.views, u.name, c.name
      )
      SELECT *,
        RANK() OVER (ORDER BY like_count DESC) as popularity_rank
      FROM post_stats
      ORDER BY ${filters.sortBy || 'created_at'} ${filters.sortOrder || 'DESC'}
      LIMIT $${++paramCount} OFFSET $${++paramCount}
    `;
    
    params.push(filters.limit || 20, ((filters.page || 1) - 1) * (filters.limit || 20));
    
    const result = await db.query(query, params);
    return result.rows;
  }
}

module.exports = PostService;
```

---

## Prisma - Modern Database Toolkit

Prisma is a next-generation ORM that makes working with databases easy and type-safe.

### Setup and Schema

```bash
npm install prisma @prisma/client
npx prisma init
```

```prisma
// prisma/schema.prisma
generator client {
  provider = "prisma-client-js"
}

datasource db {
  provider = "postgresql"
  url      = env("DATABASE_URL")
}

model User {
  id        String   @id @default(cuid())
  email     String   @unique
  name      String
  password  String
  role      Role     @default(USER)
  avatar    String?
  bio       String?
  isActive  Boolean  @default(true)
  createdAt DateTime @default(now())
  updatedAt DateTime @updatedAt

  posts     Post[]
  comments  Comment[]
  likes     Like[]
  sessions  Session[]

  @@map("users")
}

model Post {
  id          String      @id @default(cuid())
  title       String
  slug        String      @unique
  content     String
  excerpt     String?
  status      PostStatus  @default(DRAFT)
  publishedAt DateTime?
  views       Int         @default(0)
  createdAt   DateTime    @default(now())
  updatedAt   DateTime    @updatedAt

  author   User   @relation(fields: [authorId], references: [id], onDelete: Cascade)
  authorId String

  category   Category @relation(fields: [categoryId], references: [id])
  categoryId String

  tags     PostTag[]
  comments Comment[]
  likes    Like[]

  @@map("posts")
}

model Category {
  id          String  @id @default(cuid())
  name        String  @unique
  slug        String  @unique
  description String?
  color       String?

  posts Post[]

  @@map("categories")
}

model Tag {
  id   String @id @default(cuid())
  name String @unique
  slug String @unique

  posts PostTag[]

  @@map("tags")
}

model PostTag {
  post   Post   @relation(fields: [postId], references: [id], onDelete: Cascade)
  postId String
  tag    Tag    @relation(fields: [tagId], references: [id], onDelete: Cascade)
  tagId  String

  @@id([postId, tagId])
  @@map("post_tags")
}

model Comment {
  id        String   @id @default(cuid())
  content   String
  createdAt DateTime @default(now())
  updatedAt DateTime @updatedAt

  author   User   @relation(fields: [authorId], references: [id], onDelete: Cascade)
  authorId String

  post   Post   @relation(fields: [postId], references: [id], onDelete: Cascade)
  postId String

  @@map("comments")
}

model Like {
  id        String   @id @default(cuid())
  createdAt DateTime @default(now())

  user   User   @relation(fields: [userId], references: [id], onDelete: Cascade)
  userId String

  post   Post   @relation(fields: [postId], references: [id], onDelete: Cascade)
  postId String

  @@unique([userId, postId])
  @@map("likes")
}

enum Role {
  USER
  ADMIN
  MODERATOR
}

enum PostStatus {
  DRAFT
  PUBLISHED
  ARCHIVED
}
```

### Advanced Prisma Queries

```typescript
// lib/prisma.ts
import { PrismaClient } from '@prisma/client';

const globalForPrisma = globalThis as unknown as {
  prisma: PrismaClient | undefined;
};

export const prisma =
  globalForPrisma.prisma ??
  new PrismaClient({
    log: ['query'],
  });

if (process.env.NODE_ENV !== 'production') globalForPrisma.prisma = prisma;

// services/postService.ts
export class PostService {
  async getPosts(options: {
    page?: number;
    limit?: number;
    categoryId?: string;
    authorId?: string;
    search?: string;
    status?: PostStatus;
  }) {
    const { page = 1, limit = 10, categoryId, authorId, search, status = 'PUBLISHED' } = options;
    const skip = (page - 1) * limit;

    const where: any = { status };

    if (categoryId) where.categoryId = categoryId;
    if (authorId) where.authorId = authorId;
    if (search) {
      where.OR = [
        { title: { contains: search, mode: 'insensitive' } },
        { content: { contains: search, mode: 'insensitive' } },
      ];
    }

    const [posts, total] = await Promise.all([
      prisma.post.findMany({
        where,
        skip,
        take: limit,
        include: {
          author: {
            select: {
              id: true,
              name: true,
              avatar: true,
            },
          },
          category: {
            select: {
              id: true,
              name: true,
              slug: true,
            },
          },
          tags: {
            include: {
              tag: {
                select: {
                  id: true,
                  name: true,
                  slug: true,
                },
              },
            },
          },
          _count: {
            select: {
              comments: true,
              likes: true,
            },
          },
        },
        orderBy: { createdAt: 'desc' },
      }),
      prisma.post.count({ where }),
    ]);

    return {
      posts,
      pagination: {
        page,
        limit,
        total,
        pages: Math.ceil(total / limit),
      },
    };
  }

  async getPostWithDetails(slug: string, userId?: string) {
    const post = await prisma.post.findUnique({
      where: { slug },
      include: {
        author: {
          select: {
            id: true,
            name: true,
            avatar: true,
            bio: true,
          },
        },
        category: true,
        tags: {
          include: {
            tag: true,
          },
        },
        comments: {
          include: {
            author: {
              select: {
                id: true,
                name: true,
                avatar: true,
              },
            },
          },
          orderBy: { createdAt: 'desc' },
        },
        likes: userId
          ? {
              where: { userId },
              select: { id: true },
            }
          : false,
        _count: {
          select: {
            comments: true,
            likes: true,
          },
        },
      },
    });

    if (post) {
      // Increment view count
      await prisma.post.update({
        where: { id: post.id },
        data: { views: { increment: 1 } },
      });
    }

    return post;
  }

  async createPost(data: {
    title: string;
    content: string;
    categoryId: string;
    authorId: string;
    tags?: string[];
    status?: PostStatus;
  }) {
    const { tags, ...postData } = data;

    return await prisma.$transaction(async (tx) => {
      // Create post
      const post = await tx.post.create({
        data: {
          ...postData,
          slug: this.generateSlug(data.title),
        },
      });

      // Handle tags
      if (tags && tags.length > 0) {
        const tagConnections = [];

        for (const tagName of tags) {
          // Upsert tag
          const tag = await tx.tag.upsert({
            where: { name: tagName },
            update: {},
            create: {
              name: tagName,
              slug: this.generateSlug(tagName),
            },
          });

          tagConnections.push({
            postId: post.id,
            tagId: tag.id,
          });
        }

        // Create post-tag relationships
        await tx.postTag.createMany({
          data: tagConnections,
        });
      }

      return post;
    });
  }

  async getPostAnalytics(postId: string) {
    const analytics = await prisma.post.findUnique({
      where: { id: postId },
      select: {
        views: true,
        createdAt: true,
        _count: {
          select: {
            likes: true,
            comments: true,
          },
        },
        likes: {
          select: {
            createdAt: true,
          },
          orderBy: { createdAt: 'asc' },
        },
        comments: {
          select: {
            createdAt: true,
          },
          orderBy: { createdAt: 'asc' },
        },
      },
    });

    if (!analytics) return null;

    // Group engagement by day
    const engagementByDay = this.groupEngagementByDay(
      analytics.likes.map((l) => l.createdAt),
      analytics.comments.map((c) => c.createdAt)
    );

    return {
      totalViews: analytics.views,
      totalLikes: analytics._count.likes,
      totalComments: analytics._count.comments,
      engagementByDay,
    };
  }

  private generateSlug(text: string): string {
    return text
      .toLowerCase()
      .replace(/[^a-z0-9]+/g, '-')
      .replace(/(^-|-$)+/g, '');
  }

  private groupEngagementByDay(likes: Date[], comments: Date[]) {
    const engagement: Record<string, { likes: number; comments: number }> = {};
    
    [...likes, ...comments].forEach((date) => {
      const day = date.toISOString().split('T')[0];
      if (!engagement[day]) {
        engagement[day] = { likes: 0, comments: 0 };
      }
    });
    
    likes.forEach((date) => {
      const day = date.toISOString().split('T')[0];
      engagement[day].likes++;
    });
    
    comments.forEach((date) => {
      const day = date.toISOString().split('T')[0];
      engagement[day].comments++;
    });
    
    return Object.entries(engagement)
      .sort(([a], [b]) => a.localeCompare(b))
      .map(([date, data]) => ({ date, ...data }));
  }
}

export const postService = new PostService();
```

---

## Best Practices Summary

### 1. **State Management (Zustand)**
- Keep stores small and focused
- Use middleware for persistence and devtools
- Implement computed values efficiently
- Use immer for complex state updates

### 2. **Backend Development (Spring Boot)**
- Follow layered architecture (Controller → Service → Repository)
- Use proper validation and error handling
- Implement security best practices
- Write comprehensive tests

### 3. **Full-Stack Framework (Next.js)**
- Use App Router for better performance
- Implement proper SEO and metadata
- Leverage server components for data fetching
- Optimize for Core Web Vitals

### 4. **Database Management (MongoDB, PostgreSQL, Prisma)**
- Design efficient schemas and indexes
- Use transactions for data consistency
- Implement proper query optimization
- Handle connections and pools properly

### 5. **General Best Practices**
- Follow consistent coding standards
- Implement proper error handling
- Use TypeScript for type safety
- Write comprehensive documentation
- Monitor performance and security

This comprehensive guide covers the essential modern web development technologies needed for building production-ready applications. Each technology is presented with practical examples and best practices suitable for professional development environments.