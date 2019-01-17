package Services;

import Model.Coin;
import Model.Currancy;
import Model.Product;

import java.util.Map;
import java.util.Scanner;

public class IOServices {


    public static final String ANSI_YELLOW = "\u001B[33m"; //color Stuff
    public static final String ANSI_RED = "\u001B[31m"; // color Stuff
    public static final String ANSI_BLUE = "\u001B[34m"; // color Stuff

    Integer optionProduct;
    Integer priceOfSelection = 0;
    Integer sumInserted = 0;
    Integer rest = 0;
    boolean validProdSelection = false;
    Integer numberOfProduct;
    Integer numberOfCoins;


    public void displayProductStock(Map<Product, Integer> products) {
        System.out.println("Prod.COD" + '\t' + "Prod.NAME" + '\t' + "Prod.SIZE" + '\t' + "   " + "Prod.PRICE");
        System.out.println("__________________________________________________");
        for (Product product : products.keySet()) {
            System.out.println(product.getCod() + "\t " + "\t " + " " + product.getName() + " \t" + " " + "\t " + product.getSize() + '\t' + "\t " + product.getPrice() + " [" + Currancy.RON.toString() + "]");
        }
        System.out.println();
    }

    public void displayCoin(Map<Coin, Integer> coins) {
        System.out.println("The machine is using -> " + Currancy.RON.toString());
        System.out.println();
        System.out.println("Coin.COD" + '\t' + "Coin.Value");
        System.out.println("__________________________________________________");
        for (Coin coin : coins.keySet()) {
            System.out.println(coin.getCod() + "\t" + "    " + coin.getValue() + " [" + Currancy.RON.toString() + "]");
        }
    }

    public void displayMessage() {
        System.out.println("********");
        System.out.println(" Hello! ");
        System.out.println("********");
    }

    public Integer selectProduct(Map<Product, Integer> products) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to buy: ");
        optionProduct = scanner.nextInt();
        for (Product product : products.keySet()) {
            if (optionProduct == product.getCod()) {
                System.out.println(" You selection is --> " + product.getName());
                System.out.println(" The price of a " + product.getName() + "--> " + product.getPrice() + " " + Currancy.RON);
                numberOfProduct = (Integer) products.get(product);
                System.out.println(" We have " + numberOfProduct + " " + product.getName() + "available");
                priceOfSelection = product.getPrice();
                validProdSelection = true;
                products.put(product, products.get(product) - 1);
                numberOfProduct = (Integer) products.get(product);
            }
        }
        return null;
    }

    public Integer insertCoin(Map<Coin, Integer> coins, Map<Product, Integer> products) {

        Scanner scanner = new Scanner(System.in);
        if (validProdSelection && numberOfProduct - 1 > -2) {
            while (sumInserted < priceOfSelection) {
                System.out.println("INSERT -- COIN -- HERE: ");
                Integer optionCoin = scanner.nextInt();
                for (Coin coin : coins.keySet()) {
                    if (optionCoin.equals(coin.getCod())) {
                        sumInserted = sumInserted + coin.getValue();
                        System.out.println("SumInserted -> " + sumInserted + " " + Currancy.RON.toString());
                    }
                }
            }
            System.out.println("Total amout inserted -> " + sumInserted + " " + Currancy.RON);
            System.out.println("You have to play --> " + priceOfSelection + " " + Currancy.RON);
        }
        return sumInserted;
    }

    public Integer payRest() {
        if (sumInserted >= priceOfSelection && validProdSelection) {
            rest = sumInserted - priceOfSelection;
            System.out.println("__________________________________________");
            System.out.println(ANSI_BLUE + "Your change is ->" + rest + " " + Currancy.RON.toString());
            sumInserted = 0;
        }
        return rest;
    }

    public void deliverProduct(Map<Product, Integer> products, Map<Coin, Integer> coins) {
        if (sumInserted >= priceOfSelection && validProdSelection) {
            System.out.println(ANSI_YELLOW + "Thank you! Here is your product!");
        } else {
            while (!validProdSelection) {
                System.out.println(" ----- VendingMachine <-> Error1: Invalid product code selection!!!");
                System.out.println();
                selectProduct(products);
                insertCoin(coins, products);
                payRest();
                deliverProduct(products, coins);
            }
        }
    }

    public Integer searchIfProductIsAvailable(Map<Product, Integer> products, Map<Coin, Integer> coins) {
        if (numberOfProduct == 0 && numberOfProduct - 1 > -1) {
            System.out.println(" ----- VendingMachine <-> Error2: Product not available!!!");
            System.out.println();
            selectProduct(products);
            insertCoin(coins, products);
            payRest();
            deliverProduct(products, coins);
            searchIfProductIsAvailable(products, coins);
        }
        return null;
    }

    public Integer manageProductNumber(Map<Product, Integer> products) {
        if (sumInserted >= priceOfSelection && numberOfProduct > 0) {

            System.out.println("NUmber of products remaining >>>" + numberOfProduct);

        }
        System.out.println(" Number of product remaining in stock --> " + numberOfProduct);
        return products.get(optionProduct);
    }

    public Integer manageRest(Map<Coin, Integer> coins) {
        Integer sumInMachine = 0;


        for (Coin coin : coins.keySet()) {
            numberOfCoins = coins.get(coin);
            Integer maxCoin = (Integer) coins.values().toArray()[2];
            // System.out.println(" We have " + numberOfCoins + " coins of " + coin.getValue() + " " + Currancy.RON.toString());// Display for testing

            if (coin.getValue().equals(coins.values().toArray()[0])) {
                sumInMachine = coins.get(coin) * coin.getValue();
                if (sumInMachine >= maxCoin && sumInMachine >= rest) {
                    System.out.println("Change available");
                    coins.values().toArray()[0] = (Integer) coins.values().toArray()[0] - rest;
                } else {
                    System.out.println("We are sorry. Change not available.");
                    System.out.println("We recommend inserting smaller coins.");
                }
            }
        }
        return sumInMachine;
    }

}



















