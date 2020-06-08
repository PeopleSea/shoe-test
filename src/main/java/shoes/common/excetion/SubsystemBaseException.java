package shoes.common.excetion;

import org.apache.commons.lang3.StringUtils;

public class SubsystemBaseException extends RuntimeException implements IErrorBaseException {
	private static final long serialVersionUID = 8392945509706894296L;

	protected String errorCode;

	public SubsystemBaseException(String errorCode, String description, Throwable throwable) {
		super(StringUtils.isBlank(description) ? errorCode
				: (StringUtils.isBlank(errorCode) ? description : ("[" + errorCode + "] " + description)), throwable);
		this.errorCode = errorCode;
	}

	public SubsystemBaseException(String errorCode, String description) {
		super(StringUtils.isBlank(description) ? errorCode
				: (StringUtils.isBlank(errorCode) ? description : ("[" + errorCode + "] " + description)));
		this.errorCode = errorCode;
	}

	public SubsystemBaseException(String description) {
		super(description);
	}

	public SubsystemBaseException(String errorCode, Throwable throwable) {
		this(errorCode, errorCode, throwable);
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
}