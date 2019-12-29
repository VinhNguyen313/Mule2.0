/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.profiling;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnToAngle extends Command {

  double rotateToAngleRate;
  double targetAngleDegrees;
  PIDController turnController;

  public TurnToAngle(double angle) {
    requires(Robot.drive);
    targetAngleDegrees = angle;
    turnController = new PIDController(.0035, 0.0, 0.001, 0.0, Robot.navx.ahrs, new PIDOutput() {

      @Override
      public void pidWrite(double output) {
        rotateToAngleRate = output;

      }
    });
    turnController.setInputRange(-180.0f, 180.0f);
    turnController.setOutputRange(-.3, .3);
    turnController.setAbsoluteTolerance(.5);// degrees
    turnController.setContinuous(true);
    turnController.disable();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.navx.ahrs.zeroYaw();
    turnController.setSetpoint(targetAngleDegrees);
    turnController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drive.setLeft(rotateToAngleRate);
    Robot.drive.setRight(-rotateToAngleRate);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return turnController.onTarget() || Math.abs(Robot.oi.getDriveValue()) > .2
        || Math.abs(Robot.oi.getTurnValue()) > .2;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    turnController.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
