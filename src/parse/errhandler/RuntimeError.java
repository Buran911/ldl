package parse.errhandler;


public class RuntimeError extends Error {
    
    private String stackTrace;
    private String info;

    public RuntimeError(ErrorType errorType,String stackTrace,String info) {
	super();
	this.errorType = errorType;
	this.stackTrace = stackTrace;
	this.info= info;
    }

    public ErrorType getType() {
        return errorType;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getInfo() {
        return info;
    }

    public void setType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((info == null) ? 0 : info.hashCode());
	result = prime * result + ((stackTrace == null) ? 0 : stackTrace.hashCode());
	result = prime * result + ((errorType == null) ? 0 : errorType.hashCode());
	return result;
    }

    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	RuntimeError other = (RuntimeError) obj;
	if (info == null) {
	    if (other.info != null)
		return false;
	}
	else if (!info.equals(other.info))
	    return false;
	if (stackTrace == null) {
	    if (other.stackTrace != null)
		return false;
	}
	else if (!stackTrace.equals(other.stackTrace))
	    return false;
	if (errorType != other.errorType)
	    return false;
	return true;
    }

}
