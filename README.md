# StackParser
Parse java stack traces from the given block of text

## Usage
```java
String log = "a big long string with stack traces in";
List<StackException> exceptions = Parser.parse(log);
System.out.println(exceptions);
```

## Maven
Repository:
```xml
<repositories>
    <repository>
        <id>rtm516-releases</id>
        <url>https://repo.rtm516.co.uk/releases/</url>
    </repository>
</repositories>
```
Dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>com.rtm516</groupId>
        <artifactId>StackParser</artifactId>
        <version>1.0.0</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```
    