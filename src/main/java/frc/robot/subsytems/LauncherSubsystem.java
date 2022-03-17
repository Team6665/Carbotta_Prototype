// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LauncherSubsystem extends SubsystemBase {
  MotorController upperLauncherMotor = (MotorController) new WPI_TalonSRX(DriveTrainConstants.FrontLeftMotorPort);
  MotorController lowerLauncherMotor = (MotorController) new WPI_TalonSRX(DriveTrainConstants.RearLeftMotorPort);

  MotorControllerGroup launcherMotors = new  MotorControllerGroup(upperLauncherMotor,lowerLauncherMotor);

  /** Creates a new Launcher. */
  public LauncherSubsystem() {
    upperLauncherMotor = (MotorController) new WPI_TalonSRX(DriveTrainConstants.FrontLeftMotorPort);
    lowerLauncherMotor = (MotorController) new WPI_TalonSRX(DriveTrainConstants.RearLeftMotorPort);
    
    lowerLauncherMotor.setInverted(true);

    //MotorControllerGroup launcherMotors = new  MotorControllerGroup(upperLauncherMotor,lowerLauncherMotor);
  }

  public void set(Double speed) {
    launcherMotors.set(speed);
  }

  public void stop() {
    set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
