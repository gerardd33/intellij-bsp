package org.jetbrains.plugins.bsp.data

import java.net.URI
import java.util

import com.intellij.openapi.externalSystem.model.Key
import com.intellij.serialization.PropertyMapping
import org.jetbrains.plugins.bsp.data.BspEntityData.datakey

object sbtData {

}

/**
  * Data describing a "build" module: The IDEA-side representation of the sbt meta-project
  * @param imports implicit sbt file imports.
  * @param buildFor target Ids associated with the modules that this module describes the build for
  */
@SerialVersionUID(1)
case class SbtBuildModuleDataBsp @PropertyMapping(Array("imports", "buildFor"))(
  imports: util.List[String],
  buildFor: util.List[URI]
) extends BspEntityData

object SbtBuildModuleDataBsp {
  val Key: Key[SbtBuildModuleDataBsp] = datakey(classOf[SbtBuildModuleDataBsp])
}
