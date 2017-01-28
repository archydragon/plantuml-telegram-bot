package net.yuuzukiyo.plantbot

import scala.io.Source
import java.io._
import info.mukel.telegrambot4s._, api._, methods._, models._, Implicits._
import net.sourceforge.plantuml.SourceStringReader


object PlantBot extends App
                with TelegramBot
                with Polling
                with Commands {

  lazy val token = Source.fromFile("bot.token").getLines().mkString

  override def onMessage(msg: Message): Unit = {
    // read original message text
    val input = new StringBuilder
    for (text <- msg.text)
      input ++= text

    // generate PNG from the description
    val pngOutput = new ByteArrayOutputStream
    val reader = new SourceStringReader(input.toString)
    val desc = reader.generateImage(pngOutput)

    // send PNG back
    val pngSend = InputFile("plant.png", pngOutput.toByteArray)
    request(SendPhoto(msg.sender, pngSend))
  }

  run()
}
