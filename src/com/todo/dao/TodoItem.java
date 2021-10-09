package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;
public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int id;

    public TodoItem(String title, String desc,String category,String due_date){
    	this.category=category;
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss"); 
        this.due_date=due_date;
        this.current_date=f.format(new Date());
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getCategory() {
		return category;
	}
	

	public void setCategoty(String categoty) {
		this.category = categoty;
	}
    
    public String toSaveString() {
    	return category+ "##"+ title + "##" + desc + "##"+ due_date + "##" + current_date + "\n"; 
    }
    @Override
    public String toString() {
    	return "["+category+"] "+ title+ " - "+ desc + " "+ due_date+" - " +current_date +"\n"; 
    }
//System.out.println(+list.indexOf(item)+1+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    
    
    	
    
}
