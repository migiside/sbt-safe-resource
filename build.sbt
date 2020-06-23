lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin, GitVersioning)
  .settings(
    git.baseVersion := "0.0.1",
    sbtPlugin := true,
    sbtVersion := "1.3.12",
    name := "sbt-safe-resource",
    organization := "net.migiside",
    libraryDependencies ++= Seq(
      "org.scalatra.scalate" %% "scalate-core" % "1.9.6"
    ),
    scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint"),
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    },
    scriptedBufferLog := false,
    licenses += ("Apache-2.0", new URL("http://www.apache.org/licenses/LICENSE-2.0")),
    bintrayRepository := "sbt-plugins"
  )
