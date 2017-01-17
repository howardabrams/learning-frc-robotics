---
layout: subpage
title: Introduction to Java Inheritance
---
This chapter concludes our [basic introduction to the Java programming][020]
language.  I wouldn't expect you to be an expert, yet, but before we
move onto more advanced Java subjects, I thought I would answer some
frequently asked questions that many students have.

Keep in mind that most *features* of a computer language has a
trade-off with other features, so many of these questions students are
often features new programmers generally don't like, but hopefully,
after thes answers, they won't annoy you as much.

### What's up with the semi-colons?

  All spaces are *optional*, so these lines are the same:

```python
    x = 1 + foo
    x=1+foo
```

  The above is actually Python, which ignores *most* spaces...except at
  the beginning of a line. Java wanted spaces to be optional too, but
  the designers also wanted to be able to have multiple statements on
  a single line.

  Look at this code. Are we trying to assign a variable and call a function?

```python
    x = 1 + foo   bar()
```

  Or assign a variable the results of the function?

```python
    x = 1 + foobar()
```

  Java makes it clear by *terminating* all statements with a semi-colon:

```python
    x = 1 + foo;    bar();
```

  Typically, we do not place multiple statements on a line, but this
  is the reason why Java has so many semi-colons.

### What's up with Comments?

   Java evolved from a language where comments started
   with `/*` and ended with `*/ ` like:

```c
   /* This is a comment */
```

   This meant that they could easily go on multiple lines:

```c
   /*
         We can have a comment with lots of information.
       Even on multiple lines.
                  Like this. */
```

   No one likes to *hunt* for the first and last parts (especially since
   editors at that time didn't use multiple colors), so we lined up
   the asterisks:

```c
/*
 *     We can have a comment with lots of information.
 *   Even on multiple lines.
 *              Like this.
 */
```

   Other languages need a single character, and comments go to the end
   of the line:

```java
// Each comment begins with two slashes
// No ending character needed.
```

   Java decided they liked both, so you can use either style.

   Java allows you to *document* classes, functions and variables with
   an initial comment. Editors, like Eclipse, can then render those
   comments as instructions on how to call a function.

   Those comments start with two asterisks: `/**`

   And they must be before the class, function or variable they
   describe. **Note:** We document all our of functions as it makes
   debugging much easier.

```java
/*
 * Calculates and returns the nth factorial, where
 *      1! = 1, 2! = 2 * 1, 3! = 3 * 2 * 1, etc.
 * <b>Note:</b> large values of <code>n</code> may be too large
 * to process.
 * @param n any number greater than 0
 * @return the value of n multiplied by all numbers less than n
 */
long fact(int n) {
    if (n < 1)
        return 0;
    else if (n == 1)
        return 1;
    else
        return n * fib(n - 1);
}
```

### What's up with all the Text?

   Suppose Jenny wrote this function to times a number by itself:

```java
int square(int n) {
  return n * n;
}
```

   And Emma wrote this function to draw a square of a particular size
   where the mouse is located:

```java
int square(int size) {
  graphicsPage.drawRectangle(mouse.x, mouse.y, size, size);
}
```

   And you wrote this code:

```java
square(5);
```

   How does Java know what to do?

   Every thing in Java has a *long name* and a *short name*.
   Java uses `import` to give a *short name* to something by its *long name*.
   For instance...

```java
package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

class Robot extends IterativeRobot {
...
```

   Here, we make a `Robot` that uses something called `IterativeRobot`

   That's its short name.

   We could have written:

```java
 class Robot extends edu.wpi.first.wpilibj.IterativeRobot {
```

   But that is too long and Java is wordy enough.

 [020]: ../020-java-basics-1.md
