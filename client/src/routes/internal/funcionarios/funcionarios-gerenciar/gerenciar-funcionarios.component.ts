import { Component } from "@angular/core";
import { InputTextModule } from "primeng/inputtext";
import { AccordionModule } from "primeng/accordion";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { DividerModule } from "primeng/divider";
import { RippleModule } from "primeng/ripple";
import { AdicionarFuncionarioDialog } from "./dialogs/adicionar-funcionario/adicionar-funcionario.component";
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from "primeng/api";

@Component({
    selector: "reactor-gerenciar-funcionarios",
    templateUrl: "gerenciar-funcionarios.component.html",
    styleUrl: "gerenciar-funcionarios.component.css",
    imports: [
      AccordionModule, InputTextModule, TableModule, ButtonModule, 
      DividerModule, RippleModule, AdicionarFuncionarioDialog, ConfirmDialogModule],
    providers: [
      ConfirmationService
    ],
    standalone: true
})
export class GerenciarFuncionariosRoutes {
  confirmDialogVisivel = false;

  funcionarios = [
        {
          nome: 'Carlos Silva',
          cargo: 'Operador de Reator',
          salario: 8500.5,
          periodo: '10-12',
          setor: 'Operações',
        },
        {
          nome: 'Ana Costa',
          cargo: 'Engenheira Nuclear',
          salario: 12500.0,
          periodo: '10-12',
          setor: 'Engenharia',
        },
        {
          nome: 'José Ferreira',
          cargo: 'Técnico em Segurança',
          salario: 7000.0,
          periodo: '10-12',
          setor: 'Segurança',
        },
        {
          nome: 'Maria Oliveira',
          cargo: 'Supervisora',
          salario: 9500.75,
          periodo: '10-12',
          setor: 'Administração',
        },
        {
          nome: 'João Sousa',
          cargo: 'Analista de Dados',
          salario: 8800.0,
          periodo: '10-12',
          setor: 'Tecnologia',
        },
    ];

  constructor (private confirmationService: ConfirmationService) {}

  removerUsuario(resposta: number) {
    const RESPOSTA_SIM = 0, RESPOSTA_NAO = 1;
    if (resposta == RESPOSTA_SIM) {
      //...
    }
    this.confirmDialogVisivel = false;
  }
}