# Loups-garous - Mediator

## Introduction

Durant ce projet, nous avons implémenté le jeu des Loups-garous de Thiercelieux en Java, en utilisant le pattern de
conception [Mediator](https://refactoring.guru/design-patterns/mediator). Ce pattern permet de centraliser la
communication entre les différents objets du jeu (joueurs, roles, ui…), facilitant ainsi la gestion des interactions
et des règles du jeu.

Aussi, nous avons ajouté au jeu une fonctionnalité particulière, des changements aléatoires du médiateur gérant la
partie. Celà nous permet de changer les règles du jeu en cours de partie, rendant le jeu plus dynamique. Ce changement à
chaud est réalisé grâce au Strategy Pattern.

### Difficultés rencontrées

Lors de la réalisation de ce projet, nous avons rencontrés quelques difficultés. Tout d'abord, nous avons mis du temps à trouver un projet qui convienne un minimum à tous les membres du groupe, ainsi qu'à l'équipe enseignante. De plus, une fois le thème choisi, nous avons perdu énormément de temps à essayer d'avoir un jeu fonctionnel avec TCP/IP. En effet, nous comptions intiallement implémenter une version mutli-joueur du jeu es Loups-garous de Thiercelieux, mais nous avions largement sous-estimé la charge de travail importante que cela représenatait. Ainsi, une fois la moitié du temps du projet dépassée, n'ayant que peu avancé dans la partie multi-joueur du projet, et estimant qu'il ne devrait pas s'agir de l'essentiel de nos efforts pour un projet sur le pattern "Mediator", nous avons décidé d'abandonner cet aspect, et de simplement implémenter une version "jouable" sur un seul appareil.

## Conception

### Mediator

Les classes et interfaces suivantes sont utilisées pour implémenter le pattern Mediator :

- **Mediator** : Interface définissant les méthodes de communication entre les différents composants du jeu. (Sauf la
  gestion des tours des loups-garous et des villageois). ... (à compléter)
- **BaseRuleMediator** : Sous-classe de Mediator, implémente les règles de base du jeu.
- **WeatherMediator** : Interface ... (à compléter)
- **NormalWeatherMediator** : Implémentation de `WeatherMediator`. Définit les règles de base du jeu sans
  conditions météorologiques spéciales.
- **BloodMoonMediator** : Sous-classe de `NormalWeatherMediator`. Implémente des règles spécifiques pour une
  nuit de pleine lune, où les loups-garous peuvent attaquer deux joueurs au lieu d'un seul.
- **VillagerAdvantageMediator** :  Sous-classe de `NormalWeatherMediator`. Implémente des règles spécifiques où les
  villageois ont un avantage, en pouvant voter pour éliminer un joueur supplémentaire pendant la journée.

### Roles

Classe abstraite `Role` représentant un rôle dans le jeu. Elle est responsable de la gestion des actions spécifiques à
chaque rôle. Contient un lien sur le mediator avec lequel elle interagit pour effectuer des actions. Contient aussi un
champ `isAlive` pour savoir si le joueur est encore en vie.

Rôles implémentés :

- **Loup-garou** : Peuvent voter afin de choisir un joueur à éliminer pendant la nuit.
- **Villageois** : Ne possède pas de pouvoir spécial, mais participe aux discussions durant le jour et peut voter pour
  éliminer un joueur.
- **Voyante** : Peut découvrir le rôle d'un joueur pendant la nuit.
- **Sorcière** : Possède deux potions, une pour sauver un joueur et une pour tuer un joueur, qu'elle peut utiliser
  pendant la nuit.

## Diagramme de classe

![Diagramme de classe](./UML.png)

## Instructions de déploiement et lancement

### Prérequis

- Java

### Installation

### Lancement

