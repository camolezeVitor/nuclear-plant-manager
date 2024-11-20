import { Component, inject, OnInit, signal } from "@angular/core";
import { TableModule } from "primeng/table";
import { FornecedorService } from "../../../../shared/services/fornecedor.service";
import { Fornecedor } from "../../../../shared/models/fornecedor";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";

@Component({
    selector: "reactor-listar-fornecedores",
    templateUrl: 'listar-fornecedores.component.html',
    styleUrl: "listar-fornecedores.component.css",
    imports: [TableModule, ButtonModule, DialogModule],
    providers: [
        FornecedorService
    ],
    standalone: true,
})
export class ListarFornecedoresRoute implements OnInit {

    public dialogAberto: boolean = false;
    public fornecedorSelecionado: Fornecedor | null = null;

    private fornecedoresService = inject(FornecedorService);
    public fornecedores = signal<Array<Fornecedor> | null>(null);
    

    ngOnInit(): void {
        this.fornecedoresService.listarTodosOsFornecedores();
        this.fornecedores = this.fornecedoresService.getFornecedores$();
    }

    public deletarFornecedor() {
        this.fornecedoresService.excluirUmFornecedor(this.fornecedorSelecionado!);
        this.dialogAberto = false;
    }
}