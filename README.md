# TP1 - Les Sockets en Mode Connecté TCP (Activités 1-2 et 1-3)

## 📌 Description
Ce projet est la réalisation du TP1 de la matière "Développement d'applications réparties". Il s'agit d'une application Client/Serveur basée sur des sockets TCP en mode connecté. 
Le projet a évolué en deux étapes :
1. **Activité 1-2 :** Création d'un service de calculatrice simple (multiplication d'un entier `x` par 5).
2. **Activité 1-3 :** Amélioration de l'application pour permettre une connexion entre deux machines distinctes d'un même réseau local, et enrichissement du service avec 4 opérations (Addition, Soustraction, Multiplication, Division).

## 🛠️ Environnement
*   JDK 1.8 ou supérieur
*   IDE (IntelliJ IDEA, Eclipse, VS Code)
*   Git

## 📂 Structure du projet
*   `src/clientPackage/Client.java` : Code du client.
*   `src/serverPackage/Server.java` : Code du serveur.

---

## 🧠 Comprendre les Sockets et le Modèle TCP (Théorie)

Avant de plonger dans le code, il est essentiel de comprendre ce qu'est une socket et comment deux programmes communiquent sur un réseau.

### 1. C'est quoi une Socket ?
Une **socket** (ou "prise" en français) est un point de terminaison de communication bidirectionnelle entre deux programmes s'exécutant sur le réseau. 
Imaginez une prise téléphonique : pour que deux personnes se parlent, il faut que les deux aient une prise et qu'elles soient reliées par un câble. En programmation réseau, une socket est identifiée par une combinaison unique :
*   **Une adresse IP** (l'adresse de la machine, ex: `192.168.0.48`)
*   **Un numéro de port** (le numéro de la "porte" logique sur cette machine, ex: `1234`)

### 2. Le modèle Client-Serveur TCP
L'activité utilise le protocole **TCP (Transmission Control Protocol)**. TCP est un protocole **orienté connexion** et **fiable**. Cela signifie :
*   **Orientation connexion** : Avant d'envoyer des données, le client et le serveur doivent établir une connexion (comme un appel téléphonique : on décroche, on dit "allô", puis on parle).
*   **Fiabilité** : TCP garantit que les données arrivent dans le bon ordre et sans perte. Si un paquet se perd, TCP le renvoie automatiquement.

Dans ce modèle :
*   Le **Serveur** est passif. Il ouvre un port et attend patiemment qu'un client se connecte.
*   Le **Client** est actif. Il initie la connexion vers l'adresse IP et le port du serveur.

### 3. Les classes Java utilisées

*   **`ServerSocket` (Côté Serveur)** : 
    C'est la classe qui permet au serveur d'écouter sur un port spécifique. La méthode `accept()` est bloquante : le programme s'arrête à cette ligne et attend qu'un client se connecte. Dès qu'un client arrive, `accept()` retourne une nouvelle `Socket` dédiée à la communication avec ce client spécifique.
*   **`Socket` (Côté Client et Serveur)** : 
    C'est la représentation de la connexion établie. Côté client, on crée un `new Socket(IP, port)` pour se connecter. Côté serveur, on récupère la `Socket` retournée par `accept()`. C'est via cet objet qu'on récupère les flux d'entrée et de sortie.
*   **`InetAddress` et `InetSocketAddress`** : 
    `InetAddress` représente une adresse IP. `InetSocketAddress` combine une adresse IP et un port. Ils sont utilisés pour lier le serveur à une interface réseau spécifique et pour que le client cible précisément la machine serveur.
*   **`InputStream` / `OutputStream`** : 
    Les sockets communiquent via des flux d'octets (bytes). `InputStream` lit les données venant du réseau, et `OutputStream` écrit des données vers le réseau.
*   **`DataInputStream` / `DataOutputStream`** : 
    Comme les flux de base ne manipulent que des octets, ces classes "enveloppent" les flux pour permettre d'envoyer et de recevoir des types Java spécifiques comme des `int` (`writeInt`/`readInt`) ou des `double` (`writeDouble`/`readDouble`). Cela permet notamment de gérer correctement les entiers négatifs.

---

## 🖼️ Aperçu du code et des tests

### 1. Activité 1-2 : Service de calculatrice de base
Dans cette première étape, le client envoie un entier `x` au serveur. Le serveur calcule `x * 5` et renvoie le résultat. L'utilisation de `DataInputStream` et `DataOutputStream` est cruciale pour gérer les entiers négatifs.

<img width="348" height="193" alt="Screenshot 2026-10-11 172124" src="https://github.com/user-attachments/assets/3710cd91-86bd-48b0-8c8d-7e08cf2e1a44" />

*Ici, on voit l'exécution du client qui envoie un nombre et reçoit le résultat multiplié par 5.*

### 2. Activité 1-3 : Connexion sur deux machines distinctes
Pour permettre la connexion entre deux machines, nous avons remplacé `localhost` par l'adresse IP réelle de la machine serveur (`192.168.0.48` dans notre cas). Le serveur utilise `InetAddress` et `InetSocketAddress` pour se lier à cette adresse IP spécifique.

<img width="1920" height="1080" alt="Screenshot 2026-10-15 180707" src="https://github.com/user-attachments/assets/e85ae0c5-9ff1-4bed-a63b-dd39dddd6f57" />

*Ici, on voit la configuration du ServerSocket avec l'adresse IP réelle et la logique de calcul.*

<img width="1920" height="1080" alt="Screenshot 2026-10-15 180746" src="https://github.com/user-attachments/assets/65c99338-fa64-41e3-9dae-429d24b5e585" />


*Ici, on voit la création de la socket client et la connexion au serveur via l'adresse IP.*

### 3. Activité 1-3 : Service de calculatrice amélioré
Le client affiche maintenant un menu de choix (Addition, Soustraction, Multiplication, Division). Il envoie le choix de l'opération (un `int`) suivi de la valeur `x` (un `double`). Le serveur lit ces deux valeurs et utilise un `switch` pour effectuer le calcul approprié.

<img width="1920" height="1080" alt="Screenshot 2026-10-15 180733" src="https://github.com/user-attachments/assets/3ecb6b34-36a8-41e3-89a5-e4cf49de8ab7" />

*Ici, on voit la partie du code qui gère le menu et la lecture des entrées utilisateur avec la classe Scanner.*

<img width="1920" height="1080" alt="Screenshot 2026-10-15 180803" src="https://github.com/user-attachments/assets/191a4e71-ee58-4b39-8973-eab9144e6e09" />

*Résultat de l'exécution : l'utilisateur a choisi l'option 4 (Division), a entré la valeur 5, et le serveur a renvoyé 1.0.*

---

## 🔄 Le protocole de communication
Pour que le client et le serveur se comprennent, ils suivent un protocole strict (un ordre précis d'envoi et de réception) :
1. Le client envoie d'abord le **choix** de l'opération (un `int`).
2. Le client envoie ensuite la **valeur x** (un `double`).
3. Le serveur lit ces deux valeurs dans le même ordre (d'abord `readInt()`, puis `readDouble()`).
4. Le serveur renvoie le **résultat** (un `double`).

### Logique du calcul (Switch)
Le serveur utilise une expression `switch` moderne de Java pour déterminer l'opération à effectuer :
*   `case 1` : Addition (`x + 5`)
*   `case 2` : Soustraction (`x - 5`)
*   `case 3` : Multiplication (`x * 5`)
*   `case 4` : Division (`x / 5`)

*(Note : Le deuxième opérande est fixé à 5 selon l'énoncé de l'activité).*

## 🚀 Comment exécuter le projet

1.  **Configurer l'adresse IP** :
    *   Ouvrez `Server.java` et `Client.java`.
    *   Remplacez `"192.168.0.48"` par l'adresse IP réelle de la machine qui exécutera le serveur (obtenue via `ipconfig` sur Windows ou `ifconfig` sur Linux/Mac).
2.  **Lancer le Serveur** :
    *   Exécutez la classe `Server.java` en premier. Le serveur va se lier au port et attendre.
3.  **Lancer le Client** :
    *   Exécutez la classe `Client.java`.
    *   Suivez les instructions affichées dans la console (choix de l'opération, saisie de x).
4.  **Tester** :
    *   Vérifiez que le résultat renvoyé par le serveur est correct pour chaque opération (testez avec des valeurs positives, négatives et zéro).

## ⚠️ Dépannage
*   **Connection refused** : Vérifiez que le serveur est bien lancé et que le pare-feu autorise le port `1234`.
*   **UnknownHostException** : Vérifiez que l'adresse IP entrée est correcte et que les deux machines sont sur le même réseau.
*   **Résultat incorrect** : Vérifiez que le client et le serveur lisent/écrivent les données dans le même ordre et avec les mêmes types (`int` puis `double`).<img width="350" height="91" alt="Screenshot 2026-10-11 172116" src="https://github.com/user-attachments/assets/75b522c4-2ad7-497b-b487-3fb32505a1fb" />
