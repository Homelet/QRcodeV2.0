/**
 * @author HomeletWei
 * @date Mar 26, 2018
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

import homelet.Utile.encodeTemplate.EMailTemplateLoader;
import homelet.Utile.encodeTemplate.VCardTemplateLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 26, 2018
 */
public class FileHandler{
	
	private static FileHandler fileHander = new FileHandler();
	
	private FileHandler(){
	}
	
	public static FileHandler getFileHander(){
		return fileHander;
	}
	
	public VCardTemplateLoader loadVCardTemplate(String filepath){
		File templateFile = new File(filepath);
		if(templateFile.isFile() && templateFile.canRead()){
			try{
				VCardTemplateLoader templateLoader = new VCardTemplateLoader();
				Scanner             mainScanner    = new Scanner(templateFile);
				int                 cursor         = 0;
				int                 indexCounter   = 0;
				while(mainScanner.hasNextLine()){
					String thisLine = mainScanner.nextLine();
					if(thisLine.startsWith("#") && thisLine.endsWith("#")){
						templateLoader.verifyTemplate(Utility.getUtility().getValueFromSequence('#', '#', thisLine));
						continue;
					}else if(thisLine.startsWith("--") && thisLine.endsWith("--")){
						indexCounter = 0;
						templateLoader.addNewSection(cursor++);
						continue;
					}
					if(thisLine.startsWith("[") && thisLine.endsWith("]")){
						templateLoader.add(cursor - 1, Utility.getUtility().getAllValueFromSequence('\"', '\"', Utility.getUtility().getCharsFromSequence('[', ']', thisLine)));
					}else if(thisLine.startsWith("{") && thisLine.endsWith("}")){
						char[][] subSectionBuffer = Utility.getUtility().getAllCharsFromSequence('<', '>', Utility.getUtility().getCharsFromSequence('{', '}', thisLine));
						String[] firstBuffer      = Utility.getUtility().getAllValueFromSequence('\"', '\"', subSectionBuffer[0]);
						templateLoader.add(cursor - 1, indexCounter++, Boolean.valueOf(firstBuffer[0]), Integer.valueOf(firstBuffer[1]), Utility.getUtility().getAllValueFromSequence('\"', '\"', subSectionBuffer[1]), Utility.getUtility().getAllValueFromSequence('\"', '\"', subSectionBuffer[2]), Utility.getUtility().getAllValueFromSequence('\"', '\"', subSectionBuffer[3]));
					}
				}
				mainScanner.close();
				return templateLoader;
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public EMailTemplateLoader loadEmailTemplate(String filepath){
		File templateFile = new File(filepath);
		if(templateFile.isFile() && templateFile.canRead()){
			try{
				EMailTemplateLoader templateLoader = new EMailTemplateLoader();
				Scanner             mainScanner    = new Scanner(templateFile);
				while(mainScanner.hasNextLine()){
					String thisLine = mainScanner.nextLine();
					if(thisLine.startsWith("#") && thisLine.endsWith("#")){
						templateLoader.verifyTemplate(Utility.getUtility().getValueFromSequence('#', '#', thisLine));
						continue;
					}else if(thisLine.startsWith("--") && thisLine.endsWith("--")){
						continue;
					}
					if(thisLine.startsWith("[") && thisLine.endsWith("]")){
					}else if(thisLine.startsWith("{") && thisLine.endsWith("}")){
						char[][] subSectionBuffer = Utility.getUtility().getAllCharsFromSequence('<', '>', Utility.getUtility().getCharsFromSequence('{', '}', thisLine));
						templateLoader.set(Utility.getUtility().getValueFromSequence('\"', '\"', subSectionBuffer[0]), Utility.getUtility().getAllValueFromSequence('\"', '\"', subSectionBuffer[2]));
					}
				}
				mainScanner.close();
				return templateLoader;
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Get extenstion string.
	 *
	 * @param f the f
	 * @return the string
	 */
	public String getExtenstion(File f){
		return getExtenstion(f.getName());
	}
	
	/**
	 * Get extenstion string.
	 *
	 * @param name the name
	 * @return the string
	 */
	public String getExtenstion(String name){
		String ext = null;
		int    i   = name.lastIndexOf('.');
		if(i > 0 && i < name.length() - 1){
			ext = name.substring(i + 1);
		}
		return ext;
	}
	
	/**
	 * Has extension boolean.
	 *
	 * @param name              the name
	 * @param supportedFormates the supported formates
	 * @return the boolean
	 */
	public boolean hasExtension(String name, String... supportedFormates){
		int i = name.lastIndexOf('.');
		if(i == -1 || i >= name.length())
			return false;
		String ext = name.substring(i + 1);
		for(String s : supportedFormates){
			if(ext.contentEquals(s))
				return true;
		}
		return false;
	}
	
	/**
	 * Append extension string.
	 *
	 * @param name      the name
	 * @param extention the extention
	 * @return the string
	 */
	public String appendExtension(String name, String extention){
		return name.concat(".").concat(extention);
	}
	
	/**
	 * Create extention discription string.
	 *
	 * @param extentions the extentions
	 * @return the string
	 */
	public String createExtentionDiscription(String... extentions){
		String buffer = null;
		for(String s : extentions){
			buffer = (buffer == null) ? (".".concat(s)) : (buffer.concat("/.").concat(s));
		}
		return buffer;
	}
	//
	//    public  MeCardTemplateLoader loadMeCardTemplate(String filepath){
	//	File templateFile = new File(filepath);
	//	if(templateFile.isFile() && templateFile.canRead()){
	//	    try{
	//		MeCardTemplateLoader templateLoader = new MeCardTemplateLoader();
	//		Scanner mainScanner = new Scanner(templateFile);
	//		while(mainScanner.hasNextLine()){
	//		    String thisLine = mainScanner.nextLine();
	//		    if(thisLine.startsWith("#") && thisLine.endsWith("#")){
	//			templateLoader.verifyTemplate(Utils.getValueFromSequence('#', '#', thisLine));
	//			continue;
	//		    }else if(thisLine.startsWith("--") && thisLine.endsWith("--")){
	//			continue;
	//		    }
	//
	//		    if(thisLine.startsWith("[") && thisLine.endsWith("]")){
	//			templateLoader.setHeader(Utils.getAllValueFromSequence('\"', '\"',
	//			                                                       Utils.getCharsFromSequence('[', ']',
	//			                                                                                  thisLine)));
	//		    }else if(thisLine.startsWith("{") && thisLine.endsWith("}")){
	//			char[][] subSectionBuffer = Utils.getAllCharsFromSequence('<', '>',
	//			                                                          Utils.getCharsFromSequence('{', '}',
	//			                                                                                     thisLine));
	//			templateLoader.add(Utils.getValueFromSequence('\"', '\"', subSectionBuffer[0]),
	//			                   Utils.getValueFromSequence('\"', '\"', subSectionBuffer[1]));
	//		    }
	//		}
	//		mainScanner.close();
	//		return templateLoader;
	//	    }catch(FileNotFoundException e){
	//		e.printStackTrace();
	//	    }
	//	}
	//	return null;
	//    }
	//
	//    public  PhoneCallTemplateLoader loadPhoneCallTemplate(String filepath){
	//	File templateFile = new File(filepath);
	//	if(templateFile.isFile() && templateFile.canRead()){
	//	    try{
	//		EMailTemplateLoader templateLoader = new EMailTemplateLoader();
	//		Scanner mainScanner = new Scanner(templateFile);
	//		while(mainScanner.hasNextLine()){
	//		    String thisLine = mainScanner.nextLine();
	//		    if(thisLine.startsWith("#") && thisLine.endsWith("#")){
	//			templateLoader.verifyTemplate(Utils.getValueFromSequence('#', '#', thisLine));
	//			continue;
	//		    }else if(thisLine.startsWith("--") && thisLine.endsWith("--")){
	//			continue;
	//		    }
	//
	//		    if(thisLine.startsWith("[") && thisLine.endsWith("]")){
	//			templateLoader.setHeader(Utils.getAllValueFromSequence('\"', '\"',
	//			                                                       Utils.getCharsFromSequence('[', ']',
	//			                                                                                  thisLine)));
	//		    }else if(thisLine.startsWith("{") && thisLine.endsWith("}")){
	//			char[][] subSectionBuffer = Utils.getAllCharsFromSequence('<', '>',
	//			                                                          Utils.getCharsFromSequence('{', '}',
	//			                                                                                     thisLine));
	//			templateLoader.set(Utils.getValueFromSequence('\"', '\"', subSectionBuffer[0]),
	//			                   Utils.getValueFromSequence('\"', '\"', subSectionBuffer[1]));
	//		    }
	//		}
	//		mainScanner.close();
	//		return templateLoader;
	//	    }catch(FileNotFoundException e){
	//		e.printStackTrace();
	//	    }
	//	}
	//	return null;
	//    }
	//
	//    public  TextMessageTemplateLoader loadTextMessageTemplate(String filepath){
	//	File templateFile = new File(filepath);
	//	if(templateFile.isFile() && templateFile.canRead()){
	//	    try{
	//		EMailTemplateLoader templateLoader = new EMailTemplateLoader();
	//		Scanner mainScanner = new Scanner(templateFile);
	//		while(mainScanner.hasNextLine()){
	//		    String thisLine = mainScanner.nextLine();
	//		    if(thisLine.startsWith("#") && thisLine.endsWith("#")){
	//			templateLoader.verifyTemplate(Utils.getValueFromSequence('#', '#', thisLine));
	//			continue;
	//		    }else if(thisLine.startsWith("--") && thisLine.endsWith("--")){
	//			continue;
	//		    }
	//
	//		    if(thisLine.startsWith("[") && thisLine.endsWith("]")){
	//			templateLoader.setHeader(Utils.getAllValueFromSequence('\"', '\"',
	//			                                                       Utils.getCharsFromSequence('[', ']',
	//			                                                                                  thisLine)));
	//		    }else if(thisLine.startsWith("{") && thisLine.endsWith("}")){
	//			char[][] subSectionBuffer = Utils.getAllCharsFromSequence('<', '>',
	//			                                                          Utils.getCharsFromSequence('{', '}',
	//			                                                                                     thisLine));
	//			templateLoader.set(Utils.getValueFromSequence('\"', '\"', subSectionBuffer[0]),
	//			                   Utils.getValueFromSequence('\"', '\"', subSectionBuffer[1]));
	//		    }
	//		}
	//		mainScanner.close();
	//		return templateLoader;
	//	    }catch(FileNotFoundException e){
	//		e.printStackTrace();
	//	    }
	//	}
	//	return null;
	//    }
	//
	//    public  WebSiteTemplateLoader loadWebSiteTemplate(String filepath){
	//	File templateFile = new File(filepath);
	//	if(templateFile.isFile() && templateFile.canRead()){
	//	    try{
	//		EMailTemplateLoader templateLoader = new EMailTemplateLoader();
	//		Scanner mainScanner = new Scanner(templateFile);
	//		while(mainScanner.hasNextLine()){
	//		    String thisLine = mainScanner.nextLine();
	//		    if(thisLine.startsWith("#") && thisLine.endsWith("#")){
	//			templateLoader.verifyTemplate(Utils.getValueFromSequence('#', '#', thisLine));
	//			continue;
	//		    }else if(thisLine.startsWith("--") && thisLine.endsWith("--")){
	//			continue;
	//		    }
	//
	//		    if(thisLine.startsWith("[") && thisLine.endsWith("]")){
	//			templateLoader.setHeader(Utils.getAllValueFromSequence('\"', '\"',
	//			                                                       Utils.getCharsFromSequence('[', ']',
	//			                                                                                  thisLine)));
	//		    }else if(thisLine.startsWith("{") && thisLine.endsWith("}")){
	//			char[][] subSectionBuffer = Utils.getAllCharsFromSequence('<', '>',
	//			                                                          Utils.getCharsFromSequence('{', '}',
	//			                                                                                     thisLine));
	//			templateLoader.set(Utils.getValueFromSequence('\"', '\"', subSectionBuffer[0]),
	//			                   Utils.getValueFromSequence('\"', '\"', subSectionBuffer[1]));
	//		    }
	//		}
	//		mainScanner.close();
	//		return templateLoader;
	//	    }catch(FileNotFoundException e){
	//		e.printStackTrace();
	//	    }
	//	}
	//	return null;
	//    }
	//
	//    public  WIFITemplateLoader loadWIFITemplate(String filepath){
	//	File templateFile = new File(filepath);
	//	if(templateFile.isFile() && templateFile.canRead()){
	//	    try{
	//		EMailTemplateLoader templateLoader = new EMailTemplateLoader();
	//		Scanner mainScanner = new Scanner(templateFile);
	//		while(mainScanner.hasNextLine()){
	//		    String thisLine = mainScanner.nextLine();
	//		    if(thisLine.startsWith("#") && thisLine.endsWith("#")){
	//			templateLoader.verifyTemplate(Utils.getValueFromSequence('#', '#', thisLine));
	//			continue;
	//		    }else if(thisLine.startsWith("--") && thisLine.endsWith("--")){
	//			continue;
	//		    }
	//
	//		    if(thisLine.startsWith("[") && thisLine.endsWith("]")){
	//			templateLoader.setHeader(Utils.getAllValueFromSequence('\"', '\"',
	//			                                                       Utils.getCharsFromSequence('[', ']',
	//			                                                                                  thisLine)));
	//		    }else if(thisLine.startsWith("{") && thisLine.endsWith("}")){
	//			char[][] subSectionBuffer = Utils.getAllCharsFromSequence('<', '>',
	//			                                                          Utils.getCharsFromSequence('{', '}',
	//			                                                                                     thisLine));
	//			templateLoader.set(Utils.getValueFromSequence('\"', '\"', subSectionBuffer[0]),
	//			                   Utils.getValueFromSequence('\"', '\"', subSectionBuffer[1]));
	//		    }
	//		}
	//		mainScanner.close();
	//		return templateLoader;
	//	    }catch(FileNotFoundException e){
	//		e.printStackTrace();
	//	    }
	//	}
	//	return null;
	//    }
	
	/**
	 * TODO
	 *
	 * @param image
	 * @param file
	 * @param formate
	 * @return
	 * @throws IOException
	 * @author HomeletWei
	 */
	public boolean writeImageToFile(BufferedImage image, File file, String formate) throws IOException{
		return ImageIO.write(image, formate, file);
	}
	
	/**
	 * TODO
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @author HomeletWei
	 */
	public boolean writeTextToFile(String content, File file) throws IOException{
		if(file == null || (content == null || content.isEmpty()))
			return false;
		FileWriter filewriter = new FileWriter(file);
		filewriter.write(content);
		filewriter.close();
		return true;
	}
	
	public BufferedImage loadImage(File file) throws IOException{
		if(file == null)
			return null;
		return ImageIO.read(file);
	}
	
	public void openFileInSystem(File file) throws IOException{
		if(file == null)
			return;
		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().open(file);
		}
	}
	
	/**
	 * <pre>
	 * mailtoURL = "mailto:" [ to ] [ headers ]
	 * to = #mailbox
	 * headers = "?" header *( "&" header )
	 * header = hname "=" hvalue
	 * hname = *urlc
	 * hvalue = *urlc
	 * "#mailbox" is as specified in RFC 822 [RFC822]. This means that it
	 * consists of zero or more comma-separated mail addresses, possibly
	 * including "phrase" and "comment" components. Note that all URL
	 * reserved characters in "to" must be encoded: in particular,
	 * parentheses, commas, and the percent sign ("%"), which commonly occur
	 * in the "mailbox" syntax.
	 * "hname" and "hvalue" are encodings of an RFC 822 header name and
	 * value, respectively. As with "to", all URL reserved characters must
	 * be encoded.
	 * The special hname "body" indicates that the associated hvalue is the
	 * body of the message. The "body" hname should contain the content for
	 * the first text/plain body part of the message. The mailto URL is
	 * primarily intended for generation of short text messages that are
	 * actually the content of automatic processing (such as "subscribe"
	 * messages for mailing lists), not general MIME bodies.
	 * Within mailto URLs, the characters "?", "=", "&" are reserved.
	 * Because the "&" (ampersand) character is reserved in HTML, any mailto
	 * URL which contains an ampersand must be spelled differently in HTML
	 * than in other contexts. A mailto URL which appears in an HTML
	 * document must use "&amp;" instead of "&".
	 * Also note that it is legal to specify both "to" and an "hname" whose
	 * value is "to". That is,
	 * mailto:addr1%2C%20addr2
	 * is equivalent to
	 * mailto:?to=addr1%2C%20addr2
	 * is equivalent to
	 * mailto:addr1?to=addr2
	 * 8-bit characters in mailto URLs are forbidden. MIME encoded words (as
	 * defined in [RFC2047]) are permitted in header values, but not for any
	 * part of a "body" hname.
	 * </pre>
	 *
	 * @param emailAddress
	 * @throws IOException
	 * @author HomeletWei
	 */
	public void emailToInSystem(URI emailAddress) throws IOException{
		if(emailAddress == null)
			return;
		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().mail(emailAddress);
		}
	}
	
	public void emailInSystem() throws IOException{
		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().mail();
		}
	}
	
	public void printInSystem(File file) throws IOException{
		if(file == null)
			return;
		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().print(file);
		}
	}
	
	public void browseInSystem(URI uriAddress) throws IOException{
		if(uriAddress == null)
			return;
		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().browse(uriAddress);
		}
	}
}
