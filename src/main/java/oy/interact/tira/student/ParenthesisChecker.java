package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {

      int parenthesesTotal = 0;
      int lineNumber = 1;
      int columnNumber = 0;
      boolean betweenQuotes = false;

      for (int index = 0; index < fromString.length(); index++) {
         char IndexChar = fromString.charAt(index);

         columnNumber++;

         if (IndexChar == '"') {
            betweenQuotes = !betweenQuotes;
            continue;
         }

         if (betweenQuotes) {
            continue;
         }

         if (IndexChar == '(' || IndexChar == '[' || IndexChar == '{') {
            try {
               stack.push(IndexChar);

            } catch (Exception e) {
               throw new ParenthesesException("Stack Allocation was interrupted", ParenthesesException.STACK_FAILURE,
                     lineNumber, columnNumber);
            }
            parenthesesTotal++;

         } else if (IndexChar == ')' || IndexChar == ']' || IndexChar == '}') {

            if (stack.isEmpty()) {
               throw new ParenthesesException("Too many closing parentheses at: ", lineNumber, columnNumber,
                     ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
            }

            char expected = stack.pop();
            parenthesesTotal++;

            if ((IndexChar == ')' && expected != '(') ||
                  (IndexChar == ']' && expected != '[') ||
                  (IndexChar == '}' && expected != '{')) {
               throw new ParenthesesException("Not matching parentheses at line " + lineNumber
                     + ", column " + columnNumber, lineNumber, columnNumber,
                     ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
            }

         }
         if (IndexChar == '\n') {
            lineNumber++;
            columnNumber = 0;
         }
      }

      if (!stack.isEmpty()) {
         throw new ParenthesesException("More opening tags than closing tags!", lineNumber, columnNumber,
               ParenthesesException.TOO_MANY_OPENING_PARENTHESES);
      }

      return parenthesesTotal;
   }
}