# BiblioTech

## 1. O projeto

A BiblioTech é um sistema de gerenciamento de biblioteca pensado para facilitar o controle dos livros e dos empréstimos. O sistema atende principalmente leitores e bibliotecários, ajudando a consultar a disponibilidade dos livros, registrar empréstimos e realizar devoluções.

O problema que o sistema busca resolver é a organização das informações da biblioteca, tornando mais simples saber quais livros estão disponíveis e quais estão emprestados.

## 2. Histórias de usuário

| Código | História de usuário |
|---|---|
| HU01 | Como leitor, quero consultar a disponibilidade de um livro, para saber se posso pegá-lo emprestado sem ir até o balcão. |
| HU02 | Como leitor, quero devolver um livro, para não ficar com pendência na biblioteca. |
| HU03 | Como bibliotecário, quero registrar um empréstimo, para saber quem está com cada exemplar. |
| HU04 | Como bibliotecário, quero cadastrar um livro novo, para que ele possa ser encontrado no sistema. |
| HU05 | Como bibliotecário, quero ver os empréstimos atrasados, para cobrar a devolução. |
| HU06 | Como leitor, quero reservar um livro que esteja emprestado, para poder utilizá-lo quando estiver disponível. |

## 3. Requisitos

### Requisitos funcionais

| Código | Requisito funcional | Veio da |
|---|---|---|
| RF01 | O sistema deve permitir que o bibliotecário cadastre um livro no acervo. | HU04 |
| RF02 | O sistema deve permitir que o bibliotecário cadastre um leitor. | Regra da biblioteca |
| RF03 | O sistema deve permitir que o leitor consulte a disponibilidade de um livro. | HU01 |
| RF04 | O sistema deve permitir que o bibliotecário registre a devolução de um livro. | HU02 |
| RF05 | O sistema deve permitir que o bibliotecário registre o empréstimo de um livro. | HU03 |
| RF06 | O sistema deve permitir que o leitor reserve um livro que esteja emprestado. | HU06 |

### Requisitos não funcionais

| Código | Requisito não funcional |
|---|---|
| RNF01 | A consulta de disponibilidade deve responder em menos de 3 segundos. |
| RNF02 | Somente usuários identificados como bibliotecários podem alterar o acervo. |

## 4. Diagramas

### Diagrama de casos de uso

![Diagrama de casos de uso do BiblioTech](docs/casos-de-uso.svg)

### Diagrama de classes

![Diagrama de classes do BiblioTech](docs/classes.svg)