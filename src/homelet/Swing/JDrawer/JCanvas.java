/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JDrawer;

import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.GraphicsHandler;
import homelet.Utile.Utility;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author HomeletWei
 * @date Apr 11, 2018
 */
public class JCanvas extends Canvas implements Runnable, RestoreDefault, EnableListener, Render{
	
	// render list
	ArrayList<Render> preRenderList;
	ArrayList<Render> renderList;
	ArrayList<Render> postRenderList;
	private              boolean          suspend            = false;
	/** <b>serialVersionUID</b> */
	private static final long             serialVersionUID   = 1L;
	private static       int              baseSizePerUnit    = 5;
	private              Dimension        preferredDimension = new Dimension(500, 500);
	private              JCanvasAssistant assitant;
	//<!--MultiThreading-->
	private              BufferStrategy   bs;
	private              boolean          running            = false;
	private              Thread           renderThread;
	private              int              fps;
	private              Color            gray               = new Color(203, 203, 203);
	private              Color            white              = new Color(255, 255, 255);
	
	/**
	 * constructor for JCanvas
	 * TODO
	 *
	 * @param DI the canvas DI
	 * @author HomeletWei
	 */
	public JCanvas(Dimension DI){
		this.setFocusable(false);
		Utility.getUtility().setPreferredSize(this, DI);
		this.preRenderList = new ArrayList<>();
		this.renderList = new ArrayList<>();
		this.postRenderList = new ArrayList<>();
		this.addPreRenderTarget(this);
//		this.setIgnoreRepaint(true);
	}
	
	/**
	 * add a target to pre render list
	 *
	 * @param r
	 * @author HomeletWei
	 */
	public void addPreRenderTarget(Render r){
		this.preRenderList.add(r);
	}
	
	/**
	 * add a render target to render list
	 *
	 * @param r
	 * @author HomeletWei
	 */
	public void addRenderTarget(Render r){
		this.renderList.add(r);
	}
	
	/**
	 * ad a target to post render list
	 *
	 * @param r
	 * @author HomeletWei
	 */
	public void addPostRenderTarget(Render r){
		this.postRenderList.add(r);
	}
	
	public JCanvasAssistant getAssitant(){
		return assitant;
	}
	
	/**
	 * create a rendered Image
	 *
	 * @return a bufferedImage
	 * @author HomeletWei
	 */
	public BufferedImage createRenderedImage(){
		Dimension     finalDi = Utility.getUtility().getFinalDimension(preferredDimension, renderList);
		BufferedImage bg      = new BufferedImage(finalDi.width, finalDi.height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D    g       = bg.createGraphics();
		/////////////////////-draw Start-/////////////////////
		doRender(g);
		/////////////////////-draw End-//////////////////////
		g.dispose();
		bg.flush();
		return bg;
	}
	
	private void doRender(Graphics2D g){
		doRenderProcess(g, renderList);
	}
	
	void setSuspend(boolean bool){
		this.suspend = bool;
	}
	
	boolean isSuspended(){
		return suspend;
	}
	
	/**
	 * @return the canvaus bounds
	 * @author HomeletWei
	 */
	public Rectangle getCanvasBounds(){
		return this.getBounds();
	}
	
	void addAssistant(JCanvasAssistant assitant){
		this.assitant = assitant;
	}
	
	@Override
	public void run(){
		int    FPS         = 0;
		double timePerTick = 1000000000.0 / fps;
		double delta       = 0;
		long   now;
		long   lastTime;
		long   timer       = 0;
		lastTime = System.nanoTime();
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				FPS++;
				delta = 0;
				render();
			}
			if(timer >= 1000000000){
				if(assitant != null)
					assitant.updateFPS(FPS);
				FPS = 0;
				timer = 0;
			}
		}
	}
	
	private void render(){
		Dimension finalDi = Utility.getUtility().getFinalDimension(preferredDimension, renderList);
		if(!this.getSize().equals(finalDi)){
			onSizeChange(finalDi);
			return;
		}
		bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			bs = this.getBufferStrategy();
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension dimension = new Dimension(this.getSize());
		g.clearRect(0, 0, dimension.width, dimension.height);
		/////////////////////-draw Start-/////////////////////
		preRender(g);
		doRender(g);
		postRender(g);
		/////////////////////-draw End-//////////////////////
		g.dispose();
		bs.show();
	}
	
	private void onSizeChange(Dimension newDi){
		Utility.getUtility().setPreferredSize(this, newDi);
		this.repaint();
		this.getParent().revalidate();
	}
	
	private void preRender(Graphics2D g){
		if(suspend)
			return;
		doRenderProcess(g, preRenderList);
	}
	
	private void postRender(Graphics2D g){
		if(suspend)
			return;
		doRenderProcess(g, postRenderList);
	}
	
	private void doRenderProcess(Graphics2D g, ArrayList<Render> renderList){
		GraphicsHandler.getGraphicsHandler().doRenderProcess(g, renderList, this.getSize());
	}
	
	/**
	 * start the rendering ASAP
	 *
	 * @param threadName
	 * @param FPS
	 * @author HomeletWei
	 */
	public synchronized void startRendering(String threadName, int FPS){
		if(running)
			return;
		fps = FPS;
		running = true;
		renderThread = new Thread(null, this, threadName);
		renderThread.setPriority(Thread.MAX_PRIORITY);
		renderThread.start();
		System.out.println("Thread Stared");
	}
	
	/**
	 * stop the rendering ASAP
	 *
	 * @author HomeletWei
	 */
	public synchronized void stopRendering(){
		if(!running)
			return;
		running = false;
		try{
			renderThread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Thread Stoped");
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
	}
	
	@Override
	public void restoreDefault(){
	}
	
	@Override
	public void render(Graphics2D g){
		boolean drawGray;
		boolean previousLineGray = true;
		for(int y = 0; y < (g.getClipBounds().height / 2) + 1; y++){
			if(previousLineGray){
				drawGray = true;
				previousLineGray = false;
			}else{
				drawGray = false;
				previousLineGray = true;
			}
			for(int x = 0; x < (g.getClipBounds().width / 2) + 1; x++){
				if(drawGray){
					g.setColor(gray);
					drawGray = false;
				}else{
					g.setColor(white);
					drawGray = true;
				}
				g.fillRect(x * baseSizePerUnit, y * baseSizePerUnit, baseSizePerUnit, baseSizePerUnit);
			}
		}
	}
}
