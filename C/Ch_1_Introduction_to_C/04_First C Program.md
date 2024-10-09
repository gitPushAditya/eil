# **Writing Your First C Program: "Hello, World!"**

The "Hello, World!" program is often the first program developers write when learning a new programming language. It serves as a simple introduction to the structure and syntax of C.

Here’s how to write, compile, and run the "Hello, World!" program in C.

---

## **1. Writing the "Hello, World!" Program**

The code for the program is very simple:

```c
#include <stdio.h>  // Include the standard input/output library

int main() {        // Main function where the program starts execution
    printf("Hello, World!\n");  // Print "Hello, World!" to the screen
    return 0;       // Return 0 to indicate that the program ended successfully
}
```

---

## **Code Breakdown**:

1. **`#include <stdio.h>`**:

   - This is a **preprocessor directive** that includes the **standard input/output library** (`stdio.h`), which contains functions like `printf()` and `scanf()`. In this program, we use the `printf()` function to output text to the console.

2. **`int main()`**:

   - Every C program must have a `main()` function. This is the entry point of the program where execution starts. The `int` keyword indicates that the function returns an integer (0 indicates successful execution).

3. **`printf("Hello, World!\n");`**:

   - `printf()` is a function used to print output to the console. In this case, it prints the text **Hello, World!**. The `\n` is an **escape sequence** for a newline, which moves the cursor to the next line after printing.

4. **`return 0;`**:
   - The `main()` function returns an integer value. Returning 0 indicates that the program has successfully completed its execution.

---

## **2. Compiling and Running the Program**

Once you’ve written the code, you need to compile it using a C compiler (such as GCC or Turbo C) and then run the executable file.

### **For GCC (Linux, macOS, or Windows with MinGW)**:

1. Open a text editor (such as VS Code, Sublime Text, or Notepad++) and write the program.
2. Save the file with the `.c` extension (e.g., `hello.c`).

### **Compiling the Program**:

- Open a terminal (or command prompt in Windows).
- Navigate to the directory where you saved the `hello.c` file.
- Compile the code using the following command:

```bash
gcc hello.c -o hello
```

Explanation:

- `gcc`: Calls the GCC compiler.
- `hello.c`: The source code file.
- `-o hello`: Tells the compiler to output the compiled program as an executable file named `hello`.

### **Running the Program**:

- After compilation, you can run the program using this command:

```bash
./hello
```

You should see the following output:

```
Hello, World!
```

---

### **For Turbo C**:

1. Open the **Turbo C** IDE.
2. Go to **File > New** and write the "Hello, World!" program.
3. Save the file with the `.c` extension (e.g., `hello.c`).

### **Compiling the Program**:

- Go to **Compile > Compile** or press `Alt + F9` to compile the program.

### **Running the Program**:

- Go to **Run > Run** or press `Ctrl + F9` to execute the program.

You will see the output:

```
Hello, World!
```

---

## **3. Troubleshooting Common Issues**

- **Compiler Not Found**: If the command `gcc` is not recognized, ensure that the C compiler is installed correctly and that the environment variables are set up. For MinGW, ensure that the `bin` folder is added to the `PATH`.
- **Incorrect File Extension**: Ensure that the file is saved with the `.c` extension (not `.txt` or any other extension).
- **Case Sensitivity**: Remember that C is case-sensitive, so `main()` must be lowercase, and `printf()` must be written exactly like this.

---
