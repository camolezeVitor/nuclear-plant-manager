import { Component, input, output } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { CheckboxModule } from "primeng/checkbox";
import { DropdownModule } from "primeng/dropdown";
import { InputNumberModule } from "primeng/inputnumber";
import { InputTextModule } from "primeng/inputtext";
import { TableModule } from "primeng/table";
import { ADICIONAR_SETOR_FORM } from "../adicionar-setor.form";
import { FormularioSetorButton } from "./formulario-setor-button-type";

@Component({
    selector: "reactor-setor-formulario",
    templateUrl: "formulario-adicionar-setor.component.html",
    styleUrl: "formulario-adicionar-setor.component.css",
    imports: [InputTextModule, TableModule, InputNumberModule, DropdownModule, CheckboxModule, ReactiveFormsModule, ButtonModule],
    standalone: true
})
export class FormularioSetorComponent {
    public medidas = input<any>();
    public funcionarios = input<any>();
    public buttonStyle = input<FormularioSetorButton>();
    public getValue = output<object>();

    tiposSetor = [
        {label: "RH", value: 1},
        {label: "FUNCIONAMENTO", value: 2},
        {label: "SUPORTE", value: 3},
    ]

    public formulario = ADICIONAR_SETOR_FORM();
}