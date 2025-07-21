# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a coding exam project where a book management API server is implemented using only Claude Code. The project creates a REST API with two endpoints for managing books.

## Technology Stack

- **Language**: Kotlin
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **ORM**: MyBatis
- **Build Tool**: Gradle (Kotlin DSL)
- **Testing**: JUnit5
- **Logging**: SLF4J + Logback
- **Documentation**: OpenAPI 3 (Swagger)

## API Endpoints

### GET /books
- Returns all book information as a list
- Response format:
```json
{
  "books": [
    {
      "id": "1",
      "title": "テスト駆動開発",
      "author": "Kent Beck",
      "publisher": "オーム社",
      "price": 3080
    }
  ]
}
```

### POST /books
- Registers new book information
- Request body format:
```json
{
  "title": "Clean Agile",
  "author": "Robert C. Martin",
  "publisher": "ドワンゴ",
  "price": 2640
}
```
- Returns 201 with Location header pointing to the new book

## Database Schema

The `books` table structure:
- `id` (integer, PRIMARY KEY)
- `title` (varchar(100))
- `author` (varchar(100))
- `publisher` (varchar(100))
- `price` (integer)

Uses `BOOK_ID_SEQ` sequence for auto-incrementing IDs.

## Development Setup

- API server and database run in Docker containers locally
- Database initialization is handled by Flyway migrations automatically
- Build with: `./gradlew build`
- Run with: `./gradlew bootRun`
- Start database: `docker-compose up -d`

## Project Structure

- `docs/` - API documentation and tech stack specifications
- `src/main/resources/db/migration/` - Flyway database migration scripts
- Source code structure needs to be established following Spring Boot conventions