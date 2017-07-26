package com.course.innopolis.mytaskmanager.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cristina on 12.07.2017.
 */

public class Task implements Parcelable {
    private Integer id;
    private String taskName;
    private Boolean isAllDay;
    private String taskDescription;
    private Date dateBeg;
    private Date dateEnd;
    private String taskPeriodicity;
    private String taskColor;


    public Task(){
    }


    public Task(Integer id, String taskName, Boolean isAllDay, String taskDescription, Date dateBeg, Date dateEnd, String taskPeriodicity, String taskColor) {
        this.id = id;
        this.taskName = taskName;
        this.isAllDay = isAllDay;
        this.taskDescription = taskDescription;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.taskPeriodicity = taskPeriodicity;
        this.taskColor = taskColor;
    }


    public Task(String taskName, Boolean isAllDay, String taskDescription, Date dateBeg, Date dateEnd, String taskPeriodicity, String taskColor) {
        this.taskName = taskName;
        this.isAllDay = isAllDay;
        this.taskDescription = taskDescription;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.taskPeriodicity = taskPeriodicity;
        this.taskColor = taskColor;
    }


    @Override
    public int describeContents(){
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeInt(id);
        parcel.writeString(taskName);
        parcel.writeInt(isAllDay ? 1 : 0);
        parcel.writeString(taskDescription);
        parcel.writeString(dateBeg.toString()); // TODO: должно сохраняться время
        parcel.writeString(dateEnd.toString());
        parcel.writeString(taskPeriodicity);
        parcel.writeString(taskColor);
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>(){
        public Task createFromParcel(Parcel in){
            return new Task(in);
        }
        public Task[] newArray(int size){
            return new Task[size];
        }
    };

    private Task(Parcel parcel){
        id = parcel.readInt();
        taskName = parcel.readString();
        isAllDay = (parcel.readInt() != 0);
        taskDescription = parcel.readString();
        dateBeg = readDate(parcel.readString());
        dateEnd = readDate(parcel.readString());
        taskPeriodicity = parcel.readString();
        taskColor = parcel.readString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getAllDay() {
        return isAllDay;
    }

    public void setAllDay(Boolean allDay) {
        isAllDay = allDay;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(Date dateBeg) {
        this.dateBeg = dateBeg;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTaskPeriodicity() {
        return taskPeriodicity;
    }

    public void setTaskPeriodicity(String taskPeriodicity) {
        this.taskPeriodicity = taskPeriodicity;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    private Date readDate(String dateString){
        SimpleDateFormat format = new SimpleDateFormat("E, d MMMM yyyy HH:mm");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  date;
    }
}