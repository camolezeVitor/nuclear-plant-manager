import { Component, inject, Input, input } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { DividerModule } from "primeng/divider";
import { PopoverModule } from "primeng/popover";
import { ReatorAndUsinaService } from "../../../../../shared/services/reator.service";
import { TipoVareta, TipoVaretaControle } from "../../../../../shared/enums/tipo-vareta.enum";
import { Vareta } from "../../../../../shared/models/vareta";
import { CommonModule } from "@angular/common";

@Component({
    selector: "reactor-vareta",
    templateUrl: "vareta.component.html",
    styleUrl: "vareta.component.css",
    standalone: true,
    imports: [PopoverModule, DividerModule, ButtonModule, CommonModule]
})
export class VaretaComponent {
    vareta = input<Vareta>();
    @Input() left: number = 0;
    @Input() top: number = 0;

    private reatorAndUsinaService = inject(ReatorAndUsinaService);

    public seletorAtivo: boolean = false;

    alterarTipoDeVareta(tipoVareta: TipoVareta, subTipo?: TipoVaretaControle) {
        this.reatorAndUsinaService.alterarTipoDaVareta(this.vareta()!.id, tipoVareta, subTipo)
    }
}