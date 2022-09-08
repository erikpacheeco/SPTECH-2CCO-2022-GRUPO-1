# removing existent container/images
sudo docker stop ct-petfinder-static
sudo docker rm ct-petfinder-static
sudo docker rmi petfinder/petfinder-static

# building image and container
sudo docker build -t petfinder/petfinder-static .
sudo docker run -it --name ct-petfinder-static --network petfinder-network -d -p 3001:3001 petfinder/petfinder-static