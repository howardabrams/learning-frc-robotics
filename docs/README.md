Table of Contents
==================

Each `page` will need to specify in the initial meta data section,
along with the title, .e.g:

    ---
    layout: page
    title: Getting Started
    ---

However, we also need to specify the Java Source code that the student
will need to write. Should we then delete the *answer* from
`org.geeklet.machines`, or should we leave it there, and have the
student write their version in the top-level `src` directory without a
`package`?

Home
----

The `Home` page currently displays the top 50 blog entries, which we
could use to our advantage for either *News* or simply to have our
*Welcome to the Site* be a blog entry.

Introduction
------------

This *single page* should expand on the **Welcome** page with the
following:

  * Explain the background to the project
  * How to Learn this material
  * Link to important parts of the site
  * List what they should learn

Getting Started
---------------

Goal is to have them get an *environment* running on their personal
computer, including:

 * Installing Java (if needs be)
 * Installing Eclipse
 * Cloning our project (from Eclipse)
 * Eclipse Overview (just the basics)

Eventually the student will need to work on the *real robotics*
library from FIRST (called `WPILib`), however, what we have built is
really called "Part 1" (where Part 2 would be learning Arduino
programming for collecting sensor data, Part 3 would be doing vision
tracking with a Raspberry Pi, and Part 4 would be programming a
RoboRIO controller with Java).

In other words, let's not confuse the issue by bothering the student
with installing WPI Lib at this point.

Basic Concepts
--------------

Let's come up with the basics of learning Java, including these pages:

### Datatypes and OOP

While we need to teach `int` and `String`s, we really can *do
anything* without a `Class`, so perhaps we briefly talk about the
basic primitives, and state how these are similar to any other
programming language, but then show that we need to group them into a
`class` structure.

**Files:**

  * `MyBot.java` :: Just the basic part only ... it shouldn't *run*

### More OOP

Since the `MyBot.java` can't run without adding `extends Robot` to
this class, perhaps we give the basics of *class inheritence* at this
point? We really would drive home the point of the `new` when we have
them build the `MyGame.java` code, and finally they should be able to
run something.

**Files:**

  * `MyBot.java`
  * `MyGame.java`

### More Eclipse

Since the **Run** button in Eclipse starts our `MyGame` (but can't be
used to *run* the `MyBot`), we may have to explain more about Eclipse
at this point. And should them how to stop the program (by either
clicking the Window's close button or the X icon in Eclipse. Also, we
may have to explain the `Console`, so perhaps we also teach
`System.out.println()`.

Does this mean, we fully attempt to explain the windows, views,
perspectives, etc. in Eclipse, now?

### Math Fun

In order to explain our Robot more fully, we need to describe the
`move()` and `turn()` functions, and this requires a bit of
math.

**Note** An issue that I just realized is that we should not have a
`turn(angle)` function, but only a `turnLeft()` and `turnRight()` (as
well as having `forward()` and `backward()` which do not accept
parameters), as this will behave more closely to have a Robot should
*be* for the `step()` function!

The math will be the same, but now we are moving by such small numbers
that the rounding errors will kill us. This might be a good excuse to
convert the `int`-based coordinates for `x` and `y` to `float` or
`double` (see `MyBot2.java`)

### Unit Testing

The unit testing needs a function that *looks right*, but is actually
wrong. For this, I originally wrote the `turn` function that used
degrees, but calculated stuff in radians.


Advanced Programing (for the win)
-------------------

The point of the pages beforehand is to get to the really good stuff.

### Git

I figured that we could finally talk about Git and making commits and
how that can be use to share code with the other teammates through
Github. I guess this could also be somewhat optional, since during the
Getting Started guide, we had them `clone`, but that was through
Eclipse, and maybe not very Git-specific.

### Sensing the World

We should have a page that explains how to sense the field through
*virtual sensors*, like the `UltraSonic` sensor.

**Files:**

  * `BumpBot.java`
  * `BumpGame.java`

### Finite state machines I

We should have a Robot that turns *for a while* (maybe a random
while), and then goes straight. Since the methods we should teach are
`forward`, `turnLeft` and `turnRight` (see the difference between
`BumpBot.java` and `BumpBot2.java`), they really can't do any
long-term planning (like turn 50 degrees) without having a simple
state that changes between *turning* and *moving*.

### Finite state machines II

The big article about FSM should use a `VisualTracker` sensor to
locate balls.

**Files:**

  * `BallBot.java`
  * `BallGame.java`

Would Be Nice
-------------

 * Refactoring
 * TBD
