/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.profiling;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveToPosition extends Command {
  private double target;
  private double leftVal;
  private double rightVal;
  private double turnVal;

  private double angle;
  private PIDController pid;

  public DriveToPosition(double target) {
    requires(Robot.drive);
    this.target = target / RobotMap.Constants.inchesPerRotation;
    pid = new PIDController(.03, 0.0, 0.000, new PIDSource() {

      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {

      }

      @Override
      public double pidGet() {
        return (Robot.drive.leftEnc.getPosition() + Robot.drive.rightEnc.getPosition()) / 2;
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
      }
    }, new PIDOutput() {

      @Override
      public void pidWrite(double output) {
        double error = Robot.navx.ahrs.getYaw() - angle;
        turnVal = -.0035 * error;

        Robot.drive.r1.setOpenLoopRampRate(RobotMap.Constants.rampRate);
        Robot.drive.l1.setOpenLoopRampRate(RobotMap.Constants.rampRate);
        if (output == 0) {
          Robot.drive.r1.setOpenLoopRampRate(0);
          Robot.drive.l1.setOpenLoopRampRate(0);
        }

        Robot.drive.setLeftRight(output + turnVal, output - turnVal);

      }
    });
    pid.setOutputRange(-.5, .5);
    pid.setAbsoluteTolerance(2 / RobotMap.Constants.inchesPerRotation);
    pid.disable();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive.zeroEncoders();
    angle = Robot.navx.ahrs.getYaw();
    pid.setSetpoint(target);
    pid.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pid.onTarget() || Math.abs(Robot.oi.getDriveValue()) > .2 || Math.abs(Robot.oi.getTurnValue()) > .2;

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pid.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
