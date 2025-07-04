package controller;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;

class Task implements Comparable<Task> {
    int id;
    String description;
    int priority;

    public Task(int id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Task ID: " + id + ", Description: " + description + ", Priority: " + priority;
    }
}

class MaxHeap extends ArrayList<Task> {
    public MaxHeap() {
        //"this" so name is heap
    }

    public void insert(Task element) {
        this.add(element);
        int current = this.size() - 1;
        while (current > 0 && this.get(current).compareTo(this.get((current - 1) / 2)) > 0) {
            Task temp = this.get(current);
            this.set(current, this.get((current - 1) / 2));
            this.set((current - 1) / 2, temp);
            current = (current - 1) / 2;
        }
    }

    public Task remove() {
        if (this.size() == 0) return null;

        Task max = this.get(0);
        Task lastElement = this.remove(this.size() - 1);
        if (this.size() == 0) return max;

        this.set(0, lastElement);
        int current = 0;
        while (current < this.size()) {
            int leftChild = 2 * current + 1;
            int rightChild = 2 * current + 2;
            if (leftChild >= this.size()) break;

            int maxChild = leftChild;
            if (rightChild < this.size() && this.get(rightChild).compareTo(this.get(leftChild)) > 0) {
                maxChild = rightChild;
            }

            if (this.get(current).compareTo(this.get(maxChild)) < 0) {
                Task temp = this.get(current);
                this.set(current, this.get(maxChild));
                this.set(maxChild, temp);
                current = maxChild;
            } else {
                break;
            }
        }
        return max;
    }
}

public class Main {
    public static void mergeSort(Task[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    public static void merge(Task[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Task[] leftArray = new Task[n1];
        Task[] rightArray = new Task[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[]{
                new Task(1, "Security patch for vulnerability", 100),
                new Task(2, "Add login feature", 80),
                new Task(3, "Update documentation", 40),
                new Task(4, "Fix email notification bug", 90),
                new Task(5, "Refactor user management module", 70),
                new Task(6, "Implement data caching", 85),
                new Task(7, "Optimize database queries", 75),
                new Task(8, "Write unit tests for new features", 60),
                new Task(9, "Upgrade third-party libraries", 50),
                new Task(10, "Review and merge pull requests", 55)
        };

        System.out.println("Tasks before sorting:");
        for (Task task : tasks) {
            System.out.println(task);
        }
        /*Ưu điểm và nhược điểm của việc sử dụng mảng để quản lý các nhiệm vụ:
  Ưu điểm:
  - Thời gian truy xuất nhanh O(1) khi lấy phần tử theo chỉ số.
  - Đơn giản và dễ sử dụng.

  Nhược điểm:
  - Việc chèn và xóa có độ phức tạp O(n) trong trường hợp xấu nhất do phải dịch chuyển các phần tử.
  - Kích thước cố định, nghĩa là không thể thay đổi kích thước một cách linh hoạt.
  - Không hiệu quả cho việc tìm kiếm trừ khi mảng đã được sắp xếp (O(n) cho tìm kiếm tuần tự).*/

        mergeSort(tasks, 0, tasks.length - 1);

        System.out.println("\nTasks after sorting:");
        for (Task task : tasks) {
            System.out.println(task);
        }

        /* Tại sao MergeSort ổn định và tại sao Quicksort không ổn định?
   MergeSort ổn định vì nó giữ nguyên thứ tự tương đối của các phần tử bằng nhau.
   Quicksort không ổn định vì nó có thể hoán đổi các phần tử bằng nhau, thay đổi thứ tự tương đối của chúng.*/

        MaxHeap maxHeap = new MaxHeap();
        for (Task task : tasks) {
            maxHeap.insert(task);
        }

        System.out.println("\nMax Heap tasks:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(maxHeap.remove());
        }

         /* Ưu điểm của cấu trúc dữ liệu heap:
   - Hỗ trợ hiệu quả cho các thao tác hàng đợi ưu tiên.
   - Việc chèn và xóa có độ phức tạp O(log n).
   - Heap hữu ích cho việc triển khai các thuật toán sắp xếp hiệu quả như HeapSort.*/

    }
}

