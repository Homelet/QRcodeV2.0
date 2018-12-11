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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public class MeCardTemplateLoader extends TemplateLoader{
	
	String              templateTitle;
	String              templateDiscription;
	String              templateFormate;
	ArrayList<String[]> contentData;
	
	/**
	 * constructor for MeCardTemplate
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public MeCardTemplateLoader(){
		super("HomeletQRCodeTemplate-MeCard-V1.0");
		contentData = new ArrayList<>();
	}
	
	public void setHeader(String[] header){
		if(isVerifyed){
			this.templateTitle = header[0];
			this.templateDiscription = header[1];
			this.templateFormate = header[2];
		}
	}
	
	public void add(String[] header, String content){
		if(isVerifyed){
			contentData.add(new String[]{ header[0], header[1], content });
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
	
	/** @return contentData */
	public String[] getContentData(){
		return Arrays.copyOf(contentData.toArray(), contentData.size(), String[].class);
	}
}
