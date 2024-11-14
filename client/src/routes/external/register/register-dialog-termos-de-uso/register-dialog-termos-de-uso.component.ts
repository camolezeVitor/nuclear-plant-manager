import { Component } from "@angular/core";
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from "primeng/button";
import { DividerModule } from 'primeng/divider';

@Component({
selector: "reactor-register-dialog-termos-de-uso",
    templateUrl: "register-dialog-termos-de-uso.component.html",
    styleUrl: "register-dialog-termos-de-uso.component.css",
    standalone: true,
    imports: [DialogModule, ButtonModule,DividerModule]
})
export class RegisterDialogTermosDeUsoComponent {
    public dialogVisivel: boolean = false;

    public abrirDialog() {
        this.dialogVisivel = true;
    }
}