package application.util;

import generation.idtable.SourceData;

import java.util.Random;

/**
 * Генератор номеров.
 * @author Buran911
 * */

public class NumberGenerator extends SourceData {
    private int beginVal;
    private int period;
    private int endVal;
    private int curValue;
    private String genType;	// "random" or "sequence"   
    				// 	random: range - [beginVal:endVal]
    private Random rnd;		//	sequence: beginVal & period
    private boolean isfirst;	// first iteration - we need to construct randomizer or to use the begin Value
    
    public NumberGenerator(){
	this.beginVal = 0;
	this.period = 0;
	this.curValue = 0;
	this.genType = "random";
	this.isfirst = true;
    }
    
    public void setPeriod(int period) {
        this.period = period;
    }

    public void setEndVal(int endVal) {
        this.endVal = endVal;
    }

    public NumberGenerator clone(){
	NumberGenerator result = new NumberGenerator();
	return result;
    }
    
    public void setGenType(String type){
	genType = type;
    }
    
     public void setBeginVal(int val){
	beginVal = val;
	curValue = beginVal - period;
    }
    
    public int generateNumber(){
	if (genType.equals("sequence")){
	    if (isfirst == true)
		isfirst = false;
	    else
		curValue = curValue + period;
	}
	if (genType.equals("random")){
	    if (isfirst == true) {
		this.rnd = new Random(); 
		isfirst = false;
	    }
	    curValue = beginVal + rnd.nextInt(endVal - beginVal);
	}
	return curValue;
    }
}

