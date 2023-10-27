// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.*;;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 2;
  private static final int kRearLeftChannel = 3;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel = 0;

  private final XboxController m_driverController = new XboxController(0);

  private MecanumDrive m_robotDrive;

  @Override
  public void robotInit() {
    PWMSparkMax frontLeft = new PWMSparkMax(kFrontLeftChannel);
    PWMSparkMax rearLeft = new PWMSparkMax(kRearLeftChannel);
    PWMSparkMax frontRight = new PWMSparkMax(kFrontRightChannel);
    PWMSparkMax rearRight = new PWMSparkMax(kRearRightChannel);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

  }

  @Override
  public void teleopPeriodic() {

    SmartDashboard.putNumber("Get Left Y", m_driverController.getLeftY());
    SmartDashboard.putNumber("Get Left X", m_driverController.getLeftX());
    SmartDashboard.putNumber("Get Right X", m_driverController.getRightX());
    SmartDashboard.putNumber("Get Raw Axis 1", m_driverController.getRawAxis(1));
    SmartDashboard.putNumber("Get Raw Axis 2", m_driverController.getRawAxis(2));
    SmartDashboard.putNumber("Get Raw Axis 3", m_driverController.getRawAxis(3));

    // Use the joystick X axis for forward movement, Y axis for lateral
    // movement, and Z axis for rotation.
    m_robotDrive.driveCartesian(-m_driverController.getLeftY(), -m_driverController.getLeftX(), -m_driverController.getRightX());

  }
}
