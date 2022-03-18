package frc.robot.subsytems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.lib.GoalNotFoundException;


public class LimelightSubsystem extends SubsystemBase {
    final NetworkTable limelight;
    public LimelightSubsystem (String networkTableName) {
        this.limelight = NetworkTableInstance.getDefault().getTable(networkTableName);
    }
    

    // public double getLimelightOutput(int index) throws GoalNotFoundException{

    // }

    
    // public double getDistance(){
    //     try{
    //         return getLimelightOutput
    //     }
    }


    
