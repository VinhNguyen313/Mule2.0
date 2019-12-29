/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class NormalDrive extends Command {

  public NormalDrive() {
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double move = Robot.oi.getDriveValue();
    double turn = Robot.oi.getTurnValue();

    Robot.drive.r1.setOpenLoopRampRate(RobotMap.Constants.rampRate);
    Robot.drive.l1.setOpenLoopRampRate(RobotMap.Constants.rampRate);
    if (move == 0) {
      Robot.drive.r1.setOpenLoopRampRate(0);
      Robot.drive.l1.setOpenLoopRampRate(0);
    }

    Robot.drive.setRight(move - turn);
    Robot.drive.setLeft(move + turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.setLeft(0);
    Robot.drive.setRight(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
