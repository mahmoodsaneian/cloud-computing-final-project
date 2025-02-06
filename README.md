# Order Tracking Application  

## Overview  
This project is a **cloud-native order tracking application** built using **Java Spring Boot**, **Docker**, and **Kubernetes**. The application provides RESTful APIs for registering orders, approving or rejecting them, and checking order status. It is backed by a **PostgreSQL master-slave architecture**, ensuring that write operations go to the master database while read operations are handled by the slave.  

## Features  
- **Order Management**: Register, approve/reject, and track orders.  
- **Database Optimization**: Uses **master-slave PostgreSQL replication** for optimized performance.  
- **Containerization**: Dockerized for easy deployment.  
- **Orchestration**: Managed using **Kubernetes and Helm charts** for scalability.  

## API Endpoints  
| Method | Endpoint | Description |  
|--------|------------------------|-----------------------------|  
| `POST` | `/register-orders` | Register a new order |  
| `PUT` | `/approve-reject-order` | Approve or reject an order |  
| `GET` | `/check-order-status/{orderId}` | Check the status of an order |  

## Technologies Used  
- **Java 17**, **Spring Boot**  
- **PostgreSQL (Master-Slave Replication)**  
- **Docker, Kubernetes**  
- **Helm**  

## Deployment  

### Prerequisites  
- Docker & Docker Compose  
- Kubernetes cluster (Minikube, K3s, or cloud provider)  
- Helm installed  

### Running with Docker Compose  
1. Clone the repository:  
   ```sh
   git clone https://github.com/mahmoodsaneian/cloud-computing-final-project.git
   cd cloud-computing-final-project
   ```  
2. Start the services using Docker Compose:  
   ```sh
   docker-compose up -d
   ```  

### Deploying on Kubernetes  
1. Build and push the Docker image:  
   ```sh
   docker build -t your-dockerhub-username/order-tracking:latest .  
   docker push your-dockerhub-username/order-tracking:latest  
   ```  
2. Deploy using Helm:  
   ```sh
   helm install order-tracking helm/  
   ```  

## Project Structure  
```
cloud-computing-final-project/
│── src/
│   ├── main/java/com/ordertracking/ (Application logic)
│── helm/ (Helm chart for Kubernetes deployment)
│── docker-compose.yml
│── README.md
│── pom.xml
```  
