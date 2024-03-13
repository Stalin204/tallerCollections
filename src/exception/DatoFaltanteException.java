package exception;

public class DatoFaltanteException extends Exception{
	public DatoFaltanteException(){super();}
	public DatoFaltanteException(String msj){
		super(msj);
	}
}
