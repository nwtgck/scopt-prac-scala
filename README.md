# Practice of scopt
[![Build Status](https://travis-ci.org/nwtgck/scopt-prac-scala.svg?branch=master)](https://travis-ci.org/nwtgck/scopt-prac-scala)

Practice of [scopt](https://github.com/scopt/scopt) which is a little command line options parsing library.


## How to run

### SimpleMain

```bash
sbt "runMain SimpleMain --times=2525 --fpath=./test"
```

### RestArgsMain


```bash
sbt "runMain RestArgsMain --times=2525 --fpath=./test hogehoge foo piyo"
```