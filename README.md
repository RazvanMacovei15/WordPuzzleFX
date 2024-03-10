# Word Puzzle - Doublets
***
## A visualised representation of the Doublets game in JavaFX
## Table of contents:
1. Description
2. Technologies used
3. The scope
4. The need
5. How it works
### 1.Description:
In the year 1879, Lewis Carroll, the creative genius behind "Alice in Wonderland," devised a linguistic amusement called "doublets" to engage two young ladies. The essence of doublets involves linking two words of equal length by introducing intermediary words, each differing from the preceding word by only one letter. The process initiates by modifying a single letter in the initial word to generate a new word, and this progression continues until arriving at the second word.

Here, Lewis Carroll's example transforming APE into MAN is presented:

APE --> ARE --> ERE --> ERR --> EAR --> MAR --> MAN

Yet, a more streamlined solution is possible, achieving the same transformation with merely four intermediate words:

APE --> APT --> OPT --> OAT --> MAT --> MAN
 
### 2. Technologies Used:
* JavaFX
* Maven
* Graphs
* Breadth-First-Search
### 3. The scope:
* create an application to visualise a BFS(breadth-first-search) on a graph
### 4. The need:
* this came as a project at university where we had to do a console based application for this game. I wanted to take it a step further and create a visual representation of it.
### 5. How it works:
* first thing the user must do is select a dictionary file. The user can then select the start and end words. The app will then find the shortest path between the two words. The user can reset the graph to a new state and see the algorithm in action again.
* Snapshots of the app in action:

<img width="1112" alt="Screenshot 2024-03-03 at 08 31 04" src="https://github.com/RazvanMacovei15/WordPuzzleFX/assets/95320896/b720f8ca-75ae-4d20-a76c-dd0881e35476">

<img width="1112" alt="Screenshot 2024-03-03 at 08 31 34" src="https://github.com/RazvanMacovei15/WordPuzzleFX/assets/95320896/b74e0b05-bdf3-4a3d-8ec9-bf93aa995ce3">

<img width="1112" alt="Screenshot 2024-03-03 at 08 31 40" src="https://github.com/RazvanMacovei15/WordPuzzleFX/assets/95320896/0aae968f-b770-4b9f-bbbf-eb016463bf2d">

<img width="1112" alt="Screenshot 2024-03-03 at 08 32 26" src="https://github.com/RazvanMacovei15/WordPuzzleFX/assets/95320896/4999d01f-f363-475d-9175-21698f1e2be8">

* Video of the app in action:

https://github.com/RazvanMacovei15/WordPuzzleFX/assets/95320896/a82f52f9-11a8-42f4-8419-37ccc07c0768
