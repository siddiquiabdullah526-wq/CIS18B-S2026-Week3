# Week 3 – Classes and Objects Review, and Design Patterns

### Coffee Shop Ordering System

## Overview

In this assignment you will implement parts of a **coffee shop ordering system** using core **Object-Oriented Programming concepts** and basic **design patterns**.

You will build the system incrementally using:

* Classes and objects
* Encapsulation and validation
* Inheritance and polymorphism
* Interfaces
* The **Builder Pattern**
* The **Singleton Pattern**

The assignment is divided into several parts that build upon each other.

Your code will be **automatically graded using JUnit tests**, so it is important that you follow the **method names, class names, and behaviors exactly as specified**.

### How to Use This Repository

This repository not only contains your assignment intructions, but also the scaffolding for your assignment submission.  You will create a [gitlab codespace](https://docs.github.com/en/codespaces/developing-in-a-codespace/creating-a-codespace-for-a-repository) which will automatically [fork](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/working-with-forks/fork-a-repo) this repository and create a version in your github account that you can use.

Next, you'll create a [branch](https://docs.github.com/en/codespaces/developing-in-a-codespace/using-source-control-in-your-codespace#creating-or-switching-branches-1) (called a *feature branch*) where you will commit your code.  While we won't be really utilizing the full power of feature branches just yet, I want you to be comfortable with them as they will become important when we begin collaborating with our classmates in future projects.

[Commit](https://docs.github.com/en/codespaces/developing-in-a-codespace/using-source-control-in-your-codespace#committing-your-changes-1) your changes at LEAST after each assignment part, but you should commit them more often and include well-structured commit messages.

Finally, once you've completed all of your changes, you'll raise a [Pull Request](https://docs.github.com/en/codespaces/developing-in-a-codespace/using-source-control-in-your-codespace#raising-a-pull-request-1) against your `main` branch.  Once you've raised a Pull Request, a series of automated tests will run and indicate whether or not you've completed all parts of the assignment, and will give you a preliminary evaluation of your assignment.

You can raise a pull request once, and then continue to commit multiple times after the pull request has been raised.  Each time you commit and push to your repository, the pull request will automatically update and the automated tests will re-run.  You can submit them as many times as you'd like.

#### How to build and run your project

NOTE: While you can use the `javac` and `java` commands to compile and run your project, we'll start with professional tools early and often in this course by using maven to build and run your app.

Take a look at the `pom.xml` file in the root of this repository if you'd like to get a better understanding of how this project is structured.

For now, you can build and run the tests for your project by using the following command:

```bash
mvn clean test
```

The error outputs will help you better understand the gaps between your project and the expected results.

```bash
# Build and run the app
mvn compile exec:java -Prun-app
```

You can use this to test each part of your assignment independently.

### FINAL SUBMISSIONS

For your final submission, you'll copy/paste a link to your pull request in github into the submission box in Canvas.

---

# Learning Objectives

After completing this assignment you should be able to:

* Implement classes with proper encapsulation and validation
* Design object relationships using inheritance
* Use interfaces to decouple program components
* Apply basic design patterns (Builder and Singleton)
* Write maintainable, well-structured Java code

---

# Project Structure

Your project must use the following package:

```
edu.norco.cis18b.week3.coffeeshop
```

Your classes should be placed in:

```
src/main/java/edu/norco/cis18b/week3/coffeeshop
```

You do **not** need to write any test code. Tests will be provided by the autograder.  However, you can use the `App.java` class to write a main method to test your application.

---

# Part A — Menu Items and Orders (Classes and Encapsulation)

## MenuItem Class

Create a class called:

```
MenuItem
```

### Fields

```
sku   (String)
name  (String)
price (BigDecimal)
```

### Constructor

Your constructor must validate:

* `sku` cannot be null or blank
* `name` cannot be null or blank
* `price` cannot be null
* `price` must be **greater than or equal to zero**

If validation fails, throw:

```
IllegalArgumentException
```

### Methods

Implement the following methods:

```
String getSku()
String getName()
BigDecimal getPrice()
String toString()
```

The format of `toString()` does not matter as long as it returns a non-empty string.

---

## Order Class

Create a class called:

```
Order
```

### Fields

```
orderId (String)
items   (List<MenuItem>)
```

### Constructor

The constructor should validate that:

```
orderId is not null or blank
```

---

### Methods

```
void addItem(MenuItem item)
```

Requirements:

* Reject null items
* Add the item to the order

```
List<MenuItem> getItems()
```

Requirements:

* Return an **unmodifiable list**
* The caller must not be able to modify the internal list

```
BigDecimal total()
```

Requirements:

* Return the **sum of all item prices**

```
String getOrderId()
```

---

# Part B — Beverage Inheritance

Next, we will introduce inheritance.

Create an **abstract class** called:

```
Beverage
```

It should extend:

```
MenuItem
```

---

## Size Enum

Inside the `Beverage` class define the following enum:

```
Size

SMALL
MEDIUM
LARGE
```

---

## Fields

```
Size size
```

---

## Constructor

The constructor should accept:

```
sku
name
basePrice
size
```

---

## Size Multiplier

Implement the method:

```
BigDecimal sizeMultiplier()
```

Return the following values:

| Size   | Multiplier |
| ------ | ---------- |
| SMALL  | 1.00       |
| MEDIUM | 1.20       |
| LARGE  | 1.40       |

---

## Latte Class

Create a class:

```
Latte
```

Requirements:

* Extends `Beverage`
* Base price = **4.50**
* Price should be:

```
basePrice × sizeMultiplier
```

---

## ColdBrew Class

Create a class:

```
ColdBrew
```

Requirements:

* Extends `Beverage`
* Base price = **4.00**
* Price calculation is the same as Latte.

---

# Part C — Payment System (Interfaces)

Next we will decouple the payment system using an interface.

---

## PaymentReceipt Record

Create a record:

```
PaymentReceipt
```

Fields:

```
String orderId
BigDecimal amount
String method
Instant timestamp
```

---

## PaymentMethod Interface

Create an interface:

```
PaymentMethod
```

Method:

```
PaymentReceipt pay(String orderId, BigDecimal amount)
```

---

## CreditCardPayment

Create a class:

```
CreditCardPayment
```

Implements:

```
PaymentMethod
```

### Constructor

```
CreditCardPayment(String last4)
```

Requirements:

* `last4` must be exactly **4 characters**
* Otherwise throw `IllegalArgumentException`

---

### pay() Method

Return a receipt where:

```
method = CREDIT_CARD(****1234)
```

Example:

```
CREDIT_CARD(****1234)
```

---

## GiftCardPayment

Create a class:

```
GiftCardPayment
```

### Constructor

```
GiftCardPayment(BigDecimal initialBalance)
```

Requirements:

* Balance must be >= 0

---

### pay()

Requirements:

* Deduct the payment amount from the balance
* If insufficient balance:

```
throw IllegalStateException
```

---

### Additional Method

```
BigDecimal getBalance()
```

Returns the remaining balance.

---

# Part D — Design Patterns

In this section you will implement two design patterns.

---

# Builder Pattern — CustomDrink

Create a class:

```
CustomDrink
```

It should extend:

```
Beverage
```

---

## Enums

Add the following enums:

```
Milk
WHOLE
OAT
ALMOND
SKIM
```

```
Syrup
VANILLA
CARAMEL
HAZELNUT
NONE
```

```
Temperature
HOT
ICED
```

---

## Builder Class

Inside `CustomDrink`, create a nested class:

```
Builder
```

---

## Default Values

The builder should default to:

```
size = MEDIUM
milk = WHOLE
syrup = NONE
temperature = HOT
espressoShots = 1
```

---

## Builder Methods

Your builder must support:

```
size(Size size)
milk(Milk milk)
syrup(Syrup syrup)
temperature(Temperature temperature)
espressoShots(int shots)
addExtra(String extra)
build()
```

---

## Validation Rules

```
espressoShots must be >= 1
extras cannot be null or blank
```

---

## Pricing Rules

Base price:

```
4.25
```

Add the following:

| Feature             | Price |
| ------------------- | ----- |
| extra espresso shot | +0.75 |
| syrup               | +0.50 |
| each extra          | +0.25 |

Final price must include the **size multiplier**.

---

# Singleton Pattern — PricingCatalog

Create a class:

```
PricingCatalog
```

This class must be implemented as a **Singleton**.

---

## Required Method

```
static PricingCatalog getInstance()
```

---

## Price Lookup

Implement:

```
BigDecimal getBasePrice(String productKey)
```

Supported keys:

| Key       | Price |
| --------- | ----- |
| LATTE     | 4.50  |
| COLD_BREW | 4.00  |
| CUSTOM    | 4.25  |

If the key is unknown:

```
throw IllegalArgumentException
```

---

## Submission

The expected final submission is a link to the Pull Request you created against your main branch.  Once you've created a pull request (see the "Overview" section of assignment 1, or the video lecture from Week 2 to find out how), copy the URL / link for your Pull Request and paste into the submission box in Canvas.

### Requirements:
* Create a feature branch.
* Make at least three meaningful commits.
* Use clear commit messages.
* Open a Pull Request to the main branch.

## Grading Criteria

You will be graded on:
* Code compiles and runs
* Clean, readable structure
* Proper use of Git and PR workflow
* Completeness of required features
* Passes all of the given tests.

For a more complete rubric, please see the rubric attached to this assignment in Canvas.

---

# Grading

| Section              | Points |
| -------------------- | ------ |
| MenuItem + Order     | 20     |
| Beverage Inheritance | 20     |
| Payment Interface    | 25     |
| Builder Pattern      | 20     |
| Singleton Pattern    | 15     |

Total:

```
100 points
```

---

# Tips

* Use **BigDecimal** for money values.
* Follow the **exact class and method names**.
* Write small test programs locally to verify your logic.
* Build the system **incrementally**.