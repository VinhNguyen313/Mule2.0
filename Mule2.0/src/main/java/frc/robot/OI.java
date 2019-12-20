/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.DriveToAngle;
import frc.robot.util.VortxController;
import frc.robot.util.VortxMath;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public VortxController main = new VortxController(0);

  public OI(){
   //   main.a.whenPressed(new DriveToAngle(90));
  }
  public double getDriveValue() {
    return VortxMath.applyDeadband(main.getTriggerAxis(Hand.kRight) - main.getTriggerAxis(Hand.kLeft), .1);
 }

 public double getTurnValue() {
    return VortxMath.applyDeadband(-main.getX(Hand.kLeft), .1);
 }
}
