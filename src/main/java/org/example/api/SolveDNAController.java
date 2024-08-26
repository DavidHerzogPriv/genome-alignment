package org.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class SolveDNAController {

    //>Chr3[\s\S]*?(?=>Chr4)
    @Autowired
    private MatchFinderService service;

    @CrossOrigin
    @PostMapping("/dna-check")
    public DnaMatchResponse index(@RequestParam("file") MultipartFile file,
                                  @RequestParam("pattern") String pattern,
                                  @RequestParam("percentage") Integer percentage) throws IOException {
        DNARequest dnaRequest = new DNARequest(pattern, percentage);
        return service.findMatch(file, dnaRequest);
    }
}
