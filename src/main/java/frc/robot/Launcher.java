package frc.robot;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.lib.oi.JawaXboxController;


public class Launcher {
    private static CANSparkMax intakeMotor, midMotor, topMotor;
    private JawaXboxController controller;
    public Launcher(int intakeId, int midId, int topId, JawaXboxController controller) {
        intakeMotor = new CANSparkMax(intakeId, MotorType.kBrushless);
        midMotor = new CANSparkMax(midId, MotorType.kBrushless);
        topMotor = new CANSparkMax(topId, MotorType.kBrushless);
        this.controller = controller;
    }
    
    public void run(int ctrlSet) {
        if (ctrlSet == 1) {
            if (controller.getAButton()) {
                intakeMotor.set(1);
            } else if (controller.getBButton()) {
                intakeMotor.set(-1);
            } else {
                intakeMotor.set(0);
            }
            if (controller.getPOV() < 0) {
                midMotor.set(0);
            } else if (controller.getPOV() < 90 || controller.getPOV() >270) {
                midMotor.set(-1);
            } else {
                midMotor.set(1);
            }
            if (controller.getYButton()) {
                // REDUCED SPEED 
                topMotor.set(-.75);
            } else if (controller.getXButton()) {
                topMotor.set(1);
            } else {
                topMotor.set(0);
            }
        } else if (ctrlSet == 2) {
            midMotor.set(-controller.getLT() + controller.getRT());
            if (controller.aButton.get()) {
                topMotor.set(1);
            } else if (controller.yButton.get()){
                topMotor.set(-1);
            } else {
                topMotor.set(0);
            }
            if (controller.lbButton.get()) {
                intakeMotor.set(1);
            } else {
                intakeMotor.set(0);
            }
        }
        
    }
    public void runIntake(double speed) {
        intakeMotor.set(speed);
    }
    public void runMid(double speed) {
        midMotor.set(speed);
    }
    public void runTop(double speed) {
        topMotor.set(speed);
    }
    public void runMotor(int id, double speed) {
        if (id == intakeMotor.getDeviceId()) {
            intakeMotor.set(speed);
        }
        if (id == midMotor.getDeviceId()) {
            midMotor.set(speed);
        }
        if (id == midMotor.getDeviceId()) {
            topMotor.set(speed);
        }
    } 
}
