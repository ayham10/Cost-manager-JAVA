package il.ac.shenkar;

import il.ac.shenkar.model.costManagerException;
import il.ac.shenkar.model.iModel;
import il.ac.shenkar.model.DBModel;
import il.ac.shenkar.view.iView;
import il.ac.shenkar.view.View;
import il.ac.shenkar.viewmodel.viewModel;
import il.ac.shenkar.viewmodel.IViewModel;

/**
 * this is the main for cost item aplication
 * we use mvvm design , we have the model component that is model var
 * we use view var to connect the view side of the aplication
 * we use vm to connect the viewmodel to connect the model to the view
 */

public class Application {
    public static void main(String args[]) {

        //creating the application components
        iModel model = null;
        try {
            model = new DBModel();
            model.getCostItems();
        } catch (costManagerException e) {
            e.printStackTrace();
        }

          iView view = new View();
          IViewModel vm = new viewModel();


        //connecting the components with each other
          view.setViewModel(vm);
          vm.setModel(model);
           vm.setView(view);


    }
}
