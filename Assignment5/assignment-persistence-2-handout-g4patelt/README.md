## Learning goals

 - Persistence & Serialization, advanced concepts
   - Serialize objects, without relying entirely on `java.io.serializable` and `java.io.ObjectOutputStream`
   - Serialize a network of objects.


## Getting Started

 1. [Fork][github-fork] this repo.

     > _Note:_ Your fork is private and is only visible to you, the TAs and the instructors.

 2. Make sure that [issues][github-issues] are [enabled](https://help.github.com/articles/disabling-issues/) in your fork.

## Your Task


Implement the code to pass the provided unit tests.        
See the marking scheme below for more details.

**Important:** Do not modify any of the given interfaces in any way (ex: do not extend Serializer).       
The interfaces are the "contract" between our testing code and your solution. If you change them, you might get unexpected results.

## Guidance

Before you start coding, it is important that you understand the challenges and make a plan.        


#### Challenge 1

Your serializer is expected to handle any implementation of `IUser` and `IPost`.  
Since the [`IPost`](src/main/java/edu/toronto/csc301/IPost.java) interface declares the following methods:
 
```java
public RenderedImage getImage();
public void setImage(RenderedImage profilePic);
```

It is reasonable to expect your serializer to handle `IPost` implementations that have a [`RenderedImage`](http://docs.oracle.com/javase/8/docs/api/java/awt/image/RenderedImage.html) field.       

Unfortunately, Java's built-in `ObjectOutputStream` cannot serialize objects of class `RenderedImage`,
so `ObjectOutputStream` is not going to work (at least not "out of the box") for this assignment.

**Q:** _How should you handle this challenge?_

We would like you to serialize objects to JSON or XML (or even your own custom format).

That being said, you might find a solution that, with some extra work, will allow you to use Java's `ObjectOutputStream` (we haven't tried to implement such a solution oursleves). Just make sure your serializer can handle _any_ implementation of `IUser` and `IPost`.


**Q:** _How can you serialize `RenderedImage` instances?_

For the purpose of this assignment, feel free to use [this utility class](src/main/java/edu/toronto/csc301/Util.java).       
It has simple helper functions that convert `RenderedImage` to/from `byte[]` (i.e. data that can be written somewhere).      

#### Challenge 2 - Serializing an object graph

When you serialize a user, you must also serialize its full object graph.         
That is, the user, all of his/her posts (as well as posts that they like), all other users
who posted or liked these posts, all of their posts, and so on.

Before you start coding, make sure you have a clear idea of what a serialized object graph will look like, and how you would deserialize back into in-memory objects.

You might find it useful to break this task into two:
 1. Given a `IUser`, visit every object in its object graph.          
    (think of _CSC263_ and graph algorithms like _breadth/depth first search_)
 2. Representing an object graph (i.e. users, posts and the relations between them) as a chunk of data. 
 
_Note:_ You can assume that users in the same object graph have unique usernames 
(it is enforced by the user store, which is used for creating users).

_Hint:_ That is to say that the username can be used to uniquely identify a user.
When reassembling a network of objects (during deserialization) unique persistent object identifiers are often useful.

## Deliverables

Your code, submitted as a single, non-conflicting [pull-request][github-pull-requests] from your fork to the handout repo (i.e. the repo you forked).

## Marking Scheme

Your code will be marked automatically, according to the following scheme:

 * 50%, [Basic tests](src/test/java/edu/toronto/csc301/test/basic)
   * To get the mark, you must pass all of the tests. No partial marks.
 * 50%, [Advanced tests](src/test/java/edu/toronto/csc301/test/ObjectGraphTest.java)
   * For a full mark (50 out of 50), you must pass all of the tests.
   * For a partial mark (25 out of 50), you must pass the [Prerequisite](src/test/java/edu/toronto/csc301/test/objectGraphs/Prerequisite.java) test and fail no more than 10 tests.

### Important Note About Travis

Travis will "give you a green light" only if your code passes all of the basic **and** advanced tests.      
That is, Travis will not provide any indication of partial marks.

That being said, you should still check the details of the build, and make sure that the results on Travis are the same as on your local machine.        

 > You might need to sync Travis, before you can view the details of the build.       
 > For more details, see [post on Piazza](https://piazza.com/class/iebeul82n2q37z?cid=144).

In other words, it is **your responsibility** to make sure your code compiles!



[github-issues]: https://guides.github.com/features/issues/
[github-guides]: https://guides.github.com/ "GitHub guides"
[github-fork]: https://guides.github.com/activities/forking/ "Guide to GitHub fork"
[github-pull-requests]: https://help.github.com/articles/using-pull-requests/ "Guide to GitHub Pull-Requests"
