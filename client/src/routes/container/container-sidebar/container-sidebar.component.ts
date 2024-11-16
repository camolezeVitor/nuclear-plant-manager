import { Component } from "@angular/core";
import { MenuModule } from 'primeng/menu';
import { CONTAINER_SIDEBAR_ELEMENTS } from "./container-sidebar-elements";

@Component({
    selector: "reactor-container-sidebar",
    templateUrl: "container-sidebar.component.html",
    styleUrl: "container-sidebar.component.css",
    imports: [ MenuModule ],
    standalone: true,
})
export class ContainerSidebarComponent {
    public opcoesSidebar = CONTAINER_SIDEBAR_ELEMENTS;
}