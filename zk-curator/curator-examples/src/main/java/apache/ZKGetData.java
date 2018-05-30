package apache;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ZKGetData {

    private static Logger logger = LogManager.getLogger(ZKGetData.class);


    private static ZooKeeper zk;
    private static ZooKeeperConnection conn;
    public static Stat znode_exists(String path) throws
            KeeperException,InterruptedException {
        return zk.exists(path,true);
    }

    public static void main(String[] args) throws InterruptedException, KeeperException {
//        BasicConfigurator.configure();

        String path = "/MyFirstZnode";
//        final CountDownLatch connectedSignal = new CountDownLatch(1);



            try {
                conn = new ZooKeeperConnection();
                zk = conn.connect("192.168.1.147:2181,192.168.1.147:2182,192.168.1.147:2183");
//                zk = conn.connect("192.168.1.147:2181");


                do {


                Stat stat = znode_exists(path);

                if (stat != null) {

                    byte[] b = zk.getData(path, new Watcher() {

                        public void process(WatchedEvent we) {

                            if (we.getType() == Event.EventType.None) {
                                switch (we.getState()) {
                                    case Expired:
//                                        connectedSignal.countDown();
                                        break;
                                }

                            } else {
                                String path = "/MyFirstZnode";

                                try {

                                    byte[] bn = zk.getData(path,
                                            false, null);
                                    String data = new String(bn,
                                            "UTF-8");
//                                    System.out.println(data);
//                                    logger.debug(data);

//                                    connectedSignal.countDown();
                                    System.out.println("----==== getDataing ====----");

                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
                    }, null);

                    String data = new String(b, "UTF-8");
                    System.out.println(data);
//                    connectedSignal.await();
                    logger.info(data);


                } else {
                    System.out.println("Node does not exists");
                }


                TimeUnit.SECONDS.sleep(5);
            } while(true);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

    }
}