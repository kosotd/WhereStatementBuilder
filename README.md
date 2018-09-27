# WhereStatementBuilder

Class for building conditions for queries.

Usage: 
```
    String statement = WhereStatementBuilder.startBuilding()
      .addStatement("AND", "x > 10", 0)
      .addStatement("OR", "x = 0", 1)
      .addStatement("AND", "x < 20", 0)
      .endBuilding();
    Assert.assertTrue(statement.equalsIgnoreCase("(x > 10 OR (x = 0 AND x < 20))"));
```      
```
    String statement = WhereStatementBuilder.startBuilding()
      .addStatement("AND", "x > 10", 0)
      .addStatement("OR", "x = 0", 0)
      .addStatement("AND", "x < 20", 0)
      .endBuilding();
    Assert.assertTrue(statement.equalsIgnoreCase("((x > 10 OR x = 0) AND x < 20)"));
```
      
Maven:
```
    <dependency>
      <groupId>com.kosotd</groupId>
      <artifactId>where-statement-builder</artifactId>
      <version>1.0</version>
    </dependency>
    ...
    <repository>
        <id>>where-statement-builder-mvn-repo</id>
        <url>https://raw.github.com/kosotd/WhereStatementBuilder/mvn-repo/</url>
    </repository>
```
