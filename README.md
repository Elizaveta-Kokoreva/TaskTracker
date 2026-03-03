# Task Tracker Backend

REST‑сервис для управления задачами, реализованный на Spring Boot.

## О проекте

**Task Tracker** — backend pet‑проект, демонстрирующий реализацию CRUD‑приложения с использованием многослойной архитектуры.

Приложение построено по принципу разделения ответственности:

```text
Controller → Service → Repository → Database
```

### Реализовано

* REST API
* Интеграция с базой данных
* Миграции схемы БД (Flyway)
* Валидация входных данных
* Централизованная обработка исключений
* Логирование
* Архитектура
* Слои приложения

---

## Архитектура

### Слои приложения

* **Controller** — обработка HTTP‑запросов
* **Service** — бизнес‑логика
* **Repository** — слой доступа к данным
* **Models** — модели и объекты передачи данных
* **Global Exception Handler** — централизованная обработка ошибок

---

## Функциональность

* Создание задачи
* Получение списка задач по фильтру
* Получение задачи по идентификатору
* Обновление задачи

---

## Технологический стек

### Backend

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA

### База данных

* PostgreSQL

### Сборка

* Maven

### Тестирование

* JUnit 5

---

## Структура проекта

```bash
TaskTracker/
├── src/main/java/com/example.demo
│   ├── controllers/
│   ├── services/
│   ├── repositories/
│   ├── models/
│   └── exceptions/
├── src/main/resources/
│   ├── application.yml
│   └── db/migration/
└── pom.xml
```

---

## API Endpoints

| Метод | Endpoint                 | Описание                         |
| ----- | ------------------------ | -------------------------------- |
| POST  | `/tasks`                 | Создать задачу                   |
| POST  | `/tasks/getTaskByFilter` | Получить список задач по фильтру |
| GET   | `/tasks/{id}`            | Получить задачу по ID            |
| PUT   | `/tasks/{id}`            | Обновить задачу                  |

---

## Пример запроса

```http
POST /tasks
Content-Type: application/json

{
  "name": "Изучить Spring Boot",
  "description": "Реализовать REST API",
  "status": "DONE",
  "assignee": 1
}
```

---

## Модель данных

```java
public class Task {

    private Long id;
    private String name;
    private String description;
    private Status status;
    private Long assignee;

}
```

---

## Тестирование

```bash
mvn test
```

Проект содержит unit‑тесты сервисного слоя и базовые тесты контроллеров.

