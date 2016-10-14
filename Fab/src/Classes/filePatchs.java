package Classes;

import java.io.File;

import Utilities.fileUtility;

/*
 * @Author: Fábio Moreira Duarte
 * This class will keep all file information, for try reducing the looking on disk
 * */

public class filePatchs {
	//this String will keep the folder name
	private String parentName;
	
	public String GEtParentName(){
		return this.parentName;
	}
	
	public filePatchs(File parent){
		this.parentName = new String(parent.getName());
	}
	
	//this function will open the FIle directory
	public File GetFileParent(){
		return new File(directory.folderName + "/" + parentName);
	}
	
	//This function will return all files inside the current folder
	public File[] GetFilesChilds(){
		return fileUtility.GetChilds(directory.folderName + "/" + parentName);
	}
}
