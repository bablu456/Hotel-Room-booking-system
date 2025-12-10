🏨 Hotel Room Booking System (Java Project)
A simple Hotel Room Booking System implemented in Java. This project demonstrates Object-Oriented Programming (OOP), exception handling, and interface usage. It’s designed as a learning project for mastering core Java concepts and interview preparation.

📌 Features
Room Management

Different room types: DeluxeRoom, Suite

Availability tracking (available flag)

Customer Management

Create customers with unique IDs

Associate customers with bookings

Booking System

Implements Bookable interface (book(), cancel())

Successful booking marks room as unavailable

Custom exceptions:

RoomNotAvailableException

InvalidBookingException

Console Output

Color-coded messages using ANSI escape codes (Colors class)

🛠️ Technologies Used
Language: Java

Concepts Practiced:

Classes & inheritance (Room, DeluxeRoom, Suite)

Interfaces (Bookable)

Exception handling (RoomNotAvailableException, InvalidBookingException)

Composition (Booking links Room + Customer)

Reusable constants (Colors for console styling)

📂 Project Structure
Code
├── Main.java              # Entry point (Hotel Room Booking System)
├── Colors.java            # ANSI color codes for console output
├── Room.java              # Base class for rooms
├── DeluxeRoom.java        # Subclass of Room
├── Suite.java             # Subclass of Room
├── Customer.java          # Customer details
├── Booking.java           # Booking logic implementing Bookable
└── Exceptions             # Custom exceptions
    ├── RoomNotAvailableException.java
    └── InvalidBookingException.java
▶️ How to Run
Compile the program

bash
javac Main.java
Run the executable

bash
java Main
Follow the menu options

Create customers

Book or cancel rooms

View booking details

📖 Example Flow
java
Customer c1 = new Customer(101, "Bablu");
Room r1 = new DeluxeRoom(201);
Booking b1 = new Booking(301, r1, c1);

b1.book(); 
// Output: Booking Successful for Bablu in Deluxe
🎯 Learning Outcomes
OOP Principles: Inheritance, interfaces, composition

Exception Handling: Custom checked exceptions for business logic

Generics Connection: Similar to your Generics lecture notes and interview prep examples, this project can be extended with generics (e.g., BookingManager<T extends Room>).

Interview Readiness: Explains practical use of exceptions, interfaces, and type safety

🚀 Future Improvements
Add Generics for type-safe booking managers (e.g., BookingManager<DeluxeRoom>)

Implement Collections API for managing multiple bookings

Add search and filter functionality for rooms/customers

Integrate with a database for persistence

👨‍💻 Author
Developed by Bablu Kumar Aspiring Full Stack Developer | Passionate about Java, Backend Systems, and DSA
