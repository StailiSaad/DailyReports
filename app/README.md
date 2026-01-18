 
```markdown
# Daily Reports - Mini-Projet Android CC3

**Application de génération automatique de rapports journaliers**

---

## 1. Présentation

Cette application Android permet à une entreprise de services numériques (environ 50 employés) d'automatiser la génération et la gestion des rapports d’activité journaliers.  

**Problématiques adressées :**  
- Oublis fréquents dans la rédaction des rapports journaliers  
- Absence de standardisation du format  
- Perte de temps sur une tâche répétitive  
- Difficulté d’accès à l’historique des rapports  

**Objectifs :**  
- Génération automatique quotidienne des rapports  
- Exécution des tâches en arrière-plan via WorkManager  
- Notifications pour informer l’utilisateur  
- Consultation, modification et partage des rapports  

---

## 2. Fonctionnalités principales

- **EF1 :** Choix du format de rapport (PDF, TXT)  
- **EF2 :** Définition de l’heure de génération automatique  
- **EF3 :** Gestion des permissions nécessaires  
- **EF4 :** Tâches en arrière-plan avec WorkManager  
- **EF5 :** Rapport horodaté et sauvegardé localement  
- **EF6 :** Notification à la génération du rapport  
- **EF7 :** Consultation et modification des rapports  
- **EF8 :** Partage des rapports via d’autres applications  

---

## 3. Architecture

Le projet suit l’architecture **MVVM** (Model-View-ViewModel) :  

```

* Model       : Report, ReportDatabase, ReportDao
* View        : Activities (MainActivity, SettingsActivity, ReportListActivity, ReportDetailActivity)
* ViewModel   : ReportViewModel
* Utils       : FileGenerator, NotificationHelper, PreferencesManager
* Worker      : ReportWorker
* DI          : Hilt DatabaseModule

````

**WorkManager** est utilisé pour l’exécution périodique en arrière-plan, et **Hilt** pour l’injection de dépendances.

---

## 4. Extraits de code

### Génération PDF (`FileGenerator.kt`)
```kotlin
fun generatePdf(ctx: Context, content: String): String? {
    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas
    val paint = Paint().apply { textSize = 16f; isAntiAlias = true }
    var y = 50f
    content.split("\n").forEach { line ->
        canvas.drawText(line, 40f, y, paint)
        y += paint.textSize + 10
    }
    pdfDocument.finishPage(page)
    val file = File(ctx.filesDir, "report.pdf")
    pdfDocument.writeTo(FileOutputStream(file))
    pdfDocument.close()
    return file.absolutePath
}
````

### Worker (`ReportWorker.kt`)

```kotlin
class ReportWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        val prefs = PreferencesManager(applicationContext)
        val content = prefs.tasks().joinToString("\n- ", "Tâches du jour:\n- ")
        val filePath = FileGenerator.generatePdf(applicationContext, content) ?: return Result.failure()
        ReportDatabase.get(applicationContext).reportDao().insert(
            Report("Rapport Journalier", content, prefs.format(), filePath)
        )
        NotificationHelper.show(applicationContext)
        return Result.success()
    }
}
```

---

## 5. Structure du projet

```
dailyreports/
├─ app/src/main/java/com/example/dailyreports/
│  ├─ data/           # Room entities, DAO, Database
│  ├─ utils/          # PDF generation, notifications, preferences
│  ├─ worker/         # WorkManager worker
│  ├─ ui/             # Activities (Main, Settings, ReportList, ReportDetail)
│  ├─ viewmodel/      # ViewModel
│  ├─ di/             # Hilt modules
│  ├─ DailyReportsApplication.kt
│  └─ MainActivity.kt
├─ res/               # Layouts, drawables, values
├─ AndroidManifest.xml
├─ build.gradle
└─ settings.gradle
```

---

## 6. Installation et exécution

1. Cloner le projet :

```bash
git clone https://github.com/votre-utilisateur/dailyreports.git
```

2. Ouvrir le projet avec **Android Studio**
3. Synchroniser les dépendances Gradle
4. Exécuter sur un émulateur ou appareil Android réel (API 21+)
5. Configurer les tâches et l’heure dans **SettingsActivity**
6. Appuyer sur **Start** dans **MainActivity** pour programmer le rapport

---

## 7. Technologies utilisées

* Kotlin
* Android SDK (API 21+)
* Room Database
* WorkManager
* Hilt DI
* EasyPermissions
* PDF generation (android.graphics.pdf.PdfDocument)
* MVVM architecture
* JUnit et Espresso pour tests

---

## 8. Auteurs

* **Staili Saad** : Développement MVVM, PDF, WorkManager, tests unitaires
* **Soufiane Guizaoui** : UI, permissions, tests UI Espresso

---

## 9. Remarques

* Les rapports sont stockés localement dans le dossier de fichiers internes (`filesDir`)
* Les notifications ne s’affichent que si les permissions sont accordées (Android 13+)
* Le projet est conforme aux exigences professionnelles : cahier des charges, diagrammes UML, PERT et GANTT.

---

```
 
