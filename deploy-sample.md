![application-server-connections](/uploads/a4931e6d2a81269c4ed0f728dcd7c248/application-server-connections.jpg)

## Application Server
* `username`:`vahid`, `password`:`vahid12345`
* executive files

| file | description |
| :------------- | :------------- |
| `/home/vahid/start-mongodb.sh` | starts mongo-db on port `27017` |
| `/home/vahid/kill-mongodb.sh` | kills mongo-db on port `27017` |
| `/home/vahid/start-redis.sh`| starts a redis-server instant on port `3030` |
| `/home/vahid/kill-redis.sh`| kills redis-server instant runs on port `3030` |
| `/home/vahid/start-server.sh` | starts a wildfly-10 instanse for application-server on port `8180`, remote debug on port `8181`, management console on port `10090` (username:`vahid`, password:`vahid12345`)|
| `/home/vahid/kill-server.sh` | kills application server|
| `/home/vahid/start-management-server.sh`, `kill` | start a wildfly-10 instanse for management-server on port `8280`, remote debug on `8281`, management console on port `10190`|
| `/home/vahid/kill-management-server.sh` | kills management server|
| `/home/vahid/start-warehouse-server.sh` | start a wildfly-10 instanse for management-server on port `8380`, remote debug on `8381`, management console on port `10290` |
| `/home/vahid/kill-warehouse-server.sh` | kills warehouse server|
| `/home/vahid/start-notification-center.sh` | runs a jar file that create a connection on port `5555` with application server using `zeroMQ` jms|
| `/home/vahid/kill-notification-center.sh` | kills notification server|
| `/home/vahid/start-filebeats.sh` | read changes on files determined in `filebeats.yml`(in filebeats home) and send to logstash via port `5044`|
| `/home/vahid/deploy.sh` | pull war file changes and deploy last changed war file on application server|
| `/home/vahid/deploy-management.sh` | pull war file changes and deploy last changed war file on management server|
| `/home/vahid/deploy-warehouse.sh` | pull war file changes and deploy last changed war file on warehouse server|
| `/home/vahid/kill-all.sh` | runs all of `./kill-redis.sh`, `./kill-mongodb.sh`, `./kill-notification-center.sh`, `./kill-server.sh`, `./kill-management-server.sh`, `./kill-warehouse-server.sh`|
| `/home/vahid/service-checker.sh` | check `start-redis.sh`, `start-mongodb.sh`, `start-notification-center.sh`, `start-management-server.sh`, `start-warehouse-server.sh` every minute and start them if downed |
| `/home/vahid/db-backup.sh` | create db backup to directory `/opt/db/db-backup/` everyday |
| `/home/vahid/redis-backup.sh` | save redis content to directory `/var/lib/redis/dump.rdb` every minute |
| start apache2, nagios servies | you can monitor with `172.22.32.16/nagios` -- `username`:`nagiosadmin`, `password`:`ord12345` -- `config-file`: `/usr/local/nagios/etc/servers/my_host.cfg`, `/usr/local/nagios/etc/nagios.cfg` |
| `/home/vahid/parse-dashboard.sh` | starts parse dashboard on port `4040` |

## Proxy Server
* `username`: `vahid`, `password`: `vahid12345`
* `nginx` runs as a service on port `80`, it's config file directory is `/etc/nginx/sites-available/wildfly`
* executive files

| file | description |
| :------------- | :------------- |
| `/home/vahid/start-proxy-server1.sh` | starts wildfly-10 on port `8081`, debug on port `8181`, management console on port `9991` |
| `/home/vahid/start-proxy-server2.sh` | starts wildfly-10 on port `8082`, debug on port `8182`, management console on port `9992`  |
| `/home/vahid/kill-proxy-servers.sh` | kill both proxy server 1 and 2 |
| `/home/vahid/deploy.sh` | pull last changed war file and deploy on proxy server 1 |
| `/home/vahid/deploy2.sh` | pull last changed war file and deploy on proxy server 2 |
| `/home/vahid/start-filebeats.sh` | read changes on files determined in `filebeats.yml`(in filebeats home) and send to logstash via port `5044` |
| `/home/vahid/parse-server.sh` | starts parse server on port `1337` |

## file Server
* `username`: `vahid`, `password`: `qwe123`
* executive files

| file | description |
| :------------- | :------------- |
| cd $HADOOP_HOME  &&  ./bin/hdfs namenode -format && ./sbin/start-all.sh| start `namenode`,`namenode web ui` on `50070` , `datanode server` for data transfer on `50010`, `datanode http server` on `50075`, `datanode ipc server` on `50020`, `secondary namenode` on `50090` , `yarn`, `nodemanager` |
| `/home/vahid/start-file-server.sh` | starts file-server on port `9595` |
| `/home/vahid/kill-file-server.sh` | kill file-server |
| `/home/vahid/start-filebeats.sh` | for sending log file changes to logstash |

## log server
* `username`: `vahid`, `password`: `qwe123`
* executive files

| file | description |
| :------------- | :------------- |
| `/home/vahid/start-elastic-search.sh` | starts elastic search on port `9200` just for localhost requests (just for kibana and logstash that installed on localhost) |
| `/home/vahid/kill-elastic-search.sh` | kill elastic search |
| `/home/vahid/start-logstash.sh` | starts logstash that pull data from port `5044` from file beats on other machines and , parse them by `grok` defined on `/home/vahid/elastic-search/logstash-5.6.2/conf-files/beats.conf` filter and send them via port `9200` to elastic search |
| `/home/vahid/kill-logstash.sh` | kill logstash |
| `/home/vahid/start-kibana.sh` | starts kibana on port `5601` (determine elastic search to read from it via file `/home/vahid/elastic-search/kibana-5.6.2-linux-x86_64/config/kibana.yml`) to search, filter and reports on logs|
| `/home/vahid/kill-kibana.sh` | kill kibana |





