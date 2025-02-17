#!/bin/bash

# Build the application
mvn clean package -DskipTests

# Build the Docker image
eval $(minikube docker-env)
docker build -t customer-service:latest .

# Apply Kubernetes configurations
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# Wait for deployment
kubectl rollout status deployment/customer-service

# Get service URL
minikube service customer-service --url


