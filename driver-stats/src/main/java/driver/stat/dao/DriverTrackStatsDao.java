package driver.stat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import driver.stat.entity.DriverTrackStats;

public interface DriverTrackStatsDao extends JpaRepository<DriverTrackStats, Long>{

}
