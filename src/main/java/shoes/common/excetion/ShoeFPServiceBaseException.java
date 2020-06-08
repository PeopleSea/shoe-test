package shoes.common.excetion;

public class ShoeFPServiceBaseException extends SubsystemBaseException {
	
	private static final long serialVersionUID = 1056032712393658703L;

	public ShoeFPServiceBaseException(String errorCode) {
		super(errorCode, "");
	}

	public ShoeFPServiceBaseException(String errorCode, String description, Throwable throwable) {
		super(errorCode, description, throwable);
	}

	public ShoeFPServiceBaseException(String errorCode, String description) {
		super(errorCode, description);
	}

	public ShoeFPServiceBaseException(String errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}
}
