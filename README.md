# Projet REST API avec Jakarta EE

## 1. État d'avancement
Nous avons réalisé les principales étapes du projet :
- Création des entités avec JPA/Hibernate, incluant les relations entre elles.
- Mise en place de la persistance des données.
- Développement des DAO pour la gestion des entités.
- Implémentation des DTO et mappers (Utilisateur, Ticket...).
- Création des services REST avec Jakarta EE.
- Tests des endpoints avec Postman (CRUD opérationnel).

## 2. Fonctionnalités
### Fonctionnalités opérationnelles :
- Persistance des entités avec relations correctes.
- CRUD complet sur toutes les entités via Postman.
- Conversion fluide entre DTO et entités.
- Déploiement local des services REST accessible.
- Tests API réalisés avec succès.

### Fonctionnalités à améliorer ou ajouter :
- Gestion des erreurs avancée (ex : messages d'erreurs plus détaillés, gestion des exceptions spécifiques).
- Implémentation de l'authentification et gestion des rôles utilisateurs.
- Finalisation de la documentation avec Swagger/OpenAPI.
- Ajout d'une gestion avancée des Tickets (validation, état dynamique, expiration).

## 3. Comment démarrer le projet
### Prérequis
- Java 17+
- Maven installé
- MySQL
- Postman (pour tester l'API)

### Configuration de la base de données

2. Configurer le fichier `persistence.xml` plus précisement le persistance-unit avec le nom mysqlCon (dans `src/main/resources/META-INF/`)
```xml
<properties>
    <property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver"/>
    <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/mon_projet"/>
    <property name="jakarta.persistence.jdbc.user" value="votre_user"/>
    <property name="jakarta.persistence.jdbc.password" value="votre_mdp"/>
</properties>
```

### Lancer l'application
1. Compiler et exécuter le projet avec Maven :
```sh
mvn clean install
mvn jetty:run
```
Après il faut juste lancer le RestServer

2. Accéder aux endpoints via Postman :
```
Tickets
GET  http://localhost:8081/tickets
POST http://localhost:8081/tickets
Utilisateurs
GET  http://localhost:8081/utilisateur
POST http://localhost:8081/utilisateur
Concerts
GET  http://localhost:8081/concerts
POST http://localhost:8081/concerts

```

## 4. Prochaines étapes
- Finaliser la gestion des erreurs (codes HTTP spécifiques, logs détaillés).
- Mettre en place l'authentification.
- Améliorer la documentation API avec Swagger/OpenAPI.
- Ajouter des tests unitaires et d'intégration avec JUnit et Mockito.
- Développer la partie client en Angular