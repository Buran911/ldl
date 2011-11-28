package parse.errhandler;

public abstract class Error {
    private boolean isParseError;
    private boolean isRuntimeError;
    protected ErrorType errorType;
    
    {
	isParseError = false;
	isRuntimeError = false;
    }
    
    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    public boolean isParseError() {
        return isParseError;
    }

    public void setParseError() {
        this.isParseError = true;
    }

    public boolean isRuntimeError() {
        return isRuntimeError;
    }

    public void setRuntimeError() {
        this.isRuntimeError = true;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
