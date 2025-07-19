# 🚀 TP Big Data — Analyse des Incidents avec Spark SQL (Java + Maven)

Ce projet a été réalisé dans le cadre du module universitaire **Big Data**. Il s’appuie sur **Apache Spark SQL** pour analyser des données d’incidents industrielles provenant d’un fichier CSV.

---

## 📁 Description des données

Le fichier `incidents.csv` doit contient les colonnes suivantes :

- `Id` : identifiant de l’incident  
- `titre` : titre de l’incident  
- `description` : description détaillée  
- `service` : service concerné  
- `date` : date de l’incident (format date compatible avec Spark)

  Place le fichier incidents.csv à la racine du projet (même niveau que pom.xml)

---

## 🎯 Objectifs du projet

1. **Afficher le nombre d’incidents par service**
2. **Identifier les deux années avec le plus grand nombre d’incidents**

---

## ⚙️ Technologies utilisées

- **Java JDK 11+**
- **Apache Spark 3.4.1**
- **Maven** pour la gestion des dépendances
- **IntelliJ IDEA** comme IDE

