import java.util.*;

class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
}

class RoomNotAvailableException extends Exception {
    public RoomNotAvailableException(String msg) {
        super(msg);
    }
}

class InvalidBookingException extends Exception {
    public InvalidBookingException(String msg) {
        super(msg);
    }
}

interface Bookable {
    void book();
    void cancel();
}

class Room {
    int roomId;
    String type; // AC/Non-AC
    boolean available = true;

    Room(int roomId, String type) {
        this.roomId = roomId;
        this.type = type;
    }

    public String toString() {
        return "Room ID: " + roomId + " | Type: " + type + " | Available: " + available;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int id) {
        super(id, "Deluxe");
    }
}

class Suite extends Room {
    Suite(int id) {
        super(id, "Suite");
    }
}

class Customer {
    String name;
    int customerId;

    Customer(int id, String name) {
        this.customerId = id;
        this.name = name;
    }

    public String toString() {
        return "Customer ID: " + customerId + " | Name: " + name;
    }
}

class Booking implements Bookable {
    int bookingId;
    Room room;
    Customer customer;

    Booking(int id, Room room, Customer customer) {
        this.bookingId = id;
        this.room = room;
        this.customer = customer;
    }

    @Override
    public void book() {
        room.available = false;
        System.out.println(Colors.GREEN + " Booking Successful for " + customer.name + " in " + room.type + Colors.RESET);
    }

    @Override
    public void cancel() {
        room.available = true;
        System.out.println(Colors.RED + " Booking Cancelled for " + customer.name + Colors.RESET);
    }

    public String toString() {
        return "Booking ID: " + bookingId + " | " + customer + " | " + room;
    }
}

class Admin {
    private final String username = "admin";
    private final String password = "1234";

    public boolean login(String user, String pass) {
        return user.equals(username) && pass.equals(password);
    }
}

 class HotelBookingSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static int bookingCounter = 1;
    static int customerCounter = 1;

    public static void main(String[] args) {
        seedData(); // initial rooms

        while (true) {
            System.out.println(Colors.CYAN + "\n===== HOTEL ROOM BOOKING SYSTEM =====" + Colors.RESET);
            System.out.println(Colors.YELLOW + "1. Admin Login" + Colors.RESET);
            System.out.println(Colors.YELLOW + "2. Customer Menu" + Colors.RESET);
            System.out.println(Colors.YELLOW + "3. Exit" + Colors.RESET);
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> {
                    System.out.println(Colors.GREEN + "Thank you for using the system!" + Colors.RESET);
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    static void seedData() {
        rooms.add(new DeluxeRoom(101));
        rooms.add(new DeluxeRoom(102));
        rooms.add(new Suite(201));
        rooms.add(new Suite(202));
    }

    static void adminMenu() {
        Admin admin = new Admin();
        System.out.print("Enter Admin Username: ");
        String user = sc.next();
        System.out.print("Enter Admin Password: ");
        String pass = sc.next();

        if (!admin.login(user, pass)) {
            System.out.println(Colors.RED + "Invalid Login!" + Colors.RESET);
            return;
        }

        while (true) {
            System.out.println(Colors.BLUE + "\n===== ADMIN PANEL =====" + Colors.RESET);
            System.out.println("1. View All Rooms");
            System.out.println("2. View All Bookings");
            System.out.println("3. Add New Room");
            System.out.println("4. Remove Room");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> rooms.forEach(System.out::println);
                case 2 -> bookings.forEach(System.out::println);
                case 3 -> addRoom();
                case 4 -> removeRoom();
                case 5 -> { return; }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    static void addRoom() {
        System.out.print("Enter Room ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Type (Deluxe/Suite): ");
        String type = sc.next();
        if (type.equalsIgnoreCase("Deluxe"))
            rooms.add(new DeluxeRoom(id));
        else
            rooms.add(new Suite(id));

        System.out.println(Colors.GREEN + "Room Added Successfully!" + Colors.RESET);
    }

    static void removeRoom() {
        System.out.print("Enter Room ID to remove: ");
        int id = sc.nextInt();
        rooms.removeIf(r -> r.roomId == id);
        System.out.println(Colors.RED + "Room Removed Successfully!" + Colors.RESET);
    }

    // ---------- Customer Menu ----------
    static void customerMenu() {
        System.out.println(Colors.BLUE + "\n===== CUSTOMER MENU =====" + Colors.RESET);
        System.out.println("1. View Available Rooms");
        System.out.println("2. Book Room");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Back");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1 -> viewAvailableRooms();
            case 2 -> bookRoom();
            case 3 -> cancelBooking();
            case 4 -> { return; }
            default -> System.out.println("Invalid Choice!");
        }
    }

    static void viewAvailableRooms() {
        System.out.println(Colors.YELLOW + "\n=== Available Rooms ===" + Colors.RESET);
        rooms.stream().filter(r -> r.available).forEach(System.out::println);
    }

    static void bookRoom() {
        try {
            System.out.print("Enter Your Name: ");
            String name = sc.next();
            Customer cust = new Customer(customerCounter++, name);

            System.out.print("Enter Room ID to Book: ");
            int id = sc.nextInt();
            Room room = rooms.stream().filter(r -> r.roomId == id && r.available).findFirst()
                    .orElseThrow(() -> new RoomNotAvailableException("Room not available!"));

            Booking booking = new Booking(bookingCounter++, room, cust);
            booking.book();
            bookings.add(booking);

        } catch (RoomNotAvailableException e) {
            System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
        }
    }

    static void cancelBooking() {
        try {
            System.out.print("Enter Booking ID to cancel: ");
            int id = sc.nextInt();
            Booking booking = bookings.stream().filter(b -> b.bookingId == id).findFirst()
                    .orElseThrow(() -> new InvalidBookingException("Invalid Booking ID!"));
            booking.cancel();
            bookings.remove(booking);
        } catch (InvalidBookingException e) {
            System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
        }
    }
}