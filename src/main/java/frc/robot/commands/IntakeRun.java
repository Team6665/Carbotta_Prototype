// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsytems.IntakeSubsystem;

public class IntakeRun extends CommandBase {
  private final IntakeSubsystem intake;
  double speed;

  public IntakeRun(IntakeSubsystem subsystem, double speed) {
    this.speed = speed;
    intake = subsystem;
    addRequirements(intake);
  } 

  @Override
  public void initialize() {
    intake.set(0.0);
  }

  @Override
  public void execute() {
    intake.set(speed);
  }
    // Use addRequirements() here to declare subsystem dependencies.

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
