import { inject, Inject, Injectable, OnInit, PLATFORM_ID, signal } from "@angular/core";
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { ENVIRONMENT } from "../../configs/environment";
import { isPlatformBrowser } from "@angular/common";
import { UsinaStatus } from "../models/usina-status";
import { TipoVareta } from "../enums/tipo-vareta.enum";
import { HttpClient } from "@angular/common/http";
import { Vareta } from "../models/vareta";
import { MessageService } from "primeng/api";
import { Material } from "../models/material";
import { FornecedoresVaretas } from "../models/fornecedor-varetas";
import { ProfundidadeDasVaretas } from "../models/profundidade-das-varetas";

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
    public fornecedoresDeVaretas = signal<FornecedoresVaretas | null>(null);
    public profundidadeDasVaretas = signal<ProfundidadeDasVaretas | null>(null);

    constructor(@Inject(PLATFORM_ID) private platformId: object) {}

    public initializeWebSocket(): void {
        if (isPlatformBrowser(this.platformId)) {
            this.socket$ = webSocket({
                url: `ws://26.177.123.161:8080/ws`,
                deserializer: (e) => e.data,
            });
    
            this.socket$.subscribe({
                next: (res) => {
                    const newStatus = JSON.parse(res) as UsinaStatus;
                    if (JSON.stringify(this.usinaStatus()) !== JSON.stringify(newStatus)) {
                        this.usinaStatus.set(newStatus);
                    }
                },
                error: (err) => console.error("WebSocket error", err),
            });
        }
    }

    public listarProfundidadeDasVaretas() {
        this.httpClient.get<ProfundidadeDasVaretas>(`${this.environment.backendUrl}/varetas/buscar-profundidade-vareta`).subscribe({
            next: (prof) => {
                this.profundidadeDasVaretas.set(prof);
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

    public listarFornecedoresDasVaretasDeControle() {
        this.httpClient.get<FornecedoresVaretas>(`${this.environment.backendUrl}/varetas/buscar-fornecedores-varetas`).subscribe({
            next: (lista) => {
                this.fornecedoresDeVaretas.set(lista);
            }, 
            error: () => {
                this.messageService.add({
                    severity: "Sla porra",
                    detail: "Ocorreu um erro ao alterar o tipo da vareta",
                    summary: "Jair volsornaor"
                })
            }
        })
    }

    public mudarFornecedorDoTipoVareta(tipoVaretaAlterado: string, idFornecedor: number) {
        return this.httpClient.put<void>(`${this.environment.backendUrl}/varetas/mudar-fornecedor-do-tipo-vareta/${idFornecedor}`, tipoVaretaAlterado);
    }

    public mudarProfundidadeDasVaretas(profundidade: ProfundidadeDasVaretas) {
        this.httpClient.put<void>(`${this.environment.backendUrl}/varetas/mudar-profundidade`, profundidade).subscribe({
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "ERRO AO ALTERAR"
                })
            }
        })
    }
}