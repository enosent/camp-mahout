package camp.mahout.util;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.MemoryIDMigrator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

public class SampleDataModel extends FileDataModel {

	private static final long serialVersionUID = 3629022487865723346L;

	private MemoryIDMigrator migrator;

	public SampleDataModel(File dataFile) throws IOException {
		super(dataFile);
	}

	@Override
	protected long readUserIDFromString(String value) {
		if(migrator == null) {
			migrator = new MemoryIDMigrator();
		}

		long longID = migrator.toLongID(value);
		migrator.storeMapping(longID, value);

		return longID;
	}

	@Override
	protected long readItemIDFromString(String value) {
		if(migrator == null) {
			migrator = new MemoryIDMigrator();
		}

		long longID = migrator.toLongID(value);
		migrator.storeMapping(longID, value);

		return longID;
	}

	public MemoryIDMigrator getMigrator(){
		return migrator;
	}

}