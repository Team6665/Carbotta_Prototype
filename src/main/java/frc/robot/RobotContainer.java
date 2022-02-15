// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.PneumaticsConstants;
import frc.robot.subsytems.DriveTrainSubsystem;
import frc.robot.subsytems.IntakeSubsystem;
import frc.robot.subsytems.LauncherSubsystem;
import frc.robot.commands.HalveDriveSpeed;
import frc.robot.commands.IntakeDeploy;
import frc.robot.commands.IntakeRetract;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // SUbsytems are instantiated here
  public final static DriveTrainSubsystem DriveTrain = new DriveTrainSubsystem();
  public final static LauncherSubsystem launcher = new LauncherSubsystem();
  public final static IntakeSubsystem intake = new IntakeSubsystem();


  SendableChooser<Command> chooser = new SendableChooser<>();
  
  // Instatiates controller for the human driver
  XboxController driverController = new XboxController(OIConstants.DriverControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    DriveTrain.setDefaultCommand( new RunCommand(() -> DriveTrain.arcadeDrive(-driverController.getLeftY(), driverController.getRightX()),DriveTrain));

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // While holding the shoulder button, drive at half speed
    new JoystickButton(driverController, Button.kB.value).whenHeld(new HalveDriveSpeed(DriveTrain));
    new JoystickButton(driverController, Button.kRightBumper.value).whenPressed(new IntakeDeploy());
    new JoystickButton(driverController, Button.kLeftBumper.value).whenPressed(new IntakeRetract());
    /*
     * The output of GetRawButton is true/false depending on whether
     * the button is pressed; Set takes a boolean for whether
     * to use the default (false) channel or the other (true).
     */
    //intake.set(driverController.getRawButton(PneumaticsConstants.));

    /*
     * In order to set the double solenoid, if just one button
     * is pressed, set the solenoid to correspond to that button.
     * If both are pressed, set the solenoid will be set to Forwards.
     */
    // if (driverController.getRawButton(kDoubleSolenoidForward)) {
    //   doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    // } else if (driverController.getRawButton(kDoubleSolenoidReverse)) {
    //   doubleSolenoid.set(DoubleSolenoid.Value.kReverse); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
    // An ExampleCommand will run in autonomous
  }
}
