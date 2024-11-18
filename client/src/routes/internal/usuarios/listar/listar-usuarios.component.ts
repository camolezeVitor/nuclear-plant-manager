import { Component } from "@angular/core";
import { TableModule } from "primeng/table";

@Component({
    selector: "reactor-listar-usuarios",
    templateUrl: "listar-usuarios.component.html",
    styleUrl: "listar-usuarios.component.css",
    imports: [TableModule],
    standalone: true
})
export class ListarUsuariosRoute {
    usuarios = [
        { nome: 'Alice Silva', codigo: '001', cargo: 'Desenvolvedora' },
        { nome: 'Bruno Santos', codigo: '002', cargo: 'Gerente' },
        { nome: 'Carla Oliveira', codigo: '003', cargo: 'Analista' },
    ];
}