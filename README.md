Java Concurrency Example
=======================

A contrived example of simple concurrency using cached thread pool executor service.
On each request, a thread pool is created,  `"Hello from Java!"` is broken into its
component characters and submitted to the thread pool to be printed. Each time the
page loads, the characters will be printed in a different order depending on the
scheduling of the threads. Here is the example output:

    Thread[pool-29-thread-1,5,main]:	H
    Thread[pool-29-thread-1,5,main]:	l
    Thread[pool-29-thread-2,5,main]:	e
    Thread[pool-29-thread-2,5,main]:	o
    Thread[pool-29-thread-1,5,main]:
    Thread[pool-29-thread-3,5,main]:	l
    Thread[pool-29-thread-1,5,main]:	o
    Thread[pool-29-thread-4,5,main]:	f
    Thread[pool-29-thread-3,5,main]:	r
    Thread[pool-29-thread-2,5,main]:	m
    Thread[pool-29-thread-1,5,main]:	J
    Thread[pool-29-thread-6,5,main]:	v
    Thread[pool-29-thread-4,5,main]:	a
    Thread[pool-29-thread-5,5,main]:
    Thread[pool-29-thread-3,5,main]:	a
    Thread[pool-29-thread-7,5,main]:	!

Of course you would probably want to reuse the thead pool in practice, but this is creating
and shutting it down here for simplicity.
