package driver.stat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import driver.stat.entity.Engine;

public interface EngineDao extends JpaRepository<Engine, Long>{

}
