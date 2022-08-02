package model;

public class Item {

    char itemId;
    double price;
    int quantity;

    public Item (char item,double price,int quantity){
        this.itemId=item;
        this.price=price;
        this.quantity=quantity;
    }

    public char getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
