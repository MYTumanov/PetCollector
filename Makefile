start: build-server1 build-server2 build-container1 build-container2 run-container

build-server1:
	cd ./pet-collector-server && mvn clean install

build-server2:
	cd ./pet-collector-authenticator && mvn clean install

build-container1:
	cd ./pet-collector-server && docker build --pull --rm -f "Dockerfile" -t petcollector-server:latest .

build-container2:
	cd ./pet-collector-authenticator && docker build --pull --rm -f "Dockerfile" -t petcollector-authenticator:latest .

run-container:
	docker compose -f "docker-compose.yml" up -d --build

run-front-dev-server:
	cd ./frontend && npm start