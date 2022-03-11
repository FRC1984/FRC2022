// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Blake's TODO: Intake class, drivetrain voltage ramping, then teach different ideas (ex: motor controllers owned by Robot)
// connecting and driving works, ids are flipped for the drivetrain because I wired it backwards, idk if the launcher should be done in a better way -hudson

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
//import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
//import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.networktables.NetworkTableInstance;

//import frc.lib.*;
import frc.lib.oi.JawaXboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  // private final CANSparkMax m_leftDrive1 = new CANSparkMax(1,  MotorType.kBrushless);
  // private final CANSparkMax m_leftDrive2 = new CANSparkMax(2, MotorType.kBrushless);
  // private final MotorControllerGroup m_leftDrive = new MotorControllerGroup(m_leftDrive1, m_leftDrive2);
  // private final CANSparkMax m_rightDrive1 = new CANSparkMax(3, MotorType.kBrushless);
  // private final CANSparkMax m_rightDrive2 = new CANSparkMax(4, MotorType.kBrushless);
  /*
  private final CANSparkMax entryConveyor = new CANSparkMax(5, MotorType.kBrushless);
  private final CANSparkMax midConveyor = new CANSparkMax(6, MotorType.kBrushless);
  private final CANSparkMax topConveyor = new CANSparkMax(7, MotorType.kBrushless);
  */
  // private final MotorControllerGroup m_rightDrive = new MotorControllerGroup(m_rightDrive1, m_rightDrive2);
  //private final PWMSparkMax testmotor = new PWMSparkMax(0);
  private final Talon intakeMotor = new Talon(0);
  // private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  private final Joystick m_stick = new Joystick(0);
  private final JawaXboxController controller = new JawaXboxController(0);
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
    //CameraServer.startAutomaticCapture();
    CameraServer.startAutomaticCapture();
    //CameraServer.startAutomaticCapture(1);
    //server = CameraServer.getServer();
    //cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");

    // m_rightDrive1.setInverted(true);
    // m_rightDrive2.setInverted(true);
    // m_rightDrive.setInverted(false);

    drivetrain = new Drivetrain(4,3,2,1, controller);
    launcher = new Launcher(5, 6, 7, controller);
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
    // Drive for 2 seconds
    /*
    if (m_timer.get() < 2.0) {
      m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
    */
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
    //m_robotDrive.arcadeDrive(-m_stick.getY(), m_stick.getX() / turnSpeedDivisor);
    drivetrain.run(turnSpeedDivisor);
    launcher.run();
    /*
    if (m_stick.getTriggerPressed()) { topConveyor.set(1); midConveyor.set(1);}
    if (m_stick.getTriggerReleased()) {topConveyor.stopMotor(); midConveyor.stopMotor();}
    if (m_stick.getRawButtonPressed(2)) { entryConveyor.set(1);}
    if (m_stick.getRawButtonReleased(2)) { entryConveyor.stopMotor();}
    if (m_stick.getRawButtonPressed(5)) {
      intakeMotor.set(0.5);
    } else {
      intakeMotor.set(0);
    }
    
    if(controller.getAButton()) {

    }
    */
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
    //System.out.print(m_stick.getY() + " TEST " + m_stick.getX());
    //System.out.print(m_robotDrive.isAlive() + " TEST " + m_robotDrive.isSafetyEnabled());
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    /*
    if (m_stick.getTriggerPressed()) {
      m_robotDrive.arcadeDrive(0.1, 0);
    }
    */
    /*
    if (m_stick.getPOV() == 90) {
      cameraSelection.setString(cam1.getName());
    } else if (m_stick.getPOV() == 270) {
      cameraSelection.setString(cam2.getName());
    }
    */
    
    //testmotor.set(m_stick.getY());
    intakeMotor.set(m_stick.getX());
    
    /*
    if (m_stick.getRawButtonPressed(11)) { m_leftDrive.set(1);}
    if (m_stick.getRawButtonReleased(11)) { m_leftDrive.stopMotor();}
    if (m_stick.getRawButtonPressed(12)) { m_rightDrive.set(1);}
    if (m_stick.getRawButtonReleased(12)) { m_rightDrive.stopMotor();}
    if (m_stick.getRawButtonPressed(10)) { m_rightDrive1.set(1);}
    if (m_stick.getRawButtonReleased(10)) { m_rightDrive1.stopMotor();}
    if (m_stick.getRawButtonPressed(8)) { m_rightDrive2.set(1);}
    if (m_stick.getRawButtonReleased(8)) { m_rightDrive2.stopMotor();}
    if (m_stick.getRawButtonPressed(9)) { m_leftDrive1.set(1);}
    if (m_stick.getRawButtonReleased(9)) { m_leftDrive1.stopMotor();}
    if (m_stick.getRawButtonPressed(7)) { m_leftDrive2.set(1);}
    if (m_stick.getRawButtonReleased(7)) { m_leftDrive2.stopMotor();}
    */
    //if (m_stick.getTriggerPressed()) {testmotor.set(0.5);}
    //if (m_stick.getTriggerReleased()) {testmotor.stopMotor();}


  }
}