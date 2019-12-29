/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.NormalDrive;

public class DriveTrain extends Subsystem {

  public CANSparkMax l1;
  public CANSparkMax l2;
  public CANSparkMax r1;
  public CANSparkMax r2;

  public CANEncoder leftEnc;
  public CANEncoder rightEnc;

  public DriveTrain() {
    l1 = new CANSparkMax(RobotMap.Drive.l1, MotorType.kBrushless);
    l2 = new CANSparkMax(RobotMap.Drive.l2, MotorType.kBrushless);
    r1 = new CANSparkMax(RobotMap.Drive.r1, MotorType.kBrushless);
    r2 = new CANSparkMax(RobotMap.Drive.r2, MotorType.kBrushless);

    r1.restoreFactoryDefaults();
    r2.restoreFactoryDefaults();
    l1.restoreFactoryDefaults();
    l2.restoreFactoryDefaults();

    l2.follow(l1);
    r2.follow(r1);

    r1.setInverted(true);

    r1.setIdleMode(IdleMode.kBrake);
    l1.setIdleMode(IdleMode.kBrake);
    r2.setIdleMode(IdleMode.kBrake);
    l2.setIdleMode(IdleMode.kBrake);

    leftEnc = l1.getEncoder();
    rightEnc = r1.getEncoder();
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new ArcadeDrive());
    setDefaultCommand(new NormalDrive());
  }

  public void setLeft(double speed) {
    l1.set(speed);
  }

  public void setRight(double speed) {
    r1.set(speed);
  }

  public void setLeftRight(double leftSpeed, double rightSpeed) {
    setLeft(leftSpeed);
    setRight(rightSpeed);
  }

  public void zeroEncoders() {
    leftEnc.setPosition(0);
    rightEnc.setPosition(0);
  }

  public void log() {
    SmartDashboard.putNumber("Right Speed", r1.get());
    SmartDashboard.putNumber("Left Speed", l1.get());

    SmartDashboard.putNumber("Left Inches", leftEnc.getPosition() * RobotMap.Constants.inchesPerRotation);
    SmartDashboard.putNumber("Right Inches", rightEnc.getPosition() * RobotMap.Constants.inchesPerRotation);

  }

}
