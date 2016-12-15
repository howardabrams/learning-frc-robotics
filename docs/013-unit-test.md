---
layout: page
title: Unit Testing
---
Check out this code from our example Robot class:

{% highlight java %}

    void move(int amount) {
        x += Math.cos(direction) * amount;
        y += Math.sin(direction) * amount;
    }

{% endhighlight %}

Think it is right? How do you know? How do you know any of your code will work when you download it to the robot?

###Manual Testing
Sure, we run our programs to make sure they are correct, right? However, relying on running a program, isn't such a good idea. You need to be very sure.

Not to mention you need to run your program in every which way every time you make a change, because you don't know that changing code over here doesn't break something over there.

In math, we use axioms as true bits (like 0 is a natural number, and every natural number has a successor) to create proofs to verify mathematical rules.

But can you prove a program?

You can, but it is difficult. Instead, we typically strive for good confidence in our code.

###Code Testing Code
We want to write some code to help us test our code. Often beginning Java programmers, use `main` and look at the output:

{% highlight java %}

    public static void main(String[] args) {
      Robot r = new Robot(0, 0, 0);
      r.move(10);
      System.out.println("Our robot is at ", r.x, r.y);
    }

{% endhighlight %}

We then see a `10`, so we must be good! Right? Using your eyes at first is okay, but we don't depend on something so error-prone. How about this?

{% highlight java %}

    public static void main(String[] args) {
      Robot r = new Robot(0, 0, 0);
      r.move(10);
      if (r.x != 10 && r.y != 0)
         throw new Exception("Our move doesn't work!");
    }

{% endhighlight %}

That's better! Now, if what we think *should* be true is not, then we will really see some fireworks when we run our program.

But this is still not ideal. What do we do when we want to verify another method, like `turn`? Do we delete the existing `move` testing code? Do we add more code to `main`?

Besides, reading through all those thrown exceptions (as we write more and more tests) will make it hard to read.

We should have a better way.

###First Class Testing: JUnit
Good programmers write code to test their code, because, the more tests you write, the more confident you are that your program works.

Keep in mind, all the tests in the world doesn't prove it, though!

To help write tests, we use a testing framework like [JUnit](http://junit.org/junit4/). You then write a function as a test. These look like this:

{% highlight java %}

    public void testMoveRight() {
        Robot someRobot = new Robot("bob", 0, 0, 0);
        someRobot.move(10);
        assertEquals(10, someRobot.x);
        assertEquals( 0, someRobot.y);
        assertEquals( 0, someRobot.direction); // Just to make sure!
    }

{% endhighlight %}

The asserts state what should be, and if it doesn't, it throws an exception, and that fails our tests. We basically depend on these two:

* **`assertEquals`:** Compares what you want with what you got
* **`assertTrue`:** Contains a condition that much be true

Tests are grouped in test cases. A minimal JUnit test case class look like:
{% highlight java %}

    package blah; // Must be same as class you're testing

    import junit.framework.TestCase;

    public class RobotTest extends TestCase {
        // ... tests go here ...
        public void testField() {
        }
    }
    
{% endhighlight %}
**Note:** Version 3 has all tests start with word `test`. Version 4 has a different approach, but I like both versions but for different reasons. For this tutorial, we'll stick to version 3, but Eclipse will offer to help with either.

Let's see how good our `move` code is. Here is our current test case:

{% highlight java %}

package org.usfirst.frc.team2733.machines;
import junit.framework.TestCase;

public class RobotTest extends TestCase {
    public void testMoveRight() {
        Robot someRobot = new Robot("bob", 0, 0, 0);
        someRobot.move(10);
        assertEquals(10, someRobot.x);
        assertEquals(0, someRobot.y);
        assertEquals(0, someRobot.direction);
    }

    public void testMoveUp() {
        Robot someRobot = new Robot("bob", 0, 0, 90);
        someRobot.move(10);
        assertEquals(0, someRobot.x);
        assertEquals(10, someRobot.y);
        assertEquals(90, someRobot.direction);
    }
}

{% endhighlight%}

Here we move the robot to the right (with the direction pointed to 0°), and another test to move the robot "up" (where the direction is 90°). Running the JUnit runner so that:

{% highlight java %}

junit.framework.AssertionFailedError: expected:<0> but was:<-4>
at junit.framework.Assert.fail(Assert.java:47)
at junit.framework.Assert.failNotEquals(Assert.java:282)
at junit.framework.Assert.assertEquals(Assert.java:64)
at junit.framework.Assert.assertEquals(Assert.java:201)
at junit.framework.Assert.assertEquals(Assert.java:207)
at org.usfirst.frc.team2733.machines.RobotTest.testMoveUp(RobotTest.java:42)

{% endhighlight%}

Looking at the line number given, we have the line that makes this assertion:

{% highlight java %}

assertEquals(0, someRobot.x);

{% endhighlight%}

Why is this the case? Clearly we have a bug in our program and writing tests helped us realize that our move method is incorrect. (In case you are wondering, the bug is that `Math.sin()` and `Math.cos()` operate on *radians*, not *degrees*, like we are passing in. We need to correct the function to look like:

{% highlight java %}

    void move(int amount) {
        double rads = Math.toRadians(direction);
        x += Math.cos(rads) * amount;
        y += Math.sin(rads) * amount;
    }
    
{% endhighlight%}

Now our test pass. However, we should verify that by writing a function where the direction is 45 degrees, and maybe -72!

