package kafkaclient

// Example:
// Source Table
// ID, FIRST_NAME, LAST_NAME, GENDER
// 01, Jane, Doe, Male
// 02, Lizzie, Mc.Guire, Female

// Employee: ID int, FIRST_NAME string, LAST_NAME string, GENDER string
// Person: ID int, NAME string

object Models {
  case class Employee(id:Int, firstname:String, lastname:String, gender:String)
  case class Person(id:Int,name:String)
}

// sqlite> create table Employee(id integer primary key autoincrement, firstname VARCHAR(255), lastname VARCHAR(255), gender VARCHAR(255));
// sqlite> create table Person(id integer primary key autoincrement, name VARCHAR(255));