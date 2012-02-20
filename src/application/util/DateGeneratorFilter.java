package application.util;

import generation.db.QueryResult;
import generation.idtable.DataGenerator;
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.languageconstants.Behaviour;
import generation.languageconstants.Messure;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateGeneratorFilter implements Filterable {
    private IdTable idTable;

    private Double index = 0.0;

    public DateGeneratorFilter(IdTable idTable) {
	this.idTable = idTable;
    }

    private Date getSequenceDate(Calendar startDate, Messure messure, Double step) {
	Calendar newcal = (Calendar) startDate.clone();
	switch (messure)
	    {
	    case day:
		newcal.set(Calendar.DAY_OF_MONTH, newcal.get(Calendar.DAY_OF_MONTH) + index.intValue());
		break;
	    case month:
		newcal.set(Calendar.MONTH, newcal.get(Calendar.MONTH) + index.intValue());
		break;
	    case year:
		newcal.set(Calendar.YEAR, newcal.get(Calendar.YEAR) + index.intValue());
		break;
	    }
	index += step;
	return newcal.getTime();
    }

    @Override
    public void filter(QueryResult queryResult) {
	for (Identifier id : idTable.getIds()) {
	    if (id.getSrcData() instanceof DataGenerator) {

		DataGenerator dg = (DataGenerator) id.getSrcData();

		if (dg.getBehaviour() == Behaviour.random) {
		    queryResult.addResult(id.getAlias(), new SimpleDateFormat("dd/MM/yyyy").format(getRandomDateBetween(dg.getStartDate().getTime(), dg.getEndDate().getTime())));
		}
		else if (dg.getBehaviour() == Behaviour.sequence) {
		    queryResult.addResult(id.getAlias(), new SimpleDateFormat("dd/MM/yyyy").format(getSequenceDate(dg.getStartDate(), dg.getMessure(), dg.getStep())));
		}
	    }
	}
    }

    private Date getRandomDateBetween(Date from, Date to) {
	Calendar cal = Calendar.getInstance();

	cal.setTime(from);
	BigDecimal decFrom = new BigDecimal(cal.getTimeInMillis());

	cal.setTime(to);
	BigDecimal decTo = new BigDecimal(cal.getTimeInMillis());

	BigDecimal bd1 = decTo.subtract(decFrom);
	BigDecimal factor = bd1.multiply(new BigDecimal(Math.random()));

	return new Date((factor.add(decFrom)).longValue());
    }
}