# GRPC Spring Boot Starter SPEL Bean Reference issue

This is a demo project to demonstrate this
issue https://github.com/LogNet/grpc-spring-boot-starter/issues/310

There are two users configured in the system with the following username and password that can be
used for testing.

- user/password
- admin/password

There is a `PermissionService` service that is intended to be used in Spring
Security `@PreAuthorize` statements.

The permission service can be accessed without issue in a Rest Controller (see `TestController`),
but not in a GRPC service (see `GreetingService`).

Both are using basic authentication.

The following error is throwing.

`Caused by: org.springframework.expression.spel.SpelEvaluationException: EL1057E: No bean resolver registered in the context to resolve access to bean 'permissionService'
at org.springframework.expression.spel.ast.BeanReference.getValueInternal(BeanReference.java:51) ~[spring-expression-5.3.23.jar:5.3.23]
at org.springframework.expression.spel.ast.CompoundExpression.getValueRef(CompoundExpression.java:55) ~[spring-expression-5.3.23.jar:5.3.23]
at org.springframework.expression.spel.ast.CompoundExpression.getValueInternal(CompoundExpression.java:91) ~[spring-expression-5.3.23.jar:5.3.23]
at org.springframework.expression.spel.ast.SpelNodeImpl.getTypedValue(SpelNodeImpl.java:117) ~[spring-expression-5.3.23.jar:5.3.23]
at org.springframework.expression.spel.standard.SpelExpression.getValue(SpelExpression.java:309) ~[spring-expression-5.3.23.jar:5.3.23]
at org.springframework.security.access.expression.ExpressionUtils.evaluateAsBoolean(ExpressionUtils.java:30) ~[spring-security-core-5.7.3.jar:5.7.3]
... 23 common frames omitted`

Interestingly enough, using Spring's `hasRole` works fine in both. It allows and blocks as expected.

## Endpoints

REST localhost:8080/test
GRPC localhost:6565/com.pcalouche.protos.GreetingService/sayHello

Valid Basic Auth Header for user: `Basic dXNlcjpwYXNzd29yZA==`
Valid Basic Auth Header for admin: `Basic YWRtaW46cGFzc3dvcmQ=`