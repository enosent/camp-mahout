package camp.mahout.util.mahout;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;

public class IDMap {

	private FastByIDMap<String> map = null;

	public IDMap(FastByIDMap<String> dataMap) {
		map = dataMap;
	}

	public String getData(Long id){
		return map.get(id);
	}
	
}