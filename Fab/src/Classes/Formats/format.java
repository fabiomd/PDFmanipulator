package Classes.Formats;

import java.io.IOException;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import Classes.Summary;
import Classes.data;
import Interfaces.create;

/*
 * @Author: Fábio Moreira Duarte
 * This abstract class functions a format must have
 * */

public abstract class format implements create{
	
	protected data datafile = null;
	public int cover = -1;
	protected Summary summary;
	protected String tempDirectory = "TEMP\\";
	protected String tempFileName;
	
	//this function compares the class name, if is equals then the string passed is a format recognizeble by data
	public boolean match(String docType){
		String[] temp = docType.split("\\.");
		return getClass().getSimpleName().toLowerCase().equalsIgnoreCase(temp[temp.length - 1].toLowerCase());
	}
	
	public abstract void ResetPage(String Filename);
	//class constructor
	public format(data datafile){
		this.datafile = datafile;
		GetSummary();
		Inicialize();
	}
	
	protected abstract void Inicialize();
	
	protected void GetSummary(){
		this.summary = new Summary(datafile);
	}
	
	//this part read the cover marked
	protected PDPageTree generateCover() throws IOException{
		PDPageTree coverPage = new PDPageTree();
		//if exist a cover do
		if(cover != -1){
			//check each cover is selected
			for(int i=0;i<datafile.GetMenu(cover).GetMenusSize();i++){
				if(datafile.GetMenu(cover).GetMenu(i).isSelected()){
					//get all file from cover
					File[] files = datafile.getFilePatch(cover).GetFilesChilds();
					//load it
					PDDocument temp = PDDocument.load(files[i]);
					//read all file pages
					coverPage = temp.getPages();
					temp.close();
					break;
				}
			}
		}
		return coverPage;
	}
	
	//this function cleck if the fileExtension is passed
	protected String CheckFileExtension(String directory){
		//if is tru return the string passed
		if(directory.toLowerCase().contains("."+ this.getClass().getSimpleName().toString().toLowerCase())){
			return directory;
		}
		//otherwise concated the "." and the classname. The classname is the format it represents
		else{
			return directory + "."+ this.getClass().getSimpleName().toString().toLowerCase();
		}
	}
	
	public abstract void Create(String FileName);
}
