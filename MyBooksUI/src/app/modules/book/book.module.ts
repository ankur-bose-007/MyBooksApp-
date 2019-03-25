import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { ContainerComponent } from './components/container/container.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptorService } from './token-interceptor.service';
import { BookService } from './book.service';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { TbdbContainerComponent } from './components/tbdb-container/tbdb-container.component';
import { BookRouterModule } from './book-router.module';
import { MatSelectModule } from '@angular/material';
@NgModule({
  declarations: [ThumbnailComponent, ContainerComponent, FavoritesComponent, TbdbContainerComponent, SearchComponent],
  entryComponents:[],
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatInputModule,
    FormsModule,
    MatDialogModule,
    BookRouterModule,
    MatSelectModule
  ],
  exports:[
    ThumbnailComponent,
    ContainerComponent,
    MatCardModule,
    MatButtonModule,
    SearchComponent
  ],
  providers:[BookService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ]
})
export class BookModule { }
