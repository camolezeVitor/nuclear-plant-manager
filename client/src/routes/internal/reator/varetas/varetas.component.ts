import { ChangeDetectorRef, Component, ElementRef, inject, signal } from "@angular/core";
import { VaretaComponent } from "./vareta/vareta.component";
import { PanelModule } from 'primeng/panel';
import { DividerModule } from "primeng/divider";
import { KnobModule } from 'primeng/knob';
import { Vareta } from "../../../../shared/models/vareta";
import { ReatorAndUsinaService } from "../../../../shared/services/reator.service";
import { POSICAO_DAS_VARETAS } from "./posicao-das-varetas";
@Component({
    selector: "reactor-varetas",
    templateUrl: "varetas.component.html",
    styleUrls: ["varetas.component.css"],
    imports: [VaretaComponent,PanelModule,DividerModule, KnobModule],
    standalone: true,
    
})
export class VaretasRoute {
    diameter = 325;
    divSize = 30;
    varetas = POSICAO_DAS_VARETAS;
    private cdr = inject(ChangeDetectorRef);

    private reatorAndUsinaService = inject(ReatorAndUsinaService);
    public listaDeVaretas = signal<Array<Vareta> | null>(null);
  
    constructor() {}
  
    ngAfterViewInit() {
        this.cdr.detectChanges();
        this.reatorAndUsinaService.listarTodasAsVaretas();
        this.listaDeVaretas = this.reatorAndUsinaService.varetas;
        setTimeout(() => {
            console.log(this.varetas);
        }, 5000);
    }
    
}