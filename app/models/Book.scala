package models

case class Book(id: Option[Long] = None, title: String, author: String, pages: Int)