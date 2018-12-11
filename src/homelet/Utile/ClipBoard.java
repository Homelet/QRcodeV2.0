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
package homelet.Utile;

import homelet.Display.Display;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 25, 2018
 */
public class ClipBoard{
	
	private static Clipboard systemClipboard;
	private static ClipBoard clipBoard = new ClipBoard();
	
	private ClipBoard(){
		systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	}
	
	private static ClipBoard getClipBoard(){
		return clipBoard;
	}
	
	public void copyToClipBoard(String text){
		systemClipboard.setContents(new StringSelection(text == null ? "" : text), null);
	}
	
	public String getClipBoardText(){
		try{
			return (String) systemClipboard.getContents(Display.class).getTransferData(new DataFlavor(java.lang.String.class, "java.lang.String"));
		}catch(HeadlessException | IOException | UnsupportedFlavorException e){
			e.printStackTrace();
		}
		return null;
	}
}
