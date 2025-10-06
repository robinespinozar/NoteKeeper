<div align="center">

# 📝 NoteKeeper

### *Tu compañero digital para organizar ideas y tareas*

![Kotlin](https://img.shields.io/badge/Kotlin-1.8.10-purple?style=for-the-badge&logo=kotlin)
![Android](https://img.shields.io/badge/Android-27%2B-green?style=for-the-badge&logo=android)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.4.3-blue?style=for-the-badge&logo=jetpackcompose)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

[Features](#-features) • [Tech Stack](#-tech-stack) • [Architecture](#-arquitectura) • [Installation](#-instalación) • [Screenshots](#-capturas) • [Contributing](#-contribuir)

---

</div>

## 🎯 ¿Qué es NoteKeeper?

**NoteKeeper** es una aplicación Android moderna diseñada para **crear, organizar y gestionar notas y tareas** de manera eficiente. Construida con las últimas tecnologías de desarrollo Android, ofrece una experiencia fluida e intuitiva para que puedas capturar tus ideas en cualquier momento.

### 💡 Problema que resuelve

¿Cuántas veces has tenido una idea brillante y no tenías dónde anotarla? ¿O necesitabas organizar tus tareas diarias pero las apps existentes son muy complicadas? **NoteKeeper** te ofrece una solución simple, rápida y elegante para:

- ✅ **Capturar ideas** al instante sin complicaciones
- ✅ **Organizar tareas** de forma visual y clara
- ✅ **Sincronizar** tus notas en la nube (Firebase)
- ✅ **Acceder offline** a tus notas con Room Database
- ✅ **Disfrutar** de una interfaz moderna con Material Design 3

---

## ✨ Features

<table>
<tr>
<td width="50%">

### 📱 Core Features

- **Crear Notas**: Añade notas con título y contenido
- **Editar**: Modifica tus notas en cualquier momento
- **Eliminar**: Borra notas que ya no necesites
- **Listar**: Visualiza todas tus notas organizadas
- **Búsqueda**: Encuentra notas rápidamente
- **Modo Oscuro**: Protege tus ojos con tema dark

</td>
<td width="50%">

### 🚀 Technical Features

- **Offline First**: Funciona sin conexión
- **Sincronización en la nube**: Firebase Firestore
- **Autenticación**: Login seguro con Firebase Auth
- **Animaciones fluidas**: Lottie animations
- **Splash Screen**: Pantalla de carga profesional
- **Material Design 3**: UI moderna y elegante

</td>
</tr>
</table>

---

## 🛠️ Tech Stack

<div align="center">

### Lenguaje & Framework

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

### Architecture & Patterns

![MVVM](https://img.shields.io/badge/MVVM-Architecture-orange?style=for-the-badge)
![Clean Architecture](https://img.shields.io/badge/Clean%20Architecture-Layers-blue?style=for-the-badge)

### Dependency Injection

![Dagger Hilt](https://img.shields.io/badge/Dagger%20Hilt-2.46.1-green?style=for-the-badge)

### Database & Backend

![Room](https://img.shields.io/badge/Room-Database-red?style=for-the-badge)
![Firebase](https://img.shields.io/badge/Firebase-Firestore-orange?style=for-the-badge&logo=firebase)

### UI Components

![Material Design 3](https://img.shields.io/badge/Material%20Design%203-1.1.2-purple?style=for-the-badge)
![Lottie](https://img.shields.io/badge/Lottie-Animations-yellow?style=for-the-badge)

</div>

### 📦 Principales Dependencias

```gradle
// UI & Compose
Jetpack Compose BOM 2023.03.00
Material Design 3 (1.1.2)
Material Icons Extended
Lottie Compose (4.0.0)

// Architecture
Dagger Hilt (2.46.1)
Navigation Compose (2.7.4)
Lifecycle ViewModel & LiveData

// Database
Room (2.5.0)
Firebase Firestore
Firebase Auth

// Utils
Timber (logging)
DataStore Preferences
Accompanist (SystemUI, Pager)
```

---

## 🏗️ Arquitectura

NoteKeeper sigue el patrón **MVVM (Model-View-ViewModel)** con **Clean Architecture**, separando la aplicación en capas bien definidas:

```
📁 app/src/main/java/com/raerossi/notekeeper/
│
├── 📁 data/              # Capa de Datos
│   ├── local/           # Room Database
│   ├── remote/          # Firebase Firestore
│   ├── repository/      # Implementación de repositorios
│   └── model/           # Modelos de datos
│
├── 📁 domain/            # Capa de Dominio
│   ├── model/           # Entidades de negocio
│   ├── repository/      # Interfaces de repositorios
│   └── usecase/         # Casos de uso
│
├── 📁 presentation/      # Capa de Presentación
│   ├── screens/         # Pantallas Composables
│   ├── viewmodel/       # ViewModels
│   ├── navigation/      # Navegación
│   └── components/      # Componentes reutilizables
│
└── 📁 di/               # Dependency Injection (Hilt)
    └── modules/         # Módulos de Hilt
```

### 🔄 Flujo de Datos

```
┌─────────────┐      ┌──────────────┐      ┌────────────┐      ┌──────────┐
│     UI      │ ───> │  ViewModel   │ ───> │  UseCase   │ ───> │Repository│
│  (Compose)  │      │   (MVVM)     │      │  (Domain)  │      │  (Data)  │
└─────────────┘      └──────────────┘      └────────────┘      └──────────┘
       ↑                     │                                        │
       │                     ↓                                        ↓
       │              ┌──────────────┐                         ┌───────────┐
       └──────────────│   UI State   │                         │   Room    │
                      │  (LiveData)  │                         │ Firebase  │
                      └──────────────┘                         └───────────┘
```

---

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

| Herramienta | Versión Mínima | Propósito |
|------------|----------------|-----------|
| 🟢 **Android Studio** | Hedgehog 2023.1.1+ | IDE de desarrollo |
| ☕ **JDK** | 17 | Java Development Kit |
| 📱 **Android SDK** | API 27+ (Android 8.1) | SDK de Android |
| 🐘 **Gradle** | 8.0+ | Build system |
| 🔥 **Cuenta Firebase** | - | Backend y autenticación |

---

## 🚀 Instalación

### 1️⃣ Clonar el Repositorio

```bash
# Clona el proyecto
git clone https://github.com/robinespinozar/NoteKeeper.git

# Navega al directorio
cd NoteKeeper
```

### 2️⃣ Configurar Firebase

1. Crea un proyecto en [Firebase Console](https://console.firebase.google.com/)
2. Descarga el archivo `google-services.json`
3. Colócalo en `app/google-services.json`
4. Habilita:
   - ✅ Firebase Authentication (Email/Password)
   - ✅ Cloud Firestore

### 3️⃣ Sincronizar Dependencias

```bash
# Sincroniza las dependencias de Gradle
./gradlew build
```

### 4️⃣ Ejecutar la Aplicación

Abre el proyecto en **Android Studio** y:

- 🔴 Presiona el botón **Run** (Shift + F10)
- 📱 Selecciona un **emulador** o **dispositivo físico**
- ⏳ Espera a que la app se instale y ejecute

---

## 🔨 Compilación

### Compilar APK de Debug

```bash
./gradlew assembleDebug
```

📦 El APK estará en: `app/build/outputs/apk/debug/app-debug.apk`

### Compilar APK de Release

```bash
./gradlew assembleRelease
```

📦 El APK estará en: `app/build/outputs/apk/release/app-release.apk`

### Ejecutar Tests

```bash
# Tests unitarios
./gradlew test

# Tests de instrumentación
./gradlew connectedAndroidTest
```

---

## 📸 Capturas

> *Próximamente: Screenshots de la aplicación en acción*

---

## 🎨 Características Técnicas Destacadas

### 🎭 Jetpack Compose

Interfaz declarativa moderna que permite crear UI reactivas y fluidas con menos código.

```kotlin
@Composable
fun NoteCard(note: Note, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.titleLarge)
            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
```

### 💉 Dependency Injection con Hilt

Inyección de dependencias simplificada para una arquitectura limpia y testeable.

```kotlin
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {
    // ViewModel logic
}
```

### 🗄️ Persistencia con Room

Base de datos local robusta para trabajar offline-first.

```kotlin
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long
)
```

---

## 👨‍💻 Desarrollo

### 📝 Estilo de Código

Este proyecto sigue las [convenciones de Kotlin](https://kotlinlang.org/docs/coding-conventions.html):

- ✅ Nombres descriptivos en camelCase
- ✅ Indentación de 4 espacios
- ✅ Máximo 100 caracteres por línea
- ✅ Documentación con KDoc para funciones públicas

### 🔀 Git Workflow

```bash
# 1. Crea una rama para tu feature
git checkout -b feature/nueva-funcionalidad

# 2. Haz commits descriptivos
git commit -m "feat: add search functionality"

# 3. Push a tu rama
git push origin feature/nueva-funcionalidad

# 4. Crea un Pull Request en GitHub
```

### 📏 Conventional Commits

Usamos commits semánticos:

- `feat:` Nueva funcionalidad
- `fix:` Corrección de bugs
- `docs:` Cambios en documentación
- `style:` Formato, punto y coma, etc
- `refactor:` Refactorización de código
- `test:` Añadir o modificar tests
- `chore:` Mantenimiento

---

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Sigue estos pasos:

### 1️⃣ Fork el Proyecto

Haz clic en el botón **Fork** en la esquina superior derecha.

### 2️⃣ Crea tu Feature Branch

```bash
git checkout -b feature/AmazingFeature
```

### 3️⃣ Commit tus Cambios

```bash
git commit -m 'feat: Add some AmazingFeature'
```

### 4️⃣ Push a la Branch

```bash
git push origin feature/AmazingFeature
```

### 5️⃣ Abre un Pull Request

Ve a GitHub y crea un Pull Request detallando tus cambios.

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

## 👤 Autor

<div align="center">

### **Robin Espinoza Rossi**

[![GitHub](https://img.shields.io/badge/GitHub-@robinespinozar-181717?style=for-the-badge&logo=github)](https://github.com/robinespinozar)
[![Email](https://img.shields.io/badge/Email-Contact-red?style=for-the-badge&logo=gmail)](mailto:robinespinozar@example.com)

</div>

---

## 🙏 Agradecimientos

Este proyecto fue posible gracias a:

- 📚 [Android Developers](https://developer.android.com/) - Documentación oficial
- 🎨 [Material Design 3](https://m3.material.io/) - Guías de diseño
- 🔥 [Firebase](https://firebase.google.com/) - Backend as a Service
- 💜 [Kotlin](https://kotlinlang.org/) - Lenguaje de programación moderno
- 🚀 [Jetpack Compose](https://developer.android.com/jetpack/compose) - UI Toolkit
- 🏗️ [Dagger Hilt](https://dagger.dev/hilt/) - Dependency Injection
- 📦 [Room](https://developer.android.com/training/data-storage/room) - Database Library

---

<div align="center">

### ⭐ Si te gusta este proyecto, dale una estrella!

**Hecho con ❤️ y ☕ por Robin Espinoza**

[⬆ Volver arriba](#-notekeeper)

</div>