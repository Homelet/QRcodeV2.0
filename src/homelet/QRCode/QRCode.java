/**
 * @author HomeletWei
 * @date Apr 11, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.QRCode;

import homelet.QRCode.encode.EncodePreset;
import homelet.Utile.Coloring;
import homelet.Utile.Constants.EncodeCorrectionLevel;
import homelet.Utile.Constants.QRcodeApperance;
import homelet.Utile.ReverseArc;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 11, 2018
 */
public class QRCode{
	
	final int[][] restant = { { 0, 1, 3 }, { 2, 1, 5 }, { 6, 3, 7 }, { 8, 5, 7 } };
	boolean[][]  qrcodeMatrix;
	Dimension    qrcodeDI;
	EncodePreset preset;
	
	/**
	 * constructor for QRCode
	 *
	 * @param qrcodeMatrix
	 * @param qrcodeDI
	 * @author HomeletWei
	 */
	public QRCode(boolean[][] qrcodeMatrix, Dimension qrcodeDI, EncodePreset preset){
		this.qrcodeMatrix = qrcodeMatrix;
		this.qrcodeDI = qrcodeDI;
		this.preset = preset;
	}
	
	/**
	 * constructor for QRCode
	 * for decode
	 *
	 * @param qrcodeMatrix
	 * @param qrcodeDI
	 * @author HomeletWei
	 */
	public QRCode(boolean[][] qrcodeMatrix, Dimension qrcodeDI){
		this(qrcodeMatrix, qrcodeDI, null);
	}
	
	public EncodePreset getPreset(){
		return preset;
	}
	
	public Dimension getMaximunSpaceForIcon(int sizePerUnit){
		Dimension di               = this.getQrcodeDI(sizePerUnit);
		double    encodeCorrection = EncodeCorrectionLevel.getCorrectionLevel(getPreset().ErrorCorrection).getErroCorrector();
		di.setSize(di.getWidth() * encodeCorrection, di.getHeight() * encodeCorrection);
		return di;
	}
	
	public BufferedImage draw(Coloring coloring, QRcodeApperance apperance, int sizePerunit, int persentageOfCurvature){
		switch(coloring.getMode()){
			case PAIN_COLOR:
				switch(apperance){
					case NORMAL:
						return draw_PainColor_normal(coloring.getColor(), sizePerunit);
					case LIQUEFIED:
						return draw_PainColor_Liquefied(coloring.getColor(), persentageOfCurvature, sizePerunit);
				}
			case COLOR_MAP:
				switch(apperance){
					case NORMAL:
						return draw_ColorMatrix_normal(getColorMatrix(coloring, sizePerunit), sizePerunit);
					case LIQUEFIED:
						return draw_ColorMatrix_Liquefied(getColorMatrix(coloring, sizePerunit), persentageOfCurvature, sizePerunit);
				}
			case NULL:
				return null;
		}
		return null;
	}
	
	private int[][][] getColorMatrix(Coloring coloring, int sizePerUnit){
		return coloring.getGradientColorMappingMode().getGradiantColorMap(coloring.getColor(0), coloring.getColor(1), getQrcodeDI(sizePerUnit));
	}
	
	/**
	 * draw a pain color qrcode
	 *
	 * @param foregroundColor the color of the qrcode
	 * @param sizePerUnit     size per unit qrcode
	 * @return a bufferedImage with qrcode on it
	 * @author HomeletWei
	 */
	public BufferedImage draw_PainColor_normal(Color foregroundColor, int sizePerUnit){
		BufferedImage qrcodeImage = new BufferedImage(qrcodeDI.width * sizePerUnit, qrcodeDI.height * sizePerUnit, BufferedImage.TYPE_INT_ARGB);
		Graphics2D    g           = qrcodeImage.createGraphics();
		g.setColor(foregroundColor);
		for(int y = 0; y < qrcodeMatrix.length; y++){
			for(int x = 0; x < qrcodeMatrix.length; x++){
				if(qrcodeMatrix[x][y]){
					g.fillRect(x * sizePerUnit, y * sizePerUnit, sizePerUnit, sizePerUnit);
				}
			}
		}
		g.dispose();
		qrcodeImage.flush();
		return qrcodeImage;
	}
	
	/**
	 * TODO
	 *
	 * @param foregroundColor
	 * @param persentageOfCurevature
	 * @param sizePerUnit
	 * @return
	 * @author HomeletWei
	 */
	public BufferedImage draw_PainColor_Liquefied(Color foregroundColor, int persentageOfCurevature, int sizePerUnit){
		BufferedImage qrcodeImage = new BufferedImage(qrcodeDI.width * sizePerUnit, qrcodeDI.height * sizePerUnit, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D    g           = qrcodeImage.createGraphics();
		for(int y = 0; y < qrcodeMatrix.length; y++){
			for(int x = 0; x < qrcodeMatrix.length; x++){
				boolean[] buffer = new boolean[9];
				buffer[0] = ((y - 1) >= 0 && (x - 1) >= 0 && qrcodeMatrix[x - 1][y - 1]);
				buffer[1] = ((y - 1) >= 0 && qrcodeMatrix[x][y - 1]);
				buffer[2] = ((y - 1) >= 0 && (x + 1) < qrcodeMatrix.length && qrcodeMatrix[x + 1][y - 1]);
				buffer[3] = ((x - 1) >= 0 && qrcodeMatrix[x - 1][y]);
				buffer[4] = qrcodeMatrix[x][y];
				buffer[5] = ((x + 1) < qrcodeMatrix.length && qrcodeMatrix[x + 1][y]);
				buffer[6] = ((y + 1) < qrcodeMatrix.length && (x - 1) >= 0 && qrcodeMatrix[x - 1][y + 1]);
				buffer[7] = ((y + 1) < qrcodeMatrix.length && qrcodeMatrix[x][y + 1]);
				buffer[8] = ((y + 1) < qrcodeMatrix.length && (x + 1) < qrcodeMatrix.length && qrcodeMatrix[x + 1][y + 1]);
				boolean[]   inidvidalBuffer = shappingRoundEdge(buffer);
				boolean[][] inidvidalMatrix = ReverseArc.getReverseArc().getReverseArcMatrix(sizePerUnit, persentageOfCurevature, qrcodeMatrix[x][y], qrcodeMatrix[x][y], inidvidalBuffer[0], inidvidalBuffer[1], inidvidalBuffer[2], inidvidalBuffer[3]);
				g.setColor(foregroundColor);
				for(int innerY = 0; innerY < inidvidalMatrix.length; innerY++){
					int rY = y * inidvidalMatrix.length + innerY;
					for(int innerX = 0; innerX < inidvidalMatrix.length; innerX++){
						int rX = x * inidvidalMatrix.length + innerX;
						if(inidvidalMatrix[innerX][innerY]){
							g.fillRect(rX, rY, 1, 1);
						}
					}
				}
			}
		}
		g.dispose();
		qrcodeImage.flush();
		return qrcodeImage;
	}
	
	private boolean[] shappingRoundEdge(boolean[] buffer){
		boolean[] shape = new boolean[4];
		shape[0] = resolver(buffer, restant[0]);
		shape[1] = resolver(buffer, restant[1]);
		shape[2] = resolver(buffer, restant[2]);
		shape[3] = resolver(buffer, restant[3]);
		return shape;
	}
	
	private boolean resolver(boolean[] buffer, int[] restant){
		return (buffer[restant[0]] || buffer[restant[1]] || buffer[restant[2]])
				&& ((buffer[restant[0]] && buffer[restant[1]] && buffer[restant[2]])
				|| ((buffer[restant[0]] && buffer[restant[1]] && !buffer[restant[2]]) && buffer[4])
				|| ((buffer[restant[0]] && !buffer[restant[1]] && buffer[restant[2]]) && buffer[4])
				|| (!buffer[restant[0]] && buffer[restant[1]] && buffer[restant[2]])
				|| ((buffer[restant[0]] && !buffer[restant[1]] && !buffer[restant[2]]) && buffer[4])
				|| ((!buffer[restant[0]] && buffer[restant[1]] && !buffer[restant[2]]) && buffer[4])
				|| ((!buffer[restant[0]] && !buffer[restant[1]] && buffer[restant[2]]) && buffer[4]));
	}
	
	/**
	 * draw qrcode with the preset color matrix
	 *
	 * @param foregroundColorMatrix the color matrix
	 * @param sizePerUnit           size per unit qrcode
	 * @return a bufferedImage with the qrcode on it
	 * @author HomeletWei
	 */
	public BufferedImage draw_ColorMatrix_normal(int[][][] foregroundColorMatrix, int sizePerUnit){
		BufferedImage qrcodeImage = new BufferedImage(qrcodeDI.width * sizePerUnit, qrcodeDI.height * sizePerUnit, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D    g           = qrcodeImage.createGraphics();
		for(int y = 0; y < qrcodeMatrix.length; y++){
			for(int x = 0; x < qrcodeMatrix.length; x++){
				if(qrcodeMatrix[x][y]){
					for(int innerY = 0; innerY < sizePerUnit; innerY++){
						int rY = y * sizePerUnit + innerY;
						for(int innerX = 0; innerX < sizePerUnit; innerX++){
							int rX = x * sizePerUnit + innerX;
							g.setColor(new Color(foregroundColorMatrix[rX][rY][0], foregroundColorMatrix[rX][rY][1], foregroundColorMatrix[rX][rY][2], foregroundColorMatrix[rX][rY][3]));
							g.fillRect(rX, rY, 1, 1);
						}
					}
				}
			}
		}
		g.dispose();
		qrcodeImage.flush();
		return qrcodeImage;
	}
	
	/**
	 * to draw a liquefied qrode with the desired color matrix with it
	 * <hr>
	 * the algorithm of liquefied QRCode is as follow
	 *
	 * <pre>
	 * +-----+-----+-----+
	 * |     |     |     |
	 * |     |     |     |
	 * |    0|  1  |2    |
	 * +-----+-----+-----+
	 * |     |0   1|     |
	 * |    3|  4  |5    |
	 * |     |2   3|     |
	 * +-----+-----+-----+
	 * |    6|  7  |8    |
	 * |     |     |     |
	 * |     |     |     |
	 * +-----+-----+-----+
	 * </pre>
	 * <p>
	 * For example as of the top-right corner (0)
	 * <table>
	 * <thead align="center" vAlign="center" Bgcolor="#A5BDD6">
	 * <tr>
	 * <td>0(corner piece)</td>
	 * <td>1</td>
	 * <td>3</td>
	 * <td>resultant</td>
	 * </tr>
	 * </thead>
	 * <tbody align="center" vAlign="center" Bgcolor="#EEEEFF">
	 * <tr>
	 * <td>true</td>
	 * <td>true</td>
	 * <td>true</td>
	 * <td>always true</td>
	 * </tr>
	 * <tr>
	 * <td>true</td>
	 * <td>true</td>
	 * <td>false</td>
	 * <td>if center piece is true, then true, otherwise false</td>
	 * </tr>
	 * <tr>
	 * <td>true</td>
	 * <td>false</td>
	 * <td>true</td>
	 * <td>if center piece is true, then true, otherwise false</td>
	 * </tr>
	 * <tr>
	 * <td>false</td>
	 * <td>true</td>
	 * <td>true</td>
	 * <td>always true</td>
	 * </tr>
	 * <tr>
	 * <td>true</td>
	 * <td>false</td>
	 * <td>false</td>
	 * <td>if center piece is true, then true, otherwise false</td>
	 * </tr>
	 * <tr>
	 * <td>false</td>
	 * <td>true</td>
	 * <td>false</td>
	 * <td>if center piece is true, then true, otherwise false</td>
	 * </tr>
	 * <tr>
	 * <td>false</td>
	 * <td>false</td>
	 * <td>true</td>
	 * <td>if center piece is true, then true, otherwise false</td>
	 * </tr>
	 * <tr>
	 * <td>false</td>
	 * <td>false</td>
	 * <td>false</td>
	 * <td>always false</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 *
	 * @param foregroundColorMatrix the color matrix
	 * @param persentageOfCurvature the percentage of curvature for each individual
	 * @param sizePerUnit           the size per unit qrcode
	 * @return a bufferedImage with the QRCode on it
	 * @author HomeletWei
	 */
	public BufferedImage draw_ColorMatrix_Liquefied(int[][][] foregroundColorMatrix, int persentageOfCurvature, int sizePerUnit){
		BufferedImage qrcodeImage = new BufferedImage(qrcodeDI.width * sizePerUnit, qrcodeDI.height * sizePerUnit, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D    g           = qrcodeImage.createGraphics();
		for(int y = 0; y < qrcodeMatrix.length; y++){
			for(int x = 0; x < qrcodeMatrix.length; x++){
				boolean[] buffer = new boolean[9];
				buffer[0] = ((y - 1) >= 0 && (x - 1) >= 0 && qrcodeMatrix[x - 1][y - 1]);
				buffer[1] = ((y - 1) >= 0 && qrcodeMatrix[x][y - 1]);
				buffer[2] = ((y - 1) >= 0 && (x + 1) < qrcodeMatrix.length && qrcodeMatrix[x + 1][y - 1]);
				buffer[3] = ((x - 1) >= 0 && qrcodeMatrix[x - 1][y]);
				buffer[4] = qrcodeMatrix[x][y];
				buffer[5] = ((x + 1) < qrcodeMatrix.length && qrcodeMatrix[x + 1][y]);
				buffer[6] = ((y + 1) < qrcodeMatrix.length && (x - 1) >= 0 && qrcodeMatrix[x - 1][y + 1]);
				buffer[7] = ((y + 1) < qrcodeMatrix.length && qrcodeMatrix[x][y + 1]);
				buffer[8] = ((y + 1) < qrcodeMatrix.length && (x + 1) < qrcodeMatrix.length && qrcodeMatrix[x + 1][y + 1]);
				boolean[]   inidvidalBuffer = shappingRoundEdge(buffer);
				boolean[][] inidvidalMatrix = ReverseArc.getReverseArc().getReverseArcMatrix(sizePerUnit, persentageOfCurvature, qrcodeMatrix[x][y], qrcodeMatrix[x][y], inidvidalBuffer[0], inidvidalBuffer[1], inidvidalBuffer[2], inidvidalBuffer[3]);
				for(int innerY = 0; innerY < inidvidalMatrix.length; innerY++){
					int rY = y * inidvidalMatrix.length + innerY;
					for(int innerX = 0; innerX < inidvidalMatrix.length; innerX++){
						int rX = x * inidvidalMatrix.length + innerX;
						if(inidvidalMatrix[innerX][innerY]){
							g.setColor(new Color(foregroundColorMatrix[rX][rY][0], foregroundColorMatrix[rX][rY][1], foregroundColorMatrix[rX][rY][2], foregroundColorMatrix[rX][rY][3]));
							g.fillRect(rX, rY, 1, 1);
						}
					}
				}
			}
		}
		g.dispose();
		qrcodeImage.flush();
		return qrcodeImage;
	}
	
	/** @return qrcodeDI */
	public Dimension getQrcodeDI(int sizePerUnit){
		return new Dimension(qrcodeDI.width * sizePerUnit, qrcodeDI.height * sizePerUnit);
	}
}
