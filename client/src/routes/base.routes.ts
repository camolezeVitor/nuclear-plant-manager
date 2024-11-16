import { Routes } from '@angular/router';
import { internalRoutes } from './internal/internal.routes';

export const routes: Routes = [
    { 
        path: "login", 
        loadComponent: () => import("./external/login/login.component")
            .then(comp => comp.LoginRoute)
    },
    {
        path: "register",
        loadComponent: () => import("./external/register/register.component")
            .then(comp => comp.RegisterRoute)
    },
    {
        path: "app",
        loadComponent: () => import("./container/container.component")
            .then(comp => comp.ContainerRoute),
        children: internalRoutes
    }
];
