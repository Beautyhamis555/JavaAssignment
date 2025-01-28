# JavaAssignmentimport javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CafeManagementSystem {

    // Prices of items
    private static final double RICE_PRICE = 3000;
    private static final double UGALI_PRICE = 2000;
    private static final double TEA_PRICE = 1500;
    private static final double CHAPATI_PRICE = 1000;
    private static final double DONATI_PRICE = 500;
    private static final double BEANS_PRICE = 2500;
    private static final double MEAT_PRICE = 5000;
    private static final double MILK_PRICE = 2000;

    public static void main(String[] args) {
        // Define the path to the image folder
        String imageFolderPath = "C:\\Users\\hillarius\\Desktop\\images\\";

        // Ask customer type
        String[] customerTypes = {"Walk-in", "Booking"};
        String customerType = (String) JOptionPane.showInputDialog(
                null,
                "Are you a Walk-in or Booking customer?",
                "Customer Type Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                customerTypes,
                customerTypes[0]
        );

        if (customerType == null) {
            JOptionPane.showMessageDialog(null, "You must select a customer type to proceed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Additional input for booking customers
        String bookingId = null;
        String customerName = null;
        String customerLocation = null;
        String customerPhoneNumber = null;

        if (customerType.equals("Booking")) {
            // Request details for booking customers
            customerName = JOptionPane.showInputDialog(null, "Enter your Name:", "Booking Customer", JOptionPane.QUESTION_MESSAGE);
            if (customerName == null || customerName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            customerLocation = JOptionPane.showInputDialog(null, "Enter your Location:", "Booking Customer", JOptionPane.QUESTION_MESSAGE);
            if (customerLocation == null || customerLocation.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Location cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            customerPhoneNumber = JOptionPane.showInputDialog(null, "Enter your Phone Number:", "Booking Customer", JOptionPane.QUESTION_MESSAGE);
            if (customerPhoneNumber == null || customerPhoneNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Phone Number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Generate and display Booking ID
            bookingId = generateBookingId();
            JOptionPane.showMessageDialog(null, "Thank you for booking with us! Your Booking ID is: " + bookingId, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }

        // Create the frame
        JFrame frame = new JFrame("Rahabu's Cafe Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Minimized frame size
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Use BorderLayout for the main frame
        frame.setLayout(new BorderLayout(10, 10));

        // Header label
        JLabel headerLabel = new JLabel("Welcome to Rahabu's Cafe", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Adjusted font size
        frame.add(headerLabel, BorderLayout.NORTH);

        // Main panel with GridLayout for food items (adjusted rows and columns)
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new GridLayout(8, 3, 10, 10));  // Reduced space between items

        // Food items with images, quantity inputs, and labels for quantity
        JCheckBox riceCheckBox = new JCheckBox("Rice (TZS " + RICE_PRICE + ")");
        JTextField riceQuantity = new JTextField("0", 3);  // Reduced quantity field size
        JLabel riceImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "rice.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(riceCheckBox);
        foodPanel.add(riceImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(riceQuantity);

        JCheckBox ugaliCheckBox = new JCheckBox("Ugali (TZS " + UGALI_PRICE + ")");
        JTextField ugaliQuantity = new JTextField("0", 3);
        JLabel ugaliImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "ugali.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(ugaliCheckBox);
        foodPanel.add(ugaliImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(ugaliQuantity);

        JCheckBox teaCheckBox = new JCheckBox("Tea (TZS " + TEA_PRICE + ")");
        JTextField teaQuantity = new JTextField("0", 3);
        JLabel teaImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "tea.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(teaCheckBox);
        foodPanel.add(teaImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(teaQuantity);

        JCheckBox chapatiCheckBox = new JCheckBox("Chapati (TZS " + CHAPATI_PRICE + ")");
        JTextField chapatiQuantity = new JTextField("0", 3);
        JLabel chapatiImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "chapati.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(chapatiCheckBox);
        foodPanel.add(chapatiImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(chapatiQuantity);

        JCheckBox donatiCheckBox = new JCheckBox("Donati (TZS " + DONATI_PRICE + ")");
        JTextField donatiQuantity = new JTextField("0", 3);
        JLabel donatiImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "donati.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(donatiCheckBox);
        foodPanel.add(donatiImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(donatiQuantity);

        JCheckBox beansCheckBox = new JCheckBox("Beans (TZS " + BEANS_PRICE + ")");
        JTextField beansQuantity = new JTextField("0", 3);
        JLabel beansImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "beans.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(beansCheckBox);
        foodPanel.add(beansImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(beansQuantity);

        JCheckBox meatCheckBox = new JCheckBox("Meat (TZS " + MEAT_PRICE + ")");
        JTextField meatQuantity = new JTextField("0", 3);
        JLabel meatImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "meat.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(meatCheckBox);
        foodPanel.add(meatImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(meatQuantity);

        JCheckBox milkCheckBox = new JCheckBox("Milk (TZS " + MILK_PRICE + ")");
        JTextField milkQuantity = new JTextField("0", 3);
        JLabel milkImageLabel = new JLabel(new ImageIcon(new ImageIcon(imageFolderPath + "milk.jpeg").getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        foodPanel.add(milkCheckBox);
        foodPanel.add(milkImageLabel);
        foodPanel.add(new JLabel("Quantity:"));
        foodPanel.add(milkQuantity);

        // Scroll pane to hold the foodPanel
        JScrollPane scrollPane = new JScrollPane(foodPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for deposit and output
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));

        // Input field for deposit
        JPanel depositPanel = new JPanel();
        depositPanel.setLayout(new FlowLayout());
        JLabel depositLabel = new JLabel("Enter Deposit Amount (TZS):");
        JTextField depositField = new JTextField(10);  // Reduced deposit field size
        depositPanel.add(depositLabel);
        depositPanel.add(depositField);
        bottomPanel.add(depositPanel);

        // Calculate button
        JButton calculateButton = new JButton("Make payment");
        bottomPanel.add(calculateButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalCost = 0;
                ArrayList<OrderItem> orderedItems = new ArrayList<>();

                // Check if the user has entered a valid quantity for each selected item
                if (!isValidQuantity(riceCheckBox, riceQuantity) ||
                        !isValidQuantity(ugaliCheckBox, ugaliQuantity) ||
                        !isValidQuantity(teaCheckBox, teaQuantity) ||
                        !isValidQuantity(chapatiCheckBox, chapatiQuantity) ||
                        !isValidQuantity(donatiCheckBox, donatiQuantity) ||
                        !isValidQuantity(beansCheckBox, beansQuantity) ||
                        !isValidQuantity(meatCheckBox, meatQuantity) ||
                        !isValidQuantity(milkCheckBox, milkQuantity)) {
                    return; // Exit if any invalid quantity is detected
                }

                try {
                    // Check each item and add to the list if it's selected
                    if (riceCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(riceQuantity.getText());
                        totalCost += RICE_PRICE * quantity;
                        orderedItems.add(new OrderItem("Rice", quantity, RICE_PRICE));
                    }
                    if (ugaliCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(ugaliQuantity.getText());
                        totalCost += UGALI_PRICE * quantity;
                        orderedItems.add(new OrderItem("Ugali", quantity, UGALI_PRICE));
                    }
                    if (teaCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(teaQuantity.getText());
                        totalCost += TEA_PRICE * quantity;
                        orderedItems.add(new OrderItem("Tea", quantity, TEA_PRICE));
                    }
                    if (chapatiCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(chapatiQuantity.getText());
                        totalCost += CHAPATI_PRICE * quantity;
                        orderedItems.add(new OrderItem("Chapati", quantity, CHAPATI_PRICE));
                    }
                    if (donatiCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(donatiQuantity.getText());
                        totalCost += DONATI_PRICE * quantity;
                        orderedItems.add(new OrderItem("Donati", quantity, DONATI_PRICE));
                    }
                    if (beansCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(beansQuantity.getText());
                        totalCost += BEANS_PRICE * quantity;
                        orderedItems.add(new OrderItem("Beans", quantity, BEANS_PRICE));
                    }
                    if (meatCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(meatQuantity.getText());
                        totalCost += MEAT_PRICE * quantity;
                        orderedItems.add(new OrderItem("Meat", quantity, MEAT_PRICE));
                    }
                    if (milkCheckBox.isSelected()) {
                        int quantity = Integer.parseInt(milkQuantity.getText());
                        totalCost += MILK_PRICE * quantity;
                        orderedItems.add(new OrderItem("Milk", quantity, MILK_PRICE));
                    }

                    // Sort the ordered items by name
                    Collections.sort(orderedItems, Comparator.comparing(OrderItem::getItemName));

                    // Display ordered items in a message dialog
                    StringBuilder orderSummary = new StringBuilder("Your Order:\n\n");
                    for (OrderItem item : orderedItems) {
                        orderSummary.append(item.getItemName())
                                .append(" - Quantity: ").append(item.getQuantity())
                                .append(" - Total: TZS ").append(item.getTotalPrice()).append("\n");
                    }

                    orderSummary.append("\nTotal Cost: TZS ").append(totalCost);

                    // Check if deposit is sufficient
                    double depositAmount = Double.parseDouble(depositField.getText());
                    if (depositAmount >= totalCost) {
                        double change = depositAmount - totalCost;
                        orderSummary.append("\nDeposit: TZS ").append(depositAmount);
                        orderSummary.append("\nChange: TZS ").append(change);
                        JOptionPane.showMessageDialog(null, orderSummary.toString(), "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);

                        // Show thank you message
                        String thankYouMessage = "Thank you for your purchase! We appreciate your visit and hope to see you again soon!";
                        JOptionPane.showMessageDialog(null, thankYouMessage, "Thank You!", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient deposit! Please enter a larger amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid quantities and deposit amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the frame visible
        frame.setVisible(true);
    }

    // Method to check if the entered quantity is valid
    private static boolean isValidQuantity(JCheckBox checkBox, JTextField quantityField) {
        if (checkBox.isSelected()) {
            try {
                Integer.parseInt(quantityField.getText()); // Check if quantity is a valid number
                return true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid quantity for " + checkBox.getText(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    // Generate a unique booking ID (for simplicity, using a random number)
    private static String generateBookingId() {
        return "BOOK" + (int) (Math.random() * 10000);
    }
}

// OrderItem class to represent ordered food items
class OrderItem {
    private String itemName;
    private int quantity;
    private double price;

    public OrderItem(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return quantity * price;
    }
}
