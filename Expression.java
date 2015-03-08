import java.util.*;
import java.io.*;

public class Expression {
    private ArrayList expression = new ArrayList(); //The original math expression
    private ArrayList right = new ArrayList();      //A arraylist with order like "Operator-num1-num2"
    private String result;                          //The result to return
    private String ex = "";                         //The original math expression to return.

    //Use Tokenizer to get all the math expressions.
    Expression(String input) {
        StringTokenizer st = new StringTokenizer(input, "+-*/()", true);
        while (st.hasMoreElements()) {
            expression.add(st.nextToken());
            }
        }

    //Sort the math expression to "right order".
    private void toRight() {
        Stacks aStack = new Stacks();   //A stack for the things in between.
        String operator;                //Operator is stand for represent the content inside bracket---between "(" and ")".
        int position = 0;               //The start position.
        while (true) {
            if (Calculate.isOperator((String) expression.get(position))) {

                if (aStack.top == -1 || (expression.get(position)).equals("(")) {  //When we find a bracket("operator" in my word),
                    aStack.push(expression.get(position));                         //We push the content to the Stack.

                } else {                                                            //Or if here is not the start of bracket.

                    if ((expression.get(position)).equals(")")) {                   //If it is the end of bracket, and the top of stack is not "(",

                        if (!(aStack.top()).equals("(")) {        //otherwise it will be a "()" without any contents.
                            operator = (String) aStack.pop();     //Pack it.
                            right.add(operator);                  //Add to "right".
                        }

                    } else {
                            //If priority of the item in this very position is higher than the item in the top of stack,
                        if (Calculate.priority((String) expression.get(position)) <= Calculate.priority((String) aStack.top())
                                    && aStack.top != -1) {
                            //Pop the item to operator.
                            operator = (String) aStack.pop();

                            if (!operator.equals("("))
                                //if this is not a new start for a bracket, add the item to "right".
                                right.add(operator);
                        }
                        aStack.push(expression.get(position));
                    }
                }
            } else
                right.add(expression.get(position)); //Just add a item to right.
                position++;
            if (position >= expression.size()) //Break when we touch the edge.
                break;
        }

        while (aStack.top != -1) {    //Add the rest of items to "right", make sure stack is empty.
            operator = (String) aStack.pop();
            right.add(operator);
        }
    }


    //Method to compute the result in the "right order".
    String getResult() {

        this.toRight();

        Stacks aStack = new Stacks();
        String op1, op2, is;
        Iterator it = right.iterator();

        while (it.hasNext()) {
            is = (String) it.next();
            if (Calculate.isOperator(is)) {
                op1 = (String) aStack.pop();
                op2 = (String) aStack.pop();
                aStack.push(Calculate.twoResult(is, op1, op2));
            } else
                aStack.push(is);
        }

        result = (String) aStack.pop();

        it = expression.iterator();
        while (it.hasNext()) {
            ex = ex + it.next();
        }

        return ex+ " = " +result;

    }


    //The main method ,just for test.
    public static void main(String[] args) {
        if (args.length > 0){
            Expression boya = new Expression(args[0]);
            System.out.println(boya.getResult());
            System.exit(1);
        }
        try {
            System.out.println("Input a expression:");
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String input = is.readLine().trim();
                if (input.equals("q"))
                    break;
                else {
                    Expression boya = new Expression(input);
                    System.out.println(boya.getResult());
                }
                System.out.println("Input another expression or input 'q' to quit:");
            }
            is.close();
        }
        catch (IOException e) {
        System.out.println("Wrong input!!!");

    }

    }

}






