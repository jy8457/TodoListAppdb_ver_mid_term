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
		//l.importData(filename);
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

			case "ls_name":
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",1);
				break;

			case "ls_name_desc":
				System.out.println("제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",1);
				break;
				
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",0);
				break;
				
			
			case "find":
				System.out.println("찾을 내용을 입력해주세요!");
				String keyword = sc.next().trim();
				TodoUtil.find(l,keyword);
				break;
				
			case"find_cate":
				System.out.println("찾을 내용을 입력해주세요!");
				String cate= sc.next().trim();
				TodoUtil.find_cate(l,cate);
				break;
				
			case"ls_cate":
				TodoUtil.listCateAll(l);
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
