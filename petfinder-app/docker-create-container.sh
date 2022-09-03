# removing existent container/images
sudo docker stop ct-petfinder-app
sudo docker rm ct-petfinder-app
sudo docker rmi petfinder/petfinder-app

# building image and container
sudo docker build -t petfinder/petfinder-app .
sudo docker run -it --name ct-petfinder-app -d -p 3000:3000 petfinder/petfinder-app