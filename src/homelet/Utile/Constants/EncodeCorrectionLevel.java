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
public enum EncodeCorrectionLevel{
	L('L', 0.07),
	M('M', 0.15),
	Q('Q', 0.25),
	H('H', 0.30);
	public static final  int                     defaultValue    = 1;
	private static final int                     recommended     = 1;
	public static        String[]                correctionLevel = new String[]{ "L (Low)", "M (Medium)", "Q (Quality)", "H (High)" };
	public static        EncodeCorrectionLevel[] levels          = new EncodeCorrectionLevel[]{ L, M, Q, H };
	char   value;
	double erroCorrector;
	
	/**
	 * constructor for EncodeCorrectionLevel
	 * TODO
	 *
	 * @author HomeletWei
	 */
	private EncodeCorrectionLevel(char value, double erroCorrector){
		this.value = value;
		this.erroCorrector = erroCorrector;
	}
	
	public double getErroCorrector(){
		return erroCorrector;
	}
	
	public static char getRecommended(){
		return getCorrectionLevel(recommended);
	}
	
	public static char getCorrectionLevel(int selectedIndex){
		return levels[selectedIndex].value;
	}
	
	public static EncodeCorrectionLevel getCorrectionLevel(char selectedType){
		for(EncodeCorrectionLevel encodeCorrectionLevel : EncodeCorrectionLevel.levels){
			if(encodeCorrectionLevel.value == selectedType){
				return encodeCorrectionLevel;
			}
		}
		return null;
	}
}
