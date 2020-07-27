package utils.request

import play.api.mvc.{AnyContent, Request}

/**
 * Maps request to model
 *
 * @tparam Model model type
 */
trait RequestMapper[Model] {

  /**
   * @param request request to map
   * @return optional model instance
   */
  def mapRequest(request: Request[AnyContent]): Option[Model]
}
