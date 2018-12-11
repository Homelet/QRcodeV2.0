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

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public class WebSiteTemplateLoader extends TemplateLoader{
	
	String   templateTitle;
	String   templateDiscription;
	String   templateFormate;
	String   HTTPIdentifyer;
	String   nonHTTPIdentifyer;
	String[] content;
	
	/**
	 * constructor for WebSiteTemplate
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public WebSiteTemplateLoader(){
		super("HomeletQRCodeTemplate-URL-V1.0");
	}
	
	public void setHeader(String[] string){
		if(isVerifyed){
			templateTitle = string[0];
			templateDiscription = string[1];
			templateFormate = string[2];
		}
	}
	
	public void set(String header, String[] identifyers, String content){
		if(isVerifyed){
			this.content = new String[]{ header, content };
			HTTPIdentifyer = identifyers[0];
			nonHTTPIdentifyer = identifyers[1];
		}
	}
	
	/** @return hTTPIdentifyer */
	public String getHTTPIdentifyer(){
		return HTTPIdentifyer;
	}
	
	/** @return nonHTTPIdentifyer */
	public String getNonHTTPIdentifyer(){
		return nonHTTPIdentifyer;
	}
	
	/** @return templateTitle */
	public String getTemplateTitle(){
		return templateTitle;
	}
	
	/** @return templateDiscription */
	public String getTemplateDiscription(){
		return templateDiscription;
	}
	
	/** @return templateFormate */
	public String getTemplateFormate(){
		return templateFormate;
	}
	
	/** @return contentHeader */
	public String getContentHeader(){
		return content[0];
	}
	
	/** @return content */
	public String getContent(){
		return content[1];
	}
}
