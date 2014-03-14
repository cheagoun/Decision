import AssemblyKeys._

name := "bus"

organization := "com.stratio"

version := "0.0.1"

scalaVersion := "2.10.3"

seq(assemblySettings: _*)

jarName in assembly := "stratio-bus.jar"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".xml" => MergeStrategy.first
    case PathList("scala", xs @ _*)         => MergeStrategy.first
    case PathList("org","apache", xs @ _*)         => MergeStrategy.first
    case PathList("org","slf4j", xs @ _*)         => MergeStrategy.first
    case x => old(x)
  }
}

test in assembly := {}

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test" withSources() withJavadoc(),
  "org.apache.kafka" % "kafka_2.10" % "0.8.0" exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
  "com.typesafe" % "config" % "1.2.0",
  "com.netflix.curator" % "curator-framework" % "1.3.3"
)

initialCommands := "import com.stratio.bus._"

