import { Component } from "@angular/core";
import { OrganizationChartModule } from 'primeng/organizationchart';
import { CADEIA_DE_SUPRIMENTOS } from "./setor-cadeia-de-suprimentos-cadeia";

import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputTextModule } from "primeng/inputtext";
import { TagModule } from 'primeng/tag';
import { FieldsetModule } from 'primeng/fieldset';

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
        FieldsetModule
    ]
})
export class SetorCadeiaDeSuprimentosRoute {
    public data = CADEIA_DE_SUPRIMENTOS;
}