# Setting up an IDE for C++ Developement

An Integrated Development Environment (IDE) combines tools  like code editors, compilers, debuggers, and more to streamline your devleopment workflow. This guide covers setup for three popular C++ IDEs.

## Visual Studio Code

### Installation 

1. Download VSCode from [code.visualstudio.com](https://code.visualstudio.com/)
2. Run the installer and follow the prompts
3. Launch VSCode after installation

### C++ Extention Setup

1. OPen VSCode and navigate to Extentions(Ctrl + Shift + X)
2. Search for "C++" and install the following extentions:
    - "C/C++" by Microsoft
    - "C/C++ IntelliSense" by Microsoft
    - "C++ Intellisense" (optional)
    - "Code Runner" (optional but recommended)

### Configure VSCode for C++ 

1. **Create a C++ project folder**
   - Create a new folder for your project
   - Open it in VSCode (File > Open Folder)

2. **Create configuration files**
   - Press Ctrl+Shift+P (⌘+Shift+P on macOS) to open the command palette
   - Type "C/C++: Edit Configurations" and select it
   - This creates `c_cpp_properties.json` with compiler path and IntelliSense settings

3. **Set up tasks.json for building**
   - Press Ctrl+Shift+P and type "Tasks: Configure Default Build Task"
   - Select "Create tasks.json file from template"
   - Choose "C/C++ gcc build active file" (or appropriate compiler)

4. **Set up launch.json for debugging**
   - Go to the Run/Debug view (Ctrl+Shift+D or ⌘+Shift+D)
   - Click "create a launch.json file" and select C++ (GDB/LLDB)


### Example Configuration Files

#### c_cpp_properties.json

For Linux
```json
{
    "configurations": [
        {
            "name": "Linux",
            "includePath": [
                "${workspaceFolder}/**"
            ],
            "defines": [],
            "compilerPath": "/usr/bin/g++",
            "cStandard": "c17",
            "cppStandard": "c++17",
            "intelliSenseMode": "linux-gcc-x64"
        }
    ],
    "version": 4
}
```

or 

For Windows
```json
{
    "configurations": [
        {
            "name": "Win32",
            "includePath": [
                "${workspaceFolder}/**"
            ],
            "defines": [],
            "compilerPath": "C:/MinGW/bin/g++.exe",
            "cStandard": "c17",
            "cppStandard": "c++17",
            "intelliSenseMode": "windows-gcc-x64"
        }
    ],
    "version": 4
}
```

#### task.json

```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "build",
            "type": "shell",
            "command": "g++",
            "args": [
                "-g",
                "-std=c++17",
                "${file}",
                "-o",
                "${fileDirname}/${fileBasenameNoExtension}"
            ],
            "group": {
                "kind": "build",
                "isDefault": true
            }
        }
    ]
}
```
#### launch.json

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "g++ build and debug active file",
            "type": "cppdbg",
            "request": "launch",
            "program": "${fileDirname}/${fileBasenameNoExtension}",
            "args": [],
            "stopAtEntry": false,
            "cwd": "${workspaceFolder}",
            "environment": [],
            "externalConsole": false,
            "MIMode": "gdb",
            "setupCommands": [
                {
                    "description": "Enable pretty-printing for gdb",
                    "text": "-enable-pretty-printing",
                    "ignoreFailures": true
                }
            ],
            "preLaunchTask": "build"
        }
    ]
}
```

### Key Features in VSCode

- Code Navigation: F12(Go to Definition), Alt+F12(Peek Definition)
- IntelliSense: Auto-completion, parameter info, quick info
- Debugging: Breakpoints, watch variable, call stack
- Integrated Terminal: Ctrl + `
- Source Control: Git Integration
- Code Runner: Right-click and "Run Code" for quick execution

## Visual Studio(Windows)

### Installation

1. Download Visual Studio from [here](https://visualstudio.microsoft.com/downloads/)
2. Run the installer
3. Select "Desktop development with C++"
4. Complete the installation

### Creating a C++ Project

1. Launch Visual Studio
2. CLick "Create a new project"
3. Select a C++ project template:
    - "Console App" for simple programs
    - "Empty Project" for a balnk  slate
    - "Windows Desktop Application" for GUI apps
4. Name your project and click "Create"

### Key Features

- Solution Explorer: Manage project files
- Class View: Browse classes and members
- Powerful Debugger: Set breakpoints, watch variables
- Memory and Performance Analysis: Tools like memory profiler
- IntelliSense: Code completion and suggestions
- Integrated Testing: Unit testing framework

### Project Structure

```code
MyProject/
├── MyProject/
│   ├── MyProject.cpp       # Main source file
│   ├── MyProject.h         # Header file
│   └── MyProject.vcxproj   # Project file
└── MyProject.sln           # Solution file
```

### Building and Running

- Build: F7 or Ctrl + Shift + B
- Run: F5(with debugging) or Ctrl+F5(without debugging)
- Debug: Set breakpoints by clicking in the margin, then press F5

## CLion

### Installation

1. Download CLion from [jetbrains.com/clion](https://www.jetbrains.com/clion/download/)
2. Run the installer
3. If you're a student, apply for a free education license through [here](https://www.jetbrains.com/community/education/)

### Setting up CLion for C++ 

1. Launch CLion
2. Create a new project(File > New Project)
3. Select "C++ Executable"
4. Choose project location and name
5. Select toolchain:
    - Windows: MinGW, Cygwin, WSL, or MSVC
    - macOS: Xcode or Homebrew GCC
    - Linux: GCC or Clang

### CMake Configuration

CLion uses CMake as its build system. A basic `CMakeLists.txt` file:

```cmake
cmake_minimum_required(VERSION 3.17)
project(my_project)

set(CMAKE_CXX_STANDARD 17)

add_executable(my_project main.cpp)
```

### Key Features

- Smart Editor: Advanced code completion and refactoring
- CMake Integration: Built-in support for CMake
- Powerful Debugger: GDB/LLDB integration
- Integrated Terminal: Alt+F12
- Code Analysis: On-the-fly code inspections
- Unit Testing: Google Test, Boost.Test, and Catch integration
- Profiling Tools: Memory and CPU profiling

### Building and Running

- Build: Ctrl + F9
- Run: Shift + F10
- Debug: Shift + F9

## Troubleshooting Tips

### Visual Studio Code

- IntelliSense Not Working: Check compiler path in c_cpp_properties.json
- Build Failed: Verify tasks.json configuration matches your compiler
- Debugger Not Working: Ensure launch.json is correctly configured

### Visual Studio

- Missing Components: Use the Visual Studio Installer to add missing components
- Slow Performance: Disable unused extensions in Tools > Extensions
- Build Errors: Check project properties for correct compiler settings

### CLion

- CMake Errors: Ensure CMake is properly installed and CMakeLists.txt is correct
- Toolchain Issues: Verify compiler path in Settings > Build, Execution, Deployment > Toolchains
- Performance: Adjust memory allocation in Help > Change Memory Settings