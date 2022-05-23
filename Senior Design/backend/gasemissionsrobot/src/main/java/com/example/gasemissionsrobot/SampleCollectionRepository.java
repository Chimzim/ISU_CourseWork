package com.example.gasemissionsrobot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleCollectionRepository extends JpaRepository<sampleCollection, Integer> {
    List<sampleCollection> findByID(int sampleID);
}
