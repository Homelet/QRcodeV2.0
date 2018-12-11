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
public interface EnableListenerParent extends EnableListener{
	
	@Override
	default void onEnableChange(boolean newEnable){
		onChildEnableChange(newEnable);
	}
	
	default void onChildEnableChange(boolean newEnable){
		for(Iterator<EnableListener> iterator = getEnableChildList().iterator(); iterator.hasNext(); )
			iterator.next().onEnableChange(newEnable);
	}
	
	/**
	 * @return the childList
	 * @author HomeletWei
	 */
	ArrayList<EnableListener> getEnableChildList();
	
	/**
	 * add a child to the child List
	 *
	 * @param child
	 * @param enable the configrate
	 * @author HomeletWei
	 */
	default void addEnableChild(EnableListener child, boolean enable){
		if(child == null)
			return;
		child.onEnableChange(enable);
		getEnableChildList().add(child);
	}
}
