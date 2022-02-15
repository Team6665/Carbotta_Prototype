// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.math.controller.PIDController;

public class DriveTrainSubsystem extends SubsystemBase {

    PIDController pid = new PIDController(DriveTrainConstants.kP, DriveTrainConstants.kI, DriveTrainConstants.kD);

    //Creating the date structure that refer to the 4 motors (and controllers) that run the drivetrain. 
    private final MotorController frontLeftMotor = (MotorController) new WPI_VictorSPX(DriveTrainConstants.FrontLeftMotorPort);
    private final MotorController backLeftMotor = (MotorController) new WPI_VictorSPX(DriveTrainConstants.RearLeftMotorPort);
    private final MotorController frontRightMotor = (MotorController) new WPI_VictorSPX(DriveTrainConstants.FrontRightMotorPort);
    private final MotorController backRightMotor = (MotorController) new WPI_VictorSPX(DriveTrainConstants.RearRightMotorPort);

    //Group the motors on each side together as controller groups to allow for combined control
    private final MotorControllerGroup leftMotors = new  MotorControllerGroup(frontLeftMotor,backLeftMotor);
    private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRightMotor, backRightMotor);
  
    //Establish the control scheme as being differential. Allows each side to operate independantly of each other.
    private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotors, rightMotors);

  /** Creates a new driveTrain. */
  public DriveTrainSubsystem() {
    //Set the two right motors to turn backwards when given a positive voltage. Consult with Euclid as to why you need to run one set of motors in reverse.
    rightMotors.setInverted(true);

  }

  public void arcadeDrive(double fwd, double rot) {
    robotDrive.arcadeDrive(fwd, rot);
  }

  public void setMaxOutput(double maxOutput) {
    robotDrive.setMaxOutput(maxOutput);
  }

  // public void setSetpoint(int setpoint) {
  //     robotDrive.setpoint(setpoint);
  // }

  // public void PID(){
  //   DriveTrainConstants.previous_error = DriveTrainConstants.setpoint - gyro.getAngle(); // Error = Target - Actual
  //   DriveTrainConstants.integral += (DriveTrainConstants.previous_error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
  //   robotDrive.derivative = (DriveTrainConstants.previous_error - robotDrive.previous_error) / .02;
  //   robotDrive.rcw = DriveTrainConstants.kP * DriveTrainConstants.previous_error + DriveTrainConstants.kI * robotDrive.integral + DriveTrainConstants.kD * derivative;
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
