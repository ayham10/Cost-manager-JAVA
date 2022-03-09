package il.ac.shenkar.model;

import java.sql.Date;

public class costItem {
    private String name;
    private int id;
    private Date date;
    private String description;
    private String currency;
    private String category;
    private int price;

    public costItem(String name_, int id, String description, int price, String category, String currency, Date valueOf) {
        this.name = name_;
        this.id = id;
        this.date = valueOf;
        this.description = description;
        this.currency = currency;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return " Item   : "  +id + "   " + name + "   " + description + "   " + category  + "   " + price + "   " + currency + "   " + date  ;
    }

    public costItem(String name, String description , int price, String category , String currency, Date date) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.currency = currency;
        this.category = category;
        this.price= price;

    }
    public String getName() {return name;}

    public int getId() {return id;}

    public Date getDate() {return date;}

    public String getDescription() {return description;}

    public String getCurrency() {return currency;}

    public String getCategory() {return category;}

    public int getprice() {return price;}

}
