package utils.json

import play.api.libs.json.JsValue

/**
 * Converts model instance to JSON
 *
 * @tparam Model model type
 */
trait JsonParser[Model] {

  /**
   * @param model model to map
   * @return model as JSON
   */
  def asJson(model: Model): JsValue
}
