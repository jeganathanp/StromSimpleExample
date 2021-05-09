package dev.jegan.simple;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class MyTopology {
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("IntegerSpout", new IntegerSpout());
        builder.setBolt("MultiplerBolt", new MultiplerBolt()).shuffleGrouping("IntegerSpout");

        Config config = new Config();
        config.setDebug(true);
        LocalCluster cluster = new LocalCluster();
        try {

            cluster.submitTopology("simpleTopology", config, builder.createTopology());
            Thread.sleep(10000);
        } catch (Exception e) {
        } finally {
            cluster.shutdown();
        }

    }
}