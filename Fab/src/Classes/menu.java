package Classes;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/*
 * @Author: Fábio Moreira Duarte
 * This class keep the button for the window user interface
 * */

public class menu {
	//this will keep a button with the folder name, the folder name provides information about what kind of data it keeps
	private JButton title;
	//This list of button is responsable for keeping the file the user selected on the user interface for do some operations
    private ArrayList<JCheckBox> sub_menus = new ArrayList<JCheckBox>();
    //this can allow the developer selected what kind of files are allowed to be part of the summary, its only keeping the cover out of it, but still can add some more
    public boolean placeOnSummary = true;
    //This two colors allows a better view on the user interface window, the pressed JButton will swap his colors
    private Color backGround;
    private Color foreGround;
    
    //This function will Swap the button color for allowing the user visualize the area he selected
    public void Selected(boolean temp){
    	if(temp){
    		this.title.setBackground(backGround);
        	this.title.setForeground(foreGround);
    	}
    	else{
    		this.title.setBackground(foreGround);
    		this.title.setForeground(backGround);
    	}
    }
    
    //Just a function to prevent out of bounds exception
    public int GetMenusSize(){
    	return this.sub_menus.size();
    }
    
    public JCheckBox GetMenu(int i){
    	return this.sub_menus.get(i);
    }
    
    public menu(JButton jbutton){
    	this.title = jbutton;
    	this.backGround = new Color(this.title.getBackground().getRGB());
    	this.foreGround = new Color(this.title.getForeground().getRGB());
    }
    
    public JButton getTittle(){
    	return this.title;
    }
    
    public void addCheckbox(JCheckBox menu){
    	this.sub_menus.add(menu);
    }
}
