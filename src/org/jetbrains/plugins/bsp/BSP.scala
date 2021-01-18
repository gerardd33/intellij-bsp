package org.jetbrains.plugins.bsp

import com.intellij.notification.NotificationGroup
import com.intellij.openapi.externalSystem.model.ProjectSystemId
import javax.swing.Icon
import org.jetbrains.annotations.Nls

object BSP {
  @Nls
  //noinspection ScalaExtractStringToBundle
  val Name = "BSP"
  val Icon: Icon = Icons.BSP

  val ProjectSystemId = new ProjectSystemId("BSP", Name)

  val balloonNotification: NotificationGroup = BspNotificationGroup.balloon
}
