package exception;

public class NoElementsException extends Exception{

	public NoElementsException(){super("La lista a la que intentas acceder no tiene elementos");}

	public NoElementsException(String msj){
		super(msj);
	}

}
