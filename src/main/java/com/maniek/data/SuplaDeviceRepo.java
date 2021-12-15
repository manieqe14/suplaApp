package com.maniek.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="suplaDevices", path="suplaDevices")
public interface SuplaDeviceRepo extends JpaRepository<SuplaDevice, Integer> {

}
