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
public class WIFITemplateLoader extends TemplateLoader{
	
	String   templateTitle;
	String   templateDiscription;
	String   templateFormate;
	String[] nameContentHeader;
	String   nameContent;
	String[] wifiSecurityHeader;
	String[] wifiSecurityNameList;
	String[] wifiSecurityFormateList;
	String[] passwordContentHeader;
	String   passwordContent;
	
	/**
	 * constructor for WIFITemplate
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public WIFITemplateLoader(){
		super("HomeletQRCodeTemplate-WI-FI-V1.0");
	}
	
	public void setHeader(String[] header){
		if(isVerifyed){
			this.templateTitle = header[0];
			this.templateDiscription = header[1];
			this.templateFormate = header[2];
		}
	}
	
	public void setName(String[] header, String content){
		if(isVerifyed){
			this.nameContentHeader = header;
			this.nameContent = content;
		}
	}
	
	public void setContent(String[] header, String[] nameList, String[] formateLit){
		if(isVerifyed){
			this.wifiSecurityHeader = header;
			this.wifiSecurityNameList = nameList;
			this.wifiSecurityFormateList = formateLit;
		}
	}
	
	public void setPassword(String[] header, String content){
		if(isVerifyed){
			this.passwordContentHeader = header;
			this.passwordContent = content;
		}
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
	
	/** @return nameContentHeader */
	public String getNameContentTitle(){
		return nameContentHeader[0];
	}
	
	/** @return nameContentHeader */
	public String getNameContentFormate(){
		return nameContentHeader[1];
	}
	
	/** @return nameContent */
	public String getNameContent(){
		return nameContent;
	}
	
	/** @return wifiSecurityHeader */
	public String getWifiSecurityTitle(){
		return wifiSecurityHeader[0];
	}
	
	/** @return wifiSecurityHeader */
	public String getWifiSecurityFormate(){
		return wifiSecurityHeader[1];
	}
	
	/** @return wifiSecurityNameList */
	public String[] getWifiSecurityList(){
		return wifiSecurityNameList;
	}
	
	/** @return wifiSecurityFormateList */
	public String getWifiSecurityFormate(int index){
		return wifiSecurityFormateList[index];
	}
	
	/** @return passwordContentHeader */
	public String getPasswordContentTitle(){
		return passwordContentHeader[0];
	}
	
	/** @return passwordContentHeader */
	public String getPasswordContentFormate(){
		return passwordContentHeader[1];
	}
	
	/** @return passwordContent */
	public String getPasswordContent(){
		return passwordContent;
	}
}
