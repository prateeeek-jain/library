Design a Library Management System with the following requirements:
Entities:

Book — has ISBN, title, author, genre.
BookCopy — a physical copy of a book. Multiple copies can exist for the same book. Each copy has a unique copy ID and a status (AVAILABLE, BORROWED, LOST, UNDER_REPAIR).
Member — has ID, name, email, membership type (REGULAR, PREMIUM). Premium members can borrow more books and for longer.
Loan — represents a member borrowing a specific copy of a book, with issue date and due date.

Operations:

Add books and copies to the library.
Register members.
A member can borrow a book — system finds an available copy, marks it BORROWED, creates a Loan with a due date based on membership type.
A member can return a book — marks the copy AVAILABLE, closes the Loan, calculates fine if overdue.
A member can search for books by title or author (case-insensitive partial match).
List all books currently borrowed by a member.

Business rules:

REGULAR members can borrow up to 3 books at a time, for 14 days. Fine = ₹5/day overdue.
PREMIUM members can borrow up to 10 books, for 30 days. Fine = ₹2/day overdue.
You cannot borrow a book that has no AVAILABLE copies.
You cannot exceed your membership's borrow limit.