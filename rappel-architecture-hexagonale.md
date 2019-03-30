## Rappel : Architecture hexagonale

* le cœur métier, agnostique de ses appelants et des technologies
* publie ses contrats (ports)
  * Services applicatifs, le in : comment on peut l'appeler 
  * Repository, le out : ce dont il a besoin
* Les adapteurs implémentent ces contrats et seront injectés.

---
[Retour](https://github.com/edouard-gv/ddd-java9) 
|> [Suite : DDD](./rappel-ddd.md)
 
 