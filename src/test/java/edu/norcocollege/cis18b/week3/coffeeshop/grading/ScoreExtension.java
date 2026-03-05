package edu.norco.cis18b.coffeeshop.grading;

import org.junit.jupiter.api.extension.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Scores by test class (Part1Test, Part2Test, ...).
 * If ANY test in a Part fails -> that Part earns 0 points.
 * If ALL tests in a Part pass -> that Part earns full points.
 *
 * This avoids students getting "partial" credit for a part that is still broken.
 */
public class ScoreExtension implements TestWatcher {

    private static final Map<String, Boolean> partFailed = new ConcurrentHashMap<>();
    private static final Map<String, Integer> partPoints = new ConcurrentHashMap<>();

    private static String partKey(ExtensionContext context) {
        // The top-level test class (not nested test methods)
        return context.getRequiredTestClass().getName();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        String key = partKey(context);
        partFailed.putIfAbsent(key, false);

        // Record point value if annotated
        Points points = context.getRequiredTestClass().getAnnotation(Points.class);
        if (points != null) {
            partPoints.putIfAbsent(key, points.value());
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String key = partKey(context);
        partFailed.put(key, true);

        Points points = context.getRequiredTestClass().getAnnotation(Points.class);
        if (points != null) {
            partPoints.putIfAbsent(key, points.value());
        }
    }

    /**
     * Called by the scorer test to compute totals.
     */
    public static ScoreSummary summarize() {
        int earned = 0;
        int possible = 0;

        for (Map.Entry<String, Integer> e : partPoints.entrySet()) {
            String part = e.getKey();
            int pts = e.getValue();
            possible += pts;
            boolean failed = partFailed.getOrDefault(part, false);
            if (!failed) earned += pts;
        }

        return new ScoreSummary(earned, possible);
    }

    public record ScoreSummary(int earned, int possible) {}
}