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
package homelet.Utile.encodeTemplate;

import java.util.Arrays;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public class EMailTemplateLoader extends TemplateLoader{
	
	String   title;
	String[] content;
	
	/**
	 * constructor for EMailTemplate
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public EMailTemplateLoader(){
		super("HomeletQRCodeTemplate-EMAIL-V1.0");
	}
	
	public void set(String title, String[] content){
		if(isVerifyed){
			this.title = title;
			this.content = content;
		}
	}
	
	/** @return templateDiscription */
	public String getTemplateTitle(){
		return title;
	}
	
	/** @return contentHeader */
	public String getContentLoadingType(){
		return content[0];
	}
	
	/** @return content */
	public String[] getContentList(){
		return Arrays.copyOfRange(content, 1, content.length);
	}
	
	public String getContentDiscription(){
		return content[1];
	}
	
	public String getContent(){
		return content[2];
	}
}
