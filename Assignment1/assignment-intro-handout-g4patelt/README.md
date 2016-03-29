## Introduction Assignment

The goal of this assignment is to get you used to the tools (specifically, Git) and workflow 
we will use for the rest of the term.

As you will notice, this handout is faily succinct.
If you have any questions, please post them on the discussion board.


## Getting Started

 1. [Fork][github-fork] this repo.

     > _Note:_ Your fork is private and is only visible to you, the TAs and the instructors.

 2. Create a [local clone](https://help.github.com/articles/fork-a-repo/#step-2-create-a-local-clone-of-your-fork) of your fork.

 3. [Import](https://www.google.com/search?q=eclipse+import+existing+project&oq=eclipse+import+existing+project) the code into Eclipse.
 
 4. Run the unit tests.          
    At this point, most of the tests should be failing.


## Your Task

Write code to pass all the unit-tests.


## Deliverables

Your solution should be submitted as a single, non-conflicting [pull-request][github-pull-requests].

Just to be clear ...

 * There are 3 repos involved:
   1. The assignment handout - Read-only, on GitHub.
   2. Your fork (of the assignment handout) - Read/write, on GitHub.
   3. Local clone of your fork - On your machine.
 * While working you:
   1. Commit changes to your local clone.
   2. Push changes from your local clone to your fork.
 * In order to submit your work, you will submit a pull-request from your fork to the assignment handout.
  
The following diagram might make the workflow a little clearer:

<br /><br />
![Assignment workflow](http://csc301-fall-2015.github.io/static-resources/img/CSC301-assignment-workflow.png)
<br /><br />

After the deadline, our auto-marker will merge your pull-request (assuming there are no conflicts).
Any commits that were made after the deadline will be ignored.


## Marking scheme

This assignment will be fully marked by an auto-marker, running the provided unit tests.

In other words, if your submitted code passes all the unit tests, you get the full mark.

[github-issues]: https://guides.github.com/features/issues/
[github-guides]: https://guides.github.com/ "GitHub guides"
[github-fork]: https://guides.github.com/activities/forking/ "Guide to GitHub fork"
[github-pull-requests]: https://help.github.com/articles/using-pull-requests/ "Guide to GitHub Pull-Requests"
