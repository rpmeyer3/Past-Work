import java.util.*;
public class StackApps {
    public static String int2Str(int aI) {
        if (aI == 0) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        boolean isNegative = false;
        if (aI < 0) {
            isNegative = true;
            aI = -aI;
        }
        do {
            stack.push((char) (aI % 10 + '0'));
            aI /= 10;
        } while (aI != 0);
        StringBuilder sb = new StringBuilder(isNegative ? "-" : "");
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * @param ex
     * @return
     */
    public static double evalPostfix(String ex) {
        Stack<Double> stack = new Stack<>();
        String nextToken;
        final String OPERATOR_PATTERN = "[-*X/+]";
        final String INTEGER_PATTERN = "\\d+";
        final String REAL_PATTERN = "\\d+\\.\\d*";
        final String OPERAND_PATTERN = REAL_PATTERN + "|" + INTEGER_PATTERN;
        final String TOKEN_PATTERN = OPERAND_PATTERN + "|" + OPERATOR_PATTERN;

        Scanner sc = new Scanner(ex);
        while ((nextToken = sc.findInLine(TOKEN_PATTERN)) != null) {
            if (nextToken.matches(OPERAND_PATTERN)) {
                stack.push(Double.parseDouble(nextToken));
            } else {
                if (stack.size() < 2) {
                    return Double.NaN;
                }
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (nextToken.charAt(0)) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        if (operand2 == 0) {
                            return Double.NaN;
                        }
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }
        sc.close();
        if (stack.size() != 1) {
            return Double.NaN;
        }
        return stack.pop();
    }

}