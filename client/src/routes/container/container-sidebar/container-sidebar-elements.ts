import { MenuItem } from "primeng/api";
import { RemoverUsuariosDialog } from "../../internal/usuarios/dialogs/remover-usuarios/remover-usuarios.component";
import { AlterarCargoDoUsuarioDialog } from "../../internal/usuarios/dialogs/alterar-cargo/altear-cargo-usuario.component";

export const CONTAINER_SIDEBAR_ELEMENTS = (
    abrirDialog: Function
): Array<MenuItem> => ([
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
                icon: "pi pi-list",
                routerLink: "usuarios-listar"
            },
            {
                label: "Remover",
                icon: "pi pi-user-minus",
                command: () => abrirDialog(
                    RemoverUsuariosDialog, "Remover um Usuário"
                )
            },
            {
                label: "Alterar Cargo",
                icon: "pi pi-crown",
                command: () => abrirDialog(
                    AlterarCargoDoUsuarioDialog, "Alterar Cargo de um Usuário"
                ),
            }
        ]
    },
    {
        label: "Fornecedores",
        items: [
            {
                label: "Listar",
                icon: "pi pi-list",
                routerLink: "fornecedores-listar"
            },
            {
                label: "Adicionar",
                icon: "pi pi-user-plus",
                routerLink: "fornecedores-adicionar"
            },
        ]
    },
    {
        label: "Funcionários",
        items: [
            {
                label: "Gerenciar",
                icon: "pi pi-list",
                routerLink: "funcionarios-gerenciar"
            }
        ]
    },
])