import { ChangeDetectorRef, Component, ElementRef, inject } from "@angular/core";
import { VaretaComponent } from "./vareta/vareta.component";
import { PanelModule } from 'primeng/panel';
import { DividerModule } from "primeng/divider";
import { KnobModule } from 'primeng/knob';
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
    varetas: { left: number; top: number }[] = [];
    private cdr = inject(ChangeDetectorRef);
    private elRef = inject(ElementRef);
  
    constructor() {}
  
    ngAfterViewInit() {
        this.criarCirculo();
        this.cdr.detectChanges();
    }
  
    criarCirculo() {
        const radius = this.diameter / 2;
        const centerX = this.diameter / 2;
        const centerY = this.diameter / 2;

        for (let x = 0; x < this.diameter; x += this.divSize) {
            for (let y = 0; y < this.diameter; y += this.divSize) {
                const distance = Math.sqrt((x - centerX) ** 2 + (y - centerY) ** 2);
        
                if (distance <= radius) {
                    this.varetas.push({ left: x, top: y});
                }
            }
        }

        this.varetas = this.varetas.map(v => ({left: v.left + 390, top: v.top + 240}))
    }
}