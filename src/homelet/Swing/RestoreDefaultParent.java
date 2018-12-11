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
 * @date Jun 6, 2018
 */
package homelet.Swing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Jun 6, 2018
 */
public interface RestoreDefaultParent extends RestoreDefault{
	
	@Override
	default void restoreDefault(){
		restoreChildDefault();
	}
	
	public default void restoreChildDefault(){
		for(Iterator<RestoreDefault> iterator = getRestoreDefaultChildList().iterator(); iterator.hasNext(); )
			iterator.next().restoreDefault();
	}
	
	/**
	 * @return the childList
	 * @author HomeletWei
	 */
	public abstract ArrayList<RestoreDefault> getRestoreDefaultChildList();
	
	/**
	 * add a child to the child List
	 *
	 * @param child
	 * @author HomeletWei
	 */
	public default void addRestoreDefaultChild(RestoreDefault child){
		if(child == null)
			return;
		getRestoreDefaultChildList().add(child);
	}
}
