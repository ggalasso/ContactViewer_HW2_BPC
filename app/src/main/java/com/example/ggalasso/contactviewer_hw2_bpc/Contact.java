package com.example.ggalasso.contactviewer_hw2_bpc;
//Pankaj

import java.util.Comparator;

/**
 * Created by ggalasso on 2/13/15.
 */

public class Contact {

    public Contact(String firstName, String lastName, String contactTitle, String phoneType, String phoneNumber, String emailType, String emailAdd, String socialType, String social ,int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactTitle = contactTitle;
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
        this.emailType = emailType;
        this.emailAdd = emailAdd;
        this.socialType = socialType;
        this.social = social;
        this.id = id;
    }


    private String firstName;
    private String lastName;
    private String contactTitle;
    private String phoneType;
    private String phoneNumber;
    private String emailType;
    private String emailAdd;
    private String socialType;
    private String social;
    private int id;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Comparator<Contact> contactComparator = new Comparator<Contact>() {

        public int compare(Contact c1, Contact c2) {
            String contactName1 = c1.getFirstName().toUpperCase();
            String contactName2 = c2.getFirstName().toUpperCase();
            return contactName1.compareTo(contactName2);
        }
    };
}
