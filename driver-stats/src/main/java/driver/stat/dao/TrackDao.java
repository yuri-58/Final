package driver.stat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import driver.stat.entity.Track;

public interface TrackDao extends JpaRepository<Track, Long>{

}
