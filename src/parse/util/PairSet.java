package parse.util;

import java.util.Set;


/**
 * ���� ����� �������� ��������� ��� ��������. 
 * �������� ������ �������� ���������� ����, �������� � �������� �� �������.
 * �.�. ���������� ������� �������� java.util.Set.
 * 
 * @author hindu
 * */
public class PairSet {
	private Set<Pair> pairSet;
	
	public void add(Pair pair) {
		pairSet.add(pair);
	}
	
	public void contains(Pair pair) {
		pairSet.contains(pair);
	}
	
	public void remove(Pair pair) {
		pairSet.remove(pair);
	}
}
