import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Book } from '../../book';
import { BookService } from '../../book.service';

@Component({
  selector: 'book-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  books:Array<Book>;
  useFavoritesApi=true;
  constructor(private bookService:BookService,private matSnackbar:MatSnackBar) {
    this.books=[];
   }

  ngOnInit() {
    let message='Favorites is empty';
    this.bookService.getMyFavorites().subscribe(books=>{
      this.books.push(...books);
    },(error)=>{
        this.matSnackbar.open(message,'',{
          duration:1000
        });
    });
  }

}
