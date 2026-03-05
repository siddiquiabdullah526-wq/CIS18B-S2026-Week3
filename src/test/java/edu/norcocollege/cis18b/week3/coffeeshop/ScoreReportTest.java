package edu.norco.cis18b.coffeeshop;

import edu.norco.cis18b.coffeeshop.grading.ScoreExtension;
import org.junit.jupiter.api.Test;

/**
 * Prints a final score line for autograding logs.
 * This test always passes.
 */
public class ScoreReportTest {

    @Test
    void printScoreSummary() {
        ScoreExtension.ScoreSummary s = ScoreExtension.summarize();

        System.out.println("==================================================");
        System.out.println("FINAL SCORE: " + s.earned() + " / " + s.possible());
        System.out.println("==================================================");
    }
}