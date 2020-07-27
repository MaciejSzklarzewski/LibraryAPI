package bussiness.models

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.{AnyContent, Request}
import utils.json.{JsonMapper, JsonParser}
import utils.request.RequestMapper

case class Book(id: Option[Long] = None, title: String, author: String, pages: Int)

object Book extends RequestMapper[Book] with JsonMapper[Book] with JsonParser[Book] {
  implicit val bookFormat = Json.format[Book]

  override def mapRequest(request: Request[AnyContent]): Option[Book] = {
    request.body.asJson match {
      case Some(json) => fromJson(json)
      case None => None
    }
  }

  override def fromJson(json: JsValue): Option[Book] = {
    val parsingResult = Json.fromJson[Book](json)

    parsingResult match {
      case JsSuccess(book, _) => Some(book)
      case JsError(_) => None
    }
  }

  override def asJson(book: Book): JsValue = Json.toJsObject(book)
}