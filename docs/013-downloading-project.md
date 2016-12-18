---
layout: subpage
title: Downloading our Source Code Project
---
Our project's source code is stored on [Github][github], and we can
download it directly into Eclipse. This process is calling *cloning a
repository*. These instructions assume you've [installed Java][011],
and [installed Eclipse][012].

 1. Find and start Eclipse you now have installed. If you've never
    started Eclipse before, you will be shown a **Welcome** page.  If
    the **Welcome** page greets you, and you see a link, *Checkout
    Projects from Git*:

    ![Screen shot](../public/images/010-getting-started/checkout-git-project.png)

    Select that link and skip to step #3. If you see the **Welcome** page,
    but *not* that link, click the **Workbench** button in the top right
    corner, and proceed to Step #2.

 2. On the menu system, choose the **File** > **Import...* menu item:

    ![Screen shot](../public/images/010-getting-started/file-import-menu.png)

    Under the **Git** section, select **Projects from Git**:

    ![Screen shot](../public/images/010-getting-started/after-menu-import.png)

 3. You should encounter a small window that allows you to **Clone URI**:

    ![Screen shot](../public/images/010-getting-started/clone-uri.png)

 4. Fill the **Source Git Repository** mini-window that appears, with
    the following. In the first field, **URI:**, enter:
    `http://github.com/howardabrams/learning-frc-robotics.git`

    ![Screen shot](../public/images/010-getting-started/cloning-details.png)

    If you have a Github account, you *can* enter it, but for our
    purposes with this project, that is optional.

 5. The rest of the mini-windows that appear can be filled in with the
    defaults, so just keep pressing **Next** until you are finished, and
    you have the project with your system.

After you've clone the repository, if you are still on the **Welcome**
screen, click the **Workbench** button, and you should be see our
cloned project: `learning-robotics`

  ![Screen shot](../public/images/010-getting-started/project-explorer.png)

----------------------------------------

Now that you have the source code project for the tutorials on this
site, you can either:

  * Start [learning Java][020],
  * [Get to know Eclipse][030],
  * Optionally, [install the FRC Robotic Library][015]

  [github]: http://github.com/howardabrams/learning-frc-robotics.git
  [011]: ../011-installing-java
  [012]: ../012-installing-eclipse
  [015]: ../015-installing-wpilib
  [020]: ../020-java-basics-1
  [030]: ../030-eclipse-overview
