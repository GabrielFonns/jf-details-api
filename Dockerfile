# Estágio 1: Construir o projeto (Baixar o Maven e compilar)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Rodar o projeto (Apenas o Java para ficar leve)
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copia o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar
# Libera a porta 8080
EXPOSE 8080
# Comando final para ligar a API
ENTRYPOINT ["java", "-jar", "app.jar"]