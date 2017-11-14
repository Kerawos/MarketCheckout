package pl.mareksowa.MarketCheckout.models;

public class ItemModel {

    private String name;
    private int price; // instead of double - this is not absolutely nailed discount calculation logic ;)
    private int unit;
    private int specialPrice;

    public ItemModel(String name, int price, int unit, int specialPrice) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.specialPrice = specialPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }
}
