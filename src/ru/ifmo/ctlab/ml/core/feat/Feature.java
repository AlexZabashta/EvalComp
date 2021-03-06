package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.data.Instance;

public interface Feature {

    int compare(Instance x, Instance y);

    double distance(Instance x, Instance y);

    String featureName();

}