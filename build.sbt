
name := "scalajs-location-mapper"

version := "1.0"

scalaVersion := "2.11.5"

lazy val root = project.in(file(".")).
  enablePlugins(ScalaJSPlugin)

//scala
//val scalajsReactVersion = "0.8.0"
val scalajsFacadesVersion = "0.0.1-SNAPSHOT"
//val scalajsReactComponentsVersion = "0.0.1-SNAPSHOT"
val uPickleVersion = "0.2.6"

//js
//val reactVersion = "0.12.1"
val pouchDBVersion = "3.3.0"
val leaftletVersion = "0.7.3"

// scalajs deps
libraryDependencies ++= Seq(
//  "com.chandu0101.scalajs-react-components" %%% "core" % scalajsReactComponentsVersion,
  "chandu0101.scalajs-facades" %%% "core" % scalajsFacadesVersion
//  "com.github.japgolly.scalajs-react" %%% "extra" % scalajsReactVersion
)

resolvers += "bintray/non" at "http://dl.bintray.com/non/maven"

// js deps
jsDependencies ++=  Seq(
//  "org.webjars" % "react" % reactVersion / "react-with-addons.js" commonJSName "React",
  "org.webjars" % "leaflet" % leaftletVersion / "leaflet.js",
  "org.webjars" % "pouchdb" % pouchDBVersion / "pouchdb.min.js" )

// creates single jsdeps resource file for easy integration in html page
skip in packageJSDependencies := false

// uTest settings
//utest.jsrunner.Plugin.utestJsSettings


persistLauncher := true

persistLauncher in Test := false


// copy  javascript files to js folder,that are generated using fastOptJS/fullOptJS

crossTarget in (Compile, fullOptJS) := file("js")

crossTarget in (Compile, fastOptJS) := file("js")

crossTarget in (Compile, packageJSDependencies) := file("js")


//crossTarget in (Compile, packageScalaJSLauncher) := file("js")

artifactPath in (Compile, fastOptJS) := ((crossTarget in (Compile, fastOptJS)).value /
  ((moduleName in fastOptJS).value + "-opt.js"))

scalacOptions += "-feature"



