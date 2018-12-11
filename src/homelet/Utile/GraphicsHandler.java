/**
 * @author HomeletWei
 * @date Mar 29, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Utile;

import homelet.Swing.JDrawer.Render;
import homelet.Swing.JDrawer.RenderTarget;
import homelet.Utile.Constants.BorderStyle;
import homelet.Utile.Constants.GradientColorMapping;
import homelet.Utile.Math.HomeletMath;
import homelet.Utile.Math.Vector;
import homelet.Utile.Utility.Orientation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 29, 2018
 */
public class GraphicsHandler{
	
	private static GraphicsHandler graphicsHandler = new GraphicsHandler();
	
	private GraphicsHandler(){
	}
	
	public static GraphicsHandler getGraphicsHandler(){
		return graphicsHandler;
	}
	
	/**
	 * TODO
	 *
	 * @param g
	 * @param texture
	 * @param vertex
	 * @param size
	 * @author HomeletWei
	 */
	public void drawImage(Graphics2D g, Image texture, Point vertex, Dimension size){
		drawImage(g, texture, vertex.x, vertex.y, size.width, size.height);
	}
	
	/**
	 * TODO
	 *
	 * @param g
	 * @param texture
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 * @author HomeletWei
	 */
	public void drawImage(Graphics2D g, Image texture, int x, int y, int width, int height){
		g.drawImage(texture, x, y, width, height, null);
	}
	
	/**
	 * draw image within the given position and size
	 *
	 * @param g       the graphic object that needs to draw in
	 * @param texture the bufferedImage that is going to draw
	 * @param bound
	 * @author HomeletWei
	 */
	public void drawImage(Graphics2D g, Image texture, Rectangle bound){
		drawImage(g, texture, bound.x, bound.y, bound.width, bound.height);
	}
	
	/**
	 * draw string
	 *
	 * @param g      graphics object
	 * @param text   string that is going to be draw
	 * @param xPos   x-position
	 * @param yPos   y-position
	 * @param center center or not
	 * @param c      color
	 * @param font   font
	 * @author HomeletWei
	 */
	public void drawString(Graphics2D g, String text, int xPos, int yPos, boolean center, Color c, Font font){
		g.setColor(c);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int         x  = xPos;
		int         y  = yPos + fm.getHeight() - fm.getDescent();
		if(center){
			x = xPos - fm.stringWidth(text) / 2;
			y = (yPos - fm.getHeight() / 2) + fm.getAscent();
		}
		g.drawString(text, x, y);
	}
	
	/**
	 * draw a line
	 *
	 * @param g
	 * @param startX
	 * @param startY
	 * @param dropX
	 * @param dropY
	 * @author HomeletWei
	 */
	public void drawLine(Graphics g, int startX, int startY, int dropX, int dropY){
		g.drawLine(startX, startY, startX + dropX, startY + dropY);
	}
	
	/**
	 * draw a line
	 *
	 * @param g
	 * @param startX
	 * @param startY
	 * @param vector
	 * @author HomeletWei
	 */
	public void drawLine(Graphics g, int startX, int startY, Vector vector){
		drawLine(g, new Point(startX, startY), vector);
	}
	
	/**
	 * draw a line
	 *
	 * @param g
	 * @param start
	 * @param vector
	 * @author HomeletWei
	 */
	public void drawLine(Graphics g, Point start, Vector vector){
		drawLine(g, start, HomeletMath.getMath().getEndPoint(start, vector).toPoint());
	}
	
	/**
	 * draw a line
	 *
	 * @param g
	 * @param start
	 * @param end
	 * @author HomeletWei
	 */
	public void drawLine(Graphics g, Point start, Point end){
		g.drawLine(start.x, start.y, end.x, end.y);
	}
	
	public void fillRect(Graphics g, int startX, int startY, int endX, int endY){
		int sX = Math.min(startX, endX);
		int sY = Math.min(startY, endY);
		int eX = Math.max(startX, endX);
		int eY = Math.max(startY, endY);
		g.fillRect(sX, sY, eX - sX, eY - sY);
	}
	
	/**
	 * draw a bounds around
	 *
	 * @param g
	 * @param bound
	 * @param boundThinkness
	 * @param Style
	 * @param c
	 * @author HomeletWei
	 */
	public void drawBorder(Graphics2D g, Rectangle bound, int boundThinkness, BorderStyle Style, Color c){
		drawBorder(g, bound.getLocation(), bound.getSize(), boundThinkness, Style, c);
	}
	
	/**
	 * the algorithm is as follow
	 *
	 * <pre>
	 * +---------------------+
	 * |                     |
	 * +---------------------+
	 * +---+             +---+
	 * |   |             |   |
	 * |   |             |   |
	 * |   |             |   |
	 * |   |             |   |
	 * +---+             +---+
	 * +---------------------+
	 * |                     |
	 * +---------------------+
	 * </pre>
	 *
	 * @param g
	 * @param vertex
	 * @param size
	 * @param boundThinkness
	 * @param style
	 * @param c
	 * @author HomeletWei
	 */
	public void drawBorder(Graphics2D g, Point vertex, Dimension size, int boundThinkness, BorderStyle style, Color c){
		switch(style){
			case RECTANGULAR:
				drawBorder_Rectangular(g, vertex, size, boundThinkness, c);
				break;
			case DASHED:
				drawBorder_Dashed(g, vertex, size, boundThinkness, c, (int) BorderStyle.DASHED.getValues()[0]);
				break;
			case ROUND_RECTANGULAR:
				break;
			case DOTTED:
				drawBorder_Dotted(g, vertex, size, boundThinkness, c);
				break;
			case CONNERS:
				drawBorder_Conners(g, vertex, size, boundThinkness, c);
				break;
			default:
				break;
		}
	}
	
	private void drawBorder_Rectangular(Graphics2D g, Point vertex, Dimension size, int boundThinkness, Color c){
		g.setColor(c);
		g.fillRect(vertex.x, vertex.y, size.width, boundThinkness);
		g.fillRect(vertex.x, vertex.y + boundThinkness, boundThinkness, size.height - 2 * boundThinkness);
		g.fillRect(vertex.x + size.width - boundThinkness, vertex.y + boundThinkness, boundThinkness, size.height - 2 * boundThinkness);
		g.fillRect(vertex.x, vertex.y + size.height - boundThinkness, size.width, boundThinkness);
	}
	
	private void drawBorder_Dashed(Graphics2D g, Point vertex, Dimension size, int boundThinkness, Color c, int dashedInterval){
		g.setColor(c);
		{
			boolean was = false;
			for(int horizontal = vertex.x; horizontal + dashedInterval < vertex.x + size.width; horizontal += dashedInterval){
				if(!was){
					was = true;
					// top
					g.fillRect(horizontal, vertex.y, dashedInterval, boundThinkness);
					// bottom
					g.fillRect(horizontal, vertex.y + size.height - boundThinkness, dashedInterval, boundThinkness);
				}else{
					was = false;
				}
			}
		}
		{
			boolean was = false;
			for(int vertical = vertex.y; vertical + dashedInterval < vertex.y + size.height; vertical += dashedInterval){
				if(!was){
					was = true;
					// left
					g.fillRect(vertex.x, vertical, boundThinkness, dashedInterval);
					// right
					g.fillRect(vertex.x + size.width - boundThinkness, vertical, boundThinkness, dashedInterval);
				}else{
					was = false;
				}
			}
		}
	}
	
	private void drawBorder_Dotted(Graphics2D g, Point vertex, Dimension size, int boundThinkness, Color c){
		g.setColor(c);
		{
			boolean was = false;
			for(int horizontal = vertex.x; horizontal + boundThinkness < vertex.x + size.width; horizontal += boundThinkness){
				if(!was){
					was = true;
					// top
					g.fillRect(horizontal, vertex.y, boundThinkness, boundThinkness);
					// bottom
					g.fillRect(horizontal, vertex.y + size.height - boundThinkness, boundThinkness, boundThinkness);
				}else{
					was = false;
				}
			}
		}
		{
			boolean was = false;
			for(int vertical = vertex.y; vertical + boundThinkness < vertex.y + size.height; vertical += boundThinkness){
				if(!was){
					was = true;
					// left
					g.fillRect(vertex.x, vertical, boundThinkness, boundThinkness);
					// right
					g.fillRect(vertex.x + size.width - boundThinkness, vertical, boundThinkness, boundThinkness);
				}else{
					was = false;
				}
			}
		}
	}
	
	private void drawBorder_Conners(Graphics2D g, Point vertex, Dimension size, int boundThinkness, Color c){
		g.setColor(c);
		//top left
		g.fillRect(vertex.x, vertex.y, size.width / 3, boundThinkness);
		g.fillRect(vertex.x, vertex.y + boundThinkness, boundThinkness, size.height / 3);
		//top right
		g.fillRect(vertex.x + size.width - size.width / 3, vertex.y, size.width / 3, boundThinkness);
		g.fillRect(vertex.x + size.width - boundThinkness, vertex.y + boundThinkness, boundThinkness, size.height / 3);
		//bottom left
		g.fillRect(vertex.x, vertex.y + size.height - size.height / 3 - boundThinkness, boundThinkness, size.height / 3);
		g.fillRect(vertex.x, vertex.y + size.height - boundThinkness, size.width / 3, boundThinkness);
		//bottom right
		g.fillRect(vertex.x + size.width - boundThinkness, vertex.y + size.height - size.height / 3 - boundThinkness, boundThinkness, size.height / 3);
		g.fillRect(vertex.x + size.width - size.width / 3, vertex.y + size.height - boundThinkness, size.width / 3, boundThinkness);
	}
	
	public BufferedImage createRenderedImage(Dimension preferredDimension, Collection<Render> renderList){
		Dimension     dimension = Utility.getUtility().getFinalDimension(preferredDimension, renderList);
		BufferedImage bg        = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D    g         = bg.createGraphics();
		/////////////////////-draw Start-////////////////////
		doRenderProcess(g, renderList, dimension);
		/////////////////////-draw End-//////////////////////
		g.dispose();
		bg.flush();
		return bg;
	}
	
	public void doRenderProcess(Graphics2D g, Collection<Render> renderList, Dimension dimension){
		for(Render renderTarget : renderList){
			Dimension size   = null;
			Point     vertex = null;
			if(renderTarget instanceof RenderTarget){
				size = ((RenderTarget) renderTarget).getDimension();
				vertex = ((RenderTarget) renderTarget).getVertex(dimension, size);
			}
			if(size == null)
				size = dimension;
			if(vertex == null)
				vertex = new Point(0, 0);
			renderTarget.render((Graphics2D) g.create(vertex.x, vertex.y, size.width, size.height));
		}
	}
	
	/**
	 * Return a gradient color mapping for the color from and to, with the style applyed
	 *
	 * @param from
	 * @param to
	 * @param Style
	 * @param mappingDI
	 * @return a gradient color map
	 * @author HomeletWei
	 * @see #getGradiantMapping_BOTTOM_LEFT_TO_TOP_RIGHT(Color, Color, Dimension)
	 * @see #getGradiantMapping_INNER_TO_OUTER(Color, Color, Dimension)
	 * @see #getGradiantMapping_LEFT_TO_RIGHT(Color, Color, Dimension)
	 * @see #getGradiantMapping_TOP_LEFT_TO_BOTTOM_RIGHT(Color, Color, Dimension)
	 * @see #getGradiantMapping_TOP_TO_BOTTOM(Color, Color, Dimension)
	 */
	@SuppressWarnings("javadoc")
	public int[][][] getGradientColorMapping(Color from, Color to, GradientColorMapping Style, Dimension mappingDI){
		switch(Style){
			case LEFT_TO_RIGHT:
				return getGradiantMapping_LEFT_TO_RIGHT(from, to, mappingDI);
			case TOP_TO_BOTTOM:
				return getGradiantMapping_TOP_TO_BOTTOM(from, to, mappingDI);
			case TOP_LEFT_TO_BOTTOM_RIGHT:
				return getGradiantMapping_TOP_LEFT_TO_BOTTOM_RIGHT(from, to, mappingDI);
			case BOTTOM_LEFT_TO_TOP_RIGHT:
				return getGradiantMapping_BOTTOM_LEFT_TO_TOP_RIGHT(from, to, mappingDI);
			case INNER_TO_OUTER:
				return getGradiantMapping_INNER_TO_OUTER(from, to, mappingDI);
			default:
				return null;
		}
	}
	
	private int[][][] getGradiantMapping_LEFT_TO_RIGHT(Color from, Color to, Dimension mappingDI){
		int[][][] gradientColorMap     = new int[mappingDI.width][mappingDI.height][4];
		float     redIntervalPerUnit   = Integer.valueOf((from.getRed() - to.getRed())).floatValue() / mappingDI.width;
		float     greenIntervalPerUnit = Integer.valueOf((from.getGreen() - to.getGreen())).floatValue() / mappingDI.width;
		float     blueIntervalPerUnit  = Integer.valueOf((from.getBlue() - to.getBlue())).floatValue() / mappingDI.width;
		float     alphaIntervalPerUnit = Integer.valueOf((from.getAlpha() - to.getAlpha())).floatValue() / mappingDI.width;
		for(int y = 0; y < mappingDI.height; y++){
			for(int x = 0; x < mappingDI.width; x++){
				gradientColorMap[x][y][0] = (int) (from.getRed() - redIntervalPerUnit * x);
				gradientColorMap[x][y][1] = (int) (from.getGreen() - greenIntervalPerUnit * x);
				gradientColorMap[x][y][2] = (int) (from.getBlue() - blueIntervalPerUnit * x);
				gradientColorMap[x][y][3] = (int) (from.getAlpha() - alphaIntervalPerUnit * x);
				//		System.out.print(Utility.padString(String.valueOf(gradientColorMap[x][y][0]), 3, ' ') + "/" + Utility.padString(String.valueOf(gradientColorMap[x][y][1]), 3, ' ') + "/"
				//		                 + Utility.padString(String.valueOf(gradientColorMap[x][y][2]), 3, ' ') + " ");
			}
			//	    System.out.println();
		}
		return gradientColorMap;
	}
	
	private int[][][] getGradiantMapping_TOP_TO_BOTTOM(Color from, Color to, Dimension mappingDI){
		int[][][] gradientColorMap     = new int[mappingDI.width][mappingDI.height][4];
		float     redIntervalPerUnit   = Integer.valueOf((from.getRed() - to.getRed())).floatValue() / mappingDI.height;
		float     greenIntervalPerUnit = Integer.valueOf((from.getGreen() - to.getGreen())).floatValue() / mappingDI.height;
		float     blueIntervalPerUnit  = Integer.valueOf((from.getBlue() - to.getBlue())).floatValue() / mappingDI.height;
		float     alphaIntervalPerUnit = Integer.valueOf((from.getAlpha() - to.getAlpha())).floatValue() / mappingDI.height;
		for(int y = 0; y < mappingDI.height; y++){
			for(int x = 0; x < mappingDI.width; x++){
				gradientColorMap[x][y][0] = (int) (from.getRed() - redIntervalPerUnit * y);
				gradientColorMap[x][y][1] = (int) (from.getGreen() - greenIntervalPerUnit * y);
				gradientColorMap[x][y][2] = (int) (from.getBlue() - blueIntervalPerUnit * y);
				gradientColorMap[x][y][3] = (int) (from.getAlpha() - alphaIntervalPerUnit * y);
				//		System.out.print(Utility.padString(String.valueOf(gradientColorMap[x][y][0]), 3, ' ') + "/" + Utility.padString(String.valueOf(gradientColorMap[x][y][1]), 3, ' ') + "/"
				//		                 + Utility.padString(String.valueOf(gradientColorMap[x][y][2]), 3, ' ') + " ");
			}
			//	    System.out.println();
		}
		return gradientColorMap;
	}
	
	private int[][][] getGradiantMapping_TOP_LEFT_TO_BOTTOM_RIGHT(Color from, Color to, Dimension mappingDI){
		int[][][] gradientColorMap     = new int[mappingDI.width][mappingDI.height][4];
		float     redIntervalPerUnit   = Integer.valueOf((from.getRed() - to.getRed())).floatValue() / (mappingDI.width + mappingDI.height);
		float     greenIntervalPerUnit = Integer.valueOf((from.getGreen() - to.getGreen())).floatValue() / (mappingDI.width + mappingDI.height);
		float     blueIntervalPerUnit  = Integer.valueOf((from.getBlue() - to.getBlue())).floatValue() / (mappingDI.width + mappingDI.height);
		float     alphaIntervalPerUnit = Integer.valueOf((from.getAlpha() - to.getAlpha())).floatValue() / (mappingDI.width + mappingDI.height);
		for(int y = 0; y < mappingDI.height; y++){
			for(int x = 0; x < mappingDI.width; x++){
				gradientColorMap[x][y][0] = (int) (from.getRed() - redIntervalPerUnit * (x + y));
				gradientColorMap[x][y][1] = (int) (from.getGreen() - greenIntervalPerUnit * (x + y));
				gradientColorMap[x][y][2] = (int) (from.getBlue() - blueIntervalPerUnit * (x + y));
				gradientColorMap[x][y][3] = (int) (from.getAlpha() - alphaIntervalPerUnit * (x + y));
				//		System.out.print(Utility.padString(String.valueOf(gradientColorMap[x][y][0]), 3, ' ') + "/" + Utility.padString(String.valueOf(gradientColorMap[x][y][1]), 3, ' ') + "/"
				//		                 + Utility.padString(String.valueOf(gradientColorMap[x][y][2]), 3, ' ') + " ");
			}
			//	    System.out.println();
		}
		return gradientColorMap;
	}
	
	private int[][][] getGradiantMapping_BOTTOM_LEFT_TO_TOP_RIGHT(Color from, Color to, Dimension mappingDI){
		int[][][] gradientColorMap     = new int[mappingDI.width][mappingDI.height][4];
		float     redIntervalPerUnit   = Integer.valueOf((from.getRed() - to.getRed())).floatValue() / (mappingDI.width + mappingDI.height);
		float     greenIntervalPerUnit = Integer.valueOf((from.getGreen() - to.getGreen())).floatValue() / (mappingDI.width + mappingDI.height);
		float     blueIntervalPerUnit  = Integer.valueOf((from.getBlue() - to.getBlue())).floatValue() / (mappingDI.width + mappingDI.height);
		float     alphaIntervalPerUnit = Integer.valueOf((from.getAlpha() - to.getAlpha())).floatValue() / (mappingDI.width + mappingDI.height);
		for(int y = 0; y < mappingDI.height; y++){
			for(int x = 0; x < mappingDI.width; x++){
				gradientColorMap[x][y][0] = (int) (from.getRed() - redIntervalPerUnit * (x + getReletiveY(y, mappingDI.height)));
				gradientColorMap[x][y][1] = (int) (from.getGreen() - greenIntervalPerUnit * (x + getReletiveY(y, mappingDI.height)));
				gradientColorMap[x][y][2] = (int) (from.getBlue() - blueIntervalPerUnit * (x + getReletiveY(y, mappingDI.height)));
				gradientColorMap[x][y][3] = (int) (from.getAlpha() - alphaIntervalPerUnit * (x + getReletiveY(y, mappingDI.height)));
				//		System.out.print(Utility.padString(String.valueOf(gradientColorMap[x][y][0]), 3, ' ') + "/" + Utility.padString(String.valueOf(gradientColorMap[x][y][1]), 3, ' ') + "/"
				//		                 + Utility.padString(String.valueOf(gradientColorMap[x][y][2]), 3, ' ') + " ");
			}
			//	    System.out.println();
		}
		return gradientColorMap;
	}
	
	private int[][][] getGradiantMapping_INNER_TO_OUTER(Color from, Color to, Dimension mappingDI){
		int[][][]      gradientColorMap     = new int[mappingDI.width][mappingDI.height][4];
		MatrixResolver resover              = new MatrixResolver(mappingDI);
		float          redIntervalPerUnit   = Integer.valueOf((from.getRed() - to.getRed())).floatValue() / resover.totalRevolution;
		float          greenIntervalPerUnit = Integer.valueOf((from.getGreen() - to.getGreen())).floatValue() / resover.totalRevolution;
		float          blueIntervalPerUnit  = Integer.valueOf((from.getBlue() - to.getBlue())).floatValue() / resover.totalRevolution;
		float          alphaIntervalPerUnit = Integer.valueOf((from.getAlpha() - to.getAlpha())).floatValue() / resover.totalRevolution;
		for(int y = 0; y < mappingDI.height; y++){
			for(int x = 0; x < mappingDI.width; x++){
				int revolution = resover.getRevolution(x, y);
				gradientColorMap[x][y][0] = (int) (from.getRed() - redIntervalPerUnit * revolution);
				gradientColorMap[x][y][1] = (int) (from.getGreen() - greenIntervalPerUnit * revolution);
				gradientColorMap[x][y][2] = (int) (from.getBlue() - blueIntervalPerUnit * revolution);
				gradientColorMap[x][y][3] = (int) (from.getAlpha() - alphaIntervalPerUnit * revolution);
				//		System.out.print(Utility.padString(String.valueOf(gradientColorMap[x][y][0]), 3, ' ') + "/" + Utility.padString(String.valueOf(gradientColorMap[x][y][1]), 3, ' ') + "/"
				//	                 + Utility.padString(String.valueOf(gradientColorMap[x][y][2]), 3, ' ') + " ");
			}
			//	    System.out.println();
		}
		return gradientColorMap;
	}
	
	private int getReletiveY(int y, int maxY){
		return (maxY % 2) != 0 ? (maxY - y) : (y == (maxY / 2)) ? (y) : (maxY - y);
	}
	
	public BufferedImage createFillImage(Coloring coloring, Dimension dimension){
		switch(coloring.getMode()){
			case COLOR_MAP:
				return createGradientFillImage(coloring.getColor(0), coloring.getColor(1), coloring.getGradientColorMappingMode(), dimension);
			case PAIN_COLOR:
				return createPainFillImage(coloring.getColor(), dimension);
			case NULL:
		}
		return null;
	}
	
	private BufferedImage createPainFillImage(Color color, Dimension mappingDI){
		BufferedImage image = new BufferedImage(mappingDI.width, mappingDI.height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D    g     = image.createGraphics();
		g.setColor(color);
		g.fillRect(0, 0, mappingDI.width, mappingDI.height);
		g.dispose();
		image.flush();
		return image;
	}
	
	private BufferedImage createGradientFillImage(Color from, Color to, GradientColorMapping Style, Dimension mappingDI){
		int[][][]     mapping = getGradientColorMapping(from, to, Style, mappingDI);
		BufferedImage image   = new BufferedImage(mappingDI.width, mappingDI.height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D    g       = image.createGraphics();
		for(int y = 0; y < mappingDI.height; y++){
			for(int x = 0; x < mappingDI.width; x++){
				g.setColor(new Color(mapping[x][y][0], mapping[x][y][1], mapping[x][y][2], mapping[x][y][3]));
				g.fillRect(x, y, 1, 1);
			}
		}
		g.dispose();
		image.flush();
		return image;
	}
	
	class MatrixResolver{
		
		int         width;
		int         height;
		Orientation orientation;
		int         totalRevolution;
		int         padder;
		
		public MatrixResolver(Dimension mappingDI){
			width = mappingDI.width - 1;
			height = mappingDI.height - 1;
			orientation = Utility.getUtility().getOrientation(mappingDI);
			totalRevolution = (Math.min(width, height)) / 2;
			padder = Math.abs(width - height);
		}
		
		int getRevolution(int x, int y){
			int checkX    = checkX(x);
			int checkY    = checkY(y);
			int reletiveX = getReletive(x, checkX, width);
			int reletiveY = getReletive(y, checkY, height);
			if(checkX < 0 || checkX > 0){
				if(checkY < 0 || checkY > 0){
					return Math.max(reletiveX, reletiveY);
				}
				return reletiveY;
			}
			if(checkY < 0 || checkY > 0){
				return reletiveY;
			}
			return 0;
		}
		
		int checkX(int x){
			return Utility.getUtility().betweenPeaks(x, rightBound(width), leftBound());
		}
		
		int checkY(int y){
			return Utility.getUtility().between(y, rightBound(height), leftBound());
		}
		
		int getReletive(int value, int check, int side){
			if(check < 0){
				return leftBound() - value;
			}else if(check > 0){
				return value - rightBound(side);
			}else{
				return 0;
			}
		}
		
		int rightBound(int factor){
			return factor - totalRevolution;
		}
		
		int leftBound(){
			return totalRevolution;
		}
	}
}
