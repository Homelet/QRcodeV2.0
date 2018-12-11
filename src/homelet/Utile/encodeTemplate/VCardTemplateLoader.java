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

import homelet.Display.State.Encode.encodeTemplate.VCard.VCardSubUnit;
import homelet.Display.State.Encode.encodeTemplate.VCard.VCardTemplate;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 8, 2018
 */
public class VCardTemplateLoader extends TemplateLoader{
	
	public final String templatePrefix = "BEGIN:VCARD";
	public final String templateSuffix = "END:VCARD";
	ArrayList<VCardSubUnit> vcards;
	
	/**
	 * constructor for VCardTemplateLoader
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public VCardTemplateLoader(){
		super("HomeletQRCodeTemplate-VCard-V1.0");
		vcards = new ArrayList<>();
	}
	
	public void addNewSection(int index){
		if(isVerifyed)
			vcards.add(index, new VCardSubUnit());
	}
	
	public void add(int subSectionIndex, String[] version){
		if(isVerifyed)
			vcards.get(subSectionIndex).version(version);
	}
	
	public void add(int subSectionIndex, int index, boolean mustHave, int maxCapacity, String[] title, String[] type, String[] content){
		if(isVerifyed)
			vcards.get(subSectionIndex).loadTemplate(mustHave, maxCapacity, title, type, content, index);
	}
	
	public VCardTemplate get(int subSectionIndex, int titleSelected){
		if(isVerifyed)
			return vcards.get(subSectionIndex).getTemplate(titleSelected);
		return null;
	}
	
	public VCardSubUnit get(int subSectionIndex){
		if(isVerifyed)
			return vcards.get(subSectionIndex);
		return null;
	}
	
	/**
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public void print(){
		String str = identifyer + "\n";
		for(VCardSubUnit sb : vcards){
			str += "{" + sb.print() + "}\n";
		}
		System.out.println(str);
	}
}
