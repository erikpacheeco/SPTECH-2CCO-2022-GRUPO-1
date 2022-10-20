cd ../petfinder-app/src

rm Api.js && rm ApiMsg.js

cp ../../prod-log/fe-deploy/Api.js  .
cp ../../prod-log/fe-deploy/ApiMsg.js  .

cd ..

sudo bash docker-create-container.sh
