package View;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Classes.data;
import Interfaces.init;

/*
 * @Author: Fábio Moreira Duarte
 * This class abstract all function a panel must has
 * */

public abstract class panel extends JFrame implements init{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPanel jPanel = new JPanel();
	protected Dimension windowSize = null;
	protected data datafiles;
	
	public abstract void Inicialize();
	
	//contructor need a dimension and a data to work
	public panel(Dimension dimension,data data){
		this.windowSize = dimension;
		this.datafiles = data;
		Inicialize();
	}
	
	//boxlayout
	protected BoxLayout createBox(JPanel jpanel){
		BoxLayout temp = new BoxLayout(jpanel,BoxLayout.PAGE_AXIS);
		return temp;
	}
	
	//set a panel
	protected void SetJPanel(JPanel jPanel,BoxLayout boxlayout,Point point,Dimension dimension){
		jPanel.setLayout(boxlayout);
		jPanel.setLocation(point);
		jPanel.setSize(dimension);
	}
	
	//create a textArea
	protected JTextArea CreateTextArea(Dimension dimension,float alignment){
		JTextArea jTextArea = new JTextArea();
		jTextArea.setEnabled(false);
		jTextArea.setSize(dimension);
		jTextArea.setAlignmentX(alignment);
		return jTextArea;
	}
	
	//Set a button
	protected JButton SetJButton(String text,Dimension dimension,Point point,float alignment){
		JButton jButton = new JButton();
		jButton.setText(text);
		jButton.setSize(dimension);
		jButton.setLocation(point);
		jButton.setAlignmentX(alignment);
		return jButton;
	}
}
