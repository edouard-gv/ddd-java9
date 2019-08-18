---
title: Modules et organisation d'équipe
parent: Quelques rappels
grand_parent: Modules Java 9 et Architecture hexagonale
nav_order: 3
---

## Modules et organisation d'équipe

La modularisation d'un application permet d'améliorer l'organisation des équipes (loi de Conway, approche sociotechnique) 
- mapping sous-domaines / équipes, pourvoit indépendance et cohérence. 
- augmenter le frein à partager du code entre ces modules 
  - via Microservice, en y intégrant une couche http ou message-queuing : ça coûte super cher  
  - Monolithes microservice ready via ArchUnit 
  - … ou ici, via modules java 9
  
Aussi, liens externe et interne qu'il y a dans certaines équipes peut ressembler aux adapteurs de type service applicatif d'une part et adapteur repository d'autre part.
  
[Suite : les modules Java 9 (Jigsaw)](./rappel-module-java9.md)
 
