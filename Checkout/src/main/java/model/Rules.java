package model;

public class Rules {

    /**
     * Stores the rules for items
     * For item {id} when {quantity} reaches a certain threshold
     * the {price} is applied.
     */
    char id;
    int quantity;
    double price;

    public Rules(char id,double price, int quantity){
        this.id=id;
        this.quantity=quantity;
        this.price=price;
    }
    public char getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
