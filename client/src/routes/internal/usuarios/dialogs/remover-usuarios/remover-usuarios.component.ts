import { Component } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";

@Component({
    selector: "reactor-remover-usuarios",
    templateUrl: "remover-usuarios.component.html",
    styleUrl: "remover-usuarios.component.css",
    imports: [InputTextModule, ButtonModule],
    standalone: true
})
export class RemoverUsuariosDialog {

}