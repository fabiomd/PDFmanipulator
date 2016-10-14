package Classes.Formats;

import Classes.data;
import Utilities.Converter;
import Utilities.Converters.LibreOffice;

/*
 * @Author: Fábio Moreira Duarte
 * This class is a format, overrite some format function to work on a specific format DOCx
 * */

public class docx extends format{

	private Converter converter;
	
	public docx(data datafile) {
		super(datafile);
	}
	
	protected void GetSummary(){}
	//Inicialize the class, first it get the temp file that will keep the converted one
	@Override
	protected void Inicialize() {
		tempFileName = tempDirectory+"tempDocConverted.pdf";
		ResetPage(tempFileName);
		this.converter = new LibreOffice();
		//this.converter = new OfficeWord();
	}

	//that function create a pdf file from a docx file, using the JodConverter library, for more information visit : "http://www.artofsolving.com/opensource/jodconverter.html"
	@Override
	public void Create(String FileName){
		ResetPage(tempFileName);
		if(converter!=null){
			converter.Convert(FileName,tempFileName);
		}
	}
	
	//empty the temp file
	@Override
	public void ResetPage(String Filename) {
		format tempFormat = datafile.getFormat(datafile.matchFormat(".pdf"));
		tempFormat.ResetPage(Filename);
	}

}
