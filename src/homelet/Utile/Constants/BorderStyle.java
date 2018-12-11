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
public enum BorderStyle{
	RECTANGULAR,
	ROUND_RECTANGULAR,
	DASHED(10),
	DOTTED(10),
	CONNERS,
	STATIC_CONNERS,
	//	EXCULD_CONNERS,
	VOID;
	public static final String[]      borderMode   = new String[]{ "Rectangular Border", "Round-Rectangle Border", "Dashed", "Dotted", "Conners", "No Border" };
	public static final BorderStyle[] border       = new BorderStyle[]{ RECTANGULAR, ROUND_RECTANGULAR, DASHED, DOTTED, CONNERS, VOID };
	public static       int           defaultValue = 0;
	Object[] values;
	
	private BorderStyle(Object... values){
		this.values = values;
	}
	
	public static BorderStyle getBoarder(int selectedIndex){
		return border[selectedIndex];
	}
	
	/** @return values */
	public Object[] getValues(){
		return values;
	}
}
