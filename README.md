# traffic-lights

Implementing a **circular queue** means creating a data structure that manages elements (in this case, roads) in a **fixed-size, circular manner**. A circular queue is ideal for scenarios where the order of processing repeats cyclically, like managing roads in your traffic light simulator.

### **Circular Queue Explained:**
- A **queue** is a **First-In-First-Out (FIFO)** data structure: the first element added is the first to be processed.
- A **circular queue** treats the queue as circular, meaning:
    - After the last position, it wraps back to the first position.
    - It efficiently uses space by reusing the array instead of shifting elements.

This is particularly useful for your project since roads open and close in a **cyclical order**.

---

### **Key Features of a Circular Queue:**
1. **Fixed Size:**
    - It has a predefined capacity, e.g., a maximum number of roads.
2. **Two Pointers:**
    - **Front Pointer**: Points to the first element in the queue.
    - **Rear Pointer**: Points to the position where the next element will be inserted.
3. **Wrap-Around Behavior:**
    - If the rear reaches the end of the array, it wraps back to the beginning.

---

### **Example:**
Imagine a traffic light managing 3 roads, `A`, `B`, and `C`, with a circular queue:
- Initially: Queue = `[A, B, C]`
- Traffic light opens `A`, then moves to `B`, then `C`.
- Once `C` is done, it cycles back to `A`.

This behavior is naturally handled by a circular queue.

---

### **How to Implement in Java:**
You can use an array to create the circular queue, with basic operations:
- **Enqueue (Add Road):** Insert a road into the queue.
- **Dequeue (Remove Road):** Remove a road when it no longer needs to be managed.
- **Wrap Around:** Ensure indices reset to the beginning when the end of the array is reached.

---