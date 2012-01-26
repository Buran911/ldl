package generation.idtable;

public class PyFunction extends SourceData {
    private String main;
    private String params;
    private String code;
    
    
    public String getMain() {
        return main;
    }


    public void setMain(String main) {
        this.main = main;
    }


    public String getParams() {
        return params;
    }


    public void setParams(String params) {
        this.params = params;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public Object clone() {
	// TODO Auto-generated method stub
	return null;
    }

}
