FROM openjdk:8

ADD ./target/Leanpay-0.0.1-SNAPSHOT.jar leanpay.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=container", "-jar", "leanpay.jar", "container-entrypoint"]
EXPOSE 8083