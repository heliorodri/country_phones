echo
echo "Building dockers"

docker build -t jumia/phones .

echo
echo "Running ... "
echo

docker run --name jumia/phones -p 8080:8080
