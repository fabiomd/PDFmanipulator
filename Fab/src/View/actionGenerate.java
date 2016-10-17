package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import Classes.data;
import Classes.menu;
import Utilities.windowUtility;

/*
 * @Author: Fábio Moreira Duarte
 * This class is a ActionListener, will execute the actionPerformed function each time a event happens
 * */

public class actionGenerate implements ActionListener{

	//requires the data
	private data datafiles;
	//and a cover
	private menu cover = null;
	
	public actionGenerate(data data){
		this.datafiles = data;
		getCover();
	}
	
	//this function set a cover folder, first it search for a folder name that contains the word "capa" ou "capas", otherwise set the first folder as cover
	private void getCover(){
		for(int i=0;i<datafiles.getFilePatchSize();i++){
			if(datafiles.getFilePatch(i).GEtParentName().toLowerCase().indexOf("capa") != -1 || datafiles.getFilePatch(i).GEtParentName().toLowerCase().indexOf("capas") != -1){
				datafiles.GetMenu(i).placeOnSummary = false;
				datafiles.SetCover(i);
				cover = datafiles.GetMenu(i);
				return;
			}
		}
		datafiles.GetMenu(0).placeOnSummary = false;
		datafiles.SetCover(0);
		cover = datafiles.GetMenu(0);
	}
	
	
	private String getPatch(){
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home")+"\\Documentos");
		chooser.showSaveDialog(null);
		String temp = chooser.getSelectedFile().getPath();
		return temp;
	}
	//this function will be call every time a event happens
	public void actionPerformed(ActionEvent event) {
		int count = 0;
		//check if exist a cover, -1 is the cover default
		if(cover!=null){
			//count how many kinds of then are selected
			for(int i=0;i<cover.GetMenusSize();i++){
				if(cover.GetMenu(i).isSelected()){
					count++;
				}
			}
			//if are only one then is OK to proced
			if(count == 1){
				//datafiles.Create("test.pdf");
				String fileName = getPatch();
				if(fileName!=null){
					datafiles.Create(fileName);
				}
			}
			//Otherwise send a error Message
			else{
				if(count>1){
				    // show a joptionpane dialog using showMessageDialog
					windowUtility.errorMessage("Escolha apenas uma capa");
				}
				else{
					windowUtility.errorMessage("Escolha uma capa");
				}
			}
		}
	}
}
