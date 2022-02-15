// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class FireLauncher extends CommandBase {
  double speed;

  /** Creates a new FireLauncher. */
  public FireLauncher(double speed) {
      this.speed = speed;
      addRequirements(RobotContainer.launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.launcher.set(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.launcher.set(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.launcher.set(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
