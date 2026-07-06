import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class UI {
    private JFrame frame;
    private Cart cart;
    private String customerName;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel menuPanel;
    private JPanel cartPanel;
    private JTextArea billArea;
    private ArrayList<Food> allFoodItems;
    
    // Track current filters
    private String currentCategoryFilter = "All";
    private String currentTypeFilter = "All";  // "All", "Veg", "Non-Veg"
    
    // Store filter buttons for highlighting
    private JButton allTypesBtn;
    private JButton vegOnlyBtn;
    private JButton nonVegOnlyBtn;
    private HashMap<String, JButton> categoryButtons = new HashMap<>();
    
    // Colors
    private final Color BG_COLOR = new Color(18, 18, 18);
    private final Color PANEL_COLOR = new Color(45, 45, 45);
    private final Color BUTTON_COLOR = new Color(60, 60, 60);
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color ACCENT_COLOR = new Color(0, 120, 215);
    private final Color VEG_COLOR = new Color(0, 150, 0);
    private final Color NONVEG_COLOR = new Color(180, 50, 50);
    
    public UI() {
        cart = new Cart();
        loadFoodItems();
        initialize();
    }
    
    private void loadFoodItems() {
        try {
            allFoodItems = DBConnection.getAllFoodItems();
            DBConnection.debugShowAllCategories();
            
            if (allFoodItems == null || allFoodItems.isEmpty()) {
                throw new Exception("No data from database");
            }
            
            boolean hasDinner = false;
            for (Food f : allFoodItems) {
                if (f.getCategory().equals("Dinner")) {
                    hasDinner = true;
                    break;
                }
            }
            
            if (!hasDinner) {
                System.out.println("Adding dinner items to existing data...");
                addDinnerItemsToCollection();
            }
            
            System.out.println("Total items loaded: " + allFoodItems.size());
        } catch (Exception e) {
            System.out.println("Using sample data: " + e.getMessage());
            allFoodItems = getCompleteFoodItems();
        }
    }
    
    private void addDinnerItemsToCollection() {
        int nextId = allFoodItems.size() + 1;
        
        String[][] dinnerItems = {
            {"Paneer Butter Masala", "180", "Dinner", "Veg"},
            {"Kadai Paneer", "170", "Dinner", "Veg"},
            {"Shahi Paneer", "190", "Dinner", "Veg"},
            {"Dal Makhani", "150", "Dinner", "Veg"},
            {"Mix Veg Curry", "140", "Dinner", "Veg"},
            {"Malai Kofta", "180", "Dinner", "Veg"},
            {"Palak Paneer", "170", "Dinner", "Veg"},
            {"Butter Chicken", "220", "Dinner", "Non-Veg"},
            {"Chicken Curry", "200", "Dinner", "Non-Veg"},
            {"Chicken Korma", "210", "Dinner", "Non-Veg"},
            {"Egg Curry", "140", "Dinner", "Non-Veg"},
            {"Mutton Curry", "280", "Dinner", "Non-Veg"},
            {"Fish Curry", "240", "Dinner", "Non-Veg"},
            {"Veg Biryani", "160", "Dinner", "Veg"},
            {"Paneer Biryani", "180", "Dinner", "Veg"},
            {"Chicken Biryani", "220", "Dinner", "Non-Veg"},
            {"Mutton Biryani", "260", "Dinner", "Non-Veg"},
            {"Jeera Rice", "120", "Dinner", "Veg"},
            {"Steam Rice", "100", "Dinner", "Veg"},
            {"Butter Roti", "25", "Dinner", "Veg"},
            {"Plain Naan", "40", "Dinner", "Veg"},
            {"Butter Naan", "50", "Dinner", "Veg"},
            {"Garlic Naan", "60", "Dinner", "Veg"},
            {"Tandoori Roti", "30", "Dinner", "Veg"},
            {"Sweet Lassi", "60", "Dinner", "Veg"},
            {"Salted Lassi", "60", "Dinner", "Veg"},
            {"Buttermilk", "40", "Dinner", "Veg"},
            {"Soft Drink", "40", "Dinner", "Veg"}
        };
        
        for (String[] item : dinnerItems) {
            allFoodItems.add(new Food(nextId++, item[0], Integer.parseInt(item[1]), item[2], item[3]));
        }
        
        System.out.println("Added " + dinnerItems.length + " dinner items");
    }
    
    private ArrayList<Food> getCompleteFoodItems() {
        ArrayList<Food> items = new ArrayList<>();
        int id = 1;
        
        String[][] allItems = {
            {"Poha", "40", "Breakfast", "Veg"},
            {"Aloo Paratha", "70", "Breakfast", "Veg"},
            {"Paneer Paratha", "90", "Breakfast", "Veg"},
            {"Idli", "50", "Breakfast", "Veg"},
            {"Masala Dosa", "80", "Breakfast", "Veg"},
            {"Plain Dosa", "70", "Breakfast", "Veg"},
            {"Upma", "60", "Breakfast", "Veg"},
            {"Veg Sandwich", "60", "Breakfast", "Veg"},
            {"Omelette", "50", "Breakfast", "Non-Veg"},
            {"Boiled Eggs", "40", "Breakfast", "Non-Veg"},
            {"Egg Bhurji", "70", "Breakfast", "Non-Veg"},
            {"Egg Paratha", "80", "Breakfast", "Non-Veg"},
            {"Chicken Sandwich", "120", "Breakfast", "Non-Veg"},
            {"French Fries", "90", "Snacks", "Veg"},
            {"Veg Pakora", "80", "Snacks", "Veg"},
            {"Paneer Pakora", "120", "Snacks", "Veg"},
            {"Samosa", "40", "Snacks", "Veg"},
            {"Spring Rolls", "100", "Snacks", "Veg"},
            {"Veg Momos", "90", "Snacks", "Veg"},
            {"Cheese Balls", "130", "Snacks", "Veg"},
            {"Chicken Momos", "120", "Snacks", "Non-Veg"},
            {"Chicken Nuggets", "150", "Snacks", "Non-Veg"},
            {"Chicken Pakora", "140", "Snacks", "Non-Veg"},
            {"Chicken Spring Rolls", "160", "Snacks", "Non-Veg"},
            {"Veg Burger", "80", "Fast Food", "Veg"},
            {"Cheese Burger", "100", "Fast Food", "Veg"},
            {"Veg Pizza Small", "150", "Fast Food", "Veg"},
            {"Veg Pizza Medium", "250", "Fast Food", "Veg"},
            {"Paneer Pizza", "300", "Fast Food", "Veg"},
            {"White Sauce Pasta", "180", "Fast Food", "Veg"},
            {"Red Sauce Pasta", "160", "Fast Food", "Veg"},
            {"Chicken Burger", "120", "Fast Food", "Non-Veg"},
            {"Chicken Pizza Small", "200", "Fast Food", "Non-Veg"},
            {"Chicken Pizza Medium", "350", "Fast Food", "Non-Veg"},
            {"Chicken Pasta", "220", "Fast Food", "Non-Veg"},
            {"Dal Tadka", "120", "Lunch", "Veg"},
            {"Dal Makhani", "150", "Lunch", "Veg"},
            {"Shahi Paneer", "180", "Lunch", "Veg"},
            {"Kadai Paneer", "170", "Lunch", "Veg"},
            {"Mix Veg", "130", "Lunch", "Veg"},
            {"Jeera Rice", "90", "Lunch", "Veg"},
            {"Plain Rice", "70", "Lunch", "Veg"},
            {"Veg Thali", "220", "Lunch", "Veg"},
            {"Butter Chicken", "280", "Lunch", "Non-Veg"},
            {"Chicken Curry", "240", "Lunch", "Non-Veg"},
            {"Chicken Biryani", "200", "Lunch", "Non-Veg"},
            {"Egg Curry", "140", "Lunch", "Non-Veg"},
            {"Egg Biryani", "160", "Lunch", "Non-Veg"},
            {"Non-Veg Thali", "300", "Lunch", "Non-Veg"},
            {"Paneer Butter Masala", "180", "Dinner", "Veg"},
            {"Kadai Paneer", "170", "Dinner", "Veg"},
            {"Shahi Paneer", "190", "Dinner", "Veg"},
            {"Mix Veg Curry", "140", "Dinner", "Veg"},
            {"Malai Kofta", "180", "Dinner", "Veg"},
            {"Palak Paneer", "170", "Dinner", "Veg"},
            {"Butter Chicken", "220", "Dinner", "Non-Veg"},
            {"Chicken Curry", "200", "Dinner", "Non-Veg"},
            {"Chicken Korma", "210", "Dinner", "Non-Veg"},
            {"Mutton Curry", "280", "Dinner", "Non-Veg"},
            {"Fish Curry", "240", "Dinner", "Non-Veg"},
            {"Veg Biryani", "160", "Dinner", "Veg"},
            {"Paneer Biryani", "180", "Dinner", "Veg"},
            {"Chicken Biryani", "220", "Dinner", "Non-Veg"},
            {"Mutton Biryani", "260", "Dinner", "Non-Veg"},
            {"Jeera Rice", "120", "Dinner", "Veg"},
            {"Steam Rice", "100", "Dinner", "Veg"},
            {"Butter Roti", "25", "Dinner", "Veg"},
            {"Plain Naan", "40", "Dinner", "Veg"},
            {"Butter Naan", "50", "Dinner", "Veg"},
            {"Garlic Naan", "60", "Dinner", "Veg"},
            {"Tandoori Roti", "30", "Dinner", "Veg"},
            {"Sweet Lassi", "60", "Dinner", "Veg"},
            {"Salted Lassi", "60", "Dinner", "Veg"},
            {"Buttermilk", "40", "Dinner", "Veg"},
            {"Soft Drink", "40", "Dinner", "Veg"},
            {"Coca-Cola", "40", "Beverages", "Veg"},
            {"Pepsi", "40", "Beverages", "Veg"},
            {"Sprite", "40", "Beverages", "Veg"},
            {"Fanta", "40", "Beverages", "Veg"},
            {"Tea", "20", "Beverages", "Veg"},
            {"Coffee", "40", "Beverages", "Veg"},
            {"Cappuccino", "80", "Beverages", "Veg"},
            {"Latte", "90", "Beverages", "Veg"},
            {"Mango Shake", "120", "Beverages", "Veg"},
            {"Chocolate Shake", "140", "Beverages", "Veg"},
            {"Strawberry Shake", "130", "Beverages", "Veg"},
            {"Banana Shake", "100", "Beverages", "Veg"},
            {"Orange Juice", "90", "Beverages", "Veg"},
            {"Mixed Fruit Juice", "110", "Beverages", "Veg"},
            {"Gulab Jamun", "50", "Desserts", "Veg"},
            {"Rasgulla", "50", "Desserts", "Veg"},
            {"Ice Cream", "80", "Desserts", "Veg"},
            {"Brownie", "120", "Desserts", "Veg"},
            {"Chocolate Lava Cake", "150", "Desserts", "Veg"}
        };
        
        for (String[] item : allItems) {
            items.add(new Food(id++, item[0], Integer.parseInt(item[1]), item[2], item[3]));
        }
        
        return items;
    }
    
    private void initialize() {
        frame = new JFrame("Restaurant Food Ordering System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 750);
        frame.setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(BG_COLOR);
        
        createLoginScreen();
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private void createLoginScreen() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(BG_COLOR);
        loginPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel titleLabel = new JLabel("Welcome to Restaurant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);
        
        JLabel subLabel = new JLabel("Food Ordering System");
        subLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subLabel.setForeground(new Color(200, 200, 200));
        gbc.gridy = 1;
        loginPanel.add(subLabel, gbc);
        
        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setForeground(TEXT_COLOR);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        loginPanel.add(nameLabel, gbc);
        
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setBackground(PANEL_COLOR);
        nameField.setForeground(TEXT_COLOR);
        nameField.setCaretColor(TEXT_COLOR);
        nameField.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR));
        gbc.gridx = 1;
        loginPanel.add(nameField, gbc);
        
        JButton enterBtn = createStyledButton("Enter Restaurant", 16);
        enterBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name!");
                return;
            }
            customerName = name;
            createMenuScreen();
            createCartScreen();
            cardLayout.show(mainPanel, "MENU");
        });
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        loginPanel.add(enterBtn, gbc);
        
        mainPanel.add(loginPanel, "LOGIN");
    }
    
    private void createMenuScreen() {
        menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(BG_COLOR);
        
        // Top panel with filters
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(PANEL_COLOR);
        
        // Category filter row
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.setBackground(PANEL_COLOR);
        categoryPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        
        JLabel categoryLabel = new JLabel("Categories: ");
        categoryLabel.setForeground(TEXT_COLOR);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        categoryPanel.add(categoryLabel);
        
        String[] categories = {"All", "Breakfast", "Snacks", "Fast Food", "Lunch", "Dinner", "Beverages", "Desserts"};
        
        for (String category : categories) {
            JButton catBtn = createStyledButton(category, 12);
            catBtn.addActionListener(e -> {
                currentCategoryFilter = category;
                applyFilters();
                // Highlight selected category
                for (JButton btn : categoryButtons.values()) {
                    btn.setBackground(BUTTON_COLOR);
                }
                catBtn.setBackground(ACCENT_COLOR);
            });
            categoryPanel.add(catBtn);
            categoryButtons.put(category, catBtn);
        }
        
        // Type filter row (Veg/Non-Veg)
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setBackground(PANEL_COLOR);
        typePanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        
        JLabel typeLabel = new JLabel("Food Type: ");
        typeLabel.setForeground(TEXT_COLOR);
        typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        typePanel.add(typeLabel);
        
        // All button
        allTypesBtn = createStyledButton("All Types", 12);
        allTypesBtn.setBackground(ACCENT_COLOR);
        allTypesBtn.addActionListener(e -> {
            currentTypeFilter = "All";
            applyFilters();
            updateTypeButtonHighlights();
        });
        typePanel.add(allTypesBtn);
        
        // Veg button
        vegOnlyBtn = createStyledButton("🥬 Veg Only", 12);
        vegOnlyBtn.setBackground(VEG_COLOR);
        vegOnlyBtn.addActionListener(e -> {
            currentTypeFilter = "Veg";
            applyFilters();
            updateTypeButtonHighlights();
        });
        typePanel.add(vegOnlyBtn);
        
        // Non-Veg button
        nonVegOnlyBtn = createStyledButton("🍗 Non-Veg Only", 12);
        nonVegOnlyBtn.setBackground(NONVEG_COLOR);
        nonVegOnlyBtn.addActionListener(e -> {
            currentTypeFilter = "Non-Veg";
            applyFilters();
            updateTypeButtonHighlights();
        });
        typePanel.add(nonVegOnlyBtn);
        
        // Cart button
        JPanel cartButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cartButtonPanel.setBackground(PANEL_COLOR);
        cartButtonPanel.setBorder(new EmptyBorder(0, 0, 10, 10));
        
        JButton cartBtn = createStyledButton("🛒 View Cart (" + cart.getCartItems().size() + ")", 12);
        cartBtn.setBackground(new Color(0, 100, 0));
        cartBtn.addActionListener(e -> {
            updateCartDisplay();
            cardLayout.show(mainPanel, "CART");
        });
        cartButtonPanel.add(cartBtn);
        
        topPanel.add(categoryPanel);
        topPanel.add(typePanel);
        topPanel.add(cartButtonPanel);
        
        menuPanel.add(topPanel, BorderLayout.NORTH);
        
        // Center panel for menu items
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(BG_COLOR);
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        menuPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(menuPanel, "MENU");
        
        // Initial load
        applyFilters();
    }
    
    private void updateTypeButtonHighlights() {
        allTypesBtn.setBackground(currentTypeFilter.equals("All") ? ACCENT_COLOR : BUTTON_COLOR);
        vegOnlyBtn.setBackground(currentTypeFilter.equals("Veg") ? ACCENT_COLOR : VEG_COLOR);
        nonVegOnlyBtn.setBackground(currentTypeFilter.equals("Non-Veg") ? ACCENT_COLOR : NONVEG_COLOR);
    }
    
    private void applyFilters() {
        if (menuPanel == null) return;
        
        JScrollPane scrollPane = (JScrollPane) menuPanel.getComponent(1);
        JPanel centerPanel = (JPanel) scrollPane.getViewport().getView();
        centerPanel.removeAll();
        
        ArrayList<Food> items = new ArrayList<>();
        
        // First filter by category and type
        for (Food f : allFoodItems) {
            boolean categoryMatch = currentCategoryFilter.equals("All") || f.getCategory().equals(currentCategoryFilter);
            boolean typeMatch = currentTypeFilter.equals("All") || f.getType().equals(currentTypeFilter);
            
            if (categoryMatch && typeMatch) {
                items.add(f);
            }
        }
        
        System.out.println("Showing " + items.size() + " items - Category: " + currentCategoryFilter + ", Type: " + currentTypeFilter);
        
        if (items.isEmpty()) {
            JLabel emptyLabel = new JLabel("No items match the selected filters!");
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 18));
            emptyLabel.setForeground(TEXT_COLOR);
            centerPanel.add(emptyLabel);
        } else {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            
            int row = 0;
            int col = 0;
            
            for (Food food : items) {
                JPanel itemCard = createItemCard(food);
                gbc.gridx = col;
                gbc.gridy = row;
                centerPanel.add(itemCard, gbc);
                
                col++;
                if (col >= 3) {
                    col = 0;
                    row++;
                }
            }
        }
        
        centerPanel.revalidate();
        centerPanel.repaint();
        updateCartButton();
    }
    
    private void updateCartButton() {
        if (menuPanel != null) {
            JPanel topPanel = (JPanel) menuPanel.getComponent(0);
            JPanel cartButtonPanel = (JPanel) topPanel.getComponent(2);
            for (Component comp : cartButtonPanel.getComponents()) {
                if (comp instanceof JButton && ((JButton) comp).getText().contains("Cart")) {
                    ((JButton) comp).setText("🛒 View Cart (" + cart.getCartItems().size() + ")");
                    break;
                }
            }
        }
    }
    
    private JPanel createItemCard(Food food) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(PANEL_COLOR);
        
        // Set border color based on type
        Color borderColor = food.getType().equals("Veg") ? VEG_COLOR : NONVEG_COLOR;
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(300, 120));
        
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        infoPanel.setBackground(PANEL_COLOR);
        
        JLabel nameLabel = new JLabel(food.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(TEXT_COLOR);
        
        JLabel priceLabel = new JLabel("₹" + food.getPrice());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(new Color(255, 200, 100));
        
        // Type indicator with color
        JLabel typeLabel = new JLabel((food.getType().equals("Veg") ? "🥬 " : "🍗 ") + food.getCategory());
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        typeLabel.setForeground(food.getType().equals("Veg") ? new Color(100, 255, 100) : new Color(255, 150, 150));
        
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(typeLabel);
        
        JButton addBtn = new JButton("Add to Cart");
        addBtn.setBackground(ACCENT_COLOR);
        addBtn.setForeground(TEXT_COLOR);
        addBtn.setFocusPainted(false);
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));
        addBtn.addActionListener(e -> {
            cart.addItem(food);
            updateCartButton();
            JOptionPane.showMessageDialog(frame, food.getName() + " added to cart!");
        });
        
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(addBtn, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void createCartScreen() {
        cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(BG_COLOR);
        mainPanel.add(cartPanel, "CART");
        updateCartDisplay();
    }
    
    private void updateCartDisplay() {
        cartPanel.removeAll();
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(PANEL_COLOR);
        
        JButton backBtn = createStyledButton("← Back to Menu", 14);
        backBtn.addActionListener(e -> {
            updateCartButton();
            cardLayout.show(mainPanel, "MENU");
        });
        
        JButton checkoutBtn = createStyledButton("Proceed to Bill →", 14);
        checkoutBtn.setBackground(new Color(0, 100, 0));
        checkoutBtn.addActionListener(e -> showBillScreen());
        
        buttonPanel.add(backBtn);
        buttonPanel.add(checkoutBtn);
        
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(BG_COLOR);
        itemsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        ArrayList<Cart.CartItem> cartItems = cart.getCartItems();
        
        if (cartItems.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty!");
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 24));
            emptyLabel.setForeground(TEXT_COLOR);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemsPanel.add(emptyLabel);
        } else {
            JPanel headerPanel = new JPanel(new GridLayout(1, 4));
            headerPanel.setBackground(PANEL_COLOR);
            headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            
            JLabel itemLabel = new JLabel("Item");
            itemLabel.setForeground(TEXT_COLOR);
            itemLabel.setFont(new Font("Arial", Font.BOLD, 14));
            JLabel qtyLabel = new JLabel("Quantity");
            qtyLabel.setForeground(TEXT_COLOR);
            qtyLabel.setFont(new Font("Arial", Font.BOLD, 14));
            JLabel priceLabel = new JLabel("Price");
            priceLabel.setForeground(TEXT_COLOR);
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            JLabel actionLabel = new JLabel("Action");
            actionLabel.setForeground(TEXT_COLOR);
            actionLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            headerPanel.add(itemLabel);
            headerPanel.add(qtyLabel);
            headerPanel.add(priceLabel);
            headerPanel.add(actionLabel);
            itemsPanel.add(headerPanel);
            itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            
            for (Cart.CartItem item : cartItems) {
                JPanel itemRow = createCartItemRow(item);
                itemsPanel.add(itemRow);
                itemsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
            
            JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            totalPanel.setBackground(PANEL_COLOR);
            totalPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            
            JLabel totalLabel = new JLabel("TOTAL: ₹" + cart.getTotal());
            totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
            totalLabel.setForeground(new Color(255, 200, 100));
            totalPanel.add(totalLabel);
            itemsPanel.add(totalPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        
        cartPanel.add(buttonPanel, BorderLayout.NORTH);
        cartPanel.add(scrollPane, BorderLayout.CENTER);
        
        cartPanel.revalidate();
        cartPanel.repaint();
    }
    
    private JPanel createCartItemRow(Cart.CartItem item) {
        JPanel row = new JPanel(new GridLayout(1, 4));
        row.setBackground(PANEL_COLOR);
        row.setBorder(new EmptyBorder(10, 10, 10, 10));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        Food food = item.getFood();
        
        JLabel nameLabel = new JLabel(food.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setForeground(TEXT_COLOR);
        
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        quantityPanel.setBackground(PANEL_COLOR);
        
        JButton minusBtn = new JButton("-");
        minusBtn.setBackground(BUTTON_COLOR);
        minusBtn.setForeground(TEXT_COLOR);
        minusBtn.setFocusPainted(false);
        minusBtn.addActionListener(e -> {
            cart.updateQuantity(food.getId(), item.getQuantity() - 1);
            updateCartDisplay();
        });
        
        JLabel qtyLabel = new JLabel(String.valueOf(item.getQuantity()));
        qtyLabel.setForeground(TEXT_COLOR);
        qtyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        qtyLabel.setPreferredSize(new Dimension(40, 30));
        qtyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton plusBtn = new JButton("+");
        plusBtn.setBackground(BUTTON_COLOR);
        plusBtn.setForeground(TEXT_COLOR);
        plusBtn.setFocusPainted(false);
        plusBtn.addActionListener(e -> {
            cart.updateQuantity(food.getId(), item.getQuantity() + 1);
            updateCartDisplay();
        });
        
        quantityPanel.add(minusBtn);
        quantityPanel.add(qtyLabel);
        quantityPanel.add(plusBtn);
        
        JLabel priceLabel = new JLabel("₹" + (food.getPrice() * item.getQuantity()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(255, 200, 100));
        
        JButton removeBtn = new JButton("Remove");
        removeBtn.setBackground(new Color(150, 0, 0));
        removeBtn.setForeground(TEXT_COLOR);
        removeBtn.setFocusPainted(false);
        removeBtn.addActionListener(e -> {
            cart.removeItem(food.getId());
            updateCartDisplay();
        });
        
        row.add(nameLabel);
        row.add(quantityPanel);
        row.add(priceLabel);
        row.add(removeBtn);
        
        return row;
    }
    
    private void showBillScreen() {
        if (cart.getCartItems().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Cart is empty! Add some items first.");
            return;
        }
        
        JPanel billPanel = new JPanel(new BorderLayout());
        billPanel.setBackground(BG_COLOR);
        
        Order order = new Order(customerName, cart.getCartItems(), cart.getTotal());
        billArea = new JTextArea(order.generateBill());
        billArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        billArea.setBackground(PANEL_COLOR);
        billArea.setForeground(TEXT_COLOR);
        billArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(billArea);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(PANEL_COLOR);
        
        JButton backBtn = createStyledButton("← Back to Cart", 14);
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "CART"));
        
        JButton placeOrderBtn = createStyledButton("✓ Place Order", 14);
        placeOrderBtn.setBackground(new Color(0, 100, 0));
        placeOrderBtn.addActionListener(e -> {
            int orderId = DBConnection.saveOrder(customerName, cart.getTotal());
            JOptionPane.showMessageDialog(frame, 
                "✅ Order placed successfully!\nOrder ID: " + orderId + 
                "\nCustomer: " + customerName + "\nTotal: ₹" + cart.getTotal());
            cart.clear();
            updateCartButton();
            cardLayout.show(mainPanel, "MENU");
            applyFilters();
        });
        
        buttonPanel.add(backBtn);
        buttonPanel.add(placeOrderBtn);
        
        billPanel.add(scrollPane, BorderLayout.CENTER);
        billPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(billPanel, "BILL");
        cardLayout.show(mainPanel, "BILL");
    }
    
    private JButton createStyledButton(String text, int fontSize) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR),
            new EmptyBorder(8, 20, 8, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}