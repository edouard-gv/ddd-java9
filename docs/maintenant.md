---
parent: Modules Java 9 et Architecture hexagonale
nav_order: 3
---

## Deux hexagones, deux modules

Voilà où en est la [branche haxagonal](https://github.com/edouard-gv/ddd-java9/tree/hexagonal). 

L'application a été séparée en deux composants (sous forme de module maven), avec un petit composant Logistic qui va petit à petit se détacher du reste. Le reste est mis dans un composant Sales. 

Côté Logistic, on pourra décider que
- Logistic ne connait pas sales
- Logistic publie son API via la racine de son domaine. Mais pas d'ACL entrant (pas de services application.) 
- On essaye de revoir l'organisation des packages au moins pour Logistic 
- On remplace les services facades, par des classes a responsabilité unique (SRP) 

Côté Sales, on a un peu avancé aussi, puisque Sales ne connaît logistic que via sa propre couche d'anti-corruption (ACL) : même si le composant Sales n'est pas refactoré, il n'accède à Sales que par un adapteur. 

Les étapes du refactoring proposé sont a priori explicitées dans l'historique git de la [branche haxagonal](https://github.com/edouard-gv/ddd-java9/tree/hexagonal).
1. Creation des modules maven et des sous packages.
1. Isolation du code logistique dans test et service spécifique.
1. Déplacement de ces classes dans le nouveau module maven, avec copie et adpatation des classes qu'on souhaite dupliquer dans les deux sous-domaines.
1. Modification des packages dans logistic pour être plus hexagonal.
1. Utilisation d'interface pour accéder depuis l'extérieur au code métier de logistic
1. Suppression d'Hibernate dans logistic
1. Côté sales, isolation de l'accès à logistic dans un adapteur, via interface
1. Et, enfin, tentative d'utilisation des modules java 9 pour assurer que les brebis seront bien gardées à long terme

Et ça n'a pas marché du premier coup ! Pour cela, lisez [là où j'ai trébuché](boum.md).
