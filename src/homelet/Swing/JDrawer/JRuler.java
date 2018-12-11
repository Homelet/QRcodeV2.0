/**
 * <pre>
 * ****************************************************
 * Name: TODO
 * Date: TODO
 * Description: TODO
 * *****************************************************
 * </pre>
 *
 * @author HomeletWei
 * @date Jun 11, 2018
 */
package homelet.Swing.JDrawer;

import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.GraphicsHandler;
import homelet.Utile.Math.HomeletMath;
import homelet.Utile.StringDrawer.StringDrawer;
import homelet.Utile.StringDrawer.StringDrawer.LinePolicy;
import homelet.Utile.StringDrawer.StringDrawerException;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Jun 11, 2018
 */
public class JRuler extends JComponent implements RestoreDefault, EnableListener{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long              serialVersionUID = 1L;
	private static final float             LONG             = 0.8f;
	private static final float             MIDIUM           = 0.3f;
	private static final float             SHORT            = 0.1f;
	private              UnitsOfMesurement unitOfMesureMent;
	private              RulerApperance    apperance;
	private              int               startPosition    = -1, endPosition = -1;
	private Color        drawColor = new Color(102, 102, 102, 55);
	private StringDrawer drawer;
	
	/**
	 * constructor for JRuler
	 *
	 * @param uom
	 * @param apperance
	 * @author HomeletWei
	 */
	public JRuler(UnitsOfMesurement uom, RulerApperance apperance){
		this.unitOfMesureMent = uom;
		this.apperance = apperance;
		drawer = new StringDrawer();
		drawer.setMinimumTextWidth(StringDrawer.FRAME_WIDTH);
		drawer.setTextAlign(Alignment.TOP);
		drawer.setLinePolicy(LinePolicy.NEVER_BREAK);
	}
	
	@Override
	public void paint(Graphics g){
		if(this.isEnabled())
			g.setColor(Color.BLACK);
		else
			g.setColor(Utility.disableColor);
		int lessDominate = Utility.getUtility().getLessDominate(this);
		int longValue    = (int) (lessDominate * LONG);
		int midValue     = (int) (lessDominate * MIDIUM);
		int shortValue   = (int) (lessDominate * SHORT);
		int count        = 0;
		switch(Utility.getUtility().getOrientation(this.getSize())){
			case HORIZONTAL:
				switch(apperance){
					case OUTER:
						for(float length = 0; length < Utility.getUtility().getDominate(this); length += unitOfMesureMent.subRatio){
							if(count % 10 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, Math.round(length), 0, 0, longValue);
							}else if(count % 5 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, Math.round(length), 0, 0, midValue);
							}else{
								GraphicsHandler.getGraphicsHandler().drawLine(g, Math.round(length), 0, 0, shortValue);
							}
							count++;
						}
						break;
					case INNER:
						for(float length = 0; length < Utility.getUtility().getDominate(this); length += unitOfMesureMent.subRatio){
							if(count % 10 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, Math.round(length), this.getHeight(), 0, -longValue);
							}else if(count % 5 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, Math.round(length), this.getHeight(), 0, -midValue);
							}else{
								GraphicsHandler.getGraphicsHandler().drawLine(g, Math.round(length), this.getHeight(), 0, -shortValue);
							}
							count++;
						}
						break;
					default:
				}
			{
				g.setColor(drawColor);
				int start = Math.min(startPosition, endPosition);
				int end   = Math.max(startPosition, endPosition);
				if(start != -1)
					GraphicsHandler.getGraphicsHandler().drawLine(g, start, 0, 0, this.getHeight());
				if(end != -1)
					GraphicsHandler.getGraphicsHandler().drawLine(g, end, 0, 0, this.getHeight());
				if(start != -1 && end != -1){
					Rectangle drawRect = new Rectangle(start, 0, end - start, this.getHeight());
					((Graphics2D) g).fill(drawRect);
					try{
						drawer.updateGraphics(g);
						drawer.initializeContents(getMesures(start, end));
						drawer.setFrame(drawRect);
						drawer.validate();
						drawer.draw();
					}catch(StringDrawerException e){
						e.printStackTrace();
					}
				}
			}
			break;
			default:
			case EQUILATERAL:
			case VERTICAL:
				switch(apperance){
					case OUTER:
						for(float length = 0; length < Utility.getUtility().getDominate(this); length += unitOfMesureMent.subRatio){
							if(count % 10 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, 0, Math.round(length), longValue, 0);
							}else if(count % 5 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, 0, Math.round(length), midValue, 0);
							}else{
								GraphicsHandler.getGraphicsHandler().drawLine(g, 0, Math.round(length), shortValue, 0);
							}
							count++;
						}
						break;
					case INNER:
						for(float length = 0; length < Utility.getUtility().getDominate(this); length += unitOfMesureMent.subRatio){
							if(count % 10 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, this.getWidth(), Math.round(length), -longValue, 0);
							}else if(count % 5 == 0){
								GraphicsHandler.getGraphicsHandler().drawLine(g, this.getWidth(), Math.round(length), -midValue, 0);
							}else{
								GraphicsHandler.getGraphicsHandler().drawLine(g, this.getWidth(), Math.round(length), -shortValue, 0);
							}
							count++;
						}
						break;
					default:
				}
			{
				g.setColor(drawColor);
				int start = Math.min(startPosition, endPosition);
				int end   = Math.max(startPosition, endPosition);
				if(start != -1)
					GraphicsHandler.getGraphicsHandler().drawLine(g, 0, start, this.getWidth(), 0);
				if(end != -1)
					GraphicsHandler.getGraphicsHandler().drawLine(g, 0, end, this.getWidth(), 0);
				if(start != -1 && end != -1){
					Rectangle drawRect = new Rectangle(0, start, this.getWidth(), end - start);
					((Graphics2D) g).fill(drawRect);
					try{
						drawer.updateGraphics(g);
						drawer.initializeContents(getMesures(start, end));
						drawer.setFrame(drawRect);
						drawer.validate();
						drawer.draw();
					}catch(StringDrawerException e){
						e.printStackTrace();
					}
				}
			}
			break;
		}
	}
	
	private String getMesures(int start, int end){
		UnitsOfMesurement curret  = this.getUnitOfMesurement();
		StringBuilder     builder = new StringBuilder();
		float             value   = (Integer.valueOf(end).floatValue() - Integer.valueOf(start).floatValue()) / curret.ratioToPixal * curret.valuePerUnit;
		builder.append(HomeletMath.getMath().percision(value, 2));
		builder.append(" ");
		builder.append(curret.unit);
		return builder.toString();
	}
	
	/**
	 * @return unit of mesuremnts
	 * @author HomeletWei
	 */
	public UnitsOfMesurement getUnitOfMesurement(){
		return unitOfMesureMent;
	}
	
	/**
	 * @author HomeletWei
	 */
	public void nextMesurement(){
		switch(unitOfMesureMent){
			case CENTIMETER:
				this.unitOfMesureMent = UnitsOfMesurement.INCH;
				break;
			case INCH:
				this.unitOfMesureMent = UnitsOfMesurement.PXIAL;
				break;
			case PXIAL:
				this.unitOfMesureMent = UnitsOfMesurement.CENTIMETER;
				break;
			default:
				break;
		}
		this.repaint();
	}
	
	/** @return startPosition */
	public int getStartPosition(){
		return startPosition;
	}
	
	/** @param startPosition : startPosition */
	public void setStartPosition(int startPosition){
		this.startPosition = startPosition;
		this.repaint();
	}
	
	/** @return endPosition */
	public int getEndPosition(){
		return endPosition;
	}
	
	/** @param endPosition : endPosition */
	public void setEndPosition(int endPosition){
		this.endPosition = endPosition;
		this.repaint();
	}
	
	/**
	 * TODO
	 *
	 * @param start
	 * @param end
	 * @author HomeletWei
	 */
	public void setRegion(int start, int end){
		this.startPosition = start;
		this.endPosition = end;
		this.repaint();
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
	
	@Override
	public void restoreDefault(){
	}
	
	/**
	 * @author HomeletWei
	 * @date Jun 11, 2018
	 */
	public enum UnitsOfMesurement{
		/** @Fields <b>CENTIMETER</b> */
		CENTIMETER("cm", 37.795276f, 3.7795276f, 1),
		/** @Fields <b>INCH</b> */
		INCH("inch", 96.0f, 9.60f, 1),
		/** @Fields <b>PXIAL</b> */
		PXIAL("px", 50.0f, 5.0f, 50);
		String unit;
		float  ratioToPixal;
		float  subRatio;
		int    valuePerUnit;
		
		private UnitsOfMesurement(String unit, float ratioToPixal, float subRatio, int valuePerUnit){
			this.unit = unit;
			this.ratioToPixal = ratioToPixal;
			this.subRatio = subRatio;
			this.valuePerUnit = valuePerUnit;
		}
	}
	
	/**
	 * @author HomeletWei
	 * @date Jun 11, 2018
	 */
	public enum RulerApperance{
		/** @Fields <b>OUTER</b> */
		OUTER,
		/** @Fields <b>INNER</b> */
		INNER;
	}
}
