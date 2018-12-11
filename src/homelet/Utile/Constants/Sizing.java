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
 * @date May 14, 2018
 */
package homelet.Utile.Constants;

import java.awt.*;

/**
 * the sizing
 *
 * @author HomeletWei
 * @date May 14, 2018
 */
public class Sizing{
	
	/** @Fields <b>interval</b> the default gap */
	public static final int       gap          = 5;
	/** @Fields <b>removeButton</b> */
	public static final Dimension removeButton = new Dimension(45, 20);
	
	/**
	 * a single item has a with of 200 and height of 100, thus the inner item shall be smaller with 180 in width and less than 100 in size
	 * <br>
	 * Note : this is used for the encode template
	 *
	 * @author HomeletWei
	 * @date May 14, 2018
	 */
	public static class EncodeTemplate{
		
		/** @Fields <b>smallPiece</b> */
		public static final int       smallPiece         = 10;
		/** @Fields <b>mediumPiece</b> TODO */
		public static final int       mediumPiece        = 30;
		/** @Fields <b>smallPiece</b> */
		public static final int       interval           = mediumPiece;
		/** @Fields <b>largePiece</b> the piece Width */
		public static final int       pieceWidthOne      = 100;
		/** @Fields <b>largePiece</b> the piece Width */
		public static final int       pieceWidthTwo      = 250;
		/** @Fields <b>pieceHeight</b> the piece Height */
		public static final int       pieceHeight        = 100;
		///////<div>/////////
		/** @Fields <b>totalTemplateWidth</b> the template item width : 3 piece + 2 interval + 2 bounds */
		public static final int       totalTemplateWidth = pieceWidthOne * 1 + pieceWidthTwo * 2 + smallPiece * 2 + smallPiece * 2 + removeButton.width;
		///////<div>/////////
		/** @Fields <b>templatePieceTwo</b> templatePieceTwo */
		public static final Dimension templatePieceOne   = new Dimension(pieceWidthOne, pieceHeight);
		/** @Fields <b>templatePieceTwo</b> templatePieceTwo */
		public static final Dimension templatePieceTwo   = new Dimension(pieceWidthTwo, pieceHeight);
		/** @Fields <b>templateItem</b> the whole template item */
		public static final Dimension itemList           = new Dimension(totalTemplateWidth, pieceHeight * 6);
		/** @Fields <b>singleLine</b> a single line item */
		public static final Dimension singleLine         = new Dimension(pieceWidthTwo, interval);
		/** @Fields <b>dividerLine</b> div */
		public static final Dimension dividerLine        = new Dimension(smallPiece, pieceHeight);
	}
	
	/**
	 * TODO
	 *
	 * @author HomeletWei
	 * @date May 14, 2018
	 */
	public static class Menu{
		
		/** @Fields <b>containerWidth</b> */
		public static final int       containerWidth  = 500;
		/** @Fields <b>containerWidth</b> */
		public static final int       objectWidth     = containerWidth - gap;
		/** @Fields <b>smallPiece</b> */
		public static final int       smallPiece      = 10;
		/** @Fields <b>mediumPiece</b> */
		public static final int       mediumPiece     = 40;
		/** @Fields <b>largePiece</b> */
		public static final int       largePiece      = 60;
		///////<div>/////////
		/** @Fields <b>smallItem</b> a small item */
		public static final Dimension smallItem       = new Dimension(containerWidth, mediumPiece);
		/** @Fields <b>largeItem</b> a large item */
		public static final Dimension largeItem       = new Dimension(containerWidth, largePiece);
		/** @Fields <b>dividerLine</b> div */
		public static final Dimension dividerLine     = new Dimension(containerWidth, smallPiece);
		///////<div>/////////
		/** @Fields <b>largeItemHeight</b> */
		public static final int       largeItemHeight = largePiece;
		/** @Fields <b>smallItemHeight</b> */
		public static final int       smallItemHeight = mediumPiece;
		/**
		 * @Fields <b>tripleOneDI</b> 1/6 of the objectWidth
		 * @see #tripleTwo
		 * @see #tripleThree
		 */
		public static final int       tripleOne       = objectWidth / 6 * 1;
		/**
		 * @Fields <b>tripleLabelTwoDI</b> 1/2 of the objectWidth
		 * @see #tripleOne
		 * @see #tripleThree
		 */
		public static final int       tripleTwo       = objectWidth / 6 * 3;
		/**
		 * @Fields <b>tripleThreeDI</b> 1/3 of the objectWidth
		 * @see #tripleOne
		 * @see #tripleTwo
		 */
		public static final int       tripleThree     = objectWidth / 6 * 2;
		///////<div>/////////
		/**
		 * @Fields <b>doubleLableOneDI</b> 1/3 of the objectWidth
		 * @see #doubleTwo
		 */
		public static final int       doubleOne       = objectWidth / 3 * 1;
		/**
		 * @Fields <b>doubleLableTwoDI</b> 2/3 of the objectWidth
		 * @see #doubleOne
		 */
		public static final int       doubleTwo       = objectWidth / 3 * 2;
		/**
		 * @Fields <b>doubleLableOneDI</b> 1/3 of the objectWidth
		 * @see #doubleTwo
		 */
		public static final int       doubleMediumOne = objectWidth / 3 * 1;
		/**
		 * @Fields <b>doubleLableTwoDI</b> 2/3 of the objectWidth
		 * @see #doubleOne
		 */
		public static final int       doubleMediumTwo = objectWidth / 3 * 2;
		///////<div>/////////
		/** @Fields <b>halfLongButtonDI</b> 1/2 of the objectWidth */
		public static final int       half            = objectWidth / 2;
		/** @Fields <b>tripDI</b> 1/3 of the objectWidth */
		public static final int       triple          = objectWidth / 3;
		///////<div>/////////
		//	public static final Dimension twoLayOutSettingLabelDI  = new Dimension(165, 30);
		//	public static final Dimension twoLayOutSettingButtonDI = new Dimension(305, 30);
		//	public static final Dimension threeLayOutDiscriptionDI = new Dimension(120, 30);
		//	public static final Dimension threeLayOutButtonDI      = new Dimension(260, 30);
		//	public static final Dimension threeLayOutLabelDI       = new Dimension(80, 30);
		//	public static final Dimension twoLayOutLabelDI	       = new Dimension(100, 30);
		//	public static final Dimension twoLayOutButtonDI	       = new Dimension(290, 30);
		//	public static final Dimension twoSameLayOutButtonDI    = new Dimension(242, 30);
	}
	
	/**
	 * TODO
	 *
	 * @author HomeletWei
	 * @date May 14, 2018
	 */
	public static class Display{
		
		/** @Fields <b>displayWidth</b> TODO */
		public static final int       displayWidth        = 500;
		/** @Fields <b>displayHeight</b> TODO */
		public static final int       displayHeight       = 500;
		/** @Fields <b>displayPanelDI</b> TODO */
		public static final Dimension displayPanelDI      = new Dimension(displayWidth + 10, displayHeight + 10);
		/** @Fields <b>canvasPanelDI</b> TODO */
		public static final Dimension canvasPanelDI       = new Dimension(displayWidth, displayHeight);
		/////// Encode ////////
		/** @Fields <b>encodePanel</b> TODO */
		public static final Dimension encodePanel         = new Dimension(displayWidth - 10, displayHeight - 50);
		/** @Fields <b>encodeContentHeader</b> TODO */
		public static final Dimension encodeContentHeader = new Dimension(encodePanel.width, 30);
		/** @Fields <b>encodeContentPanel</b> TODO */
		public static final Dimension encodeContentPanel  = new Dimension(encodePanel.width, encodePanel.height - 50);
		/** @Fields <b>controlBarButton</b> */
		public static final Dimension controlBarButton    = new Dimension(100, 30);
	}
}
