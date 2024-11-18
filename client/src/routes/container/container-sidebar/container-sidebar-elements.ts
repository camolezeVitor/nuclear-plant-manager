import { MenuItem } from "primeng/api";

export const CONTAINER_SIDEBAR_ELEMENTS: Array<MenuItem> = [
    {
        label: "Reator",
        items: [
            {
                label: "Varetas",
                icon: "pi pi-sliders-v",
                routerLink: "reator-varetas"
            },
            {
                label: "Estado",
                icon: "pi pi-wave-pulse",
                routerLink: "reator-estado"
            },
            {
                label: "Gerenciar",
                icon: "pi pi-microchip",
                routerLink: 'reator-gerenciar'
            }
        ],
    },
    {
        label: "Setores",
        items: [
            {
                label: "Alterar",
                icon: "pi pi-pencil",
                routerLink: "setor-alterar"
            },
            {
                label: "Gerenciar",
                icon: "pi pi-sliders-v",
                routerLink: 'setor-gerenciar'
            },
            {
                label: "Cadeia de Suprimentos",
                icon: "pi pi-truck",
                routerLink: 'setor-cadeia-de-suprimentos'
            },
            {
                label: "Adicionar",
                icon: "pi pi-plus",
                routerLink: 'setor-adicionar'
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
    },
    {
        label: "Fornecedores",
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
        ]
    },
    {
        label: "Funcionários",
        items: [
            {
                label: "Gerenciar",
                icon: "pi pi-list"
            }
        ]
    },
]