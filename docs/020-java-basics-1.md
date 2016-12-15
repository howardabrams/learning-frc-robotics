---
layout: page
title: Introduction to Java
---
Introduction to Java
======================================================================

As we [mentioned before][1], programming a FIRST Robot means
programming its behavior and *smarts* in [Java][2], a programming
language popular in big companies, but not usually taught as an
introduction to computers. Let's change that, by walking through the
basics by creating some *pretend robots*.

  [1] ../000-introduction
  [2] https:*/go.java*index.html?intcmp=gojava-banner-java-com

Why we Teach Java
------------------------------------------------------------

  In the 1990s, Java was originally designed for interactive
  television systems (like an AppleTV), however, it expanded its
  *expertise* with the Internet. While it may not be as simple to
  learn as languages like Python, it is good at programming *embedded
  systems*, like the computers that run our robots.

What is Object Oriented?
----------------------------------------------------------------------

  Once upon a time, we wrote all our programming code ourselves.
  Now, we write larger programs that use *libraries* and *modules* and
  *frameworks* and *systems* and...

  With so much code, we keep need a way to keep it all straight.

  Let's have a *thought experiment* and pretend we need to draw four
  robots...

   ![image](../public/images/01-robot-field-1-small.svg)

  What things do we need to know about each one? Let's assume our
  field requires a coordinate system, then we would need to know the
  robot's position with an `x` and `y` value.

  Notice that each robot could turn in different directions. We should
  remember that as an *angle*. Each robot may have a unique name, so
  let's store that too.

  Storing values in a computer means putting some value in a
  *variable*.  For instance, the variable `x` could represent the X
  value in a coordinate graph, or it could hold the mystery ingredient
  in our new soft drink. It is just a way to give a changeable value a
  name that our program can refer to it.

  We now have the idea of a few variables that could refer to a robot,
  so let's group them into a some *definition* of a robot as a
  collection of those variables:

   {% highlight java %}
     Robot {            // Warning, not correct Java
          name;         // This is just an illustration
          x;
          y;
          direction;
     }
   {% endhighlight %}

   In case you aren't familiar with Java, the semicolons separate
   stuff, and the curly brackets group the *fields* as a single *thing*
   (in this case, it applies them to the `Robot` definition.

   But Java doesn't like things like `name` or `x`, unless it knows
   what it is (we call *what it is* a "type"), so let's expand that a
   bit:

   {% highlight java %}
     Robot {             // Warning, still not correct Java
         String name;
         int x;
         int y;
         int direction;
     }
   {% endhighlight %}

   We place a field's type in front of its variable name.  A `String`
   is a definition for the `name` variable that means it will just be
   a bunch of letters and other symbols *strung* together.

   The other types are `int` which stands for *integer* (or a whole
   number).

   This still isn't quite right, since we need to also tell Java what
   `Robot` is... its *definition* for what all robot's should be. In
   Java, this is called a `Class`, so here we go with some real Java:

   {% highlight java %}
     class Robot {       // Ah, this is good Java
         String name;
         int x;
         int y;
         int direction;
     }
   {% endhighlight %}

   Why didn't I just tell you the correct Java at the beginning?  I
   wanted to introduce the parts that are important to you and I
   first, like the coordinate position on a graph (`x`, `y`) and
   `direction` from what is important to Java, like `int` and
   `Class`. Forgive me.

   Now that we have *defined* what all Robots should be,
   let's represent a *specific* robot using the `new` keyword:

   {% highlight java %}
     redOne = new Robot();
   {% endhighlight %}

   We created a variable called `redOne` and said that it should be a
   `Robot`. However, Java won't like this, because it doesn't know what
   `redOne` should be at first. So here is the correct syntax:

   {% highlight java %}
     Robot redOne = new Robot();
   {% endhighlight %}

   At this point, you might be complaining about Java's *verbosity*, and you would have
   a good point. Java programmers need to be very specific, and the
   Java language doesn't make any assumptions.

   But making `redOne` doesn't actually tell us much unless we fill in
   the details by setting those fields:

   {% highlight java %}
     Robot redOne = new Robot();

     redOne.name = "My Little Red Robot";
     redOne.x = 10;
     redOne.y = 121;
     redOne.direction = 337;  // Degrees 0° points to right
   {% endhighlight %}

   I could do each of the three others in the above picture in a
   similar way.

A Move Function
----------------------------------------------------------------------

  Let's create a function to move a robot. In many languages, you
  might do something like:

  {% highlight java %}
    move (Robot someRobot, int amount) {   // Warning! Not real Java...again
        someRobot.x = cos(someRobot.direction) * amount;
        someRobot.y = sin(someRobot.direction) * amount;
    }
  {% endhighlight %}

  Ah, don't worry about the math. I'm using a little trigonometry to
  shift a robot from a specific *x*, *y* coordinate to another based on a
  particular angular direction and some amount.

  But in Java, we assume that the robot should *move itself*. In other
  words, we place the function *inside* the Robot's definition:

  {% highlight java %}
    class Robot {
        String name;
        int x;
        int y;
        int direction;

        void move (int amount) {
            x = Math.cos(direction) ** amount;
            y = Math.sin(direction) ** amount;
        }
    }
  {% endhighlight %}

  **Note:** A function *inside a class definition* is called a
  **method**, and it is special in that it knows that `x` refers to
  the variable field `x` in the current instance of the robot.

  Just ignore the `void` statement. It actually means *nothing*, but
  is required in Java. You'll learn the details later...I'm just
  trying to paint a programming picture right now.

  This ability to group variables and functions *inside* a definition
  gives us a first step to organize our code. Here `move` is
  *associated* with our robot, and we call it like:

  {% highlight java %}
    redOne.move(39);
  {% endhighlight %}

  The `move` is specific to our robot, but we could have a `move`
  function that we associate with a dancer, but that would be a
  completely different function. However, we'd probably call it
  `boogie`.

  This sort of organization and grouping is the second lesson you
  should know.

Making Specialized Robots
----------------------------------------------------------------------

  What if our robots were so unique, that its `name` wasn't sufficient
  to draw this:

   ![image](../public/images/01-robot-field-2-small.svg)

  Perhaps we could make four different classes:

  * `FourWheeledRobot`
  * `SixWheeledRobot`
  * `TankTreadRobot`
  * `RoundedRobot`

  That could work, but much of the code, like the `move` function would
  be the same! We don't want to repeat ourselves.

  What is unique is the *drawing* part, so what if each *robot could draw
  itself*?

  Let's change our `Robot` definition:

   {% highlight java %}
     Robot {
         String name;
         int x;
         int y;
         int direction;

         void move(int amount) {
             x = Math.cos(direction) ** amount;
             y = Math.sin(direction) ** amount;
         }

         void draw();   // Notice it doesn't DO anything
     }
   {% endhighlight %}

   Unlike our `move` method, the `draw` function have any code.

   Why? Well, each specific robot would have code unique for drawing
   itself and its four or six wheels, but they could all *share* the
   same fields and the `move` function.

   We then create our unique definitions:

   {% highlight java %}
     class FourWheeledRobot extends Robot {
       void draw() {
          // We place the code to draw a robot with four wheels in here!
       }
     }
   {% endhighlight %}

   And:

   {% highlight java %}
     TankTreadRobot extends Robot {
       void draw() {
          // We place the code to draw a robot with four wheels in here!
       }
     }
   {% endhighlight %}

   A *robot definition* is called a `Class`.

   A `Class` that `extends` another `Class` is a *subclass*
      (or *child* of a *parent* class)

What's up with Java?
----------------------------------------------------------------------

  Most *features* of a computer language has a trade-off with other
  features, so let me explain some aspects of Java that new
  programmers generally don't like, and hopefully, they then won't
  annoy you as much.

### What's up with the semi-colons?

  All spaces are *optional*, so these lines are the same:

   {% highlight python %}
    x = 1 + foo
    x=1+foo
  {% endhighlight %}

  The above is actually Python, which ignores *most* spaces...except at
  the beginning of a line. Java wanted spaces to be optional too, but
  the designers also wanted to be able to have multiple statements on
  a single line.

  Look at this code. Are we trying to assign a variable and call a function?

  #+BEGIN_SRC python
    x = 1 + foo   bar()
  {% endhighlight %}

  Or assign a variable the results of the function?

   {% highlight python %}
    x = 1 + foobar()
  {% endhighlight %}

  Java makes it clear by *terminating* all statements with a semi-colon:

   {% highlight python %}
    x = 1 + foo;    bar();
  {% endhighlight %}

  Typically, we do not place multiple statements on a line, but this
  is the reason why Java has so many semi-colons.

### What's up with Comments?

   Java evolved from a language where comments started
   with `***` and ended with `*** ` like:

   {% highlight c %}
   *** This is a comment ***
   {% endhighlight %}

   This meant that they could easily go on multiple lines:

   {% highlight c %}
   /**
         We can have a comment with lots of information.
       Even on multiple lines.
                  Like this. **/
   {% endhighlight %}

   No one likes to *hunt* for the first and last parts (especially since
   editors at that time didn't use multiple colors), so we lined up
   the asterisks:

   {% highlight c %}
   /**
    **     We can have a comment with lots of information.
    **   Even on multiple lines.
    **              Like this.
    **/
   {% endhighlight %}

   Other languages need a single character, and comments go to the end
   of the line:

   {% highlight java %}
     // Each comment begins with two slashes
     // No ending character needed.
   {% endhighlight %}

   Java decided they liked both, so you can use either style.

   Java allows you to *document* classes, functions and variables with
   an initial comment. Editors, like Eclipse, can then render those
   comments as instructions on how to call a function.

   Those comments start with two asterisks: `/**`

   And they must be before the class, function or variable they
   describe. **Note:** We document all our of functions as it makes
   debugging much easier.

   {% highlight java %}
     /*
      * Calculates and returns the nth factorial, where
      *      1! = 1, 2! = 2 * 1, 3! = 3 * 2 * 1, etc.
      * <b>Note:<*b> large values of <code>n<*code> may be too large to process.
      * @param n any number greater than 0
      * @return the value of n multiplied by all numbers less than n
      */
     long fact(int n) {
         if (n < 1)
             return 0;
         else if (n == 1)
             return 1;
         else
             return n ** fib(n - 1);
     }
   {% endhighlight %}

### What's up with all the Text?

   Suppose Jenny wrote this function to times a number by itself:
   {% highlight java %}
     int square(int n) {
         return n ** n;
     }
   {% endhighlight %}

   And Emma wrote this function to draw a square of a particular size
   where the mouse is located:
   {% highlight java %}
     int square(int size) {
         graphicsPage.drawRectangle(mouse.x, mouse.y, size, size);
     }
   {% endhighlight %}

   And you wrote this code:
   {% highlight java %}
     square(5);
   {% endhighlight %}

   How does Java know what to do?

   Every thing in Java has a *long name* and a *short name*.
   Java uses `import` to give a *short name* to something by its *long name*.
   For instance...

   {% highlight java %}
     package org.usfirst.frc.team2733.robot;

     import edu.wpi.first.wpilibj.IterativeRobot;
     import edu.wpi.first.wpilibj.Timer;

     class Robot extends IterativeRobot {
       ...
   {% endhighlight %}

   Here, we make a `Robot` that uses something called `IterativeRobot`

   That's its short name.

   We could have written:
   {% highlight java %}
     class Robot extends edu.wpi.first.wpilibj.IterativeRobot {
   {% endhighlight %}

   But that is too long and Java is wordy enough.

How to Learn Java...
----------------------------------------------------------------------

  You can learn Java any way you can. You can pick up a number of
  books about Java, but we recommend you follow the interactive
  lessons at [codecademy.com][3].

   ![image](../public/images/01-codecademy-logo.png)

  Just create an account to save your progress and work your way
  through the lessons.

   ![image](../public/images/01-codecademy-screen.png)

  If you get stuck, seek out the other members on the team, or simply
  Google for the question... yes, we use search engines all the time.

  [3]: https://www.codecademy.com/learn/learn-java

Getting Started on Programming
----------------------------------------------------------------------

  While the CodeCademy site is nice to learn, you really won't be able
  to learn to program in Java, without *programming in Java*. We
  recommend installing and using Eclipse on your home computer or
  laptop and trying out some of the things you learned.

  To install Eclipse, follow [these instructions][4].

  [4]: ../012-installing-eclipse

Summary
----------------------------------------------------------------------

  What have we learned this evening?

  - We will learn and program our robots in Java
  - Learning the *syntax* of a programming language is easy.
  - Learning to format your code to be readable can be hard.
  - Programming languages, like Java, make trade-offs.
  - Java organizes code in *classes* that hold both data (values) and code
  - To write your own code, we will use Eclipse
