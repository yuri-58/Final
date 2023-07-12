package driver.stat.controller.model;

import java.util.Set;

import driver.stat.entity.Track;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackData {
	private Long trackId;
	private String trackName;
	private Integer trackPhoNum;
	private String trackEmail;
	private String trackAddress;
	private String trackCity;
	private String trackState;
	private Integer trackZipCode;
	
	Set<TrackData> tracks;
	
	public TrackData(Track save) {
		trackId = save.getTrackId();
		trackName = save.getTrackName();
		trackPhoNum = save.getTrackPhoNum();
		trackEmail = save.getTrackEmail();
		trackAddress = save.getTrackAddress();
		trackCity = save.getTrackCity();
		trackState = save.getTrackState();
		trackZipCode = save.getTrackZipCode();
	}
}
