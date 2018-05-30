# zookeeper cluster recconect test

## To be tested
1. zookeeper: raw, low lever api.
2. curator: high level api for raw zookeeper, and must have for frameworks.
3. spring-cloud-zookeeper: framework got curator embedded implements reconnection.
4. dubbo: framework got curator embedded implements reconnection.

## Results (connect-string)
### Single host:port 
All (above 1,3,4) SAME: 
- leader/follower (leader is not the access host:port) switch will not break the connection;
- access host:port down will break the connection and exit.

### Triple host:port
ALL (above 1,3,4) SAME: 
leader/follower switch and access host:port down will caues different errors. Difference as follows:

For zookeeper: TRICKY
- Basically recconnection is not supported, unless manually design and add CountDownLatch(n), For loop with well tweaked TimeUnit.Seconds.sleep(n). All these measures will cause complexity for use.

For framework (dubbo, spring-cloud): SIMPLE
- All the recconection procedures are well handled by the frameworks, no matter leader/follower switch and access host:port down scenarios.

## Notes:
1. curator as middle package is not tested, but codes are in the zk-curator/curator-examples.
2. zookeeper raw is tricky, but basically the low level api doesn't support recconect issues.