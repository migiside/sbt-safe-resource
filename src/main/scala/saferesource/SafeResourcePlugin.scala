package saferesource

import sbt.Keys._
import sbt._
import sbt.io.RegularFileFilter

object SafeResourcePlugin extends AutoPlugin {

  object autoImport {
    val safeResource = taskKey[Seq[File]]("safe your resource reference")
  }

  import autoImport._

  override def trigger = allRequirements
  override def requires = plugins.JvmPlugin

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      safeResource := safeResourceTask(Compile).value ++ safeResourceTask(Test).value,
      Compile / sourceGenerators += safeResourceTask(Compile).taskValue,
      Test / sourceGenerators += safeResourceTask(Test).taskValue
    )

  lazy val safeResourceTask = (conf: Configuration) =>
    Def.task {
      val sourceDir = (conf / sourceManaged).value
      val sourceGenerator = new SourceGenerator(sourceDir)

      (conf / resourceDirectories).value
        .flatMap(f => f.globRecursive(RegularFileFilter).get().map(ResourceInfo(f, _)))
        .map(info => sourceGenerator.generate(info))
    }
}
