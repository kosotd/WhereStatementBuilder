package com.kosotd.logic;

public class WhereStatementBuilder {

    private WhereStatementBuilder(){}

    public static InternalBuilder startBuilding(){
        return new InternalBuilder();
    }

    public static class InternalBuilder {
        private InternalBuilder(){}

        private int statementCount = 0;
        private Statement curr, prev, head = null;
        private String firstCondition = "";
        private int maxPriority = -1;

        public InternalBuilder addStatement(String logicalStatement, String condition, Integer priority){
            if (priority > maxPriority)
                maxPriority = priority;
            if (statementCount == 0){
                statementCount++;
                firstCondition = condition;
            } else if (statementCount == 1) {
                statementCount++;
                curr = new Statement(logicalStatement, priority);
                curr.left = new Condition(firstCondition);
                curr.right = new Condition(condition);
                prev = curr;
                head = curr;
            } else {
                curr.next = new Statement(logicalStatement, priority);
                curr = curr.next;
                curr.prev = prev;
                curr.left = prev.right;
                curr.right = new Condition(condition);
                prev = curr;
            }
            return this;
        }

        public String endBuilding(){
            if (head == null) return "(" + firstCondition + ")";

            for (int priority = 0; priority <= maxPriority; ++priority){
                Statement curr = head;
                while (curr != null){
                    if (priority == curr.priority && !curr.hasBrackets)
                        setBrackets(curr);
                    curr = curr.next;
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            while (head != null){
                stringBuilder.append(head.left.data).append(" ");
                stringBuilder.append(head.data).append(" ");
                if (head.next == null)
                    stringBuilder.append(head.right.data);
                head = head.next;
            }
            return stringBuilder.toString();
        }

        private void setBrackets(Statement statement){
            statement.hasBrackets = true;

            Statement left = getLeftWithoutBrackets(statement);
            left.left.data = "(" + left.left.data;

            Statement right = getRightWithoutBrackets(statement);
            right.right.data = right.right.data + ")";
        }

        private Statement getLeftWithoutBrackets(Statement statement){
            if (statement.prev != null) {
                Statement left = statement.prev;
                while (left.hasBrackets) {
                    statement = left;
                    left = left.prev;
                    if (left == null)
                        break;
                }
            }
            return statement;
        }

        private Statement getRightWithoutBrackets(Statement statement) {
            if (statement.next != null) {
                Statement right = statement.next;
                while (right.hasBrackets) {
                    statement = right;
                    right = right.next;
                    if (right == null)
                        break;
                }
            }
            return statement;
        }
    }

    private static class Condition {
        Condition(String data){
            this.data = data;
        }
        String data;
    }

    private static class Statement {
        public Statement(String data, int priority) {
            this.data = data;
            this.priority = priority;
        }
        boolean hasBrackets;
        String data;
        Statement next, prev;
        Condition left, right;
        int priority;
    }
}