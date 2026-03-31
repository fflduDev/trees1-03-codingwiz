package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrgChartImpl implements OrgChart{

	//Employee is your generic 'E'..
	private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();


	@Override
	public void addRoot(Employee e) {
		GenericTreeNode<Employee> rootEmployee = new GenericTreeNode<Employee>(e);
		nodes.add(rootEmployee);
	}

	@Override
	public void clear() {

	}

	@Override
	public void addDirectReport(Employee manager, Employee newPerson) {
		for (int i = 0; i < nodes.size(); i++) {
			GenericTreeNode<Employee> currentEmployee = nodes.get(i);

			if (currentEmployee.data.equals(manager)) {
				GenericTreeNode<Employee> newE = new GenericTreeNode<Employee>(newPerson);
				currentEmployee.addChild(newE);

				nodes.add(newE);
				break;
			}
		}
	}

	@Override
	public void removeEmployee(Employee firedPerson) {

	}

	@Override
	public void showOrgChartDepthFirst() {
		for (GenericTreeNode<Employee> candidate : nodes) {
			boolean isChild = false;
			for (GenericTreeNode<Employee> other : nodes) {
				if (other.children.contains(candidate)) {
					isChild = true;
					break;
				}
			}
			if (!isChild) printDepthFirst(candidate, 0);
		}
	}

	private void printDepthFirst(GenericTreeNode<Employee> node, int depth) {
		System.out.println("  ".repeat(depth) + node.data);
		for (GenericTreeNode<Employee> child : node.children) {
			printDepthFirst(child, depth + 1);
		}
	}

	@Override
	public void showOrgChartBreadthFirst() {
		if(!nodes.isEmpty()) {
			bFS(nodes.get(0));
		}

	}

	public void bFS(GenericTreeNode<Employee> node) {
		Queue<GenericTreeNode<Employee>> queue = new LinkedList<>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			int length = queue.size();
			System.out.println("");
			for(int i =0; i<length; i++){
				GenericTreeNode<Employee> current = queue.poll();
				System.out.print(current.data + " 	");
				ArrayList<GenericTreeNode<Employee>> child = current.getChildren();
				for(GenericTreeNode<Employee>c : child){
					queue.add(c);
				}

			}
		}
	}
}



