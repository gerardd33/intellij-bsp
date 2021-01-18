package org.jetbrains.plugins.bsp.project

import com.intellij.openapi.externalSystem.service.settings.AbstractExternalSystemConfigurable
import com.intellij.openapi.externalSystem.util.ExternalSystemSettingsControl
import com.intellij.openapi.project.Project
import org.jetbrains.plugins.bsp._
import org.jetbrains.plugins.bsp.settings._
import org.jetbrains.plugins.bsp.BSP
import org.jetbrains.plugins.bsp.settings.{BspProjectSettings, BspProjectSettingsListener, BspSettings}

class BspExternalSystemConfigurable(project: Project)
  extends AbstractExternalSystemConfigurable[BspProjectSettings, BspProjectSettingsListener, BspSettings](project, BSP.ProjectSystemId) {

  override def createProjectSettingsControl(settings: BspProjectSettings): ExternalSystemSettingsControl[BspProjectSettings] =
    new BspProjectSettingsControl(settings)

  override def createSystemSettingsControl(settings: BspSettings): ExternalSystemSettingsControl[BspSettings] =
    new BspSystemSettingsControl(settings)

  override def newProjectSettings(): BspProjectSettings = new BspProjectSettings

  override def getId: String = "bsp.project.settings.configurable"
}