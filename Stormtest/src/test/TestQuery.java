package test;

import storm.starter.bolt.PrinterBolt;
import storm.starter.spout.TwitterSampleSpout;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class TestQuery {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String keyword = "reservation";

		Object savedData = MemcacheSaver.getInstance().getDataFromMemCache(
				keyword);

		if (savedData == null) {

			System.err.println(" Found Nothing for the keyword " + keyword);
			TopologyBuilder builder = new TopologyBuilder();

			builder.setSpout("twitter", new TwitterSampleSpout(
					"Qy5b24hTUlM9PchKyp1rbnDOz",
					"GL78gicHGxZiBde4X02AMQOtj5uWQXCjQNLIR6h9A8zc7rhWA0",
					"3377235640-RPd7LvAHyV1RxtfKAkQjc7o8jXXFH0ocUa1Gym6",
					"5IHfMLjU5OOHRWUiZsu3gKPJDwD5rBePH85AXZDFN78TF",
					new String[] { keyword }));
			builder.setBolt("print", new SaveMemcacheBolt(keyword))
					.shuffleGrouping("twitter");

			Config conf = new Config();

			LocalCluster cluster = new LocalCluster();

			cluster.submitTopology("test", conf, builder.createTopology());

			Utils.sleep(80000);
			cluster.shutdown();
		}

		System.err.println("Found Data from memcache now "
				+ MemcacheSaver.getInstance().getDataFromMemCache(keyword));

	}

}
