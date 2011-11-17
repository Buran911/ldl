package application.util;

/** 
 * Класс наслудющий, RTE бросаемый при критической ошибке приложения без возможности восстановления.
 * @author hindu
 **/
@SuppressWarnings("serial")
public class Halt extends RuntimeException {

}
