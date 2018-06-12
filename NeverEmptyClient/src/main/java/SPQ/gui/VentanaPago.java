package SPQ.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import SPQ.controller.NeverEmptyController;

public class VentanaPago extends JFrame implements ActionListener{
	
	NeverEmptyController neverEmptyController;
	JPanel pSavedData, pNewData;
	
	
	public VentanaPago (NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
