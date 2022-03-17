// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.FeederConstant;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class FeederSubsystem extends SubsystemBase {
  WPI_TalonSRX motorFeederFront = new WPI_TalonSRX(FeederConstant.frontFeederMotor);
  WPI_TalonSRX motorFeederBack = new WPI_TalonSRX(FeederConstant.backFeederMotor);
  /** Creates a new FeederSubsystem. */
  
  public FeederSubsystem() {
    motorFeederFront = new WPI_TalonSRX(FeederConstant.frontFeederMotor);
    motorFeederFront = new WPI_TalonSRX(FeederConstant.backFeederMotor);
  }

  public boolean isRunning;
  public boolean isInverted;

  public void startFeeding(){
    motorFeederFront.set(ControlMode.PercentOutput, Constants.FeederConstant.frontFeederSpeed);
    motorFeederBack.set(ControlMode.PercentOutput, Constants.FeederConstant.backFeederSpeed);
    isRunning = true;
  }

  public void stopFeeding(){
    isRunning = false;
  }

  public void inverFeeder(){
    isInverted = !this.isInverted;
    int sign = isInverted ? 1: -1;
    if (isRunning) motorFeederFront.set(ControlMode.PercentOutput, sign * Constants.FeederConstant.frontFeederSpeed);  
    if (isRunning) motorFeederBack.set(ControlMode.PercentOutput, sign* Constants.FeederConstant.backFeederSpeed);

  }
  
  @Override
  public void periodic() {
    SmartDashboard.putBoolean("FeederStatus", isRunning);
    // This method will be called once per scheduler run
  
  }
}
