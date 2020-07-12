package com.italo.waiter.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RemoteConsumingUnit extends ConsumingUnit{
    @OneToOne
    private Address address;
    private String phone;
    private String name;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RemoteConsumingUnit{" +
                "address=" + address +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
