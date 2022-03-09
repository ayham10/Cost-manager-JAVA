package il.ac.shenkar.model;

import java.util.List;

public interface iModel {
    public void createConnections() throws costManagerException;
    public void addCostItem(costItem item) throws costManagerException;
    public boolean checkIfUserExist(String name , String password) throws costManagerException;
    public void deleteId(int id) throws costManagerException;
    public List<costItem> getCostItems() throws costManagerException;
    public List<costItem> getCostItemsByDate(String FirstDate , String SecDate) throws costManagerException;
    public boolean AddNewUser(String text, String text1) throws costManagerException;
}
