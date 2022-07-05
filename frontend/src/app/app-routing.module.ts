import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./components/home/home.component";
import { AdminpageComponent } from "./components/pages/adminpage/adminpage.component";
import { UserpageComponent } from "./components/pages/userpage/userpage.component";

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'user',
        component: UserpageComponent
    },
    {
        path: 'admin',
        component: AdminpageComponent
    },
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }