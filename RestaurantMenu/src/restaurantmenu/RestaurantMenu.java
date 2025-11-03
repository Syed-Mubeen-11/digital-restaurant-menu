import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RestaurantMenu extends JFrame {

    private JPanel menuPanel;
    private JPanel displayPanel;
    private JLabel imageLabel;
    private JLabel priceLabel; // Label to display price

    public RestaurantMenu() {
        setTitle("Restaurant Menu");
        setSize(800, 800);  // Increased size to fit more items
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu panel on the left for the food items
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(10, 1));  // Updated to 10 rows

        // Buttons for food items (10 menu items)
        JButton burgerButton = new JButton("Burger");
        JButton pizzaButton = new JButton("Pizza");
        JButton pastaButton = new JButton("Pasta");
        JButton saladButton = new JButton("Salad");
        JButton dessertButton = new JButton("Dessert");
        JButton sandwichButton = new JButton("Sandwich");
        JButton steakButton = new JButton("Steak");
        JButton soupButton = new JButton("Soup");
        JButton fishButton = new JButton("Sea-foods");
        JButton chickenButton = new JButton("Non-Veg");

        // Add action listeners to the buttons
        burgerButton.addActionListener(new FoodButtonListener("burger.jpg", "150"));
        pizzaButton.addActionListener(new FoodButtonListener("pizza.jpg", "300"));
        pastaButton.addActionListener(new FoodButtonListener("pasta.jpg", "250"));
        saladButton.addActionListener(new FoodButtonListener("salad.jpg", "100"));
        dessertButton.addActionListener(new FoodButtonListener("dessert.jpg", "180"));
        sandwichButton.addActionListener(new FoodButtonListener("sandwich.jpg", "120"));
        steakButton.addActionListener(new FoodButtonListener("steak.jpg", "200"));
        soupButton.addActionListener(new FoodButtonListener("soup.jpg", "80"));
        fishButton.addActionListener(new FoodButtonListener("fish.jpg", "350"));
        chickenButton.addActionListener(new FoodButtonListener("chicken.jpg", "220"));

        // Add buttons to the menu panel
        menuPanel.add(burgerButton);
        menuPanel.add(pizzaButton);
        menuPanel.add(pastaButton);
        menuPanel.add(saladButton);
        menuPanel.add(dessertButton);
        menuPanel.add(sandwichButton);
        menuPanel.add(steakButton);
        menuPanel.add(soupButton);
        menuPanel.add(fishButton);
        menuPanel.add(chickenButton);

        // Panel to display the image of the selected food
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);
        imageLabel = new JLabel("Select a food item");
        priceLabel = new JLabel(""); // Initially empty

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(imageLabel, BorderLayout.CENTER);
        displayPanel.add(priceLabel, BorderLayout.SOUTH); // Add the price label below the image

        // Add panels to the frame
        add(menuPanel, BorderLayout.WEST);
        add(displayPanel, BorderLayout.CENTER);

        // Set font for the price label (larger and bold)
        priceLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Arial, Bold, size 24
        priceLabel.setForeground(Color.RED); // Optional: set the color of the price text to red
    }

    // ActionListener for the food buttons
    private class FoodButtonListener implements ActionListener {
        private String imageName;
        private String price;

        public FoodButtonListener(String imageName, String price) {
            this.imageName = imageName;
            this.price = price;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Change the image and update the price when a food item is clicked
            ImageIcon imageIcon = null;
            String imagePath = "images/" + imageName;

            // Use java.io.File to check if the image exists
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                imageIcon = new ImageIcon(imagePath);  // Image found, load it
            } else {
                // If the image does not exist, use a default image or placeholder
                imageIcon = new ImageIcon("images/default.jpg");
            }

            // Resize the image to a larger size (e.g., 600x600 pixels)
            Image image = imageIcon.getImage(); // Get the image from ImageIcon
            Image scaledImage = image.getScaledInstance(600, 600, Image.SCALE_SMOOTH);  // Scale the image
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);  // Create new ImageIcon with scaled image

            // Set the scaled image to the label
            imageLabel.setIcon(scaledImageIcon);
            imageLabel.setText(""); // Clear text if there was any

            // Update the price label with the selected item's price
            priceLabel.setText("Price: Rs." + price);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RestaurantMenu frame = new RestaurantMenu();
                frame.setVisible(true);
            }
        });
    }
}
