# NewsProject

# This miniApp was developed using Kotlin and uses the following components:
- Jetpack compose
- Coroutines 
- Dispatchers
- Flow and StateFlow
- Clean architecture (Domain, Data, Presentation) with MVVM in Presentation
- Repository , Use case and Singleton pattern
- Navigation component by compose
- Dagger Hilt (Dependency injection)
- Retrofit , Moshi and OkHttpClient
- MockK and Junit for testing , Testing Flow with turbine
- Github

Sur ce projet j'ai pas poussé loin les tests j'aurais pu utilisé Dagger hilt ui testing , UI testing by compose , unit testing by truth google ....


Resumé : 

Pour ce mini projet from scratch , en général pour un court projet je suis plutot sur du MVC-clean architecture.

En tenant compte de ce qui était demandé , les tests surtout . et pour un projet de taille un vrai projet comme vous l'avez indiqué . j'opte pour une clean architecture + (MVVM sur la partie présentation) , en utilisant des differents patterns use case , repository , singleton et les 5  principes SOLID , ce que j'ai utilisé mais pas a 100 % sur ce mini projet , je préfére parler en général . de l'unique responsabilité les classes devront avoir que leurs propres methodes de pouvoir modifier pour ajouter des interface , héritage , de l'injection de dépendance comme avec koin ou dagger hilt (ce que j'ai utilisé sur ce projet). déja en choisissant cette architecture on opte a séparer le code en 3 couches ou modules pour faciliter la modification , les tests unitaire et  refactorisations ... 


Pour le MVVM sur la partie présentation , j'utilise les viewModel pour le databinding et du jetpack compose pour créer les interfaces et du livedata pour observer les données et modifier les interfaces utilisateurs. sinon les fragments et activities 

Pour la partie Data sur ce projet je n'ai pas utilisé une BDD pour stocker les données ces derniers temps j'ai travaillé avec Room , realm (une classe pour gérer notre model et stocker  les données .retrofit > Romm DB "fromSource toSource"). j'ai directement utilisé retrofit avec moshi remotly et c'est la qu'on implemente notre repository le responsable pour communiquer avec datasource en local et en remote en general .

Et puis on a le domain c'est la qu'on trouve notre business logic sur lequelle je développe notre use case pattern et puis les coroutines , dispatchers et flow pour exécuter le code en asynchrone , ce module est independant des deux autres et est chargé d'appels au repository de data adéquat

Junit et Mokk pour les tests unitaire . CoilImage ou Glide pour la partie Image .


Sinon j'ai du voir par avant Sonarqube pour la qualité de code , jenkins pour l'intégration continue , j'ai utilisé zepelin , figma click up pour le design , jira gestion des bugs , confluence gestion de projet .
Bitbucket avec jira ,confluence , git , jenkins .

Sinon en général aussi , j'opte pour les Sprint Review question de regarder/proposer les bonnes pratiques (ne jamais ecrire en dure , ecrire des valeurs significatifs ex : StringRessource(nom_du_screen_SaValeur)) . 

En ce qui concerne l'api key , je l'ai mis et laissé le mien dans la classe "Constants" , j'aurais pu faire une reference dans le fichier gradle de l'app et vous laisser créer votre key le mettre dans local.properties

Au final , pour le versionning , j'avais déja utilisé SVN et git , avec un desktop ou ligne de commande , gérer les branches pour les nouvelles features , correction ...