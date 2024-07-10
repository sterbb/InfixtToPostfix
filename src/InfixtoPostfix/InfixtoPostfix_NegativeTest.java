package InfixtoPostfix;

import java.util.ArrayList;
import java.util.Scanner;

public class InfixtoPostfix_NegativeTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> chararray = new ArrayList<Character>();
        ArrayList<String> infixarray = new ArrayList<String>();
        int condition = 0;
        int p1 = 0;
        int p2 = 0;
        boolean loop = true;
        // Infix to Post fix
        String co;
        // Parenthesis
        ArrayList<String> prtsarray = new ArrayList<>();

        do {
            System.out.println("Infix to Postfix");
            System.out.println("Operand available: +, -, *, /");
            System.out.println("Please enter an Infix Notation: ");
            String infix = scanner.nextLine();
            System.out.println("Infix Notation: " + infix);


            for (int i = 0; i < infix.length(); i++) {
                chararray.add(infix.charAt(i));
                if (infix.charAt(i) == '(') {
                    p1 += 1;
                } else if (infix.charAt(i) == ')') {
                    p2 += 1;
                }
            }

            for (int i = 0; i < chararray.size(); i++) {
                infixarray.add(chararray.get(i).toString());
            }



            // check if the infix does not contain an operator
            if (!chararray.contains('+') && !chararray.contains('-') && !chararray.contains('*') && !chararray.contains('/')&&!chararray.contains('^')) {
                condition = 7;
            } else {
                for (int i = 0; i < chararray.size(); i++) {
                    //check if there is consecutive operators
                    if (chararray.get(i).equals('+') || chararray.get(i).equals('*') || chararray.get(i).equals('/') || chararray.get(i).equals('-')||chararray.get(i).equals('^')) {
                        if (chararray.get(i + 1).equals('+') || chararray.get(i + 1).equals('*') || chararray.get(i + 1).equals('/')||chararray.get(i+1).equals('^')) {
                            condition = 1;
                            break;
                            //check if the negative sign is used correctly
                        }else if(chararray.get(i+1).equals('-')){
                            if (chararray.get(i+2).equals('-')){
                                condition = 1;
                                break;
                            }
                        }
                    }
                    // Check if the first and last element is an operator
                    else if (chararray.get(0).equals('+') || chararray.get(0).equals('*') || chararray.get(0).equals('/') || chararray.get(0).equals('^')|| chararray.get(chararray.size() - 1).equals('+') ||
                            chararray.get(chararray.size() - 1).equals('*') || chararray.get(chararray.size() - 1).equals('/') || chararray.get(chararray.size() - 1).equals('-')||chararray.get(chararray.size() - 1).equals('^')) {
                        condition = 2;
                        break;
                        //check if the negative is an operator or sign
                    }else if (chararray.get(0).equals('-')) {
                        if (chararray.get(1).equals('-'))
                            condition = 2;
                            break;
                        }
                        //check if the parenthesis is used properly
                        //Wont check the previous
                    else if (chararray.get(i).equals('(') && (i == 0 || i == chararray.size() - 1)) {
                        if (chararray.get(i + 1).equals('+') || chararray.get(i + 1).equals('*') || chararray.get(i + 1).equals('/') ||chararray.get(i + 1).equals('^') || chararray.get(i + 1).equals(')')) {
                            condition = 3;
                            break;
                        }//Wont check the next
                    } else if (chararray.get(i).equals(')') && (i == 0 || i == chararray.size() - 1)) {
                        if (chararray.get(i - 1).equals('+') || chararray.get(i - 1).equals('*') || chararray.get(i - 1).equals('/') || chararray.get(i - 1).equals('-')|| chararray.get(i - 1).equals('^')) {
                            condition = 3;
                            break;
                        }
                    } else if (chararray.get(i).equals('(') && (i != 0 || i != chararray.size() - 1)) {
                        if (chararray.get(i + 1).equals('+') || chararray.get(i + 1).equals('*') || chararray.get(i + 1).equals('/') ||chararray.get(i + 1).equals('^')||
                                chararray.get(i - 1).equals('0') || chararray.get(i - 1).equals('1') || chararray.get(i - 1).equals('2') || chararray.get(i - 1).equals('3') || chararray.get(i - 1).equals('4') || chararray.get(i - 1).equals('5') ||
                                chararray.get(i - 1).equals('6') || chararray.get(i - 1).equals('7') || chararray.get(i - 1).equals('8') || chararray.get(i - 1).equals('9')) {
                            condition = 3;
                            break;
                        }
                    } else if (chararray.get(i).equals(')') && (i != 0 || i != chararray.size() - 1)) {
                        if (chararray.get(i - 1).equals('+') || chararray.get(i - 1).equals('*') || chararray.get(i - 1).equals('/') || chararray.get(i - 1).equals('-')||chararray.get(i - 1).equals('^') || chararray.get(i + 1).equals('0') || chararray.get(i + 1).equals('1') ||
                                chararray.get(i + 1).equals('2') || chararray.get(i + 1).equals('3') || chararray.get(i + 1).equals('4') || chararray.get(i + 1).equals('5') ||
                                chararray.get(i + 1).equals('6') || chararray.get(i + 1).equals('7') || chararray.get(i + 1).equals('8') || chararray.get(i + 1).equals('9')) {
                            condition = 3;
                            break;
                        }
                    }
                    //check if there are equal open and close parenthesis
                    else if (p1 != p2) {
                        condition = 4;
                        break;
                        // check if does not contain operator
                    } else if ((chararray.get(i) >= 'a' && chararray.get(i) <= 'z') || (chararray.get(i) >= 'A' && chararray.get(i) <= 'Z')) {
                        condition = 5;
                        break;
                        //check if the string only contains numbers, operators and parenthesis
                    } else if (chararray.get(i).equals('0') || chararray.get(i).equals('1') || chararray.get(i).equals('2') || chararray.get(i).equals('3') || chararray.get(i).equals('4') || chararray.get(i).equals('5') ||
                            chararray.get(i).equals('6') || chararray.get(i).equals('7') || chararray.get(i).equals('8') || chararray.get(i).equals('9') || chararray.get(i).equals('+') || chararray.get(i).equals('-') ||
                            chararray.get(i).equals('*') || chararray.get(i).equals('/') || chararray.get(i).equals('^') ||chararray.get(i).equals('(') || chararray.get(i).equals(')')) {
                        condition = 6;
                    } else if(chararray.get(0).equals('.')||chararray.get(chararray.size()-1).equals('.')){
                        condition = 10;
                    }else if(chararray.get(i).equals('.')){
                        if (!chararray.get(i + 1).equals('0') || !chararray.get(i + 1).equals('1') ||
                                !chararray.get(i + 1).equals('2') || !chararray.get(i + 1).equals('3') || !chararray.get(i + 1).equals('4') || !chararray.get(i + 1).equals('5') ||
                                !chararray.get(i + 1).equals('6') || !chararray.get(i + 1).equals('7') || !chararray.get(i + 1).equals('8') || !chararray.get(i + 1).equals('9')){
                            condition = 10;
                        }
                    }
                }
            }

            System.out.println(condition);
            boolean proceed2=true;
            //Negative Integer
            String negative = "-";
            while(proceed2){
                for (int i =0; i<infixarray.size(); i++){
                    //if negative integer is located on the first element
                    if (infixarray.get(i).equals("-")&& i ==0){
                        if (infixarray.get(i + 1).equals("1") || infixarray.get(i + 1).equals("2") || infixarray.get(i + 1).equals("3") || infixarray.get(i + 1).equals("4") || infixarray.get(i + 1).equals("5") ||
                                infixarray.get(i + 1).equals("6") || infixarray.get(i + 1).equals("7") || infixarray.get(i + 1).equals("8") || infixarray.get(i + 1).equals("9")){
                            infixarray.remove(i);
                            negative +=infixarray.get(i);
                            infixarray.remove(i);
                            infixarray.add(i, negative);
                            System.out.println(infixarray+"hello");
                            System.out.println(infixarray.get(1));
                            break;

                            //gagana na tol hehehe
                        }
                        // if negative integer is not located on the first element, chech if the next operator is a negative sign and have a number next to it
                    }else if(infixarray.get(i).equals("+")||infixarray.get(i).equals("-")||infixarray.get(i).equals("*")||infixarray.get(i).equals("/")||infixarray.get(i).equals("^")||infixarray.get(i).equals("(")){
                        if(infixarray.get(i+1).equals("-")){
                            infixarray.remove(i+1);
                            negative +=infixarray.get(i+1);
                            infixarray.remove(i+1);
                            infixarray.add(i+1, negative);
                            negative="-";
                            System.out.println(infixarray+"hi");
                            System.out.println(infixarray.get(1));
                            break;
                        }
                    }else if (i==infixarray.size()-1){
                        proceed2=false;
                    }
                }

            }

            switch (condition) {
                case 1:
                    System.out.println("Infix Error: Consecutive operators detected\n");
                    break;
                case 2:
                    System.out.println("Infix Error: The FIRST or the LAST element is an operator detected\n");
                    break;
                case 3:
                    System.out.println("Infix Error: Parentheses error detected\n");
                    break;
                case 4:
                    System.out.println("Infix Error: Parentheses error(not equal) detected\n");
                    break;
                case 5:
                    System.out.println("Input error: A letter is detected\n");
                    break;
                case 6:

                    while (infixarray.contains("(") || infixarray.contains(")")) {
                        for (int i = 0; i < infixarray.size(); i++) {
                            if (infixarray.get(i).equals("(")) {
                                infixarray.remove(i);
                                while (!infixarray.get(i).equals(")")) {
                                    prtsarray.add(infixarray.get(i));
                                    infixarray.remove(i);
                                }
                                break;
                            } else if (infixarray.get(i).equals(")")) {
                                infixarray.remove(i);

                                while (prtsarray.size() != 1) {

                                    while (prtsarray.contains("^")){
                                        for (int i2 = 0; i2 < prtsarray.size(); i2++) {
                                            if (prtsarray.get(i2).equals("^")) {
                                                co = prtsarray.get(i2 - 1) + prtsarray.get(i2 + 1) + prtsarray.get(i2);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.remove(i2- 1);
                                                prtsarray.add(i2 - 1, co);
                                                break;
                                            }
                                        }
                                    }

                                    while (prtsarray.contains("*") || prtsarray.contains("/")) {
                                        for (int i2 = 0; i2 < prtsarray.size(); i2++) {
                                            if (prtsarray.get(i2).equals("*") || prtsarray.get(i2).equals("/")) {
                                                co = prtsarray.get(i2 - 1) + prtsarray.get(i2 + 1) + prtsarray.get(i2);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.add(i2 - 1, co);
                                                break;
                                            }
                                        }
                                    }
                                    while (prtsarray.contains("+") || prtsarray.contains("-")) {
                                        for (int i2 = 0; i2 < prtsarray.size(); i2++) {
                                            if (prtsarray.get(i2).equals("+") || prtsarray.get(i2).equals("-")) {
                                                co = prtsarray.get(i2 - 1) + prtsarray.get(i2 + 1) + prtsarray.get(i2);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.remove(i2 - 1);
                                                prtsarray.add(i2 - 1, co);
                                                break;
                                            }
                                        }
                                    }
                                }
                                infixarray.add(i, prtsarray.get(0));
                                prtsarray.remove(0);
                                break;
                            }
                        }

                    }

                    while (infixarray.size() != 1) {

                        // exponential

                        while (infixarray.contains("^")){
                            for (int i = 0; i < infixarray.size(); i++) {
                                if (infixarray.get(i).equals("^")) {
                                    co = infixarray.get(i - 1) + infixarray.get(i + 1) + infixarray.get(i);
                                    infixarray.remove(i - 1);
                                    infixarray.remove(i - 1);
                                    infixarray.remove(i - 1);
                                    infixarray.add(i - 1, co);
                                    break;
                                }
                            }
                        }

                        while (infixarray.contains("*") || infixarray.contains("/")) {
                            for (int i = 0; i < infixarray.size(); i++) {
                                if (infixarray.get(i).equals("*") || infixarray.get(i).equals("/")) {
                                    co = infixarray.get(i - 1) + infixarray.get(i + 1) + infixarray.get(i);
                                    infixarray.remove(i - 1);
                                    infixarray.remove(i - 1);
                                    infixarray.remove(i - 1);
                                    infixarray.add(i - 1, co);
                                    break;
                                }
                            }
                        }

                        while (infixarray.contains("+") || infixarray.contains("-")) {
                            for (int i = 0; i < infixarray.size(); i++) {
                                if (infixarray.get(i).equals("+") || infixarray.get(i).equals("-")) {
                                    co = infixarray.get(i - 1) + infixarray.get(i + 1) + infixarray.get(i);
                                    infixarray.remove(i - 1);
                                    infixarray.remove(i - 1);
                                    infixarray.remove(i - 1);
                                    infixarray.add(i - 1, co);
                                    break;
                                }
                            }
                        }

                    }

                    System.out.println("Postfix Notation: " + infixarray.get(0));
                    loop = false;
                    break;
                case 7:
                    System.out.println("Infix error: No operator detected\n");
                    break;
                case 8:
                    System.out.println("Input error: Insufficient input detected\n");
                case 9:
                    System.out.println("Input error: Operator not available\n");
            }






        //Multiple Digits - Decimal
        boolean proceed = true;
        boolean proceed1 =true;
        int inum=1;
        String cn="";

     /*||infixarray.get(infixarray.size()-1).equals("1")||
                infixarray.get(infixarray.size()-1).equals("2")||infixarray.get(infixarray.size()-1).equals("3")||infixarray.get(infixarray.size()-1).equals("4")||
                infixarray.get(infixarray.size()-1).equals("5")||infixarray.get(infixarray.size()-1).equals("6")||infixarray.get(infixarray.size()-1).equals("7")||
                infixarray.get(infixarray.size()-1).equals("8")||infixarray.get(infixarray.size()-1).equals("9")||infixarray.get(infixarray.size()-1).equals("0")||
                infixarray.get(infixarray.size()-1).equals(".")?

        while(proceed){
            for (int i =0; i< infixarray.size(); i++){
                System.out.println(inum);
                System.out.println("yut dputa");
                System.out.println(i);
                if (infixarray.get(i).equals("+")|| infixarray.get(i).equals("-")|| infixarray.get(i).equals("*")|| infixarray.get(i).equals("/")){
                        while(proceed1){
                           if(infixarray.get(i-inum).equals("+")||infixarray.get(i-inum).equals("-")||infixarray.get(i-inum).equals("*")||infixarray.get(i-inum).equals("/")){
                               System.out.println("ari nata di");
                               System.out.println(i);
                               System.out.println(inum);
                                infixarray.add(i-inum,cn);
                                System.out.println(infixarray);
                                inum = 1;
                                cn ="";
                                break;
                            }  else if (infixarray.get(i-inum).equals("1")||infixarray.get(i-inum).equals("2")||infixarray.get(i-inum).equals("3")||infixarray.get(i-inum).equals("4")||
                                   infixarray.get(i-inum).equals("5")||infixarray.get(i-inum).equals("6")||infixarray.get(i-inum).equals("7")||infixarray.get(i-inum).equals("8")||
                                   infixarray.get(i-inum).equals("9")||infixarray.get(i-inum).equals("0")||infixarray.get(i-inum).equals(".")){
                               cn = infixarray.get(i-inum)+cn;
                               infixarray.remove(i-inum);
                               inum++;
                               System.out.println(infixarray);
                               System.out.println(i);
                               if (inum > i){
                                   inum= i;
                                   System.out.println(infixarray);
                                   System.out.println(inum+"inum");
                               }
                               System.out.println(infixarray);
                           }
                        }
                }else if(i==infixarray.size()-1){
                    inum=0;
                    System.out.println();
                    while(proceed1){
                        if(infixarray.get(i-inum).equals("+")||infixarray.get(i-inum).equals("-")||infixarray.get(i-inum).equals("*")||infixarray.get(i-inum).equals("/")){
                            infixarray.add(i-(inum-1),cn);
                            System.out.println(infixarray);
                            inum = 1;
                            cn ="";
                            proceed=false;
                            break;
                        }  else if (infixarray.get(i-inum).equals("1")||infixarray.get(i-inum).equals("2")||infixarray.get(i-inum).equals("3")||infixarray.get(i-inum).equals("4")||
                                infixarray.get(i-inum).equals("5")||infixarray.get(i-inum).equals("6")||infixarray.get(i-inum).equals("7")||infixarray.get(i-inum).equals("8")||
                                infixarray.get(i-inum).equals("9")||infixarray.get(i-inum).equals("0")||infixarray.get(i-inum).equals(".")){
                            cn = infixarray.get(i-inum)+cn;
                            infixarray.remove(i-inum);
                            inum++;
                            System.out.println(infixarray);
                            System.out.println(i);
                            System.out.println(infixarray);
                        }
                    }
                }
            }
        }
*/

        }while(loop);
    }
}

