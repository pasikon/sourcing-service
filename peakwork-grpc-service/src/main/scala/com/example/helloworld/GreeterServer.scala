package com.example.helloworld

import akka.actor.ActorSystem
import akka.http.scaladsl.UseHttp2.Always
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.{Http, Http2, HttpConnectionContext}
import akka.stream.{ActorMaterializer, Materializer}
import scala.concurrent.{ExecutionContext, Future}

import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory


object GreeterServer {

  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("GreeterServer")
    new GreeterServer(system).run()
  }
}

class GreeterServer(system: ActorSystem) {

  def run(): Future[Http.ServerBinding] = {
    implicit val sys: ActorSystem = system
    implicit val mat: Materializer = ActorMaterializer()
    implicit val ec: ExecutionContext = sys.dispatcher

    val defaultConfig = ConfigFactory.defaultApplication().resolve()
    val grpcPort = defaultConfig.getInt("grpc.server.port")

    val service: HttpRequest => Future[HttpResponse] =
      GreeterServiceHandler(new GreeterServiceImpl(mat, system.log))

    val bound = Http2().bindAndHandleAsync(
      service,
      interface = "0.0.0.0",
      port = grpcPort,
      connectionContext = HttpConnectionContext(http2 = Always),
      parallelism = 16
    )

    bound.foreach { binding =>
      sys.log.info("gRPC server bound to: {}", binding.localAddress)
    }

    bound
  }
}
