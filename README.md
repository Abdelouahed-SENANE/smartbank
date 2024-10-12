# SmartBank - Simulation de Crédit et Gestion des Demandes

## Description

Cette application permet aux clients de **SmartBank** de simuler la mensualité de crédits et d’envoyer des demandes de crédit en ligne. Elle inclut des fonctionnalités de gestion de demandes, avec la possibilité de filtrer les demandes et de visualiser l’historique des modifications d'état.

## Fonctionnalités

- Simulation de la mensualité de crédit basée sur les informations fournies (montant, durée, taux, etc.).
- Soumission de demande de crédit après simulation, avec validation et envoi au backend pour traitement.
- Affichage d’une liste des demandes de crédit, avec un filtre par date et par état.
- Modification de l’état d’une demande et suivi des changements avec un historique.

## Exigences Techniques

- **ORM** : JPA avec Hibernate pour la gestion des opérations de persistance.
- **Calcul de Mensualité** : Calcul réalisé côté frontend, avec validation côté backend.
- **Tests** : Tests unitaires des fonctions de calcul avec JUnit et Mockito.

## Technologies Utilisées

- **Maven** : Pour la gestion des dépendances.
- **Tomcat / Jetty / GlassFish** : Serveur d’applications.
- **Servlet, JSP, JSTL** : Technologies pour le frontend.
- **JUnit, Mockito** : Pour les tests unitaires.
- **JPA, Hibernate** : Pour la gestion de la base de données.

## Installation

1. Clonez le dépôt Git :
   ```bash
   git clone https://github.com/votre-utilisateur/smartbank-simulation-credits.git
