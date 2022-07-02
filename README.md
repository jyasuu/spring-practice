# Practice


## Test 

```sh
# start postgres
 docker run -d \
	--name postgres \
	-e POSTGRES_PASSWORD=postgres \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-v /custom/mount:/var/lib/postgresql/data \
	postgres

# using root
docker inspect postgres --format '{{.NetworkSettings.IPAddress}}' |  awk '{print $1" postgres"}' >> /etc/hosts


mvn test
```

## curl test

```sh
curl localhost:8080/greeting

curl localhost:8080/todo

curl -X POST localhost:8080/todo?task=task1 \
   -H 'Content-Type: application/json' \
   -d '{"task":"task2"}'

curl -X DELETE localhost:8080/todo/1 

```

## psql

```sh

apt install postgresql-client

psql -h postgres -d postgres -U postgres -W

\d

\d todo


```

## todo

https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/