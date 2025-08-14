# Warhammer Battle Reports API

Une API REST Spring Boot pour la gestion de rapports de bataille Warhammer avec stockage d'images sur S3.

## 🎯 Fonctionnalités

- **Gestion des rapports de bataille** : Création, lecture, mise à jour et suppression
- **Gestion des joueurs** : Informations sur les participants aux batailles
- **Upload d'images** : Stockage sécurisé sur AWS S3 compatible (Scaleway)
- **Gestion des armées** : Types d'unités, compositions, alliances
- **Scénarios de bataille** : Différents types de parties
- **Points et règles** : Système de points et règles de montage

## 🛠️ Technologies utilisées

- **Java 17**
- **Spring Boot 3.3.0**
- **MySQL** - Base de données
- **AWS S3 SDK** - Stockage d'images
- **Apache Commons DbUtils** - Utilitaires base de données
- **HikariCP** - Pool de connexions
- **Gradle** - Gestion des dépendances
- **Docker** - Conteneurisation

## 📁 Structure du projet

```
src/
├── main/java/com/example/restservice/
│   ├── RestServiceApplication.java          # Point d'entrée de l'application
│   ├── DatabaseSingleton.java               # Singleton pour la base de données
│   ├── config/                              # Configuration CORS
│   ├── controller/                          # Contrôleurs REST
│   │   ├── BattleReportController.java      # API des rapports de bataille
│   │   ├── PlayerController.java            # API des joueurs
│   │   └── ...                              # Autres contrôleurs
│   ├── model/                               # Modèles de données
│   │   ├── BattleReport.java               # Modèle rapport de bataille
│   │   ├── Player.java                     # Modèle joueur
│   │   └── ...                             # Autres modèles
│   ├── repository/                         # Couche d'accès aux données
│   │   ├── BattleReportRepository.java     # Repository des rapports
│   │   ├── PlayerRepository.java           # Repository des joueurs
│   │   └── ...                             # Autres repositories
│   └── service/
│       └── S3Service.java                  # Service de gestion S3
└── resources/
    └── application.properties              # Configuration de l'application
```

## 🚀 Installation et démarrage

### Prérequis

- Java 17+
- MySQL 8.0+
- Compte AWS S3 ou service compatible (Scaleway)
- Docker (optionnel)

### Configuration

1. **Cloner le projet**
```bash
git clone <repository-url>
cd gs-rest-service/initial
```

2. **Configuration de la base de données**
Créez un fichier `.env` à la racine du projet :
```env
DB_URL=jdbc:mysql://localhost:3306/warhammer_db
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Configuration S3 (Scaleway)
S3_ACCESS_KEY=your_access_key
S3_SECRET_KEY=your_secret_key
S3_BUCKET_NAME=your_bucket_name
S3_REGION=fr-par
```

3. **Créer la base de données MySQL**
```sql
CREATE DATABASE warhammer_db;
```

### Démarrage avec Gradle

```bash
# Installer les dépendances et construire
./gradlew build

# Démarrer l'application
./gradlew bootRun
```

### Démarrage avec Docker

```bash
# Construire l'image
docker build -t warhammer-api .

# Lancer le conteneur
docker run -p 8080:8080 --env-file .env warhammer-api
```

## 📚 API Endpoints

### Rapports de bataille

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/battlereport` | Récupérer tous les rapports |
| GET | `/battlereport/{id}` | Récupérer un rapport par ID |
| GET | `/battlereport/user/{id}` | Récupérer les rapports d'un utilisateur |
| POST | `/battlereport` | Créer un nouveau rapport |
| PUT | `/battlereport/{id}` | Mettre à jour un rapport |
| DELETE | `/battlereport/{id}` | Supprimer un rapport |
| POST | `/battlereport/{id}/photos` | Upload d'images |
| DELETE | `/battlereport/{id}/photos` | Supprimer des images |

### Joueurs

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/player` | Récupérer tous les joueurs |
| GET | `/player/{id}` | Récupérer un joueur par ID |
| POST | `/player` | Créer un nouveau joueur |
| PUT | `/player/{id}` | Mettre à jour un joueur |
| DELETE | `/player/{id}` | Supprimer un joueur |

### Autres endpoints

- `/unit*` - Gestion des unités
- `/army*` - Gestion des armées  
- `/alliance*` - Gestion des alliances
- `/scenario*` - Gestion des scénarios
- `/mount*` - Gestion des montures
- `/points*` - Gestion des points
- `/status` - Statut de l'API

## 📝 Exemples d'utilisation

### Créer un rapport de bataille

```bash
curl -X POST http://localhost:8080/battlereport \
  -H "Content-Type: application/json" \
  -d '{
    "nameBattleReport": "Bataille d'\''Helm",
    "descriptionBattleReport": "Bataille épique au Gouffre de Helm",
    "scenario_idScenario": 1,
    "armyPoints": 1500,
    "idUser": "user123",
    "players": [
      {
        "playerName": "Aragorn",
        "playerScore": "Victoire",
        "alliance_idAlliance": 1,
        "armyName_idArmyName": 1,
        "armyComposition_idArmyComposition": 1
      }
    ]
  }'
```

### Upload d'images

```bash
curl -X POST http://localhost:8080/battlereport/1/photos \
  -F "fileBattleReportPhoto=@image1.jpg" \
  -F "fileBattleReportPhoto=@image2.jpg"
```

## 🔧 Configuration CORS

L'API est configurée pour accepter les requêtes depuis :
- `http://localhost:5173`
- `http://127.0.0.1:5173`

Pour modifier ces origines, éditez les fichiers dans `src/main/java/com/example/restservice/config/`.

## 🗃️ Modèle de données

### Entités principales

- **BattleReport** : Rapport de bataille principal
- **Player** : Joueurs participant aux batailles
- **BattleReportPhoto** : Images associées aux rapports
- **Unit** : Unités d'armée
- **Army** : Compositions d'armées
- **Alliance** : Alliances entre factions
- **Scenario** : Types de scénarios de bataille
- **Mount** : Montures disponibles

## 🚀 Déploiement

### Variables d'environnement requises

```env
DB_URL=jdbc:mysql://your-db-host:3306/warhammer_db
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password
S3_ACCESS_KEY=your_s3_access_key
S3_SECRET_KEY=your_s3_secret_key
S3_BUCKET_NAME=your_bucket_name
S3_REGION=your_s3_region
```

### Docker en production

```bash
# Construire pour la production
./gradlew bootJar

# Construire l'image Docker
docker build -t warhammer-api:latest .

# Déployer
docker run -d \
  --name warhammer-api \
  -p 8080:8080 \
  --env-file .env \
  warhammer-api:latest
```

## 🧪 Tests

```bash
# Exécuter tous les tests
./gradlew test

# Exécuter les tests avec rapport
./gradlew test jacocoTestReport
```

## 📄 Licence

Ce projet est sous licence [MIT](LICENSE).

## 🤝 Contribution

1. Forkez le projet
2. Créez votre branche de fonctionnalité (`git checkout -b feature/amazing-feature`)
3. Committez vos changements (`git commit -m 'Add amazing feature'`)
4. Poussez vers la branche (`git push origin feature/amazing-feature`)
5. Ouvrez une Pull Request

## 📞 Support

Pour toute question ou problème, veuillez ouvrir une issue sur le repository GitHub.
