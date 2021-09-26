package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
		//new리스트 형성위에 private list를 public으로 가져왔다 
	}

	public void addItem(TodoItem t) {
		list.add(t);
		// 1번 메뉴인 add메뉴를 위한 클래스 이다 array list에 다른 클래스에 있는 todoitem에 관한 정보(이름,설명,날짜를)추가 하는것.
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
		// 2번 메뉴인 del를 구현 하기 위한 클래스, remove()클래스를 활용하여 입력받은 값을 삭제한다.
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
		// 3번 메뉴인 edit을 구현하기 위한 클래스, remove를 통해 해당되는 값을 제거한후 add를 통해 새로운 값을 추가해준다.
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
		// 4번 메뉴 ls 구현하기 위한 클래스, ArrayList 값을 리턴하여 현제까지 추가된 값을 보여준다.
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
		// 5번 메뉴인 ls_name_asc과 연결되어 있음, Service 폴더안에 todosortbyname에 comparator를 통해 이름  오름차순 정렬이 가능하다.
	}
	
	

	public void listAll() {
		System.out.println("\n"
				+ "inside list_All method\n");
		for (TodoItem myitem : list) {
			System.out.println(myitem.getTitle() + myitem.getDesc());
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
		// 6번 메뉴인 ls_name_desc과 연결되어 있음, Service 폴더안에 todosortbyname에 comparator를 통해 이름 내림차순 정렬이 가능하다.
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
		// 7번 메뉴인 ls_date과 연결되어 있음, Service 폴더안에 todosortbydate에 comparator를 통해 날짜 오름차순 정렬이 가능하다.
	}
	public void sortByDatedesc() {
		Collections.sort(list, new TodoSortByDate().reversed());
		
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}
	public int getSize() {
		return list.size();
	}
	
	public void find(String line) {
		int count=0;
		for (TodoItem item : list) {
			if (item.getTitle().contains(line)||item.getDesc().contains(line)) {
				System.out.println(+list.indexOf(item)+1+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());
			count++;
			}
		}
		System.out.printf("총 %d개의 항목을 찾았습니다!\n",count);
	}
	
	public void find_cate(String line) {
		int count=0;
		for(TodoItem item:list) {
			if(item.getCategory().contains(line)) {
				System.out.println(+list.indexOf(item)+1+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());
				count++;
			}
			
		}
		System.out.printf("총 %d개의 항목을 찾았습니다!\n",count);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) {
				System.out.println(+list.indexOf(item)+1+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());
			}
		}
		return false;
	}
}
