# g-plus-crawler-picture

The goal of this project is to retrieve a large amount of pictures from G Plus.

In order to increase performance and efficiency, I implemented a multi-threaded parser in Java, moreover, the network bandwidth has an important role too. Parsing speed is governed not only by the speed of ones own internet connection, but also by the speed of the sites that are to be parsed. Especially if one is parsing sites from multiple servers, the total parsing time can be significantly reduced, if many downloads are done in parallel. Google+ splits pictures in different servers, you can see below in the report that the url for downloading picture starts with lh3, lh4, lh5 ...

<h3>What does happen if the connection goes down?</h3>
We need to restart the program, and because the users IDs file exists, we do not need to process the first step, we go directly to the second step which substract the user IDs file with the user IDs log file. After subtraction, we get the list of users who have not been parsed, and we continue. Note that if the remaining of the old user pictures were not downloaded, they are lost.

<h3>Critical resources</h3>
Running more than one thread inside the same application does not by itself cause problems. The problems arise when multiple threads access the same resources. For instance the same memory (variables, arrays, or objects), systems (databases, web services etc.) or files. In fact, problems only arise if one or more of the threads write to these resources. It is safe to let multiple threads read the same resources, as long as the resources do not change.

In this case, we have 3 critical resources that are: log users, log errors and database. To resolve this issue :
We need to access the file through a class that contains a synchronized method to write to the file. Only one thread at a time will be able to execute the method.
I think that Singleton pattern would fit for our problem.

<h3>Unit test</h3>
A unit test is a piece of code written by a developer that executes a specific functionality in the code which is tested.
A unit test targets a small unit of code, e.g. a method or a class, (local tests).
Unit tests ensure that code works as intended. They are also very helpful to ensure that the code still works as intended in case I need to modify code for fixing a bug or extending functionality. Having a high test coverage of my code allows me to continue developing features without having to perform lots of manual tests.
For this program, I implemented an unit test class which checks :
- the substract between two files containing user ids and logs (in UNIT_TEST/) - NameTools class
- IOTools class

<h3>Configs</h3>
You need to use your own google api key, and edit the config file.
