package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import bussiness.service.BookService
import bussiness.models.Book._
import utils.response.ResponseFormatter._
import constants.ErrorMessage._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class BookController @Inject()(val controllerComponents: ControllerComponents, val bookService: BookService) extends BaseController {

  def createBook(): Action[AnyContent] = Action.async { request =>
    val bookOption = mapRequest(request)

    bookOption match {
      case Some(book) => bookService.save(book)
          .map(savedBook => {
            val responseObject = asJson(savedBook)
            Created(responseObject)
          })
      case None => Future { BadRequest(badRequestResponse(INVALID_STRUCTURE)) }
    }
  }

  def deleteById(bookId: Long): Action[AnyContent] = Action.async {
    bookService.delete(bookId)
      .map {
        case true => Ok(okResponse)
        case false => Ok(failResponse)
      }
  }
}