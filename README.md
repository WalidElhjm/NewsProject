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

Sur ce projet j'ai pas poussé loin les tests avec Dagger hilt ui testing , UI testing by compose , unit testing by truth google par contre j'ai ajouté les semantics et les tags ....

J'ai utilisé MockK et Junit pour tester le repository et le viewModel (Flow par Turbine)

En ce qui concerne la langue du téléphone , j'ai testé avec la langue anglaise et française , vous pouvez trouver ci dessous les screenshots (Pour les news en fr il manque les image j'ai donc ajouté une image pour Unavailable Image) , Pour récupérer la langue du téléphone j'ai utilisé Locale.getDefault().country , cette ligne retourne deux lettres codes 

## US :
<div style="width:60px ; height:100px">
![news_us](https://user-images.githubusercontent.com/105220639/236853975-bcf74ec3-8c2e-4818-94e2-7f7a10f25eb0.png)!![detail_us|500](https://user-images.githubusercontent.com/105220639/236866173-dd30f197-165e-4589-86eb-6f4199c1a022.png)
<div>


## FR : 
![news_fr](https://user-images.githubusercontent.com/105220639/236854267-70a3bf42-1596-4d6f-b0b1-c242599820e1.png)![detail_fr](https://user-images.githubusercontent.com/105220639/236854359-2ffc5294-5927-4bd9-9477-dbea0d5232fd.png)




## Resumé : 

Pour ce mini projet from scratch , en général pour un court projet je suis plutot sur du MVC-clean architecture.(du code reutilisable, peu complexe)

En tenant compte de ce qui était demandé , les tests surtout . et pour un projet de taille , un vrai projet comme vous l'avez indiqué . j'opte pour une clean architecture + (MVVM sur la partie présentation) , en utilisant des differents patterns use case , repository , singleton et les 5  principes SOLID , ce que j'ai utilisé mais pas a 100 % sur ce mini projet .
Pour en parler en général . de l'unique responsabilité , les classes devront avoir que leurs propres methodes de pouvoir modifier , ajouter des interface , héritage , de l'injection de dépendance comme avec koin ou dagger hilt (ce que j'ai utilisé sur ce projet). déja en choisissant cette architecture on opte a séparer le code en 3 couches ou modules pour faciliter la modification et la maintenance , couplage lache , code flexible facilement testable ... 


Pour le MVVM sur la partie présentation , j'utilise les viewModels pour le databinding et du jetpack compose pour créer les interfaces et du livedata pour observer les données et ensuite modifier les composants . sinon les fragments et activities . 

Pour la partie Data sur ce projet je n'ai pas utilisé une BDD pour stocker les données ces derniers temps j'ai travaillé avec Room et realm .on a une classe pour gérer notre model et stocker  les données .retrofit to  Room DB "fromSource toSource". j'ai directement utilisé retrofit avec moshi (JSON library) remotly et afficher directement notre model. c'est sur ce module qu'on implemente notre repository le responsable pour communiquer avec le datasource en local et en remote  .

Et puis on a le domain c'est la qu'on trouve notre business logic sur lequelle je développe notre use case pattern , les coroutines , dispatchers et flow pour exécuter le code en asynchrone , ce module est independant des deux autres et est chargé d'appels au repository du data adéquat .

Junit et Mokk pour les tests unitaire . CoilImage ou Glide pour la partie Image .


Sinon j'ai du voir par avant Sonarqube pour la qualité de code , garbage collector sur profiler ou Leak Canary pour la fuite de mémoire, jenkins pour l'intégration continue , j'ai utilisé zepelin , figma click up pour le design , jira gestion des bugs , confluence gestion de projet .
Bitbucket avec jira ,confluence , git , jenkins .

En agilité Scrum:
Sprint Review 
- pour regarde/proposer des solutions optimale / modification .
- les bonnes pratiques , ne pas ecrire en dure / ecrire des valeurs significatifs ex : StringRessource(nom_du_screen_RessourceName))

En ce qui concerne l'api key , je l'ai mis et laissé le mien dans la classe "Constants" , j'aurais pu faire une reference dans le fichier gradle de l'app et vous laisser créer votre key le mettre dans local.properties . ce dernier sera sur gitignore file .

J'ai rajouté un gitignore avec tous ce qu'on en met en général .

Au final , pour le versionning , j'avais déja utilisé SVN .
Pour ce mini projet j'ai utilisé git .
En general git avec un desktop ou ligne de commande pour gérer les branches  ,pour les nouvelles features , correction de bugs...