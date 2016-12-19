package Classes;

import Utilities.fileUtility;
import Utilities.windowUtility;
import Interfaces.init;
import Interfaces.create;
import Classes.Formats.*;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

/*
 * @Author: Fábio Moreira Duarte
 * This class is the responsible for keeping the data of all program
 * */

public class data implements init,create{
	
	//This List will keep the menus, will be used for construct the view window
	private ArrayList<menu> menus = new ArrayList<menu>();
	//this List keep all the file formats this software can recognize, future formats can be implement
	private ArrayList<format> suportedFormats = new ArrayList<format>();
	//This List will keep the main folder patch, so we wont need reading it again and again for each call
	private ArrayList<filePatchs> filepatchs = new ArrayList<filePatchs>();
	
	//Class Constructor will add all formats available
	public data(){
		suportedFormats.add(new PDF(this));
		suportedFormats.add(new docx(this));
	}
	
	public int matchFormat(String filename){
		String[] extension = filename.split("\\.");
		for(int i=0;i<suportedFormats.size();i++){
			if(suportedFormats.get(i).match(extension[extension.length - 1])){
				return i;
			}
		}
		return -1;
	}
	
	public format getFormat(int index){
		return suportedFormats.get(index);
	}
	//This function will determinate each one of menus will be the cover, because all files need a cover and can must be only one
	public void SetCover(int cover){
		suportedFormats.get(0).cover = cover;
	}
	
	//This function will create a file on the directory passed by parameter
	public void Create(String directory){
		suportedFormats.get(0).Create(directory);
	}

	public filePatchs getFilePatch(int i){
		return this.filepatchs.get(i);
	}
	
	public int getFilePatchSize(){
		return this.filepatchs.size();
	}
	//this function will initialize the data
	public void Inicialize(){
		fillMenus();
	}
	
	private String getExtension(String filename){
		if(filename.contains(".")){
			String[] temp = filename.split("\\.");
			return temp[temp.length - 1];
		}
		else{
			return filename;
		}
	}
	
	private boolean checkSupported(String filename){
		for(int i=0;i<suportedFormats.size();i++){
			if(suportedFormats.get(i).match(getExtension(filename))){
				return true;
			}
		}
		return false;
	}
	
	//this function will read all data from the directory, and load it to the menus list
	private void fillMenus(){
		//First it call the fileUtility function GetChilds, that will return all files inside the folder
		File[] files = fileUtility.GetChilds(directory.folderName);
		if(files == null){
			windowUtility.errorMessage("Empty folder\n");
			return;
		}
		//Then for each folder he will call the function GetChilds, getting all the files inside of it
		for(int i=0;i<files.length;i++){
			if(!files[i].getName().toLowerCase().equals(".gitignore") && files[i].isDirectory()){
				JButton temp_Jbutton = new JButton();
				temp_Jbutton.setText(files[i].getName());
				temp_Jbutton.setHorizontalAlignment(SwingConstants.LEFT);
				temp_Jbutton.setName(files[i].getName());
				temp_Jbutton.setMaximumSize(new Dimension(650, 50));
				menu temp_menu =  new menu(temp_Jbutton);
				File[] sub_files = fileUtility.GetChilds(directory.folderName + "/" + files[i].getName());
				filepatchs.add(new filePatchs(files[i]));
				//Next for each file will create a menu and initialize it
				int j=0;
				if(sub_files != null){
					for(j=0;j<sub_files.length;j++){
						if(checkSupported(sub_files[j].getName()) && sub_files[j].isFile()){
							JCheckBox temp_jcheckbox = new JCheckBox();
							temp_jcheckbox.setText(sub_files[j].getName());
							temp_jcheckbox.setVisible(false);
							temp_jcheckbox.setSelected(false);
							temp_menu.addCheckbox(temp_jcheckbox);  
						}
					}
					//then add it to the list
				}
				if(j>0){
					menus.add(temp_menu);
				}
			}
		}
	}
	
	public int GetMenusSize(){
		return menus.size();
	}
	
	public menu GetMenu(int i){
		return menus.get(i);
	}
}
