package tree;

import java.util.ArrayList;
import java.util.List;

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

	}
}
