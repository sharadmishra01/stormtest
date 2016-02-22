package test;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class SaveMemcacheBolt extends BaseBasicBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key = null;

	/**
	 * @param args
	 */

	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		System.err.println(tuple.getValues());
		try {
			System.err.println("Saving some thing for key " + key);
			MemcacheSaver.getInstance().saveDataToMemCache(key,
					tuple.getValue(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SaveMemcacheBolt(String key) {
		super();
		this.key = key;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer ofd) {
	}

}
