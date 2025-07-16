**PRACTICAL EXAMINATION**

**Subject:** Java OOP – MVC Architecture\
**Duration:** 85 minutes\
**Project:** Event Ticket Management System\
**File required:** `tickets.txt`

---

## Objective

You are required to develop a Java console-based application to manage a list of event tickets. The application must follow the Model-View-Controller (MVC) architecture, apply Object-Oriented Programming principles, and support persistent storage via `tickets.txt`.

---

## 1. Create the `model` package

Include the following classes:

### a. `Ticket` class

Represents a ticket for an event with the following attributes:

- `ticketID` (String): Must be unique, not blank
- `eventName` (String): Cannot be blank
- `eventDate` (LocalDate): Must be a valid date (yyyy-MM-dd)
- `price` (double): Must be > 0
- `sold` (boolean): Sold status: true or false

Required Methods:

- Constructor(s)
- Getters and setters
- `toString()` method for formatted display

### b. `TicketList` class

Manages a list of `Ticket` objects.

Required functionalities:

- Load tickets from `tickets.txt` (skip invalid lines)
- Add a new ticket (ensure unique `ticketID`)
- Update a ticket’s price or status by `ticketID`
- Delete a ticket by `ticketID`
- Search tickets by event name (partial match)
- Filter tickets by date range or sold status
- Sort tickets by:
  - `eventDate` (ascending)
  - `price` (descending)
- Display all tickets
- Save all changes to `tickets.txt` after every add, update, or delete

---

## 2. Create the `view` package

### `Menu` class

Displays an interactive menu using a reusable abstract `Menu<T>` structure.\
Captures user input using utility validation methods.

---

## 3. Create the `controller` package

### `TicketManagement` class

This is the main controller that drives the program. It should present this menu:

```
========= EVENT TICKET MANAGEMENT =========
1. Display all tickets
2. Add new ticket
3. Update ticket by ID
4. Delete ticket by ID
5. Search tickets by event name
6. Filter by sold status or date range
7. Sort by event date (ascending)
8. Sort by price (descending)
9. Save to file
10. Exit
```

---

## 4. `tickets.txt` – Sample Content

```
T001, Music Festival 2025, 2025-10-01, 100.0, false
T002, Java Summit, 2025-08-15, 150.0, true
T003, Indie Film Night, 2025-07-20, 50.0, false
T004, Tech Expo, 2025-11-01, 200.0, true
```

---

## Technical Requirements

- Validate input carefully:
  - Price > 0
  - Date must follow `yyyy-MM-dd` format
  - Unique and non-empty `ticketID`
- Use Java features such as:
  - ArrayList, LocalDate, Comparator, BufferedReader, PrintWriter
  - Try-catch to handle file and input errors
- Ensure clean, modular code using:
  - Model → for business logic
  - View → for menu/input
  - Controller → to run the application
- Load `tickets.txt` on startup, and save automatically after each add, update, or delete

---

## Assessment Criteria

| Criteria                            | Points   |
| ----------------------------------- | -------- |
| Model Classes (Ticket + TicketList) | 3.0      |
| Controller Logic                    | 3.0      |
| View (Menu class, input handling)   | 1.0      |
| File I/O handling                   | 1.0      |
| Sorting, searching, filtering       | 1.0      |
| Input validation                    | 1.0      |
| **Total**                           | **10.0** |

---

## Notes

- Do not change field names or file name.
- Ensure program runs from `main()` in `TicketManagement`.
- Avoid external libraries.
- Submit a complete NetBeans project folder. Submitting `.doc`, `.txt`, or `.pdf` files only will receive 0 points.

---

**Output Load file**
