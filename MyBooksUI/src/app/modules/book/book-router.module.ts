import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 
import { SearchComponent } from './components/search/search.component';
import { AuthGuardService } from 'src/app/auth-guard.service';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { TbdbContainerComponent } from './components/tbdb-container/tbdb-container.component';
const bookRoutes:Routes=[
    {
        path:'books',
        children:[
            {
                path:'',
                redirectTo:'/books/popular',
                pathMatch:'full',
                canActivate: [AuthGuardService]

            },
            {
                path:'popular',
                component:TbdbContainerComponent,
                data:{
                    bookType:'popular'
                },
                canActivate: [AuthGuardService]
            },
            {
                path:'favorites',
                component:FavoritesComponent,
                canActivate: [AuthGuardService]
            },
            {
                path:'search',
                component:SearchComponent,
                canActivate: [AuthGuardService]
            }
        ]
    }
]
@NgModule({
    imports:[
        RouterModule.forChild(bookRoutes),
    ],
    exports:[
        RouterModule
    ]
})
export class BookRouterModule{}