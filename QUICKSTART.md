# Quick Start Guide - Bus Reservation System

## ✅ Application Status

The Bus Reservation System has been successfully compiled and is ready to run!

## 🔧 MySQL Setup Required

Before using the application, you need to ensure MySQL is properly configured.

### Option 1: Using Default MySQL Installation (Recommended)

If you have MySQL installed with default settings:

1. **Start MySQL Server**
   - Windows: Start MySQL service from Services panel
   - Or use XAMPP/WAMP control panel

2. **Verify MySQL is Running**
   ```bash
   mysql -u root -p
   ```
   - If it asks for password, press Enter (for no password)
   - Or enter your MySQL root password

3. **Update Database Credentials** (if needed)
   - Open: `src/DatabaseConnection.java`
   - Lines 15-16:
     ```java
     private static final String DB_USER = "root";
     private static final String DB_PASSWORD = "";  // Change if you have a password
     ```

4. **Recompile** (if you changed credentials)
   ```bash
   compile.bat
   ```

5. **Run the Application**
   ```bash
   run.bat
   ```

### Option 2: Using XAMPP (Easy Setup)

If you don't have MySQL or want an easy setup:

1. **Download XAMPP**
   - Visit: https://www.apachefriends.org/
   - Download and install XAMPP

2. **Start MySQL**
   - Open XAMPP Control Panel
   - Click "Start" next to MySQL

3. **Run the Application**
   ```bash
   run.bat
   ```
   
   The application will automatically:
   - Create the `bus_reservation` database
   - Create required tables
   - Insert sample bus data

### Option 3: Manual Database Setup

If automatic setup doesn't work:

1. **Open MySQL Command Line or MySQL Workbench**

2. **Run the schema script**
   ```bash
   mysql -u root -p < database/schema.sql
   ```

3. **Or manually execute:**
   ```sql
   CREATE DATABASE bus_reservation;
   USE bus_reservation;
   
   -- Copy and paste the contents of database/schema.sql
   ```

4. **Run the application**
   ```bash
   run.bat
   ```

## 🚀 Running the Application

### Windows
```bash
run.bat
```

### Linux/Mac
```bash
chmod +x run.sh
./run.sh
```

### Manual Command
```bash
java -cp ".;lib\mysql-connector-j-8.0.33.jar;bin" BusReservationSystem
```

## 📝 Testing the Application

Once the application window opens:

### 1. View Available Buses
- Click **"Show Available Buses"** button
- You should see 5 sample buses

### 2. Make a Test Reservation
Fill in the form:
- **Date:** `25-12-2025` (DD-MM-YYYY format)
- **Passenger Name:** `John Doe`
- **Bus Number:** `BUS001`
- **Seats:** `2`
- Click **"Make Reservation"**

### 3. View Reservations
- Click **"Show Reservations"** button
- Your booking should appear

## ❌ Common Issues & Solutions

### Issue 1: "Access denied for user 'root'@'localhost'"

**Solution:**
1. Find your MySQL password
2. Edit `src/DatabaseConnection.java` line 16:
   ```java
   private static final String DB_PASSWORD = "your_actual_password";
   ```
3. Recompile: `compile.bat`
4. Run: `run.bat`

### Issue 2: "Communications link failure"

**Solution:**
- MySQL server is not running
- Start MySQL service or XAMPP

### Issue 3: Application window appears but buttons don't work

**Solution:**
- Check console for error messages
- Verify MySQL credentials
- See `DATABASE_CONFIG.md` for detailed configuration

### Issue 4: "MySQL JDBC Driver not found"

**Solution:**
- Ensure `lib/mysql-connector-j-8.0.33.jar` exists
- Recompile with: `compile.bat`

## 📚 Documentation Files

- **README.md** - Complete feature documentation
- **SETUP.md** - Detailed setup instructions
- **DATABASE_CONFIG.md** - Database configuration guide
- **DOWNLOAD_MYSQL_CONNECTOR.md** - MySQL connector download guide

## 🎯 Quick Reference

### Sample Buses Included

| Bus Number | Route | Departure | Fare | Seats |
|------------|-------|-----------|------|-------|
| BUS001 | Mumbai → Pune | 06:00 | ₹350 | 40 |
| BUS002 | Delhi → Jaipur | 08:00 | ₹800 | 45 |
| BUS003 | Bangalore → Chennai | 22:00 | ₹950 | 35 |
| BUS004 | Hyderabad → Vijayawada | 14:00 | ₹450 | 50 |
| BUS005 | Kolkata → Bhubaneswar | 07:30 | ₹750 | 38 |

### Date Format
- **Input:** DD-MM-YYYY (e.g., 25-12-2025)
- **Display:** YYYY-MM-DD (automatic conversion)

### Features
✅ Make reservations  
✅ View available buses with real-time seat availability  
✅ View all reservations  
✅ Input validation  
✅ Prevent overbooking  
✅ Auto-refresh tables  

## 💡 Tips

1. **First Time Running:** Click "Show Available Buses" to verify database connection
2. **Testing:** Use the sample buses (BUS001-BUS005) for testing
3. **Date Format:** Always use DD-MM-YYYY format
4. **Seat Availability:** Available seats update automatically after each booking

## 🆘 Need Help?

If you encounter issues:

1. Check the console output for error messages
2. Verify MySQL is running
3. Check database credentials in `DatabaseConnection.java`
4. See `DATABASE_CONFIG.md` for detailed troubleshooting
5. Ensure all files are compiled: `compile.bat`

## 🎉 Success Indicators

You'll know everything is working when:
- ✅ Application window opens without errors
- ✅ "Show Available Buses" displays 5 buses
- ✅ You can successfully make a reservation
- ✅ Success dialog appears after booking
- ✅ Reservation appears in the table

---

**Enjoy using the Bus Reservation System!** 🚌
