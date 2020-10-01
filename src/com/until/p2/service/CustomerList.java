package com.until.p2.service;

import com.until.p2.bean.Customer;

public class CustomerList {
    private Customer[] customers;
    private int total = 0;

    public CustomerList() {
    }

    /**
     * 用来初始化customer数组的构造器
     *
     * @param totalCustomer:指定数组的长度
     */
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }

    /**
     * @param customer
     * @return true:添加成功 false:添加失败
     * @Description 将指定客户添加到数组中
     */
    public boolean addCustomer(Customer customer) {
        if (total >= customers.length) {
            return false;
        }

        customers[total++] = customer;
        return true;
    }

    /**
     * @param index
     * @param customer
     * @return
     */
    public boolean replaceCustomer(int index, Customer customer) {
        if (index < 0 || index >= total) {
            return false;
        }
        customers[index] = customer;
        return true;
    }

    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) {
            return false;
        }
        for (int i = index - 1; i < total - 1; i++) {
            customers[i] = customers[i + 1];
        }
        customers[total - 1] = null;
        total--;
        return true;
    }

    public Customer[] getALLCustomers() {
        Customer[] cus = new Customer[total];
        for (int i = 0; i < total; i++) {
            cus[i] = customers[i];
        }
        return cus;
    }

    public Customer getCustomer(int index) {
        if (index < 0 || index >= total) {
            return null;
        }
        return customers[index - 1];
    }

    public int getTotal() {
        return total;
    }
}
