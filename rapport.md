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

- **Mediator** : Interface définissant les méthodes de communication entre les différents composants du jeu: UserInput, GameDisplay et les différents roles.
- **BaseRuleMediator** : Sous-classe de Mediator, implémente les règles de base du jeu.
- **WeatherMediator** : Interface définissant les méthodes à implémenter pour les sous-mediators s'occupant des événements météorologiques. Ce sous-médiator s'occupe de gérer le tour des loups garous et des villageois.
- **NormalWeatherMediator** : Implémentation de `WeatherMediator`. Définit les règles de base du jeu sans
  conditions météorologiques spéciales.
- **BloodMoonMediator** : Sous-classe de `NormalWeatherMediator`. Implémente des règles spécifiques pour une
  nuit de pleine lune, où les loups-garous peuvent attaquer deux joueurs au lieu d'un seul.
- **VillagerAdvantageMediator** :  Sous-classe de `NormalWeatherMediator`. Implémente des règles spécifiques où les
  villageois ont un avantage. Si la victime choisie par le vote est un loup garou, un nouveau vote a lieu pendant la journée.

Le mediator fait office de narrateur dans la partie. Il coordonne et les différents colleagues entre eux et les informes lorsqu'une action est nécessaire.

Une spécificité intéressante du pattern mediator intervient lorsqu'il est changé à la volée. Pour illustrer celà, nous avons décider d'ajouter un pattern stratégie au sein du `BaseRuleMediator`. Le `BaseRuleMediator` délègue certaines responsabilitées au `WeatherMediator` qui s'occupe de gérer le tour des loups garous et des villageois. Le `WeatherMediator` stocke une référence sur le `BaseRuleMediator` qui l'a créé et peut ainsi définir des conditions de transition pour changer de stratégie à la volée. Dans notre cas, les différents événements météorologiques ont 1 chance sur 2 d'être déclanchés dans le cas contraire, le temps reste clair. Dans une cas où un événement a lieu, un temps dégagé est définit pour le tour suivant.

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

### Saisies utilisateurs et affichage

Afin de respecter les principes SOLID, nous avons décider distinguer l'affichage des saisies utilisateurs. Pour celà, nous avons créé deux interfaces: `UserInput` et `GameDisplay`.
Nous avons défini deux implémentations concrètes de ces interfaces: un mode console avec la class `ConsoleDisplay` et un mode graphique (java swing) avec la class `GraphicalDisplay`. Pour le mode graphique, la class `GraphicalInput` permet de saisir les entrées utilisateurs depuis l'interface. Afin de respecter le "Dependency inversion principle", la class `GraphicalDisplay` dépend de l'interface `UserInput` on peut alors également utiliser le `RandomInput` pour les modes console ou graphiques.

## Diagramme de classe

![Diagramme de classe](./UML.png)

## Instructions de déploiement et lancement

### Prérequis

- Cloner le projet
- Java JDK et JRE

### Installation

Aucune étape spécifique est nécéssaire pour l'installation du projet

### Lancement

Executer la methode static `main` de la class `Main`

Les differentes strategies d'affichage et de saisie utilisateurs sont illustrees dans la methode. Il suffit de decommenter celle que vous souhaiter essayer.
