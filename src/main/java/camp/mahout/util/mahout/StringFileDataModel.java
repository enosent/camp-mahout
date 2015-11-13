package camp.mahout.util.mahout;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringFileDataModel extends FileDataModel {

	private static final long serialVersionUID = 8999340087368589507L;
	
	private static final Logger logger = LoggerFactory.getLogger(StringFileDataModel.class);

	private IDGenerator generator;
	
	public StringFileDataModel(File dataFile) throws IOException {
		super(dataFile);
	}
	
	@Override
	protected long readUserIDFromString(String value) {
		if( generator == null ) {
			generator = new IDGenerator();
		}
		
		long userID = generator.toLongID(value);
		
		if(logger.isDebugEnabled()){
			logger.debug("userID => " + userID);
		}
		
		return userID;
	}

	@Override
	protected long readItemIDFromString(String value) {
		if( generator == null ) {
			generator = new IDGenerator();
		}
		
		long itemID = generator.toLongID(value);
		
		if(logger.isDebugEnabled()){
			logger.debug("itemID => {}", itemID);
		}
		
		return itemID;
	}
	
	@Override
	protected long readTimestampFromString(String value) {
		if( generator == null ) {
			generator = new IDGenerator();
		}
		
		long timestampID = generator.toLongID(value);
		
		if(logger.isDebugEnabled()){
			logger.debug("timestampID => {}", timestampID);
		}
		
		return timestampID;
	}

	public FastByIDMap<String> getIndexedMapInstansce(){
		return generator.getIndexedMapInstansce();
	}
	
}