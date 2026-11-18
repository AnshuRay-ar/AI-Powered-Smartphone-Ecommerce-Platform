package com.springbootproj.ProjectSpringboot.model;

public class CartItem {

    private Phone phone;
    private int quantity;

    public CartItem(Phone phone, int quantity) {
        this.phone    = phone;
        this.quantity = quantity;
    }

    public Phone getPhone()              { return phone; }
    public void setPhone(Phone phone)    { this.phone = phone; }
    public int getQuantity()             { return quantity; }
    public void setQuantity(int qty)     { this.quantity = qty; }

    public double getSubtotal() {
        return phone.getPrice() * quantity;
    }
}