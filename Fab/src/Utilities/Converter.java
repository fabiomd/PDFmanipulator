package Utilities;

/*
 * @Author: Fábio Moreira Duarte
 * This abstract class functions a convert must have
 * */

public abstract class Converter {
	public Converter(){
		Inicialize();
	}
	
	public abstract void Inicialize();
	
	public abstract void Convert(String Input,String Output);
}
