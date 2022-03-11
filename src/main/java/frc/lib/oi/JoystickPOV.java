package frc.lib.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Wrapper around D-Pad to make it usable as a Button class.
 *
 * @param joystick Joystick (e.g. Xbox controller)
 * @param dPadButton Chosen angle
 * @constructor Constructor
 */

 public class JoystickPOV extends JoystickButton {

    private GenericHID joystick;
    private int id;

     public JoystickPOV(GenericHID joystick, int id) {
        super(joystick, id);
        this.joystick = joystick;
        this.id = id;
     }

     @Override
     public boolean get() {
         return joystick.getPOV() == id;
     }
 }