package generation.idtable;

/**
 * Общий класс для всех источников. Используется для задания источника идентификатора.
 * @author hindu
 * */
public abstract class SourceData implements Cloneable{
    @Override
    public abstract SourceData clone();
}
