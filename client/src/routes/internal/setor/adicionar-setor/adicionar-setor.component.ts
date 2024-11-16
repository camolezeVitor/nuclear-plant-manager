import { Component } from "@angular/core";
import { CheckboxModule } from "primeng/checkbox";
import { DropdownModule } from "primeng/dropdown";
import { InputNumberModule } from "primeng/inputnumber";
import { InputTextModule } from "primeng/inputtext";
import { FormGroup, ReactiveFormsModule } from "@angular/forms";
import { ADICIONAR_SETOR_FORM } from "../shared/adicionar-setor.form";
import { TableModule } from "primeng/table";
import { UNIDADES_DE_MEDIDAS } from "../shared/mock/medidas";
import { FUNCIONARIOS } from "../shared/mock/funcionarios";
import { ButtonModule } from "primeng/button";
import { FormularioSetorComponent } from "../shared/formulario-setor/formulario-adicionar-setor.component";

@Component({
    selector: "reactor-adicionar-setor",
    templateUrl: "adicionar-setor.component.html",
    styleUrl: "adicionar-setor.component.css",
    imports: [
      InputTextModule, TableModule, InputNumberModule, DropdownModule, CheckboxModule, 
      ReactiveFormsModule, ButtonModule, FormularioSetorComponent  
    ],
    standalone: true,
})
export class AdicionarSetorRoute {

  unidadesDeMedida = UNIDADES_DE_MEDIDAS;
  funcionarios = FUNCIONARIOS;

  setorForm!: FormGroup;
  tiposSetor = [
    {label: "RH", value: 1},
    {label: "FUNCIONAMENTO", value: 2},
    {label: "SUPORTE", value: 3},
  ]
  
  
  constructor() {
    this.setorForm = ADICIONAR_SETOR_FORM();
  }
  
  ngOnInit(): void {}
  
  salvarSetor(): void {
    if (this.setorForm.valid) {
      const setor = this.setorForm.value;
      console.log('Setor salvo:', setor);
    } else {
      console.error('Formulário inválido');
    }
  }
}