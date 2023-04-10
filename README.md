# INFO-F-307 : Projet Flashcards (Groupe 6)
Application multiplateforme d’aide à l’étude basée sur un système de flashcards, implémentée en Java.

## Téléchargements requis
* Dernière version de java : https://www.oracle.com/java/technologies/downloads/
  * Version 19.0.2 : https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html
* IntelliJ IDEA : https://www.jetbrains.com/idea/download/
  * Version Ultimate gratuite pour les étudiants : https://www.jetbrains.com/community/education/#students

## Lancer l'application

### Depuis IntelliJ
0. Télécharger le repository et l'ouvrir dans IntelliJ
1. Bouton \<Load Gradle Project> sur le pop-up "Gradle build scripts found"
2. Click droit puis <Run 'Launcher.main()'> sur le fichier "src/ulb.info307g6/Launcher"
3. Pour les lancements suivants; <Maj+F10> (ou bouton <Run 'Launcher'> en haut à droite)

### Avec le .jar
0. Télécharger le repository
1.  ...


---

---


## Schémas
### Structure des menus de l'application
![](/resources/img/menuStructure.png)

## Accès au repo (ssh)

### Windows
```
Ouvrir un terminal
-> "ssh-keygen -t ed25519"
Enter file... -> Appuyer sur ENTER pour sauvegarder les clés dans le dossier par défaut
Enter passphrase... -> Entrer un mot de passe
Enter same passphrase... -> Confirmer le mdp

Ouvrir le fichier C:\Users\<username>\.ssh\id_ed25519.pub
Copier la clé se trouvant dans le fichier et la mettre sur https://gitlab.ulb.be/-/profile/keys

Retourner sur le terminal se deplacer à l'endroit où cloner le repository
-> git clone ssh://git@gitlab.ulb.be:30422/ulb-infof307/2023-groupe-6.git

The authenticity of host... -> "yes"
Enter passhprase... -> Entrer le mot de passe introduit plus tôt
```

### Linux
```
```

### macOS
```
```

## Liens utils
- Organisation et suivi des tâches : https://universitelibrebruxelles-my.sharepoint.com/:x:/g/personal/adrien_beslier_ulb_be/EbxqI2QjQB1Mnmaf7tUvUlsBBPic7kc17buuY6wX7cvh0w?e=n2aWS9