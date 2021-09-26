package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		String filename= ("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadlist(l,filename);
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {
			case "help":
				Menu.displaymenu();
				isList= false;

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
			case "ls_date_desc":
				l.sortByDatedesc();
				isList=true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;
			case "find":
				TodoUtil.find(l);
				break;
			case"find_cate":
				break;
				
			

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("[오류]\n위에 언급된 메뉴중에서 선택해 주세요.(도움말-help)\n");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.savelist(l,filename);
	}
}
