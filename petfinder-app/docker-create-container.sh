#Making deploy log
git log --oneline -n 1 | paste - - - > ../prod-log/deploy_3000.txt

# removing existent container/images
sudo docker stop ct-petfinder-app
sudo docker rm ct-petfinder-app
sudo docker rmi petfinder/petfinder-app

npm i

# building image and container
sudo docker build -t petfinder/petfinder-app . 
sudo docker run -it --name ct-petfinder-app -d -p 3000:3000 --network petfinder-network petfinder/petfinder-app