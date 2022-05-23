package com.example.gasemissionsrobot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author chimz
 * Creates a sample site repository
 */
@Repository
public interface SampleSiteRepository extends JpaRepository<sampleSite, Integer>{
    List<sampleSite> findByTitle(String title);
}

