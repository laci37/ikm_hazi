name := "ikm"

autoScalaLibrary := false

mainClass := Some("Main")

javaOptions in run += "-Djava.library.path=/home/mero/workspace/opencv/build/lib"
