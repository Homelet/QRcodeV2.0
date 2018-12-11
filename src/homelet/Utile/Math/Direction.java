/**
 * <pre>
 * ****************************************************
 * Name: TODO
 * Date: TODO
 * Description: TODO
 * *****************************************************
 * </pre>
 *
 * @author HomeletWei
 * @date Jun 11, 2018
 */
package homelet.Utile.Math;

/**
 * @author HomeletWei
 * @date Jun 11, 2018
 */
public class Direction{
	
	ValueType type;
	double    value;
	
	/**
	 * constructor for Direction
	 *
	 * @param type
	 * @param value
	 * @author HomeletWei
	 */
	public Direction(ValueType type, double value){
		this.value = value;
		this.type = type;
	}
	
	/**
	 * constructor for Direction
	 *
	 * @param type
	 * @param value
	 * @author HomeletWei
	 */
	public Direction(String type, double value){
		this.value = value;
		this.type = ValueType.typeOf(type);
	}
	
	/**
	 * constructor for Direction
	 *
	 * @author HomeletWei
	 */
	public Direction(){
	}
	
	/**
	 * @return the value as degrees
	 * @author HomeletWei
	 */
	public double getDegrees(){
		switch(type){
			case DEGREES:
				return value;
			case RADIENS:
				return Math.toDegrees(value);
			default:
				return 0.d;
		}
	}
	
	/**
	 * @return the value as degrees
	 * @author HomeletWei
	 */
	public double getRadiens(){
		switch(type){
			case DEGREES:
				return Math.toRadians(value);
			case RADIENS:
				return value;
			default:
				return 0.d;
		}
	}
	
	/** @return type */
	public ValueType getType(){
		return type;
	}
	
	/** @param type : type */
	public void setType(String type){
		this.type = ValueType.typeOf(type);
	}
	
	/** @param type : type */
	public void setType(ValueType type){
		this.type = type;
	}
	
	/** @return value */
	public double getValue(){
		return value;
	}
	
	/** @param value : value */
	public void setValue(double value){
		this.value = value;
	}
	
	/**
	 * @author HomeletWei
	 * @date Jun 11, 2018
	 */
	public enum ValueType{
		/** @Fields <b>DEGREE</b> */
		DEGREES("degrees"),
		/** @Fields <b>RADIENT</b> */
		RADIENS("radiens");
		String id;
		
		private ValueType(String identifter){
			this.id = identifter;
		}
		
		/**
		 * @param value
		 * @return a value type
		 * @author HomeletWei
		 */
		public static ValueType typeOf(String value){
			value = value.toLowerCase();
			for(ValueType type : ValueType.values()){
				if(value.contentEquals(type.id))
					return type;
			}
			return null;
		}
	}
}
