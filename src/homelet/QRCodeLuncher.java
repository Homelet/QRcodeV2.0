package homelet;

import homelet.Display.Display;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author HomeletWei
 * @date Mar 25, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */

/**
 * The type Qrcode Luncher.
 *
 * @author HomeletWei
 * @date Mar 25, 2018
 */
public class QRCodeLuncher{
	
	public static final String  program_waterMark = "@Homelet QRCode Generator";
	private static      Display display;
	
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @author HomeletWei
	 */
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		display = new Display();
		ArrayList<String> stringArrayList = new ArrayList<>();
		for(Iterator<String> itr = stringArrayList.iterator(); itr.hasNext(); ){
			String next = itr.next();
		}
		for(String s : stringArrayList){
		}
	}
	
	/**
	 * Gets display.
	 *
	 * @return the display
	 */
	public static Display getDisplay(){
		return display;
	}
}
