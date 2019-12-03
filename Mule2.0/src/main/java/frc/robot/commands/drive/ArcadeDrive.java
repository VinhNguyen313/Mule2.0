/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.VortxMath;

public class ArcadeDrive extends Command {
  public ArcadeDrive() {
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double leftSpeed;
    double rightSpeed;

    double move = VortxMath.applyDeadband(Robot.oi.getDriveValue(), .02);
    double rotate = VortxMath.applyDeadband(Robot.oi.getTurnValue(), .02);

    move = Math.copySign(move * move, move);
    rotate = Math.copySign(rotate * rotate, rotate);

    double maxInput = Math.copySign(Math.max(Math.abs(move), Math.abs(rotate)), move);

    if (move >= 0.0) {
      if (rotate >= 0.0) {
        leftSpeed = maxInput;
        rightSpeed = move - rotate;
      } else {
        leftSpeed = move + rotate;
        rightSpeed = maxInput;
      }
    } else {
      if (rotate >= 0.0) {
        leftSpeed = move + rotate;
        rightSpeed = maxInput;
      } else {
        leftSpeed = maxInput;
        rightSpeed = move - rotate;
      }
    }
    SmartDashboard.putNumber("Left Speed", leftSpeed);
    Robot.drive.setLeft(leftSpeed);

    SmartDashboard.putNumber("Right Speed", rightSpeed);
    Robot.drive.setRight(rightSpeed);
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
