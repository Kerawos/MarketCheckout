package pl.mareksowa.MarketCheckout;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.mareksowa.MarketCheckout.controllers.PriceCheckoutController;
import pl.mareksowa.MarketCheckout.models.UserRequestModel;
import pl.mareksowa.MarketCheckout.models.dao.impl.ItemModelDaoImpl;

import static org.junit.Assert.*;

public class PriceCheckoutIT {

    private static PriceCheckoutController priceCheckoutController;
    private static UserRequestModel userRequestModel;
    private static ItemModelDaoImpl itemModelDao;

    @BeforeClass public static void start(){
        priceCheckoutController = new PriceCheckoutController();
        userRequestModel = new UserRequestModel();
        itemModelDao = new ItemModelDaoImpl();
    }

    @AfterClass public static void stop(){
        priceCheckoutController = null;
        userRequestModel = null;
        itemModelDao = null;
    }

    @Test public void integrationTest(){
        assertNotNull(userRequestModel);
        userRequestModel.setItemName("A");
        userRequestModel.setQuantity("50");
        assertEquals(1200, itemModelDao.checkPrise(userRequestModel.getItemName(), userRequestModel.getQuantity()));
        userRequestModel.setItemName("D");
        assertEquals(1000, itemModelDao.checkPrise(userRequestModel.getItemName(), userRequestModel.getQuantity()));
        userRequestModel.setQuantity("25");
        assertEquals(505, itemModelDao.checkPrise(userRequestModel.getItemName(), userRequestModel.getQuantity()));
    }


    @Test public void integrationTestExc(){
        excCheck("C", "2147483647", "Application cannot calculate such big amount");
        excCheck("B", "-2", "Item quantity should be between 0 - billion");
        String itemName = "AB";
        excCheck(itemName, "50", "There is no '" + itemName + "' in database..");
    }

    private void excCheck(String itemName, String itemQty, String message){
        userRequestModel.setItemName(itemName);
        try {
            userRequestModel.setQuantity(itemQty);
            itemModelDao.checkPrise(userRequestModel.getItemName(), userRequestModel.getQuantity());
            fail("exc not catch");
        } catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), message);
        }
    }



}