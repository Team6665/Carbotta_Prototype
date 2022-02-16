// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveTrainConstants {
        public static final int FrontLeftMotorPort = 0;
        public static final int RearLeftMotorPort = 1;
        public static final int FrontRightMotorPort = 2;
        public static final int RearRightMotorPort = 3;
        public static final int upperLauncherMotor = 13;
        public static final int lowerLauncherMotor = 12;
        public static final int intakeMotor = 14;
        public static final int kP = 1;
        public static final int kI = 1;
        public static final int kD = 1;
        public static final int integral = 0;
        public static final int previous_error = 0;
        public static final int setpoint = 0;
    }

    public static final class PneumaticsConstants {
        public static final int intakeFront = 1;
        public static final int intakeBack = 5;
        public static final int pcm1_id = 0;
        public static final int kSolenoidButton = 1;
        public static final int kDoubleSolenoidForward = 2;
        public static final int kDoubleSolenoidReverse = 3;
    }
    
    

    public static final class OIConstants {
        public static final int DriverControllerPort = 0;
    }
    
}
