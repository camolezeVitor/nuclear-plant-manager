import { MenuItem } from "primeng/api";

export const CONTAINER_SIDEBAR_ELEMENTS: Array<MenuItem> = [
    {
        label: "Reator",
        items: [
            {
                label: "Varetas",
                icon: "pi pi-sliders-v"
            },
            {
                label: "Estado",
                icon: "pi pi-wave-pulse"
            },
            {
                label: "Gerenciar",
                icon: "pi pi-microchip"
            },
            {
                label: "Log",
                icon: "pi pi-list-check",
            }
        ],
    },
    {
        label: "Setores",
        items: [
            {
                label: "Visualizar",
                icon: "pi pi-eye"
            },
            {
                label: "Alterar",
                icon: "pi pi-pencil"
            },
            {
                label: "Gerenciar",
                icon: "pi pi-sliders-v"
            },
            {
                label: "Cadeia de Suprimentos",
                icon: "pi pi-truck",
                routerLink: 'setor-cadeia-de-suprimentos'
            },
            {
                label: "Adicionar",
                icon: "pi pi-plus"
            }
        ]
    },
    {
        label: "Usuários",
        items: [
            {
                label: "Listar",
                icon: "pi pi-list"
            },
            {
                label: "Remover",
                icon: "pi pi-user-minus"
            },
            {
                label: "Adicionar",
                icon: "pi pi-user-plus"
            },
            {
                label: "Alterar Cargo",
                icon: "pi pi-crown"
            }
        ]
    }
]