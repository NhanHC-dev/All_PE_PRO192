# Develop Employee Management OOP Application

## 1. Create MVC console application in NetBeans:

### a. Define class `Employee` with the following attributes and methods: (2 marks)
- `EmpId`, `Name`, `WorkStartingDate`, `ProductivityScore`, `Allowance`, `RewardSalary`, `MonthlyIncome`
- Constructors, Getters/Setters, `toString()` method
- Method to calculate `RewardSalary` = `ProductivityScore * 3,000,000`
- Method to calculate `MonthlyIncome` = `RewardSalary + Allowance`
- Method to update employee's data (Name, Date, Score, Allowance)
- Implements `Comparable<Employee>` to compare by `ProductivityScore` (descending)  
**Note:** No IO console is used in this model class

### b. Define class `EmployeeList` to manage the list of employees with the following methods: (4 marks)
- Read text file to load employee data – `employee.txt` is provided **(1 mark)**
- Method to add a new employee to the list with **unique EmpId** and **no duplicate phone** **(1 mark)**
- Method to search employee by any criteria using `Predicate` (e.g., name, year) **(1 mark)**
- Method to sort employees by `ProductivityScore` descending **(1 mark)**  
**Note:** No IO console is used in this model class

### c. Develop `EmployeeManagement` class to run the application (4 marks)
- Implement a menu-driven system with the following features:  
  > Hint: An abstract `Menu` class is provided for use in the application.

1. **Import employee data from file**  
2. **Add new employee**  
3. **Update employee by ID**  
4. **Delete employee by ID or ProductivityScore**  
   - Submenu: Choose to delete by either `EmpId` or `ProductivityScore`  
5. **Search employee by name or WorkStartingDate**  
   - Submenu: Choose to search by either `Name` (case-insensitive substring match) or `WorkStartingDate` (`dd/MM/yyyy`)  
6. **Calculate and show salary** (RewardSalary = ProductivityScore × 3,000,000; MonthlyIncome = RewardSalary + Allowance)  
7. **Sort by coefficient salary (ProductivityScore in descending order)**  
8. **Statistic: Number of employees by join year**  
9. **Export to file** (`output.txt`)  
10. **Exit the program**  

---

## 2. Technical Requirements:

- Use OOP design and MVC structure  
- Create a package `model` for `Employee` and `EmployeeList`  
- Design a package `controller` for class `EmployeeManagement` with menu handling logic  
- Use `Menu` abstract class from `view` package  
- Use `Predicate` interface for flexible search criteria  
- Validate inputs:  
  - `EmpId` must start with "MNV", followed by 3 digits and be exactly 6 characters  
  - `WorkStartingDate` format must be `dd/MM/yyyy`  

## 3. Sample User Interface

```
=========== EMPLOYEE MANAGEMENT ===========
1. Display all employee
2. Add new employee
3. Update employee by ID
4. Delete employee by ID or ProductivityScore
5. Search employee by name or WorkStartingDate
6. Calculate and show salary
7. Sort by coefficient salary (desc)
8. Statistic: Number of employees by join year
9. Export to file
10. Exit
===========================================
```

Sample output after calculating salaries:
```
MNV001 | JohnDoe  | 15/03/2020 | 0.90 | 2700000 | 4700000
MNV002 | Alice    | 21/06/2021 | 1.10 | 3300000 | 5300000
```
Sample option 4
```
------ DELETE EMPLOYEE ------
1. Delete by Employee ID
2. Delete by ProductivityScore
3. Back to main menu
-----------------------------
Enter your choice:
```
Sample option 5
```
------ SEARCH EMPLOYEE ------
1. Search by Name
2. Search by WorkStartingDate
3. Back to main menu
-----------------------------
Enter your choice:
```