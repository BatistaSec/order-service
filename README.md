# 🟩 Notification Service

Microserviço responsável por consumir eventos de pedidos publicados pelo **Order Service** via RabbitMQ e processar notificações.

> 📦 Este serviço faz parte do sistema [Order Processing System](https://github.com/BatistaSec/Order-messaging-system)


---

## 📌 Responsabilidades

- Consumir eventos da fila `order.created.queue`
- Processar mensagens recebidas via RabbitMQ
- Simular envio de notificações ao cliente

---

## 🧠 Tecnologias

- Java 17
- Spring Boot 4.0.6
- Spring AMQP / RabbitMQ
- Jackson (JSON)
- Lombok
- JUnit 5 + Mockito
- Testcontainers
- Docker
- GitHub Actions (CI)

---

## 🔄 Fluxo
Order Service → RabbitMQ (order.exchange) → order.created.queue → Notification Service

---

## ▶️ Como executar

### Com Docker (recomendado)

```bash
docker-compose up --build
```

| Serviço | URL |
|---|---|
| Notification Service | http://localhost:8081 |
| RabbitMQ Management | http://localhost:15672 |

### Sem Docker

```bash
# 1. Subir o RabbitMQ
docker run -d --name rabbitmq \
  -p 5672:5672 -p 15672:15672 \
  rabbitmq:3-management

# 2. Rodar a aplicação
./mvnw spring-boot:run
```

---

## 🧪 Testes

```bash
# Todos os testes
./mvnw verify

# Apenas unitários
./mvnw test -Dtest=OrderCreatedConsumerTest

# Apenas integração
./mvnw test -Dtest=OrderCreatedConsumerIntegrationTest
```

| Tipo | Ferramenta | O que testa |
|---|---|---|
| Unitário | JUnit 5 + Mockito | Consumer chama o NotificationService corretamente |
| Integração | Testcontainers | Mensagem publicada no RabbitMQ real é consumida |

---

## 🔁 CI/CD

Pipeline com **GitHub Actions** que roda automaticamente a cada push ou PR na `main`:

1. Roda todos os testes
2. Builda a imagem Docker

---

## 📂 Estrutura
src/main/java/com/jozo/notification/Service/

├── config/       # Configuração do RabbitMQ

├── consumer/     # Listener da fila

├── dto/          # Evento OrderCreatedEvent

└── service/      # Lógica de notificação

---

## 👨‍💻 Autor

João Batista — [GitHub](https://github.com/BatistaSec)
