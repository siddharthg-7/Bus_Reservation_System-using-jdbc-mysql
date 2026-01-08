#  Bus Reservation System

A complete desktop application for managing bus reservations built with **Java Swing** and **MySQL** database using **JDBC** connectivity.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-orange?style=for-the-badge)

##  Features

-  **Make Reservations** - Book bus tickets with passenger details
-  **View Available Buses** - Display all buses with real-time seat availability
-  **View Reservations** - Show all booking records
-  **Input Validation** - Comprehensive validation for all fields
-  **Date Format Conversion** - Automatic DD-MM-YYYY to YYYY-MM-DD conversion
-  **Dynamic Seat Calculation** - Real-time available seats tracking
-  **Auto Database Setup** - Automatically creates database and tables
-  **Sample Data** - Includes 5 sample buses for testing

##  Screenshots

### Main Application Window
The application features a clean, professional interface with input fields, action buttons, and data tables.

### Features in Action
- **Make Reservation**: Enter passenger details and book seats
- **Available Buses**: View all buses with real-time seat availability
- **Reservations**: Track all bookings in a table format

##  Technology Stack

- **Language**: Java (JDK 8+)
- **GUI Framework**: Java Swing
- **Database**: MySQL 5.7+
- **Connectivity**: JDBC (MySQL Connector/J 8.0.33)
- **Build Tools**: Command-line compilation (batch/shell scripts provided)

##  Prerequisites

Before running the application, ensure you have:

1. **Java Development Kit (JDK) 8 or higher**
   - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
   - Verify: `java -version`

2. **MySQL Server 5.7 or higher**
   - Download: [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
   - Ensure MySQL is running on port 3306

3. **MySQL Connector/J (JDBC Driver)**
   - Included in `lib/` directory
   - Or download: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

##  Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/siddharthg-7/Bus_Reservation_System-using-jdbc-mysql.git
cd Bus_Reservation_System-using-jdbc-mysql
```

### . Configure Database Credentials

Edit `src/DatabaseConnection.java` (lines 15-16):

```java
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_mysql_password";
```

###  Compile the Application

**Windows:**
```bash
compile.bat
```

**Linux/Mac:**
```bash
chmod +x compile.sh
./compile.sh
```

### 4. Run the Application

**Windows:**
```bash
run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

##  Project Structure

```
Bus_Reservation_System-using-jdbc-mysql/
├── src/
│   ├── BusReservationSystem.java    # Main GUI application
│   └── DatabaseConnection.java      # Database utility class
├── lib/
│   └── mysql-connector-j-8.0.33.jar # MySQL JDBC driver
├── bin/                              # Compiled class files
├── database/
│   └── schema.sql                   # Database schema (optional)
├── compile.bat / compile.sh         # Compilation scripts
├── run.bat / run.sh                 # Execution scripts
├── README.md                        # Main documentation
├── SETUP.md                         # Detailed setup guide
├── QUICKSTART.md                    # Quick start guide
├── DATABASE_CONFIG.md               # Database configuration help
└── .gitignore                       # Git ignore rules
```

##  Database Schema

### Table: `bus_info`

| Column | Type | Description |
|--------|------|-------------|
| bus_number | VARCHAR(20) PK | Unique bus identifier |
| bus_name | VARCHAR(100) | Bus service name |
| source | VARCHAR(100) | Starting location |
| destination | VARCHAR(100) | Ending location |
| departure_time | TIME | Departure time |
| arrival_time | TIME | Arrival time |
| fare | DECIMAL(10,2) | Ticket price (₹) |
| total_seats | INT | Total seats available |

### Table: `reservations`

| Column | Type | Description |
|--------|------|-------------|
| id | INT AUTO_INCREMENT PK | Unique booking ID |
| passenger_name | VARCHAR(100) | Passenger name |
| bus_number | VARCHAR(20) FK | Reference to bus_info |
| travel_date | DATE | Date of travel |
| seats | INT | Number of seats booked |

##  Sample Data

The application includes 5 pre-configured buses:

| Bus Number | Route | Timing | Fare | Seats |
|------------|-------|--------|------|-------|
| BUS001 | Mumbai → Pune | 06:00 - 09:30 | ₹350 | 40 |
| BUS002 | Delhi → Jaipur | 08:00 - 13:00 | ₹800 | 45 |
| BUS003 | Bangalore → Chennai | 22:00 - 06:00 | ₹950 | 35 |
| BUS004 | Hyderabad → Vijayawada | 14:00 - 18:30 | ₹450 | 50 |
| BUS005 | Kolkata → Bhubaneswar | 07:30 - 15:00 | ₹750 | 38 |

##  Usage Guide

### Making a Reservation

1. Enter travel date in **DD-MM-YYYY** format (e.g., `25-12-2025`)
2. Enter passenger name
3. Enter bus number (e.g., `BUS001`)
4. Enter number of seats
5. Click **"Make Reservation"**
6. Success message will confirm the booking

### Viewing Available Buses

1. Click **"Show Available Buses"** button
2. Table displays all buses with:
   - Bus details (number, name, route, timings, fare)
   - Total seats
   - Available seats (dynamically calculated)

### Viewing Reservations

1. Click **"Show Reservations"** button
2. Table displays all bookings with passenger details

##  Input Validation

The application validates:

- ✓ All fields must be filled
- ✓ Date must be in DD-MM-YYYY format
- ✓ Seats must be a positive number
- ✓ Bus number must exist in database
- ✓ Requested seats ≤ available seats

##  Troubleshooting

### Access Denied Error

**Error**: `Access denied for user 'root'@'localhost'`

**Solution**:
1. Update `DB_PASSWORD` in `src/DatabaseConnection.java`
2. Recompile: `compile.bat`
3. Run: `run.bat`

### Communications Link Failure

**Error**: `Communications link failure`

**Solution**:
- Ensure MySQL server is running
- Verify it's on port 3306
- Check firewall settings

### MySQL JDBC Driver Not Found

**Error**: `MySQL JDBC Driver not found`

**Solution**:
- Ensure `lib/mysql-connector-j-8.0.33.jar` exists
- Recompile with correct classpath

##  Documentation

- **[README.md](README.md)** - This file
- **[SETUP.md](SETUP.md)** - Detailed setup instructions
- **[QUICKSTART.md](QUICKSTART.md)** - Quick start guide
- **[DATABASE_CONFIG.md](DATABASE_CONFIG.md)** - Database configuration
- **[DOWNLOAD_MYSQL_CONNECTOR.md](DOWNLOAD_MYSQL_CONNECTOR.md)** - Connector guide

##  Key Features Implementation

### Dynamic Seat Availability

```sql
SELECT (total_seats - COALESCE(SUM(booked_seats), 0)) as available_seats
FROM bus_info b
LEFT JOIN reservations r ON b.bus_number = r.bus_number
GROUP BY b.bus_number
```

### Date Format Conversion

```java
SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = inputFormat.parse(userInput);
String mysqlDate = outputFormat.format(date);
```

### Auto Database Initialization

```java
// Creates database if not exists
stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS bus_reservation");

// Creates tables if not exist
// Inserts sample data if empty
```

##  Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

##  License

This project is open source and available for educational purposes.

##  Author

**Siddharth G**
- GitHub: [@siddharthg-7](https://github.com/siddharthg-7)

##  Acknowledgments

- Java Swing for the GUI framework
- MySQL for the database
- JDBC for database connectivity

##  Support

For issues or questions:
- Open an issue on GitHub
- Check the documentation files
- Review error messages in console

---

**⭐ If you find this project useful, please consider giving it a star!**

Made with ❤️ using Java Swing and MySQL
