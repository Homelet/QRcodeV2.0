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
public enum EncodeMode{
	EIGHT_BYTE_DATA('B'),
	NUMERIC('N'),
	ALPHANUMERIC('A'),
	KANJI('K');
	public static final  int          defaultValue = 0;
	public static final  String[]     encodeMode   = { "8 byte data (Original Data)", "Numeric (0-9 ONLY)", "Alphanumeric (0-9 A-Z $%*+-./:)", "KANJI (Japanese Character)" };
	private static final int          recommended  = 0;
	private static final EncodeMode[] encodeModes  = new EncodeMode[]{ EIGHT_BYTE_DATA, NUMERIC, ALPHANUMERIC, KANJI };
	char value;
	
	/**
	 * constructor for EncodeMode
	 * TODO
	 *
	 * @author HomeletWei
	 */
	private EncodeMode(char value){
		this.value = value;
	}
	
	public static char getRecommended(){
		return getEncodeMode(recommended);
	}
	
	public static char getEncodeMode(int indexSelected){
		return encodeModes[indexSelected].value;
	}
}
