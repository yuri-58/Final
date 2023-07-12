package driver.stat.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import driver.stat.controller.model.DriverCarType;
import driver.stat.controller.model.DriverData;
import driver.stat.controller.model.DriverData.DriverStatData;
import driver.stat.controller.model.DriverEngine;
import driver.stat.controller.model.TrackData;
import driver.stat.dao.CarTypeDao;
import driver.stat.dao.DriverDao;
import driver.stat.dao.DriverTrackStatsDao;
import driver.stat.dao.EngineDao;
import driver.stat.dao.TrackDao;
import driver.stat.entity.CarType;
import driver.stat.entity.Driver;
import driver.stat.entity.DriverTrackStats;
import driver.stat.entity.Engine;
import driver.stat.entity.Track;

@Service
public class DriverStatService {
	@Autowired
	private CarTypeDao carTypeDao;
	
	@Autowired
	private DriverDao driverDao;
	
	@Autowired
	private DriverTrackStatsDao dTSDao;
	
	@Autowired
	private EngineDao engineDao;
	
	@Autowired
	private TrackDao trackDao;

	public DriverData saveDriver(DriverData driverData) {
		Long driverId = driverData.getDriverId();
		Driver driver = findOrCreateDriver(driverId);
		
		copyDriverFields(driver, driverData);
		return new DriverData(driverDao.save(driver));	
	}
	private void copyDriverFields(Driver driver, DriverData driverData) {
		driver.setDriverId(driverData.getDriverId());
		driver.setDriverFirstName(driverData.getDriverFirstName());
		driver.setDriverLastName(driverData.getDriverLastName());
		driver.setDriverPhoNum(driverData.getDriverPhoNum());
		driver.setDriverEmail(driverData.getDriverEmail());
	}
	private Driver findOrCreateDriver(Long driverId) {
		if(Objects.isNull(driverId)) {
			return new Driver();
		} else {
			return findDriverById(driverId);
		}
	}
	public Driver findDriverById(Long driverId) {

		return driverDao.findById(driverId)
				.orElseThrow(() -> new NoSuchElementException(
						"Driver by Id = " + driverId + " was not found."));
	}
	

	public DriverCarType saveCar(DriverCarType carData) {
		Long carId = carData.getCarId();
		CarType car = findOrCreateCar(carId);
		
		copyCarTypeFields(car, carData);
		return new DriverCarType(carTypeDao.save(car));
	}
	private void copyCarTypeFields(CarType car, DriverCarType carData) {
		car.setCarId(carData.getCarId());	
		car.setCarMake(carData.getCarMake());
		car.setCarModel(carData.getCarModel());
		car.setCarEdition(carData.getCarEdition());
		car.setCarYear(carData.getCarYear());
	}
	private CarType findOrCreateCar(Long carId) {
		if(Objects.isNull(carId)) {
			return new CarType();
		} else {
			return findCarTypeById(carId);
		}
	}
	private CarType findCarTypeById(Long carId) {
		return carTypeDao.findById(carId)
				.orElseThrow(() -> new NoSuchElementException(
						"Car by Id = " + carId + " was not found."));
	}
	
	public DriverEngine saveEngine(DriverEngine engineData) {
		Long engineId = engineData.getEngineId();
		Engine engine = findOrCreateEngine(engineId);
		
		copyEngineFields(engine, engineData);
		return new DriverEngine(engineDao.save(engine));
	}
	private void copyEngineFields(Engine engine, DriverEngine engineData) {
		engine.setEngineId(engineData.getEngineId());
		engine.setEngineModel(engineData.getEngineModel());
		engine.setEngineYear(engineData.getEngineYear());
		engine.setEngineHorsePower(engineData.getEngineHorsePower());
		engine.setEngineNotes(engineData.getEngineNotes());
	}
	private Engine findOrCreateEngine(Long engineId) {
		if(Objects.isNull(engineId)) {
			return new Engine();
		} else {
			return findEngineById(engineId);
		}
	}
	private Engine findEngineById(Long engineId) {
		return engineDao.findById(engineId)
				.orElseThrow(() -> new NoSuchElementException(
						"Engine with Id = " + engineId + " was not found."));
	}
	
	public TrackData saveTrack(TrackData trackData) {
		Long trackId = trackData.getTrackId();
		Track track = findOrCreateTrack(trackId);
		
		copyTrackFields(track, trackData);
		return new TrackData(trackDao.save(track));
	}
	private void copyTrackFields(Track track, TrackData trackData) {
		track.setTrackId(trackData.getTrackId());
		track.setTrackName(trackData.getTrackName());
		track.setTrackPhoNum(trackData.getTrackPhoNum());
		track.setTrackEmail(trackData.getTrackEmail());
		track.setTrackAddress(trackData.getTrackAddress());
		track.setTrackCity(trackData.getTrackCity());
		track.setTrackState(trackData.getTrackState());
		track.setTrackZipCode(trackData.getTrackZipCode());
	}
	private Track findOrCreateTrack(Long trackId) {
		if(Objects.isNull(trackId)) {
			return new Track();
		} else {
			return findTrackById(trackId);
		}
	}
	private Track findTrackById(Long trackId) {
		return trackDao.findById(trackId)
				.orElseThrow(() -> new NoSuchElementException(
						"Track with Id = " + trackId + " was not found."));
	}
	
	public DriverStatData saveDriverStats(DriverStatData driverStats, Long driverId, Long trackId) {
		Long statId = driverStats.getStatId();
		Driver driver = findOrCreateDriver(driverId);
		Track track = findOrCreateTrack(trackId);
		DriverTrackStats driverStat = findOrCreateDriverStat(statId);
		copyDriverStatFields(driverStat, driverStats);
		
		driverStat.setDriver(driver);
		
		driverAddOrRetrieveTrack(driver, track);
		driverAddOrRetrieveStat(driver, driverStat);
		statAddOrRetrieveTrack(driverStat, track);
		return new DriverStatData(dTSDao.save(driverStat));
	}
	private void statAddOrRetrieveTrack(DriverTrackStats driverStat, Track track) {
		if(Objects.isNull(driverStat.getTrack())) {
			driverStat.setTrack(track);
		} else {
			driverStat.getTrack();
		}
	}
	private void driverAddOrRetrieveStat(Driver driver, DriverTrackStats driverStat) {
		if(Objects.isNull(driver.getDriverStats())) {
			Set<DriverTrackStats> statSet = new HashSet<DriverTrackStats>();
			statSet.add(driverStat);
			driver.setDriverStats(statSet);
		} else {
			driver.getDriverStats().add(driverStat);
		}
	}
	private void driverAddOrRetrieveTrack(Driver driver, Track track) {
		if(Objects.isNull(driver.getTracks())){
			Set<Track> driverTracks = new HashSet<Track>();
			driverTracks.add(track);
			driver.setTracks(driverTracks);
		} else {
			driver.getTracks().add(track);
		}
	}
	private void copyDriverStatFields(DriverTrackStats driverStat, DriverStatData driverStats) {
		driverStat.setStatId(driverStats.getStatId());
		driverStat.setStat0to60Time(driverStats.getStat0to60Time());
		driverStat.setStat0to100Time(driverStats.getStat0to100Time());
		driverStat.setStatFastestLapTime(driverStats.getStatFastestLapTime());
		driverStat.setStatTopSpeed(driverStats.getStatTopSpeed());
	}
	private DriverTrackStats findOrCreateDriverStat(Long statId) {
		if(Objects.isNull(statId)) {
			return new DriverTrackStats();
		} else {
			return findOrCreateDriverStatById(statId);
		}
	}
	private DriverTrackStats findOrCreateDriverStatById(Long statId) {
		return dTSDao.findById(statId)
				.orElseThrow( () -> new NoSuchElementException(
						"Stat Id = " + statId + " was not found."));
	}
	
	public DriverData saveDriverCarData(DriverData driverData, Long carId, Long engineId) {
		CarType car = findCarTypeById(carId);
		DriverCarType carD = new DriverCarType(car);
		Engine engine = findEngineById(engineId);
		Driver driver = findDriverById(driverData.getDriverId());

		addOrRetrieveDriverEngine(engine, carD);
		addOrRetrieveDriver(driver, car);
		addOrRetrieveDriverData(driverData, carD); //adds a driver to the carData
		
		return driverData;
	}
	private void addOrRetrieveDriverEngine(Engine engine, DriverCarType carD) {
		if(Objects.isNull(carD.getEngine())) {
			DriverEngine temp = new DriverEngine(engine);
			carD.setEngine(temp);
		} else {
			carD.getEngine();
		}
	}
	private void addOrRetrieveDriver(Driver driver, CarType car) {
		if(Objects.isNull(car.getDrivers())) {
			Set<Driver> drivers = new HashSet<Driver>();
			drivers.add(driver);
			
			car.setDrivers(drivers);
		} else {
			car.getDrivers().add(driver);
		}
		
		if(Objects.isNull(driver.getCarTypes())) {
			Set<CarType> cars = new HashSet<CarType>();
			cars.add(car);
			driver.setCarTypes(cars);
		} else {
			driver.getCarTypes().add(car);
		}
		driverDao.save(driver);
	}
	private void addOrRetrieveDriverData(DriverData driverData, DriverCarType carD) {
		if(Objects.isNull(driverData.getCarTypes())) {
			Set<DriverCarType> cars = new HashSet<DriverCarType>();
			cars.add(carD);
			driverData.setCarTypes(cars);
		} else {
			driverData.getCarTypes().add(carD);
		}
	}

	@Transactional(readOnly = false)
	public List<TrackData> getDriverTracks(Long driverId) {
		Driver driver = findDriverById(driverId);
		List<TrackData> result = new LinkedList<TrackData>();
		for(Track track : driver.getTracks()) {
			TrackData tracks = new TrackData(track);
		
			result.add(tracks);
		}
		return result;
	}
	
	
	public TrackData findTrackDataById(Long trackId) {
		Track track = trackDao.findById(trackId)
				.orElseThrow(()-> new NoSuchElementException(
						"Track with Id = " + trackId + " was not found."));
		
		return new TrackData(track);
	}
	
	public DriverStatData findDriverStatDataById(Long statId) {
		DriverTrackStats stat = dTSDao.findById(statId)
				.orElseThrow( () -> new NoSuchElementException(
						"Stat with Id = " + statId + " was not found."));
		return new DriverStatData(stat);		
	}
	
	@Transactional(readOnly = false)
	public List<DriverCarType> getDriverCars(Long driverId){
		Driver driver = findDriverById(driverId);
		List<DriverCarType> result = new LinkedList<DriverCarType>();
		for(CarType car : driver.getCarTypes()) {
			DriverCarType carD = new DriverCarType(car);
		
			result.add(carD);
		}
		return result;
	}
	
	public DriverData findDriverDataById(Long driverId) {
		Driver driver = driverDao.findById(driverId)
				.orElseThrow( () -> new NoSuchElementException(
						"Driver with Id = " + driverId + " was not found."));
		return new DriverData(driver);
	}
	public void deleteTrackById(Long trackId) {
		Track track = findTrackById(trackId);
		trackDao.delete(track);
	}
	
	
	
}
