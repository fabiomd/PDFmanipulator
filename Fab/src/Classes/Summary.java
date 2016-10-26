package Classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import Classes.Formats.format;
import Classes.Formats.PDF;

/*
 * @Author: Fábio Moreira Duarte
 * This class hold the definition of summary
 * */

public class Summary{
	
	//needs access the data
	private data datafile;
	private format format;
	//It passed by constructor
	public Summary(format format){
		this.datafile = format.getDatafile();
		this.format = format;
	}
	
	//This Function return a PDPage format, for more information go access ":https://pdfbox.apache.org/"
	public void Generate(PDDocument mainDoc) throws IOException{
		PDDocument document = new PDDocument(MemoryUsageSetting.setupTempFileOnly());
		PDPage summaryPage = new PDPage();	
		PDPageTree summaryPages = new PDPageTree();
		// Create a new font object selecting one of the PDF base fonts
		PDFont fontStyle = PDType1Font.HELVETICA_BOLD;
		int fontSize = 22;
		float heighTitle = summaryPage.getMediaBox().getHeight()*.85f;
		float Indetation = summaryPage.getMediaBox().getWidth()*.1f;
		float IndexBegin = summaryPage.getMediaBox().getHeight()*.8f;

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, summaryPage);		
		int pageCount = 1;
		
		//This part write the Summary String for a title 
		String Tittle = "Sumário";
		float center = summaryPage.getMediaBox().getWidth()*.5f - getStringWidth(Tittle,summaryPage.getMediaBox().getWidth(),fontStyle,fontSize)/2;
		writeLine(center,heighTitle,contentStream,Tittle,fontStyle,fontSize);
		fontSize=10;
		//This List will keep each Line
		ArrayList<String> Lines = new ArrayList<String>();
		//this List will keep each chapter start page number
		ArrayList<String> PagNumbers = new ArrayList<String>();
		
		//In this loop all line and pages are readed
		for(int i=0;i<datafile.GetMenusSize();i++){
			for(int j=0;j<datafile.GetMenu(i).GetMenusSize();j++){
				if(datafile.GetMenu(i).placeOnSummary && datafile.GetMenu(i).GetMenu(j).isSelected()){
					File[] tempFiles = datafile.getFilePatch(i).GetFilesChilds();
					Lines.add(removeExtension(tempFiles[j].getName()));
					PagNumbers.add(String.valueOf(pageCount++));
				}
			}
		}
		//This function call will be responsible for writing in the summary page
		writeLines(0,IndexBegin,contentStream,Lines,PagNumbers,"-",fontStyle,fontSize,summaryPage.getMediaBox().getWidth(),Indetation);
		// Make sure that the content stream is closed:
		//we close the content Stream
		summaryPages.add(summaryPage);
		contentStream.close();
		format temp = datafile.getFormat(0);
		for(int i=0;i<summaryPages.getCount();i++){
			((PDF)temp).formatPage(summaryPages.get(i));
			mainDoc.importPage(summaryPages.get(i));
		}
		if (document != null) {
			document.close();
		}
		//return summaryPages;
	}
	
	//This function will only remove all extension from the string, for example removeExtension("test.pdf") returns "test"
	private String removeExtension(String filename){
		for(int i=filename.length() - 1;i>0;i--){
			if(filename.charAt(i) == '.'){
				return filename.substring(0, i);
			}
		}
		return filename;
	}
	
	//write a string on content and place the text on x,y page position
	@SuppressWarnings("deprecation")
	private void writeLine(float x,float y,PDPageContentStream contentStream,String text,PDFont font,int fontSize) throws IOException{
		contentStream.beginText();
		contentStream.setFont(font, fontSize);
        contentStream.moveTextPositionByAmount(x, y);
        contentStream.drawString(text);
        contentStream.endText();
	}
	
	//this function will return the String width based on font used, fontSize and the pageWidth
	private float getStringWidth(String text,float pageWidth,PDFont font,int fontSize) throws IOException{
		return font.getStringWidth(text.substring(0,text.length())) / 1000 * fontSize;
	}
	
	//This function will write allLines based on the format: chapter title ----- page number
	private void writeLines(float x,float y,PDPageContentStream contentStream,ArrayList<String> chapterName,ArrayList<String> pagNumbers,String fillCaracter,PDFont font,int fontSize,float pageWidth,float indetantion) throws IOException{
		//this variable determinated the space between each line
		int lineHeight = fontSize + 2;
		float tempY = y;
		//while still lines to write
		for(int i=0;i<chapterName.size();i++){
			String Line = "";
			//call this function to break a String in substrings for cases the text is bigger than the page width
			ArrayList<String> temp = breakLine(chapterName.get(i),pageWidth*.7f,pageWidth,font,fontSize);
			//calculate the width of each String
			float chapterWidthSize = getStringWidth(temp.get(0),pageWidth,font,fontSize);
			float PagNumberWidthSize = getStringWidth(pagNumbers.get(0),pageWidth,font,fontSize);
			float caracterWidthSize = getStringWidth(fillCaracter,pageWidth,font,fontSize);
			//this loop will determinated how many caracters in example "-", will be necessary to keep the format: chapter title ----- page number
			for(int j=0;j<(int)((pageWidth - 2*indetantion - chapterWidthSize - PagNumberWidthSize)/caracterWidthSize);j++){
	        	Line += fillCaracter;
	        }
			//Write this caracters aforementioned
			writeLine(chapterWidthSize + indetantion,tempY,contentStream,Line,font,fontSize);
			//write the page Number
			writeLine(pageWidth - indetantion,tempY,contentStream,pagNumbers.get(i),font,fontSize);
			for(int k=0;k<temp.size();k++){
				//Write the Lines
				writeLine(x + indetantion,tempY,contentStream,temp.get(k),font,fontSize);
				tempY -= lineHeight;
			}
		}
	}
	
	//this fuction breaks a string on a list of substrings that none substring is larger then maxWidth pass by parameter
	private ArrayList<String> breakLine(String text,float maxWidth,float pageWidth,PDFont font,int fontSize) throws IOException{
		ArrayList<String> temp = new ArrayList<String>();
		//check if the string is larger then maxWidth, if it is
		if(getStringWidth(text,pageWidth,font,fontSize) > maxWidth){
			//split it for each space
			String[] parts = text.split(" ");
			String temp2 = "";
			for(int i=0;i<parts.length;i++){
				//try to concatenat is less the maxwidth, do it, otherwise save the current string and reset
				if(getStringWidth(temp2 + " " + parts[i],pageWidth,font,fontSize) <= maxWidth){
					temp2+=parts[i]+" ";
				}else{
					temp.add(new String(temp2));
					temp2=parts[i] + " ";
				}
			}
			temp.add(new String(temp2));
		}
		else{
			//if is not just return the string
			temp.add(text);
		}
		return temp;
	} 
}
