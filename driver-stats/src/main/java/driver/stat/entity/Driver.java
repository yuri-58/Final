package driver.stat.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long driverId;
	private String driverFirstName;
	private String driverLastName;
	private Long driverPhoNum;
	private String driverEmail;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "driver_car_type", joinColumns
	= @JoinColumn(name = "driver_id"),
	inverseJoinColumns = @JoinColumn(name = "car_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<CarType> carTypes = new HashSet<CarType>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "driver_engine", joinColumns
	= @JoinColumn(name = "driver_id"),
	inverseJoinColumns = @JoinColumn(name = "engine_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Engine> engines = new HashSet<Engine>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "driver_track", joinColumns
	= @JoinColumn(name = "driver_id"),
	inverseJoinColumns = @JoinColumn(name = "track_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Track> tracks = new HashSet<Track>();
	
	@OneToMany(mappedBy = "driver",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<DriverTrackStats> driverStats = new HashSet<DriverTrackStats>();
	
	
	
}
