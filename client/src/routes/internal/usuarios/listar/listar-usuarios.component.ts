import { Component, inject, OnInit, signal } from "@angular/core";
import { TableModule } from "primeng/table";
import { UsuarioService } from "../../../../shared/services/usuarios.service";
import { Usuario } from "../../../../shared/models/usuario";

@Component({
    selector: "reactor-listar-usuarios",
    templateUrl: "listar-usuarios.component.html",
    styleUrl: "listar-usuarios.component.css",
    imports: [TableModule],
    providers: [UsuarioService],
    standalone: true
})
export class ListarUsuariosRoute implements OnInit {
    public usuarios = signal<Array<Usuario> | null>(null);
    private usuarioService = inject(UsuarioService);

    ngOnInit(): void {
        this.usuarioService.listarUsuarios();
        this.usuarios = this.usuarioService.getUsuarios();
    }
}