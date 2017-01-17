---
layout: subpage
title: Introduction to Java Inheritance
---
This chapter assumes that you have updated `MyRobot.java` with both
`move` and `turn` methods as described in the [previous chapter][021].
In this chapter, we'll explain how to add fields and methods
*magically* from other classes through *inheritance*.

Making Specialized Robots
----------------------------------------------------------------------

If we were to run more than one robot on the field, we could do something like:

```java
MyRobot walle = new MyRobot();

walle.name = "Waste Allocation Load Lifter: Earth class";
walle.x = 101;
walle.y = 142;
walle.direction = 337;

field.addRobot(walle);

MyRobot eve = new MyRobot();

eve.name = "Extraterrestrial Vegetation Evaluator";
eve.x = -50;
eve.y = -26;
eve.direction = 45;

field.addRobot(eve);
```

Our `Robot` class has a `name` field that makes each robot unique.
But what if we had different *classes* of robots?  For instance, what
would it take to make a field like this:

   ![image](../public/images/01-robot-field-2-small.svg)

Perhaps each robot could store a new variable called `type` that could
then be used to decide how the robot should be displayed. The function
that drew the robots would be pretty complex. We could have each robot
*draw itself*, but this would mean we'd have to have a few classes, like:

  * `FourWheeledRobot`
  * `SixWheeledRobot`
  * `TankTreadRobot`
  * `RoundedRobot`

That could work, but much of the code, like the `move` method would
be the same! We don't want to repeat ourselves.

In this situation, what is unique is the *drawing* part, so what if
each robot type could draw itself, but some code could be shared?

Let's change our `MyRobot` definition:

```java
class MyRobot {
  String name;
  int x;
  int y;
  int direction;

  void move(int amount) {
    x = Math.cos(direction) * amount;
    y = Math.sin(direction) * amount;
  }

  void draw();   // Notice it doesn't DO anything
}
```

Unlike our `move` method, the `draw` function does not have any code.

Why? Well, each specific robot would have code unique for drawing
itself and its four or six wheels, but they could all *share* the
same fields and the `move` function.

We then create our unique definitions:

```java
class FourWheeledRobot extends MyRobot {
  void draw() {
     // We place the code to draw a robot with four wheels in here!
  }
}
```

   And:

```java
TankTreadRobot extends MyRobot {
  void draw() {
    // We place the code to draw a robot with four wheels in here!
  }
}
```

A *robot definition* is called a `Class`, and a `Class` that `extends`
another `Class` is a *subclass* (or *child* of a *parent* class).


Taking Advantages of Classes
----------------------------------------------------------------------

Let's simplify our `MyRobot` class, as our project provides a `Robot`
class we can use:

```java
class MyRobot extends Robot {
  // ...
}
```

Better yet, the `DefaultRobot` *extends* this `Robot` class, but also
supplies `x` and `y` fields, and other methods. If we extend this
class, then our robot would be more like what we'd expect a
*programmable robot* to be.

Here is the new, complete definition for `MyRobot.java`:

```java
import org.geeklet.machines.support.DefaultRobot;

class MyRobot extends DefaultRobot {

    public void step() {
        turnRight();
        forward();
    }
}
```

With this approach, we can concentrate on the behavior and hide many
of the details. Hiding details that may be important to the computer
system, but not really part of primary problem, is called
*abstraction*.

The ability to *abstract code* (hide it when appropriate) and still
share code and features is the **third important feature of Java** and
its object-oriented implementation.

*Wait a second...*

Did you catch the changes in the `step` method? Before we had a `move`
method that accepted a number of pixels, and placed the robot at that
location. That sounds like teleportation, and wouldn't be something we
should try to use when programming our pretend robots.

Instead, we now have more realistic methods, like move `forward` and
`backward`. Both the `turnRight` and `turnLeft` methods are more
realistic since they don't have to worry about angles or
degrees... just a slight turn made by changing a voltage value on a
wire that goes to a brushless motor system...

How to Learn Java...
----------------------------------------------------------------------

What have we learned so far?

  - We've learned about child and parent classes
  - Fields and methods in a parent class are shared to child classes
  - Hiding important code at the *right level* is called abstraction
  - Abstraction allows us to focus on one particular part of our problem

This concludes our basic introduction and overview of the Java
programming language (I do recommend reading
our [frequently asked questions about Java][029] next), but this
overview is probably not sufficient for a curious mind as yourself.

To learn more about the details of Java, you can pick up a number of
programming books, but perhaps you may enjoy the interactive lessons
at [codecademy.com][3].

Just create an account to save your progress and work your way
through the lessons.

   ![image](../public/images/01-codecademy-screen.png)

Once you feel comfortable with Java and [Eclipse][030] as a
programming tool, you'll want to read the next chapter
on [Testing Java][040].

  [3]: https://www.codecademy.com/learn/learn-java
  [029]: ../029-whats-up-with-java
  [030]: ../030-eclipse-overview
