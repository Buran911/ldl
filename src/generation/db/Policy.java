package generation.db;

/**
 * ѕеречисление политики выбора данных дл€ конкретного  Ё из Ѕƒ, а именно, какое
 * поле столбца будет выбрано, если данному  Ё удовлетвор€ют несколько.
 * 
 * @author hindu
 * */
public enum Policy {
    first,
    last,
    random
}
