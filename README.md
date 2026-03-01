# Loan-management-System-1
BANK MANAGEMENT SYSTEM
(Loan and Fixed Deposit Management System in Java)

--------------------------------------------------
1. PROJECT TITLE
--------------------------------------------------
Loan and Fixed Deposit Management System

--------------------------------------------------
2. OBJECTIVE
--------------------------------------------------
The objective of this project is to develop a Java-based banking system
that allows users to:
- Apply for different types of loans
- Check loan eligibility
- Calculate EMI
- Create and manage Fixed Deposits (FD)
- Calculate interest and maturity amount

This project helps in understanding Object-Oriented Programming concepts
and real-world banking operations.

--------------------------------------------------
3. LOAN MANAGEMENT MODULE
--------------------------------------------------

Features:
- Collect user details (Name, Address, Credit Score, Income)
- Display available loan types:
  * Personal Loan
  * Home Loan
  * Car Loan
  * Education Loan
  * Business Loan
- Check eligibility:
  * Credit Score >= 650
  * Income >= 2000
- Calculate EMI
- Display approval or rejection message

EMI Formula:
EMI = (P * R * (1 + R)^N) / ((1 + R)^N - 1)

Where:
P = Loan Amount
R = Monthly Interest Rate
N = Number of Months

--------------------------------------------------
4. FIXED DEPOSIT MANAGEMENT MODULE
--------------------------------------------------

Features:
- Create Fixed Deposit
- Enter:
  * User ID
  * Principal Amount
  * Interest Rate
  * Tenure (months)
- FD Types:
  * Standard
  * Tax Saving
  * Cumulative
  * Non-Cumulative
- Automatic maturity date calculation
- Interest calculation:
  * Simple Interest (Non-Cumulative)
  * Compound Interest (Cumulative)
- View FD details
- Early withdrawal with penalty

--------------------------------------------------
5. TECHNOLOGIES USED
--------------------------------------------------
Programming Language: Java

Concepts Used:
- Classes and Objects
- Encapsulation
- Static and Non-static members
- ArrayList and HashMap
- Date and Time handling
- User input using Scanner

--------------------------------------------------
6. SYSTEM REQUIREMENTS
--------------------------------------------------
- Java JDK 8 or above
- Any IDE (Eclipse / IntelliJ / VS Code) or Command Prompt
- Notepad (for documentation)

--------------------------------------------------
7. ADVANTAGES
--------------------------------------------------
- Simple and user-friendly
- Demonstrates real banking concepts
- Accurate financial calculations
- Easy to understand and modify
- Suitable for academic projects

--------------------------------------------------
8. FUTURE ENHANCEMENTS
--------------------------------------------------
- Add database (MySQL)
- Add GUI using Java Swing or JavaFX
- Add login and authentication
- Generate PDF reports
- Integrate online banking features

--------------------------------------------------
END OF DOCUMENT
--------------------------------------------------
