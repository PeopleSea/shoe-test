package shoes.common.excetion;

public class ShoeServiceBaseException extends SubsystemBaseException {
	
	private static final long serialVersionUID = 1056032712393658703L;

	public ShoeServiceBaseException(String errorCode) {
		super(errorCode, "");
	}

	public ShoeServiceBaseException(String errorCode, String description, Throwable throwable) {
		super(errorCode, description, throwable);
	}

	public ShoeServiceBaseException(String errorCode, String description) {
		super(errorCode, description);
	}

	public ShoeServiceBaseException(String errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}
}
