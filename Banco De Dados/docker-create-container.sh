# deleteing actual container and image
sudo docker stop ct-petfinder-db
sudo docker rm ct-petfinder-db
sudo docker rmi petfinder/db

# generate image and container
sudo docker build -t petfinder/db .
sudo docker run --name ct-petfinder-db -d -p 3306:3306 --network petfinder-network -e MYSQL_ROOT_PASSWORD=urubu100 -e MYSQL_DATABASE=petfinder petfinder/db

sudo docker exec -it ct-petfinder-db bash