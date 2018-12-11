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
 * @date May 13, 2018
 */
package homelet.Display.State.Encode.encodeTemplate;

import homelet.Swing.JBasic.JRemoveButton;
import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.RemoveActionListener;

import javax.swing.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date May 13, 2018
 */
public abstract class TemplateComponent extends JComponent{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor for TemplateComponent
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public TemplateComponent(){
	}
	
	protected JTextButton removeButton(boolean mustHave, RemoveActionListener removeAction){
		JTextButton remove = new JRemoveButton(removeAction, TemplateComponent.this);
		remove.setEnabled(!mustHave);
		return remove;
	}
	
	/**
	 * convert the result
	 *
	 * @return result
	 * @author HomeletWei
	 */
	public abstract String toFormate();
}
