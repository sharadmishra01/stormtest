package storm.starter;

import java.util.Map;

import storm.starter.ExclamationTopology.ExclamationBolt;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class MyNameTopology {

	public static class MyNameBolt extends BaseRichBolt {

		OutputCollector collector;

		@Override
		public void execute(Tuple tuple) {
			collector.emit(tuple, new Values(tuple.getString(0) + "@sharad"));
			collector.ack(tuple);

		}

		@Override
		public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
			collector = arg2;
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer arg0) {
			arg0.declare(new Fields("word"));

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("word", new TestWordSpout(), 10);
		builder.setBolt("sharad", new MyNameBolt(), 3).shuffleGrouping("word");
		// builder.setBolt("exclaim2", new ExclamationBolt(),
		// 2).shuffleGrouping("exclaim1");

		Config conf = new Config();
		conf.setDebug(true);

		if (args != null && args.length > 0) {
			conf.setNumWorkers(3);

			StormSubmitter.submitTopologyWithProgressBar(args[0], conf,
					builder.createTopology());
		} else {

			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("test", conf, builder.createTopology());
//			Utils.sleep(10000);
//			cluster.killTopology("test");
//			cluster.shutdown();
		}
	}

}
