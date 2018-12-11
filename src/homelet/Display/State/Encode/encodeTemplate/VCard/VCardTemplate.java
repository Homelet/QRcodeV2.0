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

import homelet.Display.State.Encode.encodeTemplate.TemplateUtility;

import java.util.Arrays;

public class VCardTemplate{
	
	String[] title;
	boolean  hasType = true;
	String[] type;
	String[] fields;
	boolean  mustHave;
	int      maxCapacity;
	int      index;
	
	/**
	 * constructor for VCardTemplate
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public VCardTemplate(String[] title, String[] type, String[] fields, boolean mustHave, int maxCapacity, int index){
		this.index = index;
		initallize(title, type, fields, mustHave, maxCapacity);
	}
	
	private void initallize(String[] title, String[] type, String[] fields, boolean mustHave, int maxCapacity){
		this.title = title;
		if(type != null && type.length != 0){
			this.type = type;
		}else{
			hasType = false;
		}
		this.fields = fields;
		this.mustHave = mustHave;
		this.maxCapacity = maxCapacity;
	}
	
	public String getTitleName(){
		return title[0];
	}
	
	public String getTypeLoadingType(){
		return hasType ? type[0] : "";
	}
	
	public String[] getTypeNameList(){
		return hasType ? TemplateUtility.getTemplateUtility().stringFiliter(TemplateUtility.getTemplateUtility().packUpStrings(getTypeList(), 2), 0) : null;
	}
	
	public String[] getTypeList(){
		return Arrays.copyOfRange(type, 1, type.length);
	}
	
	public String getFieldsLoadingType(){
		return fields[0];
	}
	
	public String[] getFieldsNameList(){
		return Arrays.copyOfRange(fields, 2, fields.length);
	}
	
	public String getFieldsDiscription(){
		return fields[1];
	}
	
	public String[] getFieldsList(){
		return Arrays.copyOfRange(fields, 1, fields.length);
	}
	
	public boolean isMustHave(){
		return mustHave;
	}
	
	public String toFormate(int typeSelected, String fieldsValue){
		return VCardTemplate.toFomate(getTitleFormateName(), getTypeFormateName(typeSelected), TemplateUtility.getTemplateUtility().stringFillter(fieldsValue));
	}
	
	public static String toFomate(String title, String type, String items){
		return title + ((type == null || type.contentEquals("*")) ? "" : ";TYPE=" + type) + ":" + items;
	}
	
	public String getTitleFormateName(){
		return title[1];
	}
	
	public String getTypeFormateName(int typeSelected){
		return hasType ? TemplateUtility.getTemplateUtility().packUpStrings(getTypeList(), 2)[typeSelected][1] : null;
	}
	
	public String toFormate(String fieldsValue){
		return VCardTemplate.toFomate(getTitleFormateName(), null, fieldsValue);
	}
	
	public String toFormate(int typeSelected, String[] fieldsValue){
		String fields = null;
		for(String s : fieldsValue)
			fields = fields == null ? TemplateUtility.getTemplateUtility().stringFillter(s) : fields + ";" + TemplateUtility.getTemplateUtility().stringFillter(s);
		return VCardTemplate.toFomate(getTitleFormateName(), getTypeFormateName(typeSelected), fields);
	}
	
	/**
	 * make sure the type is null
	 *
	 * @param fieldsValue
	 * @return
	 * @author HomeletWei
	 */
	public String toFormate(String[] fieldsValue){
		String fields = null;
		for(String s : fieldsValue)
			fields = fields == null ? TemplateUtility.getTemplateUtility().stringFillter(s) : fields + ";" + TemplateUtility.getTemplateUtility().stringFillter(s);
		return VCardTemplate.toFomate(getTitleFormateName(), null, fields);
	}
	
	/**
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public String print(){
		String str = "[" + String.valueOf(index) + "|" + String.valueOf(maxCapacity) + "|" + String.valueOf(mustHave) + "|<";
		for(String s : title){
			str += s + ",";
		}
		str += ">|(";
		if(type != null && type.length != 0){
			for(String s : type){
				str += s + ",";
			}
		}
		str += ")|{";
		for(String s : fields){
			str += s + ",";
		}
		str += "}]";
		return str;
	}
	
	/** @return title */
	public String[] getTitle(){
		return title;
	}
	
	/** @return hasType */
	public boolean isHasType(){
		return hasType;
	}
	
	/** @return type */
	public String[] getType(){
		return type;
	}
	
	/** @return fields */
	public String[] getFields(){
		return fields;
	}
	
	/** @return maxCapacity */
	public int getMaxCapacity(){
		return maxCapacity;
	}
	
	/** @return index */
	public int getIndex(){
		return index;
	}
}
