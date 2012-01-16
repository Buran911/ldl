package parse.errhandler;

public abstract class Error implements Cloneable {
    private boolean isParseError;
    private boolean isRuntimeError;
    protected ErrorClass errorClass;
    protected ErrorType errorType;
    
    {
	isParseError = false;
	isRuntimeError = false;
    }

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

    public ErrorClass getErrorClass() {
        return errorClass;
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
    
    @Override
    public abstract Error clone();
}
