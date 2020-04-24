package org.codejudge.sb.exception.model;

public class CommonException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Error error;

	public CommonException(Error error) {
		super();
		this.error = error;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
