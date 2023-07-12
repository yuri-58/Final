package driver.stat.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class CarType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carId;
	private String carMake;
	private String carModel;
	private String carEdition;
	private Integer carYear;
	
	@ManyToMany(mappedBy = "carTypes",
			cascade = CascadeType.PERSIST)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Driver> drivers = new HashSet<Driver>();
	
}
