package com.todo.service;

import java.util.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	//String filename;
	public static void createItem(TodoList list) {
		
		String title, desc;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[항목 추가]\n"
				+ "추가 하려는 항목의 이름을 입력하세요.\n"
				+ "\n");// 실행하려는 메뉴의 제목을 입력하십시
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이름은 중복 될 수 없습니다.");
			return;
		}
		sc.nextLine();
		System.out.println("설명을 입력하세요.");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("추가 완료");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("\n"
				+ "[항목 삭제]\n"
				+ "삭제 하려는 항목의 이름을 입력하세요.\n"
				+ "\n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제완료!");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[항목 수정]\n"
				+ "수정 하려는 항목의 이름을 입력하세요.\n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("그런 이름은 없습니다.");
			return;
		}

		System.out.println("새로운 이름을 입력하세요.");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이름은 중복 될 수 없습니다.");
			return;
		}
		
		System.out.println("새로운 설명을 입력하세요.");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description,);
				l.addItem(t);
				System.out.println("수정 되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" +item.getTitle()+ "]"+ item.getDesc() + " - " +item.getCurrent_date());
			
		}
	}
	
	public static void loadlist(TodoList l,String filename) {
		int count=0;
		try {
			BufferedReader br= new BufferedReader(new FileReader(filename));
			String line;
			while((line=br.readLine())!=null) {
				StringTokenizer st=new StringTokenizer(line,"##");
				String title=st.nextToken();
				String desc=st.nextToken();
				TodoItem t=new TodoItem(title,desc);
				l.addItem(t);
				count++;
			}
			br.close();
			System.out.printf("%d개의 정보를 읽어왔습니다!\n",count);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void savelist(TodoList l,String filename) {
		
		try {
			Writer w= new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
				
				
			}
			w.close();
			
				
		}
		//catch(FileNotFoundException e){
		//	e.printStackTrace();
		//}
		catch(IOException e){
			e.printStackTrace();
		}
	
		
	}
	
}
