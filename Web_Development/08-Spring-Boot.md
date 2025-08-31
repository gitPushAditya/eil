# Spring Boot

## Introduction to Spring Boot

Spring Boot is a Java-based framework that simplifies the development of production-ready Spring applications. It provides auto-configuration, embedded servers, and a convention-over-configuration approach to reduce boilerplate code.

---

## Getting Started

### Project Setup

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
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
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

### Main Application Class

```java
package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
```

---

## REST Controllers

### Basic Controller

```java
package com.example.myapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private List<User> users = new ArrayList<>();
    
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return user;
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
```

### Request/Response DTOs

```java
package com.example.myapp.dto;

import jakarta.validation.constraints.*;

public class CreateUserRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be less than 120")
    private Integer age;
    
    // Constructors, getters, and setters
    public CreateUserRequest() {}
    
    public CreateUserRequest(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    // Getters and setters...
}

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDateTime createdAt;
    
    // Constructors, getters, and setters...
}
```

---

## Data Access Layer

### JPA Entity

```java
package com.example.myapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false)
    private Integer age;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public User() {}
    
    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    // Getters and setters...
}
```

### Repository Interface

```java
package com.example.myapp.repository;

import com.example.myapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Derived query methods
    Optional<User> findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByAgeGreaterThan(Integer age);
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);
    
    // Custom JPQL queries
    @Query("SELECT u FROM User u WHERE u.age >= :minAge AND u.name LIKE %:namePattern%")
    List<User> findUsersWithAgeAndNamePattern(@Param("minAge") Integer minAge, 
                                             @Param("namePattern") String namePattern);
    
    // Native SQL query
    @Query(value = "SELECT * FROM users WHERE created_at >= ?1", nativeQuery = true)
    List<User> findUsersCreatedAfter(LocalDateTime date);
    
    // Check existence
    boolean existsByEmail(String email);
    
    // Count query
    long countByAgeGreaterThan(Integer age);
}
```

---

## Service Layer

```java
package com.example.myapp.service;

import com.example.myapp.dto.CreateUserRequest;
import com.example.myapp.dto.UserResponse;
import com.example.myapp.entity.User;
import com.example.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return convertToResponse(user);
    }
    
    public UserResponse createUser(CreateUserRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        
        User user = new User(request.getName(), request.getEmail(), request.getAge());
        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }
    
    public UserResponse updateUser(Long id, CreateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        
        // Check if email is taken by another user
        if (!user.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        
        User updatedUser = userRepository.save(user);
        return convertToResponse(updatedUser);
    }
    
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    
    private UserResponse convertToResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getAge(),
            user.getCreatedAt()
        );
    }
}
```

---

## Exception Handling

### Custom Exceptions

```java
package com.example.myapp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
```

### Global Exception Handler

```java
package com.example.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred",
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    
    // Constructor, getters, and setters...
}
```

---

## Configuration

### Application Properties

```properties
# application.properties

# Server configuration
server.port=8080
server.servlet.context-path=/api

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/myappdb
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Logging configuration
logging.level.com.example.myapp=DEBUG
logging.level.org.springframework.web=INFO

# Validation messages
spring.messages.basename=validation
```

### Configuration Class

```java
package com.example.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {
    
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
```

---

## Testing

### Repository Test

```java
package com.example.myapp.repository;

import com.example.myapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void findByEmail_ExistingEmail_ReturnsUser() {
        // Given
        User user = new User("John Doe", "john@example.com", 25);
        entityManager.persistAndFlush(user);
        
        // When
        Optional<User> found = userRepository.findByEmail("john@example.com");
        
        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John Doe");
    }
    
    @Test
    void existsByEmail_ExistingEmail_ReturnsTrue() {
        // Given
        User user = new User("John Doe", "john@example.com", 25);
        entityManager.persistAndFlush(user);
        
        // When
        boolean exists = userRepository.existsByEmail("john@example.com");
        
        // Then
        assertThat(exists).isTrue();
    }
}
```

### Service Test

```java
package com.example.myapp.service;

import com.example.myapp.dto.CreateUserRequest;
import com.example.myapp.dto.UserResponse;
import com.example.myapp.entity.User;
import com.example.myapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void createUser_ValidRequest_ReturnsUserResponse() {
        // Given
        CreateUserRequest request = new CreateUserRequest("John Doe", "john@example.com", 25);
        User savedUser = new User("John Doe", "john@example.com", 25);
        savedUser.setId(1L);
        
        when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        
        // When
        UserResponse response = userService.createUser(request);
        
        // Then
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("John Doe");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
    }
}
```

---

## Best Practices

1. **Use proper layered architecture** - Controller, Service, Repository
2. **Implement proper exception handling** - Global exception handlers
3. **Validate input data** - Use Bean Validation annotations
4. **Use DTOs** - Separate internal entities from API contracts
5. **Write comprehensive tests** - Unit tests and integration tests
6. **Configure security** - Use Spring Security for authentication/authorization
7. **Use profiles** - Different configurations for dev, test, prod
8. **Implement logging** - Use proper logging levels and patterns
9. **Handle transactions properly** - Use @Transactional annotations
10. **Follow REST conventions** - Proper HTTP methods and status codes
11. **Document your API** - Use Swagger/OpenAPI documentation
12. **Implement caching** - For frequently accessed data

---