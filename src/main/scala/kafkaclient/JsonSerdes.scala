package kafkaclient

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization._
import Models._
import java.util

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object JsonSerDes {

  val EmployeeSerDe: Serde[Employee] = new Serde[Employee] {

    def close(): Unit = {}
    def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = {}

    def serializer: Serializer[Employee] = new Serializer[Employee] {

      override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

      override def serialize(topic: String, emp: Employee): Array[Byte] = {

        try {
          emp.toString.getBytes
        }
        catch {
          case ex: Exception => throw new Exception("serialize from Employee: " + ex.getMessage)
        }
      }

      override def close(): Unit = {}
    }

    def deserializer: Deserializer[Employee] = new Deserializer[Employee] {

      def close(): Unit = {}

      def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = {}

      def deserialize(topic: String, bytes: Array[Byte]): Employee = {

        try {
          //bytes is actually containing a JSON string
          val rawJson:String = bytes.map(_.toChar).foldLeft("")(_+_)
//          val circeJson = parse(rawJson).getOrElse(Json.Null)
          decode[Employee](rawJson).getOrElse(Employee(0,"","",""))
        }
        catch {
          case ex: Exception => throw new Exception("Deserialize to Employee: " + ex.getMessage)
        }

      }

    }


  }

  implicit val PersonSerDe: Serde[Person] = new Serde[Person] {

    def close(): Unit = {}
    def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = {}

    def serializer: Serializer[Person] = new Serializer[Person] {

      override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

      override def serialize(topic: String, per: Person): Array[Byte] = {

        try {
          per.asJson.noSpaces.getBytes
        }

        catch {
          case ex: Exception => throw new Exception("Serialize from Person: " + ex.getMessage)
        }


      }

      override def close(): Unit = {}
    }

    def deserializer: Deserializer[Person] = new Deserializer[Person] {

      def close(): Unit = {}
      def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = {}

      def deserialize(topic: String, bytes: Array[Byte]): Person = {

        try {
          val rawJson:String = bytes.map(_.toChar).foldLeft("")(_+_)
          decode[Person](rawJson).getOrElse(Person(0,""))
        }
        catch {
          case ex: Exception => throw new Exception("Deserialize to Person: " + ex.getMessage)
        }
      }
    }

  }

}
