# Restaurant Order Management System

## Project Overview
The **Restaurant Order Management System** is a command-line interface (CLI) application built in Java. It simulates a fast-paced cafe environment where staff can view a menu, take customer orders with multiple items, queue the orders for the kitchen, and serve them sequentially. It also features a safety mechanism to "undo" accidentally served orders.

This project was developed for the Data Structures and Algorithms (ICT 143-2) assignment to demonstrate the practical application of fundamental data structures in a real-world scenario.

## Features & Data Structures Used
To ensure maximum efficiency and logic correctness, this application leverages 5 specific data structures:

1. **Arrays**: Used to store the static, fixed menu of the cafe (`MenuItem[]`). Provides O(1) lookup time.
2. **ArrayLists (Dynamic Arrays)**: Used to store multiple items within a single customer's ticket (`List<MenuItem>`). Allows the ticket to grow dynamically as the customer orders more food.
3. **Queues**: Used to manage pending orders waiting for the kitchen. Enforces **First-In-First-Out (FIFO)** processing so older orders are always served first.
4. **Linked Lists**: Used as the underlying foundation for the Queue (`LinkedList<Order>`), providing highly efficient O(1) insertions at the tail and removals at the head.
5. **Stacks**: Used to track recently completed orders. Enforces **Last-In-First-Out (LIFO)** processing, which perfectly supports an "Undo" feature by retrieving the most recently closed order.

## Setup & Execution Instructions

### Prerequisites
- You must have [Java Development Kit (JDK)](https://adoptium.net/) installed on your machine.

### How to Run
1. Open your Terminal, PowerShell, or Command Prompt.
2. Navigate to the directory containing the project files. For example:
   ```bash
   cd "C:\Users\user\Desktop\java project"
   ```
3. Compile the Java source file:
   ```bash
   javac RestaurantOrderSystem.java
   ```
4. Run the compiled application:
   ```bash
   java RestaurantOrderSystem
   ```
5. Follow the on-screen interactive menu to test out the features!

## Author
*Created as part of the ICT 143-2 Data Structures and Algorithms Practical Assignment.*
