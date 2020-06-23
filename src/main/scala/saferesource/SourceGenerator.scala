package saferesource

import org.fusesource.scalate.TemplateEngine
import sbt._

class SourceGenerator(baseSourceDirectory: File) {

  private val engine = new TemplateEngine(Seq(new File(this.getClass.getResource("").getFile)))

  def generate(info: ResourceInfo): File = {
    val filePath = info.baseResourceDirectory.toPath.relativize(info.resourceFile.toPath)
    val destFile = (baseSourceDirectory.toPath.resolve(filePath + ".scala")).toFile

    val bind = Map(
      ("namespace", if (filePath.getParent == null) "" else "package " + filePath.getParent.toString.replace('/', '.')),
      ("fileName", info.resourceFile.name),
      ("filePath", "/" + filePath.toString)
    )

    destFile.getParentFile.mkdirs()

    IO.write(destFile, engine.layout("ResourceReader.mustache", bind))
    destFile
  }
}
