# 🛡️ Veille Sécurité - Projet Warhammer

## Vue d'ensemble

Ce projet intègre une veille sécurité automatisée pour détecter les vulnérabilités et surveiller les activités suspectes.

## 🔧 Fonctionnalités de sécurité

### 1. Analyse automatique des vulnérabilités
- **OWASP Dependency Check** : Scan des dépendances
- **SpotBugs + FindSecBugs** : Analyse du code source
- **Rapports HTML** générés automatiquement

### 2. Surveillance en temps réel
- Détection d'injections SQL
- Protection contre XSS
- Blocage automatique des IPs suspectes
- Validation des uploads de fichiers

### 3. Endpoints de monitoring
- `/security/status` - Statistiques de sécurité
- `/security/health` - État du service de sécurité
- `/actuator/health` - Santé de l'application

## 🚀 Utilisation

### Lancement des contrôles de sécurité
```bash
# Analyse complète
./gradlew securityCheck

# OWASP uniquement
./gradlew dependencyCheckAnalyze

# SpotBugs uniquement
./gradlew spotbugsMain
```

### Script de surveillance
```bash
# Sur Unix/Linux/Mac
./security-monitoring-auto.sh

# Sur Windows (à exécuter dans Git Bash)
bash security-monitoring-auto.sh
```

### Vérification du statut de sécurité
```bash
curl http://localhost:8080/security/status
```

## 📊 Rapports générés

Les rapports sont automatiquement créés dans :
- `build/reports/dependency-check-report.html` - Vulnérabilités des dépendances
- `build/reports/spotbugs/main.html` - Failles de sécurité du code
- `security-report.txt` - Rapport de synthèse

## 🔒 Sécurité des uploads

Le système valide automatiquement :
- ✅ Taille des fichiers (max 10MB)
- ✅ Type de contenu (images uniquement)
- ✅ Noms de fichiers malveillants
- ✅ Tentatives d'injection dans les paramètres

## 📈 Surveillance continue

### GitHub Actions
Le workflow `security.yml` lance automatiquement :
- Scan quotidien à 6h du matin
- Vérification à chaque push/pull request
- Upload des rapports en artifacts

### Logs de sécurité
Consultez les événements de sécurité :
```bash
tail -f logs/application.log | grep "SECURITY"
```

## 🚨 Alertes et notifications

Le système log automatiquement :
- `SQL_INJECTION_ATTEMPT` - Tentatives d'injection SQL
- `XSS_ATTEMPT` - Tentatives d'attaques XSS  
- `IP_BLOCKED` - Blocage d'IPs suspectes
- `FILE_UPLOAD_SUCCESS/ERROR` - Événements d'upload

## 🔧 Configuration

### Seuils de sécurité (modifiables dans SecurityMonitoringService)
- **Tentatives suspectes** : 10 avant blocage IP
- **Durée de blocage** : 30 minutes
- **Taille max input** : 5000 caractères

### Niveaux d'alerte OWASP
- **CVSS Score** : 7+ considéré comme critique
- **Échec de build** : Désactivé pour éviter les blocages

## 📋 Checklist de sécurité

### Avant déploiement
- [ ] `./gradlew securityCheck` sans erreurs critiques
- [ ] Test des endpoints de sécurité
- [ ] Vérification des logs d'erreur
- [ ] Configuration SSL pour la production

### Maintenance régulière
- [ ] Scan hebdomadaire des dépendances
- [ ] Revue des logs de sécurité
- [ ] Mise à jour des exclusions si nécessaire

## 🆘 En cas d'incident

1. **Consulter les logs** : `logs/application.log`
2. **Vérifier le statut** : `GET /security/status`
3. **Analyser les rapports** dans `build/reports/`
4. **Débloquer une IP** : Redémarrer l'application

## 🔗 Endpoints utiles

- `GET /security/status` - Statistiques de sécurité
- `GET /security/health` - Santé du service
- `POST /security/validate` - Validation d'inputs
- `GET /actuator/health` - Santé générale de l'app

---

**Note** : Cette configuration privilégie la simplicité et l'efficacité. Pour un environnement de production critique, considérez des outils de sécurité plus avancés.
