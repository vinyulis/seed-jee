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

In order to run this configuration use the command:

```bash
docker-compose up -d
```

Once started, browse http://localhost:8080/seed/swagger/ in order to get the API main page

Here's the file docker-compose-with-elk.yml file. In this case, several containers will start, with traefik as a reverse proxy, mysql, two backend servers, seed-angular as frontend in a nginx server and elasticsearch, logstash and kibana:

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
      - "traefik.frontend.priority=1"
      - "traefik.weight=1"
      - "traefik.port=8080"
      - "traefik.backend.loadbalancer.method=wrr"
    depends_on:
      - db
      - logstash
    environment:
      - MYSQL_URI=db:3306
      - MYSQL_HOST=db
      - MYSQL_PORT=3306
      - MYSQL_DATABASE=SEED
      - MYSQL_USER=SEED
      - MYSQL_PASSWORD=SEED
      - LOGSTASH_HOST=logstash
      - LOGSTASH_PORT=5000
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
      - logstash
    environment:
      - MYSQL_URI=db:3306
      - MYSQL_HOST=db
      - MYSQL_PORT=3306
      - MYSQL_DATABASE=SEED
      - MYSQL_USER=SEED
      - MYSQL_PASSWORD=SEED
      - LOGSTASH_HOST=logstash
      - LOGSTASH_PORT=5000
  frontent1:
    image: systelab/seed-angular:latest
    ports:
      - "80:80"
    depends_on:
      - traefik
    environment:
      - BACKEND=http://127.0.0.1:90
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch-oss:6.2.1
    volumes:
      - ./docker/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml:ro
    ports:
      - "9200:9200"
      - "9300:9300"
    environment:
      ES_JAVA_OPTS: "-Xmx256m -Xms256m"
  logstash:
    image: docker.elastic.co/logstash/logstash-oss:6.2.1
    volumes:
      - ./docker/logstash/config/logstash.yml:/usr/share/logstash/config/logstash.yml:ro
      - ./docker/logstash/pipeline:/usr/share/logstash/pipeline:ro
    ports:
      - "5000:5000"
    environment:
      LS_JAVA_OPTS: "-Xmx256m -Xms256m"
    depends_on:
      - elasticsearch
  kibana:
    image: docker.elastic.co/kibana/kibana-oss:6.2.1
    volumes:
      - ./docker/kibana/config/:/usr/share/kibana/config:ro
    ports:
      - "5601:5601"
    depends_on:
      - elasticsearch

```

In order to run this configuration use the command:

```bash
docker-compose -f docker-compose-with-elk.yml up -d
```

Browse http://localhost to run the application, http://localhost:90/seed/swagger/ in order to get the API main page, http://localhost:8080/ to get traefik  and http://localhost:5601/ to get the Kibana main page. 

> [Diagramr](http://diagramr.inventage.com/) could help you in order to understand the docker-compose files.
