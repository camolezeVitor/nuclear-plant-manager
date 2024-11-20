import { Component, inject, OnInit, signal } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { SelectModule } from "primeng/select";
import { Material } from "../../../../shared/models/material";
import { MaterialService } from "../../../../shared/services/material.service";
import { MedidaService } from "../../../../shared/services/medida.service";
import { Medida } from "../../../../shared/models/medida";
import { FORMULARIO_FORNECEDOR } from "./adicionar-fornecedor.form";
import { ReactiveFormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { FornecedorForm } from "../../../../shared/models/fornecedor-form";
import { FornecedorService } from "../../../../shared/services/fornecedor.service";

@Component({
    selector: "reactor-adicionar-fornecedor",
    templateUrl: "adicionar-fornecedor.component.html",
    styleUrl: "adicionar-fornecedor.component.css",
    imports: [InputTextModule, SelectModule, ButtonModule, ReactiveFormsModule, CommonModule],
    providers: [
        MaterialService,
        MedidaService,
        FornecedorService
    ],
    standalone: true
})
export class AdicionarFornecedorRoute implements OnInit {
    public formulario = FORMULARIO_FORNECEDOR;

    public fornecedorService = inject(FornecedorService);

    public materialService = inject(MaterialService);
    public materiais = signal<Array<Material> | null>(null);

    public medidaService = inject(MedidaService);
    public medidas = signal<Array<Medida> | null>(null);

    ngOnInit(): void {
        this.materialService.listarMateriais();
        this.medidaService.listarMedidas();
        this.materiais = this.materialService.getMateriais();
        this.medidas = this.medidaService.getMedidas();
    }

    public cadastrarUmNovoSetor() {
        if (this.formulario.valid) {
            this.fornecedorService.cadastarUmFornecedor(this.formulario.value as FornecedorForm);
        }
    }
}