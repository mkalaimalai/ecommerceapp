#!/bin/bash

# # Start Minikube if not running
# minikube start

# # Set docker env to use Minikube's docker daemon
# eval $(minikube docker-env)

# # Build both services
cd customer-service && mvn clean package -DskipTests && docker build -t customer-service:latest . && cd ..
cd product-service && mvn clean package -DskipTests && docker build -t product-service:latest . && cd ..

# Apply the combined deployment
kubectl apply -f k8s/deployment.yaml

# Wait for deployments
kubectl rollout status deployment/customer-service
kubectl rollout status deployment/product-service

# Get service URLs
echo "Customer Service URL:"
minikube service customer-service --url
echo "Product Service URL:"
minikube service product-service --url

# Optional: View pods status
kubectl get pods 