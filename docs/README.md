# Deo User Guide

Deo is a **task management chatbot** that helps you track todos, deadlines, and events via a command-line interface.

---

## Quick Start

1. Ensure you have Java 11 or above installed.
2. Download the latest `deo.jar` from the releases page.
3. Run the jar: `java -jar deo.jar`
4. Type commands and press Enter.

---

## Features

### Add a Todo

Adds a task with no date attached.

**Format:** `todo DESCRIPTION`

**Example:**

```
todo read book
```

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

---

### Add a Deadline

Adds a task with a due date.

**Format:** `deadline DESCRIPTION /by YYYY-MM-DD`

**Example:**

```
deadline return book /by 2019-12-01
```

```
Got it. I've added this task:
  [D][ ] return book (by: Dec 01 2019)
Now you have 2 tasks in the list.
```

---

### Add an Event

Adds a task with a start and end time.

**Format:** `event DESCRIPTION /from FROM /to TO`

**Example:**

```
event project meeting /from Mon 2pm /to Mon 4pm
```

```
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: Mon 4pm)
Now you have 3 tasks in the list.
```

---

### List All Tasks

Displays all tasks currently in the list.

**Format:** `list`

**Example:**

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 01 2019)
3.[E][ ] project meeting (from: Mon 2pm to: Mon 4pm)
```

---

### Mark a Task as Done

Marks the specified task as completed.

**Format:** `mark INDEX`

**Example:**

```
mark 1
```

```
Nice! I've marked this task as done:
  [T][X] read book
```

---

### Unmark a Task

Marks the specified task as not done.

**Format:** `unmark INDEX`

**Example:**

```
unmark 1
```

```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

---

### Delete a Task

Removes the specified task from the list.

**Format:** `delete INDEX`

**Example:**

```
delete 2
```

```
Noted. I've removed this task:
  [D][ ] return book (by: Dec 01 2019)
Now you have 2 tasks in the list.
```

---

### Find Tasks by Keyword

Searches for tasks whose descriptions contain the given keyword.

**Format:** `find KEYWORD`

**Example:**

```
find book
```

```
Here are the matching tasks in your list:
1.[T][ ] read book
```

---

### Exit

Exits the chatbot.

**Format:** `bye`

---

## Task Types Summary

| Symbol | Type     | Date/Time Required |
| ------ | -------- | ------------------ |
| `[T]`  | Todo     | None               |
| `[D]`  | Deadline | `/by YYYY-MM-DD`   |
| `[E]`  | Event    | `/from` and `/to`  |

---

## Data Storage

Tasks are saved automatically to `data/deo.txt` after every command. The file is created automatically on first run. Do not edit the file manually.
