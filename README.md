# Task Management System

## Описание
Task Management System (Система управления задачами) — это REST API-приложение, которое позволяет создавать, редактировать, удалять и просматривать задачи.

Каждая задача содержит следующие свойства:
- **Заголовок**
- **Описание**
- **Статус** (например, "в ожидании", "в процессе", "завершено")
- **Приоритет** (например, "высокий", "средний", "низкий")
- **Комментарии**
- **Автор**
- **Исполнитель**

---

## Стек технологий
- **Java 21**
- **Spring Boot 3.4**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL**
- **MongoDB**
- **Redis**
- **Apache Kafka**
- **Lombok** 
- **Springdoc OpenAPI** (генерация Swagger-документации)
- **Maven** (сборка проекта)

---

## Установка и запуск проекта

### Требования
- Java 21
- Maven
- Git

### Шаги для запуска

1. **Клонировать репозиторий**
   ```bash
   git clone https://github.com/your-repo/task-management-system.git
   cd task-management-system
2. **Доступ к серверу После успешного запуска сервер будет доступен по адресу:**
   ```bash
   http://localhost:8080
3. **Собрать проект Используйте Maven для сборки проекта:**
   ```bash
   mvn clean install
4. **Запуск приложения в Docker Для запуска приложения в Docker, убедитесь, что у вас есть файл Dockerfile и docker-compose.yml в корневой директории проекта. Запустите контейнеры с помощью Docker Compose:**
   ```bash
   docker-compose up --build
5. **Swagger-документация Для просмотра API-документации откройте браузер и перейдите по следующему адресу:**
   ```bash
   http://localhost:8080/swagger-ui/index.html
