package driver.stat.controller.model;

import java.util.Set;

import driver.stat.entity.CarType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverCarType {
	private Long carId;
	private String carMake;
	private String carModel;
	private String carEdition;
	private Integer carYear;
	
	Set<DriverCarType> cars;
	DriverEngine engine;
	
	public DriverCarType(CarType save) {
		carId = save.getCarId();
		carMake = save.getCarMake();
		carModel = save.getCarModel();
		carEdition = save.getCarEdition();
		carYear = save.getCarYear();
	}
}
