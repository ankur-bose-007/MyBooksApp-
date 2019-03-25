import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs'
import { Book } from './book';
@Injectable({
  providedIn: 'root'
})
export class BookService {

  openLibEndpoint: string;
  favouritesEndpoint: string;
  userId: string;


  constructor(private http: HttpClient) {
    this.favouritesEndpoint = 'http://localhost:9090/api/v1/book/';
    this.userId=localStorage.getItem('userId');
  }

  getBooks(type: string): Observable<Array<Book>> {
    return this.http.get(`http://openlibrary.org/search.json?q=${type}&page=1`).pipe(
      map(this.formatObjects)
    );


  }

  formatObjects(object) {
    let objectList = object['docs'];
    let book:Book;
    let bookList = [];
    for (let object of objectList) {
      book = new Book();
      if (object.title === undefined || object.author_name === undefined || object.isbn === undefined || object.key === undefined)
        continue;
      book.title = object.title;
      book.author = object.author_name[0];
      book.isbn = object.isbn[0];
      book.key = object.key;
      book.url = "www.amazon.com";
      bookList.push(book);
    }
    return bookList;
  }

  saveFavoritesBooks(book: Book) {
    book.userId=this.userId;
    console.log(book.userId)
    return this.http.post(this.favouritesEndpoint,book);
  }

  deleteFromMyFavorites(book: Book) {
    book.userId=this.userId;
    return this.http.delete(this.favouritesEndpoint+book.id);    
  }

  getMyFavorites() {
    return this.http.get<Array<Book>>(this.favouritesEndpoint+this.userId);
  }

  searchBooks(type:string,searchKey:string ){
    return this.http.get(`http://openlibrary.org/search.json?${type}=${searchKey}&page=1`).pipe(
      map(this.formatObjects)
    );
  }
}
