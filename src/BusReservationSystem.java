import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * BusReservationSystem - Main GUI application for bus reservation management
 * Features: Make reservations, view available buses, view all reservations
 */
public class BusReservationSystem extends JFrame {

    // GUI Components
    private JTextField txtDate, txtPassengerName, txtBusNumber, txtSeats;
    private JButton btnMakeReservation, btnShowBuses, btnShowReservations;
    private JTable tableBuses, tableReservations;
    private DefaultTableModel busTableModel, reservationTableModel;
    private JScrollPane scrollBuses, scrollReservations;

    /**
     * Constructor - Initializes the GUI components
     */
    public BusReservationSystem() {
        setTitle("Bus Reservation System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Create and add components
        add(createInputPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Creates the input panel with form fields and buttons
     */
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Reservation Details"));
        panel.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Date field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Travel Date (DD-MM-YYYY):"), gbc);
        gbc.gridx = 1;
        txtDate = new JTextField(15);
        panel.add(txtDate, gbc);

        // Passenger Name field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Passenger Name:"), gbc);
        gbc.gridx = 1;
        txtPassengerName = new JTextField(15);
        panel.add(txtPassengerName, gbc);

        // Bus Number field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Bus Number:"), gbc);
        gbc.gridx = 1;
        txtBusNumber = new JTextField(15);
        panel.add(txtBusNumber, gbc);

        // Seats field
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Number of Seats:"), gbc);
        gbc.gridx = 1;
        txtSeats = new JTextField(15);
        panel.add(txtSeats, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));

        btnMakeReservation = new JButton("Make Reservation");
        btnMakeReservation.setBackground(new Color(144, 238, 144));
        btnMakeReservation.setForeground(Color.BLACK);
        btnMakeReservation.setFont(new Font("Arial", Font.BOLD, 12));
        btnMakeReservation.setFocusPainted(false);
        btnMakeReservation.addActionListener(e -> makeReservation());

        btnShowBuses = new JButton("Show Available Buses");
        btnShowBuses.setBackground(new Color(135, 206, 250));
        btnShowBuses.setForeground(Color.BLACK);
        btnShowBuses.setFont(new Font("Arial", Font.BOLD, 12));
        btnShowBuses.setFocusPainted(false);
        btnShowBuses.addActionListener(e -> showAvailableBuses());

        btnShowReservations = new JButton("Show Reservations");
        btnShowReservations.setBackground(new Color(255, 200, 100));
        btnShowReservations.setForeground(Color.BLACK);
        btnShowReservations.setFont(new Font("Arial", Font.BOLD, 12));
        btnShowReservations.setFocusPainted(false);
        btnShowReservations.addActionListener(e -> showReservations());

        buttonPanel.add(btnMakeReservation);
        buttonPanel.add(btnShowBuses);
        buttonPanel.add(btnShowReservations);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    /**
     * Creates the panel containing tables for buses and reservations
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Bus table
        String[] busColumns = { "Bus Number", "Bus Name", "Source", "Destination",
                "Departure", "Arrival", "Fare (₹)", "Total Seats", "Available Seats" };
        busTableModel = new DefaultTableModel(busColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableBuses = new JTable(busTableModel);
        tableBuses.setRowHeight(25);
        tableBuses.getTableHeader().setBackground(new Color(70, 130, 180));
        tableBuses.getTableHeader().setForeground(Color.WHITE);
        tableBuses.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        scrollBuses = new JScrollPane(tableBuses);
        scrollBuses.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Available Buses"));

        // Reservation table
        String[] reservationColumns = { "ID", "Passenger Name", "Bus Number", "Travel Date", "Seats" };
        reservationTableModel = new DefaultTableModel(reservationColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableReservations = new JTable(reservationTableModel);
        tableReservations.setRowHeight(25);
        tableReservations.getTableHeader().setBackground(new Color(255, 140, 0));
        tableReservations.getTableHeader().setForeground(Color.WHITE);
        tableReservations.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        scrollReservations = new JScrollPane(tableReservations);
        scrollReservations.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Reservations"));

        panel.add(scrollBuses);
        panel.add(scrollReservations);

        return panel;
    }

    /**
     * Validates input fields
     * 
     * @return true if all fields are valid, false otherwise
     */
    private boolean validateInput() {
        if (txtDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter travel date!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtPassengerName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter passenger name!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtBusNumber.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter bus number!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtSeats.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter number of seats!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate seats is a number
        try {
            int seats = Integer.parseInt(txtSeats.getText().trim());
            if (seats <= 0) {
                JOptionPane.showMessageDialog(this, "Number of seats must be greater than 0!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for seats!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Converts date from DD-MM-YYYY to YYYY-MM-DD format
     * 
     * @param dateStr Date string in DD-MM-YYYY format
     * @return Date string in YYYY-MM-DD format
     */
    private String convertDateFormat(String dateStr) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = inputFormat.parse(dateStr);
        return outputFormat.format(date);
    }

    /**
     * Makes a new reservation
     */
    private void makeReservation() {
        if (!validateInput()) {
            return;
        }

        String passengerName = txtPassengerName.getText().trim();
        String busNumber = txtBusNumber.getText().trim();
        String dateStr = txtDate.getText().trim();
        int seats = Integer.parseInt(txtSeats.getText().trim());

        try {
            // Convert date format
            String travelDate = convertDateFormat(dateStr);

            Connection conn = DatabaseConnection.getConnection();

            // Check if bus exists
            String checkBusQuery = "SELECT total_seats FROM bus_info WHERE bus_number = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkBusQuery);
            checkStmt.setString(1, busNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Bus number not found!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                rs.close();
                checkStmt.close();
                conn.close();
                return;
            }

            int totalSeats = rs.getInt("total_seats");
            rs.close();
            checkStmt.close();

            // Check available seats
            String availableSeatsQuery = "SELECT COALESCE(SUM(seats), 0) as booked FROM reservations " +
                    "WHERE bus_number = ? AND travel_date = ?";
            PreparedStatement availStmt = conn.prepareStatement(availableSeatsQuery);
            availStmt.setString(1, busNumber);
            availStmt.setString(2, travelDate);
            rs = availStmt.executeQuery();
            rs.next();
            int bookedSeats = rs.getInt("booked");
            int availableSeats = totalSeats - bookedSeats;
            rs.close();
            availStmt.close();

            if (seats > availableSeats) {
                JOptionPane.showMessageDialog(this,
                        "Not enough seats available! Only " + availableSeats + " seats remaining.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                conn.close();
                return;
            }

            // Insert reservation
            String insertQuery = "INSERT INTO reservations (passenger_name, bus_number, travel_date, seats) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, passengerName);
            insertStmt.setString(2, busNumber);
            insertStmt.setString(3, travelDate);
            insertStmt.setInt(4, seats);

            int rowsAffected = insertStmt.executeUpdate();
            insertStmt.close();
            conn.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this,
                        "Reservation successful!\nPassenger: " + passengerName +
                                "\nBus: " + busNumber + "\nSeats: " + seats,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear fields
                txtDate.setText("");
                txtPassengerName.setText("");
                txtBusNumber.setText("");
                txtSeats.setText("");

                // Refresh tables
                showAvailableBuses();
                showReservations();
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid date format! Please use DD-MM-YYYY format.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Displays all available buses with available seats calculation
     */
    private void showAvailableBuses() {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String query = "SELECT b.bus_number, b.bus_name, b.source, b.destination, " +
                    "b.departure_time, b.arrival_time, b.fare, b.total_seats, " +
                    "(b.total_seats - COALESCE(SUM(r.seats), 0)) as available_seats " +
                    "FROM bus_info b " +
                    "LEFT JOIN reservations r ON b.bus_number = r.bus_number " +
                    "GROUP BY b.bus_number, b.bus_name, b.source, b.destination, " +
                    "b.departure_time, b.arrival_time, b.fare, b.total_seats";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Clear existing rows
            busTableModel.setRowCount(0);

            // Add rows to table
            while (rs.next()) {
                Object[] row = {
                        rs.getString("bus_number"),
                        rs.getString("bus_name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getDouble("fare"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats")
                };
                busTableModel.addRow(row);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading bus data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Displays all reservations
     */
    private void showReservations() {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String query = "SELECT * FROM reservations ORDER BY id DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Clear existing rows
            reservationTableModel.setRowCount(0);

            // Add rows to table
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("passenger_name"),
                        rs.getString("bus_number"),
                        rs.getString("travel_date"),
                        rs.getInt("seats")
                };
                reservationTableModel.addRow(row);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading reservation data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Main method - Entry point of the application
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new BusReservationSystem());
    }
}
