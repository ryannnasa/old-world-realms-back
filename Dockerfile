# Dockerfile multi-stage pour optimiser la taille de l'image

# Stage 1: Build
FROM openjdk:17-jdk-slim AS build

# Installer Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers de configuration Maven
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Télécharger les dépendances (layer séparé pour le cache Docker)
RUN mvn dependency:go-offline -B

# Copier le code source
COPY src ./src

# Construire l'application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jre-slim AS runtime

# Créer un utilisateur non-root pour la sécurité
RUN addgroup --system --gid 1001 spring && \
    adduser --system --uid 1001 --gid 1001 spring

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR depuis le stage de build
COPY --from=build /app/target/*.jar app.jar

# Changer le propriétaire du fichier
RUN chown spring:spring app.jar

# Passer à l'utilisateur non-root
USER spring:spring

# Exposer le port par défaut de Spring Boot
EXPOSE 8080

# Variables d'environnement pour optimiser la JVM
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:+UseContainerSupport"

# Point d'entrée de l'application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# Labels pour la métadonnée
LABEL maintainer="votre-email@exemple.com"
LABEL version="1.0"
LABEL description="Warhammer Battle Report API - Spring Boot Application"
