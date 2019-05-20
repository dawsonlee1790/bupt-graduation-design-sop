FROM java:8
MAINTAINER bupt.dawsonlee1790
ADD build/libs/bupt-graduation-design-sop-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# --spring.profiles.active=product 指定部署环境为product