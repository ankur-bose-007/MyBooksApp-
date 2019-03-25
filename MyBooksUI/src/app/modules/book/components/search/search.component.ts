import { Component, OnInit } from '@angular/core';
import { Book } from '../../book';
import { BookService } from '../../book.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'book-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  books:Array<Book>;
  selected:string='';

  constructor(private bookService: BookService,private matSnackBar:MatSnackBar) { }

  ngOnInit() {
  }

  onEnter(searchKey){
    console.log('search key',searchKey);
    if(this.selected==''||this.selected=='any'){
      this.bookService.getBooks(searchKey).subscribe(books=>{
        this.books=books;
      });  
    }else{
      this.bookService.searchBooks(this.selected,searchKey).subscribe(books=>{
        this.books=books;
      });
    }
  }
}
