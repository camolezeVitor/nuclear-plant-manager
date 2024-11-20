import { Component, inject, OnInit, signal } from "@angular/core";
import { InputTextModule } from "primeng/inputtext";
import { AccordionModule } from "primeng/accordion";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { DividerModule } from "primeng/divider";
import { RippleModule } from "primeng/ripple";
import { AdicionarFuncionarioDialog } from "./dialogs/adicionar-funcionario/adicionar-funcionario.component";
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from "primeng/api";
import { SetorService } from "../../../../shared/services/setor.service";
import { Setor } from "../../../../shared/models/setor";
import { SetorComFuncionarios } from "../../../../shared/models/setor-com-funcionarios";
import { CommonModule } from "@angular/common";
import { FuncionarioService } from "../../../../shared/services/funcionario.service";
import { EditarFuncionarioDialog } from "./dialogs/editar-funcionario/editar-funcionario.component";
import { Funcionario } from "../../../../shared/models/funcionario";

@Component({
    selector: "reactor-gerenciar-funcionarios",
    templateUrl: "gerenciar-funcionarios.component.html",
    styleUrl: "gerenciar-funcionarios.component.css",
    imports: [
      AccordionModule, InputTextModule, TableModule, ButtonModule, 
      DividerModule, RippleModule, EditarFuncionarioDialog, AdicionarFuncionarioDialog, ConfirmDialogModule, CommonModule],
    providers: [
      ConfirmationService,
      SetorService,
      FuncionarioService
    ],
    standalone: true
})
export class GerenciarFuncionariosRoutes implements OnInit {
  confirmDialogVisivel = false;
  public funcionarioSelecionado: Funcionario | null = null;

  private messageService = inject(MessageService);
  private funcionarioService = inject(FuncionarioService);

  private setorService = inject(SetorService);
  public setores = signal<Array<SetorComFuncionarios> | null>(null);

  constructor (private confirmationService: ConfirmationService) {}
  
  public listarSetores() {
    this.setorService.listarTodosOsSetoresComFuncionarios().subscribe({
      next: (lista) => {
        console.log(lista);
        this.setores.set(lista);
      },
      error: () => {
        this.messageService.add({
          severity: "error", detail: "Erro ao listar setores", summary: "Não faço ideia irmão :("
        })
      }
    })
  }

  atualizarFuncionario(funcionarioForm: any) {
    this.funcionarioService.atualizarUmFuncionario(funcionarioForm).subscribe({
      next: () => {
        this.messageService.add({
          severity: "success", detail: "Atualizado com sucesso!", summary: "Não faço ideia irmão :("
        })
        this.listarSetores();
      },
      error: () => {
        this.messageService.add({
          severity: "error", detail: "Erro ao atualizar Funcionários", summary: "Não faço ideia irmão :("
        })
      }
    })
  }

  cadastrarUmFuncionario(funcionarioForm: any, codigoSetor: string) {
    funcionarioForm.codigoSetor = codigoSetor;
    this.funcionarioService.cadastrarUmFuncionario(funcionarioForm).subscribe({
      next: () => {
        this.messageService.add({
          severity: "success", detail: "Cadastrado com sucesso!", summary: "Não faço ideia irmão :("
        })
        this.listarSetores();
      },
      error: () => {
        this.messageService.add({
          severity: "error", detail: "Erro ao cadastrar Funcionários", summary: "Não faço ideia irmão :("
        })
      }
    })
  }

  ngOnInit(): void {
    this.listarSetores();
  }

  removerUsuario(resposta: number) {
    const RESPOSTA_SIM = 0, RESPOSTA_NAO = 1;
    if (resposta == RESPOSTA_SIM) {
      this.funcionarioService.deletarUmFuncionario(this.funcionarioSelecionado!.id).subscribe({
        next: () => {
          this.messageService.add({
            severity: "success", detail: "Funcionário demitido com sucesso!", summary: "Não faço ideia irmão :("
          })
          this.listarSetores();
        },
        error: () => {
          this.messageService.add({
            severity: "error", detail: "Erro ao deletar Funcionários", summary: "Não faço ideia irmão :("
          })
        }
      })
    } else {
      this.funcionarioSelecionado = null;
    }
    this.confirmDialogVisivel = false;
  }
}