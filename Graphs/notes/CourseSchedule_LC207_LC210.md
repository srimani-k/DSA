# Topological Sort — LeetCode 207 & 210

## Core Pattern: Kahn's Algorithm (BFS)

**Used for:** Processing directed graphs in dependency order and detecting directed cycles.

- **LC 207 — Course Schedule:** Determine whether all courses can be completed.
- **LC 210 — Course Schedule II:** Return a valid ordering of all courses, or an empty array if impossible.

---

## 1. Core Concepts

### Directed edges and prerequisites

A prerequisite pair `[course, prerequisite]` means the prerequisite must be completed before the course.

Example: `[1, 0]` means course `0` must be completed before course `1`.

Therefore, create the directed edge:

`0 → 1`

In Java:

```java
graph.get(prerequisite).add(course);
```

### Indegree

**Indegree = number of incoming edges to a node.**

A course with indegree `0` has no remaining prerequisites, so we can take it immediately.

Example:

```text
0 → 2
1 → 2
2 → 3
```

Indegrees:

```text
Course:   0  1  2  3
Indegree: 0  0  2  1
```

Courses `0` and `1` can be processed first.

### Why Kahn's algorithm detects cycles

If a directed cycle exists, the courses in that cycle keep each other blocked. Their indegrees never become zero.

Therefore, if we cannot process all `numCourses` courses, a cycle exists and no valid complete ordering is possible.

---

## 2. Kahn's Algorithm — Step by Step

1. Build an adjacency list for the directed graph.
2. Calculate the indegree of every node.
3. Add all nodes with indegree `0` to a queue.
4. While the queue is not empty:
    - Remove a node using `poll()`.
    - Process it: increment `count` or add it to `result`.
    - For every outgoing neighbor, decrement its indegree.
    - If the neighbor's indegree becomes `0`, add that **neighbor** to the queue.
5. Determine whether all nodes were processed.

**Cycle check:**

- If processed count equals `numCourses`, no cycle prevents completion.
- Otherwise, a cycle exists.

---

## 3. LC 207 — Course Schedule

**Problem:** Return `true` if all courses can be completed; otherwise, return `false`.

### Java solution

```java
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build directed edges: prerequisite -> course
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];

            graph.get(prerequisite).add(course);
        }

        // Calculate indegrees
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            for (int neighbor : graph.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Start with courses having no prerequisites
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;

            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return count == numCourses;
    }
}
```

### Key idea

We don't need the actual ordering, so we only maintain `count`.

- `count == numCourses` → return `true`.
- `count < numCourses` → return `false`.

---

## 4. LC 210 — Course Schedule II

**Problem:** Return a valid ordering of all courses. If completing all courses is impossible, return an empty `int[]`.

### Difference from LC 207

The graph construction, indegree calculation, and BFS are the same.

Instead of counting processed courses, store their order in a `List<Integer>`.

### Java solution

```java
import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build directed edges: prerequisite -> course
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];

            graph.get(prerequisite).add(course);
        }

        // Calculate indegrees
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            for (int neighbor : graph.get(i)) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // A partial ordering is not a valid answer
        if (result.size() != numCourses) {
            return new int[]{};
        }

        // Convert List<Integer> to int[]
        int[] order = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            order[i] = result.get(i);
        }

        return order;
    }
}
```

### Why check `result.size()` before creating the array?

Suppose `numCourses = 4`, but only three courses were processed:

```text
result = [0, 1, 2]
```

Returning a four-element array could introduce a default `0`:

```text
[0, 1, 2, 0]  // Incorrect partial ordering
```

So we first check:

```java
if (result.size() != numCourses) {
    return new int[]{};
}
```

After this check, we know:

```java
result.size() == numCourses
```

Therefore, the array has exactly enough elements, and every position can be filled.

**Remember:** Return either the complete ordering or an empty array — never a partial ordering.

---

## 5. Common Mistakes

| Mistake | Correct approach |
|---|---|
| Building edge `course → prerequisite` | Build `prerequisite → course` |
| Calculating indegree for the current node instead of its neighbor | Use `indegree[neighbor]++` |
| Enqueuing `node` after its neighbor's indegree becomes zero | Enqueue `neighbor` |
| Forgetting to decrement indegrees | Decrement each outgoing neighbor |
| Assuming an empty queue means success | Check processed count or result size |
| Returning `result.toArray()` as `int[]` | Manually copy elements or use streams |
| Returning a partial ordering in LC 210 | Return `new int[]{}` if not all courses were processed |

**Java note:** `List<Integer>.toArray()` returns an `Object[]`, not an `int[]`. Manual copying is a perfectly valid solution.

---

## 6. Edge Cases

| Input situation | LC 207 | LC 210 |
|---|---|---|
| No prerequisites | `true` | Any ordering containing all courses |
| One course, no prerequisites | `true` | `[0]` |
| Simple chain `0 → 1 → 2` | `true` | `[0, 1, 2]` |
| Multiple courses with indegree `0` | `true` if no cycle | Multiple valid orders may exist |
| Cycle `0 → 1 → 0` | `false` | `[]` |
| Disconnected DAG | `true` if all courses can be processed | Ordering includes every course |
| `numCourses = 0` | `true` | `[]` |

Note: A valid topological ordering is not necessarily unique. The judge accepts any ordering that satisfies every prerequisite.

---

## 7. Complexity Analysis

Let:

- `V` = number of courses.
- `E` = number of prerequisite pairs.

**Time complexity: `O(V + E)`**

- Build adjacency list: `O(V + E)`.
- Calculate indegrees: `O(V + E)`.
- BFS processing: `O(V + E)`.

**Space complexity: `O(V + E)`**

The adjacency list stores vertices and edges; the indegree array, queue, and result require up to `O(V)` additional space.

---

## 8. Interview Pattern Recognition

When you see a problem involving prerequisites, dependencies, task ordering, or whether every task can be completed, consider **topological sorting**.

Ask yourself:

1. Is this a directed graph?
2. Does one task depend on another?
3. Do I need to detect a cycle, or return an ordering?
4. Can I use indegree and BFS (Kahn's algorithm)?

### Final revision

- **LC 207:** Kahn's algorithm + processed count → `count == numCourses`.
- **LC 210:** Kahn's algorithm + result list → check size, then convert to `int[]`.
- **Indegree `0`:** Node is ready to process.
- **Cycle:** Some nodes remain unprocessed.
- **Core pattern:** Build graph → calculate indegrees → queue indegree-zero nodes → process neighbors → check completeness.
