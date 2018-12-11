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
 * @date Jun 10, 2018
 */
package homelet.Swing.JDrawer;

import homelet.Swing.EnableListener;
import homelet.Swing.EnableListenerParent;
import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Swing.JDrawer.JRuler.RulerApperance;
import homelet.Swing.JDrawer.JRuler.UnitsOfMesurement;
import homelet.Swing.RestoreDefault;
import homelet.Swing.RestoreDefaultParent;
import homelet.Utile.ColorBank;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.GraphicsHandler;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Jun 10, 2018
 */
public class JCanvasAssistant extends JPanel implements RestoreDefaultParent, EnableListenerParent, RenderTarget{
	
	private static final long serialVersionUID = 1L;
	JCanvas           parent;
	LayoutDrawer      layoutDrawer;
	CursorSupporter   cursorSupporter;
	UnitsOfMesurement inital           = UnitsOfMesurement.CENTIMETER;
	JRulerGroup       group;
	JTextLabel        fps;
	int               defaultThinkness = 60;
	int               startX           = -1;
	int               startY           = -1;
	int               endX             = -1;
	int               endY             = -1;
	Color             innerColor       = new Color(255, 102, 51, 55);
	
	/**
	 * constructor for JCanvasAssistant
	 *
	 * @param parent
	 * @author HomeletWei
	 */
	public JCanvasAssistant(JCanvas parent){
		this.parent = parent;
		parent.addAssistant(this);
		parent.addPostRenderTarget(this);
		createDisplay();
		addAction();
	}
	
	private void createDisplay(){
		layoutDrawer = new LayoutDrawer(parent);
		parent.addPostRenderTarget(layoutDrawer);
		cursorSupporter = new CursorSupporter(parent);
		parent.addPostRenderTarget(cursorSupporter);
		Dimension                connerPiece = new Dimension(defaultThinkness, defaultThinkness);
		Layouter.GridBagLayouter layout      = new GridBagLayouter(this);
		JRuler                   top         = new JRuler(inital, RulerApperance.INNER);
		Utility.getUtility().setPreferredSize(top, parent.getWidth(), defaultThinkness);
		JRuler left = new JRuler(inital, RulerApperance.INNER);
		Utility.getUtility().setPreferredSize(left, defaultThinkness, parent.getHeight());
		JRuler right = new JRuler(inital, RulerApperance.OUTER);
		Utility.getUtility().setPreferredSize(right, left.getPreferredSize());
		JRuler buttom = new JRuler(inital, RulerApperance.OUTER);
		Utility.getUtility().setPreferredSize(buttom, top.getPreferredSize());
		group = new JRulerGroup(top, left, right, buttom);
		JTextButton UFM = new JTextButton(inital.unit);
		Utility.getUtility().setPreferredSize(UFM, connerPiece);
		UFM.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				UFM.setText(group.nextMesurement().unit);
			}
		});
		fps = new JTextLabel("--", Alignment.CENTER, Alignment.CENTER, 0, null, true);
		Utility.getUtility().setPreferredSize(fps, connerPiece);
		JTextButton showGrid = new JTextButton("Grid");
		showGrid.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				super.mousePressed(e);
				parent.setSuspend(true);
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				super.mouseReleased(e);
				parent.setSuspend(false);
			}
		});
		Utility.getUtility().setPreferredSize(showGrid, connerPiece);
		JTextButton showLayout = new JTextButton("Layout");
		showLayout.addActionListener((ActionEvent e)->{
			layoutDrawer.filpDoLayout();
		});
		Utility.getUtility().setPreferredSize(showLayout, connerPiece);
		layout.put(layout.instanceOf(UFM, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(top, 1, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(fps, 2, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(left, 0, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(parent, 1, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(right, 2, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(showLayout, 0, 2).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(buttom, 1, 2).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(showGrid, 2, 2).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
	}
	
	// pressing util
	boolean mousePressing = false;
	int     mousePressStartX;
	int     mousePressStartY;
	int     mousePressingX;
	int     mousePressingY;
	
	private void addAction(){
		parent.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2){
					startX = -1;
					startY = -1;
					endX = -1;
					endY = -1;
					group.drawRegion(-1, -1, -1, -1);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				mousePressing = true;
				mousePressStartX = e.getX();
				mousePressStartY = e.getY();
				startX = check(mousePressStartX, 0, parent.getWidth());
				startY = check(mousePressStartY, 0, parent.getHeight());
				group.drawStart(startX, startY);
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				endX = check(e.getX(), 0, parent.getWidth());
				endY = check(e.getY(), 0, parent.getHeight());
				group.drawEnd(endX, endY);
				mousePressing = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				parent.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e){
				parent.setCursor(Cursor.getDefaultCursor());
			}
		});
		parent.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e){
				mousePressingX = e.getX();
				mousePressingY = e.getY();
				endX = check(mousePressingX, 0, parent.getWidth());
				endY = check(mousePressingY, 0, parent.getHeight());
				group.drawEnd(endX, endY);
			}
			
			@Override
			public void mouseMoved(MouseEvent e){}
		});
	}
	
	boolean isPressing(){
		return mousePressing;
	}
	
	int getMousePressStartX(){
		return mousePressStartX;
	}
	
	int getMousePressStartY(){
		return mousePressStartY;
	}
	
	public int getMousePressingX(){
		return mousePressingX;
	}
	
	public int getMousePressingY(){
		return mousePressingY;
	}
	
	private int check(int value, int min, int max){
		if(value < min){
			return 0;
		}else if(value > max){
			return max;
		}else{
			return value;
		}
	}
	
	void updateFPS(int fps){
		this.fps.setText(String.valueOf(fps));
	}
	
	/**
	 * @return the canvaus
	 * @author HomeletWei
	 */
	public JCanvas getCanvas(){
		return parent;
	}
	
	@Override
	public ArrayList<EnableListener> getEnableChildList(){
		return null;
	}
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return null;
	}
	
	@Override
	public Dimension getDimension(){
		return null;
	}
	
	@Override
	public Point getVertex(Dimension frameDimension, Dimension objectDimension){
		return null;
	}
	
	@Override
	public void render(Graphics2D g){
		g.setColor(innerColor);
		if(startX != -1 && startY != -1 && endX != -1 && endY != -1){
			GraphicsHandler.getGraphicsHandler().fillRect(g, startX, startY, endX, endY);
		}
	}
}

class LayoutDrawer implements Render{
	
	JCanvas   parent;
	boolean   doLayout = false;
	ColorBank bank;
	
	public LayoutDrawer(JCanvas parent){
		this.parent = parent;
		bank = new ColorBank();
	}
	
	boolean isDoLayout(){
		return doLayout;
	}
	
	void filpDoLayout(){
		this.doLayout = !doLayout;
	}
	
	@Override
	public void render(Graphics2D g){
		if(!doLayout)
			return;
		for(Iterator<Render> iterator = parent.renderList.iterator(); iterator.hasNext(); ){
			Render    renderTarget = iterator.next();
			Dimension objDi        = null;
			if(renderTarget instanceof RenderTarget)
				objDi = ((RenderTarget) renderTarget).getDimension();
			if(objDi == null)
				objDi = parent.getSize();
			Point point = null;
			if(renderTarget instanceof RenderTarget)
				point = ((RenderTarget) renderTarget).getVertex(parent.getSize(), objDi);
			if(point == null)
				point = new Point(0, 0);
			Rectangle rect = new Rectangle(point, objDi);
			g.setColor(Color.BLUE);
			g.drawLine(0, rect.y, parent.getWidth(), rect.y);
			g.drawLine(rect.x, 0, rect.x, parent.getHeight());
			g.drawLine(rect.x + rect.width, 0, rect.x + rect.width, parent.getHeight());
			g.drawLine(0, rect.y + rect.height, parent.getWidth(), rect.y + rect.height);
		}
	}
}

class CursorSupporter implements Render{
	
	JCanvasAssistant parent;
	
	public CursorSupporter(JCanvas parent){
		this.parent = parent.getAssitant();
	}
	
	@Override
	public void render(Graphics2D g){
		if(parent.isPressing()){
			g.setColor(Color.RED);
			Rectangle bound = g.getClipBounds();
			// horizontalStartLine
			GraphicsHandler.getGraphicsHandler().drawLine(g, 0, parent.getMousePressStartY(), bound.width, 0);
			// verticalStartLine
			GraphicsHandler.getGraphicsHandler().drawLine(g, parent.getMousePressStartX(), 0, 0, bound.height);
			// horizontalPressingLine
			GraphicsHandler.getGraphicsHandler().drawLine(g, 0, parent.getMousePressingY(), bound.width, 0);
			// verticalPressingLine
			GraphicsHandler.getGraphicsHandler().drawLine(g, parent.getMousePressingX(), 0, 0, bound.height);
		}
	}
}