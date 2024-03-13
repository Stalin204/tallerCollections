package exception;

public class JavaFXException extends Exception{

	public JavaFXException() {
		super();
	}

	public JavaFXException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public JavaFXException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public JavaFXException(String arg0) {
		super(arg0);
	}

	public JavaFXException(Throwable arg0) {
		super(arg0);
	}

}
