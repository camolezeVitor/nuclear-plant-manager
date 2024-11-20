import { Component } from "@angular/core";
import { OrganizationChartModule } from 'primeng/organizationchart';

import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputTextModule } from "primeng/inputtext";
import { TagModule } from 'primeng/tag';
import { FieldsetModule } from 'primeng/fieldset';
import { PopoverModule } from "primeng/popover";
import { CommonModule } from "@angular/common";
import { AccordionModule } from 'primeng/accordion';
import { DividerModule } from "primeng/divider";

@Component({
    selector: "reactor-setor-cadeia-de-suprimentos",
    templateUrl: "setor-cadeia-de-suprimentos.component.html",
    styleUrl: "setor-cadeia-de-suprimentos.component.css",
    standalone: true,
    imports: [
        OrganizationChartModule,
        InputGroupModule,
        InputGroupAddonModule,
        InputTextModule,
        TagModule,
        FieldsetModule,
        PopoverModule,
        CommonModule,
        AccordionModule,
        DividerModule
    ]
})
export class SetorCadeiaDeSuprimentosRoute {
}