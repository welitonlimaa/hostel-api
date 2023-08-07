# API de Reservas para Hostel

Esta é uma API para gerenciar reservas em um hostel de temporada. Ela permite que os hóspedes façam reservas para estadias em datas específicas, com informações sobre o hóspede e a quantidade de pessoas.

## Tecnologias

- Spring Boot
- H2 Database

## Endpoints

### Criar uma Reserva

- Método: POST
- Endpoint: `/reservas`
- Corpo da solicitação (JSON):

```json
{
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4
}
```
* Resposta (JSON):
```json
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}

```

### Obter todas as Reservas
- Método: GET
- Endpoint: /reservas
* Resposta (JSON):
```json
[
    {
        "id": 1,
        "nomeHospede": "Fulano de Tal",
        "dataInicio": "2023-08-10",
        "dataFim": "2023-08-15",
        "quantidadePessoas": 4,
        "status": "CONFIRMADA"
    },
    {
        "id": 2,
        "nomeHospede": "Ciclano da Silva",
        "dataInicio": "2023-09-01",
        "dataFim": "2023-09-05",
        "quantidadePessoas": 2,
        "status": "PENDENTE"
    },
]
```

### Obter uma Reserva Específica por ID
- Método: GET
- Endpoint: /reservas/{id}
* Resposta (JSON):

```json
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```
### Atualizar uma Reserva Existente
- Método: PUT
- Endpoint: /reservas/{id}
- Corpo da solicitação (JSON):

```json
{
    "nomeHospede": "Fulano da Silva",
    "dataInicio": "2023-08-12",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 5,
    "status": "PENDENTE"
}
```
### Cancelar uma Reserva
- Método: DELETE
- Endpoint: /reservas/{id}/cancelar
- Resposta (JSON):
```json
{
    "id": 1,
    "nomeHospede": "Fulano da Silva",
    "dataInicio": "2023-08-12",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 5,
    "status": "CANCELADA"
}
```
### Obter Reservas por Status

Obtém uma lista de reservas com base no status fornecido.

- Método: GET
- Endpoint: `/reservas/status`
  
```http
GET /reservas/status?status=confirmada
```

