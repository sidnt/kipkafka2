package kafkaclient
import Models._

object Helpers {

  def employeeToPerson(e:Employee): Person = {
    val salutation = e.gender.toLowerCase match {
      case "male" => "Mr."
      case "female" => "Ms."
      case _ => ""
    }

    Person(e.id, s"$salutation ${e.firstname} ${e.lastname}")
  }
}