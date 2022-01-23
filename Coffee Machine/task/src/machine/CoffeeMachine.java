package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int money = 550;
    private int disposableCups = 9;
    private final int[][] typesOfCoffee = {{250, 0, 16, 4}, {350, 75, 20, 7}, {200, 100, 12, 6}};

	// comment
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine cm = new CoffeeMachine();
        boolean flag = true;

        while (flag) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            switch (scanner.next()) {
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, " +
                            "2 - latte, 3 - cappuccino, back - to main menu:");
                    String request = scanner.next();
                    switch (request) {
                        case "1":
                        case "2":
                        case "3":
                            String[] checkResult = cm.checkSupplies(cm,
                                    cm.typesOfCoffee[Integer.parseInt(request) - 1]);
                            if (checkResult[0].equals("true")) {
                                System.out.println("I have enough resources, making you a coffee!\n");
                                cm.water -= cm.typesOfCoffee[Integer.parseInt(request) - 1][0];
                                cm.milk -= cm.typesOfCoffee[Integer.parseInt(request) - 1][1];
                                cm.coffeeBeans -= cm.typesOfCoffee[Integer.parseInt(request) - 1][2];
                                cm.money += cm.typesOfCoffee[Integer.parseInt(request) - 1][3];
                                cm.disposableCups--;
                            } else {
                                System.out.println("Sorry, not enough " + checkResult[1] + "!\n");
                            }
                            break;
                        case "back":
                            System.out.println();
                            break;
                    }
                    break;
                case "fill":
                    System.out.println("\nWrite how many ml of water you want to add:");
                    cm.water += scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    cm.milk += scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    cm.coffeeBeans += scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee you want to add:");
                    cm.disposableCups += scanner.nextInt();
                    System.out.println();
                    break;
                case "take":
                    System.out.println("I gave you $" + cm.money);
                    cm.money = 0;
                    System.out.println();
                    break;
                case "remaining":
                    cm.currentState();
                    break;
                case "exit":
                    flag = false;
                    break;
            }
        }
    }

    private void currentState() {
        System.out.println("\nThe coffee machine has:\n" +
                this.water + " ml of water\n" +
                this.milk + " ml of milk\n" +
                this.coffeeBeans + " g of coffee beans\n" +
                this.disposableCups + " disposable cups\n" +
                "$" + this.money + " of money\n");
    }

    private String[] checkSupplies(CoffeeMachine cm, int[] typeOfCoffee) {
        String[] checkResult = new String[2];
        if (cm.water < typeOfCoffee[0]) {
            checkResult[0] = "false";
            checkResult[1] = "water";
        } else if (cm.milk < typeOfCoffee[1]) {
            checkResult[0] = "false";
            checkResult[1] = "milk";
        } else if (cm.coffeeBeans < typeOfCoffee[2]) {
            checkResult[0] = "false";
            checkResult[1] = "coffee beans";
        } else if (cm.disposableCups < 1) {
            checkResult[0] = "false";
            checkResult[1] = "disposable cups";
        } else {
            checkResult[0] = "true";
        }
        return checkResult;
    }
}
