/**
 * @author HomeletWei
 * @date Mar 30, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JComplex;

import homelet.Swing.ChangeActionListener;
import homelet.Swing.JBasic.JColorLabel;
import homelet.Swing.JBasic.JRemoveButton;
import homelet.Swing.JBasic.JTextButton;
import homelet.Utile.Coloring;
import homelet.Utile.Constants.GradientColorMapping;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 30, 2018
 */
public class JComplexColorChooser extends JComplex{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	JColorLabel colorLabel;
	JTextButton actionButton;
	
	/**
	 * constructor for JComplexColorChooser
	 *
	 * @param intalColor
	 * @param removeAble
	 * @param canGradient
	 * @param repsent
	 * @author HomeletWei
	 */
	public JComplexColorChooser(Color intalColor, boolean removeAble, boolean canGradient, String repsent){
		this.colorLabel = new JColorLabel(intalColor, repsent);
		this.actionButton = new JTextButton("Choose ".concat(repsent).concat("..."));
		addRestoreDefaultChild(colorLabel);
		addRestoreDefaultChild(actionButton);
		addEnableChild(colorLabel, true);
		addEnableChild(actionButton, true);
		actionButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(canGradient){
					String[] select    = new String[]{ "Pain Color", "Gradient Color" };
					String   selection = (String) JOptionPane.showInputDialog(JComplexColorChooser.this, "Please Select A Color Mode:", "Choose Color Mode", JOptionPane.PLAIN_MESSAGE, null, select, select[0]);
					if(selection == null)
						return;
					switch(Utility.getUtility().indexOf(select, selection)){
						default:
						case 0:
							showPain();
							break;
						case 1:
							showGradient();
							break;
					}
				}else{
					showPain();
				}
				onChangeActionInvoke();
			}
			
			void showPain(){
				Color c = show(repsent);
				if(c == null){
					return;
				}
				colorLabel.setFillColor(c);
			}
			
			void showGradient(){
				String selection = (String) JOptionPane.showInputDialog(JComplexColorChooser.this, "Please Choose How The Gradient Color Distribute:", "Choose Gradient Color Distribution", JOptionPane.PLAIN_MESSAGE, null, GradientColorMapping.colorMappingMode, GradientColorMapping.colorMappingMode[GradientColorMapping.defaultValue]);
				if(selection == null)
					return;
				GradientColorMapping mode = GradientColorMapping.colorMapping[Utility.getUtility().indexOf(GradientColorMapping.colorMappingMode, selection)];
				Color                from = show(repsent + " - Gradient Start Color");
				if(from == null)
					return;
				Color to = show(repsent + " - Gradient End Color");
				if(to == null)
					return;
				colorLabel.setFillColor(mode, from, to);
			}
			
			Color show(String title){
				return JColorChooser.showDialog(JComplexColorChooser.this, title, colorLabel.getColoring().isNull() ? intalColor : colorLabel.getColoring().getColor());
			}
		});
		Utility.getUtility().setPreferredSize(colorLabel, Sizing.Menu.doubleOne, Sizing.Menu.smallItemHeight);
		Utility.getUtility().setPreferredSize(actionButton, Sizing.Menu.doubleTwo, Sizing.Menu.smallItemHeight);
		Layouter.GridBagLayouter layout = new GridBagLayouter(this);
		layout.put(layout.instanceOf(colorLabel, 0, 0).setAnchor(Anchor.LEFT).setFill(Fill.BOTH).setWeight(0, 100).setInsets(5));
		layout.put(layout.instanceOf(actionButton, 1, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100).setInsets(5));
		if(removeAble){
			layout.put(layout.instanceOf(new JRemoveButton((ActionEvent e)->{
				colorLabel.removeFillColor();
				onChangeActionInvoke();
			}), 2, 0).setAnchor(Anchor.RIGHT).setFill(Fill.BOTH).setWeight(0, 100).setInsets(5).setInnerPad(10, 10));
		}
	}
	
	/**
	 * TODO
	 *
	 * @param color
	 * @author HomeletWei
	 */
	public void setColor(Color color){
		this.colorLabel.setFillColor(color);
	}
	
	/**
	 * TODO
	 *
	 * @return
	 * @author HomeletWei
	 */
	public Coloring getColoring(){
		return colorLabel.getColoring();
	}
	
	@Override
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		super.addChangeActionListener(changeActionListener);
		colorLabel.addChangeActionListener(changeActionListener);
	}
}
