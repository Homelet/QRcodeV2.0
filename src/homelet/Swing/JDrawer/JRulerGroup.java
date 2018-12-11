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
package homelet.Swing.JDrawer;

import homelet.Swing.JDrawer.JRuler.UnitsOfMesurement;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Jun 11, 2018
 */
public class JRulerGroup{
	
	JRuler[] rulers;
	
	/**
	 * constructor for JRulerGroup
	 *
	 * @param top
	 * @param left
	 * @param right
	 * @param bottom
	 * @author HomeletWei
	 */
	public JRulerGroup(JRuler top, JRuler left, JRuler right, JRuler bottom){
		rulers = new JRuler[]{ top, bottom, left, right };
	}
	
	/**
	 * @return a mesurment
	 * @author HomeletWei
	 */
	public UnitsOfMesurement nextMesurement(){
		for(JRuler r : rulers){
			r.nextMesurement();
		}
		return rulers[0].getUnitOfMesurement();
	}
	
	/**
	 * @param xStart
	 * @param yStart
	 * @param xEnd
	 * @param yEnd
	 * @author HomeletWei
	 */
	public void drawRegion(int xStart, int yStart, int xEnd, int yEnd){
		if(rulers[0] != null)
			rulers[0].setRegion(xStart, xEnd);
		if(rulers[1] != null)
			rulers[1].setRegion(xStart, xEnd);
		if(rulers[2] != null)
			rulers[2].setRegion(yStart, yEnd);
		if(rulers[3] != null)
			rulers[3].setRegion(yStart, yEnd);
	}
	
	/**
	 * TODO
	 *
	 * @param xStart
	 * @param yStart
	 * @author HomeletWei
	 */
	public void drawStart(int xStart, int yStart){
		if(rulers[0] != null)
			rulers[0].setStartPosition(xStart);
		if(rulers[1] != null)
			rulers[1].setStartPosition(xStart);
		if(rulers[2] != null)
			rulers[2].setStartPosition(yStart);
		if(rulers[3] != null)
			rulers[3].setStartPosition(yStart);
	}
	
	/**
	 * TODO
	 *
	 * @param xEnd
	 * @param yEnd
	 * @author HomeletWei
	 */
	public void drawEnd(int xEnd, int yEnd){
		if(rulers[0] != null)
			rulers[0].setEndPosition(xEnd);
		if(rulers[1] != null)
			rulers[1].setEndPosition(xEnd);
		if(rulers[2] != null)
			rulers[2].setEndPosition(yEnd);
		if(rulers[3] != null)
			rulers[3].setEndPosition(yEnd);
	}
}
