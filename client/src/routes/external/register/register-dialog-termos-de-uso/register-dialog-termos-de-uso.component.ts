import { Component, input, InputSignal, output, OutputEmitterRef } from "@angular/core";
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from "primeng/button";
import { DividerModule } from 'primeng/divider';
import { CondicaoDeAceitacaoDosTermos } from "../enums/condicao-termos";

@Component({
selector: "reactor-register-dialog-termos-de-uso",
    templateUrl: "register-dialog-termos-de-uso.component.html",
    styleUrl: "register-dialog-termos-de-uso.component.css",
    standalone: true,
    imports: [DialogModule, ButtonModule,DividerModule]
})
export class RegisterDialogTermosDeUsoComponent {
    public dialogVisivel: boolean = false;
    public estadoCondicaoAceitacao: InputSignal<boolean | undefined> = input<boolean>();
    public condicaoAceitacaoEmmit: OutputEmitterRef<CondicaoDeAceitacaoDosTermos> = output<CondicaoDeAceitacaoDosTermos>();

    public abrirDialog() {
        this.dialogVisivel = true;
    }

    public emitirEstadoDeAceitacaoEFecharODialog(estado: CondicaoDeAceitacaoDosTermos) {
        this.condicaoAceitacaoEmmit.emit(estado);
        this.dialogVisivel = false;
    }
}