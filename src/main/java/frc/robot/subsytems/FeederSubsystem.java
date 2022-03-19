// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.FeederConstant;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class FeederSubsystem extends SubsystemBase {
  private WPI_TalonSRX motorFeederLeft = new WPI_TalonSRX(FeederConstant.LeftFeederMotor);
  private WPI_TalonSRX motorFeederRight = new WPI_TalonSRX(FeederConstant.RightFeederMotor);
  private final MotorControllerGroup feederMotors = new  MotorControllerGroup(motorFeederLeft,motorFeederRight);
  /** Creates a new FeederSubsystem. */
  
  public FeederSubsystem() {
    motorFeederLeft = new WPI_TalonSRX(FeederConstant.LeftFeederMotor);
    motorFeederRight = new WPI_TalonSRX(FeederConstant.RightFeederMotor);

    motorFeederLeft.setInverted(true);
    motorFeederRight.setInverted(true);

  }

  public boolean isRunning;
  public boolean isInverted;

  public void startFeeding(){
    motorFeederLeft.set(ControlMode.PercentOutput, Constants.FeederConstant.LeftFeederSpeed);
    motorFeederRight.set(ControlMode.PercentOutput, Constants.FeederConstant.RightFeederSpeed);
    isRunning = true;
  }

  public void set(double speed) {
    feederMotors.set(speed);
  }
  public void stop() {
    set(0.0);
  }

  public void stopFeeding(){
    isRunning = false;
  }

  public void inverFeeder(){
    isInverted = !this.isInverted;
    int sign = isInverted ? 1: -1;
    if (isRunning) motorFeederLeft.set(ControlMode.PercentOutput, sign * Constants.FeederConstant.LeftFeederSpeed);  
    if (isRunning) motorFeederRight.set(ControlMode.PercentOutput, sign* Constants.FeederConstant.RightFeederSpeed);

  }
  
  @Override
  public void periodic() {
    SmartDashboard.putBoolean("FeederStatus", isRunning);
    // This method will be called once per scheduler run
  
  }
}
