import { Component, OnInit, Input } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Book } from '../../book';
import { BookService } from '../../book.service';

@Component({
  selector: 'book-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  books:Array<Book>;

  @Input()
  useFavoritesApi:boolean;

  constructor(private bookService:BookService,private matSnackBar:MatSnackBar) {}

  ngOnInit() {
    
  }

  addBookToFavorites(book){
    let message=`${book.title} added to favorites`;
    let errorMessage=`${book.title} already in favorites`;
    console.log("book title"+book.title);
    this.bookService.saveFavoritesBooks(book).subscribe((book)=>{
      this.matSnackBar.open(message,'',{
        duration:1000
      })
    },error=>{
      this.matSnackBar.open(errorMessage,'',{
        duration:1000
      })
    });
  }

  deleteFromFavorites(book){
    let message=`${book.title} deleted from your favorites`;

    for(var i=0;i<this.books.length;i++){
      if(this.books[i].title===book.title){
        this.books.splice(i,1);
      }
    }

    this.bookService.deleteFromMyFavorites(book).subscribe((book)=>{
      this.matSnackBar.open(message,'',{
        duration:1000
      });
    });
  }

}
