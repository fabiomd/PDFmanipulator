package View;

import Classes.data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @Author: Fábio Moreira Duarte
 * This class is a ActionListener, will execute the actionPerformed function each time a event happens
 * */

public class actionMenu implements ActionListener {

	private data datafile;
	
	public actionMenu(data data){
		datafile = data;
	}
	
	//this function will close everything that is not child of the selected button
	public void actionPerformed(ActionEvent event) {   
		
        for(int i =0 ;i<datafile.GetMenusSize();i++){
        	boolean temp = event.getSource()==datafile.GetMenu(i).getTittle();
        	datafile.GetMenu(i).Selected(temp);
             for(int j=0;j<datafile.GetMenu(i).GetMenusSize();j++){
            	 datafile.GetMenu(i).GetMenu(j).setVisible(temp);
            }
        }		
	}

}
