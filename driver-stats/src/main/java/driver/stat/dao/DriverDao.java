package driver.stat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import driver.stat.entity.Driver;

public interface DriverDao extends JpaRepository<Driver, Long>{

}
