
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.profiling.TurnToAngle;
import frc.robot.util.VortxController;
import frc.robot.util.VortxMath;
import frc.robot.commands.ZeroEncoders;
import frc.robot.commands.profiling.DriveToPosition;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
   public VortxController main = new VortxController(0);

   public OI() {
      main.b.whenPressed(new TurnToAngle(90));
      main.a.whenPressed(new ZeroEncoders());
      main.x.whenPressed(new DriveToPosition(90));
   }

   public double getDriveValue() {
      return Math.copySign(Math.pow(VortxMath.applyDeadband(main.getTriggerAxis(Hand.kRight) - main.getTriggerAxis(Hand.kLeft), .1),2),VortxMath.applyDeadband(main.getTriggerAxis(Hand.kRight) - main.getTriggerAxis(Hand.kLeft), .1));
   }

   public double getTurnValue() {
      double val = -VortxMath.limit(VortxMath.applyDeadband(main.getX(Hand.kLeft), .1),-.5,.5);
      return Math.copySign(val*val,val);
   }
}
