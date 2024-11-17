import { Component, Input, input } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { DividerModule } from "primeng/divider";
import { PopoverModule } from "primeng/popover";

@Component({
    selector: "reactor-vareta",
    templateUrl: "vareta.component.html",
    styleUrl: "vareta.component.css",
    standalone: true,
    imports: [PopoverModule, DividerModule, ButtonModule]
})
export class VaretaComponent {
    @Input() left: number = 0;
    @Input() top: number = 0;

    public seletorAtivo: boolean = false;
}