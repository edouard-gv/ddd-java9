---
title: DDD et architecture modulaire
parent: Quelques rappels
grand_parent: Modules Java 9 et Architecture hexagonale
nav_order: 2
---

## Domain Driven Design et architecture modulaire

Les architectures de ce type vont bien avec la modularisation par domaine
- isolation des sous-domaines (DDD)
- on ne partage pas ses bases de données entre sous-domaines 
- mais on peut copier des données entre sous domaines (via « caches » ou « snapshots »)
  - /!\ mais pas plusieurs golden sources) 
  
[Suite : Module et organisation équipe](rappel-organisation-modulaire.md)
