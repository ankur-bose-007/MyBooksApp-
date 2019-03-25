import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../book';
import { BookService } from '../../book.service';

@Component({
  selector: 'book-tbdb-container',
  templateUrl: './tbdb-container.component.html',
  styleUrls: ['./tbdb-container.component.css']
})
export class TbdbContainerComponent implements OnInit {
  
  books:Array<Book>;
  bookType:string;

  constructor(private bookService:BookService,private route:ActivatedRoute) {
    this.books=[];
    this.route.data.subscribe((data)=>{
      this.bookType=data.bookType;
    });
  }

  ngOnInit() {
    this.bookService.getBooks(this.bookType).subscribe((books)=>{
      this.books.push(...books);
    });
  }
}
