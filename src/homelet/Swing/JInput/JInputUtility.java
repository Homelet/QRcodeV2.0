/**
 * @author HomeletWei
 * @date Apr 26, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JInput;

import homelet.Utile.Utility;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 26, 2018
 */
public class JInputUtility{
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void copyAll(JTextComponent parent){
		selectAll(parent);
		copy(parent);
	}
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void selectAll(JTextComponent parent){
		parent.selectAll();
	}
	
	/**
	 * s
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void copy(JTextComponent parent){
		parent.copy();
	}
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void replaceAll(JTextComponent parent){
		selectAll(parent);
		pasta(parent);
	}
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void pasta(JTextComponent parent){
		parent.paste();
	}
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void cutAll(JTextComponent parent){
		selectAll(parent);
		cut(parent);
	}
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void cut(JTextComponent parent){
		parent.cut();
	}
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void clearAll(JTextComponent parent){
		try{
			parent.getDocument().remove(0, parent.getDocument().getLength());
		}catch(BadLocationException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param parent
	 * @return the letter Count
	 * @author HomeletWei
	 */
	public static int getLetterCount(JTextComponent parent){
		return Utility.getUtility().removeWhiteSpace(parent.getText()).length();
	}
	
	/**
	 * @param parent
	 * @return the word Count
	 * @author HomeletWei
	 */
	public static int getWordCount(JTextComponent parent){
		return parent.getText().length() == 0 ? 0 : parent.getText().trim().split("\\s+").length;
	}
	
	/**
	 * @param parent
	 * @return the character count
	 * @author HomeletWei
	 */
	public static int getCharaterCount(JTextComponent parent){
		return parent.getText().length();
	}
	
	/**
	 * TODO
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public static void FireFinishDialog(JComponent parent){
		JOptionPane.showMessageDialog(parent, "Successful Copyed to ClipBoard", "Operation Success", JOptionPane.INFORMATION_MESSAGE, null);
	}
}
