# Database Configuration Guide

## Current Settings

The application is currently configured with:

```java
DB_URL = "jdbc:mysql://localhost:3306/"
DB_NAME = "bus_reservation"
DB_USER = "root"
DB_PASSWORD = ""  // Empty password (default for most MySQL installations)
```

## How to Change Database Credentials

If your MySQL server uses different credentials, follow these steps:

### Step 1: Open DatabaseConnection.java

Navigate to: `src/DatabaseConnection.java`

### Step 2: Update the Constants (Lines 13-16)

Find these lines:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/";
private static final String DB_NAME = "bus_reservation";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "";
```

### Step 3: Modify as Needed

**For a different username:**
```java
private static final String DB_USER = "your_username";
```

**For a password:**
```java
private static final String DB_PASSWORD = "your_password";
```

**For a different host/port:**
```java
private static final String DB_URL = "jdbc:mysql://192.168.1.100:3306/";
```

**For a different database name:**
```java
private static final String DB_NAME = "my_bus_system";
```

### Step 4: Recompile

After making changes, recompile the application:

**Windows:**
```bash
compile.bat
```

**Linux/Mac:**
```bash
./compile.sh
```

### Step 5: Run

```bash
run.bat    # Windows
./run.sh   # Linux/Mac
```

## Common MySQL Configurations

### Default XAMPP Installation
```java
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "siddu";  // Empty password
```

### Default WAMP Installation
```java
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "siddu";  // Empty password
```

### Default MAMP Installation (Mac)
```java
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "siddu";
```

### Custom MySQL Installation
```java
private static final String DB_USER = "admin";
private static final String DB_PASSWORD = "siddu";
```

### Remote MySQL Server
```java
private static final String DB_URL = "jdbc:mysql://myserver.com:3306/";
private static final String DB_USER = "remote_user";
private static final String DB_PASSWORD = "remote_password";
```

## Troubleshooting

### Error: "Access denied for user 'root'@'localhost'"

**Cause:** Incorrect username or password

**Solution:**
1. Check your MySQL credentials
2. Update `DB_USER` and `DB_PASSWORD` in DatabaseConnection.java
3. Recompile and run

### Error: "Communications link failure"

**Cause:** MySQL server is not running or wrong host/port

**Solution:**
1. Start MySQL server
2. Verify it's running on the correct port (default: 3306)
3. Update `DB_URL` if using a different host/port

### Error: "Unknown database 'bus_reservation'"

**Cause:** Database doesn't exist (shouldn't happen as app auto-creates it)

**Solution:**
1. Ensure the MySQL user has CREATE DATABASE privileges
2. Or manually create the database:
   ```sql
   CREATE DATABASE bus_reservation;
   ```

## Testing Your Connection

You can test the database connection separately:

```bash
# Compile
javac -cp ".;lib\mysql-connector-j-8.0.33.jar" src\DatabaseConnection.java

# Run test
java -cp ".;lib\mysql-connector-j-8.0.33.jar;src" DatabaseConnection
```

If successful, you'll see:
```
Database connection successful!
Database: bus_reservation
```

## Security Note

> [!WARNING]
> For production use, never hardcode passwords in source code. Consider using:
> - Environment variables
> - Configuration files (not in version control)
> - Secure credential management systems

Example using environment variables:
```java
private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
```

Then set the environment variable before running:
```bash
set DB_PASSWORD=siddu  # Windows
export DB_PASSWORD=siddu  # Linux/Mac
```
