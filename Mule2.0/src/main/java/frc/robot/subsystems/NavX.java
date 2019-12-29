/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */
public class NavX extends Subsystem {
 
  public AHRS ahrs = new AHRS(SPI.Port.kMXP);

  @Override
  public void initDefaultCommand() {
  }

  public void log() {
    SmartDashboard.putNumber("Yaw Angle", ahrs.getYaw());
  }
}
