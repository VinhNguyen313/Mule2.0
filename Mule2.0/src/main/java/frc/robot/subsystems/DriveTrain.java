/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.ArcadeDrive;
import frc.robot.commands.drive.NormalDrive;

public class DriveTrain extends Subsystem {

  private CANSparkMax l1;
  private CANSparkMax l2;
  private CANSparkMax r1;
  private CANSparkMax r2;

  private CANPIDController pid;

  private CANEncoder leftEnc;
  private CANEncoder rightEnc;

  public DriveTrain() {
    l1 = new CANSparkMax(RobotMap.Drive.l1, MotorType.kBrushless);
    l2 = new CANSparkMax(RobotMap.Drive.l2, MotorType.kBrushless);
    r1 = new CANSparkMax(RobotMap.Drive.r1, MotorType.kBrushless);
    r2 = new CANSparkMax(RobotMap.Drive.r2, MotorType.kBrushless);

    l2.follow(l1);
    r2.follow(r1);
  

    r1.setInverted(true);

    r1.setIdleMode(IdleMode.kCoast);
    l1.setIdleMode(IdleMode.kCoast);
    r2.setIdleMode(IdleMode.kCoast);
    l2.setIdleMode(IdleMode.kCoast);


    pid = l1.getPIDController();

    leftEnc = l1.getEncoder();
    rightEnc = r1.getEncoder();
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new ArcadeDrive());
    setDefaultCommand(new NormalDrive());
  }

  public void setLeft(double speed){
    l1.set(speed);
  }

  public void setRight(double speed){
    r1.set(speed);
  }

  public void log(){
    SmartDashboard.putNumber("Left Rotations", leftEnc.getPosition());
    SmartDashboard.putNumber("Right Rotations", rightEnc.getPosition());
  }

}
