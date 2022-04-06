// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;
import frc.lib.oi.JawaXboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final JawaXboxController driverController = new JawaXboxController(0);
  private final JawaXboxController operatorController = new JawaXboxController(1);
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
    CameraServer.startAutomaticCapture(1);
    //server = CameraServer.getServer();
    //cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");
    drivetrain = new Drivetrain(4,3,2,1, driverController);
    launcher = new Launcher(5, 6, 7, operatorController);
  }

  /** This function is run once each time the robot enters autonomous mode. */

  

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }
  //finals autonomous, gets/shoots first ball and stays still for ~10sec, then moves out.
  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 0.25) {
      //run back
      drivetrain.run(0.75, 0);
    }  else if (m_timer.get() < 2) {
      //shooot
      drivetrain.run(0,0);
      launcher.runIntake(1);
      launcher.runMid(-.5);
      launcher.runTop(-1);
    } else if (m_timer.get() < 10){
      drivetrain.run(0,0);
      launcher.runIntake(0);
      launcher.runMid(0);
      launcher.runTop(0);
    } else if (m_timer.get() < 12) {
      drivetrain.run(-.75, 0);
    } else {
      drivetrain.run(0, 0);
      launcher.runIntake(0);
      launcher.runMid(0);
      launcher.runTop(0);
    }
  }
  //quali auto, shoots first ball then gets second and shoots that one aswell
  /*
  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 0.25) {
      //run back
      drivetrain.run(0.75, 0);
    }  else if (m_timer.get() < 2) {
      //shooot
      drivetrain.run(0,0);
      launcher.runIntake(1);
      launcher.runMid(-.5);
      launcher.runTop(-1);
    }else if (m_timer.get() < 4) {
      //forward
      launcher.runTop(1);
      drivetrain.run(-0.75, 0);
    } else if (m_timer.get() < 6) {
      //back
      drivetrain.run(0.75, 0);
    } else if (m_timer.get() < 7) {
      //shoot
      drivetrain.run(0,0);
      launcher.runTop(-1);
    } else {
      //stop
      drivetrain.run(0,0);
      launcher.runTop(0);
      launcher.runMid(0);
      launcher.runIntake(0);
    }
  }*/

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
    drivetrain.smoothRun(turnSpeedDivisor);
    launcher.run(1);
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
    launcher.run(2);
  }
}