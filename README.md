# TP 11 : API REST sécurisée avec JWT

---

### Réalisé par

**Abla MARGHOUB**

### Encadré par

**Pr. Mohamed LACHGAR**

### Module

**Techniques de Programmation Avancée**

### Établissement

**École Normale Supérieure - Université Cadi Ayyad**

---

## 1. Objectif du TP
L'objectif de ce TP est de développer une application Spring Boot sécurisée avec **authentification JWT (JSON Web Token)**.  
L'application permet :  
- La connexion des utilisateurs via un login REST.  
- La génération d’un token JWT après authentification.  
- La sécurisation des endpoints en fonction des rôles (`USER`, `ADMIN`).  
- L’accès contrôlé aux données utilisateur et aux fonctionnalités administratives.

---

## 2. Architecture du TP

### 2.1 Stack technologique

| Technologie          | Rôle dans le projet                                   |
|---------------------|-------------------------------------------------------|
| **Spring Boot**      | Framework principal pour le développement web       |
| **Spring Security**  | Gestion de l’authentification et des rôles          |
| **Spring Data JPA**  | Gestion de la persistance avec la base de données   |
| **MySQL**            | Base de données relationnelle                        |
| **Lombok**           | Génération automatique de getters, setters, etc.   |
| **jjwt (io.jsonwebtoken)** | Gestion de la génération et validation des JWT  |
| **Postman**          | Test des endpoints REST                               |

### 2.2 Structure du projet

<img width="620" height="892" alt="image" src="https://github.com/user-attachments/assets/edb01489-0076-49cb-8acc-4e82f12c2509" />

---

## 3. Résultat attendu

### 3.1 Authentification

**Requête : ```POST /api/auth/login```**

<img width="842" height="738" alt="image" src="https://github.com/user-attachments/assets/4384e6b4-d211-4542-b930-bd13ecaddfc1" />

---

### 3.2 Accès protégé (/user/profile)

<img width="826" height="669" alt="image" src="https://github.com/user-attachments/assets/db4e700e-c8f1-4191-99f7-6869d017c727" />

### 3.3 Base de donnees:

<img width="870" height="449" alt="image" src="https://github.com/user-attachments/assets/1af5f4fa-c37a-4f3b-b601-f6a10f34c038" />


