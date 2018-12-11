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
package homelet.Display.State.Encode;

import homelet.Display.Display;
import homelet.Display.State.Encode.customize.CustomizePanel;
import homelet.Display.State.Encode.encodeTemplate.TemplateDialog;
import homelet.Display.State.Encode.encodeTemplate.VCard.VCardDialog;
import homelet.Display.State.States;
import homelet.QRCode.encode.QRCodeEncoder;
import homelet.QRCodeLuncher;
import homelet.Swing.JBasic.*;
import homelet.Swing.JDrawer.JCanvas;
import homelet.Swing.JDrawer.JCanvasAssistant;
import homelet.Swing.JInput.JInputArea;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.FileHandler;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.StringDrawer.StringDrawer;
import homelet.Utile.StringDrawer.StringDrawer.LinePolicy;
import homelet.Utile.StringDrawer.StringDrawerException;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 26, 2018
 */
public class EncodeState extends States implements ComponentListener{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	JCanvasAssistant displayCanvas;
	JInputArea       inputField;
	boolean          usingTemplate = false;
	TemplateDialog   currentDialog = null;
	//
	CustomizePanel   customizePanel;
	
	/**
	 * constructor for EncodeState
	 * TODO
	 *
	 * @param name
	 * @author HomeletWei
	 */
	public EncodeState(String name){
		super(name);
		this.addComponentListener(this);
	}
	
	JFlipAblePanel canvasFlipable;
	
	@Override
	public void componentResized(ComponentEvent e){
		if(isBigger(canvasFlipable.getBounds(), displayCanvas.getBounds())){
//			if(this.isVisible())
//				this.displayCanvas.getCanvas().startRendering("Encode State", Display.menueSettingState.getFPS());
			flipCanvas(JFlipAblePanel.STATE_ONE);
			System.out.println("true");
		}else{
//			this.displayCanvas.getCanvas().stopRendering();
			flipCanvas(JFlipAblePanel.STATE_TWO);
			System.out.println("false");
		}
	}
	
	private void flipCanvas(boolean target){
		if(canvasFlipable.getCurrentState() == target)
			return;
		canvasFlipable.flipTo(target);
	}
	
	@Override
	public void componentMoved(ComponentEvent e){}
	
	@Override
	public void componentShown(ComponentEvent e){}
	
	@Override
	public void componentHidden(ComponentEvent e){}
	
	private boolean isBigger(Rectangle rect, Rectangle comparator){
		if(rect.width >= comparator.width && rect.height >= comparator.height)
			return true;
		return false;
	}
	
	/**
	 * @see homelet.Display.State.States#createDisplay()
	 */
	@Override
	public void createDisplay(){
		this.addBackAction(Display.menuState);
		this.addTargetStateAction(Display.encodeSettingState, "Encode Setting");
		JTextButton saveAs = new JTextButton("Save as", (ActionEvent a)->{
			File f = JFileHandler.FC.writeImageFile(EncodeState.this, displayCanvas.getCanvas().createRenderedImage());
			if(f != null){
				int result = JOptionPane.showConfirmDialog(EncodeState.this, "Successfully write to File\nDo you want to open it?", "Operation Complete", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
				if(result == JOptionPane.YES_OPTION){
					try{
						FileHandler.getFileHander().openFileInSystem(f);
					}catch(IOException e1){
						e1.printStackTrace();
					}
				}
			}
		});
		// canvas side
		this.canvasFlipable = new JFlipAblePanel(){
			@Override
			protected void exitStateAction(){
			}
		};
		JCanvas canvas = new JCanvas(Sizing.Display.canvasPanelDI);
		this.displayCanvas = new JCanvasAssistant(canvas);
		canvas.addComponentListener(this);
		canvasFlipable.setState(displayCanvas, true);
		canvasFlipable.setState(new JPanel(){
			StringDrawer drawer;
			
			{
				drawer = new StringDrawer("Too big to be shown, please try resize the frame");
				drawer.setFont(drawer.getFont().deriveFont(30f));
				drawer.setTextAlign(Alignment.TOP);
				drawer.setAlign(Alignment.CENTER);
				drawer.setColor(Color.RED);
				drawer.setLinePolicy(LinePolicy.BREAK_BY_WORD);
				drawer.setDrawFrameBorder(true);
			}
			
			@Override
			public void paint(Graphics g){
				super.paint(g);
				try{
					drawer.updateGraphics(g);
					drawer.setFrame(g.getClipBounds());
					drawer.validate();
					drawer.draw();
					System.out.println("draw");
				}catch(StringDrawerException e){
					e.printStackTrace();
				}
			}
		}, false);
		canvasFlipable.flipTo(true);
		// input side
		ButtonGroup   sidePanelNavigationBar   = new ButtonGroup();
		JStatusButton navigationGenerateQRCode = new JStatusButton("Generate QRCode", true);
		Utility.getUtility().setPreferredSize(navigationGenerateQRCode, Sizing.Display.encodeContentHeader.width / 2, Sizing.Display.encodeContentHeader.height);
		JStatusButton navigationAddCustomization = new JStatusButton("Customization", false);
		Utility.getUtility().setPreferredSize(navigationAddCustomization, Sizing.Display.encodeContentHeader.width / 2, Sizing.Display.encodeContentHeader.height);
		sidePanelNavigationBar.add(navigationGenerateQRCode);
		sidePanelNavigationBar.add(navigationAddCustomization);
		// flipable
		JFlipAblePanel inputFilpable = new JFlipAblePanel(){
			@Override
			protected void exitStateAction(){}
		};
		navigationGenerateQRCode.addActionListener((actionEvent)->{
			inputFilpable.flipTo(JFlipAblePanel.STATE_ONE);
		});
		navigationAddCustomization.addActionListener((actionEvent)->{
			inputFilpable.flipTo(JFlipAblePanel.STATE_TWO);
		});
		// normal input
		JPanel inputPanel = (JPanel) inputFilpable.truePanel;
		this.inputField = new JInputArea("Enter your content here...", Sizing.Display.displayPanelDI, true);
		JTextButton generate = new JTextButton("Generate", new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					encode();
				}catch(IndexOutOfBoundsException e1){
					if(!Display.encodeSettingState.isUseAdvanceEncoding()){
						throwsError(e1);
						return;
					}
					int result = JOptionPane.showConfirmDialog(EncodeState.this, "An Error has occured during Encodement\nPlease adjust your setting or reduce the text size and try again.\n(Encode->EncodeSetting->UseAdvanceEncoding)\nPress Yes to use the Recommended Setting.", "Operation Fail", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null);
					switch(result){
						case JOptionPane.YES_OPTION:
							Display.encodeSettingState.setUseAdvanceEncoding(false);
							try{
								encode();
							}catch(IndexOutOfBoundsException e2){
								throwsError(e2);
							}
							break;
						case JOptionPane.NO_OPTION://$FALL-THROUGH$
						case JOptionPane.CANCEL_OPTION://$FALL-THROUGH$
						default:
							return;
					}
				}
				customizePanel.getIconModule().onQRCodeChange();
			}
			
			void encode() throws IndexOutOfBoundsException{
				customizePanel.getQrCodeModule().setQrCode(QRCodeEncoder.encode(inputField.getContent(), Display.encodeSettingState.getEncodePreset()));
//				qrcodeImage = qrcode.draw_ColorMatrix_Liquefied(GraphicsHandler.getGradientColorMapping(Color.BLACK, Color.RED, GradientColorMapping.TOP_LEFT_TO_BOTTOM_RIGHT, qrcode.getQrcodeDI(11)), 100, 11);
			}
			
			void throwsError(Exception e){
				Utility.getUtility().handleException(EncodeState.this, e, "Operation Fail", "An Error has occured during Encodement\nPlease Reduce the text size (" + inputField.getContent().length() + " Charaters)", "2.0 Encode Fail");
			}
		});
		Utility.getUtility().setPreferredSize(generate, Sizing.Display.encodeContentHeader);
		addControlBarButton(saveAs);
		{
			Layouter.GridBagLayouter layouter = new GridBagLayouter(inputPanel);
			layouter.put(layouter.instanceOf(createTemplateNavigation(), 0, 0).setAnchor(Anchor.CENTER).setWeight(100, 0).setFill(Fill.BOTH));
			layouter.put(layouter.instanceOf(inputField, 0, 1).setAnchor(Anchor.CENTER).setWeight(100, 100).setFill(Fill.BOTH));
			layouter.put(layouter.instanceOf(generate, 0, 2).setAnchor(Anchor.CENTER).setWeight(100, 0).setFill(Fill.BOTH));
		}
		// customzie
		this.customizePanel = new CustomizePanel();
		{
			Layouter.GridBagLayouter layouter = new GridBagLayouter((JPanel) inputFilpable.falsePanel);
			layouter.put(layouter.instanceOf(customizePanel, 0, 0).setAnchor(Anchor.CENTER).setWeight(100, 100).setFill(Fill.BOTH));
		}
		canvas.addRenderTarget(customizePanel.getQrCodeModule());
		canvas.addRenderTarget(customizePanel.getIconModule());
		Utility.getUtility().setPreferredSize(inputFilpable, new Dimension(610, 645));
		// layout
		Layouter.GridBagLayouter layout = new GridBagLayouter(displayPanel);
		layout.put(layout.instanceOf(canvasFlipable, 0, 0, 1, 3).setAnchor(Anchor.CENTER).setWeight(100, 100).setFill(Fill.NONE).setInsets(10));
		layout.put(layout.instanceOf(navigationGenerateQRCode, 1, 0, 1, 1).setAnchor(Anchor.CENTER).setWeight(0, 0).setFill(Fill.BOTH));
		layout.put(layout.instanceOf(navigationAddCustomization, 2, 0, 1, 1).setAnchor(Anchor.CENTER).setWeight(0, 0).setFill(Fill.BOTH));
		layout.put(layout.instanceOf(inputFilpable, 1, 2, 2, 1).setAnchor(Anchor.CENTER).setWeight(0, 0).setFill(Fill.BOTH));
	}
	
	private JPanel createTemplateNavigation(){
		String[] title              = { "Add Template", "Edit Template" };
		String[] templates          = { "vCard (Virtual Contact File)", "MeCard (Simple vCard)", "WI-FI", "Website", "Text Message", "Phone Call", "E-Mail" };
		JPanel   templateNavigation = new JPanel();
		Utility.getUtility().setPreferredSize(templateNavigation, Sizing.Display.encodeContentHeader);
		JTextButton addTemplate = new JTextButton(title[0]);
		JTextButton removeTemplate = new JRemoveButton((ActionEvent e)->{
			addTemplate.setText(title[0]);
		});
		removeTemplate.setEnabled(false);
		addTemplate.addActionListener((ActionEvent e)->{
			//////<div>//////
			if(!usingTemplate){
				String result = (String) JOptionPane.showInputDialog(templateNavigation, "Choose a Template", "Template", JOptionPane.QUESTION_MESSAGE, null, templates, templates[0]);
				if(result == null)
					return;
				switch(Utility.getUtility().indexOf(templates, result)){
					case 0:
						currentDialog = new VCardDialog(QRCodeLuncher.getDisplay());
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						//		    UseTemplate.currentDialog = new EMail(SystemCentral.getDisplay());
						break;
					default:
						break;
				}
				if(currentDialog == null)
					return;
				currentDialog.showDialog();
				String s = currentDialog.getResult();
				if(s != null && s.length() != 0)
					inputField.setContent(s);
			}else{
				currentDialog.showDialog();
				String s = currentDialog.getResult();
				if(s != null && s.length() != 0)
					inputField.setContent(s);
			}
			//////<div>//////
			usingTemplate = true;
			addTemplate.setText(title[1]);
			removeTemplate.setEnabled(true);
			if(!Display.menueSettingState.isUseOpenEdit())
				inputField.onEnableChange(false);
		});
		removeTemplate.addActionListener((ActionEvent e)->{
			//////<div>//////
			if(currentDialog != null)
				currentDialog.removeDialog();
			//////<div>//////
			usingTemplate = false;
			removeTemplate.setEnabled(false);
			if(!Display.menueSettingState.isUseOpenEdit())
				inputField.onEnableChange(true);
		});
		Layouter.GridBagLayouter layout = new GridBagLayouter(templateNavigation);
		layout.put(layout.instanceOf(addTemplate, 0, 0).setAnchor(Anchor.RIGHT).setFill(Fill.BOTH).setWeight(100, 100));
		layout.put(layout.instanceOf(removeTemplate, 1, 0).setAnchor(Anchor.LEFT).setFill(Fill.BOTH).setWeight(0, 100));
		return templateNavigation;
	}
	
	/**
	 * @see homelet.Display.State.States#enterStateAction()
	 */
	@Override
	public void enterStateAction(){
		super.enterStateAction();
		if(Display.menueSettingState.isPerformanceMode())
			displayCanvas.getCanvas().startRendering("Encode State", Display.menueSettingState.getFPS());
		if(Display.menueSettingState.isUseOpenEdit())
			inputField.onEnableChange(true);
	}
	
	/**
	 * @see homelet.Display.State.States#exitStateAction()
	 */
	@Override
	public void exitStateAction(){
		super.exitStateAction();
		displayCanvas.getCanvas().stopRendering();
	}
}
/**
 * <h1>feature list:</h1>
 * <ul>
 * <li>QRCode</li>
 * <ul>
 * <li>change style (liquefied, Normal, shaped)</li>
 * <li>change forgroundColor style(gradient, pain)</li>
 * <li>change backgroundColor style(blurred, pain)</li>
 * <li>add border style</li>
 * <li>add shade</li>
 * <li>add icon</li>
 * </ul>
 * <li>Add Text</li>
 * <ul>
 * <li>add Text box</li>
 * <li>change text font</li>
 * <li>change text color</li>
 * <li>change line spacing</li>
 * <li></li>
 * <li>add icon</li>
 * </ul>
 * </ul>
 */
