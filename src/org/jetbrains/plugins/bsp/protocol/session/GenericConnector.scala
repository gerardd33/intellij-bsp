package org.jetbrains.plugins.bsp.protocol.session

import java.io.File
import ch.epfl.scala.bsp4j.BspConnectionDetails
import org.jetbrains.plugins.bsp.protocol.session.BspServerConnector.{BspCapabilities, ProcessBsp}
import org.jetbrains.plugins.bsp.protocol.session.BspSession.Builder
import org.jetbrains.plugins.bsp.reporter.BuildReporter
import org.jetbrains.plugins.bsp.{BspBundle, BspError, BspErrorMessage}

class GenericConnector(base: File, compilerOutput: File, capabilities: BspCapabilities, methods: List[ProcessBsp]) extends BspServerConnector() {

  override def connect(reporter: BuildReporter): Either[BspError, Builder] = {
    methods.collectFirst {
      case ProcessBsp(details: BspConnectionDetails) =>
        // TODO check bsp version compatibility
        // TODO check languages compatibility
        Right(prepareBspSession(details))
    }.getOrElse(Left(BspErrorMessage(BspBundle.message("bsp.protocol.no.supported.connection.method.for.this.server"))))
  }

  private def prepareBspSession(details: BspConnectionDetails): Builder = {
    val process =
      new java.lang.ProcessBuilder(details.getArgv)
        .directory(base)
        .start()

    val cleanup = () => {
      process.destroy()
    }

    val rootUri = base.getCanonicalFile.toURI
    val compilerOutputUri = compilerOutput.getCanonicalFile.toURI
    val initializeBuildParams = BspServerConnector.createInitializeBuildParams(rootUri, compilerOutputUri, capabilities)

    BspSession.builder(process.getInputStream, process.getErrorStream, process.getOutputStream, initializeBuildParams, cleanup)
  }
}
