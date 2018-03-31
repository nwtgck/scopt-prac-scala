

sealed trait SubConfig
case object NoSubConfig extends SubConfig
case class SubConfig1(times: Int=1, fpath: String="default-string") extends SubConfig
case class SubConfig2(name: String="Jack", rest: List[String]=Nil) extends SubConfig

case class MyConfig(subConfig: SubConfig = NoSubConfig)


object SubcommandMain {

  def main(args: Array[String]): Unit = {

    val parser = new scopt.OptionParser[MyConfig]("") {

      // (from: https://github.com/scopt/scopt/issues/29)
      cmd("sub1") action {(_, c) => c.copy(subConfig = SubConfig1())} children {
        type Sub = SubConfig1
        opt[Int]('t', "times") action {case (times, c@MyConfig(subConfig: Sub)) =>
          c.copy(subConfig=subConfig.copy(times=times))
        } text ("this is description of times")

        opt[String]('p', "fpath") action {case (fpath, c@MyConfig(subConfig: Sub)) =>
          c.copy(subConfig=subConfig.copy(fpath=fpath))
        } text ("this is description of fpath")
      }

      cmd("sub2") action {(_, c) => c.copy(subConfig = SubConfig2())} children {
        type Sub = SubConfig2
        opt[String]('n', "name") action {case (name, c@MyConfig(subConfig: Sub)) =>
          c.copy(subConfig=subConfig.copy(name=name))
        } text ("this is description of name")

        arg[String]("<rest>...").unbounded().optional().action{case (x, c@MyConfig(subConfig: Sub)) =>
          c.copy(subConfig=subConfig.copy(rest=subConfig.rest :+ x))
        }
      }

    }

    val res = parser.parse(args, MyConfig())
    println(res)


    // Pattern Match

    res match {
      case Some(myConfig) =>

        myConfig.subConfig match {
          case NoSubConfig =>
            println("Unexpected error! (NoSubConfig)")
          case SubConfig1(times, fpath) =>
            println(s"sub1: times: ${times}, fpath: ${fpath}")
          case SubConfig2(name, rest) =>
            println(s"sub2: name: ${name}, rest: ${rest}")
        }

      case None =>
        println("None!")
    }

  }
}
