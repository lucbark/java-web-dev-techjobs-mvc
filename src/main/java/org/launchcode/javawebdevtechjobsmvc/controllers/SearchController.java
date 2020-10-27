package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) { //search method
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("/search/results") //Step 1
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) { //Step 2,3,4
        ArrayList<Job> jobs;

        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){ //Step 5
            model.addAttribute("title", "All Jobs");
            jobs = JobData.findAll();

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);//Step 6 Passing jobs into the search.html via model parameter
        model.addAttribute("columns", columnChoices); // Step 7passing ListController.columnChoices

        return "/search/results";
    }

}
