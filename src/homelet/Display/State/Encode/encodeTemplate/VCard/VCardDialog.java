/**
 * @author HomeletWei
 * @date Apr 10, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Display.State.Encode.encodeTemplate.VCard;

import homelet.Display.State.Encode.encodeTemplate.TemplateDialog;
import homelet.Utile.FileHandler;
import homelet.Utile.encodeTemplate.VCardTemplateLoader;

import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 10, 2018
 */
public class VCardDialog extends TemplateDialog{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	VCardPanel vcardPanel;
	
	/**
	 * constructor for VCardDialog
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public VCardDialog(Frame frame){
		super(frame, "VCard 1.0 BETA");
		VCardTemplateLoader tp = FileHandler.getFileHander().loadVCardTemplate("/Users/homeletwei/Workspaces/MyEclipse 2017 CI/QRcode V2.0/Template-VCard.qrcodeTemplate");
		//	tp.print();
		this.vcardPanel = new VCardPanel(this, tp);
		//	vcardPanel.addApplyAction(new ActionListener(){
		//
		//	    @Override
		//	    public void actionPerformed(ActionEvent e){
		//		VCardDialog.this.closeDialog();
		//	    }
		//	});
		addTemplatePanel(vcardPanel);
	}
	
	@Override
	public void showDialog(){
		if(!vcardPanel.isReadyForDisplaying()){
			if(vcardPanel.initialize()){
				super.showDialog();
			}
			else{
				this.closeDialog();
			}
		}
		else{
			super.showDialog();
		}
	}
	
	@Override
	public String getResult(){
		return vcardPanel.getResult();
	}
}
