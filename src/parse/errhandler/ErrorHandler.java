package parse.errhandler;

import java.util.LinkedList;
import java.util.Queue;

import parse.util.PairSet;
import parse.util.Source;
 // TODO Ќаучить работать хэндлер
/**
 *  ласс накапливает, содержит и обрабатывает ошибки парсинга и анализа исходного кода(синтаксические и семантические).
 *  ажда€ ошибка содержит подробное описание.
 * ѕри возникновение ошибок преп€тсвующих возможности продолжени€ работы приложени€,
 * либо накоплении критической массы заверщаетс€ работа приложени€. 
 * 
 * @author hindu
 * */
public class ErrorHandler {	
	private LinkedList<ParseError> errors;
	private final int errCritCount; // количество ошибок, после которого дальнейшие проверки нецелесообразны 
	private PairSet incompatibleErrors; // набор несовместимых друг с другом ошибок
	private Source src;
	
	{
		errors = new LinkedList<ParseError>();
		errCritCount = 15;
		incompatibleErrors = new PairSet();
	}
	
	public ErrorHandler(Source src) {
		this.src = src;
	}

	public void addError(ParseError error){
		errors.add(error);
		handle();
	}
	
	public boolean hasError(ParseError error){		
		return errors.contains(error);
	}
	
	public boolean hasErrors(){
		return errors.size() > 0 ? true : false;
	}
	
	
	private void handle() {
		//TODO проверить на несовместимость ошибки, превышение криткаунта и проча€
		ParseError lastErr = errors.getLast();
		String errorLine = src.getLine( lastErr.getLineNo());
		
		lastErr.setErrorLine(errorLine);
	}
	
	public void printErrors(){
		//XXX распечатать в удобном виде информацию по ошибкам
		for(ParseError error : errors){
			System.err.println("Error["+ error.getLineNo() +
					"]" + error.getErrorLine());
		}
	}
	
}
