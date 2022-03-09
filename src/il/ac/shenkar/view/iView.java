package il.ac.shenkar.view;

import il.ac.shenkar.model.costItem;
import java.util.List;
import il.ac.shenkar.viewmodel.IViewModel;

public interface iView {
      void setViewModel(IViewModel vm);
      void showMessage(String text);
      void showItems(List<costItem> vec);
      void showReportItems(List<costItem> items);
}
