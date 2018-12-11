/**
 * @author HomeletWei
 * @date Apr 16, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Display.State.Encode.encodeTemplate.VCard;

import homelet.Display.State.Encode.encodeTemplate.TemplateComponent;
import homelet.Display.State.Encode.encodeTemplate.TemplateUtility;
import homelet.Swing.JBasic.JDividerLine;
import homelet.Swing.JBasic.JDividerLine.DividerMode;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Swing.JEditable.*;
import homelet.Swing.RemoveActionListener;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;
import homelet.Utile.Utility.Orientation;

import javax.swing.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 16, 2018
 */
public class VCardComponent extends TemplateComponent{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	VCardTemplate template;
	
	/**
	 * constructor for VCardTemplateItem
	 * TODO
	 *
	 * @param template
	 * @param removeAction
	 * @author HomeletWei
	 */
	public VCardComponent(VCardTemplate template, RemoveActionListener removeAction){
		this.template = template;
		////////////<div>///////////////
		JComponent first = new JTextLabel(template.getTitleName(), homelet.Utile.Constants.Alignment.CENTER, homelet.Utile.Constants.Alignment.CENTER, 2, null, false);
		Utility.getUtility().setPreferredSize(first, Sizing.EncodeTemplate.templatePieceOne);
		////////////<div>///////////////
		JComponent second;
		switch(template.getTypeLoadingType()){
			case JEditableCombBox.templateIdentifyer:
				second = new JEditableCombBox<>(template.getTypeNameList(), 0);
				break;
			case JEditableTextArea.templateIdentifyer://$FALL-THROUGH$
			case JEditableTextFields.templateIdentifyer://$FALL-THROUGH$
			case JEditableDateChoooser.templateIdentifyer://$FALL-THROUGH$
			default:
				second = new JDividerLine(DividerMode.DOT, Sizing.EncodeTemplate.templatePieceTwo, Orientation.HORIZONTAL);
				break;
		}
		////////////<div>///////////////
		JComponent third;
		switch(template.getFieldsLoadingType()){
			case JEditableTextArea.templateIdentifyer:
				third = new JEditableTextArea(template.getFieldsNameList()[0], template.getFieldsDiscription());
				break;
			case JEditableTextFields.templateIdentifyer:
				third = new JEditableTextFields(template.getFieldsNameList(), template.getFieldsDiscription());
				break;
			case JEditableCombBox.templateIdentifyer:
				third = new JEditableCombBox<>(TemplateUtility.getTemplateUtility().stringFiliter(TemplateUtility.getTemplateUtility().packUpStrings(template.getFieldsNameList(), 2), 0), 0);
				break;
			case JEditableDateChoooser.templateIdentifyer:
				third = new JEditableDateChoooser(template.getFieldsList());
				break;
			default:
				third = new JDividerLine(DividerMode.DOT, Sizing.EncodeTemplate.templatePieceTwo, Orientation.HORIZONTAL);
				break;
		}
		///////////<div>///////////////
		Layouter.GridBagLayouter layout = new GridBagLayouter(this);
		layout.put(layout.instanceOf(removeButton(template.isMustHave(), removeAction), 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.VERTICAL).setWeight(0, 100));
		layout.put(layout.instanceOf(first, 1, 0).setAnchor(Anchor.LEFT).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(new JDividerLine(Sizing.EncodeTemplate.dividerLine, Orientation.VERTICAL), 2, 0).setAnchor(Anchor.CENTER).setFill(Fill.VERTICAL).setWeight(0, 100));
		layout.put(layout.instanceOf(second, 3, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(new JDividerLine(Sizing.EncodeTemplate.dividerLine, Orientation.VERTICAL), 4, 0).setAnchor(Anchor.CENTER).setFill(Fill.VERTICAL).setWeight(0, 100));
		layout.put(layout.instanceOf(third, 5, 0).setAnchor(Anchor.RIGHT).setFill(Fill.BOTH).setWeight(100, 100));
		////////////<div>///////////////
	}
	
	@Override
	public String toFormate(){
		synchronized(this.getTreeLock()){
			int      typeSelected = -1;
			String[] content      = null;
			for(int index = 0; index < this.getComponentCount(); index++){
				JEdit item = null;
				if(this.getComponent(index) instanceof JEdit)
					item = (JEdit) this.getComponent(index);
				if(item == null)
					continue;
				if(!item.isReady()){
					JOptionPane.showMessageDialog(VCardComponent.this, "Please set a value before applying", "Message", JOptionPane.WARNING_MESSAGE, null);
					return null;
				}
				switch(index){
					case 3:
						if(item instanceof JEditableCombBox<?>){
							typeSelected = ((JEditableCombBox<?>) item).getSelectedIndex();
						}
						break;
					case 5:
						if(item instanceof JEditableTextArea){
							content = new String[]{ ((JEditableTextArea) item).getContent() };
						}else if(item instanceof JEditableTextFields){
							content = ((JEditableTextFields) item).getContent();
						}else if(item instanceof JEditableCombBox<?>){
							content = new String[]{ TemplateUtility.getTemplateUtility().stringFiliter(TemplateUtility.getTemplateUtility().packUpStrings(template.getFieldsList(), 2), 1)[((JEditableCombBox<?>) item).getSelectedIndex()] };
						}else if(item instanceof JEditableDateChoooser){
							content = new String[]{ ((JEditableDateChoooser) item).getContent() };
						}
						break;
					default:
				}
			}
			if(typeSelected == -1){
				return template.toFormate(content);
			}
			return template.toFormate(typeSelected, content);
		}
	}
	
	/**
	 * @return the template
	 * @author HomeletWei
	 */
	public VCardTemplate getTemplateItem(){
		return template;
	}
}
