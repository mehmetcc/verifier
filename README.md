# verifier

## why?
i thought writing a fluent verification library for java would be cool.

## how to use?
it's all fluent:
```java
String obj = Verifier.forObject("hehehe")
                .unless(String::isBlank)
                .unless(String::isEmpty)
                .verify();
```
verifier applies all the given predicates and returns the object if verifications passes. throws an unchecked exception otherwise.