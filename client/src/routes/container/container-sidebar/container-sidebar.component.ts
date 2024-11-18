import { Component, Type } from "@angular/core";
import { MenuModule } from 'primeng/menu';
import { CONTAINER_SIDEBAR_ELEMENTS } from "./container-sidebar-elements";
import { DialogService, DynamicDialogModule, DynamicDialogRef } from 'primeng/dynamicdialog';

@Component({
    selector: "reactor-container-sidebar",
    templateUrl: "container-sidebar.component.html",
    styleUrl: "container-sidebar.component.css",
    imports: [ MenuModule, DynamicDialogModule ],
    providers: [ DialogService ],
    standalone: true,
})
export class ContainerSidebarComponent {
    public opcoesSidebar = CONTAINER_SIDEBAR_ELEMENTS(this.abrirDialog.bind(this));

    ref: DynamicDialogRef | undefined;
    
    constructor(private dialogService: DialogService) {}

    abrirDialog(component: Type<any>, header: string) {
        console.log("aqui??")
        this.ref = this.dialogService.open(component, {
            closable: true,
            header: header,
            modal: true,
            width: '50vw',
            height: '50vh'
        })
    }
}