# Online University Android App

This is an Android application for managing university-related data, including students, teachers, clubs, and classes. The app allows users to add, update, and list various entities like students, teachers, and events. It also includes functionality for managing the database using the **Room persistence library**.

## Features

- Add, update, and list students, teachers, and clubs.
- Manage classes and events.
- Database integration with **Room** for storing university-related data.
- User-friendly UI to handle administrative tasks.
- Support for various university management functionalities.

## Prerequisites

To run this project locally, make sure you have the following installed:

- [Android Studio](https://developer.android.com/studio) (latest version recommended)
- [Java JDK 11 or higher](https://adoptopenjdk.net/)
- Android SDK (comes with Android Studio)

## App Structure

### Main Activities:

- **AjoutEtudiant**: Activity to add a new student.
- **GestionEtudiant**: Activity to manage students.
- **ListeEtudiants**: Activity to list all students.
- **AjoutClub**: Activity to add a new club.
- **GestionClub**: Activity to manage clubs.
- **ListeClubs**: Activity to list all clubs.
- **AjoutClasse**: Activity to add a new class.
- **GestionClasse**: Activity to manage classes.

### Database

The app uses **Room** for database management, and the `MyDatabase` class is the singleton entry point for the database.

Entities like `Etudiant`, `Classe`, and `Club` are stored in the Room database and can be inserted, updated, or queried using their respective DAO interfaces (`EtudiantDao`, `ClasseDao`, `ClubDao`).

## Libraries & Technologies

- **Room**: For local database management.
- **AndroidX**: For modern Android app components.
- **Java**: The primary language used for this project.
- **XML**: For layout design.


