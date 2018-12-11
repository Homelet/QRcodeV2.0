/**
 * @author HomeletWei
 * @date Apr 1, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.QRCode.encode;

import com.swetake.util.Qrcode;
import homelet.QRCode.QRCode;

import java.awt.*;
import java.io.UnsupportedEncodingException;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 1, 2018
 */
public class QRCodeEncoder{
	
	/**
	 * construct a QRCode
	 * Error correcting is defined in 4 level as below.
	 * <ul>
	 * <li>level L : About 7% or less errors can be corrected.</li>
	 * <li>level M : About 15% or less errors can be corrected.</li>
	 * <li>level Q : About 25% or less errors can be corrected.</li>
	 * <li>level H : About 30% or less errors can be corrected.</li>
	 * </ul>
	 * EncodeMode <ul>
	 * <li>numeric(0-9)</li>
	 * <ul>
	 * <li>3 characters are encoded to 10bit length.</li>
	 * <li>In theory, 7089 characters or less can be stored in a QRcode.</li>
	 * </ul>
	 * <li>alphanumeric(0-9A-Z $%*+-./:)45characters</li>
	 * <ul>
	 * <li>2 characters are encoded to 11bit length.</li>
	 * <li>In theory, 4296 characters or less can be stored in a QRcode.</li>
	 * </ul>
	 * <li>8bit byte data</li>
	 * <ul>
	 * <li>In theory, 2953 characters or less can be stored in a QRcode.</li>
	 * </ul>
	 * <li>KANJI</li>
	 * <ul>
	 * <li>A KANJI character(this is multi byte character) is encoded to 13bit length.</li>
	 * <li>In theory, 1817 characters or less can be stored in a QRcode.</li>
	 * </ul>
	 * </ul>
	 * <ul>
	 * <li>Version is from 1 to 40.</li>
	 * <li>Version 1 is 21*21 matrix. And 4 modules increases whenever 1 version increases.
	 * So version 40 is 177*177 matrix.</li>
	 * </ul>
	 * EncodeCharSet
	 * <ul>
	 * <li>US-ASCII</li>
	 * <ul>
	 * <li>Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set</li>
	 * </ul>
	 * <li>ISO-8859-1  </li>
	 * <ul>
	 * <li>ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1</li>
	 * </ul>
	 * <li>UTF-8</li>
	 * <ul>
	 * <li>Eight-bit UCS Transformation Format</li>
	 * </ul>
	 * <li>UTF-16BE</li>
	 * <ul>
	 * <li>Sixteen-bit UCS Transformation Format, big-endian byte order</li>
	 * </ul>
	 * <li>UTF-16LE</li>
	 * <ul>
	 * <li>Sixteen-bit UCS Transformation Format, little-endian byte order</li>
	 * </ul>
	 * <li>UTF-16</li>
	 * <ul>
	 * <li>Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark</li>
	 * </ul>
	 * </ul>
	 * QRcodeVersion Size of QRcode is defined as version.
	 * ErrorCorrection QRcode has a function of an error correcting for miss reading that white is black.
	 *
	 * @param content the content that wants to encode in the QRcode
	 * @param preset
	 * @return a QRCode Object
	 * @throws UnsupportedEncodingException
	 * @throws IndexOutOfBoundsException
	 * @author HomeletWei
	 */
	public static QRCode encode(String content, EncodePreset preset) throws IndexOutOfBoundsException{
		if(content == null || content.isEmpty()){
			return null;
		}
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect(preset.ErrorCorrection);
		qrcode.setQrcodeEncodeMode(preset.encodeMode);
		qrcode.setQrcodeVersion(preset.version);
		try{
			return new QRCode(qrcode.calQrcode(content.getBytes(preset.charSet)), getQRCodeDimention(qrcode.getQrcodeVersion()), preset);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get the QRcode Dimension
	 *
	 * @param QRCodeVersion the version
	 * @return an dimension of the QRcode in certain QRcode version
	 * @author HomeletWei
	 */
	public static Dimension getQRCodeDimention(int QRCodeVersion){
		int di = 21 + (QRCodeVersion - 1) * 4;
		//177 ＝ 21+（40-1）＋4 ＝ 21 ＋156.
		return new Dimension(di, di);
	}
}
