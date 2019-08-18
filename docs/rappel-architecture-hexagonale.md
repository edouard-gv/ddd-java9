---
title: Architecture hexagonale
parent: Quelques rappels
grand_parent: Modules Java 9 et Architecture hexagonale
nav_order: 1
---

## Rappel : Architecture hexagonale

* le cœur métier, agnostique de ses appelants et des technologies
* publie ses contrats (ports)
  * Services applicatifs, le in : comment on peut l'appeler 
  * Repository, le out : ce dont il a besoin
* Les adapteurs implémentent ces contrats et seront injectés.

[Suite : DDD](rappel-ddd.md)
 
 
