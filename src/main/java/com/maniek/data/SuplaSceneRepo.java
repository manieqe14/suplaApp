package com.maniek.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="suplaScenes", path="suplaScenes")
public interface SuplaSceneRepo extends JpaRepository<SuplaScene, Integer> {
	
}
