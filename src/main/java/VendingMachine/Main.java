package VendingMachine;

import Model.Coin;
import Model.Currancy;
import Model.Product;
import Services.IOServices;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<Product, Integer> products = new LinkedHashMap<Product, Integer>();
        Product suc1 = new Product("Cola doza   ", 2, 1, "0.25l");
        Product suc2 = new Product("Cola sticla ", 4, 2, "0.50l");
        Product suc3 = new Product("Fanta doza  ", 2, 3, "0.25l");
        Product suc4 = new Product("Fanta sticla", 4, 4, "0.50l");
        Product bar1 = new Product("Bounty Bar  ", 5, 5, "100 g");
        Product bar2 = new Product("Lyon Bar    ", 5, 6, "100 g");

        products.put(suc1, 0);
        products.put(suc2, 4);
        products.put(suc3, 1);
        products.put(suc4, 4);
        products.put(bar1, 7);
        products.put(bar2, 6);
        Map<Coin, Integer> coins = new LinkedHashMap<Coin, Integer>();
        Coin c1 = new Coin(1000, 1);
        Coin c2 = new Coin(2000, 5);
        Coin c3 = new Coin(3000, 10);
        coins.put(c1, 100);
        coins.put(c2, 20);
        coins.put(c3, 5);
        IOServices ios = new IOServices();
        VendingMachine myVendingMachine = new VendingMachine(products, coins, Currancy.RON, ios);
        myVendingMachine.run();


    }
}
