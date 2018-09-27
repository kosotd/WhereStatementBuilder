package com.kosotd.logic;

import org.junit.Assert;
import org.junit.Test;

public class WhereStatementBuilderTest {

    @Test
    public void test1(){
        String statement = WhereStatementBuilder.startBuilding().addStatement("AND", "x > 10", 0).endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("(x > 10)"));
    }

    @Test
    public void test2(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x > 10", 0)
                .addStatement("OR", "x = 0", 0)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("(x > 10 OR x = 0)"));
    }

    @Test
    public void test3(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x > 10", 0)
                .addStatement("OR", "x = 0", 0)
                .addStatement("AND", "x < 20", 0)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("((x > 10 OR x = 0) AND x < 20)"));
    }

    @Test
    public void test4(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x > 10", 0)
                .addStatement("OR", "x = 0", 1)
                .addStatement("AND", "x < 20", 0)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("(x > 10 OR (x = 0 AND x < 20))"));
    }

    @Test
    public void test5(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x > 10", 0)
                .addStatement("OR", "x = 0", 1)
                .addStatement("AND", "x < 20", 0)
                .addStatement("OR", "x = 6", 2)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("((x > 10 OR (x = 0 AND x < 20)) OR x = 6)"));
    }

    @Test
    public void test6(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x > 10", 0)
                .addStatement("OR", "x = 0", 1)
                .addStatement("AND", "x < 20", 2)
                .addStatement("OR", "x = 6", 0)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("((x > 10 OR x = 0) AND (x < 20 OR x = 6))"));
    }

    @Test
    public void test7(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x > 10", 0)
                .addStatement("OR", "x = 0", 2)
                .addStatement("AND", "x < 20", 1)
                .addStatement("OR", "x = 6", 0)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("(x > 10 OR (x = 0 AND (x < 20 OR x = 6)))"));
    }

    @Test
    public void test8(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x = 10", 0)
                .addStatement("OR", "x > 10", 4)
                .addStatement("AND", "x = 0", 2)
                .addStatement("OR", "x < 20", 3)
                .addStatement("AND", "x = 6", 0)
                .addStatement("AND", "x > 10", 2)
                .addStatement("OR", "x = 0", 1)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("(x = 10 OR ((x > 10 AND x = 0) OR ((x < 20 AND x = 6) AND (x > 10 OR x = 0))))"));
    }

    @Test
    public void test9(){
        String statement = WhereStatementBuilder.startBuilding()
                .addStatement("AND", "x = 10", 0)
                .addStatement("OR", "x > 10", 0)
                .addStatement("AND", "x = 0", 0)
                .addStatement("OR", "x < 20", 0)
                .addStatement("AND", "x = 6", 0)
                .addStatement("AND", "x > 10", 0)
                .addStatement("OR", "x = 0", 0)
                .endBuilding().trim();
        System.out.println("---" + statement + "---");
        Assert.assertTrue(statement.equalsIgnoreCase("((((((x = 10 OR x > 10) AND x = 0) OR x < 20) AND x = 6) AND x > 10) OR x = 0)"));
    }
}

