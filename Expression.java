import java.util.*;
import java.io.*;

public class Expression {
    private ArrayList expression = new ArrayList();// 存储中序表达式
    private ArrayList right = new ArrayList();// 存储右序表达式
    private String result;// 结果
    private String ex = ""; //


    // 依据输入信息创建对象，将数值与操作符放入ArrayList中
    Expression(String input) {
        StringTokenizer st = new StringTokenizer(input, "+-*/()", true);
        while (st.hasMoreElements()) {
            expression.add(st.nextToken());
            }
        }

    // 将中序表达式转换为右序表达式
    private void toRight() {
        Stacks aStack = new Stacks();
        String operator;
        int position = 0;
        while (true) {
            if (Calculate.isOperator((String) expression.get(position))) {
                if (aStack.top == -1 ||(expression.get(position)).equals("(")) {
                    aStack.push(expression.get(position));

                } else {

                    if ((expression.get(position)).equals(")")) {

                        if (!(aStack.top()).equals("(")) {
                            operator = (String) aStack.pop();
                            right.add(operator);
                        }

                    } else {

                        if (Calculate.priority((String) expression
                                .get(position)) <= Calculate.priority((String) aStack.top())
                                    && aStack.top != -1) {
                            operator = (String) aStack.pop();

                            if (!operator.equals("("))
                                right.add(operator);
                        }
                        aStack.push(expression.get(position));
                    }
                }
            } else
                right.add(expression.get(position));
            position++;
            if (position >= expression.size())
                break;
        }

        while (aStack.top != -1) {
            operator = (String) aStack.pop();
            right.add(operator);
        }
    }
     // 对右序表达式进行求值

    String getResult() {

        this.toRight();
        Stacks aStack = new Stacks();
        String op1, op2, is = null;
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



    public static void main(String avg[]) {
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






