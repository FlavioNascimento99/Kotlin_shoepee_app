# 🛍️ Shoepee
_Aplicação de loja para dispositivos mobile de Sistema Operacional Android_

## 💡 Introdução
`A Fazer`

## 🗺️ UML do Projeto
[**🧠 Link de mapa lógico no Excalidraw 🖌️**]()
![UML](https://github.com/user-attachments/assets/2eb36f89-89f7-48de-9e1a-3817088c2d99)

## 🤖 Prototipo Figma
[🌏**Link de interface Figma**🎨](https://www.figma.com/design/2hMOdfr9efI621MKYYTabq/Shoepee-Prototype?node-id=0-1&p=f&t=NtROQv8dYVv8bcpR-0)


## 🎲 Tabela de Entidades
#### Cliente (`Client`)

| Campo               | Tipo               | Descrição                           |
|---------------------|--------------------|-------------------------------------|
| `id`                | `String`           | ID do cliente                       |
| `name`              | `String`           | Nome do cliente                     |
| `age`               | `Int`              | Idade do cliente                    |
| `cpf`               | `String`           | CPF do cliente                      |
| `email`             | `String`           | Email do cliente                    |
| `phone`             | `String`           | Telefone do cliente                 |
| `address`           | `Address`          | Endereço do cliente                 |
| `buyOrderHistory`   | `List<BuyOrder>`   | Histórico de pedidos do cliente     |

#### Endereço (`Address`)

| Campo               | Tipo               | Descrição                           |
|---------------------|--------------------|-------------------------------------|
| `id`                | `String`           | ID do endereço                      |
| `street`            | `String`           | Nome da rua                         |
| `number`            | `Int`              | Número do endereço                  |
| `complement`        | `String`           | Complemento do endereço             |
| `neighborhood`      | `String`           | Bairro                              |
| `city`              | `String`           | Cidade                              |
| `state`             | `String`           | Estado                              |
| `cep`               | `String`           | CEP                                 |

#### Login do Usuário (`User`)

| Campo               | Tipo               | Descrição                           |
|---------------------|--------------------|-------------------------------------|
| `id`                | `String`           | ID do login                         |
| `name`              | `String`           | Nome do usuário                     |
| `login`             | `String`           | Login do usuário                    |
| `password`          | `String`           | Senha do usuário                    |
| `type`              | `String?`          | Tipo de usuário                     |
| `status`            | `String?`          | Status do usuário                   |
| `dateRegister`      | `LocalDateTime`    | Data de registro                    |
| `dateLastUpdate`    | `LocalDateTime?`   | Data da última atualização          |

#### Pedido de Compra (`BuyOrder`)

| Campo               | Tipo               | Descrição                           |
|---------------------|--------------------|-------------------------------------|
| `id`                | `String`           | ID do pedido                        |
| `client`            | `Client`           | Cliente que fez o pedido            |
| `items`             | `List<Item>`       | Itens do pedido                     |
| `totalValue`        | `Double`           | Valor total do pedido               |
| `dateBuy`           | `LocalDateTime`    | Data da compra                      |
| `status`            | `String`           | Status do pedido                    |

#### Item (`Item`)

| Campo               | Tipo               | Descrição                           |
|---------------------|--------------------|-------------------------------------|
| `id`                | `String`           | ID do item                          |
| `product`           | `Product`          | Produto associado                   |
| `quantity`          | `Int`              | Quantidade do item                  |
| `pricePerUnit`      | `Double`           | Preço por unidade                   |

#### Produto (`Product`)

| Campo               | Tipo               | Descrição                           |
|---------------------|--------------------|-------------------------------------|
| `id`                | `String`           | ID do produto                       |
| `name`              | `String`           | Nome do produto                     |
| `description`       | `String`           | Descrição do produto                |
| `type`              | `String`           | Tipo do produto                     |
| `brand`             | `String`           | Marca do produto                    |
| `size`              | `String`           | Tamanho do produto                  |
| `color`             | `String`           | Cor do produto                      |
| `price`             | `Double`           | Preço do produto                    |
| `quantity`          | `Int`              | Quantidade em estoque               |

## Relacionamentos entre entidades
`Cliente <-> Endereco`: Um cliente possui um endereço `(relação 1 para 1)`.

`Cliente <-> OrdemDeCompra`: Um cliente pode ter várias ordens de compra `(relação 1 para muitos)`.

`OrdemDeCompra <-> Item`: Uma ordem de compra possui vários itens `(relação 1 para muitos)`.

`Item <-> Produto`: Um item se refere a um produto específico `(relação 1 para 1)`.

`Funcionario <-> OrdemDeCompra`: Um funcionário pode ser responsável por várias ordens de compra `(relação 1 para muitos)`.
