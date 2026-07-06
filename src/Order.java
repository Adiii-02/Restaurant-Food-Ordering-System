import java.util.*;

public class Order {
    private int orderId;
    private String customerName;
    private ArrayList<Cart.CartItem> items;
    private int totalAmount;
    private Date orderDate;
    
    public Order(String customerName, ArrayList<Cart.CartItem> items, int totalAmount) {
        this.customerName = customerName;
        this.items = new ArrayList<>(items);
        this.totalAmount = totalAmount;
        this.orderDate = new Date();
    }
    
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public ArrayList<Cart.CartItem> getItems() { return items; }
    public int getTotalAmount() { return totalAmount; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public String generateBill() {
        StringBuilder bill = new StringBuilder();
        bill.append("=".repeat(50)).append("\n");
        bill.append("            RESTAURANT BILL\n");
        bill.append("=".repeat(50)).append("\n");
        bill.append("Customer: ").append(customerName).append("\n");
        bill.append("Date: ").append(orderDate).append("\n");
        bill.append("-".repeat(50)).append("\n");
        bill.append(String.format("%-30s %10s %10s\n", "Item", "Qty", "Price"));
        bill.append("-".repeat(50)).append("\n");
        
        for (Cart.CartItem item : items) {
            Food food = item.getFood();
            int qty = item.getQuantity();
            int price = food.getPrice();
            bill.append(String.format("%-30s %10d %10d\n", 
                food.getName(), qty, price * qty));
        }
        
        bill.append("-".repeat(50)).append("\n");
        bill.append(String.format("%-30s %10s %10d\n", "TOTAL", "", totalAmount));
        bill.append("=".repeat(50)).append("\n");
        bill.append("      Thank you! Visit Again!\n");
        bill.append("=".repeat(50)).append("\n");
        
        return bill.toString();
    }
}