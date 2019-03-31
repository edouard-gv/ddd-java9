---
parent: Modules Java 9 et Architecture hexagonale
nav_order: 5
---

## Quelques pistes 

### Modularisation
Séparer sales en deux modules du même sous domaine : l'ACL et le domain. Pour ne permettre qu'à l'ACL d'avoir besoin de modules extérieurs. Plus j'y réfléchis, plus c'est la bonne piste.
- Un module adapteur, le seul à pouvoir accéder aux autres modules.
- Un module domain, qui ne publie que le package contenant ses interfaces, ie ses ports (y compris les value objects pour l'appeler !), et qui ne peut accéder à rien d'autre qu'à lui même et au framework d'injection.

### Extension du domaine de la lutte

Plusieurs pistes :
- Ajouter un deuxième service dans logistic, mais qui n'est pas publié et n'a donc pas besoin d'interface (de port).
- Assumer qu'on n'est pas complètement héxagonal : entre sales et logistic, il n'y a pas double couche d'adpapteur. En décidant que sales dépendait de logisitic, logistic n'a pas besoin d'adapteur côté entrant. On pourrait du coup étendre l'exemple dans le cas où les dépendances vont dans les deux sens.


### Outillage
Réparer Maven. 

