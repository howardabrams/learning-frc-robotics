---
layout: subpage
title: Introduction to Java Methods
---
This chapter assumes that you have created `MyRobot.java` and updated
`MyGame.java` in the [previous chapter][020]. In this chapter, we'll
get our robot to move across the playing field.

A Move Function
----------------------------------------------------------------------

Let's create a function to move a robot towards the east-side of the
playing field (towards the right from how we are looking at it). In
many languages, you might do something like:

```java
moveEast (Robot someRobot, int amount) { // Warning! Not real Java
  someRobot.x = someRobot.x + amount;
}
```

Here we are just changing a robot's *x* coordinate by some amount.  In
Java, we assume that the robot should *move itself*. In other words,
we place the function *inside* the Robot's definition:

```java
class Robot {
  String name;
  int x;
  int y;
  int direction;

  void moveEast (int amount) {
    x = x + amount;
  }
}
```

**Note:** A function *inside a class definition* is called a
**method**, and it is special in that it knows that `x` refers to the
variable field `x` in the current instance of the robot.

Just ignore the `void` statement. It actually means *nothing*, but is
required in Java. You'll learn the details later...I'm just trying to
paint a programming picture right now.

This ability to group variables and functions *inside* a definition
gives us our **second step** to organize our code. Here `move` is
*associated* with our robot, and we call it like:

```java
myRobot.moveEast(39);
```

The `moveEast` is specific to our robot, but we could have a
`moveEast` function that we associate with a dancer, but that would be
a completely different function (we'd probably call it `boogieEast`
anyway).

This sort of organization and grouping is the second lesson you
should know.

Moving our Robot
----------------------------------------------------------------------

Where would we call the `myRobot.moveEast` method? The way our pretend
robot game works, is our robots need to make a method called `step`,
which will be called repeatedly until the robot crashes into something
or finishes the game.

Add the following methods to your `MyRobot.java` code:

```java
void moveEast (int amount) {
  x = x + amount;
}

void step() {
  moveEast(3);
}
```

When you re-run your `MyGame` code, you'll see the robot slowly crash
into the wall on the right side of the field. That's progress.

Let's have it crash into a different wall.

Shall We Turn?
----------------------------------------------------------------------

We could create methods like `moveNorth` and `moveWest`, but would we
really want to create `moveNorthWest` and `moveNorthNorthWest`? That
seems be getting a bit silly.  Obviously our next feature we'd like to
write is a `turn` method that would look something like:

```java
void turn(angle) {
  direction = direction + angle;
}
```

Add that to your robot code.

Next, we could have a simple `move` method that used the current
direction and move at any angle. But that method involves a bit of
math, so I will save it for [the next chapter][023].


Summary
----------------------------------------------------------------------

At this point, we have a pretend robot that can both position itself
on a playing field, but can also change its position along a
path... pretty much just to the right, but we'll fix that.

Not only are we saving information in field variables, but we are
changing those variables, and those changes affect the behavior.  The
collection of values in all of a class' field variables is an object's
*state*. For example, the `x`, `y` and `direction` fields in an
instance of the `Robot` class (an object) determines where it will be
shown on the field. If the `x` field variable was set to `-300`, the
robot will be positioned off the screen and wouldn't be seen! We need
to make sure a robot's *state* is always valid.

Hrm... how would you think we could help guarantee that? Let's move on
to the [next chapter][023] where we both create a `move` method that
can position the robot based on its direction, but can also test to
make sure our code is good.

  [020]: ../020-java-basics-1.md
  [023]: ../023-java-basics-unit-testing
