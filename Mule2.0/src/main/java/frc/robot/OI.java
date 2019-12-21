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
import frc.robot.commands.drive.ZeroEncoders;
import frc.robot.commands.drive.lol;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
   public VortxController main = new VortxController(0);

   public OI() {
      main.b.whenPressed(new DriveToAngle(90));
      main.a.whenPressed(new ZeroEncoders());
      main.x.whenPressed(new lol());
   }

   public double getDriveValue() {
      double value = -VortxMath.applyDeadband(main.getTriggerAxis(Hand.kRight) - main.getTriggerAxis(Hand.kLeft), .2);
      double output = Math.abs(value) >= .2 ? Math.copySign(.2, value) : value;
      return output;
   }

   public double getTurnValue() {
      double value = VortxMath.applyDeadband(main.getX(Hand.kLeft), .2);
      double output = Math.abs(value) >= .2 ? Math.copySign(.2, value) : value;
      return output;
   }
}
