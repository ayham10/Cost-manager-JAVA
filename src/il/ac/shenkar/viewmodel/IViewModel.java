package il.ac.shenkar.viewmodel;

import il.ac.shenkar.model.costItem;
import il.ac.shenkar.model.iModel;
import il.ac.shenkar.model.costManagerException;
import il.ac.shenkar.view.iView;

public interface IViewModel {

     public void setView(iView view);
     public void setModel(iModel model);
     public void getItems();
     public void addCostItem(costItem item);
     public void deleteId(int idNumber);
     public boolean logIn(String Name , String Password ) throws costManagerException;
     public void getReports(String text, String text1);
    boolean CreateNewUser(String text, String text1) throws costManagerException;
}
