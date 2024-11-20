import { inject, Inject, Injectable, OnInit, PLATFORM_ID, signal } from "@angular/core";
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { ENVIRONMENT } from "../../configs/environment";
import { isPlatformBrowser } from "@angular/common";
import { UsinaStatus } from "../models/usina-status";
import { TipoVareta } from "../enums/tipo-vareta.enum";
import { HttpClient } from "@angular/common/http";
import { Vareta } from "../models/vareta";
import { MessageService } from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class ReatorAndUsinaService {
    private environment = ENVIRONMENT;
    public httpClient = inject(HttpClient);
    private messageService = inject(MessageService);
    private socket$: WebSocketSubject<any> | null = null;
    public usinaStatus = signal<UsinaStatus | null>(null);
    public varetas = signal<Array<Vareta> | null>(null);

    constructor(@Inject(PLATFORM_ID) private platformId: object) {}

    public initializeWebSocket(): void {
        if (isPlatformBrowser(this.platformId)) {
            this.socket$ = webSocket({
                url: `ws://26.177.123.161:8080/ws`,
                deserializer: (e) => e.data,
            });

            this.socket$.subscribe({
                next: (res) => {
                    this.usinaStatus.set(JSON.parse(res) as UsinaStatus);
                },
                error: (err) => {
                    console.error("erro!", err);
                }
            })
        }
    }

    public listarTodasAsVaretas() {
        this.httpClient.get<Array<Vareta>>(`${this.environment.backendUrl}/varetas/buscar-todas`).subscribe({
            next: (lista) => {
                this.varetas.set(lista);
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    detail: "AEU NÃO GUENTO MAI :((((((",
                    summary: "Jair volsornaor"
                })
            }
        })
    }

    public alterarTipoDaVareta(idVareta: number, tipoVareta: TipoVareta, tipoSecundarioDaVareta?: string) {
        const alterarTipoVareta = {
            tipoDaVareta: tipoVareta,
            tipoSecundario: tipoSecundarioDaVareta ?? null
        }
        return this.httpClient.put<void>(`${this.environment.backendUrl}/varetas/mudar-tipo-vareta/${idVareta}`, alterarTipoVareta).subscribe({
            next: () => {
                this.messageService.add({
                    severity: "success",
                    detail: "Tipo alterado com sucesso!",
                    summary: "Jair volsornaor"
                })
                this.listarTodasAsVaretas();
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    detail: "Ocorreu um erro ao alterar o tipo da vareta",
                    summary: "Jair volsornaor"
                })
            }
        })
    }
}