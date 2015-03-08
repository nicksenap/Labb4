
public class Calculate {

    // Check if it is Operator.
    public static boolean isOperator(String operator) {
        return    (operator.equals("+") || operator.equals("-")
                || operator.equals("*") || operator.equals("/")
                || operator.equals("(") || operator.equals(")"));
    }

    // Set priority.
    public static int priority(String operator) {
        if (operator.equals("+") || operator.equals("-")
                || operator.equals("("))
            return 1;
        else if (operator.equals("*") || operator.equals("/"))
            return 2;
        else
            return 0;

    }


    // Calulate between 2.
    public static String twoResult(String operator, String a, String b) {
        try {
            String op = operator;
            String rs = new String();
            double x = Double.parseDouble(b);
            double y = Double.parseDouble(a);
            double z = 0;
            if (op.equals("+"))
                z = x + y;

            else if (op.equals("-"))
                z = x - y;

            else if (op.equals("*"))
                z = x * y;

            else if (op.equals("/"))
                z = x / y;

            else
                z = 0;

            return rs + z;

        } catch (NumberFormatException e) {
            System.out.println("input has something wrong!");
            return "Error";

        }

    }

}


