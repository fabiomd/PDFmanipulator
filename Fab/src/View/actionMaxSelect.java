package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.data;

public class actionMaxSelect implements ActionListener {
	private data datafile;
	private boolean value = true;
	
	public actionMaxSelect(data data,boolean value){
		this.datafile = data;
		this.value = value;
	}
	
	//this function will close everything and uncheck all buttons
	public void actionPerformed(ActionEvent event) {   
		
        for(int i =0 ;i<datafile.GetMenusSize();i++){
        	if(datafile.GetMenu(i).isSelected()){
        		for(int j=0;j<datafile.GetMenu(i).GetMenusSize();j++){
        			datafile.GetMenu(i).GetMenu(j).setSelected(value);
        		}
        		break;
        	}
        }		
	}
}
