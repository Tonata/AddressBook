package com.example.address_book.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonata on 8/18/14.
 */
public class Contact implements Parcelable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String cellNumber;
    private String homeAddress;
    private int listPostion = 0;


    public Contact(Builder b){
        id = b.id;
        firstName = b.firstName;
        lastName = b.lastName;
        cellNumber = b.cellNumber;
        homeAddress = b.homeAddress;
        listPostion = b.listPostion;
    }

    public Contact(int id , String fName , String lName , String cell , String home, int listPos){
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.cellNumber = cell;
        this.homeAddress = home;
        this.listPostion = listPos;
    }

    public static class Builder{
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String cellNumber;
        private String homeAddress;
        private int listPostion = 0;

        public Builder(){

        }

        public Builder(int id , String fName , String lName , String cell , String home, int listPos){
            this.id = id;
            this.firstName = fName;
            this.lastName = lName;
            this.cellNumber = cell;
            this.homeAddress = home;
            this.listPostion = listPos;
        }

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder firstName(String fName){
           firstName = fName;
           return this;
        }

        public Builder lastName(String lName){
            lastName = lName;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder cellNumber(String cellNumber){
            this.cellNumber = cellNumber;
            return this;
        }

        public Builder homeAddress(String homeAddress){
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder listPosition(int listPostion)
        {
            this.listPostion = listPostion;
            return this;
        }

        public Builder contact(Contact c){
            id = c.getId();
            firstName = c.getFirstName();
            lastName = c.getLastName();
            email = c.getEmail();
            homeAddress = c.getHomeAddress();
            cellNumber = c.getCellNumber();
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }



    }

    //Setters
    public void setId(int id){ this.id = id; }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setCellNumber(String cellNumber){
        this.cellNumber = cellNumber;
    }

    public void setHomeAddress(String homeAddress){
        this.homeAddress = homeAddress;
    }

    public void setListPosition(int listPostion){this.listPostion = listPostion;}

    //Getters
    public int getId(){ return  id;}

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getCellNumber(){
        return cellNumber;
    }

    public String getHomeAddress(){ return homeAddress;
    }

    public int getListPostion(){return listPostion;}

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel dest , int flags){
        dest.writeInt(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.cellNumber);
        dest.writeString(this.homeAddress);
        dest.writeInt(listPostion);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>(){
        public Contact createFromParcel(Parcel in){
            return new Contact(in);
        }

        public Contact[] newArray(int size){
            return new Contact[size];
        }
    };

    private Contact(Parcel in){
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.cellNumber = in.readString();
        this.homeAddress = in.readString();
        this.listPostion = in.readInt();
    }
}
