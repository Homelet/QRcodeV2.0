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

import homelet.Utile.GraphicsHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 25, 2018
 */
public enum ImageResizeMode{
	/** @Fields <b>SCALE_TO_FIT</b> resize to DI by keeping the shortest side and resize the other by ratio to fit the desired DI, this may result in some blank spaceing */
	SCALE_TO_FIT,
	/** @Fields <b>SCALE_TO_FILL</b> resize to DI by keeping the longest side and resize the other by ratio to fit the desired DI, this may result in cut off some of the part */
	SCALE_TO_FILL,
	/** @Fields <b>STRETCH_TO_FILL</b> resize to DI by stretching both size of the image to be the desired DI */
	STRETCH_TO_FILL,
	/** @Fields <b>DO_NOT_RESIZE</b> do not do any resize */
	DO_NOT_RESIZE;
	public static final String[]          imageResizeMode = { "Scale To Fit", "Scale To Fill", "Stretch To Fill", "Do Not Resize" };
	public static final ImageResizeMode[] resizeMode      = new ImageResizeMode[]{ SCALE_TO_FIT, SCALE_TO_FILL, STRETCH_TO_FILL, DO_NOT_RESIZE };
	public static       int               defaultValue    = 0;
	
	/**
	 * get the image
	 *
	 * @param image
	 * @param containerDI
	 * @return a bufferedImage
	 * @author HomeletWei
	 */
	public BufferedImage getResizedImage(BufferedImage image, Dimension containerDI){
		switch(this){
			case SCALE_TO_FIT:
				return scaleToFit(image, containerDI);
			case SCALE_TO_FILL:
				return scaleToFill(image, containerDI);
			case STRETCH_TO_FILL:
				return stretchToFill(image, containerDI);
			case DO_NOT_RESIZE://$FALL-THROUGH$
			default:
				return image;
		}
	}
	
	private static BufferedImage scaleToFit(BufferedImage image, Dimension containerDI){
		// because scaling to fit result in the long side to be kept,
		// but the short side needs to be resize
		int width  = image.getWidth();
		int height = image.getHeight();
		if(width > height){
			float scaleFactor = Integer.valueOf(containerDI.width).floatValue() / width;
			width = containerDI.width;
			height = (int) (height * scaleFactor);
		}else{
			float scaleFactor = Integer.valueOf(containerDI.height).floatValue() / height;
			height = containerDI.height;
			width = (int) (width * scaleFactor);
		}
		Dimension     finalDI    = new Dimension(width, height);
		BufferedImage finalImage = new BufferedImage(containerDI.width, containerDI.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D    g          = finalImage.createGraphics();
		GraphicsHandler.getGraphicsHandler().drawImage(g, image.getScaledInstance(finalDI.width, finalDI.height, Image.SCALE_SMOOTH), Alignment.getDesiredVertex(Alignment.CENTER, new Point(0, 0), containerDI, null, finalDI), finalDI);
		g.dispose();
		finalImage.flush();
		return finalImage;
	}
	
	private static BufferedImage scaleToFill(BufferedImage image, Dimension containerDI){
		// because scaling to fill result in the short side to be kept,
		// but the long side needs to be resized
		int width  = image.getWidth();
		int height = image.getHeight();
		if(width < height){
			float scaleFactor = Integer.valueOf(containerDI.width).floatValue() / width;
			width = containerDI.width;
			height = (int) (height * scaleFactor);
		}else{
			float scaleFactor = Integer.valueOf(containerDI.height).floatValue() / height;
			height = containerDI.height;
			width = (int) (width * scaleFactor);
		}
		Dimension     finalDI    = new Dimension(width, height);
		BufferedImage finalImage = new BufferedImage(containerDI.width, containerDI.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D    g          = finalImage.createGraphics();
		GraphicsHandler.getGraphicsHandler().drawImage(g, image.getScaledInstance(finalDI.width, finalDI.height, Image.SCALE_SMOOTH), Alignment.getDesiredVertex(Alignment.CENTER, new Point(0, 0), containerDI, null, finalDI), finalDI);
		g.dispose();
		finalImage.flush();
		return finalImage;
	}
	
	private static BufferedImage stretchToFill(BufferedImage image, Dimension containerDI){
		BufferedImage finalImage = new BufferedImage(containerDI.width, containerDI.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D    g          = finalImage.createGraphics();
		GraphicsHandler.getGraphicsHandler().drawImage(g, image.getScaledInstance(containerDI.width, containerDI.height, Image.SCALE_SMOOTH), new Point(0, 0), containerDI);
		g.dispose();
		finalImage.flush();
		return finalImage;
	}
}
