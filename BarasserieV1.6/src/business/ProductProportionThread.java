package business;

import java.util.List;

import controller.AdminController;
import javafx.application.Platform;
import tools.DBOutput.ProductProportion;

public class ProductProportionThread extends Thread {
    private List<ProductProportion> productsProportion;
    private AdminController controller;

    public ProductProportionThread(List<ProductProportion> productsProportion, AdminController controller) {
        this.productsProportion = productsProportion;
        this.controller = controller;
    }

    @Override
    public void run() {
        int totalQuantity = 0;
        for (ProductProportion product : productsProportion) {
            totalQuantity += product.getQuantity();
        }

        for (ProductProportion product : productsProportion) {
            product.setProportion(totalQuantity);
        }

        //TODO demander une à la prof si c'est bien de faire ça
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            controller.displayProductsProportion(productsProportion);
        });
    }
}
