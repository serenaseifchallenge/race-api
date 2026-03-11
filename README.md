# TP — Création d'une API REST : Gestion d'inscriptions à une course

## Objectif

L'objectif de ce TP est de concevoir et développer une **API REST** permettant de gérer l'inscription de coureurs à différentes courses.

# Stack technique


* **Java 17**
* **Spring Boot 4**
* **Spring Web**
* **Spring Data JPA**
* **Flyway**


* **Docker**
* **PostgreSQL**
* **Adminer**

---

# Fork le projet

Avant de commencer le TP, forkez le projet pour avoir votre propre repo associé au TP :
![fork.png](fork.png)


# Installation et lancement du projet

## 1 - Clone le projet 

git clone <url-du-depôt>

## 1 — Démarrer la base de données

Pour lancer votre base de données SQL et Adminer :

```bash
docker compose up -d
```

## 2 — Accéder à Adminer

URL :

```
http://localhost:8081
```

Paramètres de connexion :

| Champ    | Valeur        |
| -------- |---------------|
| System   | PostgreSQL    |
| Server   | race_postgres |
| Username | race          |
| Password | race          |
| Database | race_db       |

---

## 3 — Lancer l'application

Lancer votre configuration directement sur IntelliJ.

Sinon, depuis votre IDE ou en ligne de commande :

```bash
mvn spring-boot:run
```

L'API sera disponible sur :

```
http://localhost:8080
```

---

## 4 - Endpoints

Coureurs (/runners) : 
GET /runners : Liste tous les coureurs

GET /runners/{id} : Récupérer un coureur

PUT /runners/{id} : Modifier un coureur

POST /runners : Crée un coureur (Email avec @ obligatoire)

GET /runners/{id}/races : Liste les courses d'un coureur

DELETE /runners/{id} : Supprimer un coureur

Courses (/races) : 
GET /races : Liste les courses (Filtre possible sur l'adresse)

GET /races/{id} : Récupérer une course

POST /races : Créer une course

GET /races/{id}/participants/count : Compter le nombre de participants inscrits par course

Inscriptions (/races/{raceId}/registrations)
POST /races/{raceId}/registrations : Inscrire un coureur à une course

GET /races/{raceId}/registrations : Liste tous les participants à une course

# 5 Règles Métier Implémentées

Validation Email : Erreur 400 si le mail ne contient pas de @.

Doublon : Erreur 409 si un coureur s'inscrit deux fois à la même course.

Capacité : Erreur 409 si la course a atteint son maxParticipants.

Existence : Erreur 404 si une ressource (Runner ou Race) est introuvable.