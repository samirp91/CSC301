## Learning goals

 - Java, JUnit and Test Driven Development.
 - Basic Git operations.
 - Collaborating using [GitHub][github-guides].

_Tip:_ This handout contains many useful links, we suggest that you read through all of them.

## Getting Started

 1. [Fork][github-fork] this repo.

     > _Note:_ Your forks are private and are only visible to the two of you, the TAs and the instructors.

 2. Make sure that [issues][github-issues] are [enabled](https://help.github.com/articles/disabling-issues/) in your fork.

Once _both_ teammates complete the steps above, you are good to go.

## Important !

Do not add any collaborators or teams to your fork (not even your teammate)!

Since you are the owner of your fork repo, GitHub allows you to share it with others. Note that GitHub also allows us (the instructors and TA’s) to see if share your fork with anyone.

If you share your fork with anyone, you will automatically get a 0 mark for this assignment.



## Your Task

__[Work together](workflow.md)__ and write code to pass all the unit-tests.

#### Guidance

One of our (the instructors) goals for this assignment is to get you (the students) comfortable reading code.     
Therefore, the requirements for this assignment are specified almost entirely in code, using unit tests.

We encourage you to jump into the code, help each other understand it and use the discussion board to ask questions.

We suggest that you start by running the tests in [`ImplementationStructureTest`](src/test/java/edu/toronto/csc301/test/ImplementationStructureTest.java) (Right click -> Run As -> JUnit Test):

![Screenshot](http://csc301-fall-2015.github.io/static-resources/img/eclipse_run_junit_screenshot.png)

These tests specify which classes you need to create, which interfaces they should implement and which constructor(s) each class must have. Once you pass these tests, you can fill in the rest of the implementation based on the other test classes.

_Note:_ This assignment is slightly different than a real _test driven development_ environment.
In most realistic scenarios, you will not start with a full set of test cases. Instead, you will write a test (or a few tests), then write the code to pass the test(s) and repeat the process.


## Deliverables

 1. Your code
   * Submitted as a single, non-conflicting [pull-request][github-pull-requests] from one of your forks to the handout repo (i.e. the repo you forked).
   * It doesn't matter which fork the pull-request comes from - Since you are collaborating, both of your forks should be in sync anyway.

 2. Written report
    * Submitted with your code (i.e. as part of the pull-request), by editing the file [`report.md`](report.md).


## Marking scheme

 * 60% : Auto-marker running the provided unit-tests against your code.
 * 22% : [report.md](report.md)
 * 18% : Individual participation       
    * Your TA will evaluate your individual participation based on the commit history, issues and pull-requests in your repos.

__Important:__ This assignment is about collaboration.    
If you let your teammate do all of the work (or, if you do all the work yourself, without giving your teammate a chance to contribute), you will get a mark of 0.



[github-issues]: https://guides.github.com/features/issues/
[github-guides]: https://guides.github.com/ "GitHub guides"
[github-fork]: https://guides.github.com/activities/forking/ "Guide to GitHub fork"
[github-pull-requests]: https://help.github.com/articles/using-pull-requests/ "Guide to GitHub Pull-Requests"
