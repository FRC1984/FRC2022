// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Blake's TODO: drivetrain voltage ramping, then teach different ideas (ex: motor controllers owned by Robot)
// connecting and driving works, ids are flipped for the drivetrain because I wired it backwards, idk if the launcher should be done in a better way -hudson

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;

//import frc.lib.*;
import frc.lib.oi.JawaXboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //private final Joystick m_stick = new Joystick(0);
  private final JawaXboxController driverController = new JawaXboxController(1);
  private final JawaXboxController operatorController = new JawaXboxController(0);
  private final Timer m_timer = new Timer();
  private Drivetrain drivetrain;
  private Launcher launcher;
  private final int turnSpeedDivisor = 2;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    CameraServer.startAutomaticCapture();
    //CameraServer.startAutomaticCapture(1);
    //server = CameraServer.getServer();
    //cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");
    drivetrain = new Drivetrain(4,5,7,6, driverController);
    launcher = new Launcher(3, 1, 2, operatorController);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
    System.out.print("AUTONOMOUS INIT ____ (DEBUG)");
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    //drivetrain.run(turnSpeedDivisor);
    drivetrain.smoothRun();
    launcher.run();
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    drivetrain.run();

  }
}