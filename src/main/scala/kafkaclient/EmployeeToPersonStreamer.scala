package kafkaclient

import com.lightbend.kafka.scala.streams.KStreamS
import com.lightbend.kafka.scala.streams.ImplicitConversions._
import com.lightbend.kafka.scala.streams.DefaultSerdes._
import org.apache.kafka.streams.{Consumed, KafkaStreams, StreamsBuilder, StreamsConfig}
import org.apache.kafka.streams.kstream.KStream
import java.util.Properties

import Models._
import Helpers._
import SerDes._


object EmployeeToPersonStreamer extends App {

  val builder: StreamsBuilder  = new StreamsBuilder();
  val sourceStream: KStream[String, Employee] = builder.stream("kipkafka2b-Employee",consumedFromSerde(stringSerde,SerDes.EmployeeSerDe))
  val inputStream:KStreamS[String, Employee] = new KStreamS(sourceStream)
  
  val interimStream:KStreamS[String, Person] = inputStream.mapValues(employeeToPerson(_))//.to(Serdes.String(), kafkaclient.PersonSerDe)
  println("trying to print")

  interimStream.to("kipkafka2b-Person")

  val props = new Properties()
  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "EmployeeToPersonStreamer")
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")

  val streams = new KafkaStreams(builder.build(), props);

  streams.start();
  
  // val outputStream = new KafkaStreams()
  //(implicit keySerde: Serde[K], valueSerde: Serde[V]): Produced[K, V] )
  // val props = new Properties()
  // props.put(StreamsConfig.APPLICATION_ID_CONFIG, "EmployeeToPersonStreamer")
  // props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, "kafkaclient.EmployeeSerDe")
  
  
    //   (new StreamsBuilder()).stream("kipkafka2-Employee")
    // )

  

  // val outputStream = new KafkaStreams(inputStream, config)
  // outputStream.start()


  

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