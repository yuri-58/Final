package driver.stat.controller.model;

import java.util.Set;

import driver.stat.entity.Engine;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverEngine {
	private Long engineId;
	private String engineModel;
	private Integer engineYear;
	private Integer engineHorsePower;
	private String engineNotes;
	
	Set<DriverEngine> engines;
	
	public DriverEngine (Engine save) {
		engineId = save.getEngineId();
		engineModel = save.getEngineModel();
		engineYear = save.getEngineYear();
		engineHorsePower = save.getEngineHorsePower();
		engineNotes = save.getEngineNotes();
	}
}
