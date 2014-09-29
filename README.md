JavaScript Engine under Java
======================
This project is help to figure out the performance above JSR223 between Chrom V8 javascript engine to Mozilla Rhino.

## Introduction
The use of scripting languages to add new functionality to systems is something that very helpful. Especially by integrating Javascript, You can refer integrate you core conceptual model cross programming language like Java and JavaScript. 
Some typical use case:
JSON validator
Workflow Script Engine
Simple script on a simple function without compiling the system


## JavaScript Engine in Java world
Two engine are popular, Chrom V8 javascript engine, and Mozilla Rhino.
Mozilla Rhino as the default Jdk binded JavaScript engine, it is implemented by pure java. And Google Chrome V8 Javascript engine is Google's  blazing fast javascript engine.

 
## Performance comparision:
The performance difference is outstanding. Particularly interesting is the scalability of V8 when can handle more more iterations. V8 has an advanced cache system an surely that is helping to keep the performance as the iteration grows.

Test case: http://www.docjar.com/html/api/org/apache/solr/handler/dataimport/ScriptTransformer.java.html
The test consisted in encrypting 10000 records of text from a database, and measured 10 times out of 15 rounds under 1 thread sequentially.
And from the following results show that V8 wins by a big margin.

### The results: 
Engine	Time taken (seconds)
V8	    2.96
Rhino	36.67

### The results in bentchmarks:
JsPerfTest.runRhinoJs: [measured 10 out of 15 rounds, threads: 1 (sequential)]
 round: 2.15 [+- 0.23], round.block: 0.00 [+- 0.00], round.gc: 0.00 [+- 0.00], GC.calls: 10, GC.time: 0.03, time.total: 36.67, time.warmup: 15.16, time.bench: 21.51
JsPerfTest.runV8Js: [measured 10 out of 15 rounds, threads: 1 (sequential)]
 round: 0.20 [+- 0.08], round.block: 0.00 [+- 0.00], round.gc: 0.00 [+- 0.00], GC.calls: 3, GC.time: 0.08, time.total: 2.96, time.warmup: 0.98, time.bench: 1.98


