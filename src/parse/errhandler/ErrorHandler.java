package parse.errhandler;

import java.util.LinkedList;
import java.util.Queue;

import parse.util.PairSet;
 // TODO Ќаучить работать хэндлер
/**
 *  ласс накапливает, содержит и обрабатывает ошибки парсинга и анализа исходного кода(синтаксические и семантические).
 *  ажда€ ошибка содержит подробное описание.
 * ѕри возникновение ошибок преп€тсвующих возможности продолжени€ работы приложени€,
 * либо накоплении критической массы заверщаетс€ работа приложени€. 
 * 
 * @author hindu
 * */
public abstract class ErrorHandler {
	private Queue<ParseError> errors = new LinkedList<ParseError>();
	private final int errCritCount = 15; // количество ошибок, после которого дальнейшие проверки нецелесообразны 
	private PairSet incompatibleErrors = new PairSet(); // набор несовместимых друг с другом ошибок 
	
	public void addError(ParseError error){
		errors.add(error);
		handle();
	}
	
	public void hasError(ParseError error){
		errors.contains(error);
	}
	
	public boolean hasErrors(){
		return errors.size() > 0 ? true : false;
	}
	
	
	private void handle() {
		//XXX проверить на несовместимость ошибки, превышение криткаунта и проча€
	}
	
	private void printErrors(){
		//XXX распечатать в удобном виде информацию по ошибкам
	}
	
}
