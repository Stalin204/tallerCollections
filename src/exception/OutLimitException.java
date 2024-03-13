package exception;

public class OutLimitException extends Exception{
	public OutLimitException(){super();}

	public OutLimitException(String msj)
	{
		super(msj);
	}
	public OutLimitException(int i, int tam)
	{
		super("el indice " + i + " al que intentas acceder no existe en una lista de tamaño " + tam +" ya que el indice mayor para esta lsita es el " + (tam-1));
	}
}
