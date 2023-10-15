
val libraries = new {
    val versions = new {
        val zio = "1.0.18"
    }
    val zio = "dev.zio" %% "zio" % versions.zio
}

libraryDependencies += libraries.zio