package saferesource

import sbt.File

case class ResourceInfo(
    baseResourceDirectory: File,
    resourceFile: File
)
