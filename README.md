[![scala version](https://img.shields.io/badge/scala-2.12.3-orange.svg)](https://www.scala-lang.org)
[![sbt version](https://img.shields.io/badge/sbt-0.13.16-green.svg)](http://www.scala-sbt.org/index.html)
[![TRAVIS_CI STATUS](https://travis-ci.org/lrf141/twitter4s.svg?branch=master)](https://travis-ci.org/lrf141/twitter4s)
# Twitter4S
Twitter4S is a Scala wrapper for the Twitter API.

## Description

Twitter4S is an unofficial Scala library for the TwitterAPI.  
With Twitter4S, you can easily integrate your Scala project with the Twitter Service.  
100% Pure Scala - works on any Scala Platform version 2.12 or later.

## Source Code
This library is always published in this repository.

## How to use

### get instance and init

```scala:GetInstance.scala
val twitter:Twitter = TwitterFactory.getInstance
twitter.initialize("consumer key", "consumer secret key",
                   "access token", "access token secret")
```

### post your tweet

```scala:postYourTweet.scala
twitter.updateStatus("Hello,World!!") //return TweetStatus
```


### get your home timeline

```scala:getHomeTimeLine.scala
twitter.getHomeTimeLine //return home timeline data as Seq[UserTimeLine]
```

### get your followers List as Seq

```scala:getFollowersList.scala
twitter.getFollowersList // return followers list as Seq[UserArray]
```

### get your friends(follow) List as Seq

```scala:getFriendsList.scala
twitter.getFriendsList // return friends list as Seq[UserArray]
```

## get user timeline as Seq
```scala:getUserTimeLine.scala
twitter.getUserTimeLine("screen_name")
```

## Install

You can integrate the latest Twitter4S build easily by using sbt.  
Add this code on your build.sbt or Build.scala.

```scala:build.sbt
resolvers += "Maven Repo on github" at "https://lrf141.github.io/Twitter4S/"

libraryDependencies ++= Seq(
     "Twitter4S" % "twitter4s_2.12" % "1.0.0"
)

```

## License

Twitter4S is released under MIT License.

```License
MIT License

Copyright (c) 2017 K.Takeuchi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

