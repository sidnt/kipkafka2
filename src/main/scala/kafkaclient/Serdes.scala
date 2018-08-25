package kafkaclient

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization._
import Models._
import java.util
import java.io.{ByteArrayOutputStream, ByteArrayInputStream, ObjectOutputStream, ObjectInputStream}

object SerDes {

  implicit val EmployeeSerDe: Serde[Employee] = new Serde[Employee] {

    def close(): Unit = {}
    def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = {}

    def serializer: Serializer[Employee] = new Serializer[Employee] {

      override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

      override def serialize(topic: String, emp: Employee): Array[Byte] = {

        try {
          val byteOutputStream = new ByteArrayOutputStream()
          val objectSerialized = new ObjectOutputStream(byteOutputStream)
          objectSerialized.writeObject(emp)
          byteOutputStream.toByteArray
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
          val byteInputStream = new ByteArrayInputStream(bytes)
          val inputObject = new ObjectInputStream(byteInputStream)
          val objectDeserialized = inputObject.readObject().asInstanceOf[Employee]
          objectDeserialized
          // val stringRep = bytes.map(_.toChar).foldLeft("")(_+_) //"Employee(1, Jane, Doe, Male)"
          // val arr = stringRep split "," //
          // val (id, fname, lname, gender) = ( arr(0).drop(9).toInt, arr(1), arr(2), arr(3).init )
          // Employee(id,fname, lname, gender)
        }
        catch {
          case ex: Exception => throw new Exception("deserialize to Employee: " + ex.getMessage)
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
          val byteOutputStream = new ByteArrayOutputStream()
          val objectSerialized = new ObjectOutputStream(byteOutputStream)
          objectSerialized.writeObject(per)
          byteOutputStream.toByteArray
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
          val byteInputStream = new ByteArrayInputStream(bytes)
          val inputObject = new ObjectInputStream(byteInputStream)
          val objectDeserialized = inputObject.readObject().asInstanceOf[Person]
          objectDeserialized
          // val stringRep = bytes.map(_.toChar).foldLeft("")(_+_) //"Person(1, Mr. Jane Doe)"
          // val arr = stringRep split ","
          // val (id, name) = (arr(0).drop(7).toInt, arr(1).init)
          // Person(id,name)
        }
        catch {
          case ex: Exception => throw new Exception("Deserialize to Person: " + ex.getMessage)
        }
      }
    }

  }

}
