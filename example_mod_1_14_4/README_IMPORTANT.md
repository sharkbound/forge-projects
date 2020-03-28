IF BUILDING AS A JAR, CHANGE `    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"` TO `compile "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"` IN BUILD.GRADLE!

KEEP IT AS `    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"` TO KEEP CLIENT/SERVER RUNS WORKING IN INTELLIJ

MAKE SURE TO DO A GRADLE CLEAN ON CHANGES!