
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.CounterBase;//encoder
import edu.wpi.first.wpilibj.Encoder;//encoder
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;//encoder data connect to the smartdashboard

public class Robot extends TimedRobot {
  private RobotContainer robotContainer;
  private Command autonomousCommand;
  private final Encoder m_encoder = new Encoder(1,2,false,CounterBase.EncodingType.k4X);//Encoder(port1, port2, direction(true inversed, false default),CounterBase.Encoding Type, encoding rate (k1X,k2X,k4X(the highest it can goes)))

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    m_encoder.setSamplesToAverage(10);//(1-255) larger number means smoother, but might be less accurate
    m_encoder.setDistancePerPulse(1/360*6*Math.PI);//how far the mechanism attached to the encoder moves per pulse(unit in inches): 1/360countes*diameter*Math.PI
    m_encoder.setMinRate(1);//distance in inches/second; this is the lowest rate the encoder will not stop 
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    autonomousCommand = robotContainer.getAutonomousCommand();

    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
     autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("encoderDistance",m_encoder.getDistance());
    SmartDashboard.putNumber("encoderRate",m_encoder.getRate());

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}