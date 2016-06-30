import java.util.*;

public class testQueue {
	public static void main(String[] args) {
		Queue<String> queue = new PriorityQueue<String>();
		queue.add("Hello");
		queue.add("My name");
		queue.add("Is Eli");

		System.out.println(queue);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}