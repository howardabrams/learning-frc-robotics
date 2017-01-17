---
layout: subpage
title: Introduction to Testing Java
---
This chapter assumes that you have updated your `MyRobot.java` code in
the [previous chapter][021] to have a `turn` method.  In this chapter,
we'll write a `move` method to move our robot in any direction, and
learn how to make sure our code is correct.


Updating our Turn Method
----------------------------------------------------------------------

Remember our `turn` method to change the direction from our [previous chapter][021]?

```java
void turn (int amount) {
  direction = direction + amount;
}
```

If you want a shortcut, adding a value to a variable, like `x`, you
can write:

```java
x += 5
```

So our `turn` function could be:

```java
void turn (int amount) {
  direction += amount;
}
```


Writing a Better Move
----------------------------------------------------------------------

Let's get our robot to move based on the value of the `direction`
field variable.  For instance, if `direction` was set to `45` (for
45°), the robot should move to the north *and* east.
How much should we change the `x` and `y` field variables?

If the `x` and `y` field variables changed by the same amount, the
robot would move at a 45° angle.  While 45° is quite straightforward,
60° or even -13° may be more of a challenge.

Our pretend robots run around on a normal coordinate field (called
a [Cartesian Coordinate System][cart]), so we can use *math* to figure
out the change to the `x` and `y` field variables based on an
arbitrary angle.

> *x + cos(direction) × amount*

> *y + sin(direction) × amount*

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
`Math.cos`, and the multiplication is specified by an asterisk: *.

Why does Eclipse underline those lines of code in red? If you use your
mouse to hover over the red lines, you will see that Eclipse gives you
two options:

  ![Screen shot](../public/images/020-java-basics/fixing-cosine.png)

What is a `double`? No, it isn't twice as much, it is a way that Java
can remember a number that is a fractional value. `int` is a whole
number, but `double` is a *floating point number*, like `3.1415`.
This will actually make our robot's position on the field more
accurate, so select that for each red line, and notice the type
changes for the `x` and `y` variable fields at the top of the class.


Is it Right?
----------------------------------------------------------------------

With no red underlines, your code will now compile, and if you have
typed these changes into your `MyRobot` code, you may feel pretty good
about updating the `step` method to call both `move` and `turn` to
drive around the field.

But is our code correct?

Having correct code is very important, but knowing your code is
correct is surprisingly difficult. Do you see the bug we've written?
Chances are, you didn't.

This makes testing our code critical.

Sure, we can run our programs to make sure they are correct, however,
relying on running a program, isn't such a good idea.  You see, when I
was younger, I would run my code in every possible way to flush out
the bugs. Since I'm not a very patient person, an exhaustive test
didn't happen more than once. This meant that bugs that snuck in while
working on a new feature may only get caught when I gave the program
to someone else to use.

So later, I started writing code to run my code.

Code Testing Code
----------------------------------------------------------------------

We want to write some code to help us test our code. Often beginning
Java programmers, use `main` and look at the output:

```java
public static void main(String[] args) {
  Robot r = new Robot(0, 0, 0);
  r.move(10);
  System.out.println("Our robot is at ", r.x, r.y);
}
```

When we see a `10`, we assume we must be good! Right? Using your eyes
at first is okay, but we don't depend on something so error-prone. How
about this?

```java
public static void main(String[] args) {
  Robot r = new Robot(0, 0, 0);
  r.move(10);
  if (r.x != 10 && r.y != 0)
     throw new Exception("Our move doesn't work!");
}
```

That's better! Now, if what we think *should* be true, is not, then we
will really see some fireworks when we run our program.

What should we do when we want to verify another method, like `turn`?
Do we delete the existing `move` testing code? Do we add more code to
`main`? Just how big can `main` get? A large `main` method will be
hard to read.

We should have a better way.


First Class Testing with JUnit
----------------------------------------------------------------------

Good programmers write code to test their code, because, the more
tests you write, the more confident you are that your program works.

Keep in mind, all the tests in the world doesn't prove it, though!

To help write tests, we use a testing framework
like [JUnit][test]. Try this in Eclipse,

You then write a function as a test. These look
like this:

```java
public void testMoveRight() {
    Robot someRobot = new Robot("bob", 0, 0, 0);
    someRobot.move(10);
    assertEquals(10, someRobot.x);
    assertEquals( 0, someRobot.y);
    assertEquals( 0, someRobot.direction); // Just to make sure!
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

We've really improved our pretend robot and have it acting more like
how a real robot would behave. It can now change its direction and
with a bit of trigonometry, it can also move in any direction on the
playing field.

If you think about the pretend robot we've built, we really want to
concentrate on the robot's behavior and not on the mechanics of the
field's coordinate system. Features like the `x` and `y` fields and
the `move` and `turn` methods, isn't something we should really
concern ourselves. In [the next chapter][025], we'll show you how to
jettison those details and let us concentrate on our robot's
behavior... which is really our goal.

  [021]: ../021-java-basics-methods.md
  [025]: ../025-java-basics-inheritance.md
  [sin]: https://en.wikipedia.org/wiki/Trigonometric_functions#sine
  [cos]: https://en.wikipedia.org/wiki/Trigonometric_functions#cosine
  [cart]: https://en.wikipedia.org/wiki/Cartesian_coordinate_system
  [test]: http://junit.org/junit4/
