package org.jetbrains.plugins.bsp.project

import com.intellij.serialization.PropertyMapping

import java.io.File

sealed abstract class SdkReference

final case class JdkByName @PropertyMapping(Array("name")) (name: String) extends SdkReference
final case class JdkByHome @PropertyMapping(Array("home")) (home: File) extends SdkReference
final case class JdkByVersion @PropertyMapping(Array("version")) (version: String) extends SdkReference
final case class AndroidJdk @PropertyMapping(Array("version")) (version: String) extends SdkReference