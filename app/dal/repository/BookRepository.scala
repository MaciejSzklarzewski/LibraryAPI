package dal.repository

import dal.config.{Db, DbConfiguration}
import dal.tables.BooksTable
import models.Book
import slick.dbio.DBIOAction
import slick.jdbc.meta.MTable

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class BookRepository extends Db with DbConfiguration with BooksTable {
  import config.profile.api._
  import scala.concurrent.ExecutionContext.Implicits.global


  def init: Future[Unit] = database.run(DBIOAction.seq(books.schema.create))

  Await.result(database.run(DBIOAction.seq(
    MTable.getTables map (tables => {
      if (!tables.exists(_.name.name == books.baseTableRow.tableName))
        init
    })
  )), Duration.Inf)


  def findAll: Future[Seq[Book]] = database run books.result

  def save(book: Book): Future[Book] =
    database
    .run(books returning books
      .map(_.id)
      += book)
    .map(generatedId => book.copy(id = Some(generatedId)))

  def delete(id: Long): Future[Boolean] =
    database
    .run(books
      .filter(_.id === id)
      .delete)
    .map { _ > 0 }
}