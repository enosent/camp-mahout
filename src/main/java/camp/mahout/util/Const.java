package camp.mahout.util;

public class Const {

	public static String getSampleFile() {
		return Const.class.getClassLoader().getResource("u.data").getFile();
	}
	
	public static String getUserKeywordFile() {
		return Const.class.getClassLoader().getResource("user.data").getFile();
	}
	
}