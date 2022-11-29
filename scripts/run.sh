#!/bin/bash
minikube delete
minikube start 
kubectl apply -f ../kuber/database/db.yml -f ../kuber/database/secrets/secret.yml -f ../kuber/database/services/cluster-ip.yml
kubectl apply -f ../kuber/backend/back.yml -f ../kuber/backend/configmaps/back-cm.yml -f ../kuber/backend/services/cluster-ip.yml
kubectl apply -f ../kuber/external/load-balancer.yml
minikube tunnel