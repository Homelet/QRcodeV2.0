package homelet.QRCode.encode;
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

import homelet.Utile.Constants.CharacterSet;
import homelet.Utile.Constants.EncodeCorrectionLevel;
import homelet.Utile.Constants.EncodeMode;
import homelet.Utile.Constants.EncodeVersion;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 27, 2018
 */
public class EncodePreset{
	
	public final char   ErrorCorrection;
	public final char   encodeMode;
	public final int    version;
	public final String charSet;
	
	/**
	 * public static QRCode encode(String content, char ErrorCorrection, char EncodeMode, int Version, String charSetName
	 * constructor for EncodePreset
	 * TODO
	 *
	 * @author HomeletWei
	 */
	private EncodePreset(char erroCorrection, char encodeMode, int version, String charSet){
		this.ErrorCorrection = erroCorrection;
		this.encodeMode = encodeMode;
		this.version = version;
		this.charSet = charSet;
	}
	
	public static EncodePreset getInstance(boolean useCustomize, int error, int encodeMode, int version, int chatset){
		if(!useCustomize)
			return new EncodePreset(EncodeCorrectionLevel.getRecommended(), EncodeMode.getRecommended(), EncodeVersion.getRecommended(), CharacterSet.getRecommended());
		return new EncodePreset(EncodeCorrectionLevel.getCorrectionLevel(error), EncodeMode.getEncodeMode(encodeMode), EncodeVersion.getEncodeVersion(version), CharacterSet.getCharactertSet(chatset));
	}
}
