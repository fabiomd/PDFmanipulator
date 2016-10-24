package Classes.Formats;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedDirectoryStreamException;
import java.util.Date;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import Classes.data;
import Utilities.windowUtility;

/*
 * @Author: Fábio Moreira Duarte
 * This class is a format, overrite some format function to work on a specific format PDF
 * */

public class PDF extends format{
	
	private PDDocument tempDocument = null;
	
	public PDF(data datafile) {
		super(datafile);
		// TODO Auto-generated constructor stub
	}
	
	//Inicialize the class, first it get the temp file that will keep the converted one
	protected void Inicialize(){
		tempFileName = tempDirectory+"temp.pdf";
		ResetPage(tempFileName);
	}

	//This function seeks the selected cover buttons down the cover tab and take it
	private void ReadCover() throws IOException{
		if(cover != -1){
			for(int i=0;i<datafile.GetMenu(cover).GetMenusSize();i++){
				if(datafile.GetMenu(cover).GetMenu(i).isSelected()){
					File[] files = datafile.getFilePatch(cover).GetFilesChilds();
					ReadDoc(files[i]);
					break;
				}
			}
		}
	}
	
	//this function will sleep 1 until the file is modify or the limit is reach
	private boolean waitConvertion(File file,Date lastModify,int max){
		int count = 0;
		while(new Date(file.lastModified()).equals(lastModify) && count < max){
			try {
				Thread.sleep(1000);
				count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count < max;
	}
	
	
	private File convert(format tempFormat,File file){
		File fileTemp = new File(tempFormat.tempFileName);
		Date lastModify = new Date(fileTemp.lastModified());
		tempFormat.Create(file.getPath());
		if(!waitConvertion(fileTemp,lastModify,180)){
			windowUtility.errorMessage("Tempo excedido");
		}
		return fileTemp;
	}
	
	//This function is responsible for loading the file and reading the pages, if its not in pdf format, will automatically convert it and add
	private void ReadDoc(File file) throws IOException{
		File tempFile = file;
		format tempFormat = null;
		//This part is the one responsable for checking if the file required is already on pdf format, if not will seek his format and convert it
		if(!match(file.getName())){
			tempFormat = datafile.getFormat(datafile.matchFormat(file.getName()));
			tempFile = convert(tempFormat,file);
		}
		
		PDDocument document = PDDocument.load(tempFile,MemoryUsageSetting.setupTempFileOnly());
		try {
			loadTemp();
			if(document!=null && tempDocument!=null){
				for(int i=0;i<document.getNumberOfPages();i++){
					PDPage tempPag = (PDPage)document.getDocumentCatalog().getPages().get(i);
					formatPage(tempPag);
					tempDocument.importPage(tempPag);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(tempDocument!=null){
				unloadTemp();
			}
			if(document!=null){
				document.close();
			}
			if(tempFormat != null){
				tempFormat.ResetPage();
			}
		}
	}
	
	private void loadTemp() throws IOException{
		tempDocument = PDDocument.load(new File(tempFileName),MemoryUsageSetting.setupTempFileOnly());
	}
	
	private void unloadTemp() throws IOException{
		tempDocument.save(tempFileName);
		tempDocument.close();
	}
	
	private void unloadTemp(String fileName) throws IOException{
		tempDocument.save(fileName);
		tempDocument.close();
	}
	
	
	public void ResetPage(String Filename){
		PDDocument temp = new PDDocument(MemoryUsageSetting.setupTempFileOnly());
		try {
			if(temp!=null){
				temp.save(tempFileName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(temp!=null){
				try {
					temp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void Create(String fileDirectory) {
		Inicialize();
		try {
			ReadCover();
			//loadTemp();
			//summary.Generate(tempDocument);
			//unloadTemp();
			for(int i=0;i<datafile.GetMenusSize();i++){
				if(i!=cover){
					for(int j=0;j<datafile.GetMenu(i).GetMenusSize();j++){
						if(datafile.GetMenu(i).GetMenu(j).isSelected()){
							File[] files = datafile.getFilePatch(i).GetFilesChilds();
						    ReadDoc(files[j]);
						}
					}
				}
			}
			loadTemp();
			if(tempDocument!=null){
				unloadTemp(CheckFileExtension(fileDirectory));
				windowUtility.errorMessage("PDF Criado com sucesso");
			}
		} catch (ClosedDirectoryStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ResetPage(tempFileName);
		}
	}
	
	//this function will set all page format to A4
	public void formatPage(PDPage page){
		page.setMediaBox(PDRectangle.A4);
	}

	@Override
	public void ResetPage() {
		ResetPage(tempFileName);
	}	
}
