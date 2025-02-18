#!/bin/bash

# Build the application
mvn clean package -DskipTests

# Build the Docker image
eval $(minikube docker-env)
docker build -t product-service:latest .

# Apply Kubernetes configurations
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# Wait for deployment
kubectl rollout status deployment/product-service

# Get service URL
minikube service product-service --url 