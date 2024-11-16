import { Route } from "@angular/router";

export const internalRoutes: Array<Route> = [
    {
        path: "setor-cadeia-de-suprimentos",
        loadComponent: () => import("./setor/cadeia-de-suprimentos/setor-cadeia-de-suprimentos.component")
            .then(comp => comp.SetorCadeiaDeSuprimentosRoute)
    }
]