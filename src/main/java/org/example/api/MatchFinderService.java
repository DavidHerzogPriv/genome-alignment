package org.example.api;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchFinderService {

    public DnaMatchResponse findMatch(MultipartFile file, DNARequest request) throws IOException {
        System.out.println("Starting to find match");
        String sequence = new String(file.getBytes(), StandardCharsets.UTF_8).replace("\n", "");

        String pattern = request.pattern().replace("\n", "");

        double maxNumberOffDifference = (request.matchPercentage() / 100.0) * pattern.length();

        List<String> matches = new ArrayList<>();
        for (int i = 0; i < sequence.length(); i++) {
            if (i + pattern.length() >= sequence.length()) {
                break;
            }
            String substring = sequence.substring(i, i + pattern.length());
            int difference = difference(substring, pattern, maxNumberOffDifference);
            if (difference < maxNumberOffDifference) {
                System.out.println("Found match:");
                System.out.println(substring);
                matches.add(substring);
            }
        }
        if(matches.isEmpty()) {
            System.out.println("No Matches Found.");
        }
        System.out.println("Found the following number of matches: " + matches.size());
        return new DnaMatchResponse(matches);
    }

    private static int difference(String str1, String str2, double maxDifference) {
        int differences = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (differences > maxDifference) {
                break;
            }
            if (str1.charAt(i) != str2.charAt(i)) {
                differences++;
            }
        }
        return differences;
    }
}
