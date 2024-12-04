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
verifier applies all given predicates and returns the object if verifications passes. throws an unchecked exception otherwise.

alternatively, you can take advantage of java's Optional API.
```java
Optional<String> obj = Verifier.forObject("hehehe")
                .unless(String::isBlank)
                .unless(String::isEmpty)
                .verifyMaybe();

```
