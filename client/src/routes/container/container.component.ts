import { AfterViewInit, ChangeDetectionStrategy, Component, inject, OnInit, signal } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ContainerHeaderComponent } from "./container-header/container-header.component";
import { ContainerSidebarComponent } from "./container-sidebar/container-sidebar.component";
import { LoginService } from "../../shared/security/logged.service";
import { ReatorAndUsinaService } from "../../shared/services/reator.service";
import { UsinaStatus } from "../../shared/models/usina-status";

@Component({
    selector: "reactor-container",
    templateUrl: "container.component.html",
    styleUrl: "container.component.css",
    imports: [
        RouterModule,
        ContainerHeaderComponent,
        ContainerSidebarComponent,
    ],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ContainerRoute implements OnInit {
    public reatorAndUsinaService = inject(ReatorAndUsinaService);
    public usinaStatus = signal<UsinaStatus | null>(null); 

    ngOnInit(): void {
        this.reatorAndUsinaService.initializeWebSocket();
        this.usinaStatus = this.reatorAndUsinaService.usinaStatus;
    }
}