import { Component, input, output } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { InputTextModule } from "primeng/inputtext";
import { SelectModule } from "primeng/select";
import { ADICIONAR_FUNCIONARIO_FORM } from "../../funcionario.form";

@Component({
    selector: "reactor-adicionar-um-funcionario-dialog",
    templateUrl: "adicionar-funcionario.component.html",
    styleUrl: "adicionar-funcionario.component.css",
    imports: [DialogModule, InputTextModule, SelectModule, ButtonModule, ReactiveFormsModule],
    standalone: true
})
export class AdicionarFuncionarioDialog {
    visible: boolean = false;

    formulario = ADICIONAR_FUNCIONARIO_FORM();

    public cadastrar = output<any>();

    public abrirDialog() {
        this.visible = true;
        this.formulario = ADICIONAR_FUNCIONARIO_FORM();
    }

    public salvar() {
        if (this.formulario.valid) {
            this.cadastrar.emit(this.formulario.value);
        }
    }

    onReset() {};
}