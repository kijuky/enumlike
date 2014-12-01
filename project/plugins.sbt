// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
//resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Access git from sbt
resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.6.4")

// allows SBT publish to deploy with a metadata.xml for Artifactory to use
addSbtPlugin("no.arktekk.sbt" % "aether-deploy" % "0.11")

// for code formatting
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

// Plugin to check for outdated dependencies (run "sbt dependency-updates")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.6")

// For doing automated releases
addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8.5")
