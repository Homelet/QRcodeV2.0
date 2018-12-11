/**
 * @author HomeletWei
 * @date Apr 25, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Utile.Constants;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 25, 2018
 */
public enum CharacterSet{
	US_ASCII("US-ASCII"),
	UTF_8("UTF-8"),
	UTF_16("UTF-16");
	public static final  int            defaultValue  = 1;
	public static final  String[]       characterSets = { "ASCII (American Standard Code for Information Interchange)", "UTF-8 (8-bit Unicode Transformation Format)", "UTF-16 (16-bit Unicode Transformation Format)" };
	private static final int            recommended   = 1;
	private static final CharacterSet[] characterSet  = new CharacterSet[]{ US_ASCII, UTF_8, UTF_16 };
	String representive;
	
	private CharacterSet(String representive){
		this.representive = representive;
	}
	
	public static String getRecommended(){
		return getCharactertSet(recommended);
	}
	
	public static String getCharactertSet(int selected){
		return characterSet[selected].representive;
	}
}
