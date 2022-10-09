#Making deploy log
git log --oneline -n 1 | paste - - - > ../prod-log/deploy_8081.txt

# deleting container and image
sudo docker stop ct-petfinder-api-msg
sudo docker rm ct-petfinder-api-msg
sudo docker rmi petfinder/petfinder-api-msg

# dependencies
sudo mvn package

# building image and container
sudo docker build -t petfinder/petfinder-api-msg .
sudo docker run -it --name ct-petfinder-api-msg --network petfinder-network -d -p 8081:8081 petfinder/petfinder-api-msg