package camp.mahout.util.mahout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;

/*
 * 원본 : org.apache.mahout.cf.taste.impl.model.AbstractIDMigrator
 */
public class IDGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(IDGenerator.class);

	private final MessageDigest md5Digest;

	private IDMigrator migrator;

	public IDGenerator() {
		try {
			md5Digest = MessageDigest.getInstance("MD5");

			migrator = new IDMigrator();

		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * @return most significant 8 bytes of the MD5 hash of the string, as a long
	 */
	protected final long hash(String value) {
		byte[] md5hash;
		synchronized (md5Digest) {
			md5hash = md5Digest.digest(value.getBytes(Charsets.UTF_8));
			md5Digest.reset();
		}
		long hash = 0L;
		for (int i = 0; i < 8; i++) {
			hash = hash << 8 | md5hash[i] & 0x00000000000000FFL;
		}
		return hash;
	}

	public long toLongID(String stringID) {
		if(logger.isDebugEnabled()){
			logger.debug("stringID => {}", stringID);
		}
		
		Long id = 0l;
		
		try {
			id = hash(stringID);
			
			if(migrator.toStringID(id) == null){
				migrator.storeMapping(id, stringID);
			}
		} catch (Exception e) {
			logger.error("converstion exception => {}", e.getMessage());
		}

		return id;
	}

	protected FastByIDMap<String> getIndexedMapInstansce(){
		return migrator.getIDMap();
	}
	
}