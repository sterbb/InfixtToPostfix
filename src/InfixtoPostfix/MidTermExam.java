package InfixtoPostfix;

import java.util.ArrayList;
import java.util.Scanner;

public class MidTermExam {


    public static ArrayList<Integer> fifoarray = new ArrayList<>();
    public static ArrayList<Integer> pidarray = new ArrayList<>();
    public static ArrayList<Integer> quantitiyarray = new ArrayList<>();
    public static ArrayList<Integer> soldarary = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);

    public static void StockIn() {

        int productid, quantity;
        String answer;
        boolean proceed = true;

        do {
            System.out.print("Enter Product ID: ");
            productid= input.nextInt();
            System.out.print("Enter Quantity: ");
           quantity = input.nextInt();


            if (pidarray.contains(productid)) {
                for (int i = 0; i < pidarray.size(); i++) {
                    if (pidarray.get(i).equals(productid)){
                        quantitiyarray.set(i, quantitiyarray.get(i) + quantity);
                    }
                }
            } else {
                pidarray.add(productid);
                quantitiyarray.add(quantity);
                soldarary.add(0);
            }

            fifoarray.add(productid);
            fifoarray.add(quantity);
            System.out.print("Do you want to enter another stock-in transaction? ");
            answer = input.next().toUpperCase();
            if (!answer.equals("Y") && !answer.equals("N")){
                while (true){
                    System.out.println("Incorrect Response. Please try again.");
                    System.out.print("Do you want to enter another stock-in transaction? ");
                    answer = input.next().toUpperCase();
                    if (answer.equals("Y")|| answer.equals("N")){
                        break;
                    }
                }
            }
            if (answer.equals("N")){
                System.out.println("Product ID");
                System.out.println("Quantity");
                for (int i = 0; i < fifoarray.size(); i++) {
                    System.out.println(fifoarray.get(i));
                    }
                break;
                }
            System.out.println("----------------------------------------------------------------------");
        }while(true);
    }


    public static void Sales() {
        int excess;
        boolean proceed=true;

        do {
            int productid, quantity;
            String answer;
            System.out.print("Enter Product ID: ");
            productid= input.nextInt();
            System.out.print("Enter Order Quantity: ");
            quantity = input.nextInt();

            do {
                for (int i = 0; i < pidarray.size(); i++) {
                    if (pidarray.get(i).equals(productid)) {
                        if (quantitiyarray.get(i)<quantity){
                            System.out.println("\nStock are insufficient. Please try again");
                            System.out.print("Enter Product ID: ");
                            productid= input.nextInt();
                            System.out.print("Enter Order Quantity: ");
                            quantity = input.nextInt();
                            break;
                        }else{
                            proceed = false;
                        }
                    }
                }
            }while (proceed);



            for (int i = 0; i < fifoarray.size(); i+=2) {
                if (fifoarray.get(i).equals(productid) && fifoarray.get(i + 1)>0) {
                    fifoarray.set(i + 1, fifoarray.get(i + 1) - quantity);
                    if (fifoarray.get(i + 1) < 0) {
                        excess = fifoarray.get(i+1);
                        fifoarray.set(i+1,0);
                        for (int i2 = 0; i2 < fifoarray.size(); i2 += 2) {
                            if (fifoarray.get(i2).equals(productid) && fifoarray.get(i2 + 1) > 0) {
                                fifoarray.set(i2 + 1, fifoarray.get(i2 + 1) + excess);
                                break;
                            }
                        }
                    }
                    break;
                }
            }

            for (int i = 0; i < pidarray.size(); i++) {
                if (pidarray.get(i).equals(productid)) {
                    quantitiyarray.set(i, quantitiyarray.get(i)-quantity);
                }
            }

            for (int i = 0; i < pidarray.size(); i++) {
                if (pidarray.get(i).equals(productid)) {
                    soldarary.set(i, soldarary.get(i)+quantity);
                }
            }

            System.out.println(quantitiyarray);
            System.out.println(soldarary);

            System.out.println("\nIn-Stock:\n");
            System.out.println("Product ID \nQuantity");

            for (int i = 0; i < fifoarray.size(); i++){
                System.out.println(fifoarray.get(i));
            }

            System.out.print("\nDo you want another sale transaction? ");
            answer = input.next().toUpperCase();
            if (!answer.equals("Y") && !answer.equals("N")){
                while (true){
                    System.out.println("Incorrect Response. Please try again.");
                    System.out.print("Do you want to enter another stock-in transaction? ");
                    answer = input.next().toUpperCase();
                    if (answer.equals("Y")|| answer.equals("N")){
                        break;
                    }
                }
            }
            if (answer.equals("N")){
                break;
            }
            System.out.println("----------------------------------------------------------------------");
        }while (true);

    }

    public static void Report(){
        System.out.println("\nSummary Report\n");
        System.out.println("Product ID");
        System.out.println("Quantity Sold");
        System.out.println("Remaining Quantity\n");

        for (int i=0; i<pidarray.size(); i++){
            System.out.println(pidarray.get(i));
            System.out.println(soldarary.get(i));//quantitiy sold
            System.out.println(quantitiyarray.get(i));//remaining quantity

        }
    }

    public static void main(String[] args) {
        System.out.println("[STOCK-IN TRANSACTION]\n");
        StockIn();

        System.out.println("\n[SALES TRANSACTION]\n");
        Sales();

        System.out.println("\n [SUMMARY REPORT]");
        Report();
    }
}