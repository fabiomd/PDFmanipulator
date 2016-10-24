package Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/*@Author : Fábio Moreira Duarte
 *This code has a lot of file function
 * */

public class fileUtility {
	
	//Read all files from the directory and return it
	public static File[] GetChilds(String directory){
		File file =  new File(directory);
		File[] sub_files = null;
		if(file.exists()){
			sub_files = file.listFiles();
		}		
		return sub_files;
	}
	
	//wrte a string content in a file
	public static void WriteFile(File file,String content) throws IOException{
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}
	
	public static String removeExtension(String fileName){
		return fileName.split("\\.")[0];
	}
}
