echo
echo "Building dockers"

docker build -t back/jumia .

echo
echo "Running ... "
echo

docker run -d --name jumiaBack -p 8080:8080 --network=host --rm back/jumia


cd front-end/data-table
echo
echo

docker build -t front/jumia .

docker run -d --name jumiaFront -p 3000:3000 --network=host --rm front/jumia


echo "###########################################"
echo "You can access the app using this URL: http://localhost:3000"
echo "###########################################"