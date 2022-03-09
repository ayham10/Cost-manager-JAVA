package il.ac.shenkar.viewmodel;

import il.ac.shenkar.model.costItem;
import il.ac.shenkar.model.iModel;
import il.ac.shenkar.view.iView;
import il.ac.shenkar.model.costManagerException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class viewModel implements IViewModel {

    private iModel model;
    private iView view;
    private ExecutorService pool;

    public viewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setView(iView view) {this.view = view;}

    @Override
    public void setModel(iModel model) {this.model = model;}

    @Override
    public void getItems() {
    pool.submit(new Runnable() {
        @Override
        public void run() {
            try{
                List<costItem> items = model.getCostItems();
                view.showItems(items);
            }catch (costManagerException e) {
                view.showMessage(e.getMessage());
            }
        }
    });
    }

    @Override
    public void addCostItem(costItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("addCostItem in  view model");
                    model.addCostItem(item);
                    view.showMessage("Cost item was added successfully");
                    List<costItem> items = model.getCostItems();
                    view.showItems(items);
                } catch(costManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });

    }

    @Override
    public void deleteId(int id) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteId(id);
                    view.showMessage("Cost item was deleted successfully");
                    List<costItem> items = model.getCostItems();
                    view.showItems(items);
                } catch (costManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean logIn(String Name, String Password) throws costManagerException {
        System.out.println(Name);
        System.out.println(Password);
        if (model.checkIfUserExist( Name ,Password)){
            return true;
        }
          return false;
    }

    @Override
    public void getReports(String text, String text1) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    List<costItem> items = model.getCostItemsByDate(text,text1);
                    view.showReportItems(items);
                }catch (costManagerException e) {
                    view.showMessage(e.getMessage());

                }
            }
        });
    }

    @Override
    public boolean CreateNewUser(String text, String text1) throws costManagerException {
        if (model.AddNewUser( text ,text1)){
            return true;
        }
        return false;
    }
}
