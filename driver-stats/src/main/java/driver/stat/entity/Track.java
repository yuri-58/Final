package driver.stat.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trackId;
	private String trackName;
	private Integer trackPhoNum;
	private String trackEmail;
	private String trackAddress;
	private String trackCity;
	private String trackState;
	private Integer trackZipCode;
	
	@OneToMany(mappedBy = "track", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<DriverTrackStats> stats;
	
	@ManyToMany(mappedBy = "tracks",
			cascade = CascadeType.PERSIST)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Driver> drivers = new HashSet<Driver>();
	
}
