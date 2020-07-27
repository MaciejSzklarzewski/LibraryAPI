package bussiness.models

case class User(id: Option[Long] = None, username: String, password: String, details: UserDetails)