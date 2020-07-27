package utils.response

import constants.ResponseStatus.{FAIL, OK}
import constants.ResponseTag.{ERROR, STATUS}
import play.api.libs.json.{JsObject, JsString, JsValue}

object ResponseFormatter {

  def okResponse: JsValue = JsObject {
    Seq {
      STATUS -> JsString(OK)
    }
  }

  def failResponse: JsValue = JsObject {
    Seq {
      STATUS -> JsString(FAIL)
    }
  }

  def badRequestResponse(errorMessage: String): JsValue = JsObject {
    Seq {
      STATUS -> JsString(FAIL)
      ERROR -> JsString(errorMessage)
    }
  }
}
