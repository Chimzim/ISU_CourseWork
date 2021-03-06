package com.example.gasemissionsrobot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author chimz
 * Connects to the sample site repository
 */
@RestController
public class SampleSiteController {

    /**
     * wires to the food repository
     */
    @Autowired
    SampleSiteRepository db;


    /**
     * Creates a Sample Site and then returns that sample site
     * @param site
     * @return
     */
    @PostMapping("/sampleSite")
    sampleSite createSampleSite(@RequestBody sampleSite site) {
        db.save(site);
        return site;
    }

    /**
     * Returns all the sample sites on the database
     * @return - List of all the sample sites saved in the database
     */
    @GetMapping("/sampleSite")
    List<sampleSite> getSampleSites(){
        return db.findAll();
    }

    /**
     * Deletes the given sample site from the database
     * @param site - The sample site to delete from the database
     */
    @DeleteMapping("/sampleSite") //deletes
    void deleteSampleSite(@RequestBody sampleSite site) {
        db.delete(site);
    }

}

