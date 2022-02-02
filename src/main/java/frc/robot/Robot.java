//Carbotta Protoype POC Code
//This should make Carbotta drive with an Xbox controller, but nothing else.
//Prior to running, verify that the motor controllers are configured and set to run using the constants below


//IMPORTANT
//This will run for two seconds during the autonomous mode. Then it will sit, waiting, for 13 seconds. Then, and only then,
//can you drive this as an operator.


package frc.robot;

//Import statements for the required packages. These are the bare minumum required to make an FRC robot run
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 
 * DO NOT MESS WITH THIS NAME!!!
 */
public class Robot extends TimedRobot {

  //Creating the date structure that refer to the 4 motors (and controllers) that run the drivetrain. 
  private final MotorController frontLeftMotor = (MotorController) new VictorSPX(0);
  private final MotorController backLeftMotor = (MotorController) new VictorSPX(1);
  private final MotorController frontRightMotor = (MotorController) new VictorSPX(2);
  private final MotorController backRightMotor = (MotorController) new VictorSPX(3);

  //Group the motors on each side together as controller groups to allow for combined control
  private final MotorControllerGroup leftMotors = new  MotorControllerGroup(frontLeftMotor,backLeftMotor);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRightMotor, backRightMotor);

  //Establish the control scheme as being differential. Allows each side to operate independantly of each other.
  private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotors, rightMotors);
  
  //Create the data structure that refers to the driver controller.
  private final XboxController driverController = new XboxController(0);
  //private final Joystick driverStick = new Joystick(0);

  private final Timer timer = new Timer();
  
  //Not useful in this program. Don't worry about these for now
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.

    //Set the two right motors to turn backwards when given a positive voltage. Consult with Euclid as to why you need to run one set of motors in reverse.
    frontRightMotor.setInverted(true);
    backRightMotor.setInverted(true);

    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (timer.get() < 2.0) {
      robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      robotDrive.stopMotor(); // stop robot
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    //Established the driver control scheme. Should be very similar to most driving games on an Xbox or PS.
    robotDrive.arcadeDrive(-driverController.getLeftY(), driverController.getRightX());
    //robotDrive.arcadeDrive(-deriverStick.getY(), driverStick.getX());

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}