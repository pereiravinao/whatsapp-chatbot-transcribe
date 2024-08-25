# Usando uma imagem base de Java 21
FROM openjdk:21-rc-jdk

ENV LANG=C.UTF-8
ENV JAVA_VERSION=21


RUN mkdir /app

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o jar da aplicação para o diretório de trabalho
COPY target/*.jar /app/whatsapp-chatbot-transcribe.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "app/whatsapp-chatbot-transcribe.jar"]

# Expondo a porta 8443
EXPOSE 8443
