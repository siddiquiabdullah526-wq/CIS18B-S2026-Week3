package edu.norco.cis18b.week3.coffeeshop;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        Part1Test.class,
        Part2Test.class,
        Part3Test.class,
        Part4Test.class,
        ScoreReportTest.class
})
public class AllTests {
}