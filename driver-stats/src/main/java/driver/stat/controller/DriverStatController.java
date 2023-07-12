package driver.stat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import driver.stat.controller.model.DriverCarType;
import driver.stat.controller.model.DriverData;
import driver.stat.controller.model.DriverData.DriverStatData;
import driver.stat.controller.model.DriverEngine;
import driver.stat.controller.model.TrackData;
import driver.stat.service.DriverStatService;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/driver_stat")
@Slf4j
public class DriverStatController {
	
	private Map<String, String> messageInput(){
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "Deleted!");
		return msg;
	}

	@Autowired
	private DriverStatService dSService;
	
	
	@PostMapping("/new_driver")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DriverData saveDriver(
			@RequestBody DriverData driverData) {
		log.info("Created a new driver {}", driverData);
		return dSService.saveDriver(driverData);
		
	}
	
	@PostMapping("/new_car")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DriverCarType saveCar(
			@RequestBody DriverCarType carData) {
		log.info("Created new car {}", carData);
		return dSService.saveCar(carData);
	}
	
	@PostMapping("/new_engine")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DriverEngine saveEngine(
			@RequestBody DriverEngine engineData) {
		log.info("Created new engine {}", engineData);
		return dSService.saveEngine(engineData);
	}
	
	@PostMapping("/new_track")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TrackData saveTrack(
			@RequestBody TrackData trackData) {
		log.info("Created new track {}", trackData);
		return dSService.saveTrack(trackData);
	}
	
	@PostMapping("/{driverId}/{trackId}/new_driver_stats")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DriverStatData saveDriverStats(
			@PathVariable Long driverId,
			@PathVariable Long trackId,
			@RequestBody DriverStatData driverStats) {
		log.info("Created new driver track stats {}", driverStats);
		return dSService.saveDriverStats(driverStats, driverId, trackId);
	}
	
	@PostMapping("/{driverId}/driver_cars/{carId}/{engineId}")
	public DriverData createDriverCarData(
			@PathVariable Long driverId,
			@PathVariable Long carId,
			@PathVariable Long engineId
			) {
		DriverData driverData = dSService.findDriverDataById(driverId);
		return dSService.saveDriverCarData(driverData, carId, engineId);
	}
	
	@PutMapping("/{driverId}/driver")
	public DriverData updateDriver(
			@PathVariable Long driverId,
			@RequestBody DriverData driverData) {
		driverData.setDriverId(driverId);
		return dSService.saveDriver(driverData);
	}
	
	@PutMapping("/{carId}/car")
	public DriverCarType updateCarType(
			@PathVariable Long carId,
			@RequestBody DriverCarType carData) {
		carData.setCarId(carId);
		return dSService.saveCar(carData);
	}
	
	@PutMapping("/{driverId}/driver_cars/{carId}/{engineId}")
	public DriverData updateDriverCarData(
			@PathVariable Long driverId,
			@PathVariable Long carId,
			@PathVariable Long engineId
			) {
		DriverData driverData = dSService.findDriverDataById(driverId);
		return dSService.saveDriverCarData(driverData, carId, engineId);
	}
	
	@PutMapping("/{driverId}/{trackId}/{statId}/driver_stats")
	public DriverStatData updateDriverStats(
			@PathVariable Long driverId,
			@PathVariable Long trackId,
			@PathVariable Long statId,
			@RequestBody DriverStatData driverStats) {
		return dSService.saveDriverStats(driverStats, driverId, trackId);
	}
	
	@GetMapping("/{driverId}/driver_tracks")
	public List<TrackData> driverTracks(
			@PathVariable Long driverId){
		return dSService.getDriverTracks(driverId);
	}
	
	@DeleteMapping("/{trackId}/delete_track")
	public Map<String, String> deleteTrack(
			@PathVariable Long trackId){
		dSService.deleteTrackById(trackId);
		return messageInput();
	}
			
	
}
