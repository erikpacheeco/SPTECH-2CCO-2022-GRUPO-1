# deleting container and image
sudo docker stop ct-petfinder-api
sudo docker rm ct-petfinder-api
sudo docker rmi petfinder/petfinder-api

# dependencies
sudo mvn package

# building image and container
sudo docker build -t petfinder/petfinder-api .
sudo docker run -it --name ct-petfinder-api --network petfinder-network -d -p 8080:8080 petfinder/petfinder-api