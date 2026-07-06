public class Food {
    private int id;
    private String name;
    private int price;
    private String category;
    private String type;
    
    public Food(int id, String name, int price, String category, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.type = type;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getCategory() { return category; }
    public String getType() { return type; }
    
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setType(String type) { this.type = type; }
    
    @Override
    public String toString() {
        return name + " - ₹" + price;
    }
}