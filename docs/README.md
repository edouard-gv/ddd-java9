---
title: Modules Java 9 et Architecture hexagonale
nav_order: 1
has_children: true
has_toc: false
---

## Modules Java 9 et Architecture hexagonale

Dans la continuité des deux dernières jams de code sur la nouvelle fonctionnalité Java 9 de modules, nous vous proposerons de refactorer un code à l’ancienne, mais néanmoins propre et couvert par des tests, en deux sous-domaines hexagonaux, l'idée étant d'utiliser les modules java 9 pour marquer les frontières entre les sous-domaines.

(Merci Arnaud et Ludo !)

Si vous voulez commencer par le code, il se trouve sur [branche master](https://github.com/edouard-gv/ddd-java9) : c'est le code sous forme de monolithe organisé en couche service / entité / repository. Plus de détail [ici](./debut.md).

Le but est d'isoler le code de calcul des modes de transports dans un module maven à part, puis de protéger les deux module maven via des modules java 9. Dans le détail, c'est [là](./maintenant.md).

Une ébauche de solution se trouve sur la [branche hexagonal](https://github.com/edouard-gv/ddd-java9/tree/hexagonal) : c'est une première étape vers plus de modularité. 

Sinon vous pouvez parcourir cette table des matières.

### Quelques rappels
* [Rappel : architecture hexagonale](./rappel-architecture-hexagonale.md)
* [Rappel : DDD](./rappel-ddd.md)
* [Rappel : Module et organisation équipe](./rappel-organisation-modulaire.md)
* [Rappel : les modules Java9 (Jigsaw)](./rappel-module-java9.md)

### Branch master : d'où je suis parti
* [Une application à l'ancienne](./debut.md)

### Branche hexagonal : là où j'en suis
* [Deux modules pour deux hexagones](./maintenant.md)

### Quand je trébuche en voulant rajouter les modules java 9
* [Protéger par des modules java 9 : les limites de l'outillage](./boum.md)

### Et après ?
* [Quelques pistes](suite.md)


[Suite (architecture hexagonale)](rappel-architecture-hexagonale.md)
