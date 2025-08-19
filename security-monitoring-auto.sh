#!/bin/bash

# Script de surveillance sécurité pour le projet Warhammer
echo "=== SURVEILLANCE SÉCURITÉ ==="
echo "Démarrage: $(date)"

# Variables
LOG_FILE="logs/security-monitoring.log"

# Fonction de logging
log() {
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1" | tee -a "$LOG_FILE"
}

# 1. Analyse des dépendances avec OWASP
log "Démarrage de l'analyse OWASP..."
./gradlew dependencyCheckAnalyze --continue

# 2. Analyse SpotBugs pour la sécurité du code
log "Démarrage de l'analyse SpotBugs..."
./gradlew spotbugsMain --continue

# 3. Test de l'application
log "Test de l'endpoint de sécurité..."
if curl -s http://localhost:8080/security/health > /dev/null; then
    log "✓ Service de sécurité opérationnel"
else
    log "⚠ Service de sécurité non accessible"
fi

# 4. Vérification des logs d'application pour des tentatives d'attaque
log "Analyse des logs d'application..."
if [ -f "logs/application.log" ]; then
    sql_attempts=$(grep -c "SQL_INJECTION_ATTEMPT" logs/application.log 2>/dev/null || echo "0")
    xss_attempts=$(grep -c "XSS_ATTEMPT" logs/application.log 2>/dev/null || echo "0")
    blocked_ips=$(grep -c "IP_BLOCKED" logs/application.log 2>/dev/null || echo "0")

    log "INFO: $sql_attempts tentatives SQL injection détectées"
    log "INFO: $xss_attempts tentatives XSS détectées"
    log "INFO: $blocked_ips IPs bloquées"
else
    log "WARN: Fichier de log application non trouvé"
fi

# 5. Génération d'un rapport
log "Génération du rapport de sécurité..."
cat > "security-report.txt" << EOF
Rapport de Sécurité - $(date)
=============================

Analyses effectuées:
- ✓ OWASP Dependency Check
- ✓ SpotBugs Security Analysis
- ✓ Vérification logs application

Statistiques:
- Tentatives SQL injection: $sql_attempts
- Tentatives XSS: $xss_attempts
- IPs bloquées: $blocked_ips

Rapports détaillés disponibles dans:
- build/reports/dependency-check-report.html
- build/reports/spotbugs/main.html

Pour plus d'informations: http://localhost:8080/security/status
EOF

log "Rapport généré: security-report.txt"
log "Surveillance terminée: $(date)"

echo "=== FIN DE LA SURVEILLANCE ==="
