package Utilities.Converters;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import Utilities.Converter;
import Utilities.fileUtility;

/*
 * @Author: Fábio Moreira Duarte
 * This class is a converter, overrite some converter function to work on a specific format DOCx
 * */

public class OfficeWord extends Converter{

	@Override
	public void Inicialize() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Convert(String Input, String Output) {
		SetPath(Input,Output);
		try {
			Process process = Runtime.getRuntime().exec("cmd /c start batchs\\OfficeWord.bat");
			if(process.waitFor()==0){
				System.out.println("Converted");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void SetPath(String Input,String Output){
		//input will get the file source
		File inputFile = new File("paths\\convertFilePatch.txt");
		//output will set the temp file
		File outputFile = new File("paths\\DocxTempFile.txt");
		try {
			fileUtility.WriteFile(inputFile,Input);
			fileUtility.WriteFile(outputFile,Output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
