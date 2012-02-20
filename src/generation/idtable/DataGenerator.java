package generation.idtable;

import generation.languageconstants.Behaviour;
import generation.languageconstants.Messure;

import java.util.Calendar;

public class DataGenerator extends SourceData {
    private Calendar startDate = null;
    private Calendar endDate = null;
    private Behaviour behaviour = null;
    private Double step = null;
    private Messure messure = null;

    @Override
    public Object clone() {
	return null;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
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

    public void setStartDate(Calendar startData) {
        this.startDate = startData;
    }

    public void setEndDate(Calendar endData) {
        this.endDate = endData;
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