# 📝 AppNotas

**AppNotas** es una aplicación sencilla y funcional desarrollada para Android que permite crear, editar y eliminar notas de forma rápida. Está diseñada como un proyecto introductorio para aprender los fundamentos del desarrollo móvil usando **Java**, **Android Studio** y **RecyclerView**.

---

## 📌 Características principales

### ✔ Crear notas
El usuario puede agregar una nueva nota escribiendo un título y un contenido básicos.

### ✔ Editar notas
Al seleccionar una nota en la lista, se abre una pantalla de edición donde es posible modificar su título.

### ✔ Eliminar notas con swipe
La aplicación permite eliminar una nota deslizando hacia la derecha o izquierda.

### ✔ Guardado persistente
Todas las notas se almacenan usando **SharedPreferences** en formato JSON, evitando perder información al cerrar la app.

### ✔ Interfaz limpia y minimalista
Utiliza Material Design, un botón flotante y tarjetas para mostrar cada nota de forma clara.

---

## 🛠️ Tecnologías utilizadas

- **Java**
- **Android Studio**
- **Android SDK**
- **RecyclerView**
- **ItemTouchHelper** (para eliminar notas con swipe)
- **SharedPreferences** (para guardar datos)
- **Material Design**

---


## 📂 Estructura del proyecto

```
app/
├── java/
│   └── com.rhsoft.appnotas/
│       ├── MainActivity.java
│       ├── AddNoteActivity.java
│       ├── EditNoteActivity.java
│       ├── NotasAdapter.java
│       └── Nota.java
│
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_add_note.xml
    │   ├── activity_edit_note.xml
    │   └── item_nota.xml
    ├── drawable/
    │   ├── boton_fondo.xml
    │   └── boton_fondo_sec.xml
    └── values/
        ├── colors.xml
        └── themes.xml
```


---

## 🚀 Instalación y ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/AppNotas.git
2. Abrir el proyecto en Android Studio.

3. Esperar a que Gradle sincronice.

4. Ejecutar en un emulador o dispositivo físico.

   🧩 Objetivo del proyecto

El propósito de este proyecto es:

Practicar el uso de RecyclerView y adaptadores.

Manejar actividades usando startActivityForResult.

Implementar almacenamiento local sin base de datos.

Desarrollar lógica CRUD básica (Crear, Leer, Actualizar, Eliminar).

📄 Licencia

Este proyecto es completamente libre para fines educativos y personales.
