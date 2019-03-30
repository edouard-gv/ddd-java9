## Protéger par des modules java 9 : là où j'ai trébuché. 

Le soucis semble que l'outillage n'est pas prêt. En bref: 
> Je n'ai réussi à le faire marcher qu'avec Ant !

En vrac :
- Un bug dans Maven compile 3.8.0 qui fait juste une NPE quand erreur de modules, du coup impossible de trouver ce qui lui manque (une modulepath ?).
- IntelliJ qui mélangerait class path et module path à la compilation ? 
- Après une heure, j'ai décidé d'oublier Gradle.

En tous cas, on comprend vite qu'une règle fondamentale, mais très contraignante, de Java 9, est qu'on n'a pas le droit d'avoir le même package dans deux modules ("split package").

---
[Précédent : Deux modules pour deux hexagones](./maintenant.md)
<| [Retour](https://github.com/edouard-gv/ddd-java9) 
|> [Suite : Pour aller plus loin](./suite.md)
