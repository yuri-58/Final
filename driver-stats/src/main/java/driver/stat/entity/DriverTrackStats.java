package driver.stat.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class DriverTrackStats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statId;
	private String stat0to60Time;
	private String stat0to100Time;
	private String statFastestLapTime;
	private String statTopSpeed;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "track_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Track track;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Driver driver;
	
	
	
}
