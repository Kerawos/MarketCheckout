package pl.mareksowa.MarketCheckout.models.dao;

import pl.mareksowa.MarketCheckout.models.ItemModel;

import java.util.List;

public interface ItemModelDao {
    List<ItemModel> getAllItems();
    int checkPrise(String itemName, int itemQuantity);
    boolean isItemInDatabase(String itemName); //scan item
//    void updateItemPrice(String itemName, int newPrice);
//    void updateItemSpecialPrice(String itemName, int newSpecialPrice);
//    void updateItemUnit(String itemName, int neUnit);
}
