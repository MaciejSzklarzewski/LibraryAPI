package dal.tables

import bussiness.models.Book
import dal.config.Db

trait BooksTable { this: Db =>
  import config.profile.api._

  class Books(tag: Tag) extends Table[Book](tag, _tableName = "books") {
    def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def title: Rep[String] = column[String]("title")
    def author: Rep[String] = column[String]("author")
    def pages: Rep[Int] = column[Int]("pages")

    override def * =
      (id.?, title, author, pages) <> ((Book.apply _).tupled, Book.unapply)
  }

  val books = TableQuery[Books]
}