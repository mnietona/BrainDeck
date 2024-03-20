# INFO-F-307 : Projet Flashcards (Groupe 6)
Application multiplateforme d’aide à l’étude basée sur un système de flashcards. Implémentée en Java.

## Prérequis
Pour exécuter le projet, vous devez installer les outils suivants :

- Java Development Kit (JDK) dernière version
- IntelliJ IDEA Community ou Ultimate

## Téléchargement et installation
1. Téléchargez et installez le JDK depuis le site Web d'Oracle (https://www.oracle.com/java/technologies/downloads/).
2. Téléchargez et installez la dernière version d'IntelliJ IDEA Community ou Ultimate depuis le site Web de JetBrains (https://www.jetbrains.com/idea/download/).

## Lancement du programme depuis IntelliJ
1. Ouvrez le projet dans IntelliJ. Si une fenêtre contextuelle ```"Gradle build scripts found"``` apparaît, cliquez sur le bouton ```Load Gradle Project``` pour charger les scripts de construction Gradle.
2. Pour le premier lancement :
    - Faites un clic droit sur le fichier ```src/ulb/info307/g6/Launcher``` et sélectionnez ```Run 'Launcher.main()'``` pour exécuter le programme.
3. Pour les lancements ultérieurs :
    - Répétez l'étape 2 ou sélectionnez "Launcher" dans la liste des configurations située en haut à droite de la fenêtre, puis cliquez sur le bouton ```Run 'Launcher'``` (flèche verte).

## (Re)Compiler et lancer le .jar depuis IntelliJ
1. Suivez l'Étape 1 de la section précédente pour charger le projet.
2. Accédez au menu ```Gradle``` situé à droite, faites un clic droit sur l'élément ```g6/Tasks/build/jar``` et sélectionnez ```Run '2023-groupe-6 [jar]'``` pour (re)compiler le projet en .jar.
3. Pour lancer le .jar depuis IntelliJ :
    - Répétez l'Étape 2 de la première section, mais en sélectionnant le bouton ```Run 'g6-iteration-X.jar'``` pour le fichier ```dist/g6-iteration-2.jar```.
    - Pour les lancements ultérieurs, répétez l'Étape 3 de la première section, mais sélectionnez ```g6-iteration-X.jar``` dans la liste des configurations.

Cela transforme les instructions pour refléter un processus similaire dans IntelliJ, mais en ajustant les détails spécifiques à vos besoins, comme les chemins de fichiers et les actions spécifiques à Gradle.


