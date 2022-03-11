package frc.lib.oi;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class JawaXboxController extends XboxController {
    
    public JawaXboxController(int port) {
        super(port);
    }

    // Buttons
    public JoystickButton aButton = new JoystickButton(this, XboxMap.A_ID);
    public JoystickButton bButton = new JoystickButton(this, XboxMap.B_ID);
    public JoystickButton xButton = new JoystickButton(this, XboxMap.X_ID);
    public JoystickButton yButton = new JoystickButton(this, XboxMap.Y_ID);
    public JoystickButton lbButton = new JoystickButton(this, XboxMap.LB_ID);
    public JoystickButton rbButton = new JoystickButton(this, XboxMap.RB_ID);
    public JoystickButton startButton = new JoystickButton(this, XboxMap.START_ID);
    public JoystickButton selectButton = new JoystickButton(this, XboxMap.SELECT_ID);
    public JoystickButton lsButton = new JoystickButton(this, XboxMap.LS_ID);
    public JoystickButton rsButton = new JoystickButton(this, XboxMap.RS_ID);
    // Axes as Buttons
    public AxisButton ltButton = new AxisButton(this, XboxMap.LT_ID, 0.05);
    public AxisButton rtButton = new AxisButton(this, XboxMap.RT_ID, 0.05);

    // DPad
    public JoystickPOV upDPad = new JoystickPOV(this, XboxMap.DPad.UP);
    public JoystickPOV upRightDPad = new JoystickPOV(this, XboxMap.DPad.UP_RIGHT);
    public JoystickPOV rightDPad = new JoystickPOV(this, XboxMap.DPad.RIGHT);
    public JoystickPOV downRightDPad = new JoystickPOV(this, XboxMap.DPad.DOWN_RIGHT);
    public JoystickPOV downDPad = new JoystickPOV(this, XboxMap.DPad.DOWN);
    public JoystickPOV downLeftDPad = new JoystickPOV(this, XboxMap.DPad.DOWN_LEFT);
    public JoystickPOV leftDPad = new JoystickPOV(this, XboxMap.DPad.LEFT);
    public JoystickPOV upLeftDPad = new JoystickPOV(this, XboxMap.DPad.UP_LEFT);

    // Joysticks
    public double getLSX() { return this.getRawAxis(XboxMap.LSX_ID); }
    public double getLSY() { return this.getRawAxis(XboxMap.LSY_ID); }
    public double getRSX() { return this.getRawAxis(XboxMap.RSX_ID); }
    public double getRSY() { return this.getRawAxis(XboxMap.RSY_ID); }

    // Triggers
    public double getLT() { return this.getRawAxis(XboxMap.LT_ID); }
    public double getRT() { return this.getRawAxis(XboxMap.RT_ID); }
}