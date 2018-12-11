/**
 * @author HomeletWei
 * @date Apr 27, 2018
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
 * @date Apr 27, 2018
 */
public enum EncodeVersion{
	AUTO(0),
	V_1(1),
	V_2(2),
	V_3(3),
	V_4(4),
	V_5(5),
	V_6(6),
	V_7(7),
	V_8(8),
	V_9(9),
	V_10(10),
	V_11(11),
	V_12(12),
	V_13(13),
	V_14(14),
	V_15(15),
	V_16(16),
	V_17(17),
	V_18(18),
	V_19(19),
	V_20(20),
	V_21(21),
	V_22(22),
	V_23(23),
	V_24(24),
	V_25(25),
	V_26(26),
	V_27(27),
	V_28(28),
	V_29(29),
	V_30(30),
	V_31(31),
	V_32(32),
	V_33(33),
	V_34(34),
	V_35(35),
	V_36(36),
	V_37(37),
	V_38(38),
	V_39(39),
	V_40(40);
	public static final  int             defaultValue = 7;
	public static final  String[]        versionList  = new String[]{ "Auto", "1", "2", "3", "4", "5", "6", "7 (Default)", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" };
	private static final int             recommended  = 0;
	private static final EncodeVersion[] versionLists = new EncodeVersion[]{ AUTO, V_1, V_2, V_3, V_4, V_5, V_6, V_7, V_8, V_9, V_10, V_11, V_12, V_13, V_14, V_15, V_16, V_17, V_18, V_19, V_20, V_21, V_22, V_23, V_24, V_25, V_26, V_27, V_28, V_29, V_30, V_31, V_32, V_33, V_34, V_35, V_36, V_37, V_38, V_39, V_40 };
	int verison;
	
	/**
	 * constructor for EncodeVersion
	 * TODO
	 *
	 * @author HomeletWei
	 */
	private EncodeVersion(int version){
		this.verison = version;
	}
	
	public static int getRecommended(){
		return getEncodeVersion(recommended);
	}
	
	public static int getEncodeVersion(int indexSelected){
		return versionLists[indexSelected].verison;
	}
}
