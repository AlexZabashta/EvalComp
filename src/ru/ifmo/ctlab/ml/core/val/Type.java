package ru.ifmo.ctlab.ml.core.val;

// FIXME Maybe convert this in abstract class?
public interface Type {

    public String nameOfValue(int index);

    public int allValues();

    public int cmpValues();

    public int numValues();

    public int intValues();

}
