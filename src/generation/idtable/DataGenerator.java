package generation.idtable;

import generation.languageconstants.Behaviour;
import generation.languageconstants.Messure;

import java.util.Calendar;

public class DataGenerator extends SourceData {
    private Calendar startData = null;
    private Calendar endData = null;
    private Behaviour behaviour = null;
    private Double step = null;
    private Messure messure = null;

    @Override
    public Object clone() {
	// TODO Auto-generated method stub
	return null;
    }

    public Calendar getStartData() {
        return startData;
    }

    public Calendar getEndData() {
        return endData;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public Double getStep() {
        return step;
    }

    public Messure getMessure() {
        return messure;
    }

    public void setStartData(Calendar startData) {
        this.startData = startData;
    }

    public void setEndData(Calendar endData) {
        this.endData = endData;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void setStep(Double step) {
        this.step = step;
    }

    public void setMessure(Messure messure) {
        this.messure = messure;
    }
}