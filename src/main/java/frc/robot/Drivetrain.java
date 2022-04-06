package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.lib.oi.JawaXboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {

    private static CANSparkMax leftDrive1, leftDrive2,
                               rightDrive1, rightDrive2;
                               
    private static MotorControllerGroup leftDrive,
                                        rightDrive;

    private static DifferentialDrive drive;

    private JawaXboxController controller;

    private final double kSpeedDelta = 0.05;
    private double oldspeed = 0;
    /*
    private final double maxTurn = 1;
    private double oldturn = 0;
    */
    
    public Drivetrain(int leftID1, int leftID2, int rightID1, int rightID2, JawaXboxController controller) {
        leftDrive1 = new CANSparkMax(leftID1, MotorType.kBrushless);
        leftDrive2 = new CANSparkMax(leftID2, MotorType.kBrushless);
        rightDrive1 = new CANSparkMax(rightID1, MotorType.kBrushless);
        rightDrive2 = new CANSparkMax(rightID2, MotorType.kBrushless);

        leftDrive = new MotorControllerGroup(leftDrive1, leftDrive2);
        rightDrive = new MotorControllerGroup(rightDrive1, rightDrive2);
        drive = new DifferentialDrive(leftDrive, rightDrive);

        rightDrive.setInverted(true); // possible issue

        this.controller = controller;
    }

    public void run() {
        drive.arcadeDrive(controller.getLSY(), controller.getRSX());
    }

    public void run(double turnDivisor) {
         drive.arcadeDrive(controller.getLSY() - 0.1, controller.getRSX() / turnDivisor);
    }
    public void run(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    //smoother movement
    public void smoothRun(double turnDivisor) {
        double newpos = controller.getLSY();
        double newturn = controller.getRSX();
        if (newpos - oldspeed > kSpeedDelta ) {
            newpos = oldspeed+kSpeedDelta;
        }
        if (oldspeed - newpos > kSpeedDelta) {
            newpos = oldspeed - kSpeedDelta;
        }
        //sets turning speed like driving, is too hard to control
        /*
        if (newturn - oldturn > maxTurn ) {
            newturn = oldturn+maxTurn;
        }
        if (oldturn - newturn > maxTurn) {
            newturn = oldturn - maxTurn;
        }
        */
        drive.arcadeDrive(newpos, newturn / turnDivisor);
        oldspeed = newpos;
        //oldturn = newturn;
        //FIND DIFFERENCE BETWEN LAST SPEED (THROTTLE POS) AND CURRENT, IF GREATER THAN ALLOWED, SET TO ALLOWED
    }
}