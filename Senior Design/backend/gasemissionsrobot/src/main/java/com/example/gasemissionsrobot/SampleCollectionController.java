package com.example.gasemissionsrobot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author chimz
 * Connects to the sample Collection repository
 */
@RestController
public class SampleCollectionController {

    /**
     * wires to the food repository
     */
    @Autowired
    SampleCollectionRepository db;


    /**
     * Creates a sample collection instance of the collected data for that sample site and then returns the collected data
     * @param collectedData
     * @return
     */
    @PostMapping("/sampleCollection")
    sampleCollection createSampleSite(@RequestBody sampleCollection collectedData) {
        db.save(collectedData);
        return collectedData;
    }

    /**
     * Returns all the data for a corresponding sample sites on the database
     * @return - List of all the data for a corresponding sample sites saved in the database
     */
    @GetMapping("/sampleCollection")
    List<sampleCollection> getSampleSites(){
        return db.findAll(); //NEED to UPDATE TO TAKE IN A SAMPLE SITE ID
    }


}

