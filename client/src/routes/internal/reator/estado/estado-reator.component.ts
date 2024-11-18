import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { MessageService } from "primeng/api";
import { ChartModule } from "primeng/chart"
import { PanelModule } from "primeng/panel";

@Component({
    selector: "reactor-estado",
    templateUrl: "estado-reator.component.html",
    styleUrl: "estado-reator.component.css",
    standalone: true,
    imports: [ChartModule, CommonModule, PanelModule],
    providers: [MessageService]
})
export class EstadoReatorRoute {
    reatorStatus = 'Operando Estável';
    statusColor = 'green';
  
    lineChartData = {
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
      datasets: [{ label: 'Produção (GW)', data: [1, 1.2, 1.1, 1.3, 1.5], borderColor: '#4ED000', fill: false }]
    };
    
    barChartData = {
      labels: ['Reator A', 'Reator B', 'Reator C'],
      datasets: [{ label: 'Capacidade (%)', data: [80, 90, 70], backgroundColor: '#4ED000' }]
    };
    
    pieChartData = {
      labels: ['Combustível', 'Moderação', 'Controle'],
      datasets: [{ data: [40, 30, 30], backgroundColor: ['#4ED000', '#FFD700', '#FF4500'] }]
    };
    
    polarChartData = {
      labels: ['Temperatura', 'Pressão', 'Velocidade', 'Produção'],
      datasets: [{ data: [320, 100, 75, 1.2], backgroundColor: ['#4ED000', '#FFD700', '#FF4500', '#1E90FF'] }]
    };
  
    constructor(private messageService: MessageService) {
      this.checkReactorStatus();
    }
  
    checkReactorStatus() {
      setTimeout(() => {
        this.reatorStatus = 'Alerta de Aquecimento!';
        this.statusColor = 'red';
        this.messageService.add({
          severity: 'warn',
          summary: 'Atenção',
          detail: 'A temperatura do núcleo excedeu os limites recomendados!',
        });
      }, 5000); // Simula alteração do status após 5 segundos
    }
}