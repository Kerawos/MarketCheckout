package pl.mareksowa.MarketCheckout.models.dao.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemModelDaoImplTest {

    private static ItemModelDaoImpl itemModelDaoImpl;

    @BeforeClass public static void start(){
        itemModelDaoImpl = new ItemModelDaoImpl();
    }

    @AfterClass public static void stop(){
        itemModelDaoImpl = null;
    }

    @Test public void testUnit(){
        assertEquals(150, itemModelDaoImpl.checkPrise("A", 5));
        assertEquals(70, itemModelDaoImpl.checkPrise("A", 3));
        assertEquals(40, itemModelDaoImpl.checkPrise("A", 1));
        assertEquals(80, itemModelDaoImpl.checkPrise("A", 2));
        assertEquals(110, itemModelDaoImpl.checkPrise("A", 4));
        assertEquals(40, itemModelDaoImpl.checkPrise("B", 5));
        assertEquals(60, itemModelDaoImpl.checkPrise("C", 2));
        assertEquals(65, itemModelDaoImpl.checkPrise("D", 3));

        assertTrue(itemModelDaoImpl.isItemInDatabase("A"));
        assertFalse(itemModelDaoImpl.isItemInDatabase("Z"));

        assertEquals(4, itemModelDaoImpl.getAllItems().size());

        assertFalse(itemModelDaoImpl.isAboveInt(214748364));
    }

    @Test public void testExcOverAmount(){
        excCheck("A", 2147483647, "Application cannot calculate such big amount");
        String itemName = "Z";
        excCheck(itemName, 4, "There is no '" + itemName + "' in database..");
    }

    private void excCheck(String itemName, int itemQty, String message){
        try {
            itemModelDaoImpl.checkPrise(itemName, itemQty);
            fail("exc not catch");
        } catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), message);
        }
    }

}