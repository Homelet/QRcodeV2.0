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
package homelet.Swing;

/**
 * a to listen the enable change event
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public interface EnableListener{
	
	/**
	 * this listener is called when the components enable states changed
	 *
	 * @param newEnable
	 * @author HomeletWei
	 */
	public abstract void onEnableChange(boolean newEnable);
}
