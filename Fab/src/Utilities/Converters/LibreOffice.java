package Utilities.Converters;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import Utilities.Converter;

/*
 * @Author: Fábio Moreira Duarte
 * This class is a converter, overrite some converter function to work on a specific format DOCx
 * Attention some complex pdf file can be distorced, recomended for simple ones
 * */

public class LibreOffice extends Converter{

	private int Port = 8100;
	
	@Override
	public void Inicialize() {
		StartServer();
	}

	private void StartServer(){
		try {
			Runtime.getRuntime().exec("cmd /c start batchs\\LibreOffice.bat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void Convert(String Input, String Output) {
		//input will get the file source
		File inputFile = new File(Input);
		//output will set the temp file
		File outputFile = new File(Output); 
		// connect to an OpenOffice.org instance running on port 8100
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(Port);
		try {
			connection.connect();
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// convert
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(inputFile, outputFile); 
		// close the connection
		connection.disconnect();
	}
	
}
