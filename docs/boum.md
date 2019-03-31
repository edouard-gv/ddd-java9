---
parent: Modules Java 9 et Architecture hexagonale
nav_order: 4
---

## Protéger par des modules java 9 : les limites de l'outillage.

Il ne reste plus qu'à rajouter les fichier module-info.java... et là, je trébuche. 

Le soucis semble que l'outillage n'est pas prêt. En bref: 
> Je n'ai réussi à le faire marcher qu'avec Ant !

En vrac :
- Un bug dans Maven compile 3.8.0 qui fait juste une NPE quand erreur de modules, du coup impossible de trouver ce qui lui manque (une modulepath ?).
- IntelliJ qui mélangerait class path et module path à la compilation ? 
- Après une heure, j'ai décidé d'oublier Gradle.

En tous cas, on comprend vite qu'une règle fondamentale, mais très contraignante, de Java 9, est qu'on n'a pas le droit d'avoir le même package dans deux modules ("split package").

Mais que faire donc [pour aller plus loin](suite.md) ?
