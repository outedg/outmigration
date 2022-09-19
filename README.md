# outmigration
migration


### Rodar oracle container DOCKER
```
docker login container-registry.oracle.com
docker pull container-registry.oracle.com/database/express:21.3.0-xe
docker run --name oraclexpress -p 1521:1521 -e ORACLE_PWD=P4S$W0Rd container-registry.oracle.com/database/express:21.3.0-xe
```

### Url link para rodar oracle em um container docker.

https://ronekins.com/2020/10/07/oracle-19c-on-docker-and-kubernetes-part-1-getting-started/
https://ronekins.com/2020/10/10/oracle-19c-on-docker-and-kubernetes-part-2-running-oracle-on-docker/