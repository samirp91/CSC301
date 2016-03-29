## Learning goals

 - Persistence & Serialization, basic concepts
 - Handling a few common serialization challenges


## Getting Started

 1. [Fork][github-fork] this repo.

     > _Note:_ Your fork is private and is only visible to you, the TAs and the instructors.

 2. Make sure that [issues][github-issues] are [enabled](https://help.github.com/articles/disabling-issues/) in your fork.

## Your Task


Implement the code to pass the provided unit tests.        
See the marking scheme below for more details.

## Deliverables

Your code, submitted as a single, non-conflicting [pull-request][github-pull-requests] from your fork to the handout repo (i.e. the repo you forked).

## Marking Scheme

Your code will be marked automatically, according to the following scheme:

 * 75%, [Basic tests](src/test/java/edu/toronto/csc301/test/BasicTest.java)
   * To get the full mark (out of 75) you must pass all of the basic tests.
   * If you fail at most 5 test methods, you will earn a mark of 50 (out of 75).
   * If you fail more than 5 test methods, or if your code doesn't compile, you will not earn a mark for this part.
 * 15%, [Advanced tests](src/test/java/edu/toronto/csc301/test/AdvancedTest.java)
   * To get the mark, you must pass all of the tests. No partial marks.
 * 10%, Tests not included with this handout
   * We will run the basic tests with **your `ISerializer`** implmenetation and **our `IUser`, `IPost` and `IUserStore`** implementations.
   * The point is to verify that your code can serialize/deserialize any `IUser`, `IPost` and `IUserStore` implementation (not just yours).
   * To get the mark, you must pass all of the tests. No partial marks.


### Important Note About Travis

Travis will "give you a green light" only if your code passes all of the basic **and** advanced tests.      
That is, Travis will not provide any indication of partial marks.

That being said, you should still check the details of the build, and make sure that the results on Travis are the same as on your local machine.

In other words, it is **your responsibility** to make sure your code compiles!



[github-issues]: https://guides.github.com/features/issues/
[github-guides]: https://guides.github.com/ "GitHub guides"
[github-fork]: https://guides.github.com/activities/forking/ "Guide to GitHub fork"
[github-pull-requests]: https://help.github.com/articles/using-pull-requests/ "Guide to GitHub Pull-Requests"
