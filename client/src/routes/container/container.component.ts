import { AfterViewInit, Component, inject } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ContainerHeaderComponent } from "./container-header/container-header.component";
import { ContainerSidebarComponent } from "./container-sidebar/container-sidebar.component";
import { LoginService } from "../../shared/security/logged.service";

@Component({
    selector: "reactor-container",
    templateUrl: "container.component.html",
    styleUrl: "container.component.css",
    imports: [
        RouterModule,
        ContainerHeaderComponent,
        ContainerSidebarComponent,
    ],
    standalone: true
})
export class ContainerRoute {}