import { Component, inject } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { SelectModule } from "primeng/select";
import { UsuarioService } from "../../../../../shared/services/usuarios.service";
import { HttpErrorResponse } from "@angular/common/http";
import { MessageService } from "primeng/api";
import { Usuario } from "../../../../../shared/models/usuario";
import { FormsModule } from "@angular/forms";
import { UserType } from "../../../../../shared/enums/user-type-enum";

@Component({
    selector: "reactor-alterar-cargo-do-usuario",
    templateUrl: "altear-cargo-usuario.component.html",
    styleUrl: "altear-cargo-usuario.component.css",
    imports: [InputTextModule, ButtonModule, SelectModule, FormsModule],
    providers: [UsuarioService],
    standalone: true
})
export class AlterarCargoDoUsuarioDialog {
    public cargos: Array<{nome: string, codigo: string}> = [
        {nome: "Administrador", codigo: "ADMINISTRADOR" },
        {nome: "Gerente", codigo: "GERENTE" },
        {nome: "Engenheiro de Setor", codigo: "ENGENHEIRO_DE_SETOR" },
    ]

    public usuarioService = inject(UsuarioService);
    public codigoDoUsuarioSelecionado: string = "";
    private messageService = inject(MessageService);
    public usuarioSelecionado: Usuario | null = null;
    public cargoSelecionado: UserType | null = null;

    ngOnInit(): void {
        this.usuarioSelecionado = null;
        this.codigoDoUsuarioSelecionado = "";
        this.cargoSelecionado = null;
    }

    buscarUmUsuario() {
        this.usuarioService.buscarUsuarioPorCadastro(this.codigoDoUsuarioSelecionado).subscribe({
            next: (usuario: Usuario) => {
                this.usuarioSelecionado = usuario;
            }, 
            error: (err: HttpErrorResponse) => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao buscar usuário", 
                    detail: "Ocorreu um erro ao buscar o usuário"
                });
            }
        })
    }

    aumentarPrioridadeDeUmDeterminadoUsuario() {
        if (this.usuarioSelecionado) {
            this.usuarioService.aumentarCargoDeDeterminadoUsuario(this.usuarioSelecionado?.cadastro, this.cargoSelecionado!).subscribe({
                next: () => {
                    this.messageService.add({
                        severity: 'success', 
                        summary: "Cargo do usuário aumentado!", 
                        detail: "O Cargo do usuário foi aumentado com sucesso!"
                    });
                    this.usuarioService.listarUsuarios();
                },
                error: () => {
                    this.messageService.add({
                        severity: 'errorr', 
                        summary: "Erro ao aumentar cargo do usuário!", 
                        detail: "O Cargo do usuário não foi aumentado por um motivo desconhecido!"
                    })
                }
            })
        }
    }
}