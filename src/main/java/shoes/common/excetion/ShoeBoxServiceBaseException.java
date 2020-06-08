package shoes.common.excetion;

public class ShoeBoxServiceBaseException extends SubsystemBaseException {
	
	private static final long serialVersionUID = 1056032712393658703L;

	public ShoeBoxServiceBaseException(String errorCode) {
		super(errorCode, "");
	}

	public ShoeBoxServiceBaseException(String errorCode, String description, Throwable throwable) {
		super(errorCode, description, throwable);
	}

	public ShoeBoxServiceBaseException(String errorCode, String description) {
		super(errorCode, description);
	}

	public ShoeBoxServiceBaseException(String errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}
}
