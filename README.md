# WarCard Game - Android
This project is an Android implementation of the classic card game **War**. I originally built this game as my very first 
Android development project, and now that my skills have grown, I am rebuilding it with a stronger focus on clean architecture,
and modern Android practices, and scalability. 

**You can look at my developer branch for the latest app updates.**

## App-Design
The initial app design was made using Figma: 
<img width="4885" height="3788" alt="War Card Game_Design" src="https://github.com/user-attachments/assets/e08e5d26-a059-4e46-a451-d53cfa260ef9" />
The goal of the new version is to improve: 
- Application architecture with proper MVVM principles
- Coroutine usage for asynchronous operation
- Navigation using Navigation3.

## Initial Features in the first game
- CPU player mode
- Full 54-card deck(including Jokers) designed in Figma.
- Interactive War mode
- Sound effects
- Animations (YoYo animation library)
- Real-time score tracking
- Winner screen
- Replay / New game options
- Buttons being disabled when tie, joker and win

## Currently Working on
-  Login system using Firebase Auth
-  Player vs Player Mode
-  CPU improvements
-  Online multiplayer mode
-  Persistent high score tracking
-  Player statistics
-  State handling with flow
-  Coroutines for async operations
-  Improved MVVM structure
-  Navigating with Navigation3
- 


## Tech Stack
- Firebase Auth
- Firebase Cloud Firestore
- Kotlin
- Android SDK
- MVVM architecture
- ViewModel + StateFlow
- Jetpack Compose
- MediaPlayer
- YoYo Animations Library
- Hilt / Koin (DI)
- Navigation3

## Architecture 
The project follows MVVM: 
- UI layer built with Jetpack Compose
- ViewModels expose state via Flow/StateFlow
- Domain logic handled through use-cases
- Repository layer abstracts data sources
- Coroutines manage background work and threading
- Navigation is handled with NAvigation3

## Project Goals
- Showcase modern Android development practices
- Demonstrate architectural improvements over time
- Explore multiplayer game logic
- Practice scalable UI patterns with Compose

Created by **Aurelie Vaudan.**
