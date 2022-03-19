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
    
    public void run() {

        //the top and middle motors need variable speeds, intake doesnt, 
        //do the triggers return  -1,1 range?
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
            //.set(0) or .stopMotor() better??
            intakeMotor.set(0);
        }

    }
}
