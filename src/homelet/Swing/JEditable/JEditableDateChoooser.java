/**
 * @author HomeletWei
 * @date Apr 14, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JEditable;

import homelet.Swing.JBasic.JCustomizeComboBox;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.DateHandler;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author HomeletWei
 * @date Apr 14, 2018
 */
public class JEditableDateChoooser extends JEdit{
	
	public static final  String templateIdentifyer = "#DC#";
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long   serialVersionUID   = 1L;
	final                String BEFORE_TODAY       = "#BEFORE_TODAY#";
	final                String NO_SEPCIFIC        = "#NO_SEPCIFIC#";
	JTextLabel                 displayLabel;
	JCustomizeComboBox<String> year;
	JCustomizeComboBox<String> month;
	JCustomizeComboBox<String> day;
	
	/**
	 * constructor for JEditableTextLabel
	 *
	 * @param content
	 * @author HomeletWei
	 */
	public JEditableDateChoooser(String[] content){
		super();
		int[] now = DateHandler.getDateHandler().getCurrentDateInfo();
		switch(content[0]){
			case BEFORE_TODAY:
				year = new JCustomizeComboBox<>(DateHandler.getDateHandler().getYearBeforeToday(now[0], 50), 0, Sizing.EncodeTemplate.singleLine);
				month = new JCustomizeComboBox<>(DateHandler.getDateHandler().getDayOrMonthRange(DateHandler.getDateHandler().getMonthInYear()), now[1], Sizing.EncodeTemplate.singleLine);
				day = new JCustomizeComboBox<>(DateHandler.getDateHandler().getDayOrMonthRange(DateHandler.getDateHandler().getDaysInMonth(now[0], now[1])), now[2], Sizing.EncodeTemplate.singleLine);
				break;
			case NO_SEPCIFIC://$FALL-THROUGH$
			default:
				year = new JCustomizeComboBox<>(DateHandler.getDateHandler().getYearRange(now[0], 50), 50, Sizing.EncodeTemplate.singleLine);
				month = new JCustomizeComboBox<>(DateHandler.getDateHandler().getDayOrMonthRange(DateHandler.getDateHandler().getMonthInYear()), now[1], Sizing.EncodeTemplate.singleLine);
				day = new JCustomizeComboBox<>(DateHandler.getDateHandler().getDayOrMonthRange(DateHandler.getDateHandler().getDaysInMonth(now[0], now[1])), now[2], Sizing.EncodeTemplate.singleLine);
				break;
		}
		this.displayLabel = new JTextLabel(content[1], Alignment.CENTER, Alignment.CENTER, 0, null, true);
		Layouter.GridBagLayouter trueLayout = new GridBagLayouter((JPanel) truePanel);
		trueLayout.put(trueLayout.instanceOf(displayLabel, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		Layouter.GridBagLayouter falseLayout = new GridBagLayouter((JPanel) falsePanel);
		falseLayout.put(falseLayout.instanceOf(year, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		falseLayout.put(falseLayout.instanceOf(month, 0, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		falseLayout.put(falseLayout.instanceOf(day, 0, 2).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		falseLayout.put(falseLayout.instanceOf(getBackButton(), 0, 3).setAnchor(Anchor.CENTER).setFill(Fill.HORIZONTAL).setWeight(100, 0));
		this.setMinimumSize(Sizing.EncodeTemplate.templatePieceTwo);
		addRestoreDefaultChild(year);
		addRestoreDefaultChild(month);
		addRestoreDefaultChild(day);
		addRestoreDefaultChild(displayLabel);
		addEnableChild(year, true);
		addEnableChild(month, true);
		addEnableChild(day, true);
		addEnableChild(displayLabel, true);
		addAction();
		flipTo(true);
	}
	
	private void addAction(){
		this.displayLabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2 && isEnabled()){
					flip();
				}
			}
		});
		this.year.addActionListener((ActionEvent e)->{
			onYearChange(Integer.valueOf(year.getContent()));
		});
		this.month.addActionListener((ActionEvent e)->{
			onMonthChange(Integer.valueOf(year.getContent()), Integer.valueOf(month.getContent()));
		});
	}
	
	private void onYearChange(int newYear){
		month.setSelectedIndex(0);
		onMonthChange(newYear, month.getSelectedIndex());
	}
	
	private void onMonthChange(int newYear, int newMonth){
		day.setSelectedIndex(0);
		day.setModule(DateHandler.getDateHandler().getDayOrMonthRange(DateHandler.getDateHandler().getDaysInMonth(newYear, newMonth)));
	}
	
	@Override
	protected void exitStateAction(){
		this.displayLabel.setText(getInnerContent());
	}
	
	private String getInnerContent(){
		return Utility.getUtility().padString(year.getContent(), 4, '0') + Utility.getUtility().padString(month.getContent(), 2, '0') + Utility.getUtility().padString(day.getContent(), 2, '0');
	}
	
	@Override
	public boolean isReady(){
		//	flipTo(true);
		return true;
	}
	
	@Override
	public String getContent(){
		return getInnerContent();
	}
	
	@Override
	public void restoreDefault(){
		this.displayLabel.restoreDefault();
		int[] now = DateHandler.getDateHandler().getCurrentDateInfo();
		this.year.setSelectedItem(DateHandler.getDateHandler().getYear());
		this.onMonthChange(now[0], now[1]);
	}
}
