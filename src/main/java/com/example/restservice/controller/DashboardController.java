package com.example.restservice.controller;

import com.example.restservice.service.AlertingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/monitoring")
public class DashboardController {

    @Autowired
    private AlertingService alertingService;

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard() {
        String html = generateDashboardHtml();
        return ResponseEntity.ok()
                .header("Content-Type", "text/html; charset=utf-8")
                .body(html);
    }

    @GetMapping("/alerts/status")
    public ResponseEntity<Map<String, Object>> getAlertingStatus() {
        return ResponseEntity.ok(alertingService.getAlertingStatus());
    }

    private String generateDashboardHtml() {
        return """
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord - Monitoring Warhammer</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #1e3c72, #2a5298);
            color: #fff;
            min-height: 100vh;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .header {
            text-align: center;
            margin-bottom: 30px;
            padding: 20px;
            background: rgba(0, 0, 0, 0.3);
            border-radius: 10px;
        }
        
        .header h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
            color: #ffd700;
        }
        
        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .card {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
            padding: 20px;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.2);
        }
        
        .card h3 {
            color: #ffd700;
            margin-bottom: 15px;
            font-size: 1.3em;
        }
        
        .metric {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 8px 0;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }
        
        .metric:last-child {
            border-bottom: none;
        }
        
        .status {
            padding: 4px 12px;
            border-radius: 20px;
            font-weight: bold;
            font-size: 0.9em;
        }
        
        .status.up {
            background-color: #28a745;
            color: white;
        }
        
        .status.down {
            background-color: #dc3545;
            color: white;
        }
        
        .status.degraded {
            background-color: #ffc107;
            color: black;
        }
        
        .refresh-btn {
            background: #ffd700;
            color: #1e3c72;
            border: none;
            padding: 12px 24px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            margin: 10px 5px;
            transition: all 0.3s;
        }
        
        .refresh-btn:hover {
            background: #ffed4e;
            transform: translateY(-2px);
        }
        
        .auto-refresh {
            text-align: center;
            margin-top: 20px;
        }
        
        .loading {
            display: none;
            text-align: center;
            color: #ffd700;
        }
        
        .timestamp {
            font-size: 0.8em;
            color: #ccc;
        }
        
        .progress-bar {
            width: 100%;
            height: 20px;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
            overflow: hidden;
            margin-top: 5px;
        }
        
        .progress-fill {
            height: 100%;
            background: linear-gradient(45deg, #28a745, #20c997);
            border-radius: 10px;
            transition: width 0.3s;
        }
        
        .progress-fill.warning {
            background: linear-gradient(45deg, #ffc107, #fd7e14);
        }
        
        .progress-fill.danger {
            background: linear-gradient(45deg, #dc3545, #e74c3c);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🛡️ Monitoring Warhammer - Tableau de Bord</h1>
            <p>Surveillance en temps réel de l'application Old World Realms</p>
        </div>
        
        <div class="auto-refresh">
            <button class="refresh-btn" onclick="refreshData()">🔄 Actualiser</button>
            <button class="refresh-btn" onclick="toggleAutoRefresh()">⏰ Auto-actualisation</button>
            <div class="loading" id="loading">⏳ Chargement...</div>
        </div>
        
        <div class="grid">
            <div class="card">
                <h3>🏥 État de Santé</h3>
                <div id="health-content">Chargement...</div>
            </div>
            
            <div class="card">
                <h3>💾 Métriques Système</h3>
                <div id="metrics-content">Chargement...</div>
            </div>
            
            <div class="card">
                <h3>🗄️ Base de Données</h3>
                <div id="database-content">Chargement...</div>
            </div>
            
            <div class="card">
                <h3>☁️ Service S3</h3>
                <div id="s3-content">Chargement...</div>
            </div>
            
            <div class="card">
                <h3>🚨 Système d'Alertes</h3>
                <div id="alerts-content">Chargement...</div>
            </div>
        </div>
    </div>
    
    <script>
        let autoRefreshInterval;
        let isAutoRefreshing = false;
        
        function formatBytes(bytes) {
            const sizes = ['B', 'KB', 'MB', 'GB'];
            if (bytes === 0) return '0 B';
            const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
            return Math.round(bytes / Math.pow(1024, i) * 100) / 100 + ' ' + sizes[i];
        }
        
        function getStatusClass(status) {
            return status.toLowerCase();
        }
        
        function createProgressBar(percentage, label) {
            let className = '';
            if (percentage > 85) className = 'danger';
            else if (percentage > 70) className = 'warning';
            
            return `
                <div class="metric">
                    <span>${label}</span>
                    <span>${percentage}%</span>
                </div>
                <div class="progress-bar">
                    <div class="progress-fill ${className}" style="width: ${percentage}%"></div>
                </div>
            `;
        }
        
        async function fetchData(endpoint) {
            try {
                const response = await fetch(`/monitoring/${endpoint}`);
                return await response.json();
            } catch (error) {
                console.error(`Erreur lors du chargement de ${endpoint}:`, error);
                return { error: 'Erreur de chargement' };
            }
        }
        
        async function updateHealth() {
            const data = await fetchData('health');
            const content = document.getElementById('health-content');
            
            if (data.error) {
                content.innerHTML = '<div class="metric">❌ Erreur de connexion</div>';
                return;
            }
            
            content.innerHTML = `
                <div class="metric">
                    <span>Statut Global</span>
                    <span class="status ${getStatusClass(data.status)}">${data.status}</span>
                </div>
                <div class="metric">
                    <span>Santé Globale</span>
                    <span>${data.overall_health ? '✅' : '❌'}</span>
                </div>
                <div class="metric timestamp">
                    <span>Dernière vérification</span>
                    <span>${new Date(data.timestamp).toLocaleString('fr-FR')}</span>
                </div>
            `;
        }
        
        async function updateMetrics() {
            const data = await fetchData('metrics');
            const content = document.getElementById('metrics-content');
            
            if (data.error) {
                content.innerHTML = '<div class="metric">❌ Erreur de chargement</div>';
                return;
            }
            
            const memory = data.memory;
            content.innerHTML = `
                <div class="metric">
                    <span>Mémoire Utilisée</span>
                    <span>${memory.used}</span>
                </div>
                <div class="metric">
                    <span>Mémoire Totale</span>
                    <span>${memory.total}</span>
                </div>
                ${createProgressBar(memory.usage_percentage, 'Utilisation Mémoire')}
                <div class="metric">
                    <span>Processeurs</span>
                    <span>${data.processors}</span>
                </div>
                <div class="metric">
                    <span>Temps de Fonctionnement</span>
                    <span>${data.uptime}</span>
                </div>
            `;
        }
        
        async function updateDatabase() {
            const data = await fetchData('database');
            const content = document.getElementById('database-content');
            
            if (data.error) {
                content.innerHTML = '<div class="metric">❌ Erreur de connexion</div>';
                return;
            }
            
            content.innerHTML = `
                <div class="metric">
                    <span>Statut</span>
                    <span class="status ${getStatusClass(data.status)}">${data.status}</span>
                </div>
                <div class="metric">
                    <span>Base de Données</span>
                    <span>${data.database || 'N/A'}</span>
                </div>
                <div class="metric">
                    <span>Version</span>
                    <span>${data.version || 'N/A'}</span>
                </div>
                <div class="metric">
                    <span>Test de Connexion</span>
                    <span>${data.connection_test ? '✅' : '❌'}</span>
                </div>
            `;
        }
        
        async function updateS3() {
            const data = await fetchData('s3');
            const content = document.getElementById('s3-content');
            
            if (data.error) {
                content.innerHTML = '<div class="metric">❌ Erreur de connexion</div>';
                return;
            }
            
            content.innerHTML = `
                <div class="metric">
                    <span>Statut</span>
                    <span class="status ${getStatusClass(data.status)}">${data.status}</span>
                </div>
                <div class="metric">
                    <span>Service</span>
                    <span>${data.service}</span>
                </div>
                <div class="metric timestamp">
                    <span>Dernière vérification</span>
                    <span>${new Date(data.last_check).toLocaleString('fr-FR')}</span>
                </div>
            `;
        }
        
        async function updateAlerts() {
            const data = await fetchData('alerts/status');
            const content = document.getElementById('alerts-content');
            
            if (data.error) {
                content.innerHTML = '<div class="metric">❌ Erreur de chargement</div>';
                return;
            }
            
            content.innerHTML = `
                <div class="metric">
                    <span>Service d'Alertes</span>
                    <span class="status up">${data.service_status}</span>
                </div>
                <div class="metric">
                    <span>Échecs Consécutifs</span>
                    <span>${data.consecutive_failures}/${data.failure_threshold}</span>
                </div>
                <div class="metric">
                    <span>Monitoring</span>
                    <span>${data.monitoring_enabled ? '✅ Activé' : '❌ Désactivé'}</span>
                </div>
            `;
        }
        
        async function refreshData() {
            const loading = document.getElementById('loading');
            loading.style.display = 'block';
            
            await Promise.all([
                updateHealth(),
                updateMetrics(),
                updateDatabase(),
                updateS3(),
                updateAlerts()
            ]);
            
            loading.style.display = 'none';
        }
        
        function toggleAutoRefresh() {
            if (isAutoRefreshing) {
                clearInterval(autoRefreshInterval);
                isAutoRefreshing = false;
                document.querySelector('button[onclick="toggleAutoRefresh()"]').textContent = '⏰ Auto-actualisation';
            } else {
                autoRefreshInterval = setInterval(refreshData, 30000); // 30 secondes
                isAutoRefreshing = true;
                document.querySelector('button[onclick="toggleAutoRefresh()"]').textContent = '⏸️ Arrêter Auto';
            }
        }
        
        // Initialisation
        document.addEventListener('DOMContentLoaded', function() {
            refreshData();
        });
    </script>
</body>
</html>
                """;
    }
}
