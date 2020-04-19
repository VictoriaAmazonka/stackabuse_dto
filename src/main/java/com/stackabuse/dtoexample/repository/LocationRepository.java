package com.stackabuse.dtoexample.repository;

import com.stackabuse.dtoexample.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
