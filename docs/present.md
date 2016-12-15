Part 1: Introduction
====================

Hi, I'm Paul Willey with Cloud Engineering at Workday.

And I'm Howard Abrams. While I also work as an engineer on the Cloud
Team, I also mentor a FIRST robotics team at the local high school. My
specific task has been to train the young programmers.

While they are eager to learn, programming the controllers on these
robots involve students learning Java, Eclipse and Git, and I noticed
that any online resources they found on those subjects were oriented
towards enterprise application developers, not budding young
roboticists.

So Paul and I decided for our part in this year's Hackathon to put
together a helpful, but fun environment for open source project for
students to learn Java.

Part 2: Tutorials  (Paul)
=================

One part was to put together a framework where we could easily explain
concepts and puzzles to solve, and have them show up on a web
site. Our goal is to keep the words succinct, and replace longer, more
complicated sections with short videos and presentations.

The focus is to allow the student to copy and paste specific code from
the web site directly into their IDE.

  (DEMO: Cutting the following code into `MyRobot.java`):

    int x;
    int y;
    int direction;

Here we take the idea that a Java class can be viewed as a data
structure for grouping variables, by typing or copying these variables
into a class.

Part 3: Code Visualization  (Howard)
==========================

Reading tutorials that are to the point and creating simplistic
classes can be helpful, but we really wanted a visual component to
this learning. In this class...

  (DEMO: Highlight the `step()` method in `MyRobot.java`

The student has a pretend their robot moves by changing its X coordinate.
However, the student can take their simple robot, and add it to a Game.

  (DEMO: Highlight the `MyGame.java`)

And see how it behaves in a field.

Of course, students who program real robots wouldn't be using X and Y
coordinates, I just found that it was just a useful concept when
initially learning.

As they go through this course, these pretend robots become more
virtual, so their robots *drop* the coordinate fields and move around
using function abstractions to virtual engines, like this call to
`forward` and `turnLeft`:

  (DEMO: Show `BumpBot2.java`)

Then, during the lesson on Finite State Machines, the student would
define their states and then attach virtual sensors to generate Events
to change their state.

  (DEMO: Show `StatefulRobot.java`)

Here, a visual tracker can locate a ball, and drive right towards it
until it gets right next to it.

  (DEMO: Run the `StatefulGame.java`)

Part 4: Conclusion   (Paul)
==================

We hope to continue working on this project on nights and weekends and
have a complete course ready for the students during next year's build
season.
