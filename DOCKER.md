# Docker

To create the container, execute the command:

```bash
docker run -e MYSQL_HOST=ip -e MYSQL_PORT=port -e MYSQL_DATABASE=database -e MYSQL_USER=user -e MYSQL_PASSWORD=password -p 8080:8080 systelab/seed-jee
```

Usually the mySQL database port is 3306.

You can create both containers with a docker-compose.yml file and the command docker-compose.

Here's a sample docker-compose.yml file:

```yaml
version: "2"
services:
  db:
    image: mysql:latest
    ports:
      - "3307:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=supersecret
      - MYSQL_DATABASE=SEED
      - MYSQL_USER=SEED
      - MYSQL_PASSWORD=SEED
  web:
    image: systelab/seed-jee:latest
    depends_on:
      - db
    environment:
      - MYSQL_URI=db:3306
      - MYSQL_HOST=db
      - MYSQL_PORT=3306
      - MYSQL_DATABASE=SEED
      - MYSQL_USER=SEED
      - MYSQL_PASSWORD=SEED
    ports:
      - "8080:8080"
```

Here's another docker-compose.yml file, but in that case with traefik as a reverse proxy, a mysql, two backend servers and the seed-angular as frontend:

```yaml
version: "2"
services:
  traefik:
    image: traefik
    command: --web --docker --docker.domain=docker.localhost --logLevel=DEBUG
    ports:
      - "90:80"
      - "8080:8080"
      - "443:443"
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
      - /dev/null:/traefik.toml
  db:
    image: mysql:latest
    ports:
      - "3307:3306"
    environment:
      - MYSQL_ROOT_PASSWORD= supersecret
      - MYSQL_DATABASE=SEED
      - MYSQL_USER=SEED
      - MYSQL_PASSWORD=SEED
  backend1:
    image: systelab/seed-jee:latest
    labels:
      - "traefik.frontend.rule=PathPrefix:/"
      - "traefik.frontend.priority=3"
      - "traefik.weight=1"
      - "traefik.port=8080"
      - "traefik.backend.loadbalancer.method=wrr"
    depends_on:
      - db
    environment:
      - MYSQL_URI=db:3306
      - MYSQL_HOST=db
      - MYSQL_PORT=3306
      - MYSQL_DATABASE=SEED
      - MYSQL_USER=SEED
      - MYSQL_PASSWORD=SEED
  backend2:
    image: systelab/seed-jee:latest
    labels:
      - "traefik.frontend.rule=PathPrefix:/"
      - "traefik.frontend.priority=1"
      - "traefik.weight=1"
      - "traefik.port=8080"
      - "traefik.backend.loadbalancer.method=wrr"
    depends_on:
      - db
    environment:
      - MYSQL_URI=db:3306
      - MYSQL_HOST=db
      - MYSQL_PORT=3306
      - MYSQL_DATABASE=SEED
      - MYSQL_USER=SEED
      - MYSQL_PASSWORD=SEED
  frontent1:
    image: systelab/seed-angular:latest
    ports:
      - "80:80"
    depends_on:
      - traefik
    environment:
      - BACKEND=http://127.0.0.1:90
```

In order to run this configuration use the command:

```bash
docker-compose up -d
```
