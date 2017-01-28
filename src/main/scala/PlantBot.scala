package net.yuuzukiyo.plantbot

import scala.io.Source
import scala.util.Properties
import java.io._
import java.nio.file.{Files, Paths}
import net.sourceforge.plantuml.SourceStringReader
import info.mukel.telegrambot4s._, api._, methods._, models._, Implicits._


object PlantBot extends App
                with TelegramBot
                with Polling
                with Commands {

  lazy val token = Source.fromFile("bot.token").getLines().mkString

  override def onMessage(msg: Message): Unit = {
    val input = new StringBuilder
    for (text <- msg.text) {
      input ++= text
    }
    val pngOutput = new ByteArrayOutputStream
    val reader = new SourceStringReader(input.toString)
    val desc = reader.generateImage(pngOutput)

    val pngSend = InputFile("plant.png", pngOutput.toByteArray)
    request(SendPhoto(msg.sender, pngSend))
  }

  run()
}
