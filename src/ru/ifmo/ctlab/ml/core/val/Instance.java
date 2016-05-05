package ru.ifmo.ctlab.ml.core.val;

public interface Instance {

    public Type type();

    /**
     * Distance between missing values is Double.NaN
     */
    public double distance(Instance other, int index);

    /**
     * All missing values should be in the end!
     */
    public int compare(Instance other, int index);

    /**
     * Missing value is Double.NaN
     */
    public double numValue(int index);

    /**
     * Missing value is Integer.MIN_VALUE
     */
    public int intValue(int index);

}
