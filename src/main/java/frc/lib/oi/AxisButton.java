package frc.lib.oi;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.GenericHID;

public class AxisButton extends Trigger {

    GenericHID source;
    int id;
    double deadband = 0.0;

    public AxisButton(GenericHID source, int id) {
        this.source = source;
        this.id = id;
    }

    public AxisButton(GenericHID source, int id, double deadband) {
        this(source, id);
        this.deadband = deadband;
    }

    @Override
    public boolean get() {
        return source.getRawAxis(id) > deadband;
    }

    public void setDeadband(double deadband) {
        this.deadband = deadband;
    }
}