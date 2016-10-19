package ar.fiuba.tdd.template.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nicolas on 15/10/2016.
 */
public abstract class Constants {

    public static final String UP = "AR";
    public static final String RIGHT = "DER";
    public static final String DOWN = "AB";
    public static final String LEFT = "IZQ";
    public static final String UP_DIAGONAL = "/";
    public static final String DOWN_DIAGONAL = "\\";

    public static final int UP_VALUE = 3;
    public static final int RIGHT_VALUE = 2;
    public static final int DOWN_VALUE = 4;
    public static final int LEFT_VALUE = 1;
    public static final int UP_DIAGONAL_VALUE = 2;
    public static final int DOWN_DIAGONAL_VALUE = 1;
    public static final int UPPER_LEFT_CORNER = 1;
    public static final int UPPER_RIGHT_CORNER = 2;
    public static final int LOWER_LEFT_CORNER = 3;
    public static final int LOWER_RIGHT_CORNER = 4;

    public static final int NO_CLUE_RESTRICTION = -1;
}
