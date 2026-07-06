import java.util.*;

public class Cart {
    private HashMap<Integer, CartItem> items;
    
    public Cart() {
        items = new HashMap<>();
    }
    
    public void addItem(Food food) {
        int id = food.getId();
        if (items.containsKey(id)) {
            CartItem item = items.get(id);
            item.setQuantity(item.getQuantity() + 1);
        } else {
            items.put(id, new CartItem(food, 1));
        }
    }
    
    public void removeItem(int foodId) {
        items.remove(foodId);
    }
    
    public void updateQuantity(int foodId, int quantity) {
        if (quantity <= 0) {
            removeItem(foodId);
        } else if (items.containsKey(foodId)) {
            items.get(foodId).setQuantity(quantity);
        }
    }
    
    public ArrayList<CartItem> getCartItems() {
        return new ArrayList<>(items.values());
    }
    
    public int getTotal() {
        int total = 0;
        for (CartItem item : items.values()) {
            total += item.getFood().getPrice() * item.getQuantity();
        }
        return total;
    }
    
    public void clear() {
        items.clear();
    }
    
    public static class CartItem {
        private Food food;
        private int quantity;
        
        public CartItem(Food food, int quantity) {
            this.food = food;
            this.quantity = quantity;
        }
        
        public Food getFood() { return food; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}