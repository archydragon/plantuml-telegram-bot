FROM lustefaniak/sbt:0.13.12_2.11.8

ADD . /app
WORKDIR /app
RUN curl 'https://vorboss.dl.sourceforge.net/project/plantuml/plantuml.jar' -o '/app/plantuml.jar' -L \
    && sbt -mem 256 assembly

ENV PLANTUML_JARFILE=/app/plantuml.jar

CMD ["java", "-jar", "/app/target/scala-2.11/PlantBot-assembly-0.1.jar"]