// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsytems.DriveTrainSubsystem;

public class HalveDriveSpeed extends CommandBase {
  private final DriveTrainSubsystem driveTrain;

  public HalveDriveSpeed(DriveTrainSubsystem drive) {
    driveTrain = drive;
  }

  @Override
  public void initialize() {
    driveTrain.setMaxOutput(0.5);
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.setMaxOutput(1);
  }
}
