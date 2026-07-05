# 🛒 Sistema de Compras

Sistema web desarrollado con **Spring Boot**, **Thymeleaf** y **PostgreSQL** para la gestión de compras de una organización.

Este proyecto fue desarrollado como parte de la asignatura **Contabilidad Empresarial / Ingeniería de Software** en UNAPEC.

---

## 📋 Funcionalidades

- Dashboard administrativo
- Gestión de Departamentos
- Gestión de Proveedores
- Gestión de Unidades de Medida
- Gestión de Artículos
- Gestión de Órdenes de Compra
- Base de datos PostgreSQL
- Interfaz web con Thymeleaf y Bootstrap

---

## 🛠 Tecnologías utilizadas

- Java 21
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Thymeleaf
- Bootstrap 5
- HTML5
- CSS3
- Maven

---

## 📂 Estructura del proyecto

```
src
├── controller
├── entity
├── repository
├── service
├── resources
│   ├── static
│   └── templates
```

---

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/DylanGomez99/sistema-compras-api.git
```

### 2. Abrir el proyecto

Importar el proyecto como **Maven Project** desde IntelliJ IDEA.

### 3. Configurar PostgreSQL

Crear una base de datos:

```sql
CREATE DATABASE sistema_compras;
```

Modificar el archivo:

```
src/main/resources/application.properties
```

con las credenciales correspondientes.

### 4. Ejecutar

Ejecutar la clase:

```
SistemaComprasApiApplication.java
```

La aplicación estará disponible en:

```
http://localhost:8080
```

---

## 📌 Módulos implementados

- ✅ Dashboard
- ✅ Departamentos
- ✅ Proveedores
- ✅ Unidades de Medida
- ✅ Artículos
- ✅ Órdenes de Compra

---

## 👨‍💻 Autores

- Dilan Gómez
- Axel Grullon
- Anthony Liriano
---
