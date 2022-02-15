// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.PneumaticsConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  WPI_VictorSPX motorIntake = new WPI_VictorSPX(DriveTrainConstants.intakeMotor);
  DoubleSolenoid deployIntake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

/** Creates a new Intake. */
  public Intake() {
    motorIntake = new WPI_VictorSPX(DriveTrainConstants.intakeMotor);
    deployIntake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, PneumaticsConstants.intakeFront, PneumaticsConstants.intakeBack);
  }

  public void deploy() {
    deployIntake.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    deployIntake.set(DoubleSolenoid.Value.kReverse);
  }
  
  public void set(double speed) {
    motorIntake.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
