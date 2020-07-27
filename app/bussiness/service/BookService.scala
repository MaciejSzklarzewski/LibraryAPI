package bussiness.service

import bussiness.models.Book
import dal.repository.BookRepository
import javax.inject.{Inject, Singleton}

import scala.concurrent.Future

@Singleton
class BookService @Inject()(val bookRepository: BookRepository) {

  def save(book: Book): Future[Book] = bookRepository.save(book)

  def delete(bookId: Long): Future[Boolean] = bookRepository.delete(bookId)
}