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
package homelet.Display.State.Menu;

import homelet.Display.Display;
import homelet.Display.State.States;
import homelet.Swing.JBasic.JDividerLine;
import homelet.Swing.JComplex.JComplexCheckBox;
import homelet.Swing.JComplex.JComplexSlider;
import homelet.Swing.RestoreDefault;
import homelet.Swing.RestoreDefaultParent;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.SpringLayouter;
import homelet.Utile.Layouter.SpringLayouter.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 26, 2018
 */
public class MenuSettingState extends States implements RestoreDefaultParent{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	final                int  maxFPS           = 6, minFPS = 1, defaultFPS = 6;
	JComplexCheckBox          performanceMode;
	JComplexSlider            FPS;
	JComplexCheckBox          useDoubleEnsurance;
	JComplexCheckBox          useOpenEdit;
	JComplexCheckBox          autoCopyToClipboard;
	JComplexCheckBox          addProgramWaterMark;
	ArrayList<RestoreDefault> restoreDefaultChildList = new ArrayList<>();
	
	/**
	 * constructor for MenuSettingState
	 * TODO
	 *
	 * @param name
	 * @author HomeletWei
	 */
	public MenuSettingState(String name){
		super(name);
	}
	
	/**
	 * @see homelet.Display.State.States#createDisplay()
	 */
	@Override
	public void createDisplay(){
		this.addBackAction(Display.menuState);
		this.addRestoreDefault(this);
		performanceMode = new JComplexCheckBox("Image will be forced to tick.", "Performance Mode", true);
		performanceMode.setMnemonic(KeyEvent.VK_P);
		performanceMode.setCheckChangeAble(false);
		FPS = new JComplexSlider("Frame Per Second:", maxFPS, minFPS, defaultFPS, JComplexSlider.VALUE_UNIT, "FPS");
		FPS.setResultRatio(5);
		performanceMode.addEnableChild(FPS, true);
		autoCopyToClipboard = new JComplexCheckBox("Result of decode and the path of the generated file will be copyed to ClipBoard Automatically.", "Auto Copy To Clipboard", false);
		autoCopyToClipboard.setMnemonic(KeyEvent.VK_C);
		useDoubleEnsurance = new JComplexCheckBox("Check Readability before output.", "Output Insurance (Recommended)", true);
		useDoubleEnsurance.setMnemonic(KeyEvent.VK_I);
		useOpenEdit = new JComplexCheckBox("Editor will remain editable.", "Open Edit", false);
		useOpenEdit.setMnemonic(KeyEvent.VK_O);
		addProgramWaterMark = new JComplexCheckBox("The Program Water Mark will be added.", "Add Program Water Mark", true);
		addProgramWaterMark.setCheckChangeAble(false);
		addProgramWaterMark.setMnemonic(KeyEvent.VK_W);
		addRestoreDefaultChild(performanceMode);
		addRestoreDefaultChild(FPS);
		addRestoreDefaultChild(autoCopyToClipboard);
		addRestoreDefaultChild(useDoubleEnsurance);
		addRestoreDefaultChild(useOpenEdit);
		addRestoreDefaultChild(addProgramWaterMark);
		Layouter.SpringLayouter layout    = new SpringLayouter(displayPanel);
		JDividerLine            div_start = new JDividerLine();
		JDividerLine            div_one   = new JDividerLine();
		JDividerLine            div_two   = new JDividerLine();
		JDividerLine            div_three = new JDividerLine();
		JDividerLine            div_four  = new JDividerLine();
		JDividerLine            div_five  = new JDividerLine();
		JDividerLine            div_end   = new JDividerLine();
		//////////////////////////<div>//////////////////////////
		layout.put(Position.CONSTRAIN_X, div_start, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_start, 5, Position.CONSTRAIN_Y, displayPanel);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_start, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_start).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		//<div>//
		layout.put(Position.CONSTRAIN_X, performanceMode, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, performanceMode, 5, Position.CONSTRAIN_Y_HEIGHT, div_start);
		layout.put(Position.CONSTRAIN_X_WIDTH, performanceMode, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, FPS, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, FPS, 5, Position.CONSTRAIN_Y_HEIGHT, performanceMode);
		layout.put(Position.CONSTRAIN_X_WIDTH, FPS, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//////////////////////////<div>//////////////////////////
		layout.put(Position.CONSTRAIN_X, div_one, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_one, 5, Position.CONSTRAIN_Y_HEIGHT, FPS);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_one, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_one).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		//<div>//
		layout.put(Position.CONSTRAIN_X, autoCopyToClipboard, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, autoCopyToClipboard, 5, Position.CONSTRAIN_Y_HEIGHT, div_one);
		layout.put(Position.CONSTRAIN_X_WIDTH, autoCopyToClipboard, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//////////////////////////<div>//////////////////////////
		layout.put(Position.CONSTRAIN_X, div_two, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_two, 5, Position.CONSTRAIN_Y_HEIGHT, autoCopyToClipboard);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_two, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_two).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		//<div>//
		layout.put(Position.CONSTRAIN_X, useDoubleEnsurance, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, useDoubleEnsurance, 5, Position.CONSTRAIN_Y_HEIGHT, div_two);
		layout.put(Position.CONSTRAIN_X_WIDTH, useDoubleEnsurance, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//////////////////////////<div>//////////////////////////
		layout.put(Position.CONSTRAIN_X, div_three, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_three, 5, Position.CONSTRAIN_Y_HEIGHT, useDoubleEnsurance);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_three, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_three).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		//<div>//
		layout.put(Position.CONSTRAIN_X, useDoubleEnsurance, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, useDoubleEnsurance, 5, Position.CONSTRAIN_Y_HEIGHT, div_two);
		layout.put(Position.CONSTRAIN_X_WIDTH, useDoubleEnsurance, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//////////////////////////<div>//////////////////////////
		layout.put(Position.CONSTRAIN_X, div_four, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_four, 5, Position.CONSTRAIN_Y_HEIGHT, useDoubleEnsurance);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_four, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_four).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		//<div>//
		layout.put(Position.CONSTRAIN_X, useOpenEdit, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, useOpenEdit, 5, Position.CONSTRAIN_Y_HEIGHT, div_four);
		layout.put(Position.CONSTRAIN_X_WIDTH, useOpenEdit, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//////////////////////////<div>//////////////////////////
		layout.put(Position.CONSTRAIN_X, div_five, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_five, 5, Position.CONSTRAIN_Y_HEIGHT, useOpenEdit);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_five, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_five).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		//<div>//
		layout.put(Position.CONSTRAIN_X, addProgramWaterMark, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, addProgramWaterMark, 5, Position.CONSTRAIN_Y_HEIGHT, div_five);
		layout.put(Position.CONSTRAIN_X_WIDTH, addProgramWaterMark, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//////////////////////////<div>//////////////////////////
		layout.put(Position.CONSTRAIN_X, div_end, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_end, 5, Position.CONSTRAIN_Y_HEIGHT, addProgramWaterMark);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_end, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_end).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
	}
	
	/**
	 * @return the fps
	 * @author HomeletWei
	 */
	public int getFPS(){
		return isPerformanceMode() ? (int) (FPS.getValue() * FPS.getResultRatio()) : 0;
	}
	
	/**
	 * @return is used perforemance mode
	 * @author HomeletWei
	 */
	public boolean isPerformanceMode(){
		return performanceMode.isSelected();
	}
	
	/**
	 * @return is auto copy to clip borad
	 * @author HomeletWei
	 */
	public boolean isAutoCopyToClipBoard(){
		return autoCopyToClipboard.isSelected();
	}
	
	/**
	 * @return is used double ensurence
	 * @author HomeletWei
	 */
	public boolean isUseDoubleInsurence(){
		return useDoubleEnsurance.isSelected();
	}
	
	/**
	 * @return is used open edit
	 * @author HomeletWei
	 */
	public boolean isUseOpenEdit(){
		return useOpenEdit.isSelected();
	}
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return restoreDefaultChildList;
	}
}
