import { Component, inject } from "@angular/core";
import { CheckboxModule } from "primeng/checkbox";
import { DropdownModule } from "primeng/dropdown";
import { InputNumberModule } from "primeng/inputnumber";
import { InputTextModule } from "primeng/inputtext";
import { FormGroup, ReactiveFormsModule } from "@angular/forms";
import { ADICIONAR_SETOR_FORM } from "../shared/adicionar-setor.form";
import { TableModule } from "primeng/table";
import { FUNCIONARIOS } from "../shared/mock/funcionarios";
import { ButtonModule } from "primeng/button";
import { FormularioSetorComponent } from "../shared/formulario-setor/formulario-adicionar-setor.component";
import { UNIDADES_DE_MEDIDAS } from "../../../../rules/unidades_de_medida";
import { SetorService } from "../../../../shared/services/setor.service";
import { MessageService } from "primeng/api";

@Component({
    selector: "reactor-adicionar-setor",
    templateUrl: "adicionar-setor.component.html",
    styleUrl: "adicionar-setor.component.css",
    imports: [
      InputTextModule, TableModule, InputNumberModule, DropdownModule, CheckboxModule, 
      ReactiveFormsModule, ButtonModule, FormularioSetorComponent  
    ],
    providers: [
      SetorService
    ],
    standalone: true,
})
export class AdicionarSetorRoute {
  private setorService = inject(SetorService);
  private messageService = inject(MessageService)
  
  ngOnInit(): void {}
  
  salvarSetor(event: any): void {
    this.setorService.cadastrarUmSetor(event).subscribe({
      next: () => {
        this.messageService.add({
          severity: "success",
          summary: "Setor cadastrado com sucesso!",
          detail: "Parabéns!"
        })
      },
      error: () => {
        this.messageService.add({
          severity: "danger",
          summary: "Erro ao cadastrar setor!",
          detail: "Parabéns também são poucos que conseguem erros!"
        })
      }
    })
  }
}