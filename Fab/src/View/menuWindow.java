package View;

import Interfaces.init;
import View.leftPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Classes.data;

/*
 * @Author: Fábio Moreira Duarte
 * This is the main window, keep the user interface
 * */

public class menuWindow extends JFrame implements init {
	
	private static final long serialVersionUID = 1L;
	private data datafiles;
	//get screenSize
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public menuWindow(data datafiles){
		this.datafiles = datafiles;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Set inicial size
	private void SetWindowSize(){
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(getPreferredSize());
	}
	
	//inicialize it
	public void Inicialize() {
		SetWindowSize();
		CreatePanel();
	}
	
	//Configure the scroll panel
	private void ConfigureJScrollPane(JScrollPane jScrollPane,Point point,JPanel jPanel){
		jScrollPane.setLocation(point);
        jScrollPane.setViewportView(jPanel);
	}
	
	//configure the layout
	private void ConfigureLayout(GroupLayout groupLayout,JScrollPane jScrollPane){
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane, GroupLayout.Alignment.TRAILING, (int)(width * .3f), (int)(width * .65f), (int)(width * .7f)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane, GroupLayout.Alignment.TRAILING, (int)(height * .6f), (int)(height * .65f), (int)(height * 1f)));
		jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
	}
	
	//create the panel
	private void CreatePanel(){
		JScrollPane jScrollPane = new JScrollPane();
        //Define se pode ser redimencionado
        setResizable(false);   
        setBackground(Color.yellow);
        int height = this.getHeight();
		int width = this.getWidth();
        panel jPanelLeft = new leftPanel(getPreferredSize(),datafiles,new Point(0,0),new Dimension(width*2/3,height));    //painel esquerdo
        panel jPanelRight = new rightPanel(getPreferredSize(),datafiles,new Point(width*2/3 + 1,0),new Dimension(width*1/3,height));    //painel direito 
        ((leftPanel)jPanelLeft).CreateFileButtons();       
        Point point = new Point(20,80);
        ConfigureJScrollPane(jScrollPane,point,jPanelLeft.jPanel);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout);
        ConfigureLayout(groupLayout,jScrollPane);
        add(jScrollPane);
        add(jPanelRight.jPanel);
	}
}
