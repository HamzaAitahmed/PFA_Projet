
# Gestionnaire de Tâches

## Table des matières
1. [Description](#description)
2. [Fonctionnalités](#fonctionnalités)
3. [Technologies Utilisées](#technologies-utilisées)
4. [Installation](#installation)
5. [Base De Données](#base-de-données)
6. [Exécution](#exécution)
7. [Structure du Projet](#structure-du-projet)
8. [Contribuer](#contribuer)

---

## Description
Le **Gestionnaire de Tâches** est une application Java conçue pour faciliter l'organisation des tâches quotidiennes ou professionnelles. Cette application permet aux utilisateurs de :
- Créer des tâches avec des détails spécifiques.
- Modifier des tâches existantes.
- Supprimer des tâches une fois complétées ou non nécessaires.
- Consulter une liste complète de toutes les tâches.

L'application est basée sur une architecture moderne utilisant le framework **Spring Boot** et le gestionnaire de dépendances **Maven**, assurant une extensibilité facile et une gestion des dépendances efficace.

---

## Fonctionnalités
- **Création de Tâches** : Permet à l'utilisateur d'ajouter une nouvelle tâche avec un titre et une description.
- **Modification de Tâches** : Possibilité de mettre à jour les informations d'une tâche existante.
- **Suppression de Tâches** : Retrait des tâches terminées ou obsolètes.
- **Affichage des Tâches** : Liste complète des tâches avec leurs statuts.
- **Support des Statuts** : Les tâches peuvent être marquées comme "À faire", "En cours" ou "Terminées".

---

## Technologies Utilisées
- **Java 8+** : Langage de programmation principal.
- **Spring Boot** : Framework pour la création d'applications Java basées sur Spring.
- **Maven** : Outil de gestion de projet et de dépendances.
- **MySQL** : Base de données embarquée pour le développement et les tests.
- **Thymeleaf** : Moteur de templates pour l'interface utilisateur.

---

## Installation
### Prérequis
Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :
- **Java 8** ou une version ultérieure.
- **Maven** (si vous n'utilisez pas les scripts `mvnw` inclus).

### Étapes d'installation
1. Clonez le dépôt GitHub sur votre machine locale :
   ```bash
   git clone https://github.com/HamzaAitahmed/GestionnaireDesTaches.git
   ```
2. Accédez au répertoire du projet :
   ```bash
   cd GestionnaireDesTaches
   ```

3. Compilez le projet et téléchargez les dépendances Maven :
   ```bash
   mvn clean install
   ```

   Si vous n'avez pas Maven installé, vous pouvez utiliser le Maven Wrapper inclus dans le projet :
   ```bash
   ./mvnw clean install
   ```

---

## Base De Données
### Démarrer la base de données MySQL dans XAMPP

1. **Lancer XAMPP** : Ouvrez le panneau de contrôle XAMPP et cliquez sur le bouton **Start** à côté de **MySQL** pour démarrer le serveur MySQL.

![image](https://github.com/user-attachments/assets/c0a79d9c-e2e4-43a2-affc-ab96138b9b0a)


2. **Accéder à phpMyAdmin** : Une fois MySQL démarré, cliquez sur le bouton **Start** à côté de **Apache**  ouvrez votre navigateur et allez à l'URL suivante : `http://localhost/phpmyadmin`. Cela vous redirigera vers l'interface de gestion de la base de données phpMyAdmin.

### Importer la base de données dans phpMyAdmin

1. **Créer une nouvelle base de données** :
   - Dans phpMyAdmin, allez à l'onglet **Bases de données**.
   - Dans le champ **Créer une base de données**, entrez le nom de la base de données, dans notre cas : `gestionnairedestaches`.
   - Cliquez sur **Créer**.

<span style="color:red;">&#9888;</span> Si vous voulez choisir un autre nom pour votre BD, il faut aussi modifier le fichier **application.properties** le nom `GestionnaireDesTaches` par `NomDeVotreBD`

![image](https://github.com/user-attachments/assets/86eb37fb-60a5-4940-9811-f4cade71a430)


2. **Importer le fichier SQL** :
   - Sélectionnez la base de données que vous venez de créer dans la liste à gauche.
   - Une fois dans cette base de données, allez à l'onglet **Importer**.
   - Cliquez sur **Choisir un fichier** et sélectionnez le fichier `gestionnairedestachesVersionPFA.sql` `GestionnaireDesTaches\gestionnairedestachesVersionPFA.sql` .
   - Cliquez sur **Exécuter** pour importer les tables et les données du fichier SQL dans votre base de données.

---

## Exécution
### Lancement de l'application
Pour démarrer l'application en local, exécutez la commande suivante :
```bash
mvn spring-boot:run
```

Ou avec le Maven Wrapper :
```bash
./mvnw spring-boot:run
```

### Accès à l'application
Une fois l'application démarrée, ouvrez votre navigateur et accédez à l'adresse suivante :
```
http://localhost:8085
```
Vous verrez l'interface utilisateur du Gestionnaire de Tâches.
- **Page index** 

![image](https://github.com/user-attachments/assets/e52ae765-b12e-43b9-a94f-79b6d7597e6d)

- **Page Login**
  
![image](https://github.com/user-attachments/assets/b74bf05e-9df5-4a4a-9bfa-4eeaf9a7e942)

- **Page Acceuil** 

![image](https://github.com/user-attachments/assets/dd2fc22a-0c1f-4f93-a886-0c80c79029b9)

- **Page Tasks**
  
![image](https://github.com/user-attachments/assets/cf508dd3-0047-4740-a68a-0e81dbae46f8)


---

## Structure du Projet
Voici un aperçu de l'organisation du projet :

```plaintext
GestionnaireDesTaches/
│
├── src/                        # Code source de l'application
│   ├── main/                   # Contient les classes principales
│   │   ├── java/               # Code Java
│   │   └── resources/          # Fichiers de configuration et templates
│   └── test/                   # Tests unitaires
│
├── pom.xml                     # Fichier de configuration Maven
├── .gitignore                  # Fichier définissant les éléments à ignorer par Git
├── mvnw, mvnw.cmd              # Scripts Maven Wrapper
├── HELP.md                     # Aide pour l'application
└── README.md                   # Documentation du projet
```

### Détails des répertoires :
- **`src/main/java`** : Contient les classes Java qui définissent la logique métier, les contrôleurs, et les services de l'application.
- **`src/main/resources`** : Contient les ressources de l'application, telles que les fichiers de configuration et les templates HTML pour l'interface utilisateur (utilisant **Thymeleaf**).
- **`pom.xml`** : Ce fichier Maven gère les dépendances du projet et définit les plugins pour le cycle de vie du projet.

---

## Contribuer
Les contributions sont les bienvenues ! Si vous souhaitez améliorer ce projet, suivez ces étapes :
1. **Fork** le projet.
2. Créez une nouvelle branche pour vos modifications :
   ```bash
   git checkout -b ma-nouvelle-branche 
   ```
3. Faites vos modifications et **commit** :
   ```bash
   git commit -m "Ajout d'une nouvelle fonctionnalité"
   ```
4. Poussez vos changements sur GitHub :
   ```bash
   git push origin ma-nouvelle-branche
   ```
5. Créez une **pull request** sur le dépôt original.
