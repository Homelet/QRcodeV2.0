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
import homelet.Display.State.Encode.encodeTemplate.TemplateDialog;
import homelet.Display.State.Encode.encodeTemplate.TemplatePanel;
import homelet.Display.State.Encode.encodeTemplate.TemplateUtility;
import homelet.Swing.JBasic.JFileHandler;
import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.JEditable.JEditableTextFields;
import homelet.Swing.RemoveActionListener;
import homelet.Utile.DateHandler;
import homelet.Utile.Utility;
import homelet.Utile.encodeTemplate.VCardTemplateLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 16, 2018
 */
public class VCardPanel extends TemplatePanel implements RemoveActionListener{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	VCardTemplateLoader templateLoader;
	int                 version = 0;
	boolean             isReady = false;
	String              result  = null;
	
	/**
	 * constructor for VCardPanel
	 *
	 * @param parent
	 * @param templateLoader
	 * @author HomeletWei
	 */
	public VCardPanel(TemplateDialog parent, VCardTemplateLoader templateLoader){
		super(parent);
		this.templateLoader = templateLoader;
		this.controlBar.add(new JTextButton("save as...", (ActionEvent e)->{
			result = generateForm();
			if(result == null)
				return;
			JFileHandler.FC.writeTextFile(VCardPanel.this, result, "vcard");
		}));
	}
	
	private String toFormatedName(VCardComponent nameTempalate){
		String[]      content = ((JEditableTextFields) nameTempalate.getComponent(5)).getContent();
		StringBuilder builder = new StringBuilder();
		builder.append(TemplateUtility.getTemplateUtility().stringFillter(content[3])).append(TemplateUtility.getTemplateUtility().stringFillter(content[0] + " ")).append(TemplateUtility.getTemplateUtility().stringFillter(content[1] + " ")).append(TemplateUtility.getTemplateUtility().stringFillter(content[2] + " ")).append(((content.length == 5) ? TemplateUtility.getTemplateUtility().stringFillter(content[4]) : ""));
		return builder.toString();
	}
	
	/**
	 * @return the result
	 * @author HomeletWei
	 */
	public String getResult(){
		return result;
	}
	
	@Override
	public void removeActionPerformed(JComponent compoenet){
		//	System.out.println("REMOVE : " + compoenet.toString());
		removeTemplateItem((TemplateComponent) compoenet);
	}
	
	@Override
	protected ActionListener add(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String selected = (String) JOptionPane.showInputDialog(VCardPanel.this, "Choose a title to add", "Add", JOptionPane.PLAIN_MESSAGE, null, templateLoader.get(version).getTitles(itemsList.getComponents()), 0);
				if(selected == null){
					return;
				}
				int index     = Utility.getUtility().indexOf(templateLoader.get(version).getAllTitles(), selected);
				int realIndex = templateLoader.get(version, index).getIndex();
				if(index == -1){
					return;
				}
				addTemplateComponent(realIndex);
			}
		};
	}
	
	protected void addTemplateComponent(int index){
		VCardComponent t = new VCardComponent(templateLoader.get(version, index), VCardPanel.this);
		addTemplateComponent(t);
	}
	
	@Override
	protected ActionListener apply(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				result = generateForm();
				if(result == null)
					return;
				parent.closeDialog();
			}
		};
	}
	
	@Override
	protected ActionListener startNew(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(!initialize()){
					return;
				}
			}
		};
	}
	
	/**
	 * @param versionNumber
	 * @author HomeletWei
	 */
	public void initalize(int versionNumber){
		this.version = versionNumber;
		restoreDefault();
		setReady(true);
		parent.setTitle(templateLoader.get(version).getVerion());
	}
	
	@Override
	public void restoreDefault(){
		removeAllTemplateItem();
		for(int i : templateLoader.get(version).getMustHaves()){
			addTemplateComponent(i);
		}
	}
	
	@Override
	public String generateForm(){
		StringBuilder builder = new StringBuilder(templateLoader.templatePrefix);
		builder.append('\n');
		builder.append("VERSION:").append(templateLoader.get(version).getVerion()).append('\n');
		builder.append("REV:").append(DateHandler.getDateHandler().getDateInfo()).append('\n');
		synchronized(itemsList.getTreeLock()){
			for(Component c : itemsList.getComponents()){
				if(c instanceof VCardComponent){
					String s = ((VCardComponent) c).toFormate();
					if(s == null)
						return null;
					builder.append(s).append('\n');
					if(((VCardComponent) c).getTemplateItem().getTitleFormateName().contentEquals("N")){
						String fn = toFormatedName((VCardComponent) c);
						if(fn != null && fn.length() != 0)
							builder.append("FN:").append(fn).append('\n');
					}
				}
			}
		}
		builder.append(templateLoader.templateSuffix);
		return builder.toString();
	}
	
	@Override
	public boolean initialize(){
		String[] strs   = new String[]{ "VCard 2.1 (Mostly supported)", "VCard 3.0 (Widely use in recent years)", "VCard 4.0 (Provide most interface)" };
		int      result = Utility.getUtility().indexOf(strs, (String) JOptionPane.showInputDialog(VCardPanel.this, "please select a version of VCard", "Select your Version", JOptionPane.QUESTION_MESSAGE, null, strs, "VCard 3.0 (Widely use in recent years)"));
		if(result == -1){
			return false;
		}
		initalize(result);
		return true;
	}
	
	@Override
	public String getRepresentString(){
		return templateLoader.get(version).getVerion();
	}
}
