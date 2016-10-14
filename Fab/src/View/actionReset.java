package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.data;

/*
 * @Author: Fábio Moreira Duarte
 * This class is a ActionListener, will execute the actionPerformed function each time a event happens
 * */

public class actionReset implements ActionListener {
private data datafile;
	
	public actionReset(data data){
		datafile = data;
	}
	
	//this function will close everything and uncheck all buttons
	public void actionPerformed(ActionEvent event) {   
		
        for(int i =0 ;i<datafile.GetMenusSize();i++){
             for(int j=0;j<datafile.GetMenu(i).GetMenusSize();j++){
            	 datafile.GetMenu(i).Selected(false);
            	 datafile.GetMenu(i).GetMenu(j).setSelected(false);
            	 datafile.GetMenu(i).GetMenu(j).setVisible(false);
            }
        }		
	}
}
