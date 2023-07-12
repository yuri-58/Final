package driver.stat.controller.model;

import java.util.HashSet;
import java.util.Set;

import driver.stat.entity.Driver;
import driver.stat.entity.DriverTrackStats;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverData {
	private Long driverId;
	private String driverFirstName;
	private String driverLastName;
	private Long driverPhoNum;
	private String driverEmail;
	
	Set<DriverData> drivers = new HashSet<DriverData>();
	Set<TrackData> tracks = new HashSet<TrackData>();
	Set<DriverCarType> carTypes = new HashSet<DriverCarType>();
	
	public DriverData(Driver save) {
		driverId = save.getDriverId();
		driverFirstName = save.getDriverFirstName();
		driverLastName = save.getDriverLastName();
		driverPhoNum = save.getDriverPhoNum();
		driverEmail = save.getDriverEmail();
	}
	
	@Data
	@NoArgsConstructor
	public static class DriverStatData {
	private Long statId;
	private String stat0to60Time;
	private String stat0to100Time;
	private String statFastestLapTime;
	private String statTopSpeed;
	
	Set<DriverStatData> stats = new HashSet<DriverStatData>();
	
	public DriverStatData(DriverTrackStats saveS) {
		statId = saveS.getStatId();
		stat0to60Time = saveS.getStat0to60Time();
		stat0to100Time = saveS.getStat0to100Time();
		statFastestLapTime = saveS.getStatFastestLapTime();
		statTopSpeed = saveS.getStatTopSpeed();
	}
	}
	
}


