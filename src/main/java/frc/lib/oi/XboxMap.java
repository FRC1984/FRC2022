package frc.lib.oi;

public class XboxMap {
    public double DEAD_BAND = 0.05;

    // Buttons
    static final int A_ID = 1;
    static final int B_ID = 2;
    static final int X_ID = 3;
    static final int Y_ID = 4;
    static final int LB_ID = 5;
    static final int RB_ID = 6;
    static final int SELECT_ID = 7;
    static final int START_ID = 8;
    static final int LS_ID = 9;
    static final int RS_ID = 10;

    // Axes
    static final int LSX_ID = 0;
    static final int LSY_ID = 1;
    static final int RSX_ID = 4;
    static final int RSY_ID = 5;
    static final int LT_ID = 2;
    static final int RT_ID = 3;

    class DPad {
        static final int NETURAL = -1;
        static final int UP = 0;
        static final int UP_RIGHT = 45;
        static final int RIGHT = 90;
        static final int DOWN_RIGHT = 135;
        static final int DOWN = 180;
        static final int DOWN_LEFT = 225;
        static final int LEFT = 270;
        static final int UP_LEFT = 315;
    }
}