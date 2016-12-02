---
layout: post
title:  "Thank you Workday!"
date:   2016-12-01 22:19:54
categories: update
---
Today our company sponsored a *hack-a-thon* where we were to work on
anything not related to our typical work day.

I asked if I could work on a open source / public domain project that
I had thought about over the Summer of 2016, while trying to get my
proteges at the Pigmice (FRC Team #2733) ready for *autonomous mode*
by programming the robots they make.

They agreed, and **Mr. Warner** offered to help. Together we jammed
the stitches to the project and a bit of the lessons that will someday
become a *mini book* on programming Java.

We really wanted a `Robot` to begin as simply as a class with a few
fields, perhaps a constructor and a very simple methods, like:

{% highlight java %}
public class MyBot extends Robot {
	public int x;
	public int y;
	public int direction;

	public MyBot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public void step() {
		x += 2;
	}
}
{% endhighlight %}

The student could then create a simple game that combines their robot
and a field and *visually* see its behavior. This should be really
cool, so stay with us as we finish the project over the next few evenings and weekends.
