import { Component, input } from "@angular/core";


import { ButtonModule } from "primeng/button";
import { ToolbarModule } from 'primeng/toolbar';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from "primeng/inputtext";
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { IftaLabelModule } from 'primeng/iftalabel';
import { UsinaStatus } from "../../../shared/models/usina-status";

@Component({
    selector: "reactor-container-header",
    templateUrl: "container-header.component.html",
    styleUrl: "container-header.component.css",
    standalone: true,
    imports: [
        ToolbarModule,
        ButtonModule,
        IconFieldModule,
        InputTextModule,
        InputIconModule,
        InputGroupModule,
        InputGroupAddonModule,
        IftaLabelModule
    ]
})
export class ContainerHeaderComponent {
    public statusUsina = input<UsinaStatus | null>(null);
}