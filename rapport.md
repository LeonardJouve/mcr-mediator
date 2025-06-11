# Loups-garous - Mediator

## Introduction

Durant ce projet, nous avons implémenté le jeu des Loups-garous de Thiercelieux en Java, en utilisant le pattern de
conception [Mediator](https://refactoring.guru/design-patterns/mediator). Ce pattern permet de centraliser la
communication entre les différents objets du jeu (joueurs, roles, ui…), facilitant ainsi la gestion des interactions
et des règles du jeu.

## Conception

### Mediator

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

