package exception;

public class FileManagerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FileManagerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	public FileManagerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public FileManagerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	public FileManagerException() {
		super();
	}
	public FileManagerException(String msj) {
		super(msj);
	}


}
