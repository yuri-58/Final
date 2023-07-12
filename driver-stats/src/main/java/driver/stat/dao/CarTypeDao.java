package driver.stat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import driver.stat.entity.CarType;

public interface CarTypeDao extends JpaRepository<CarType, Long>{

}
