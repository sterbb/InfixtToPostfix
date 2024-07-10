package InfixtoPostfix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Controller {

    @FXML
    private TextArea processarea;

    @FXML
    private TextField txtinfix;

    @FXML
    private TextField txtpostfix;

    @FXML
    private Button btnConvert;

    @FXML
    private Text txterror;

    private ArrayList<Character> chararray = new ArrayList<Character>();
    private ArrayList<String> infixarray = new ArrayList<String>();
    private ArrayList<String> prtsarray = new ArrayList<>();
    private int condition = 0;
    private int p1 = 0;//open parenthesis
    private int p2 = 0;// close parenthesis
    private boolean loop = true;
    private  String co;
    private int decimal = 0;
    private int operators = 0;

    //input to arrays
    public void userInput(String infix){

        for (int i = 0; i < infix.length(); i++) {
            chararray.add(infix.charAt(i));
            if (infix.charAt(i) == '(') {
                p1 += 1;
            } else if (infix.charAt(i) == ')') {
                p2 += 1;
            }
        }
        // char array to string array, purpose:concatenate
        for (int i = 0; i < chararray.size(); i++) {
            infixarray.add(chararray.get(i).toString());
        }
        processarea.appendText("\n"+infixarray);

    }

    //error traps
    public void errorTraps(){
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
                    }else if (chararray.get(0).equals('-')){
                        if (chararray.get(1).equals('0') || chararray.get(1).equals('1') ||
                                chararray.get(1).equals('2') || chararray.get(1).equals('3') || chararray.get(1).equals('4') || chararray.get(1).equals('5') ||
                                chararray.get(1).equals('6') || chararray.get(1).equals('7') || chararray.get(1).equals('8') || chararray.get(1).equals('9')||chararray.get(1).equals('.')){
                            condition = 6;
                        } else if (chararray.get(1).equals('(')) {
                            condition = 3;
                            break;
                        }//check if the first element is a negative sign then if there is an operator
                        for (int i2 = 0; i2<chararray.size()-1;i2++){
                            if(chararray.get(i2).equals('+') || chararray.get(i2).equals('*') || chararray.get(i2).equals('/')||chararray.get(i2).equals('-')||chararray.get(i2).equals('^')){
                                operators+=1;
                            }
                        }
                        //check if there is an operator
                        if (operators<2){
                            condition = 7;
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
                    if (chararray.get(1).equals('-')) {
                        condition = 2;
                        break;
                    }
                }
                //check if the parenthesis is used properly
                //Open Prts, Wont check the previous
                else if (chararray.get(i).equals('(') && (i == 0)) {
                    if (chararray.get(i + 1).equals('+') || chararray.get(i + 1).equals('*') || chararray.get(i + 1).equals('/') ||chararray.get(i + 1).equals('^') || chararray.get(i + 1).equals(')')) {
                        condition = 3;
                        break;
                    }//Close Prts, Wont check the next
                } else if (chararray.get(i).equals(')') && (i == chararray.size() - 1)) {
                    if (chararray.get(i - 1).equals('+') || chararray.get(i - 1).equals('*') || chararray.get(i - 1).equals('/') || chararray.get(i - 1).equals('-')|| chararray.get(i - 1).equals('^')) {
                        condition = 3;
                        break;
                    }
                    //Open prts
                } else if (chararray.get(i).equals('(') && (i != 0 || i != chararray.size() - 1)) {
                    if (chararray.get(i + 1).equals('+') || chararray.get(i + 1).equals('*') || chararray.get(i + 1).equals('/') ||chararray.get(i + 1).equals('^')||
                            chararray.get(i - 1).equals('0') || chararray.get(i - 1).equals('1') || chararray.get(i - 1).equals('2') || chararray.get(i - 1).equals('3') || chararray.get(i - 1).equals('4') || chararray.get(i - 1).equals('5') ||
                            chararray.get(i - 1).equals('6') || chararray.get(i - 1).equals('7') || chararray.get(i - 1).equals('8') || chararray.get(i - 1).equals('9')|| chararray.get(i - 1).equals('.')) {
                        condition = 3;
                        break;
                    }
                    //close prts
                } else if (chararray.get(i).equals(')') && (i != 0 || i != chararray.size() - 1)) {
                    if (chararray.get(i - 1).equals('+') || chararray.get(i - 1).equals('*') || chararray.get(i - 1).equals('/') || chararray.get(i - 1).equals('-')||chararray.get(i - 1).equals('^') || chararray.get(i + 1).equals('0') || chararray.get(i + 1).equals('1') ||
                            chararray.get(i + 1).equals('2') || chararray.get(i + 1).equals('3') || chararray.get(i + 1).equals('4') || chararray.get(i + 1).equals('5') ||
                            chararray.get(i + 1).equals('6') || chararray.get(i + 1).equals('7') || chararray.get(i + 1).equals('8') || chararray.get(i + 1).equals('9')||chararray.get(i - 1).equals('.')) {
                        condition = 3;
                        break;
                    }
                }
                //check if there are equal open and close parenthesis
                else if (p1 != p2) {
                    condition = 4;
                    break;
                    // check if input has a letter
                } else if ((chararray.get(i) >= 'a' && chararray.get(i) <= 'z') || (chararray.get(i) >= 'A' && chararray.get(i) <= 'Z')) {
                    condition = 5;
                    break;
                    //check if decimal is used properly
                } else if(chararray.get(chararray.size()-1).equals('.')){
                    condition = 8;
                    break;
                    //check if decimal is used properly
                }else if(chararray.get(i).equals('.')){
                    if (chararray.get(i + 1).equals('+')||chararray.get(i + 1).equals('-')||chararray.get(i + 1).equals('*')||chararray.get(i + 1).equals('/')||chararray.get(i + 1).equals('(')||
                            chararray.get(i + 1).equals(')')||chararray.get(i + 1).equals('.')){
                        condition = 8;
                        break;
                    }
                    //check if the string only contains numbers,decmial, operators and parenthesis
                } else if (chararray.get(i).equals('0') || chararray.get(i).equals('1') || chararray.get(i).equals('2') || chararray.get(i).equals('3') || chararray.get(i).equals('4') || chararray.get(i).equals('5') ||
                        chararray.get(i).equals('6') || chararray.get(i).equals('7') || chararray.get(i).equals('8') || chararray.get(i).equals('9') || chararray.get(i).equals('+') || chararray.get(i).equals('-') ||
                        chararray.get(i).equals('*') || chararray.get(i).equals('/') || chararray.get(i).equals('^') ||chararray.get(i).equals('(') || chararray.get(i).equals(')')||chararray.get(i).equals('.')) {
                    condition = 6;
                }
            }
        }

        //for decimal counting
        for (int i = 0; i<infixarray.size();i++){
            if (infixarray.get(i).equals(".")){
                decimal+=1;
            }//decimal equals to zero
            if (infixarray.get(i).equals("+")||infixarray.get(i).equals("-")||infixarray.get(i).equals("*")||
                    infixarray.get(i).equals("/")||infixarray.get(i).equals("^")||infixarray.get(i).equals(")")){
                decimal=0;
            }else if (decimal>1){
                condition = 8;
                break;
            }
        }
    }

    //Negative Integer
    public void negativeInteger(){
        boolean proceed2=true;
        String negative = "-";
        while(proceed2){
            for (int i =0; i<infixarray.size(); i++){
                //if negative integer is located on the first element
                if (infixarray.get(i).equals("-")&& i ==0){
                    if (infixarray.get(i + 1).equals("1") || infixarray.get(i + 1).equals("2") || infixarray.get(i + 1).equals("3") || infixarray.get(i + 1).equals("4") || infixarray.get(i + 1).equals("5") ||
                            infixarray.get(i + 1).equals("6") || infixarray.get(i + 1).equals("7") || infixarray.get(i + 1).equals("8") || infixarray.get(i + 1).equals("9")||infixarray.get(i + 1).equals("0")|| infixarray.get(i+1).equals(".")){
                        infixarray.remove(i);
                        negative +=infixarray.get(i);
                        infixarray.remove(i);
                        infixarray.add(i, negative);
                        negative="-";
                        processarea.appendText("\n"+infixarray);
                        break;

                    }
                    // if negative integer is not located on the first element, chech if the next operator is a negative sign and have a number next to it
                }else if(infixarray.get(i).equals("+")||infixarray.get(i).equals("-")||infixarray.get(i).equals("*")||infixarray.get(i).equals("/")||infixarray.get(i).equals("^")||infixarray.get(i).equals("(")){
                    if(infixarray.get(i+1).equals("-")){
                        infixarray.remove(i+1);
                        negative +=infixarray.get(i+1);
                        infixarray.remove(i+1);
                        infixarray.add(i+1, negative);
                        negative="-";
                        processarea.appendText("\n"+infixarray);
                        break;
                    }
                }else if (i==infixarray.size()-1){
                    proceed2=false;
                }

            }

        }
    }

    //Multiple Numbers and Decimal
    public void multipleNum(){
        String cn="";
        for (int j = 0; j<infixarray.size()-1; j++ ){
            if (j == infixarray.size()-1){
                break;
                //check if current element is integer or decimal
            }else if ((infixarray.get(j).equals("1")||infixarray.get(j).equals("2")||infixarray.get(j).equals("3")||infixarray.get(j).equals("4")||
                    infixarray.get(j).equals("5")||infixarray.get(j).equals("6")||infixarray.get(j).equals("7")||infixarray.get(j).equals("8")||
                    infixarray.get(j).equals("9")||infixarray.get(j).equals("0")||infixarray.get(j).equals("-1")||infixarray.get(j).equals("-2")||
                    infixarray.get(j).equals("-3")||infixarray.get(j).equals("-4")|| infixarray.get(j).equals("-5")||infixarray.get(j).equals("-6")||
                    infixarray.get(j).equals("-7")||infixarray.get(j).equals("-8")|| infixarray.get(j).equals("-9")||infixarray.get(j).equals("-0")||
                    infixarray.get(j).equals(".")||infixarray.get(j).equals("-."))) {
                cn+=infixarray.get(j);
                infixarray.remove(j);
                processarea.appendText("\n"+infixarray);
                //if the current element is an operator
                //single digits
                if(infixarray.get(j).equals("+")||infixarray.get(j).equals("-")||infixarray.get(j).equals("*")||infixarray.get(j).equals("/")||infixarray.get(j).equals("^")||infixarray.get(j).equals(")")){
                    infixarray.add(j,cn);
                    processarea.appendText("\n"+infixarray);
                    cn ="";
                }else{
                    //mulitple digits
                    //if current operator is a number or a decimal
                    while(infixarray.get(j).equals("1")||infixarray.get(j).equals("2")||infixarray.get(j).equals("3")||infixarray.get(j).equals("4")||
                            infixarray.get(j).equals("5")||infixarray.get(j).equals("6")||infixarray.get(j).equals("7")||infixarray.get(j).equals("8")||
                            infixarray.get(j).equals("9")||infixarray.get(j).equals("0")||infixarray.get(j).equals(".")){
                        //specific condition ex. (456+45)
                        if (j == infixarray.size()-1){
                            cn+=infixarray.get(j);
                            infixarray.remove(j);
                            infixarray.add(j,cn);
                            processarea.appendText("\n"+infixarray);
                            cn ="";
                            break;
                        }
                        cn+=infixarray.get(j);
                        infixarray.remove(j);
                        //if the current element is an operator
                        if(infixarray.get(j).equals("+")||infixarray.get(j).equals("-")||infixarray.get(j).equals("*")||infixarray.get(j).equals("/")||infixarray.get(j).equals("^")||infixarray.get(j).equals(")")){
                            infixarray.add(j,cn);
                            processarea.appendText("\n"+infixarray);
                            cn ="";
                            break;
                            //if last element
                        }else if (j == infixarray.size()-1){
                            cn+=infixarray.get(j);
                            infixarray.remove(j);
                            infixarray.add(j,cn);
                            processarea.appendText("\n"+infixarray);
                            cn ="";
                            break;
                        }
                    }
                }
            }
        }
    }

    //Parenthesis
    public void parenthesis(){
        //parenthesis
        while (infixarray.contains("(") || infixarray.contains(")")) {
            for (int i = 0; i < infixarray.size(); i++) {
                //elements inside the parenthesis are transfered in another array
                if (infixarray.get(i).equals("(")) {
                    infixarray.remove(i);
                    processarea.appendText("\n"+infixarray);
                    while (!infixarray.get(i).equals(")")) {
                        prtsarray.add(infixarray.get(i));
                        processarea.appendText("\n"+prtsarray);
                        infixarray.remove(i);
                        processarea.appendText("\n"+infixarray);
                    }
                    break;
                } else if (infixarray.get(i).equals(")")) {
                    infixarray.remove(i);
                    processarea.appendText("\n"+infixarray);
                    //infix to postfix inside the parenthesis
                    I2P(prtsarray);
                    infixarray.add(i, prtsarray.get(0));
                    processarea.appendText("\n"+infixarray);
                    prtsarray.remove(0);
                    break;
                }
            }

        }
    }


    //Process infix to postfix
    public void I2P(ArrayList array){
        while (array.size() != 1) {
            //infix to postfix inside the parenthesis
            while (array.contains("^")){
                for (int i2 = 0; i2 < array.size(); i2++) {
                    if (array.get(i2).equals("^")) {
                        co = (String)array.get(i2 - 1) + array.get(i2 + 1) +array.get(i2);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.remove(i2- 1);
                        processarea.appendText("\n"+array);
                        array.add(i2 - 1, co);
                        processarea.appendText("\n"+array);
                        break;
                    }
                }
            }

            while (array.contains("*") || array.contains("/")) {
                for (int i2 = 0; i2 < array.size(); i2++) {
                    if (array.get(i2).equals("*") || array.get(i2).equals("/")) {
                        co = (String)array.get(i2 - 1) + array.get(i2 + 1) + array.get(i2);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.add(i2 - 1, co);
                        processarea.appendText("\n"+array);
                        break;
                    }
                }
            }
            while (array.contains("+") || array.contains("-")) {
                for (int i2 = 0; i2 < array.size(); i2++) {
                    if (array.get(i2).equals("+") || array.get(i2).equals("-")) {
                        co = (String)array.get(i2 - 1) + array.get(i2 + 1) + array.get(i2);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.remove(i2 - 1);
                        processarea.appendText("\n"+array);
                        array.add(i2 - 1, co);
                        processarea.appendText("\n"+array);
                        break;
                    }
                }
            }
        }
    }

    public void condition(){
        switch (condition) {
            case 1:
                txterror.setText("Infix Error: Consecutive operators detected\n");
                txtpostfix.setText("");
                txterror.setVisible(true);
                processarea.appendText("\n"+"Infix Error: Consecutive operators detected\n");
                break;
            case 2:
                txterror.setText("Infix Error: The FIRST or the LAST element is an operator detected\n");
                txtpostfix.setText("");
                txterror.setVisible(true);
                processarea.appendText("\n"+"Infix Error: The FIRST or the LAST element is an operator detected\n");
                break;
            case 3:
                txterror.setText("Infix Error: Parentheses error detected\n");
                txtpostfix.setText("");
                txterror.setVisible(true);
                processarea.appendText("\n"+"Infix Error: Parentheses error detected\n");
                break;
            case 4:
                txterror.setText("Infix Error: Parentheses error(not equal) detected\n");
                txtpostfix.setText("");
                txterror.setVisible(true);
                processarea.appendText("\n"+"Infix Error: Parentheses error(not equal) detected\n");
                break;
            case 5:
                txterror.setText("Input error: A letter is detected\n");
                txtpostfix.setText("");
                txterror.setVisible(true);
                processarea.appendText("\n"+"Input error: A letter is detected\n");
                break;
            case 6:
                negativeInteger();
                multipleNum();
                parenthesis();
                I2P(infixarray);
                txtpostfix.setText(infixarray.get(0));
                txterror.setVisible(false);
                processarea.appendText("\n"+"Postfix Notation: " + infixarray.get(0));
                System.out.println("Postfix Notation: " + infixarray.get(0));
                break;
            case 7:
                txterror.setText("Input error: No operator detected\n");
                txtpostfix.setText("");
                txterror.setVisible(true);
                processarea.appendText("\n"+"Input error: No operator detected\n");
                break;
            case 8:
                txterror.setText("Input error: Decimal not properly used");
                txtpostfix.setText("");
                txterror.setVisible(true);
                processarea.appendText("\n"+"Input error: Decimal not properly used");
        }
    }



    @FXML
    void Convert(ActionEvent event) {
        processarea.setText("Process:");
        userInput(txtinfix.getText());
        errorTraps();
        condition();
        chararray.clear();
        infixarray.clear();
        prtsarray.clear();
        condition=0;
        p1 =0;
        p2 = 0;
        loop = true;
        co="";
        decimal=0;
        operators = 0;

    }


}
