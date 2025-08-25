#!/bin/bash

# Script de déploiement du backend Old World Realms
echo "🚀 Déploiement du backend Old World Realms..."

# Appliquer les configurations Kubernetes
echo "📦 Application des configurations..."
kubectl apply -f k8s-backend/secret.yaml
kubectl apply -f k8s-backend/configmap.yaml
kubectl apply -f k8s-backend/deployment.yaml
kubectl apply -f k8s-backend/service.yaml
kubectl apply -f k8s-backend/certificate.yaml
kubectl apply -f k8s-backend/ingress.yaml

# Vérifier le déploiement
echo "🔍 Vérification du déploiement..."
kubectl rollout status deployment/old-world-realms-backend

echo "✅ Déploiement terminé !"
echo "🌐 API disponible sur : https://api.oldworldrealms.app"

# Afficher les pods
kubectl get pods -l app=old-world-realms-backend
