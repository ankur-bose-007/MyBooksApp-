import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { Book } from '../../book';

@Component({
  selector: 'book-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  book:Book;

  @Input()
  useFavoritesApi:boolean;

  @Output()
  addBook=new EventEmitter();

  @Output()
  deleteBook=new EventEmitter();

  constructor(private snackbar:MatSnackBar,private dialog:MatDialog) {}

  ngOnInit() {
  }

  addToFavorites(){

    this.addBook.emit(this.book);

  }

  deleteFromFavorites(){
    this.deleteBook.emit(this.book);
  }
}
