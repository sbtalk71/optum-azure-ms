## Minikube Kubernetes Commands

Imp: when using Minikube, Service type LoadBalancer will be unavailable.

## start cluster
minikube start 

## Get cluster Info
kubectl get info

# Get information about the nodes
kubectl get nodes

# create a POD using nginx
kubectl run nginx-app --image=nginx:latest

# Get Information about the pods in the cluster
kubctl get pods

# expose the service as NodePort
kubectl expose pod nginx-app --type=NodePort --port=8080 --target-port=80

--port is one which you access and the --target-port is that port which service will use to connect to the container

# Get information about the services in the cluster
kubctl get svc

## Access the service with local browser

minikube service nginx-app

## the above command will create a Tunnel and Open the default broser to access the service

##Scaling Deployments
kubectl scale deployment <deployment-name> --replicas=3

##Delete all Events log

kubectl get Events
kubectl delete events --all


##Deployments and services
=========================

1. Create a Deployment (Spring boot app running on port 8080)

kubectl create deployment k8s-demo --image=sbtalk71/k8s-demo:1 --port=8080

2. Get information about the deployment and the pods
kubectl get deployments
kubectl get pods

3. Expose the deployment as Service (NodePort)

kubectl expose deployment k8s-demo --type=NodePort

4. Access the Service in the Local System (Same Node as its NodePort Service)
kubectl get svc

minikube service k8s-demo --url

Paste the generated url to the browser to access the service

## Access Service with LoadBalancer and Tunnelling

1. kubectl create deployment nginx-app --image=nginx
2. kubectl expose deployment nginx-app --type=LoadBalancer --port=8090 --target-port=80
3. Minikube tunnel (this window will keep running)
4. kubectl get service nginx-app

use the url 'http://<External-IP>:8090

##PersistentVolume and PersistentVolumeClaim

apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mysql-pvc  
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
        - name: mysql
          image: mysql:latest
          env:
            - name: MYSQL_ROOT_PASSWORD
              value: "your_password_here"
          ports:
            - containerPort: 3306
          volumeMounts:
            - name: mysql-persistent-storage
              mountPath: /var/lib/mysql
      volumes:
        - name: mysql-persistent-storage
          persistentVolumeClaim:
            claimName: mysql-pvc

...
apiVersion: v1
kind: PersistentVolume
metadata:
  name: mysql-pv
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: "/data/mysql"


##Run MySQL Client POD
Run a POD as mysql client
 Host: mysql
 Root User: root
 Password: root
 kubectl run -it --rm --image=mysql:8.0 --restart=Never mysql-client -- mysql -hmysql -uroot -proot
 
 
 ##IMPORTANT####
 When you use persistent volume in minikube, the volume will be mounted under minikube VM's /data directory.
 
 apiVersion: v1
kind: PersistentVolume
metadata:
  name: mysql-pv
  labels:
    type: local
spec:
  storageClassName: manual
  capacity:
    storage: 2Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: /data/mysql/  #minikube vm /data directory

##IMPORTANT####