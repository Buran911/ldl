package parse.errhandler;

import application.util.StackTrace;

public class RuntimeError extends Error {
    private Exception exception;
    private String info;

    public RuntimeError(ErrorType errorType, Exception exception, String info) {
	super();
	this.errorClass = ErrorType.getErrorClass(errorType);
	this.errorType = errorType;
	this.exception = exception;
	this.info = info;
    }

    public ErrorType getType() {
	return errorType;
    }

    public String getStackTrace() {
	return StackTrace.getStackTrace(exception);
    }

    public String getInfo() {
	return info;
    }

    public void setType(ErrorType errorType) {
	this.errorType = errorType;
    }

    public void setInfo(String info) {
	this.info = info;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((exception == null) ? 0 : exception.hashCode());
	result = prime * result + ((info == null) ? 0 : info.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	RuntimeError other = (RuntimeError) obj;
	if (exception == null) {
	    if (other.exception != null)
		return false;
	}
	else if (!exception.equals(other.exception))
	    return false;
	if (info == null) {
	    if (other.info != null)
		return false;
	}
	else if (!info.equals(other.info))
	    return false;
	return true;
    }

}
