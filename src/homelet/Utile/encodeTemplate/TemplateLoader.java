/**
 * @author HomeletWei
 * @date Apr 8, 2018
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
 * @date Apr 8, 2018
 */
public class TemplateLoader{
	
	protected final String  identifyer;
	protected       boolean isVerifyed;
	
	public TemplateLoader(String identifyer){
		this.identifyer = identifyer;
	}
	
	public boolean verifyTemplate(String templateIdentifyer){
		isVerifyed = identifyer.contentEquals(templateIdentifyer);
		return isVerifyed;
	}
}

