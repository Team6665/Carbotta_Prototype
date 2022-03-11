// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.GyroContants;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;//encoder data connect to the smartdashboard


public class DriveTrainSubsystem extends SubsystemBase {

    public final Encoder encoder; //establish encoder

    PIDController pid = new PIDController(DriveTrainConstants.kP, DriveTrainConstants.kI, DriveTrainConstants.kD);
    private final PIDController drivetrainPIDController = new PIDController(1,0,0);
  
    private static final double kAngleSetpoint = 0.0;
	  private static final double kP = 0.005; // propotional turning constant
    public static final double kVoltsPerDegreePerSecond = 0.0128;

    //Instantiating the Gyro
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro(GyroContants.kGyroPort);

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


    //encoder configuration
    encoder = new Encoder(1,2,false,CounterBase.EncodingType.k4X);//Encoder(port1, port2, direction(true inversed, false default),CounterBase.Encoding Type, encoding rate (k1X,k2X,k4X(the highest it can goes)))
    encoder.setSamplesToAverage(10);//(1-255) larger number means smoother, but might be less accurate
    encoder.setDistancePerPulse(1/360*6*Math.PI);//how far the mechanism attached to the encoder moves per pulse(unit in inches): 1/360countes*diameter*Math.PI
    encoder.setMinRate(1);//distance in inches/second; this is the lowest rate the encoder will not stop 
  }

  public void arcadeDrive(double fwd, double rot) {
    robotDrive.arcadeDrive(fwd, rot);
  }

  public void setMaxOutput(double maxOutput) {
    robotDrive.setMaxOutput(maxOutput);
    // final double leftOutput =
    // DriveTrainSubsystem.calculate(encoder.getRate(), );
  }

  public void gyroAngleReset(){
    gyro.reset();
  }
  
  public double getGyroValue(){
    return gyro.getRate();
    
  }
  
  // public void getTurningValue() {
  //   double turningValue = (kAngleSetpoint - Gyro.getAngle()) * kP;
	// 	// Invert the direction of the turn if we are going backwards
	// 	turningValue = Math.copySign(turningValue, RobotContainer.driverController.getY());
	// 	RobotContainer.arcadeDrive(driverController.getY(), turningValue);
  // }

  // public void setSetpoint(int setpoint) {
  //     robotDrive.setpoint(setpoint);
  // }

  // public void PID(){
  //   DriveTrainConstants.previous_error = DriveTrainConstants.setpoint - gyro.getAngle(); // Error = Target - Actual
  //   DriveTrainConstants.integral += (DriveTrainConstants.previous_error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
  //   robotDrive.derivative = (DriveTrainConstants.previous_error - robotDrive.previous_error) / .02;
  //   robotDrive.rcw = DriveTrainConstants.kP * DriveTrainConstants.previous_error + DriveTrainConstants.kI * robotDrive.integral + DriveTrainConstants.kD * derivative;
  //}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("encoderDistance",encoder.getDistance());
    SmartDashboard.putNumber("encoderRate",encoder.getRate());
  }
}
