package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrgChartImpl implements OrgChart{

	//Employee is your generic 'E'..
	private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();
	private GenericTreeNode<Employee> root;

	@Override
	public void addRoot(Employee e) {
		this.root = new GenericTreeNode<Employee>(e);
		nodes.add(root);
	}

	@Override
	public void clear() {
		this.root = null;
		this.nodes.clear();
	}

	@Override
	public void addDirectReport(Employee manager, Employee newPerson) {
		GenericTreeNode<Employee> managerNode = searchDFS(manager, root);
		if (managerNode != null) {
			GenericTreeNode<Employee> newE = new GenericTreeNode<Employee>(newPerson);
			managerNode.addChild(newE);
			nodes.add(newE);
		}
	}

	@Override
	public void removeEmployee(Employee firedPerson) {
		GenericTreeNode<Employee> toRemove = searchDFS(firedPerson, root);
		if (toRemove == null) return;
		removeFromListRecursive(toRemove);
		if (toRemove == root) {
			root = null;
		} else {
			for (GenericTreeNode<Employee> potentialParent : nodes) {
				if (potentialParent.getChildren().contains(toRemove)) {
					potentialParent.getChildren().remove(toRemove);
					break;
				}
			}
		}
	}

	private void removeFromListRecursive(GenericTreeNode<Employee> node) {
		nodes.remove(node);
		for (GenericTreeNode<Employee> child : node.getChildren()) {
			removeFromListRecursive(child);
		}
	}

	public GenericTreeNode<Employee> searchDFS(Employee search, GenericTreeNode<Employee> node) {
		if (node == null) return null;
		if (node.data.equals(search)) {
			return node;
		}
		for (GenericTreeNode<Employee> child : node.getChildren()) {
			GenericTreeNode<Employee> found = searchDFS(search, child);
			if (found != null) return found;
		}
		return null;
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
				for(GenericTreeNode<Employee> c : child){
					queue.add(c);
				}

			}
		}
	}
}



