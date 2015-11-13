package camp.mahout.util.mahout;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 원본 : org.apache.mahout.cf.taste.impl.model.MemoryIDMigrator
 */
public class IDMigrator {
	
	private static final Logger logger = LoggerFactory.getLogger(IDMigrator.class);

	private final FastByIDMap<String> longToString;

	public IDMigrator() {
		this.longToString = new FastByIDMap<String>(100);
	}

	public void storeMapping(long longID, String stringID) {
		synchronized (longToString) {
			longToString.put(longID, stringID);
		}
	}

	public String toStringID(long longID) {
		synchronized (longToString) {
			return longToString.get(longID);
		}
	}
	
	public FastByIDMap<String> getIDMap() {
		logger.info("IDMigrator size => {}", longToString.size());
		
		return longToString;
	}
	
}