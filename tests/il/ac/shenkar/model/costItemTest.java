package il.ac.shenkar.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

import il.ac.shenkar.model.costItem;

public class costItemTest {
    Date firstDate1 = new Date(2020,7,20);
    Date firstDate2 = new Date(2020,8,20);
    Date firstDate3 = new Date(2020,8,20);
    private costItem item1;
    private costItem item2;
    private costItem item3;
    @Before
    public void setUp() throws Exception {
        item1=new costItem("dogfood",1,"beef and rice",130,"food","USD",firstDate1);
        item2=new costItem("catfood",1,"salmon and chicken",190,"food","EUR",firstDate2);
        item3=new costItem("table",1,"dinning table",560,"other","EUR",firstDate3);
    }
    @After
    public void tearDown() throws Exception {
        item1=null;
        item2=null;
        item3=null;
    }
    @Test
    public void getName() {
        assertEquals(item1.getName(),"dogfood");
        assertEquals(item2.getName(),"catfood");
        assertEquals(item3.getName(),"table");
    }

    @Test
    public void getDate() {
        assertEquals(item1.getDate(),firstDate1);
        assertEquals(item2.getDate(),firstDate2);
        assertEquals(item3.getDate(),firstDate3);
    }

    @Test
    public void getDescription() {
        assertEquals(item1.getDescription(),"beef and rice");
        assertEquals(item2.getDescription(),"salmon and chicken");
        assertEquals(item3.getDescription(),"dinning table");
    }

    @Test
    public void getCurrency() {
        assertEquals(item1.getCurrency(),"USD");
        assertEquals(item2.getCurrency(),"EUR");
        assertEquals(item3.getCurrency(),"EUR");
    }

    @Test
    public void getCategory() {
        assertEquals(item1.getCategory(),"food");
        assertEquals(item2.getCategory(),"food");
        assertEquals(item3.getCategory(),"other");
    }

    @Test
    public void getprice() {
        assertEquals(item1.getprice(),130);
        assertEquals(item2.getprice(),190);
        assertEquals(item3.getprice(),560);
    }


}