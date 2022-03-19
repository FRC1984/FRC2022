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
    private double oldturn = 0;
    
    public Drivetrain(int leftID1, int leftID2, int rightID1, int rightID2, JawaXboxController controller) {
        leftDrive1 = new CANSparkMax(leftID1, MotorType.kBrushless);
        leftDrive2 = new CANSparkMax(leftID2, MotorType.kBrushless);
        rightDrive1 = new CANSparkMax(rightID1, MotorType.kBrushless);
        rightDrive2 = new CANSparkMax(rightID2, MotorType.kBrushless);

        leftDrive = new MotorControllerGroup(leftDrive1, leftDrive2);
        rightDrive = new MotorControllerGroup(rightDrive1, rightDrive2);
        drive = new DifferentialDrive(leftDrive, rightDrive);

        rightDrive.setInverted(true); // TODO: Check this

        this.controller = controller;
    }

    public void run() {
        drive.arcadeDrive(controller.getLSY(), controller.getRSX());
    }

    // public void run(double turnDivisor) {
    //     drive.arcadeDrive(controller.getLSY(), controller.getRSX() / turnDivisor);
    // }

    //smoother movement
    public void smoothRun() {
        double newpos = controller.getLSY();
        double newturn = controller.getRSX();
        if (newpos - oldspeed > kSpeedDelta ) {
            newpos = oldspeed+kSpeedDelta;
        }
        if (oldspeed - newpos > kSpeedDelta) {
            newpos = oldspeed - kSpeedDelta;
        }
        if (newturn - oldturn > kSpeedDelta ) {
            newturn = oldturn+kSpeedDelta;
        }
        if (oldturn - newturn > kSpeedDelta) {
            newturn = oldturn - kSpeedDelta;
        }
        drive.arcadeDrive(newpos, newturn);
        oldspeed = newpos;
        oldturn = newturn;
        //FIND DIFFERENCE BETWEN LAST SPEED (THROTTLE POS) AND CURRENT, IF GREATER THAN ALLOWED, SET TO ALLOWED, 
    }
}