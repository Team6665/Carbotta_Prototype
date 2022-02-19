// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsytems.DriveTrainSubsystem;
//import java.util.function.DoubleSupplier;

public class DefaultDrive extends CommandBase {
  private final DriveTrainSubsystem drive;
  private final double fwd;
  private final double rot;
  
  /** Creates a new DefaultDrive. */
  public DefaultDrive(DriveTrainSubsystem subsystem, double forward, double rotation) {//DoubleSupplier forward, DoubleSupplier rotation) {
    drive = subsystem;
    fwd = forward;
    rot = rotation;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.setMaxOutput(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.arcadeDrive(fwd, rot);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
