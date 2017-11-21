case class MyOptions(times: Int=1, fpath: String="default-string")

object SimpleMain {
  def main(args: Array[String]): Unit = {

    val parser = new scopt.OptionParser[MyOptions]("my first scopt program") {
      opt[Int]('t', "times") action {(times, myOptions) =>
        myOptions.copy(times=times)
      } text ("this is description of times")

      opt[String]('p', "fpath") action {(fpath, myOptions) =>
        myOptions.copy(fpath=fpath)
      } text ("this is description of fpath")
    }

    val res = parser.parse(args, MyOptions())
    println(res)
  }
}
