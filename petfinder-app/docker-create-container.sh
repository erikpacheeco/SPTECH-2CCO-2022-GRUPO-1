# building image and container
sudo docker build -t petfinder/petfinder-app .
sudo docker run -it --name ct-petfinder-app --network petfinder-network -d -p 3000:3000 petfinder/petfinder-app