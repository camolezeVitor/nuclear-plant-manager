import { Component, input, OnInit, output } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { InputTextModule } from "primeng/inputtext";
import { SelectModule } from "primeng/select";
import { ADICIONAR_FUNCIONARIO_FORM } from "../../funcionario.form";
import { Funcionario } from "../../../../../../shared/models/funcionario";

@Component({
    selector: "reactor-editar-um-funcionario-dialog",
    templateUrl: "editar-funcionario.component.html",
    styleUrl: "editar-funcionario.component.css",
    imports: [DialogModule, InputTextModule, SelectModule, ButtonModule, ReactiveFormsModule],
    standalone: true
})
export class EditarFuncionarioDialog  {
    visible: boolean = false;

    formulario = ADICIONAR_FUNCIONARIO_FORM();
    funcionarioASerEditado = input<Funcionario>();

    public salvarDados = output<any>();

    public abrirDialog() {
        this.visible = true;
        this.formulario = ADICIONAR_FUNCIONARIO_FORM(this.funcionarioASerEditado());
    }

    public salvar() {
        if (this.formulario.valid) {
            this.salvarDados.emit(this.formulario.value);
        }
    }

    onReset() {};
}