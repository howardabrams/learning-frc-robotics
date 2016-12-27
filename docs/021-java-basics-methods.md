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

When you re-run your `MyGame`, you'll see the robot slowly crash into
the wall on the right side of the field. That's progress.

Let's have it crash into a different wall.

Writing a Better Move
----------------------------------------------------------------------

Let's get our robot to move based on the value of the `direction`
field variable.  For instance, if `direction` was set to `45` (for
45°), the robot should move to the north *and* east.
How much should we change the `x` and `y` field variables?
If the `x` and `y` field variables changed by the same amount, the
robot would move at a 45° angle.  While 45° is quite straightforward,
60° or even -13° may be more of a challenge.

Our pretend robots run around on a normal coordinate field (called a
[Cartesian Coordinate System](https://en.wikipedia.org/wiki/Cartesian_coordinate_system)),
so we can use *math* to figure out the change to the `x` and `y` field
variables based on an arbitrary angle.

> x + cos(direction) * amount

> y + sin(direction) * amount

This uses a little trigonometry to shift a robot from a specific *x*,
*y* coordinate to another based on a particular angular direction and
some amount using the [sine][sin] and [cosine][cos] math functions.

To render these two math equations in our Java code, we would write a
`move` method, like:

```java
void move (int amount) {
  x = x + Math.cos(direction) * amount;
  y = y + Math.sin(direction) * amount;
}
```

Notice the [sine][sin] and [cosine][cos] functions in Java, are
located in `Math`, so that is why I needed to write `Math.sin` and
`Math.cos`.

I suppose we should have a `turn` method to change the direction:

```java
void turn (int amount) {
  direction = direction + amount;
}
```

If you want a shortcut, adding a value to a variable, like `x`, you can write:

```java
x += 5
```

So our `turn` function could be:

```java
void turn (int amount) {
  direction += amount;
}
```

Crash into the Corner
----------------------------------------------------------------------

With our new methods, we can remove the `moveEast` method, and update
our `step` method to crash our robot somewhere else:

```java
void step() {
  move(3);
  turn(3);
}
```

When you run the `MyGame` program, you will see that for each forward
movement, the robot also turns to the right, until it eventually
crashes. Play around with the numbers, and get it turning in a
circle. Notice that even driving in a circle, the robot's circular
path will shift and eventually, it will run into a different wall. Why
do you think that is the case?

Summary
----------------------------------------------------------------------

At this point, we have a pretend robot that can both position itself
on a playing field, but can also change its direction and with a bit
of trigonometry, it can also change its position along a path.

If you think about the pretend robot we've built, we really want to
concentrate on the robot's behavior and not on the mechanics of the
field's coordinate system. Features like the `x` and `y` fields and
the `move` and `turn` methods, isn't something we should really
concern ourselves. In [the next chapter][025], we'll show you how to
jettison those details and let us concentrate on our robot's
behavior... which is really our goal.

  [020]: ../020-java-basics-1.md
  [025]: ../025-java-basics-inheritance.md
  [sin]: https://en.wikipedia.org/wiki/Trigonometric_functions#sine
  [cos]: https://en.wikipedia.org/wiki/Trigonometric_functions#cosine
