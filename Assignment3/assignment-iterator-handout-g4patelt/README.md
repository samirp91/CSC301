# Iterator Assignment

Learning goals:

 - Implementing non-trivial iterators
 - Common software engineering concepts:
   - Lazy-loading
   - Lambda expressions

_Tip:_ This handout contains many useful links, we suggest that you read through all of them.

## Getting Started

 1. [Fork][github-fork] this repo.

     > _Note:_ Your fork is private and is only visible to you, the TAs and the instructors.

 2. Make sure that [issues][github-issues] are [enabled](https://help.github.com/articles/disabling-issues/) in your fork.


## Your Task

Implement two different Iterators:

 1. [Stream reading iterator](#stream-reading-iterator)
 2. [Filtering iterator](#filtering-iterator)


### Stream reading iterator

In the previous assignment, we defined the `load` method in `IDataLoader` like so:

````
public List<ITweet> load(InputStream data) throws IOException;
````

This was a naive design choice.
If the `data` is large (e.g. large like Twitter), loading __all__ tweets into a list may not be feasible.
For example, because the list would be too large to fit in memory.

To deal with this issue, in this assignment, we rewrite the `load` method in  [`IDataLoader`](src/main/java/edu/toronto/csc301/IDataLoader.java) as follows:

````
public Iterator<ITweet> load(InputStream data) throws IOException;
```

This is a subtle, yet significant change - Instead of asking for a list of tweets, we ask for an iterator that will allow us to visit the tweets one at a time. You will implement such an iterator that:

 * Reads lines of text from `data` and converts them to `ITweet` objects.
 * Instead of reading the whole stream, reads one line from the stream whenever someone calls `next()`.      
   This technique is known as [lazy loading](https://en.wikipedia.org/wiki/Lazy_loading).

> _Note:_ It is OK if your iterator reads a few lines at a time (instead of a single one), as long as it is reading on-demand and not consuming the whole stream in advance.       
In fact, it is a common technique to read extra data from a stream into an in-memory buffer (aka cache).
For example, [`BufferedReader`](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html) does that.
Therefore, if you choose to use a BufferedReader, your code will do the same.


### Filtering Iterator

Our stream reading iterator visits every tweet in the input stream. But, what if you want to visit only tweets with the hash-tag `csc301`?

One way to satisfy this requirement is to compose our iterator with another (filtering) iterator:

 * A stream reading iterator that reads all the tweets (one at a time) from the input stream.
 * A filtering iterator that uses the first iterator to visit the tweets, but skips tweets that do not have the `csc301` hashtag.

> _Note:_ The filtering iterator technique described above can be written in a very generic way - All you need is an underlying iterator (not necessarily of tweets) and a filtering condition indicating whether an item should be filtered or not. 

 
## Tips 


Java8 provides some new features that can help express the filter very elegantly. See  [lamba expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) and [predicates](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html)

## Deliverables

Pull request to handout repo.

## Marking scheme

 * 100% Implementation. Auto-marked via unit-tests.

 > _Note:_ Although you are not marked for it, we still expect you to follow best practices - Keep
 track of your work using issues, push small commits regularly and provide meaningful commit messages.


[github-guides]: https://guides.github.com/ "GitHub guides"
[github-fork]: https://guides.github.com/activities/forking/ "Guide to GitHub fork"
[github-issues]: https://guides.github.com/features/issues/  "Guide to GitHub issues"
