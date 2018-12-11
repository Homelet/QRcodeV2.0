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
 * @date May 13, 2018
 */
package homelet.Display.State.Encode.encodeTemplate;

import homelet.Swing.JBasic.JBasePanel;
import homelet.Swing.JBasic.JTextButton;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.FlowLayouter;
import homelet.Utile.Layouter.FlowLayouter.FlowAlignment;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author HomeletWei
 * @date May 13, 2018
 */
public abstract class TemplatePanel extends JBasePanel{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long           serialVersionUID = 1L;
	protected            JPanel         itemsList;
	protected            JScrollPane    scroll;
	protected            JPanel         controlBar;
	protected            TemplateDialog parent;
	protected            FlowLayouter   layouter;
	private              JTextButton    apply;
	private              boolean        isReady;
	
	/**
	 * constructor for TemplatePanel
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public TemplatePanel(TemplateDialog parent){
		this.parent = parent;
		this.createDisplay();
	}
	
	/**
	 * TODO
	 *
	 * @author HomeletWei
	 */
	protected void createDisplay(){
		JTextButton add = new JTextButton("Add", add());
		this.itemsList = new JPanel();
		layouter = new FlowLayouter(itemsList, FlowAlignment.TOP, 10, 10, false, false);
		scroll = new JScrollPane(itemsList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Utility.getUtility().setPreferredSize(scroll, Utility.getUtility().getScrollPanelDI(Sizing.EncodeTemplate.itemList));
		controlBar = new JPanel(new FlowLayout());
		apply = new JTextButton("Apply", apply());
		controlBar.add(new JTextButton("Clear All", (ActionEvent e)->{
			restoreDefault();
		}));
		controlBar.add(apply);
		controlBar.add(new JTextButton("Start New", startNew()));
		Layouter.GridBagLayouter layout = new GridBagLayouter(this);
		layout.put(layout.instanceOf(add, 0, 1).setAnchor(Anchor.TOP).setFill(Fill.BOTH).setWeight(100, 0));
		layout.put(layout.instanceOf(scroll, 0, 2).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(controlBar, 0, 3).setAnchor(Anchor.BOTTOM).setFill(Fill.BOTH).setWeight(100, 0));
	}
	
	protected abstract ActionListener add();
	
	protected abstract ActionListener apply();
	
	protected abstract ActionListener startNew();
	
	protected void addTemplateComponent(TemplateComponent comp){
		layouter.add(comp);
		revalidateItemList();
		scrollToBottom();
	}
	
	protected void revalidateItemList(){
		itemsList.revalidate();
		itemsList.repaint();
	}
	
	protected void scrollToBottom(){
		SwingUtilities.invokeLater(()->{
			scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
		});
	}
	
	protected void removeTemplateItem(TemplateComponent comp){
		layouter.remove(comp);
		revalidateItemList();
	}
	
	protected void removeAllTemplateItem(){
		layouter.removeAll();
		revalidateItemList();
	}
	
	/**
	 * the result of this panel
	 *
	 * @return result
	 * @author HomeletWei
	 */
	public abstract String generateForm();
	
	/**
	 * TODO
	 *
	 * @return
	 * @author HomeletWei
	 */
	public boolean isReadyForDisplaying(){
		return isReady;
	}
	
	/**
	 * TODO
	 *
	 * @param ready
	 * @author HomeletWei
	 */
	public void setReady(boolean ready){
		this.isReady = ready;
	}
	
	/**
	 * @return boolean that indicate whether the initialization is success
	 * @author HomeletWei
	 */
	public abstract boolean initialize();
	
	protected abstract String getRepresentString();
}
