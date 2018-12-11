/**
 * @author HomeletWei
 * @date Apr 13, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Display.State.Encode.encodeTemplate;

import homelet.Display.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * the template dialog all dialog should extend this
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public abstract class TemplateDialog extends JDialog{
	
	/** @Fields <b>serialVersionUID</b> */
	private static final long   serialVersionUID = 1L;
	protected            String titleRoot;
	
	/**
	 * constructor for EncodeTemplate
	 *
	 * @param frameOwner
	 * @param titleRoot
	 * @author HomeletWei
	 */
	public TemplateDialog(Frame frameOwner, String titleRoot){
		super(frameOwner, true);
		this.titleRoot = "Template - " + titleRoot;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setResizable(true);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				closeDialog();
				super.windowClosing(e);
			}
		});
	}
	
	/**
	 * close the dialog
	 *
	 * @author HomeletWei
	 */
	public void closeDialog(){
		this.setVisible(false);
	}
	
	/**
	 * @param panel
	 * @author HomeletWei
	 */
	public void addTemplatePanel(TemplatePanel panel){
		this.setMinimumSize(panel.getPreferredSize());
		this.setPreferredSize(this.getMinimumSize());
		this.setMaximumSize(new Dimension(this.getMinimumSize().width, Display.SCREEN_HEIGHT));
		this.setContentPane(panel);
	}
	
	/**
	 * show the dialog
	 *
	 * @author HomeletWei
	 */
	public void showDialog(){
		this.setVisible(true);
	}
	
	/**
	 * remove the dialog data, the dialog will not be called after this
	 *
	 * @author HomeletWei
	 */
	public void removeDialog(){
		this.dispose();
	}
	
	/**
	 * get the result
	 *
	 * @return the result
	 * @author HomeletWei
	 */
	public abstract String getResult();
	
	/**
	 * set title
	 *
	 * @param templateTitle
	 * @author HomeletWei
	 */
	@Override
	public void setTitle(String templateTitle){
		super.setTitle(titleRoot + " - " + templateTitle);
	}
}
