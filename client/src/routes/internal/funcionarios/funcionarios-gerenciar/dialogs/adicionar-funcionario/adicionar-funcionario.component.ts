import { Component, input, output } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { InputTextModule } from "primeng/inputtext";
import { SelectModule } from "primeng/select";

@Component({
    selector: "reactor-adicionar-um-funcionario-dialog",
    templateUrl: "adicionar-funcionario.component.html",
    styleUrl: "adicionar-funcionario.component.css",
    imports: [DialogModule, InputTextModule, SelectModule, ButtonModule],
    standalone: true
})
export class AdicionarFuncionarioDialog {
    visible: boolean = false;

    public abrirDialog() {
        this.visible = true;
    }

    setores = [
        { label: 'Operações', value: 1 },
        { label: 'Engenharia', value: 2 },
        { label: 'Segurança', value: 3 },
        { label: 'Administração', value: 4 },
        { label: 'Tecnologia', value: 5 },
    ];

    onReset() {};
}