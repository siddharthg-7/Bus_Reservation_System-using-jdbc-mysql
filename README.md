# Bus Reservation System

A desktop application for managing bus reservations built with Java Swing and MySQL database.

## Features

- **Make Reservations**: Book bus tickets by entering passenger details, bus number, travel date, and number of seats
- **View Available Buses**: Display all buses with real-time available seats calculation
- **View Reservations**: Show all booking records in a table format
- **Input Validation**: Comprehensive validation for all input fields
- **Date Format Conversion**: Automatic conversion from DD-MM-YYYY to MySQL DATE format
- **Dynamic Seat Availability**: Real-time calculation of available seats based on bookings
- **Auto-Database Setup**: Automatically creates database and tables on first run
- **Sample Data**: Includes sample bus data for immediate testing

## System Requirements

- **Java**: JDK 8 or higher
- **MySQL**: MySQL Server 5.7 or higher
- **JDBC Driver**: MySQL Connector/J 8.0+

## Database Configuration

The application uses the following default database settings:

- **Database Name**: `bus_reservation`
- **Host**: `localhost:3306`
- **Username**: `root`
- **Password**: `root`

To change these settings, edit the constants in `DatabaseConnection.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/";
private static final String DB_NAME = "bus_reservation";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "root";
```

## Database Schema

### Table: bus_info
Stores information about available buses.

| Column | Type | Description |
|--------|------|-------------|
| bus_number | VARCHAR(20) | Primary key, unique bus identifier |
| bus_name | VARCHAR(100) | Name of the bus service |
| source | VARCHAR(100) | Starting location |
| destination | VARCHAR(100) | Ending location |
| departure_time | TIME | Departure time |
| arrival_time | TIME | Arrival time |
| fare | DECIMAL(10,2) | Ticket price |
| total_seats | INT | Total seats in the bus |

### Table: reservations
Stores passenger booking records.

| Column | Type | Description |
|--------|------|-------------|
| id | INT | Auto-increment primary key |
| passenger_name | VARCHAR(100) | Name of the passenger |
| bus_number | VARCHAR(20) | Foreign key to bus_info |
| travel_date | DATE | Date of travel |
| seats | INT | Number of seats booked |

## Installation & Setup

### 1. Install MySQL Server

Make sure MySQL Server is installed and running on your system.

### 2. Download MySQL Connector/J

Download the MySQL JDBC driver from:
https://dev.mysql.com/downloads/connector/j/

Extract the JAR file (e.g., `mysql-connector-j-8.0.33.jar`)

### 3. Clone or Download the Project

```bash
git clone <repository-url>
cd javabusreservation
```

### 4. Compile the Application

Navigate to the project directory and compile with the MySQL connector in classpath:

**Windows:**
```bash
javac -cp ".;path\to\mysql-connector-j-8.0.33.jar" src\*.java
```

**Linux/Mac:**
```bash
javac -cp ".:path/to/mysql-connector-j-8.0.33.jar" src/*.java
```

### 5. Run the Application

**Windows:**
```bash
java -cp ".;path\to\mysql-connector-j-8.0.33.jar;src" BusReservationSystem
```

**Linux/Mac:**
```bash
java -cp ".:path/to/mysql-connector-j-8.0.33.jar:src" BusReservationSystem
```

## Usage Guide

### Making a Reservation

1. Enter the travel date in **DD-MM-YYYY** format (e.g., 25-11-2025)
2. Enter the passenger name
3. Enter the bus number (e.g., BUS001)
4. Enter the number of seats to book
5. Click **"Make Reservation"** button
6. A success message will appear if the booking is successful

### Viewing Available Buses

1. Click **"Show Available Buses"** button
2. The table will display all buses with:
   - Bus details (number, name, route, timings, fare)
   - Total seats
   - **Available seats** (dynamically calculated)

### Viewing Reservations

1. Click **"Show Reservations"** button
2. The table will display all bookings with passenger details

## Sample Bus Data

The application includes 5 sample buses for testing:

| Bus Number | Name | Route | Departure | Arrival | Fare | Seats |
|------------|------|-------|-----------|---------|------|-------|
| BUS001 | Express Deluxe | Mumbai → Pune | 06:00 | 09:30 | ₹350 | 40 |
| BUS002 | Volvo AC | Delhi → Jaipur | 08:00 | 13:00 | ₹800 | 45 |
| BUS003 | Sleeper Coach | Bangalore → Chennai | 22:00 | 06:00 | ₹950 | 35 |
| BUS004 | Semi Sleeper | Hyderabad → Vijayawada | 14:00 | 18:30 | ₹450 | 50 |
| BUS005 | Luxury AC | Kolkata → Bhubaneswar | 07:30 | 15:00 | ₹750 | 38 |

## Input Validation

The application validates:

- ✅ All fields must be filled
- ✅ Date must be in DD-MM-YYYY format
- ✅ Seats must be a positive number
- ✅ Bus number must exist in the database
- ✅ Requested seats must not exceed available seats

## Error Handling

The application handles:

- Database connection errors
- Invalid date formats
- Insufficient seat availability
- Non-existent bus numbers
- Invalid input data

## Project Structure

```
javabusreservation/
├── src/
│   ├── BusReservationSystem.java    # Main GUI application
│   └── DatabaseConnection.java      # Database utility class
├── database/
│   └── schema.sql                   # Database schema (optional)
└── README.md                        # This file
```

## Troubleshooting

### MySQL Connection Error

If you get a connection error:
1. Verify MySQL server is running
2. Check username and password in `DatabaseConnection.java`
3. Ensure MySQL is listening on port 3306

### ClassNotFoundException

If you get "MySQL JDBC Driver not found":
1. Ensure `mysql-connector-j-*.jar` is in your classpath
2. Check the `-cp` parameter when running the application

### Table Already Exists Error

The application automatically creates tables if they don't exist. If you encounter issues:
1. Drop the existing database: `DROP DATABASE bus_reservation;`
2. Restart the application

## License

This project is open source and available for educational purposes.

## Author

Built with Java Swing and MySQL for desktop bus reservation management.
