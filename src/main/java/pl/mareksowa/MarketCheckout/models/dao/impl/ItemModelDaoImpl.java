package pl.mareksowa.MarketCheckout.models.dao.impl;

import pl.mareksowa.MarketCheckout.models.ItemModel;
import pl.mareksowa.MarketCheckout.models.dao.ItemModelDao;

import java.util.ArrayList;
import java.util.List;

public class ItemModelDaoImpl implements ItemModelDao {

    private List<ItemModel> items; // working as a database in this example here

    public ItemModelDaoImpl() {
        //generate potential items like from database
        items = new ArrayList<>();
        items.add(new ItemModel("A", 40, 3, 70));
        items.add(new ItemModel("B", 10, 2, 15));
        items.add(new ItemModel("C", 30, 4, 60));
        items.add(new ItemModel("D", 25, 2, 40));
    }

    @Override
    public List<ItemModel> getAllItems() {
        return items;
    }


    @Override
    public int checkPrise(String itemName, String itemQty) {
        int itemQuantity = parseToInt(itemQty);
        if (itemQuantity < 0 || itemQuantity > Integer.MAX_VALUE){
            throw new IllegalArgumentException("Item quantity should be between 0 - billion");
        }
        if (itemName==null || !isItemInDatabase(itemName)){
            throw new IllegalArgumentException("There is no '" + itemName + "' in database..");
        }
        int regularPrice = 0;
        int unitPrice = 0;
        for (int i = 0; i < getAllItems().size(); i++) {
            if (itemName.equals(getAllItems().get(i).getName())) {
                unitPrice = (itemQuantity / getAllItems().get(i).getUnit()) * getAllItems().get(i).getSpecialPrice();
                isAboveInt(unitPrice);
                regularPrice = (itemQuantity % getAllItems().get(i).getUnit()) * getAllItems().get(i).getPrice();
                isAboveInt(regularPrice);
                break;
            }
        }
        isAboveInt(regularPrice + unitPrice);
        return regularPrice + unitPrice;
    }

    @Override
    public boolean isItemInDatabase(String itemName) {
        for (ItemModel item : getAllItems()) {
            if (itemName.equals(item.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean isAboveInt(double a){
        if (a < 0) {
            throw new IllegalArgumentException("Application cannot calculate such big amount");
        }
        return false;
    }

    public static int parseToInt(String str) {
        if (str.length() > 9){
            throw new IllegalArgumentException("Application cannot calculate such big amount");
        }
        int parsed = 0;
        try {
            parsed = Integer.parseInt(str);
        }
        catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Quantity should be strictly a number");
        }
        return parsed;
    }
}
