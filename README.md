# INFO-F-307 : Projet Flashcards (Groupe 6)
Application multiplateforme d’aide à l’étude basée sur un système de flashcards. Implémentée en Java.

## Prérequis
Pour exécuter le projet, vous devez installer les outils suivants :

- Java Development Kit (JDK) dernière version
- IntelliJ IDEA Community ou Ultimate

## Téléchargement et installation

1. Téléchargez et installez le JDK depuis le site Web d'Oracle (https://www.oracle.com/java/technologies/downloads/).
2. Téléchargez et installez la dernière version d'IntelliJ IDEA Community ou Ultimate depuis le site Web de JetBrains (https://www.jetbrains.com/idea/download/).
3. Téléchargez et installez Gradle depuis le site Web de Gradle (https://gradle.org/install/).
   - Pour macOS, vous pouvez utiliser Homebrew pour installer Gradle en exécutant la commande suivante dans le terminal :
     ```bash
     brew install gradle
     ```
   - Vérifiez l'installation en exécutant :
     ```bash
     gradle -v
     ```
## Lancement du programme depuis un Terminal

Pour lancer le programme, suivez ces étapes dans votre terminal :

1. **Exécutez les commandes suivantes** :
   ```bash
   gradle wrapper
   ./gradlew build
   ./gradlew run
   ./gradlew clean

### Explications des étapes :
- **`gradle wrapper`** : Génère les fichiers du wrapper Gradle nécessaires.
- **`./gradlew build`** : Compile le projet et crée les fichiers nécessaires.
- **`./gradlew run`** : Exécute le programme.
- **`./gradlew clean`** : Nettoie les fichiers générés.

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

## Démonstration

Vous pouvez regarder la vidéo de démonstration en suivant ce lien : [Vidéo de démonstration](https://drive.google.com/file/d/1AGy0uTqzqWNkNYC6KYpwlQo-1JpCte2r/view?usp=share_link)
