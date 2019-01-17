package VendingMachine;

import Model.Coin;
import Model.Currancy;
import Model.Product;
import Services.IOServices;

import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Map<Product, Integer> productStock;
    private Map<Coin, Integer> coinStock;
    private Currancy currancyType = Currancy.RON;
    private IOServices ioServices;
    boolean buyAnotherProduct = false;

    public VendingMachine(Map<Product, Integer> productStock, Map<Coin, Integer> coinStock, Currancy currancyType, IOServices ioServices) {
        this.productStock = productStock;
        this.coinStock = coinStock;
        this.currancyType = currancyType;
        this.ioServices = ioServices;
    }

    public void run() {
        ioServices.displayMessage();
        ioServices.displayProductStock(productStock);
        ioServices.displayCoin(coinStock);
        ioServices.selectProduct(productStock);
        ioServices.insertCoin(coinStock, productStock);
        ioServices.payRest();
        ioServices.deliverProduct(productStock, coinStock);
        ioServices.manageProductNumber(productStock);
        ioServices.manageRest(coinStock);
        ioServices.searchIfProductIsAvailable(productStock,coinStock);
        restart();
    }

    public void restart() {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.println(" Enter -->  YES  <--  to buy another product.");
        String optionToRestart = s.nextLine();
        if (optionToRestart.equals("YES")) {
            run();
        }
    }


}


