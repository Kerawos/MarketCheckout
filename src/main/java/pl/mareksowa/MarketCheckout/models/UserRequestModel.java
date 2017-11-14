package pl.mareksowa.MarketCheckout.models;

public class UserRequestModel {

    private String itemName;
    private int quantity;

    public UserRequestModel() {
    }

    public UserRequestModel(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
