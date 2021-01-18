package org.jetbrains.plugins.bsp.project.importing.preimport

import java.io.File
//import com.intellij.openapi.projectRoots.{JavaSdk, ProjectJdkTable}
//import com.intellij.openapi.util.io.FileUtil
//import org.jetbrains.plugins.bsp.BspBundle
import org.jetbrains.plugins.bsp.reporter.{BuildMessages, BuildReporter}
//import org.jetbrains.plugins.bsp.util.extensions.invokeAndWait
//import org.jetbrains.plugins.scala.buildinfo.BuildInfo
//import org.jetbrains.plugins.scala.project.Version
//import org.jetbrains.sbt.SbtUtil.{detectSbtVersion, getDefaultLauncher, sbtVersionParam, upgradedSbtVersion}
//import org.jetbrains.sbt.project.SbtExternalSystemManager
//import org.jetbrains.sbt.project.structure.SbtStructureDump
//import org.jetbrains.sbt.{Sbt, SbtUtil}

import scala.util.Try

//class BloopPreImporter(dumper: SbtStructureDump, runDump: SbtStructureDump => Try[BuildMessages])
class BloopPreImporter() extends PreImporter {

//  override def cancel(): Unit = dumper.cancel()
  override def cancel(): Unit = ??? // TODO implement me

//  def run(): Try[BuildMessages] = runDump(dumper)
  def run(): Try[BuildMessages] = ??? // TODO implement me
}

object BloopPreImporter {
  def apply(baseDir: File)(implicit reporter: BuildReporter): BloopPreImporter = ???
}

//object BloopPreImporter {
//  def apply(baseDir: File)(implicit reporter: BuildReporter): BloopPreImporter = {
//    invokeAndWait(ProjectJdkTable.getInstance.preconfigure())
//    val jdkType = JavaSdk.getInstance()
//    val jdk = ProjectJdkTable.getInstance().findMostRecentSdkOfType(jdkType)
//    val jdkExe = new File(jdkType.getVMExecutablePath(jdk))
//    val jdkHome = Option(jdk.getHomePath).map(new File(_))
//    val sbtLauncher = SbtUtil.getDefaultLauncher
//
//    val injectedPlugins = s"""addSbtPlugin("ch.epfl.scala" % "sbt-bloop" % "${BuildInfo.bloopVersion}")"""
//    val pluginFile = FileUtil.createTempFile("idea",Sbt.Extension, true)
//    val pluginFilePath = SbtUtil.normalizePath(pluginFile)
//    FileUtil.writeToFile(pluginFile, injectedPlugins)
//
//    val injectedSettings = """bloopExportJarClassifiers in Global := Some(Set("sources"))"""
//    val settingsFile = FileUtil.createTempFile(baseDir, "idea-bloop", Sbt.Extension, true)
//    FileUtil.writeToFile(settingsFile, injectedSettings)
//
//    val sbtCommandArgs = List(
//      "early(addPluginSbtFile=\"\"\"" + pluginFilePath + "\"\"\")"
//    )
//    val sbtCommands = "bloopInstall"
//
//    val projectSbtVersion = Version(detectSbtVersion(baseDir, getDefaultLauncher))
//    val sbtVersion = upgradedSbtVersion(projectSbtVersion)
//    val upgradeParam =
//      if (sbtVersion > projectSbtVersion)
//        List(sbtVersionParam(sbtVersion))
//      else List.empty
//
//    val vmArgs = SbtExternalSystemManager.getVmOptions(Seq.empty, jdkHome) ++ upgradeParam
//
//    try {
//      val dumper = new SbtStructureDump()
//      val runDump = (dumper: SbtStructureDump) => dumper.runSbt(
//        baseDir, jdkExe, vmArgs,
//        Map.empty, sbtLauncher, sbtCommandArgs, sbtCommands,
//        BspBundle.message("bsp.resolver.creating.bloop.configuration.from.sbt"),
//      )
//      new BloopPreImporter(dumper, runDump)
//    } finally {
//      settingsFile.delete()
//    }
//  }
//}
