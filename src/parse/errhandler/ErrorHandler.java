package parse.errhandler;

import java.util.LinkedList;
import java.util.Queue;

import parse.util.PairSet;
 // TODO ������� �������� �������
/**
 * ����� �����������, �������� � ������������ ������ �������� � ������� ��������� ����(�������������� � �������������).
 * ������ ������ �������� ��������� ��������.
 * ��� ������������� ������ ������������� ����������� ����������� ������ ����������,
 * ���� ���������� ����������� ����� ����������� ������ ����������. 
 * 
 * @author hindu
 * */
public abstract class ErrorHandler {
	private Queue<ParseError> errors = new LinkedList<ParseError>();
	private final int errCritCount = 15; // ���������� ������, ����� �������� ���������� �������� ��������������� 
	private PairSet incompatibleErrors = new PairSet(); // ����� ������������� ���� � ������ ������ 
	
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
		//XXX ��������� �� ��������������� ������, ���������� ���������� � ������
	}
	
	private void printErrors(){
		//XXX ����������� � ������� ���� ���������� �� �������
	}
	
}
