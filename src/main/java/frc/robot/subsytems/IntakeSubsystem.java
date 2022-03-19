// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Constants;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.PneumaticsConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  WPI_TalonSRX motorIntake = new WPI_TalonSRX(DriveTrainConstants.intakeMotor);
  //DoubleSolenoid deployIntake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
  //private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

/** Creates a new Intake. */
  public IntakeSubsystem() {
   // motorIntake.configFactoryDefault();
    motorIntake = new WPI_TalonSRX(DriveTrainConstants.intakeMotor);
    motorIntake.setInverted(true);
    //deployIntake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, PneumaticsConstants.intakeFront, PneumaticsConstants.intakeBack);
  } 

  public boolean isRunning = false;
  public boolean isDeployed = false;
  public boolean isRetracted = false;
  public boolean isInverted = false;
    

  public void deploy() {
    //deployIntake.set(DoubleSolenoid.Value.kForward);
    isRetracted = false;
  }

  public void retract() {
    //deployIntake.set(DoubleSolenoid.Value.kReverse);
    isDeployed = false;
  }
  
  public void invertedIntakeMotor (){
    isInverted = !this.isInverted;
    int sign = isInverted ? 1:-1;
    if(isRunning) motorIntake.set(ControlMode.PercentOutput, sign * Constants.IntakeConstants.intakeMotorSpeed);
  }
  public void set(double speed) {
    motorIntake.set(ControlMode.PercentOutput, speed);
  }

  public boolean isRunning(){
    return isRunning;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("IntakeStatus", isRunning);

    // This method will be called once per scheduler run
  }
}
