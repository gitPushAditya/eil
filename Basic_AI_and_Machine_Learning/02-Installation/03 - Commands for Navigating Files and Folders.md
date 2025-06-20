# Basic Command Prompt Commands for Navigating Files and Folders (Windows)

This guide covers essential commands to move around and manage files and directories (folders) using the Windows Command Prompt or the Anaconda Prompt.

---

## 1. Open Command Prompt

- Press `Windows + R`, type `cmd`, and press `Enter`.
- Or, search for **"Anaconda Prompt"** if you are using Anaconda/Miniconda.

---

## 2. Show Current Directory

```sh
cd
```
- Prints the current working directory (folder).

---

## 3. List Files and Folders

```sh
dir
```
- Lists all files and folders in the current directory.

---

## 4. Change Directory

```sh
cd folder_name
```
- Moves into the specified folder.

**Example:**
```sh
cd Documents
```

---

## 5. Move Up One Directory

```sh
cd ..
```
- Moves up to the parent directory.

---

## 6. Go to a Specific Path

```sh
cd path\to\folder
```

**Example:**
```sh
cd C:\Users\YourName\Desktop
```

---

## 7. Change Drive

To switch from one drive to another (e.g., from `C:` to `D:`):

```sh
D:
```

- Just type the drive letter followed by a colon and press Enter.
- You cannot use `cd D:\` from C: to change the drive; you must type `D:` first.

---

## 8. Create a New Folder

```sh
mkdir folder_name
```
**Example:**
```sh
mkdir my_new_project
```

---

## 9. Remove an Empty Folder

```sh
rmdir folder_name
```

---

## 10. Clear the Screen

```sh
cls
```

---

## 11. Helpful Tips

- **Tab Completion:** Press `Tab` while typing a folder or file name to auto-complete.
- **Drag and Drop:** You can drag a folder into the Command Prompt window to paste its path.

---

## Example Workflow

```sh
cd Desktop
mkdir my_project
cd my_project
```

---