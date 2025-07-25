# PRACTICAL EXAMINATION

---

## 1. Create MVC console application in NetBeans:

### a. Define class `Employee` with the following attributes and methods: (2 marks)
- `EmpId`, `Name`, `WorkStartingDate`, `ProductivityScore`, `Allowance`, `RewardSalary`, `MonthlyIncome`
- Constructors, Getters/Setters, `toString()` method
- Method to calculate `RewardSalary = ProductivityScore × 3,000,000`
- Method to calculate `MonthlyIncome = RewardSalary + Allowance`
- Method to update employee's data (Name, Date, Score, Allowance)
- Implements `Comparable<Employee>` to compare by `ProductivityScore` (descending)  
> **Note:** No IO console is used in this model class

---

### b. Define class `EmployeeList` to manage the list of employees with the following methods: (4 marks)
- Read text file to load employee data – `employee.txt` is provided **(1 mark)**
- Method to add a new employee to the list with:
  - Unique `EmpId` (must start with "MNV" followed by 3 digits)
  - No duplicate phone number **(1 mark)**
- Method to search employees using `Predicate` (e.g., by name, year, etc.) **(1 mark)**
- Method to sort employees by `ProductivityScore` in descending order **(1 mark)**  
> **Note:** No IO console in this class

---

### c. Develop `EmployeeManagement` class to run the application (4 marks)
- Use a menu-driven interface with the following features:  
> Hint: An abstract `Menu` class is provided to use as base.

#### Menu Options:
1. **Import employee data from file**
2. **Add new employee**
3. **Update employee by ID**
4. **Delete employee by ID or by income lower than average**
    - Submenu:  
      - 1. Delete by Employee ID  
      - 2. Delete all employees with `MonthlyIncome < average income`
      - 3. Back to main menu
5. **Search employee by name or WorkStartingDate**
    - Submenu:  
      - 1. Search by Name (case-insensitive substring match)  
      - 2. Search by WorkStartingDate (`dd/MM/yyyy`)
      - 3. Back to main menu
6. **Calculate and display salary** (`RewardSalary` and `MonthlyIncome`)
7. **Sort by ProductivityScore (descending)**
8. **Statistic: Number of employees by join year**
9. **Export employee list to file** (`output.txt`)
10. **Exit the program**

---

## 2. Technical Requirements:

- Use proper OOP and MVC design
- Place model classes (`Employee`, `EmployeeList`) in `model` package
- Place control logic in `controller` package (`EmployeeManagement`)
- Use `Menu` abstract class from `view` package
- Use `Predicate<T>` for flexible searching
- Validate input:
  - `EmpId`: must match `"MNV\d{3}"`
  - `WorkStartingDate`: format `"dd/MM/yyyy"`

---

## 3. Sample Interface

```
=========== EMPLOYEE MANAGEMENT ===========
1. Import from file
2. Add new employee
3. Update employee by ID
4. Delete employee by ID or by income < average
5. Search employee by name or start date
6. Calculate and show salary
7. Sort by productivity score (desc)
8. Statistic: Employees by year
9. Export to file
10. Exit
===========================================
```

### Option 4 – Delete Submenu
```
------ DELETE EMPLOYEE ------
1. Delete by Employee ID
2. Delete by MonthlyIncome < average
3. Back to main menu
-----------------------------
Enter your choice:
```

### Option 5 – Search Submenu
```
------ SEARCH EMPLOYEE ------
1. Search by Name
2. Search by WorkStartingDate
3. Back to main menu
-----------------------------
Enter your choice:
```

### Sample Salary Output:
```
MNV001 | John Doe  | 15/03/2020 | 0.90 | 2700000 | 4700000
MNV002 | Alice     | 21/06/2021 | 1.10 | 3300000 | 5300000
```

---

## 4. File Input Format (`employee.txt`)

Each line contains:  
`EmpId,Name,WorkStartingDate,ProductivityScore,Allowance`

Example:
```
MNV001,John Doe,15/03/2020,0.9,2000000
MNV002,Alice,21/06/2021,1.1,2000000
```

---

## 5. Export Format (`output.txt`)

Each line:
```
EmpId | Name | WorkStartingDate | ProductivityScore | RewardSalary | MonthlyIncome
```

---

**End of Practical Examination**
