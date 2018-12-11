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
 * @date May 4, 2018
 */
package homelet.Utile;

/**
 * TODO
 *
 * @param <E> the enclosing type for matrix
 * @author HomeletWei
 * @date May 4, 2018
 */
public class Matrix<E>{
	
	protected Object[][][] matrix;
	int dimention;
	int valueLength;
	
	/**
	 * constructor for Matrix
	 * TODO
	 *
	 * @param matrixArray
	 * @author HomeletWei
	 */
	public Matrix(E[][][] matrixArray){
		this(matrixArray[0].length, matrixArray[0][0].length);
		initializeMatrix(matrixArray);
	}
	
	/**
	 * constructor for Matrix
	 * TODO
	 *
	 * @param dimention
	 * @param valueLength
	 * @author HomeletWei
	 */
	public Matrix(int dimention, int valueLength){
		this.dimention = dimention;
		this.valueLength = valueLength;
		matrix = new Object[dimention][dimention][valueLength];
	}
	
	/**
	 * TODO
	 *
	 * @param matrixArray
	 * @author HomeletWei
	 */
	public void initializeMatrix(E[][][] matrixArray){
		for(int y = 0; y < dimention; y++){
			for(int x = 0; x < dimention; x++){
				matrix[x][y] = matrixArray[x][y];
			}
		}
	}
	
	/**
	 * TODO
	 *
	 * @param x
	 * @param y
	 * @param index
	 * @param value
	 * @author HomeletWei
	 */
	public void put(int x, int y, int index, E value){
		if(!preCheck(x, y, index))
			return;
		matrix[x][y][index] = value;
	}
	
	private boolean preCheck(int x, int y, int index){
		if((x > dimention || x < 0) || (y > dimention || y < 0) || (index > valueLength || index < 0)){
			return false;
		}
		return true;
	}
	
	/**
	 * TODO
	 *
	 * @param x
	 * @param y
	 * @param value
	 * @author HomeletWei
	 */
	public void puts(int x, int y, E[] value){
		if(!preCheck(x, y, 0))
			return;
		matrix[x][y] = value;
	}
	
	/**
	 * TODO
	 *
	 * @param x
	 * @param y
	 * @param index the index must be smaller than the value length
	 * @return the value
	 * @author HomeletWei
	 */
	public E valueOf(int x, int y, int index){
		if(!preCheck(x, y, index))
			return null;
		return valuesOf(x, y)[index];
	}
	
	/**
	 * @param x
	 * @param y
	 * @return the value array
	 */
	@SuppressWarnings("unchecked")
	public E[] valuesOf(int x, int y){
		if(!preCheck(x, y, 0))
			return null;
		return (E[]) matrix[x][y];
	}
}
