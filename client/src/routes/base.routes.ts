import { Routes } from '@angular/router';

export const routes: Routes = [
    { 
        path: "login", 
        loadComponent: () => import("./external/login/login.component")
            .then(comp => comp.LoginRoute)
    }
];
