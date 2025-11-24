# Bus Reservation System - Setup Instructions

## Quick Start Guide

### Prerequisites

1. **Java Development Kit (JDK) 8 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **MySQL Server 5.7 or higher**
   - Download from: https://dev.mysql.com/downloads/mysql/
   - Make sure MySQL is running on port 3306

3. **MySQL Connector/J (JDBC Driver)**
   - Download from: https://dev.mysql.com/downloads/connector/j/
   - Download version 8.0.33 or later

### Step-by-Step Setup

#### 1. Download MySQL Connector

1. Visit: https://dev.mysql.com/downloads/connector/j/
2. Select "Platform Independent" 
3. Download the ZIP archive
4. Extract the ZIP file
5. Find the JAR file: `mysql-connector-j-8.0.33.jar`

#### 2. Setup Project Structure

Create a `lib` folder in the project directory and place the MySQL connector JAR:

```
javabusreservation/
├── lib/
│   └── mysql-connector-j-8.0.33.jar  ← Place the JAR here
├── src/
│   ├── BusReservationSystem.java
│   └── DatabaseConnection.java
├── compile.bat
├── run.bat
└── README.md
```

#### 3. Configure MySQL Database

The application will auto-create the database, but you need to ensure:

- MySQL server is running
- Default credentials are `root` / `root`
- If your credentials are different, edit `src/DatabaseConnection.java`:

```java
private static final String DB_USER = "your_username";
private static final String DB_PASSWORD = "your_password";
```

#### 4. Compile the Application

**On Windows:**
```bash
compile.bat
```

**On Linux/Mac:**
```bash
chmod +x compile.sh
./compile.sh
```

This will:
- Check for MySQL connector JAR
- Compile all Java files
- Place compiled classes in `bin/` directory

#### 5. Run the Application

**On Windows:**
```bash
run.bat
```

**On Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

The application window will open automatically!

### First Run

On the first run, the application will:
1. ✅ Create the `bus_reservation` database
2. ✅ Create `bus_info` and `reservations` tables
3. ✅ Insert 5 sample buses for testing

### Testing the Application

1. **View Available Buses**
   - Click "Show Available Buses" button
   - You should see 5 sample buses

2. **Make a Test Reservation**
   - Date: `25-11-2025` (DD-MM-YYYY format)
   - Passenger Name: `John Doe`
   - Bus Number: `BUS001`
   - Seats: `2`
   - Click "Make Reservation"

3. **View Reservations**
   - Click "Show Reservations" button
   - Your booking should appear in the table

### Troubleshooting

#### "MySQL Connector JAR not found"
- Make sure `mysql-connector-j-8.0.33.jar` is in the `lib/` folder
- Check the filename matches exactly

#### "Access denied for user 'root'@'localhost'"
- Update username/password in `DatabaseConnection.java`
- Or create a MySQL user with username `root` and password `root`

#### "Communications link failure"
- Ensure MySQL server is running
- Check if MySQL is listening on port 3306
- Verify firewall settings

#### "Table 'bus_reservation.bus_info' doesn't exist"
- The application should auto-create tables
- If it fails, manually run `database/schema.sql` in MySQL

### Manual Database Setup (Optional)

If auto-creation fails, you can manually setup the database:

```bash
mysql -u root -p < database/schema.sql
```

Or using MySQL Workbench:
1. Open MySQL Workbench
2. Connect to your MySQL server
3. Open `database/schema.sql`
4. Execute the script

### Next Steps

- Modify sample bus data in `DatabaseConnection.java`
- Add more buses through MySQL
- Customize the GUI in `BusReservationSystem.java`
- Add additional features like cancellation, search, etc.

### Support

For issues or questions:
- Check the main [README.md](README.md) for detailed documentation
- Review error messages in the console
- Verify MySQL connection settings
