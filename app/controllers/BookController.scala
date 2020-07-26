package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{BaseController, ControllerComponents}

@Singleton
class BookController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

}