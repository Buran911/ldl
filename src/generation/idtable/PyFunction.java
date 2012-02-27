package generation.idtable;

import java.util.LinkedList;
import java.util.List;

public class PyFunction extends SourceData {
    private String main;
    private List<String> params;
    private String code;
    
    {
	params = new LinkedList<String>();
    }
    
    public String getMain() {
        return main;
    }


    public void setMain(String main) {
        this.main = main;
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


    public List<String> getParams() {
        return params;
    }


    public void addParam(String param) {
        params.add(param);
    }

}
