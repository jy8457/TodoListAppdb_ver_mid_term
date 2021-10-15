package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;
public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private String genre;
    private int id;
    private int is_completed;
    private int rate;
    public TodoItem(String title, String desc,String category,String due_date,int is_completed,int rate,String genre){
    	this.category=category;
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss"); 
        this.due_date=due_date;
        this.current_date=f.format(new Date());
        this.is_completed=is_completed;
        this.rate=rate;
        this.genre=genre;
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
    	if (is_completed==1)
    	return id+ " ["+getCategory()+"]"+" ["+getGenre()+"] "+ getTitle()+ "[V]  - "+ getDesc() + " [" + getRate()+"] " + getDue_date()+" - " +getCurrent_date() +"\n"; 
    	else
    	return id+ " ["+getCategory()+"]"+" ["+getGenre()+"] "+ getTitle()+ "  - "+ getDesc() + " [" + getRate()+"] " + getDue_date()+" - " +getCurrent_date() +"\n"; 
    		
    }
    
//System.out.println(+item.getId()+"."+ "["+item.getCategory()+"] "+ item.getTitle()+ " - "+ item.getDesc() + " "+ item.getDue_date()+" - " +item.getCurrent_date());

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
    
    
    	
    
}
