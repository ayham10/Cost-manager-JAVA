package il.ac.shenkar.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this is the model side of the application to connect the application to the Database
 * we use SQL database using mamp and phpmyadmin
 *
 */

public class DBModel implements iModel {
    public static String driver = "com.mysql.jdbc.Driver";
    public static String connectionString = "jdbc:mysql://localhost:3306/adam";
    public Connection connection = null;
    public Statement statement = null;
    public ResultSet rs = null;
    public String query = "";
    public PreparedStatement state;
    public PreparedStatement categoryState;

    public DBModel() throws costManagerException {
        createConnections();
    }
    // this function connects to the database through the connectionString parameter given from the user
    public void createConnections() throws costManagerException {
        try {
            Class.forName(driver);

            //getting connection by calling get connection
            this.connection = DriverManager.getConnection(connectionString ,"Adam", "123");
            this.statement = this.connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("DataBase not connected!");
        }
    }

    @Override
    public void addCostItem(costItem item) throws costManagerException {
        try {
            state = this.connection.prepareStatement("insert into items(name,description,price,category,currency,date ) values (?,?,?,?,?,?)");

            state.setString(1,item.getName());
            state.setString(2, item.getDescription());
            state.setInt(3, item.getprice());
            state.setString(4, item.getCategory());
            state.setString(5, item.getCurrency());
            state.setDate(6, item.getDate());

            state.execute();

        } catch (SQLException e) {
            throw new costManagerException("problem with adding the item ", e);
        }
    }

    @Override
    public boolean checkIfUserExist(String name , String Password) throws costManagerException {
        try{
            int flag=0;
            query = "Select * from users Where username='" + name + "' and password='" + Password + "'";
            System.out.println("connected to DB!");
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                System.out.println(user+" "+ pass);
                flag=1;
            }
            if (flag==1) {
                return true;
            }
           else return false;
        } catch (SQLException e) {
            throw new costManagerException(e.getMessage());
        }

    }

    //delete the item with the given ID from the user
    @Override
    public void deleteId(int id) throws costManagerException {
        try{
            query = "delete from items where id = "+ id;
            statement.execute(query);
        }
        catch (SQLException e){
            throw new costManagerException("Problem in deleting c", e);
        }
    }

    public List<costItem> getCostItems() throws costManagerException {
        List<costItem> costItems = new ArrayList<costItem>();

        try {
            rs = statement.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String currency = rs.getString("currency");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                //Build the List<CostItems>
                costItems.add(new costItem(name,id,description,price,category,currency,date));
            }
        } catch (SQLException e) {
            throw new costManagerException(e.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                throw new costManagerException(e.getMessage());
            }
        }
        return costItems;
    }

    @Override
    public List<costItem> getCostItemsByDate(String FirstDate , String SecDate) throws costManagerException {
        List<costItem> costItems = new ArrayList<costItem>();
        try {
            query = "Select * from items Where date >='" + FirstDate + "' and date<='" + SecDate + "'";
            rs = statement.executeQuery(query);

//            rs = statement.executeQuery("SELECT * FROM items where ");
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String currency = rs.getString("currency");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                costItems.add(new costItem(name,id,description,price,category,currency,date));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                throw new costManagerException(e.getMessage());
            }
        }
        return costItems;
    }

    @Override
    public boolean AddNewUser(String username, String password) throws costManagerException {
        try {
            System.out.println(username);
            System.out.println(password);
            state = this.connection.prepareStatement("insert into users(username,password) values (?,?)");

            state.setString(1,username);
            state.setString(2, password);
            state.execute();
            return true;
        } catch (SQLException e) {
            throw new costManagerException("problem with adding the item ", e);
        }
    }


}
