---
parent: Modules Java 9 et Architecture hexagonale
nav_order: 2
---

## Une application propre, mais à l'ancienne 

Ce que vous trouverez sur La [branche master](https://github.com/edouard-gv/ddd-java9) : les vielles bonnes pratiques 

(Merci Cyrille et Arnauld !) 

Principes
- Des services façades 
- Des packages par couche. 
- Drivé par un model CRUD, on savait que c'était des bdd derrière. Il va peut-être falloir apprendre à s'en abstraire. 

L'idée c'est que ça devait ressembler à un monolithe de PME bien écrit. 

Mais c'est déjà un peu plus :
- Seul ce ce qui a une implémentation qui peut dépendre de la technologie a une interface pour injection
  - les services n'ont pas d'interface car n'ont que du code métier (pas d'IoC via DI du coup) 
- Pas d'attribut en type primitif mais des value objects 
- Approche tests first
  - Il n'y que les tests qui fonctionnent, pas possible de lancer l'application. 
  - Pas de test sur les get des Optional (car pas les tests écrits).

Une fois cela fait, l'objectif a été de refactorer cela en [deux modules pour deux hexagones](maintenant.md).
