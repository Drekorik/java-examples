# Java 11 features in comparison to Java 8

## Private methods in interfaces

```java
private interface I1 {
    private void thisIsPrivateMethod(){
        System.out.println("This is private method");
    }

    private static void thisIsPrivateStaticMethod(){
        System.out.println("This is private static method");
    }
}
```

## Optional Class

- `Optional.ifPresentOrElse()`
- `Optional.or()`
- `Optional.stream()`

## Immutable Collection Factory

- `Set.of("John", "George", "Betty");`
- `Map.of("John", 1, "Betty", 2);`

## CompletableFuture improvements

- `CompletableFuture<String> newOne = future.orTimeout(10, TimeUnit.SECONDS);`
- `CompletableFuture<String> newOne = future.completeOnTimeout("value in case of timeout", 10, TimeUnit.SECONDS);`

## Enhancements in @Deprecated

`@Deprecated(since = "4", forRemoval = true)`

## Keyword “var” (Local-Variable Type Inference)

```java
var company = "Codete"; // infers String
var characters = company.toCharArray(); // infers char[]

var numbers = List.of("a", "b", "c");
for (var nr : numbers) {
   System.out.print(nr + " ");
}
```

## Additions to JDK

Overloaded version of Optional.orElseThrow() which doesn’t take any parameters and throws NoSuchElementException by default.

API for creating unmodifiable collections has been improved by adding List.copyOf(), Set.copyOf(), Map.copyOf() methods, which are factory methods for creating unmodifiable instances from existing collections. Also, Collectors class has some new methods like toUnmodifiableList, toUnmodifiableSet, toUnmodifiableMap.

## Local-Variable Syntax for Lambda Parameters

```java
IntFunction<Integer> doubleIt1 = (@Valid final int x) -> x * 2;   // Compiles since Java 8
IntFunction<Integer> doubleIt2 = (@Valid final var x) -> x * 2;   // Will compile from Java 11
```

## Launch Single-File Source-Code Programs

`java SimpleProgram.java`

## Java String Methods

- `isBlank()`
- `lines()`
- `strip(), stripLeading(), stripTrailing()`
- `repeat(int)`

## Nested Based Access Control

Before Java 11 this was possible:

```java
public class Main {
 
    public void myPublic() {
    }
 
    private void myPrivate() {
    }
 
    class Nested {
 
        public void nestedPublic() {
            myPrivate();
        }
    }
}
```

private method of the main class is accessible from the above-nested class in the above manner.
But if we use Java Reflection, it will give an IllegalStateException.

```java
Method method = ob.getClass().getDeclaredMethod("myPrivate");
method.invoke(ob);
```

Java 11 nested access control addresses this concern in reflection.
`java.lang.Class` introduces three methods in the reflection API: `getNestHost()`, `getNestMembers()`, and `isNestmateOf()`.

##  Remove the Java EE and CORBA Modules

The modules were already deprecated in Java 9. They are now completely removed.

Following packages are removed: java.xml.ws, java.xml.bind, java.activation, java.xml.ws.annotation, java.corba, java.transaction, java.se.ee, jdk.xml.ws, jdk.xml.bind

## HTTP Client

Java 11 standardizes the Http CLient API.
The new API supports both HTTP/1.1 and HTTP/2. It is designed to improve the overall performance of sending requests by a client and receiving responses from the server. It also natively supports WebSockets.

## Reading/Writing Strings to and from the Files

```java
Path path = Files.writeString(Files.createTempFile("test", ".txt"), "This was posted on JD");
System.out.println(path);
String s = Files.readString(path);
System.out.println(s); //This was posted on JD
```

