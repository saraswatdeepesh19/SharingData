logging.level.org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory=DEBUG
server:
  port: 1234

spring:
  cloud:
    gateway:
      routes:
        - id: user-management-route
          uri: http://hkw2501ABCD:8007 # Correct target service
          predicates:
            - Path=/v1/api/user-management/**
          filters:
            - RewritePath=/v1/api/user-management/(?<segment>.*), /user/${segment}
