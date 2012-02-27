package parse.util;

/**
 * Узлы поддерживающие интерфейс могут вернуть свою позицию в АСТ.
 * @author hindu
 * */
public interface Positionable {
    public Integer getLineNo();
    public Integer getColumnNo();
}
