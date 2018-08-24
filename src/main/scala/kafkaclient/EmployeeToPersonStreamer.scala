package kafkaclient

import com.lightbend.kafka.scala.streams.KStreamS

object EmployeeToPersonStreamer extends App {

  // val inputStream = 
  //   (new KStreamBuilder()).stream("kipkafka2-Employee")
  

}
/*
  KStreamBuilder builder = new KStreamBuilder();
    KStream<String, String> textLines = builder.stream("TextLinesTopic");
    KTable<String, Long> wordCounts = textLines
        .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
        .groupBy((key, word) -> word)
        .count("Counts");
    wordCounts.to(Serdes.String(), Serdes.Long(), "WordsWithCountsTopic");

    KafkaStreams streams = new KafkaStreams(builder, config);
streams.start();

}*/