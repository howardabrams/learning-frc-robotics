---
layout: page
title: Introduction to Java
---
As we [mentioned before][000], programming a FIRST Robot means
programming its behavior and *smarts* in [Java][java], a programming
language popular in big companies, but not usually taught as an
introduction to computers. Let's change that!

Here we'll learn the basics by creating some *pretend robots*.

Why we Teach Java
------------------------------------------------------------

  In the 1990s, Java was originally designed for interactive
  television systems (like older versions of an AppleTV), however, it
  expanded its expertise with the Internet. While it may not be as
  simple to learn as languages like Python, it is good at programming
  *embedded systems*, like the computers that run our robots.

What is Object Oriented?
----------------------------------------------------------------------

Once upon a time, we wrote all our programming code ourselves.  Now,
we write larger programs that use *libraries* and *modules* and
*frameworks* and *systems* and...
With so much code, we need a way to organize it.

Let's have a *thought experiment* and pretend we need to draw four
robots...

   ![image](../public/images/01-robot-field-1-small.svg)

What things do we need to know about each one to draw this?

Let's assume our graphics field use coordinates to place things, then
we would need to know the robot's position with an `x` and `y` value.

Notice that each robot could turn in different directions. We should
remember that as an *angle*. Each robot may have a unique name, so
let's store that too.

  * X coordinate
  * Y coordinate
  * Directional angle
  * Unique name

Storing values in a computer means putting some value in a *variable*.
For instance, the variable `x` could represent the X value in a
coordinate graph, or it could hold the mystery ingredient in our new
soft drink. It is just a way to give a changeable value a name that
our program can use.

Let's use the following variables to store the above four aspects:

  * `x`
  * `y`
  * `direction`
  * `name`

But we have more than one robot. We *could* do something like:

  * `robot1x`
  * `robot1y`
  * `robot1direction`
  * `robot1name`
  * `robot2x`
  * `robot2y`
  * `robot2direction`
  * `robot2name`
  * `robot3x`
  * `robot3y`
  * `robot3direction`
  * `robot3name`

Ugh. That would be awful, and hard to change if we wanted a field with
six or twenty robots.

Since each four variables, refer to a single robot, we could group
them. We could make a *definition* of a robot as a collection of these
variables:

{% highlight java %}

    Robot {    // Warning, not correct Java
  name     // This is just an illustration
  x
  y
  direction
}

{% endhighlight %}

While the above code *is not Java*, notice we we *group* the variables
between curly braces and give it a name, `Robot`.

**We could then create a `robot1` and `robot2` from this definition,
and each would get all four variables.** This is the first key to
understanding Java and other languages that are called *object
oriented*.

Java insists that variables, like `name` or `x`, specify *what it can
contain*, like a number or letter. What a variable can contain is its
**type**.  Let's expand our definition a bit:

{% highlight java %}

    Robot {             // Warning, still not correct Java
  String name
  int x
  int y
  int direction
}

{% endhighlight %}

We place a field's type in front of its variable name.  A `String`
is a definition for the `name` variable that means it will just be
a bunch of letters and other symbols *strung* together.

The other types are `int` which stands for *integer* (or a whole
number).

This still isn't quite right, since we need to also tell Java what
`Robot` is... that it is a *definition* for what all robot's should be. In
Java, this is called a `Class`, so here we go with some real Java:

{% highlight java %}

    class Robot {       // Ah, this is good Java
  String name;
  int x;
  int y;
  int direction;
}

{% endhighlight %}

The semicolons separate stuff. Why we we need those? Let me answer
that one later.

Why didn't I just tell you the correct Java at the beginning?  I
wanted to introduce the parts that are important to you and I first,
like the coordinate position on a graph (`x`, `y`) and `direction`
from what is important to Java, like `int` and `Class`.

Now that we have *defined* what all Robots should be,
let's represent a *specific* robot using the `new` keyword:

{% highlight java %}

    redOne = new Robot();

{% endhighlight %}

We created a variable called `redOne` and said that it should be a
`Robot`. However, Java won't like this, because it doesn't know what
the `redOne` variable should contain. So here is the correct syntax:

{% highlight java %}

    Robot redOne = new Robot();

{% endhighlight %}

At this point, you might be complaining about Java's *verbosity*, for
instance, why specify what `redOne` will contain when we also put a
`Robot` in that variable. Couldn't it have figured it out?  A good
point. Java programs need to be very specific, and the Java language
doesn't make any assumptions. As a Java programmer, you will need to
be very specific.

But creating `redOne` doesn't actually tell us much unless we fill in
the details by setting those variables (we'll call them *fields* from
now on):

{% highlight java %}

    Robot redOne = new Robot();

    redOne.name = "My Little Red Robot";
    redOne.x = 10;
    redOne.y = 121;
    redOne.direction = 337;  // Degrees 0° points to right

{% endhighlight %}

I could do each of the three others in the above picture in a
similar way.

Using Eclipse
----------------------------------------------------------------------

I think you've been reading enough. Let's try it out. Now that you
have [installed Eclipse][012] and [downloaded our project][013], start
Eclipse, and right-click the `learning-robotics` project (or hold down
the `Control` key and click that project if you're using a Mac):

  ![Making a new Class](../public/images/020-java-basics/new-class.png)

Eclipse will bring up a panel for describing your new class. For now,
let's just give it a name of `MyRobot`:

  ![Making a new Class](../public/images/020-java-basics/new-class-dialog.png)

You should have a window to your code that reads:

{% highlight java %}

    public class MyRobot {

}

{% endhighlight %}

I don't want to get you bogged into the details, but `public` is an
extra definition that tells Java that other class definitions can see
it.

Fill our new class definition with the variable fields from above:

{% highlight java %}

    public class MyRobot {
  int x;
  int y;
  int direction;
  String name;
}

{% endhighlight %}

Any red marks? If so, use your mouse to hover over it, and Eclipse
will tell you what is wrong, and may even give you the correct answer
on how to fix the problem.

Viewing our Robot
----------------------------------------------------------------------

To view our Robot, we need to play a game. In the panel labeled,
**Package Explorer**, you should see a file, `MyGame.java`:

  ![Screen shot](../public/images/020-java-basics/mygame-java.png)

Double-click that to open it, and notice the comments. Comments begin
with two slashes, are are just notes that you leave for others or to
yourself. In this case, I left it as a note to explain what is going
on and to tell you where to *add your robot*:

{% highlight java %}

    public static void main(String[] args) {
  // The field takes a string that is used for the Window title.
  Field field = new ArenaField("My Fun Game");

  // REPLACE THIS LINE TO ADD YOUR ROBOT HERE!

  field.runGame();
}

{% endhighlight %}

Let's use our robot definition (a Class) to make a robot, so let's
replace that comment with some code:

{% highlight java %}

    MyRobot fred = new MyRobot();

fred.name = "Fred, My Little Robot";
fred.x = 101;
fred.y = 142;
fred.direction = 337;

{% endhighlight %}

While we have *made* the robot, our last step is to *place* him on the field with this little magic spell:

{% highlight java %}

    field.addRobot(fred);

{% endhighlight %}

At this point, don't worry about what you *don't understand*, as I'll
explain that in the next section, but instead, let's see this work.
Right-click in the editing panel of `MyGame` where we just entered
this code, and move the mouse to the **Run** menu to select **Java
Application**:

  ![Screen shot](../public/images/020-java-basics/run-menu.png)

If all goes well, and you don't have any errors appear, you should see
a popup window with a green screen and a little car-looking robot
appear on it. Congratulations, this is your first virtual robot.

Summary
----------------------------------------------------------------------

What have we learned so far? We've used Eclipse to create a Java
class, which is a definition for what information things like robots
should have. In our case, we made a `MyRobot` definition that held the
X,Y coordinates and a direction for where our robot should point, and
we added it to `MyGame` so that it could be seen.

We've also learned a little about Java's syntax, like how every
variable should have a *type*, like `int` to hold a number, and
`String` to hold some words.

You can close this window when you are done looking at it. We should
make it move, right? Let's go to the [next chapter][021] to learn
about methods.

  [000]: ../000-introduction
  [012]: ../012-installing-eclipse
  [013]: ../013-downloading-project
  [021]: ../021-java-basics-methods
  [java]: https://go.java/index.html?intcmp=gojava-banner-java-com
