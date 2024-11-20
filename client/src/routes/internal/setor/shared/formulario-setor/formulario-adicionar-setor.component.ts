import { Component, inject, input, OnInit, output, signal } from "@angular/core";
import { FormArray, FormControl, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { CheckboxModule } from "primeng/checkbox";
import { DropdownModule } from "primeng/dropdown";
import { InputNumberModule } from "primeng/inputnumber";
import { InputTextModule } from "primeng/inputtext";
import { TableModule } from "primeng/table";
import { ADICIONAR_SETOR_FORM } from "../adicionar-setor.form";
import { FormularioSetorButton } from "./formulario-setor-button-type";
import { SelectModule } from "primeng/select";
import { MaterialService } from "../../../../../shared/services/material.service";
import { MedidaService } from "../../../../../shared/services/medida.service";
import { TipoSetorService } from "../../../../../shared/services/tipo-setor.service";
import { FornecedorService } from "../../../../../shared/services/fornecedor.service";
import { Medida } from "../../../../../shared/models/medida";
import { Material } from "../../../../../shared/models/material";
import { TipoSetor } from "../../../../../shared/models/tipo-setor";
import { Fornecedor } from "../../../../../shared/models/fornecedor";
import { DialogModule } from "primeng/dialog";
import { DividerModule } from "primeng/divider";
import { SetorComDependencias } from "../../../../../shared/models/setor";

@Component({
    selector: "reactor-setor-formulario",
    templateUrl: "formulario-adicionar-setor.component.html",
    styleUrl: "formulario-adicionar-setor.component.css",
    imports: [InputTextModule, TableModule, InputNumberModule, DialogModule, SelectModule, CheckboxModule, ReactiveFormsModule, ButtonModule, DividerModule, FormsModule],
    providers: [
        MaterialService,
        MedidaService,
        TipoSetorService,
        FornecedorService
    ],
    standalone: true
})
export class FormularioSetorComponent implements OnInit {

    private medidasService = inject(MedidaService);
    private materialService = inject(MaterialService);
    private tipoSetorService = inject(TipoSetorService);
    private fornecedorService = inject(FornecedorService);

    ngOnInit(): void {
        this.medidasService.listarMedidas();
        this.materialService.listarMateriais();
        this.tipoSetorService.listarTodosOsTiposSetorSilent();
        this.fornecedorService.listarTodosOsFornecedores();
        this.medidas = this.medidasService.getMedidas();
        this.materiais = this.materialService.getMateriais();
        this.tiposSetor = this.tipoSetorService.getTiposSetores();
        this.fornecedores = this.fornecedorService.getFornecedores$();
        if (this.setorSendoEditado != null) {
            this.dependencias = this.setorSendoEditado()!.dependencias;
            this.formulario = ADICIONAR_SETOR_FORM(this.setorSendoEditado()!);
        }
    }
    
    public medidas = signal<Array<Medida> | null>(null);
    public materiais = signal<Array<Material> | null>(null);
    public tiposSetor = signal<Array<TipoSetor> | null>(null);
    public fornecedores = signal<Array<Fornecedor> | null>(null);

    public setorSendoEditado = input<SetorComDependencias | null>();

    public buttonStyle = input<FormularioSetorButton>();
    public getValue = output<object>();

    dialogVisivel = false;
    formularioMedida: { medida: Medida | null, material: Material | null, qtd: number } = {
        medida: null,
        material: null,
        qtd: 0
    }

    public formulario = ADICIONAR_SETOR_FORM();
    public dependencias: Array<{ medida: Medida | null, material: Material | null, qtd: number }> = [];

    abrirDialog() {
        this.limparFormularioMedida();
        this.dialogVisivel = true;
    }

    emitirFormulario() {
        const form = {
            ...this.formulario.value,
            dependencias: this.dependencias
        }
        this.getValue.emit(form);
    }

    limparFormularioMedida() {
        this.formularioMedida = {
            medida: null,
            material: null,
            qtd: 0
        }
    }

    fecharDialog() {
        this.limparFormularioMedida();
        this.dialogVisivel = false;
    }

    adicionarDependencia() {
        this.dependencias.push({...this.formularioMedida});
    }

    removerDependencia(index: number) {
        this.dependencias.splice(index, 1);
    }
}