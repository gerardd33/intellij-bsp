//package org.jetbrains.plugins.bsp.project.test.environment
//
//import java.io.File
//
//import com.intellij.openapi.module.Module
//import org.jetbrains.plugins.bsp.BspUtil
//import org.jetbrains.plugins.scala.worksheet.WorksheetCompilerExtension
//import org.jetbrains.plugins.scala.worksheet.actions.topmenu.TopComponentAction
//
//class BspWorksheetCompilerExtension extends WorksheetCompilerExtension {
//  override def worksheetClasspath(module: Module): Option[Seq[File]] = {
//    if (BspUtil.isBspModule(module)) {
//      BspJvmEnvironment.resolveForWorksheet(module).toOption.map { env =>
//        env.classpath.map(path => new File(path))
//      }
//    } else None
//  }
//
//  override def extraWorksheetActions(): Seq[TopComponentAction] = {
//    Seq(new ConfigureBspTargetForWorksheet)
//  }
//}
//
