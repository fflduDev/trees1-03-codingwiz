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
		GenericTreeNode<Employee> result = searchDFS(firedPerson, nodes.get(0));
		if(result.getChildren()==null){

		}
	}

	public GenericTreeNode<Employee> searchDFS(Employee search, GenericTreeNode<Employee> node) {
		if (node.getChildren() != null) {
			ArrayList<GenericTreeNode<Employee>> children = node.getChildren();
			for (GenericTreeNode<Employee> child : children) {
				if (child.equals(search)) {
					return node;
				}
				searchDFS(search, child);

			}
			return null;
		}
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



