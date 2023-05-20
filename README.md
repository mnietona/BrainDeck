# INFO-F-307 : Projet Flashcards (Groupe 6)
Application multiplateforme d’aide à l’étude basée sur un système de flashcards. Implémentée en Java.

## Téléchargements
* [Dernière version de java](https://www.oracle.com/java/technologies/downloads/)
    * [Version 19.0.2 utilisée pour le développement](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
    * [Version Ultimate gratuite pour les étudiants](https://www.jetbrains.com/community/education/#students)

## Lancer l'application
1. Cloner ou télécharger le [repository](https://gitlab.ulb.be/ulb-infof307/2023-groupe-6). 
2. Ouvrir "dist/g6-iteration-X.jar".

#### Lancer l'application depuis IntelliJ
1. Ouvrir le projet dans IntelliJ. Clicker sur le bouton \<Load Gradle Project> du pop-up "Gradle build scripts found", si ce n'est pas déjà fait.
2. Pour le premier lancement;
    - Click droit puis <Run 'Launcher.main()'> sur le fichier "src/ulb/info307/g6/Launcher".
3. Pour les lancements suivants;
    - Même chose que l'étape 2, ou sélectionner "Launcher" dans la liste des configurations, puis clicker sur le bouton <Run 'Launcher'> (flèche verte en haut à droite).

#### (re)Compiler et lancer le .jar depuis IntelliJ
1. [Étape 1 section précédente](#lancer-lapplication-depuis-intellij).
2. Dans le menu \<Gradle> à droite, click droit puis <Run '2023-groupe-6 [jar]'> sur l'élément "g6/Tasks/build/jar".
3. Lancer le .jar depuis IntelliJ;
    - [Étape 2 section précédente](#lancer-lapplication-depuis-intellij), mais avec le bouton <Run 'g6-iteration-X.jar'> et le fichier "dist/g6-iteration-2.jar".
    - [Étape 3 section précédente](#lancer-lapplication-depuis-intellij), mais avec "g6-iteration-X.jar" dans la liste des configurations.
