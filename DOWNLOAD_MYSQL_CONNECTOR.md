# Download MySQL Connector/J

The Bus Reservation System requires the MySQL JDBC driver to connect to the database.

## Download Instructions

### Option 1: Direct Download (Recommended)

1. Visit the MySQL Connector/J download page:
   **https://dev.mysql.com/downloads/connector/j/**

2. Select the following:
   - **Operating System**: Platform Independent
   - **Version**: 8.0.33 or later

3. Click **Download** on the ZIP Archive option

4. You may need to:
   - Login to Oracle account (free), OR
   - Click "No thanks, just start my download" at the bottom

5. Extract the downloaded ZIP file

6. Find the JAR file: `mysql-connector-j-8.0.33.jar`

7. Copy it to the `lib/` folder in this project:
   ```
   javabusreservation/lib/mysql-connector-j-8.0.33.jar
   ```

### Option 2: Maven Central (Alternative)

If you prefer, you can download directly from Maven Central:

**Direct Link:**
https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar

1. Click the link above
2. Save the file as `mysql-connector-j-8.0.33.jar`
3. Place it in the `lib/` folder

### Option 3: Using Maven (For Maven Users)

If you're using Maven, add this dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Option 4: Using Gradle (For Gradle Users)

If you're using Gradle, add this to your `build.gradle`:

```gradle
dependencies {
    implementation 'com.mysql:mysql-connector-j:8.0.33'
}
```

## Verify Installation

After placing the JAR file in the `lib/` folder, verify the structure:

```
javabusreservation/
├── lib/
│   └── mysql-connector-j-8.0.33.jar  ✓ This file should exist
├── src/
│   ├── BusReservationSystem.java
│   └── DatabaseConnection.java
├── compile.bat
└── run.bat
```

## Next Steps

Once the MySQL connector is in place:

1. **Compile**: Run `compile.bat` (Windows) or `./compile.sh` (Linux/Mac)
2. **Run**: Execute `run.bat` (Windows) or `./run.sh` (Linux/Mac)

## Troubleshooting

### "File not found" error when compiling
- Ensure the JAR filename is exactly: `mysql-connector-j-8.0.33.jar`
- Check that it's in the `lib/` folder, not in a subfolder
- If you downloaded a different version, update the filename in `compile.bat` and `run.bat`

### Different version downloaded
If you downloaded a different version (e.g., 9.0.0), you need to update:

1. **compile.bat** - Change line:
   ```batch
   javac -cp ".;lib\mysql-connector-j-9.0.0.jar" -d bin src\*.java
   ```

2. **run.bat** - Change line:
   ```batch
   java -cp ".;lib\mysql-connector-j-9.0.0.jar;bin" BusReservationSystem
   ```

3. **compile.sh** and **run.sh** - Update similarly for Linux/Mac

## Why is this needed?

The MySQL Connector/J is the official JDBC driver for MySQL. It allows Java applications to:
- Connect to MySQL databases
- Execute SQL queries
- Retrieve and manipulate data

Without this driver, the application cannot communicate with the MySQL database.
