PlantUML Telegram bot
=====================

Source code of [@plantuml_bot](https://t.me/plantuml_bot). More about PlantUML
and its usage can be read on the [official page](http://plantuml.com/).

Any feature requests and improvements are welcome via Github issues :)


#### If you want to run it by yourself

    git clone http://github.com/Mendor/plantuml-telegram-bot.git
    cd plantuml-telegram-bot
    echo $YOUR_TELEGRAM_BOT_TOKEN > bot.token

Building and running with [sbt](http://www.scala-sbt.org/):

    sbt compile:run

Building and running with [Docker](https://www.docker.com/):

    docker build -t plantbot .
    docker run -dt plantbot


## Todo

Commands processing (like `/start` and `/help`).


## License

[MIT](https://github.com/Mendor/plantuml-telegram-bot/blob/master/LICENSE)
