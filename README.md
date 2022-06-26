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


mvn test
```