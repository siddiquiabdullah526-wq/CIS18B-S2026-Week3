package edu.norco.cis18b.coffeeshop.grading;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Points {
    int value();
}