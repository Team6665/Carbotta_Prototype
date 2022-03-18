// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.LauncherConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LauncherSubsystem extends SubsystemBase {
  WPI_TalonSRX motorLauncher = new WPI_TalonSRX(DriveTrainConstants.LauncherMotor);

  /** Creates a new Launcher. */
  public LauncherSubsystem() {
    motorLauncher = new WPI_TalonSRX(DriveTrainConstants.LauncherMotor);  }

  public void set(Double speed) {
    motorLauncher.set(speed);
  }

  public boolean isRunning;
  public boolean isInverted;
  
  public void startLaunching(){
    motorLauncher.set(ControlMode.PercentOutput, LauncherConstants.LauncherSpeed);
    isRunning = true;
  }

  public void stopLauncher(){
    isRunning = false;
  }


  public void invertedIntakeMotor (){
    isInverted = !this.isInverted;
    int sign = isInverted ? 1:-1;
    if(isRunning) motorLauncher.set(ControlMode.PercentOutput, sign * LauncherConstants.LauncherSpeed);
  }
  public void set(double speed) {
    motorLauncher.set(ControlMode.PercentOutput, speed);
  }
  public void stop() {
    set(0.0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("LauncherStatus", isRunning);
    // This method will be called once per scheduler run
  }
}
