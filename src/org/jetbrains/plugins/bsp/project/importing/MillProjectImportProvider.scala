package org.jetbrains.plugins.bsp.project.importing

import org.jetbrains.plugins.bsp.reporter.BuildMessages

import java.io.File

import scala.io.Source
import scala.sys.process._
import scala.util.{Failure, Try, Using}

object MillProjectImportProvider {
  def canImport(workspace: File): Boolean =
    Option(workspace) match {
      case Some(directory) if directory.isDirectory => isBspCompatible(directory) || isLegacyBspCompatible(directory)
      case _ => false
    }

  def bspInstall(workspace: File): Try[BuildMessages] = {
    val work =
      if (isBspCompatible(workspace)) Try(Process("./mill mill.bsp.BSP/install", workspace).!!)
      else if (isLegacyBspCompatible(workspace)) Try(Process("./mill -i mill.contrib.BSP/install", workspace).!!)
      else Failure(new IllegalStateException("Unable to install BSP as this is not a Mill project"))

    work.transform(
      _ => Try(BuildMessages.empty.status(BuildMessages.OK)),
      e => Try(BuildMessages.empty.status(BuildMessages.Error).addError(e.getMessage))
    )
  }

  private val versionPattern = """^.*(0\.8\.0|0\.7.+|0\.6.+)$"""
  private def isBspCompatible(workspace: File) = {
    findFileByName(workspace, "mill").exists { buildScript =>
      Using.resource(Source.fromFile(buildScript)) { source =>
        source
          .getLines()
          .exists(!_.matches(versionPattern))
      }
    }
  }

  // Legacy Mill =< 0.8.0
  private def isLegacyBspCompatible(workspace: File) =
    findFileByName(workspace, "build.sc").exists { buildScript =>
      Using.resource(Source.fromFile(buildScript))(
        _.getLines().contains("import $ivy.`com.lihaoyi::mill-contrib-bsp:$MILL_VERSION`")
      )
    }

  private def findFileByName(dir: File, name: String): Option[File] =
    Option(dir.listFiles())
      .getOrElse(Array.empty)
      .filterNot(_.isDirectory)
      .find(_.getName == name)
}
