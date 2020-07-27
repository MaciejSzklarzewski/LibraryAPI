package utils.json

import play.api.libs.json.JsValue

/**
 * Maps JSON to model instance
 * @tparam Model model type
 */
trait JsonMapper[Model] {

  /**
   * @param json json to map
   * @return optional model instance
   */
  def fromJson(json: JsValue): Option[Model]
}
