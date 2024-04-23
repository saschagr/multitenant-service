# Multitenat Beispiel

Beipielimplementierung einer einfachen Multitenant Anwendung mit REST.
Implementiert mit JakartaEE, Wildfly und Datenbank PostgreSQL

## Bauen und Ausführen

Datenbank starten

    docker compose up 

Bauen und ausführen mit maven

    mvn clean package wildfly:run


Container und Volumes löschen

    docker rm multitenant-postgres
    docker volume rm multitenant-service_postgres-data

## Postman Collection

befindet sich im Verzeichnis __*postman*__
