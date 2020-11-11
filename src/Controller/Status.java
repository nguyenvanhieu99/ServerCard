/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author van hieu
 */
public class Status {
   private String name;
   String status;
   int diem;

    public Status(String name, String status, int diem) {
        this.name = name;
        this.status = status;
        this.diem = diem;
    }

    public Status() {
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getDiem() {
        return diem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
   
   
}
