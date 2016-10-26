package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BoxLayout;
import Classes.data;
import Classes.menu;

/*
 * @Author: Fábio Moreira Duarte
 * This class defines a panel
 * */

public class leftPanel extends panel{
	
	//Set the Panel dimensions
	public leftPanel(Dimension screenDimension,data data,Point point,Dimension dimension) {
		super(screenDimension,data,point,dimension);
	}

	private static final long serialVersionUID = 1L;
	
	//Inicialize it
	public void Inicialize() {
		BoxLayout boxlayoutLeft = createBox(jPanel);
        SetJPanel(jPanel,boxlayoutLeft,point,dimension);
	}
	
	//Create the buttons for it
	public void CreateFileButtons(){
		Dimension dimension;
		if(windowSize != null){
			//set windowns dimension
			dimension = new Dimension((int)(windowSize.width * 0.8f) ,(int)(windowSize.height*1f));
		}else{
			dimension = new Dimension(200,30);
		}
		//set color for button
		Color background = Color.black;
		Color foreground = Color.white;
		//fills the buttons
		for(int i=0;i<datafiles.GetMenusSize();i++){
			menu temp1 = datafiles.GetMenu(i);
			temp1.getTittle().setSize(dimension);
			temp1.getTittle().setBackground(background);
			temp1.getTittle().setForeground(foreground);
			temp1.getTittle().addActionListener(new actionMenu(datafiles));
        	jPanel.add(datafiles.GetMenu(i).getTittle());

        	for(int j=0;j<datafiles.GetMenu(i).GetMenusSize();j++)
        	{
        		jPanel.add(datafiles.GetMenu(i).GetMenu(j));
        	}
        }
	}
}