/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

public class DriveTrain extends Subsystem {

  private CANSparkMax l1;
  private CANSparkMax l2;
  private CANSparkMax r1;
  private CANSparkMax r2;

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


  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }

  public void setLeft(double speed){
    l1.set(speed);
  }

  public void setRight(double speed){
    r1.set(speed);
  }

}
