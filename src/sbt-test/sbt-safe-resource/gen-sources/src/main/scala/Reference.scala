object Reference {
  def main(args: Array[String]): Unit = {
    assert(
      scala.io.Source.fromInputStream(net.migiside.`namespace.txt`.getResourceAsStream()).mkString("") ==
        "namespace content"
    )
    assert(
      scala.io.Source.fromInputStream(net.migiside.`same-namespace.txt`.getResourceAsStream()).mkString("") ==
        "same-namespace content"
    )
    assert(
      scala.io.Source.fromInputStream(`no-namespace.txt`.getResourceAsStream()).mkString("") ==
        "no-namespace content"
    )
  }
}
