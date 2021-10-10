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
import java.util.ArrayList;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
		public static void createItem(TodoList list) {
		
		String title, desc,category,due_date;
		
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
		//sc.nextLine();
		System.out.println("카테고리를 입력해 주세요");
		
		category=sc.next();
		sc.nextLine();
		System.out.println("설명을 입력하세요.");
		desc = sc.nextLine().trim();
		System.out.println("마감일자를 입력해 주세요.");
		due_date=sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc,category,due_date);
		if(list.addItem(t)>0)
		System.out.println("추가 완료");
	}
	

	public static void deleteItem(TodoList l) {
		TodoUtil.listAll(l);
		Scanner sc = new Scanner(System.in);
		//String title = sc.next();
		
		System.out.println("\n"
				+ "[항목 삭제]\n"
				+ "삭제 하려는 항목의 숫자을 입력하세요.\n"
				+ "\n");
		
		int num=sc.nextInt();
		if(l.deleteItem(num-1)>0)
			System.out.println("삭제되었습니다.");
				
	
			
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		TodoUtil.listAll(l);
		System.out.println("\n"
				+ "[항목 수정]\n"
				+ "수정 하려는 항목의 숫자를 입력하세요.\n"
				+ "\n");
		int num=sc.nextInt();
		int mark=0;
		
		for(TodoItem item:l.getList()) {
			if(num-1==l.indexOf(item)) {
				mark=1;
			}
		}
		if(mark==0) {
			System.out.println("그런 항목은 없습니다!");
			return;
		}
		System.out.println("새로운 이름을 입력하세요.");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이름은 중복 될 수 없습니다.");
			return;
		}
		
		System.out.println("새로운 카테고리를 입력하세요.");
		String new_cate=sc.next();
		sc.nextLine();
		System.out.println("새로운 설명을 입력하세요.");
		String new_description = sc.nextLine().trim();
		System.out.println("새로운 마감일자을 입력하세요.");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description,new_cate,new_due_date);
		t.setId(num);
		if(l.updateItem(t)>0) System.out.println("수정 되었습니다.");
		

	}

	public static void listAll(TodoList l) {
		
		System.out.printf("[전체 목록, 총 %d개]\n",l.getCount());
		
		for (TodoItem item : l.getList()) {
			System.out.println(+item.getId()+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());
			
		}
	}
	
	public static void listAll(TodoList l,String orderby,int ordering) {
			
			System.out.printf("[전체 목록, 총 %d개]\n",l.getCount());
			
			for (TodoItem item : l.getOrderedList(orderby, ordering)) {
				System.out.println(+item.getId()+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());
				
			}
		}
	
	public static void find(TodoList l,String keyword) {
		//TodoUtil.listAll(l);
		int count=0;
		for(TodoItem item: l.getList(keyword)) {
			System.out.println(+item.getId()+1+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
		
	}
	public static void find_cate(TodoList l,String cate) {
		//TodoUtil.listAll(l);
		int count=0;
		for(TodoItem item:l.getListCategory(cate)) {
			System.out.println(+item.getId()+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
		
		
	}
	
	public static void listCateAll(TodoList l) {
		
		for(String item:l.getCategories()) {
			System.out.print(item + " ");
			
		}
		//System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다,\n", count);
	}
	
	public static void loadlist(TodoList l,String filename) {
		int count=0;
		try {
			BufferedReader br= new BufferedReader(new FileReader(filename));
			String line;
			while((line=br.readLine())!=null) {
				StringTokenizer st=new StringTokenizer(line,"##");
				String category=st.nextToken();
				String title=st.nextToken();
				String desc=st.nextToken();
				String due_date=st.nextToken();
				String current_date=st.nextToken();
				TodoItem t=new TodoItem(title,desc,category,due_date);
				t.setCurrent_date(current_date);
				l.addItem(t);
				count++;
			}
			br.close();
			System.out.printf("%d개의 정보를 읽어왔습니다!\n",count);
		}
		catch(FileNotFoundException e){
			System.out.println(filename+"파일이 없습니다!");
			//e.printStackTrace();
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
			System.out.println("저장 완료!");
				
		}
		//catch(FileNotFoundException e){
		//	e.printStackTrace();
		//}
		catch(IOException e){
			e.printStackTrace();
		}
	
		
	}
	
}
