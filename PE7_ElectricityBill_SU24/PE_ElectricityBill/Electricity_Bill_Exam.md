
## PRACTICAL EXAMINATION

**Subject:** Java OOP – MVC Architecture  
**Duration:** 85 minutes  
**Project:** Electricity Bill Management System  
**Required File:** `Electricitybill.txt`

---

### 1. Create the `model` package

#### a. `Bill` class
Represents an electricity bill with attributes:
- `id` (int): Unique identifier.
- `customerName` (String): Name of customer.
- `amount` (double): Amount due (> 0).
- `dueDate` (Date): Date in format `dd/MM/yyyy`.
- `paid` (boolean): True if the bill has been paid, otherwise False.

Methods:
- Constructor(s), Getters/Setters.
- `toString()` for formatted output (date must follow `dd/MM/yyyy` format).

>  No I/O code allowed in this class.

#### b. `ListBill` class
Handles the list of bills. Must include:
- Load bill data from `Electricitybill.txt`. (Skip invalid lines)
- Add a new bill (ensure unique ID).
- Delete a bill (by ID, customer name, or due date).
- Retrieve list of **unpaid** bills (`paid == false`).
- Retrieve bill with **largest amount**.
- **(New)** Retrieve list of **overdue** bills (dueDate before today and unpaid).
- **Save the list to file** after each add/delete operation.

>  No I/O code allowed in this class.

---

### 2. Create the `view` package

#### a. `Menu` class
An abstract class to handle menu display and selection.

#### b. `BillView` class
Handles user interaction: input, output prompts, formatting. 

---

### 3. Create the `controller` package

#### a. `BillManagement` class
Controls program flow. Displays the following main menu:

```
========= ELECTRICITY BILL MANAGEMENT =========
1. Display all bills
2. Add a new bill
3. Delete a bill
4. Find bill with largest amount
5. List unpaid bills
6. List overdue bills
7. Save to file
8. Exit
```

Features:
- Display all bills.
- Add bill (with input validation).
- Delete bill (choose criteria: ID, name, date).
- Find bill with highest amount.
- List all unpaid bills.
- **List overdue bills** (due date before current date and unpaid).
- Save list to file.
- Exit program.

---

### 4. Technical Requirements

- Follow MVC pattern strictly with packages: `model`, `view`, `controller`.
- Use exception handling for all invalid input cases.
- Automatically load file on startup.
- Automatically save to file after add/delete.
- Console display format must match examples.

---

### 5. Example `Electricitybill.txt`
```
1,Nguyen Van An,520000,20/05/2024,True
2,Nguyen Van Hung,1200000,20/04/2024,True
3,Le Anh,750000,20/03/2024,False
4,Tran Thi Hoa,330000,25/03/2024,False
5,Pham Minh,900000,12/04/2024,True
```

---
### 6. Console UI Format

When displaying bill list, each item must be printed using the format:
```

Bill{id=1, customerName='Nguyen Van An', amount=520000.0, dueDate=20/05/2024, isPaid=true}

```
The delete function must include a sub-menu:

```
Delete choice
----------------------
1. Delete by id
2. Delete by customer name
3. Delete by due date
----------------------
```
### Notes

1. If separating view and controller, put input/output methods into `BillView`.
2. **Strict adherence** to the specification is required. Any deviation results in 0 marks.
3. You may create helper classes/methods for clean and modular code.
4. Submit the complete NetBeans project folder.
