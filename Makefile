start: build-server build-container run-container

build-server:
	cd ./pet-collector-server && mvn install

build-container:
	cd ./pet-collector-server && docker build --pull --rm -f "Dockerfile" -t petcollector:latest .

run-container:
	cd ./pet-collector-server && docker compose -f "docker-compose.yml" up -d --build

run-front-dev-server:
	cd ./frontend && npm start