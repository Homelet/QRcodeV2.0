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
package homelet.Swing.JBasic;

import homelet.Utile.FileHandler;
import homelet.Utile.Utility;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public class JFileHandler{
	
	/** @Fields <b>publicFileChooser</b> TODO */
	public static JFileHandler FC                         = new JFileHandler();
	/** @Fields <b>serialVersionUID</b> TODO */
	final         String[]     supportedReadImageFormate  = { "jpg", "gif", "png", "jpeg" };
	final         String[]     supportedWriteImageFormate = { "jpg", "gif", "png", "jpeg" };
	JFileChooser fileChooser;
	
	/**
	 * constructor for JComplexFileChooser
	 * TODO
	 *
	 * @author HomeletWei
	 */
	private JFileHandler(){
		fileChooser = new JFileChooser();
	}
	
	/**
	 * show a File Dialog to choose Image File
	 *
	 * @param parent the parent componet
	 * @return the choose Image File
	 * @author HomeletWei
	 */
	public BufferedImage showChooseImageDialog(Component parent){
		fileChooser.setFileFilter(FileFliter.createFileFliter(supportedReadImageFormate));
		int returnVal = fileChooser.showOpenDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			try{
				return FileHandler.getFileHander().loadImage(fileChooser.getSelectedFile());
			}catch(IOException e1){
				Utility.getUtility().handleException(parent, e1, "IOException", "Fail to load file\nFile formate not supported or file is damaged", "3.1 Unsupported Image/File Formate");
			}
		}
		return null;
	}
	
	/**
	 * show a File Dialog to choose Image File
	 *
	 * @param parent the parent componet
	 * @return the choose Image File
	 * @author HomeletWei
	 */
	public File showChooseImageDialog_file(Component parent){
		fileChooser.setFileFilter(FileFliter.createFileFliter(supportedReadImageFormate));
		int returnVal = fileChooser.showOpenDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	/**
	 * open a dialog to save to a image file
	 *
	 * @param parent        the parent compoenet
	 * @param renderedImage the image that will be write
	 * @return The file which is contains the image
	 * @author HomeletWei
	 */
	public File writeImageFile(Component parent, BufferedImage renderedImage){
		int returnVal = fileChooser.showSaveDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String absPath   = fileChooser.getSelectedFile().getAbsolutePath();
			String extention = null;
			if(!FileHandler.getFileHander().hasExtension(absPath, supportedWriteImageFormate)){
				extention = (String) JOptionPane.showInputDialog(parent, "Choose your file Formate", "Confirm File Formate", JOptionPane.PLAIN_MESSAGE, null, supportedWriteImageFormate, "png");
				if(extention == null){
					return null;
				}
				absPath = FileHandler.getFileHander().appendExtension(absPath, extention);
			}else{
				extention = FileHandler.getFileHander().getExtenstion(absPath);
			}
			File f = new File(absPath);
			if(f.exists() && f.isFile()){
				int result = JOptionPane.showConfirmDialog(parent, "Are you sure to overwrite \"" + f.getName() + "\"?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				if(result == JOptionPane.NO_OPTION){
					return null;
				}
			}
			try{
				if(FileHandler.getFileHander().writeImageToFile(renderedImage, f, extention))
					return f;
			}catch(IOException e){
				Utility.getUtility().handleException(parent, e, "IOException", "Fail to write file\n" + e.getLocalizedMessage(), "3.0 IOException");
			}
		}
		return null;
	}
	
	public File writeTextFile(Component parent, String content, String extention){
		int returnVal = fileChooser.showSaveDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String absPath = fileChooser.getSelectedFile().getAbsolutePath();
			if(!FileHandler.getFileHander().hasExtension(absPath, extention)){
				absPath = FileHandler.getFileHander().appendExtension(absPath, extention);
			}
			File f = new File(absPath);
			if(f.exists() && f.isFile()){
				int result = JOptionPane.showConfirmDialog(parent, "Are you sure to overwrite \"" + f.getName() + "\"?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
				if(result == JOptionPane.NO_OPTION){
					return null;
				}
			}
			try{
				if(FileHandler.getFileHander().writeTextToFile(content, f))
					return f;
			}catch(IOException e){
				Utility.getUtility().handleException(parent, e, "IOException", "Fail to write file\n" + e.getLocalizedMessage(), "3.0 IOException");
			}
		}
		return null;
	}
}

class FileFliter{
	
	public static FileNameExtensionFilter createFileFliter(String... extensions){
		return new FileNameExtensionFilter(FileHandler.getFileHander().createExtentionDiscription(extensions), extensions);
	}
}
