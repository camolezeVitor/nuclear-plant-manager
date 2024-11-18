import { Route } from "@angular/router";

/**
 * primeiramente gostaria de esclarescer que não concatenei
 * as rotas pois estada meio apressadão :) 
 * 
 * (depois eu faço isso... bjs)
 */
export const internalRoutes: Array<Route> = [
    //ROTAS DO SETOR:
    {
        path: "setor-cadeia-de-suprimentos",
        loadComponent: () => import("./setor/cadeia-de-suprimentos/setor-cadeia-de-suprimentos.component")
            .then(comp => comp.SetorCadeiaDeSuprimentosRoute),
    },
    {
        path: "setor-gerenciar",
        loadComponent: () => import("./setor/gerenciar-setores/gerenciar-setores.component")
            .then(comp => comp.GerenciarSetoresRoute),
    },
    {
        path: "setor-adicionar",
        loadComponent: () => import("./setor/adicionar-setor/adicionar-setor.component")
            .then(comp => comp.AdicionarSetorRoute)
    },
    {
        path: "setor-alterar",
        loadComponent: () => import("./setor/alterar-setor/alterar-setor.component")
            .then(comp => comp.AlterarSetorRoute)
    },

    //ROTAS DO NUCLEO
    {
        path: "reator-varetas",
        loadComponent: () => import("./reator/varetas/varetas.component")
            .then(comp => comp.VaretasRoute)
    },
    {
        path: "reator-estado",
        loadComponent: () => import("./reator/estado/estado-reator.component")
            .then(comp => comp.EstadoReatorRoute)
    },
    {
        path: "reator-gerenciar",
        loadComponent: () => import("./reator/gerenciar/gerenciar-reator.component")
            .then(comp => comp.GerenciarReatorRoute)
    },

    //ROTAS DO USUÁRIO
    {
        path: "usuarios-listar",
        loadComponent: () => import("./usuarios/listar/listar-usuarios.component")
            .then(comp => comp.ListarUsuariosRoute)
    }
]