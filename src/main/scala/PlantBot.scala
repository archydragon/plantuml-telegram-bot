package net.yuuzukiyo.plantbot

import scala.io.Source
import scala.util.Properties
import java.io._
import java.io.{ File => IOFile }
import java.nio.file.{Files, Paths}
import scala.sys.process._
import info.mukel.telegrambot4s._, api._, methods._, models._, Implicits._


object PlantBot extends App
                with TelegramBot
                with Polling
                with Commands {

  lazy val token = Source.fromFile("bot.token").getLines().mkString
  lazy val jarfile = Properties.envOrElse("PLANTUML_JARFILE", "plantuml.jar")

  override def onMessage(msg: Message): Unit = {
    val id = msg.messageId.toString
    val baseFilename = "/tmp/" + id
    val txtFilename = baseFilename + ".txt"
    val bw = new BufferedWriter(new FileWriter(new IOFile(txtFilename)))
    for (text <- msg.text) {
      bw.write(text)
    }
    bw.close()

    val stdout = new StringBuilder
    val stderr = new StringBuilder
    val runPlant = Seq("java", "-Djava.awt.headless=true", "-jar", jarfile, txtFilename)
    val code = runPlant ! ProcessLogger(stdout append _, stderr append _)

    if (code == 0) {
      val pngFilename = baseFilename + ".png"
      // plantuml.jar returns 0 status code even for the files don't containing
      // anything like UML diagram description, so this try-catch is necessary.
      try {
        val sendPng = InputFile(pngFilename, Files.readAllBytes(Paths.get(pngFilename)))
        request(SendPhoto(msg.sender, sendPng))
      } catch {
        case e: java.nio.file.NoSuchFileException => {}
      }
    } else {
      request(SendMessage(msg.sender, stderr.toString.replaceFirst(" in file.*txt", "\n")))
    }
  }

  run()
}
