package birds.conservatory;

import birds.enums.Enums;

// a type of food and its quantity
public class Foods {
    private Enums.Food food;
    private int quantity;

    public Foods(Enums.Food food, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        this.food = food;
        this.quantity = quantity;
    }

    public Enums.Food getFood() {
        return this.food;
    }

    public int getQuantity() {
        return this.quantity;
    }
}