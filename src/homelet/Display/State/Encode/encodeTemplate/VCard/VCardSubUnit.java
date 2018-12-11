/**
 * @author HomeletWei
 * @date Apr 16, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Display.State.Encode.encodeTemplate.VCard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class VCardSubUnit{
	
	String                   version;
	ArrayList<VCardTemplate> templateList;
	
	/**
	 * constructor for VCardSubUnit
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public VCardSubUnit(){
		templateList = new ArrayList<>();
	}
	
	public void version(String[] version){
		if(version[0].contentEquals("VERSION")){
			this.version = version[1];
		}
	}
	
	public void loadTemplate(boolean mustHave, int maxCapacity, String[] title, String[] type, String[] content, int index){
		templateList.add(new VCardTemplate(title, type, content, mustHave, maxCapacity, index));
	}
	
	public VCardTemplate getTemplate(int titleNumber){
		return templateList.get(titleNumber);
	}
	
	public String getVerion(){
		return version;
	}
	
	public String[] getAllTitles(){
		String[] str   = new String[templateList.size()];
		int      index = 0;
		for(VCardTemplate t : templateList){
			str[index++] = t.getTitleName();
		}
		return Arrays.copyOf(str, index);
	}
	
	public String[] getTitles(Component[] cList){
		String[] str   = new String[templateList.size()];
		int      index = 0;
		for(VCardTemplate t : templateList){
			int adder = 0;
			if(t.mustHave){
				continue;
			}
			if(t.maxCapacity != 0){
				for(Component c : cList){
					if(c instanceof VCardComponent){
						if(t.index == ((VCardComponent) c).getTemplateItem().index){
							adder++;
						}
						if(adder >= t.maxCapacity){
							break;
						}
					}
				}
			}
			if(t.maxCapacity == 0 || adder < t.maxCapacity){
				str[index++] = t.getTitleName();
			}
		}
		return Arrays.copyOf(str, index);
	}
	
	public int[] getMustHaves(){
		int[] str   = new int[templateList.size()];
		int   index = 0;
		for(VCardTemplate t : templateList){
			if(t.mustHave){
				str[index++] = t.index;
			}
		}
		return Arrays.copyOf(str, index);
	}
	
	public String print(){
		String str = version + "\n";
		for(VCardTemplate t : templateList){
			str += t.print() + "\n";
		}
		return str;
	}
}
