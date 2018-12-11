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
package homelet.Swing.JBasic;

import homelet.Swing.ChangeActionListener;
import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Coloring;
import homelet.Utile.Constants.GradientColorMapping;
import homelet.Utile.GraphicsHandler;
import homelet.Utile.ReverseArc;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * the label for displaying a color or color map
 *
 * @author HomeletWei
 * @date Mar 29, 2018
 */
public class JColorLabel extends JComponent implements RestoreDefault, EnableListener{
	
	/** @Fields <b>serialVersionUID</b> */
	private static final long      serialVersionUID = 1L;
	private static       int       defaultCurvature = 20;
	///Util///
	private              Color     defaultColor;
	///////
	private              Coloring  fillColor;
	private              int[][][] gradientMap;
	
	/**
	 * constructor for JColorLabel<br>
	 * default Color can only be a pain color
	 *
	 * @param c
	 * @param represent
	 * @author HomeletWei
	 */
	public JColorLabel(Color c, String represent){
		defaultColor = c;
		this.fillColor = new Coloring(c);
		this.addMouseListener(new MouseAdapter(){
			/**
			 * @param e
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2 && JColorLabel.this.isEnabled()){
					switch(fillColor.getMode()){
						case COLOR_MAP:{
							String selection = (String) JOptionPane.showInputDialog(JColorLabel.this, "Please Choose How The Gradient Color Distribute:", "Choose Gradient Color Distribution", JOptionPane.PLAIN_MESSAGE, null, GradientColorMapping.colorMappingMode, fillColor.getGradientColorMappingMode().getValue());
							if(selection == null)
								return;
							GradientColorMapping mode = GradientColorMapping.colorMapping[Utility.getUtility().indexOf(GradientColorMapping.colorMappingMode, selection)];
							Color                from = show(represent + " - Start Color", fillColor.getColor(0));
							if(from == null)
								return;
							Color to = show(represent + " - End Color", fillColor.getColor(1));
							if(to == null)
								return;
							setFillColor(mode, from, to);
						}
						break;
						case PAIN_COLOR:{
							Color color = show(represent, fillColor.getColor(0));
							if(color == null)
								return;
							setFillColor(color);
						}
						break;
						case NULL:{
							Color color = show(represent, defaultColor);
							if(color == null)
								return;
							setFillColor(color);
						}
						break;
						default:
							break;
					}
				}
				onChangeActionInvoke();
			}
			
			Color show(String title, Color preColor){
				String text           = "Please Input With the Following Syntax:\nHEX :\n\t\t#FFFFFF\nRGB or ARGB :\n\t\trgb(red, green, blue) \n\t\trgb(red, green, blue, alpha) \n\t\targb(red, green, blue, alpha)\nExamples :\n1. #FFaa00\n2. Rgb(10,10,200)\n3. rgb(100,200,100,255)\n4. ARGB(200,100,200,255)";
				String preColorString = "rgb(" + preColor.getRed() + "," + preColor.getGreen() + "," + preColor.getBlue() + (preColor.getAlpha() == 255 ? "" : "," + preColor.getAlpha()) + ")";
				String input          = (String) JOptionPane.showInputDialog(JColorLabel.this, text, title, JOptionPane.PLAIN_MESSAGE, null, null, preColorString);
				return input == null ? null : Utility.getUtility().translateColor(input);
			}
		});
		this.setIgnoreRepaint(true);
	}
	
	/**
	 * @param mode
	 * @param colors
	 * @author HomeletWei
	 */
	public void setFillColor(GradientColorMapping mode, Color... colors){
		this.fillColor.setColors(colors);
		this.fillColor.setGradientColorMappingMode(mode);
		this.gradientMap = GraphicsHandler.getGraphicsHandler().getGradientColorMapping(fillColor.getColor(0), fillColor.getColor(1), mode, new Dimension(this.getWidth(), this.getHeight()));
		this.repaint();
	}
	
	/**
	 * @param c
	 * @author HomeletWei
	 */
	public void setFillColor(Color c){
		if(c == null){
			removeFillColor();
			return;
		}
		this.fillColor.setColor(c);
		this.repaint();
	}
	
	/**
	 * remove the fill Color
	 *
	 * @author HomeletWei
	 */
	public void removeFillColor(){
		this.fillColor.clearColors();
		this.repaint();
	}
	
	@Override
	public void restoreDefault(){
		setFillColor(defaultColor);
	}
	
	/**
	 * @param g
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		switch(fillColor.getMode()){
			case NULL:
				if(!this.isEnabled()){
					g2.setColor(Utility.disableColor);
				}else{
					g2.setColor(Color.BLACK);
				}
				g2.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), defaultCurvature, defaultCurvature);
				break;
			case PAIN_COLOR:
				if(!this.isEnabled()){
					g2.setColor(Utility.disableColor);
				}else{
					g2.setColor(fillColor.getColor());
				}
				g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), defaultCurvature, defaultCurvature);
				break;
			case COLOR_MAP:
				boolean[][] topLeft = ReverseArc.getReverseArc().getReverseArcMatrix(defaultCurvature, 100, true, true, false, true, true, true);
				boolean[][] topRight = ReverseArc.getReverseArc().getReverseArcMatrix(defaultCurvature, 100, true, true, true, false, true, true);
				boolean[][] bottomLeft = ReverseArc.getReverseArc().getReverseArcMatrix(defaultCurvature, 100, true, true, true, true, false, true);
				boolean[][] bottomRight = ReverseArc.getReverseArc().getReverseArcMatrix(defaultCurvature, 100, true, true, true, true, true, false);
				int width = this.getWidth();
				int height = this.getHeight();
				for(int y = 0; y < height; y++){
					for(int x = 0; x < width; x++){
						if(x < defaultCurvature && y < defaultCurvature){
							int xOff = 0;
							int yOff = 0;
							if(!topLeft[x - xOff][y - yOff])
								continue;
						}else if(x >= width - defaultCurvature && y < defaultCurvature){
							int xOff = width - defaultCurvature;
							int yOff = 0;
							if(!topRight[x - xOff][y - yOff])
								continue;
						}else if(x < defaultCurvature && y >= height - defaultCurvature){
							int xOff = 0;
							int yOff = height - defaultCurvature;
							if(!bottomLeft[x - xOff][y - yOff])
								continue;
						}else if(x >= width - defaultCurvature && y >= height - defaultCurvature){
							int xOff = width - defaultCurvature;
							int yOff = height - defaultCurvature;
							if(!bottomRight[x - xOff][y - yOff])
								continue;
						}
						if(!this.isEnabled()){
							g2.setColor(Utility.disableColor);
						}else{
							g.setColor(new Color(gradientMap[x][y][0], gradientMap[x][y][1], gradientMap[x][y][2], gradientMap[x][y][3]));
						}
						g.fillRect(x, y, 1, 1);
					}
				}
				break;
			default:
				break;
		}
	}
	
	/**
	 * @return the fill Color
	 * @author HomeletWei
	 */
	public Coloring getColoring(){
		return fillColor;
	}
	
	/**
	 * @param newEnable
	 * @see homelet.Swing.EnableListener#onEnableChange(boolean)
	 */
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
	
	private ArrayList<ChangeActionListener> changeActionListeners = new ArrayList<>();
	
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		this.changeActionListeners.add(changeActionListener);
	}
	
	protected void onChangeActionInvoke(){
		for(ChangeActionListener c : changeActionListeners){
			if(c != null)
				c.onChange();
		}
	}
}
