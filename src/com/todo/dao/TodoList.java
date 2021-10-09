package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;
	Connection conn;
	
	public TodoList() {
		this.conn=DbConnect.getConnection();
		this.list = new ArrayList<TodoItem>();
		
		//new리스트 형성위에 private list를 public으로 가져왔다 
	}
	public int addItem(TodoItem t) {
		String sql="insert into list(title, memo, category, current_date, due_date)"
				+ " values (? ,? ,? ,? ,?);";
		PreparedStatement pstmt;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			count=pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

//	public void addItem(TodoItem t) {
//		list.add(t);
//		// 1번 메뉴인 add메뉴를 위한 클래스 이다 array list에 다른 클래스에 있는 todoitem에 관한 정보(이름,설명,날짜를)추가 하는것.
//	}

	public int deleteItem(int index) {
		String sql="delete from list where id=?;";
		PreparedStatement pstmt;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,index);
			count = pstmt.executeUpdate();
			pstmt.close();
	}catch(SQLException e){
		e.printStackTrace();
	}
		return count;
	}
	

	public int updateItem(TodoItem t) {
		String sql="update list set title=?, memo=?, category=?, current_date=?, due_date=?" + "where id=?;";
		PreparedStatement pstmt;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			pstmt.setInt(6,t.getId());
			count=pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			String sql="SELECT * FROM list ";
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category =rs.getString("category");
				String title =rs.getString("title");
				String memo =rs.getString("memo");
				String due_date =rs.getString("due_date");
				String current_date =rs.getString("current_date");
				TodoItem t = new TodoItem(title,memo,category,due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
				
			}
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		System.out.println(keyword);
		PreparedStatement pstmt;
		keyword= "%"+keyword+"%";
		try {
			String sql="SELECT * FROM list WHERE title like ? or memo like ?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs= pstmt.executeQuery();
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby,int ordering) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt= conn.createStatement();
			String sql = "selec * from list order by"+ orderby;
			if(ordering==0) {
				sql+="desc";
			}
			ResultSet rs= stmt.executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
		// 5번 메뉴인 ls_name_asc과 연결되어 있음, Service 폴더안에 todosortbyname에 comparator를 통해 이름  오름차순 정렬이 가능하다.
	}
	
	public int getCount() {
		Statement stmt;
		int count=0;
		try {
			stmt =conn.createStatement();
			String sql="select count(id) from list;";
			ResultSet rs= stmt.executeQuery(sql);
			rs.next();
			count=rs.getInt("count(id)");
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
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
	
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql="insert into list (title,memo,category,current_date,due_date)"
					+ " values (?,?,?,?,?);";
			int records =0;
			while((line=br.readLine())!=null) {
				StringTokenizer st =new StringTokenizer(line,"##");
				String category=st.nextToken();
				String title=st.nextToken();
				String description=st.nextToken();
				String due_date=st.nextToken();
				String current_date=st.nextToken();
				
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,title);
				pstmt.setString(2,description);
				pstmt.setString(3,category);
				pstmt.setString(4,current_date);
				pstmt.setString(5,due_date);
				
				int count=pstmt.executeUpdate();
				if(count>0) records++;
				pstmt .close();
				
			}
			System.out.println(records+" records read!!");
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list=new ArrayList<String>();
		Statement stmt;
		try {
			stmt=conn.createStatement();
			String sql="SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		//return null;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword){
		ArrayList<TodoItem> list=new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql="SELECT * FROM list WHERE category = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery(sql);
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
