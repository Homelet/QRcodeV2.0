/**
 * @author HomeletWei
 * @date Apr 17, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Utile;

import homelet.Utile.Math.Coefficient;
import homelet.Utile.Math.Function;
import homelet.Utile.Math.Relation;
import homelet.Utile.Math.RelationFunction;

/**
 * the class for creating resvered arc maxtrix
 *
 * @author HomeletWei
 * @date Apr 17, 2018
 */
public class ReverseArc{
	
	private static ReverseArc reverseArc = new ReverseArc();
	
	private ReverseArc(){
	}
	
	public static ReverseArc getReverseArc(){
		return reverseArc;
	}
	
	/**
	 * <!--//////////////////////////////////////////////////////////////////////////-->
	 * difference between real word function implementation and computer<br>
	 * <hr>
	 * in real word the function is as follow
	 * <ul>
	 * <li>from -x infinity to +x infinity</li>
	 * <li>from -y infinity to +y infinity</li>
	 * </ul>
	 *
	 * <pre>
	 *          A
	 *          |(+y)
	 *          |
	 *          |
	 * (-x)     |
	 * ---------+---------->
	 *          |	 (+x)
	 *          |
	 *          |(-y)
	 *          |
	 * </pre>
	 * <p>
	 * but in computer, matrix is laid out like:
	 *
	 * <pre>
	 * +-------------->
	 * |(0,0)      (+x)
	 * |
	 * |
	 * |
	 * |
	 * |(+y)
	 * V
	 * </pre>
	 * <p>
	 * so the resultant will looks as follow
	 * <ul>
	 * <li>from -x infinity to +x infinity</li>
	 * <li>from +y infinity to -y infinity</li>
	 * </ul>
	 *
	 * <pre>
	 * +--------A-------->
	 * |   (-x) |      (+x)
	 * |        |
	 * |(+y)    |
	 * |        |
	 * |        |
	 * |        |
	 * ---------+--------->
	 * |        |
	 * |        |
	 * |(-y)    |
	 * |        |
	 * |        |
	 * V        |
	 * </pre>
	 * <hr>
	 * For every reveredArcMatrix, there is option that leave a certain part of the matrix empty
	 * <ul>
	 * <li>Include Middle</li>
	 * <ul>
	 * <li>if true, Middle part will be shown, otherwise, the area restrict to it wouldn't be shown</li>
	 * </ul>
	 * <li>Include Boarder</li>
	 * <ul>
	 * <li>if true, Boarder will be shown, otherwise, the area restrict to it wouldn't be shown</li>
	 * </ul>
	 * <li>Include Top Right</li>
	 * <ul>
	 * <li>if true, Top Right part will be shown, otherwise, the area restrict to it wouldn't be shown</li>
	 * </ul>
	 * <li>Include Top Left</li>
	 * <ul>
	 * <li>if true, Top Left part will be shown, otherwise, the area restrict to it wouldn't be shown</li>
	 * </ul>
	 * <li>Include Bottom Right</li>
	 * <ul>
	 * <li>if true, Bottom Right part will be shown, otherwise, the area restrict to it wouldn't be shown</li>
	 * </ul>
	 * <li>Include Bottom Left</li>
	 * <ul>
	 * <li>if true, Bottom Left part will be shown, otherwise, the area restrict to it wouldn't be shown</li>
	 * </ul>
	 * </ul>
	 * -> the Diagram(D1) shows how we decomposing for Odd Dimension
	 * <table cellspacing="0" frame="void" rules="all" cellpadding="0">
	 * <caption>(D1) Diagram for Odd Dimension (DI = 7)</caption>
	 * <tbody align="center" vAlign="center">
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-3</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-2</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-1</td>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">0</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">2</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">3</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">3</td>
	 * <td width="40px" height="40px" bgColor="#357D21" colspan="3" rowspan="3">Top Right</td>
	 * <td width="40px" height="40px" bgColor="#3E7126" rowspan="3">+Y</td>
	 * <td width="40px" height="40px" bgColor="#0018F5" colspan="3" rowspan="3">Top Left</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">0</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">2</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">2</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">0</td>
	 * <td width="40px" height="40px" bgColor="#CA9E59" colspan="3">-X</td>
	 * <td width="40px" height="40px" bgColor="red">Center Piece</td>
	 * <td width="40px" height="40px" bgColor="#0D1DC2" colspan="3">+X</td>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">3</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-1</td>
	 * <td width="40px" height="40px" bgColor="#F4A93C" colspan="3" rowspan="3">Bottom Left</td>
	 * <td width="40px" height="40px" bgColor="#D6D673" rowspan="3">-Y</td>
	 * <td width="40px" height="40px" bgColor="#FFFE55" colspan="3" rowspan="3">Bottom Right</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">4</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-2</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">5</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-3</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">6</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">0</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">2</td>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">3</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">4</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">5</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">6</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * </tr>
	 * </tbody>
	 * </table>
	 * -> the Diagram(D2) shows how we decomposing for Even Dimension
	 * <table cellspacing="0" frame="void" rules="all" cellpadding="0">
	 * <caption>(D2) Diagram for Even Dimension (DI = 6)</caption>
	 * <tbody align="center" vAlign="center">
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-2</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-1</td>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">0</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">2</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">3</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">2</td>
	 * <td width="40px" height="40px" bgColor="#357D21" colspan="2" rowspan="2">Top Left</td>
	 * <td width="40px" height="40px" bgColor="#3E7126" rowspan="2">+Y</td>
	 * <td width="40px" height="40px" bgColor="#357D21" rowspan="2">/ / / / / / / / / / / / Padded / / / / / / / / / / / /</td>
	 * <td width="40px" height="40px" bgColor="#0018F5" colspan="2" rowspan="2">Top Right</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">0</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">0</td>
	 * <td width="40px" height="40px" bgColor="#F4A93C" colspan="2">/ / / / / / / / / / / / / / / Padded / / / / / / / / / / / / / / /</td>
	 * <td width="40px" height="40px" bgColor="red" colspan="2" rowspan="2">Center Piece</td>
	 * <td width="40px" height="40px" bgColor="#0D1DC2" colspan="2">+X</td>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">2</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">-1</td>
	 * <td width="40px" height="40px" bgColor="#CA9E59" colspan="2">-X</td>
	 * <td width="40px" height="40px" bgColor="#0018F5" colspan="2">/ / / / / / / / / / / / / / / Padded / / / / / / / / / / / / / / /</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">3</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-2</td>
	 * <td width="40px" height="40px" bgColor="#F4A93C" colspan="2" rowspan="2">Bottom Left</td>
	 * <td width="40px" height="40px" bgColor="#FFFE55" rowspan="2">/ / / / / / / / / / / / Padded / / / / / / / / / / / /</td>
	 * <td width="40px" height="40px" bgColor="#D6D673" rowspan="2">-Y</td>
	 * <td width="40px" height="40px" bgColor="#FFFE55" colspan="2" rowspan="2">Bottom Right</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">4</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">-3</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">5</td>
	 * </tr>
	 * <tr>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">0</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">1</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">2</td>
	 * <td width="40px" height="40px" bgColor="#F2F2F2">3</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">4</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF">5</td>
	 * <td width="40px" height="40px" bgColor="#FFFFFF"></td>
	 * </tr>
	 * </tbody>
	 * </table>
	 * <table frame="box" rules="rows">
	 * <caption>Legend</caption>
	 * <tr>
	 * <td>Top Left</td>
	 * <td width="10px" height="10px" bgColor="#357D21"></td>
	 * <td width="10px" height="10px" bgColor="#3E7126"></td>
	 * </tr>
	 * <td>Top Right</td>
	 * <td width="10px" height="10px" bgColor="#0018F5"></td>
	 * <td width="10px" height="10px" bgColor="#0D1DC2"></td>
	 * </tr>
	 * <tr>
	 * <tr>
	 * <td>Bottom Right</td>
	 * <td width="10px" height="10px" bgColor="#FFFE55"></td>
	 * <td width="10px" height="10px" bgColor="#D6D673"></td>
	 * </tr>
	 * <tr>
	 * <td>BottomLeft</td>
	 * <td width="10px" height="10px" bgColor="#F4A93C"></td>
	 * <td width="10px" height="10px" bgColor="#CA9E59"></td>
	 * </tr>
	 * </table>
	 *
	 * @param DI                 the dimension, in this case width == height
	 * @param curvature          the percentage of curvature 100 full Curvature, 0 no Curvature
	 * @param includeBoarder     is include the boarder piece
	 * @param includeMiddle      is include the middle piece
	 * @param includeTopLeft     is include the top_left piece
	 * @param includeTopRight    is include the top_right piece
	 * @param includeButtomLeft  is include the buttom_left piece
	 * @param includeButtomRight is include the buttom_right piece
	 * @return an matrix that meets desired situation
	 * @author HomeletWei
	 */
	public boolean[][] getReverseArcMatrix(int DI, int curvature, boolean includeBoarder, boolean includeMiddle, boolean includeTopLeft, boolean includeTopRight, boolean includeButtomLeft, boolean includeButtomRight){
		int newCurvature = getDesiredCurvature(curvature, DI);
		// -2 is the border, thus the border could be displayed,
		// since display cannot consist half pixel. Thus, the new radius might be one pixel off (up one pixel)
		int radius = ((DI) - 1) / 2 + newCurvature;
		RelationFunction arc = new RelationFunction(new Relation(){
			@Override
			public double[] equate(double x, Coefficient coefficient){
				double absV = Math.sqrt((Math.pow(coefficient.get(0), 2)) - (Math.pow((x), 2)));
				return new double[]{ absV, -absV };
			}
		}, new Coefficient(radius));
		switch(DI % 2){
			case 0:
				return doEvenMatrix(arc, DI, radius, newCurvature, includeBoarder, includeMiddle, includeTopLeft, includeTopRight, includeButtomLeft, includeButtomRight);
			case 1:
				return doOddMatrix(arc, DI, radius, newCurvature, includeBoarder, includeMiddle, includeTopLeft, includeTopRight, includeButtomLeft, includeButtomRight);
			default:
				return null;
		}
	}
	
	private int getDesiredCurvature(int persentage, int diameter){
		return (int) (Integer.valueOf(getMaxCurvature(diameter)).floatValue() / 100 * (100 - persentage));
	}
	
	/**
	 * the algorithm to the even matrix
	 *
	 * @author HomeletWei
	 */
	private boolean[][] doEvenMatrix(RelationFunction arc, int DI, int radius, int arcArrangement, boolean includeBoarder, boolean includeMiddle, boolean includeTopLeft, boolean includeTopRight, boolean includeButtomLeft, boolean includeButtomRight){
		boolean[][] matrix = new boolean[DI][DI];
		for(int y = radius; y >= -radius - 1; y--){
			int realY = Function.getRelativeY(y, (radius - arcArrangement));
			if(realY < 0 || realY >= DI)
				continue;
			for(int x = -radius; x <= radius + 1; x++){
				int realX = Function.getRelativeX(x, (radius - arcArrangement));
				if(realX < 0 || realX >= DI)
					continue;
				if(x <= 1 && y > 0){
					if(x == 1){
						if(y == radius){
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}else{
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}
						continue;
					}
					int graphX = x;
					int graphY = y;
					process_inner_1(arc, includeBoarder, includeMiddle, includeTopLeft, matrix, realY, realX, graphX, graphY);
				}else if(x > 1 && y >= -1){
					if(y == -1){
						if(x == radius + 1){
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}else{
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}
						continue;
					}
					int graphX = x - 1;
					int graphY = y;
					process_inner_1(arc, includeBoarder, includeMiddle, includeTopRight, matrix, realY, realX, graphX, graphY);
				}else if(x >= 0 && y < -1){
					if(x == 0){
						if(y == -radius - 1){
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}else{
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}
						continue;
					}
					int graphX = x - 1;
					int graphY = y + 1;
					process_inner_2(arc, includeBoarder, includeMiddle, includeButtomRight, matrix, realY, realX, graphX, graphY);
				}else if(x < 0 && y <= 0){
					if(y == 0){
						if(x == -radius){
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}else{
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
						}
						continue;
					}
					int graphX = x;
					int graphY = y + 1;
					process_inner_2(arc, includeBoarder, includeMiddle, includeButtomLeft, matrix, realY, realX, graphX, graphY);
				}else{
					if(includeMiddle)
						matrix[realX][realY] = true;
					else
						matrix[realX][realY] = false;
				}
			}
		}
		return matrix;
	}
	
	/**
	 * the algorithm to the odd matrix
	 *
	 * @author HomeletWei
	 */
	private boolean[][] doOddMatrix(RelationFunction arc, int DI, int radius, int arcArrangement, boolean includeBoarder, boolean includeMiddle, boolean includeTopLeft, boolean includeTopRight, boolean includeButtomLeft, boolean includeButtomRight){
		boolean[][] matrix = new boolean[DI][DI];
		for(int y = radius; y >= -radius; y--){
			int realY = Function.getRelativeY(y, (radius - arcArrangement));
			if(realY < 0 || realY >= DI)
				continue;
			for(int x = -radius; x <= radius; x++){
				int realX = Function.getRelativeX(x, (radius - arcArrangement));
				if(realX < 0 || realX >= DI)
					continue;
				if(x <= 0 && y > 0){
					int graphX = x;
					int graphY = y;
					switch(isInsideArc(arc, graphX, graphY)){
						case 2:
							if(includeTopLeft)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case -1://$FALL-THROUGH$
						case 1:
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case 0:
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case -2://$FALL-THROUGH$
						default:
							System.out.println("odd - 1");
							matrix[realX][realY] = false;
							break;
					}
				}else if(x > 0 && y >= 0){
					int graphX = x;
					int graphY = y;
					switch(isInsideArc(arc, graphX, graphY)){
						case 2:
							if(includeTopRight)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case -1://$FALL-THROUGH$
						case 1:
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case 0:
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case -2://$FALL-THROUGH$
						default:
							System.out.println("odd - 2");
							matrix[realX][realY] = false;
							break;
					}
				}else if(x >= 0 && y < 0){
					int graphX = x;
					int graphY = y;
					switch(isInsideArc(arc, graphX, graphY)){
						case 2://$FALL-THROUGH$
						default:
							System.out.println("odd - 3");
							matrix[realX][realY] = false;
							break;
						case 0:
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case 1://$FALL-THROUGH$
						case -1:
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case -2:
							if(includeButtomRight)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
					}
				}else if(x < 0 && y <= 0){
					int graphX = x;
					int graphY = y;
					switch(isInsideArc(arc, graphX, graphY)){
						case 2://$FALL-THROUGH$
						default:
							System.out.println("odd - 4");
							matrix[realX][realY] = false;
							break;
						case 0:
							if(includeMiddle)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case 1://$FALL-THROUGH$
						case -1:
							if(includeBoarder)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
						case -2:
							if(includeButtomLeft)
								matrix[realX][realY] = true;
							else
								matrix[realX][realY] = false;
							break;
					}
				}else{
					if(includeMiddle)
						matrix[realX][realY] = true;
					else
						matrix[realX][realY] = false;
				}
			}
		}
		return matrix;
	}
	
	/**
	 * the algorithm is that according to a Math Law
	 *
	 * <pre>
	 * +
	 * |-
	 * | -
	 * |  -
	 * |   -
	 * |(h) -
	 * +-----+(45)
	 *   (h)
	 * </pre>
	 * <p>
	 * in 45 degree triangle, "h" is always equals to it's the half of the 1/2 power of the longest(opposite) side times the half of the longest(opposite) side
	 *
	 * @param diameter diameter of the circle
	 * @return the maxCurature that a shape can every get to
	 * @author HomeletWei
	 */
	public int getMaxCurvature(int diameter){
		return (int) ((Double.valueOf(diameter) - Math.pow((diameter * (diameter / 2)) / 2, 0.5)) / 2);
	}
	
	private void process_inner_1(RelationFunction arc, boolean includeBoarder, boolean includeMiddle, boolean includeTopLeft, boolean[][] matrix, int realY, int realX, int graphX, int graphY){
		switch(isInsideArc(arc, graphX, graphY)){
			case 2:
				if(includeTopLeft)
					matrix[realX][realY] = true;
				else
					matrix[realX][realY] = false;
				break;
			case -1://$FALL-THROUGH$
			case 1:
				if(includeBoarder)
					matrix[realX][realY] = true;
				else
					matrix[realX][realY] = false;
				break;
			case 0:
				if(includeMiddle)
					matrix[realX][realY] = true;
				else
					matrix[realX][realY] = false;
				break;
			case -2://$FALL-THROUGH$
			default:
				matrix[realX][realY] = false;
				break;
		}
	}
	
	private void process_inner_2(RelationFunction arc, boolean includeBoarder, boolean includeMiddle, boolean includeButtomRight, boolean[][] matrix, int realY, int realX, int graphX, int graphY){
		switch(isInsideArc(arc, graphX, graphY)){
			case 2://$FALL-THROUGH$
			default:
				matrix[realX][realY] = false;
				break;
			case 0:
				if(includeMiddle)
					matrix[realX][realY] = true;
				else
					matrix[realX][realY] = false;
				break;
			case 1://$FALL-THROUGH$
			case -1:
				if(includeBoarder)
					matrix[realX][realY] = true;
				else
					matrix[realX][realY] = false;
				break;
			case -2:
				if(includeButtomRight)
					matrix[realX][realY] = true;
				else
					matrix[realX][realY] = false;
				break;
		}
	}
	
	private int isInsideArc(RelationFunction arcFunction, int x, int y){
		int upperLimit = (int) (arcFunction.doEquate(x)[0]);
		int lowerLimit = (int) (arcFunction.doEquate(x)[1]);
		return Utility.getUtility().between(y, upperLimit, lowerLimit);
	}
}
