resolvers += Resolver.bintrayRepo("com.eed3si9", "sbt-assembly")

lazy val root = (project in file(".")).
  settings(
    name := "Test",
    version := "1.0"
  )
  