# Используем базовый образ с OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем Maven Wrapper и POM файл
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Копируем исходный код
COPY src/ src/

# Устанавливаем зависимости
RUN ./mvnw dependency:go-offline

# Копируем остальной код приложения
COPY src/main/java .

# Собираем приложение
RUN ./mvnw package

# Определяем команду для запуска приложения
CMD ["java", "-jar", "target/your-app.jar"]
