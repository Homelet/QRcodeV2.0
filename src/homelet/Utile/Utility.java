/**
 * @author HomeletWei
 * @date Mar 26, 2018
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

import homelet.Swing.JDrawer.Render;
import homelet.Swing.JDrawer.RenderTarget;
import homelet.Utile.Math.HomeletMath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 26, 2018
 */
public class Utility{
	
	private static Utility utility = new Utility();
	
	private Utility(){}
	
	/**
	 * Get utility utility.
	 *
	 * @return the utility
	 */
	public static Utility getUtility(){
		return utility;
	}
	
	/**
	 * The constant disableColor.
	 *
	 * @Fields <b>disableColor</b> the disable Color
	 */
	public static final Color disableColor = new Color(184, 207, 229);
	/**
	 * The constant currentOS.
	 */
	public static final OS    currentOS    = OS.getCurrentWorkingOS();
	
	/**
	 * Determine the Position of the value which between two peaks
	 * <br>
	 * if know the two Comparable's large, use {@link Utility#betweenPeaks(Comparable, Comparable, Comparable)}
	 *
	 * @param <E>     the type parameter
	 * @param value   value that needs to be compared
	 * @param peakOne one value
	 * @param peakTwo two value
	 * @return <ul> <li>-2 value is smaller than minimum</li> <li>-1 value is equals than minimum</li> <li>0 value in range</li> <li>1 value is equals than max</li> <li>2 value is bigger than max</li> </ul>
	 * @author HomeletWei
	 */
	public <E extends Comparable<E>> int between(E value, E peakOne, E peakTwo){
		return betweenPeaks(value, peakOne.compareTo(peakTwo) >= 0 ? peakOne : peakTwo, peakTwo.compareTo(peakOne) <= 0 ? peakTwo : peakOne);
	}
	
	/**
	 * Determine the Position of the value which between two peaks
	 *
	 * @param <E>     the type parameter
	 * @param value   value that needs to be compared
	 * @param maxPeak max peak
	 * @param minPeak minimum peak
	 * @return <ul> <li>-2 value is smaller than minimum peak</li> <li>-1 value is equals than minimum peak</li> <li>0 value in range</li> <li>1 value is equals than max peak</li> <li>2 value is bigger than max peak</li> </ul>
	 * @author HomeletWei
	 */
	public <E extends Comparable<E>> int betweenPeaks(E value, E maxPeak, E minPeak){
		if(value.compareTo(minPeak) < 0){
			return -2;
		}else if(value.compareTo(minPeak) == 0){
			return -1;
		}else{
			if(value.compareTo(maxPeak) > 0){
				return 2;
			}else if(value.compareTo(maxPeak) == 0){
				return 1;
			}else{
				return 0;
			}
		}
	}
	
	/**
	 * Translate color color.
	 *
	 * @param context the context
	 * @return the color
	 */
	public Color translateColor(String context){
		context = context.toLowerCase();
		if(context.startsWith("#")){
			String hext  = context.substring(1, 7);
			int    value = Integer.parseInt(hext, 16);
			if(value > Integer.parseInt("ffffff", 16)){
				return new Color(Integer.parseInt("ffffff", 16));
			}else if(value < Integer.parseInt("000000", 16)){
				return new Color(Integer.parseInt("000000", 16));
			}else{
				return new Color(value);
			}
		}else if(context.contains("rgb")){
			String value     = context.substring(context.indexOf('(') + 1, context.lastIndexOf(')') + 1);
			int[]  values    = new int[4];
			int    index     = 0;
			String buffer    = null;
			char[] charValue = value.toCharArray();
			for(int x = 0; x < charValue.length; x++){
				if(charValue[x] == ' '){
					continue;
				}
				if(charValue[x] == ',' || x + 1 >= charValue.length){
					int v = Integer.parseInt(buffer);
					if(v > 256 || v < 0){
						return null;
					}
					values[index] = v;
					index++;
					buffer = null;
					continue;
				}
				buffer = buffer == null ? String.valueOf(charValue[x]) : buffer + charValue[x];
			}
			values = Arrays.copyOf(values, index);
			if(values.length == 3){
				return new Color(values[0], values[1], values[2]);
			}else if(values.length == 4){
				System.out.println(values[3]);
				return new Color(values[0], values[1], values[2], values[3]);
			}
		}
		return null;
	}
	
	/**
	 * print a centain array into a System.out sepcific, with Addition in the front, with toString as String converter
	 *
	 * @param <E>      the type parameter
	 * @param addition the addtion String added in the front
	 * @param array    the array of object
	 * @author HomeletWei
	 */
	public <E> void printOutArray(String addition, E[] array){
		printOutArray(addition, array, (E Object)->{
			return Object.toString();
		});
	}
	
	/**
	 * print a centain array into a System.out sepcific, with Addition in the front
	 *
	 * @param <E>       the type parameter
	 * @param addition  the addtion String added in the front
	 * @param array     the array of object
	 * @param converter the converter of the E
	 * @author HomeletWei
	 */
	public <E> void printOutArray(String addition, E[] array, StringConvert<E> converter){
		printOutArray(System.out, addition, array, false, converter);
	}
	
	/**
	 * print a certain array into a PrintStream specific, with Addition in the front
	 *
	 * @param <E>         the type parameter
	 * @param printStream the print Steam that specific
	 * @param addition    the addition String added in the front
	 * @param array       the array of object
	 * @param ignoreNull  should null be ignored
	 * @param converter   the converter of the E
	 * @author HomeletWei
	 * @see java.io.PrintStream
	 */
	public <E> void printOutArray(PrintStream printStream, String addition, E[] array, boolean ignoreNull, StringConvert<E> converter){
		if(array == null || array.length == 0){
			return;
		}
		int index     = 0;
		int maxLenght = lengthOf(array.length);
		for(E e : array){
			if(e == null && ignoreNull){
				return;
			}
			printStream.println(padString(String.valueOf(index++), maxLenght, ' ') + "|" + addition + ((e == null) ? "NULL" : converter.getString(e)));
		}
	}
	
	/**
	 * Length of int.
	 *
	 * @param integer the integer
	 * @return the int
	 */
	public int lengthOf(int integer){
		return String.valueOf(integer).length();
	}
	
	/**
	 * Pad string string.
	 *
	 * @param i             the
	 * @param desiredLength the desired length
	 * @param padder        the padder
	 * @return the string
	 */
	public String padString(String i, int desiredLength, char padder){
		if(i.length() >= desiredLength)
			return i;
		StringBuilder builder = new StringBuilder();
		for(int adder = 0; adder < desiredLength - i.length(); adder++){
			builder.append(padder);
		}
		return builder.append(i).toString();
	}
	
	/**
	 * Get stack trace string [ ].
	 *
	 * @return stackTrace string [ ]
	 * @author HomeletWei
	 */
	public String[] getStackTrace(){
		StackTraceElement[] elements  = Thread.currentThread().getStackTrace();
		String[]            strBuffer = new String[elements.length - 2];
		int                 counter   = 0;
		strBuffer[counter++] = Thread.currentThread().getName();
		for(int i = 3; i < elements.length; i++){
			StackTraceElement element = elements[i];
			strBuffer[counter] = "at " + element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":" + element.getLineNumber() + ")";
			counter++;
		}
		return strBuffer;
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return string string
	 * @author HomeletWei
	 */
	public String getValueFromSequence(char startSign, char endSign, String string){
		return String.valueOf(grabValueFromSequence(startSign, endSign, string.toCharArray()));
	}
	
	private char[] grabValueFromSequence(char startSign, char endSign, char[] charBuffer){
		if(charBuffer == null || charBuffer.length == 0){
			return null;
		}
		boolean started       = false;
		char[]  newCharBuffer = new char[charBuffer.length];
		int     count         = 0;
		for(char c : charBuffer){
			if(c == startSign && !started){
				started = true;
				continue;
			}else if(c == endSign && started){
				started = false;
				break;
			}
			if(started)
				newCharBuffer[count++] = c;
		}
		return Arrays.copyOf(newCharBuffer, count);
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return char [ ]
	 * @author HomeletWei
	 */
	public char[] getCharsFromSequence(char startSign, char endSign, String string){
		return grabValueFromSequence(startSign, endSign, string.toCharArray());
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return string string
	 * @author HomeletWei
	 */
	public String getValueFromSequence(char startSign, char endSign, char[] string){
		return String.valueOf(grabValueFromSequence(startSign, endSign, string));
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return char [ ]
	 * @author HomeletWei
	 */
	public char[] getCharsFromSequence(char startSign, char endSign, char[] string){
		return grabValueFromSequence(startSign, endSign, string);
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return string [ ]
	 * @author HomeletWei
	 */
	public String[] getAllValueFromSequence(char startSign, char endSign, String string){
		char[][] charBuffer = grabAllValueFromSequence(startSign, endSign, string.toCharArray());
		if(charBuffer == null || charBuffer.length == 0){
			return null;
		}
		String[] stringBuffer = new String[charBuffer.length];
		int      index        = 0;
		for(char[] cb : charBuffer){
			stringBuffer[index++] = String.valueOf(cb);
		}
		return Arrays.copyOf(stringBuffer, index);
	}
	
	private char[][] grabAllValueFromSequence(char startSign, char endSign, char[] charBuffer){
		if(charBuffer == null || charBuffer.length == 0){
			return null;
		}
		boolean  started       = false;
		char[][] newCharBuffer = new char[charBuffer.length / 2][charBuffer.length];
		int      index         = 0, count = 0;
		for(char c : charBuffer){
			if(c == startSign && !started){
				started = true;
				continue;
			}else if(c == endSign && started){
				started = false;
				newCharBuffer[index] = Arrays.copyOf(newCharBuffer[index], count);
				count = 0;
				index++;
				continue;
			}
			if(started)
				newCharBuffer[index][count++] = c;
		}
		return Arrays.copyOf(newCharBuffer, index);
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return char [ ] [ ]
	 * @author HomeletWei
	 */
	public char[][] getAllCharsFromSequence(char startSign, char endSign, String string){
		return grabAllValueFromSequence(startSign, endSign, string.toCharArray());
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return string [ ]
	 * @author HomeletWei
	 */
	public String[] getAllValueFromSequence(char startSign, char endSign, char[] string){
		char[][] charBuffer = grabAllValueFromSequence(startSign, endSign, string);
		if(charBuffer == null || charBuffer.length == 0){
			return null;
		}
		String[] stringBuffer = new String[charBuffer.length];
		int      index        = 0;
		for(char[] cb : charBuffer){
			stringBuffer[index++] = String.valueOf(cb);
		}
		return Arrays.copyOf(stringBuffer, index);
	}
	
	/**
	 * TODO
	 *
	 * @param startSign the start sign
	 * @param endSign   the end sign
	 * @param string    the string
	 * @return char [ ] [ ]
	 * @author HomeletWei
	 */
	public char[][] getAllCharsFromSequence(char startSign, char endSign, char[] string){
		return grabAllValueFromSequence(startSign, endSign, string);
	}
	
	/**
	 * Index of int.
	 *
	 * @param strings the strings
	 * @param str     the str
	 * @return the int
	 */
	public int indexOf(String[] strings, String str){
		if(strings == null || strings.length == 0 || str == null){
			return -1;
		}
		for(int index = 0; index < strings.length; index++){
			if(strings[index].contentEquals(str)){
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * To border rectangle.
	 *
	 * @param target the target
	 * @return the rectangle
	 */
	public Rectangle toRectangle(Component target){
		return toRectangle(0, 0, target.getPreferredSize());
	}
	
	/**
	 * To border rectangle.
	 *
	 * @param x  the x
	 * @param y  the y
	 * @param DI the di
	 * @return the rectangle
	 */
	public Rectangle toRectangle(int x, int y, Dimension DI){
		return toRectangle(x, y, DI.width, DI.height);
	}
	
	/**
	 * To border rectangle.
	 *
	 * @param x      the x
	 * @param y      the y
	 * @param width  the width
	 * @param height the height
	 * @return the rectangle
	 */
	public Rectangle toRectangle(int x, int y, int width, int height){
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * To border rectangle.
	 *
	 * @param x          the x
	 * @param y          the y
	 * @param imageTarge the image targe
	 * @return the rectangle
	 */
	public Rectangle toRectangle(int x, int y, BufferedImage imageTarge){
		return toRectangle(x, y, imageTarge.getWidth(), imageTarge.getHeight());
	}
	
	/**
	 * Pad int with zero string.
	 *
	 * @param i      the
	 * @param padder the padder
	 * @return the string
	 */
	public String padIntWithZero(int i, int padder){
		return padString(String.valueOf(i), padder, '0');
	}
	
	/**
	 * Test string boolean.
	 *
	 * @param group the group
	 * @return the boolean
	 */
	public boolean checkNullString(String[] group){
		try{
			for(String s : group){
				if(s == null)
					return false;
			}
		}catch(@SuppressWarnings("unused") NullPointerException e){
			return false;
		}
		return true;
	}
	
	/**
	 * Get combination key starter int.
	 *
	 * @return the int
	 */
	public int getCombinationKeyStarter(){
		switch(currentOS){
			case MAC_OS_X:
				return KeyEvent.META_DOWN_MASK;
			case WINDOWS://$FALL-THROUGH$
			case OTHER://$FALL-THROUGH$
			default:
				return KeyEvent.CTRL_DOWN_MASK;
		}
	}
	
	/**
	 * Get mouse location point.
	 *
	 * @param component the component
	 * @return the point
	 */
	public Point getMouseLocation(Component component){
		Point p      = MouseInfo.getPointerInfo().getLocation();
		Point frameP = component.getLocationOnScreen();
		return new Point(p.x - frameP.x, p.y - frameP.y);
	}
	
	/**
	 * TODO
	 *
	 * @param parent          the parent
	 * @param e               the e
	 * @param title           the title
	 * @param additionMessage the addition message
	 * @param exceptionIndex  the exception index
	 * @author HomeletWei
	 */
	public void handleException(Component parent, @SuppressWarnings("unused") Exception e, String title, Object additionMessage, String exceptionIndex){
		//	if(e != null)
		//	    e.printStackTrace(System.err);
		JOptionPane.showMessageDialog(parent, additionMessage + "\n(For more information please refer to\nUser Manual->Errors And Exceptions->" + exceptionIndex + ")", title, JOptionPane.ERROR_MESSAGE, null);
	}
	
	/**
	 * set the component's preferred size
	 * <br>
	 * <br>
	 * <b>equivalent to setSize(c, new Dimension(width, height));</b>
	 *
	 * @param c      the c
	 * @param width  the width
	 * @param height the height
	 * @author HomeletWei
	 * @see Utility#setPreferredSize(Component, Dimension) Utility#setPreferredSize(Component, Dimension)Utility#setPreferredSize(Component, Dimension)
	 */
	public void setPreferredSize(Component c, int width, int height){
		setPreferredSize(c, new Dimension(width, height));
	}
	
	/**
	 * set the component's preferred Size
	 *
	 * @param c the c
	 * @param d the d
	 * @author HomeletWei
	 */
	public void setPreferredSize(Component c, Dimension d){
		c.setPreferredSize(d);
		c.setMaximumSize(d);
		c.setMinimumSize(d);
	}
	
	/**
	 * only use this when there is no layout on it
	 *
	 * @param c the c
	 * @param d the d
	 * @author HomeletWei
	 */
	public void setSize(Component c, Dimension d){
		c.setSize(d);
	}
	
	/**
	 * only use this when there is no layout on it
	 *
	 * @param c      the c
	 * @param width  the width
	 * @param height the height
	 * @author HomeletWei
	 */
	public void setSize(Component c, int width, int height){
		c.setSize(width, height);
	}
	
	/**
	 * Sum point point.
	 *
	 * @param vertex   the vertex
	 * @param thePoint the the point
	 * @return the point
	 */
	public Point sumPoint(Point vertex, Point thePoint){
		return new Point(vertex.x + thePoint.x, vertex.y + thePoint.y);
	}
	
	/**
	 * Get scroll panel di dimension.
	 *
	 * @param scrollItem the scroll item
	 * @return the dimension
	 */
	public Dimension getScrollPanelDI(Dimension scrollItem){
		return new Dimension(scrollItem.width + 20, scrollItem.height);
	}
	
	/**
	 * get the orientation of the DI
	 *
	 * @param di the di
	 * @return the orientation
	 * @author HomeletWei
	 */
	public Orientation getOrientation(Dimension di){
		if(di.width > di.height){
			return Orientation.HORIZONTAL;
		}else if(di.width < di.height){
			return Orientation.VERTICAL;
		}else{
			return Orientation.EQUILATERAL;
		}
	}
	
	/**
	 * Get dominate int.
	 *
	 * @param comp the comp
	 * @return the orientation
	 * @author HomeletWei
	 */
	public int getDominate(Component comp){
		return Math.max(comp.getWidth(), comp.getHeight());
	}
	
	/**
	 * Get less dominate int.
	 *
	 * @param comp the comp
	 * @return the int
	 */
	public int getLessDominate(Component comp){
		return Math.min(comp.getWidth(), comp.getHeight());
	}
	
	/**
	 * Remove white space string.
	 *
	 * @param source the source
	 * @return the string
	 */
	public String removeWhiteSpace(String source){
		StringBuilder builder = new StringBuilder();
		Scanner       sc      = new Scanner(source);
		while(sc.hasNext()){
			builder.append(sc.next());
		}
		sc.close();
		return builder.toString();
	}
	
	/**
	 * Plus boarder rectangle.
	 *
	 * @param bounds  the bounds
	 * @param padding the padding
	 * @return the rectangle
	 */
	public Rectangle plusBounds(Rectangle bounds, int padding){
		return plusInsets(bounds, padding, padding, padding, padding);
	}
	
	/**
	 * Minus boarder rectangle.
	 *
	 * @param bounds  the bounds
	 * @param padding the padding
	 * @return the rectangle
	 */
	public Rectangle minusBounds(Rectangle bounds, int padding){
		return minusInsets(bounds, padding, padding, padding, padding);
	}
	
	public Rectangle plusInsets(Rectangle bounds, int top, int bottom, int right, int left){
		return new Rectangle(bounds.x - left, bounds.y - top, bounds.width + (left + right), bounds.height + (top + bottom));
	}
	
	public Rectangle minusInsets(Rectangle bounds, int top, int bottom, int right, int left){
		return new Rectangle(bounds.x + left, bounds.y + top, bounds.width - (left + right), bounds.height - (top + bottom));
	}
	
	/**
	 * Get final dimension dimension.
	 *
	 * @param preferredDimension the preferred dimension
	 * @param renderList         the render list
	 * @return the dimension
	 */
	public Dimension getFinalDimension(Dimension preferredDimension, Collection<Render> renderList){
		Dimension finalDimention = null;
		for(Iterator<Render> iterator = renderList.iterator(); iterator.hasNext(); ){
			Render    render = iterator.next();
			Dimension di     = null;
			if(render instanceof RenderTarget)
				di = ((RenderTarget) render).getDimension();
			if(di == null)
				continue;
			if(finalDimention == null)
				finalDimention = new Dimension();
			HomeletMath.getMath().maxDimension(finalDimention, di, finalDimention);
		}
		if(finalDimention == null){
			finalDimention = preferredDimension;
		}
		return finalDimention;
	}
	
	/**
	 * Regulate value e.
	 *
	 * @param <E>   the type parameter
	 * @param value the value
	 * @param max   the max
	 * @param min   the min
	 * @return the e
	 */
	public <E extends Comparable<E>> E regulateValue(E value, E max, E min){
		if(value.compareTo(max) > 0)
			return max;
		else if(value.compareTo(min) < 0)
			return min;
		else
			return value;
	}
	
	/**
	 * TODO
	 *
	 * @author HomeletWei
	 * @date Apr 27, 2018
	 */
	public enum OS{
		/**
		 * Windows os.
		 *
		 * @Fields <b>WINDOWS</b> TODO
		 */
		WINDOWS("Windows"),
		/**
		 * The Mac os x.
		 *
		 * @Fields <b>MAC_OS_X</b> TODO
		 */
		MAC_OS_X("Mac OS X"),
		/**
		 * Other os.
		 *
		 * @Fields <b>OTHER</b> TODO
		 */
		OTHER("");
		private static OS[] osList = { WINDOWS, MAC_OS_X };
		/**
		 * The Identifyer.
		 */
		String identifyer;
		
		/**
		 * constructor for Utils.OS
		 * TODO
		 *
		 * @author HomeletWei
		 */
		private OS(String identifyer){
			this.identifyer = identifyer;
		}
		
		/**
		 * Get current working os os.
		 *
		 * @return the os
		 */
		public static OS getCurrentWorkingOS(){
			return OS.matchOS(System.getProperty("os.name"));
		}
		
		/**
		 * TODO
		 *
		 * @param identifyer the identifyer
		 * @return os os
		 * @author HomeletWei
		 */
		public static OS matchOS(String identifyer){
			for(OS s : osList){
				if(identifyer.contentEquals(s.identifyer)){
					return s;
				}
			}
			return OTHER;
		}
	}
	
	/**
	 * The enum Orientation.
	 */
	public enum Orientation{
		/**
		 * Horizontal orientation.
		 */
		HORIZONTAL,
		/**
		 * Vertical orientation.
		 */
		VERTICAL,
		/**
		 * Equilateral orientation.
		 */
		EQUILATERAL;
	}
	
	/**
	 * The interface String convert.
	 *
	 * @param <E> the type parameter
	 */
	public interface StringConvert<E>{
		
		/**
		 * Gets string.
		 *
		 * @param Object the object
		 * @return the string
		 */
		public String getString(E Object);
	}
}
