# Install a C++ compiler (GCC, Clang, or MSVC)

## What is C++ Compiler?

A C++ Compiler translates your human-readable C++ code into machine codes that computers can execute. The three most popular C++ compilers are:

1. **GCC (GNU Compiler Collection)**: An open source compiler that works on macOS, Linux and Windows(via MinGW or WSL)
2. **Clang**: A compiler focused on fast compile times and helpful error messages, available on all major platforms
3. **MSVC (Microsoft Visual C++)**: Microsoft's C++ Compiler, primarily for windows.

## Installing GCC 

### On Linux

#### Ubuntu/Debian: 

```bash
sudo apt update
sudo apt install build-essential
```

#### Fedora/RHEL/CentOS

```bash
sudo dnf install gcc-c++ make
```

#### Arch Linux

```bash
sudo pacman -S base-devel
```

### On macOS

1. Install XcodeCommand Line Tools" 

```bash
xcode-select --install
```

2. Alternatively, intall via Homebrew

```bash
brew install gcc
```

### On Windows

#### Option-1 MinGW-w64 (recommended for beginner)

1. Download the MinGW-w64 installer from [winlibs.com]()
2. Run the installer and follow the prompts
3. Choose the latest GCC version, architecture (x86_64), and threads (posix)
4. Install to a path without spaces, like C:\mingw64
5. Add the bin directory to your system PATH:
    - Right-click on "This PC" or "My Computer"
    - Select "Properties" → "Advanced system settings" → "Environment Variables"
    - Edit the "Path" variable and add C:\mingw64\bin

#### Option 2: Windows Subsystem for Linnux(WSL)

1. Open PowerShell as administrator and run
```powershell
wsl -install
```
2. Restart your computer
3. Open Ubuntu from start menu and follow the Linux installation above

## Installing Clang

### On Linux

#### Ubuntu/ Debian 

```bash
sudo apt update
sudo apt install clang
```


#### Fedora

```bash
sudo dnf install clang
```

#### Arch Linux

```bash
sudo pacman -5 clang
```

### On macOS

1. Install via Homebrew

```bash
brew install llvm
```

2. Add to PATH(if using Homebrew)
```bash
echo 'export PATH="/usr/local/opt/llvm/bin:$PATH"' >> ~/.zshrc
# or for bash:
echo 'export PATH="/usr/local/opt/llvm/bin:$PATH"' >> ~/.bash_profile
```

### On Windows

1. Download the LLVM installer from [https://releases.llvm.org/download.html]()
2. Run installer and follow the prompts
3. Check "Add LLVM to the system PATH" during installation

## Installing MSVC

1. Download the Visual Studio Commmunity installer from [https://visualstudio.microsoft.com/downloads/]()
2. Run the installer
3. Select "Desktop development with C++"
4. Complete the installation(this might take a while)
5. The compiler(cl.exe) will be available through: 
    - Visual Studio Developer Command Prompt
    - Visual Stusio IDE

# Verifying Your Installation

## GCC

Open a terminal/command prompt and run:

```bash
g++ --version
```

You should see output like this:

```code
g++ (GCC) 11.2.0
Copyright (C) 2021 Free Software Foundation, Inc.
...
```

## Clang

```bash
clang++ --version
```

## MSVC

In a developer command prompt:

```cmd
cl
```
You should see the compiler version information.

# Your First C++ Program

1. Create a file named hello.cpp with the following content:

```c++
#include <iostream>

int main(){
    std::cout << "Hello, World" << std::endl;
    return 0;
}
```
2. Compile and run:

With GCC:

```bash
g++ hello.cpp -o hello
# On Linux/macOs
./hello
# On Windows
hello
```

With Clang:

```bash
clang++ hello.cpp -o helloclang++ hello.cpp -o hello
./hello  # On Linux/macOS
hello    # On Windows
```

With MSVC:

```cmd
cl /EHsc hello.cpp
hello
```

# Troubleshooting Common Issues

## GCC/Clang: "Commmand not found"
- Ensure the compiler is properly installed
- Check if the bin directory is in your PATH

## MSVC: "'cl' is not recognized"
- Use the "Developer Command Prompt for VS" from the Start menu
- Or run vcvarsall.bat to set up the environment

## Linker Errors
- Make sure all required librearies are installed
- For non-standard libraries, add approprialte flags(e.g., `-lm` for math)

## Permission Denied(Linux/macOS)
- Make your executable file runnable `chmod +x ./hello` 