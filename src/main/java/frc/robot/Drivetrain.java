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

    public void run(double turnDivisor) {
        drive.arcadeDrive(controller.getLSY(), controller.getRSX() / turnDivisor);
    }
}
