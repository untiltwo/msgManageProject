package com.until.p2.ui;

import com.until.p2.bean.Customer;
import com.until.p2.service.CustomerList;
import com.until.p2.util.CMUtility;
import javafx.scene.input.Mnemonic;
import sun.nio.cs.ext.MacHebrew;

public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public void EnterMainMenu() {
        boolean bIsFlag = true;
        while (bIsFlag) {
            System.out.println("--------------客户信息管理软件--------------");
            System.out.println("                1添加客户");
            System.out.println("                2修改客户");
            System.out.println("                3删除客户");
            System.out.println("                4客户列表\n");
            System.out.println("                5退   出");
            System.out.print("                请选择（1-5）： ");

            char menu = CMUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    AddNewCustomer();
                    break;
                case '2':
                    ModifyCustomer();
                    break;
                case '3':
                    DeleteCustomer();
                    break;
                case '4':
                    ListAllCustomers();
                    break;
                case '5':
                    System.out.println("exit");
                    System.out.println("确认是否退出Y/N:");
                    char isExict = CMUtility.readConfirmSelection();
                    if (isExict == 'Y') {
                        bIsFlag = false;
                    }
                    break;
            }

        }
    }

    private void AddNewCustomer() {
        System.out.println("--------------添加客户--------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(13);
        System.out.print("邮箱：");
        String email = CMUtility.readString(30);

        Customer customer = new Customer(name,gender,age,phone,email);
        boolean bIsSucess = customerList.addCustomer(customer);
        if (bIsSucess){
            System.out.println("--------------添加完成--------------");
        }else {
            System.out.println("--------------客户目录已满，添加失败--------------");
        }
    }

    private void ModifyCustomer() {
        System.out.println("--------------修改客户--------------");
        Customer customer = new Customer();
        int number;
        for(;;){
            System.out.print("请选择待修改客户编号(-1退出)：");
            number = CMUtility.readInt();
            if (number == -1){
                return;
            }
            customer = customerList.getCustomer(number-1);
            if(customer == null){
                System.out.println("无法找到指定客户");
            }else {
                break;
            }
        }
        //修改客户信息
        System.out.println("姓名（" +customer.getName() +"): ");
        String name = CMUtility.readString(10, customer.getName());

        System.out.println("性别（" + customer.getGender()+"): ");
        char gender = CMUtility.readChar(customer.getGender());

        System.out.println("年龄（" +customer.getGender() +"): ");
        int age = CMUtility.readInt(customer.getAge());

        System.out.println("电话（" + customer.getPhone()+"): ");
        String phone = CMUtility.readString(13, customer.getPhone());

        System.out.println("邮箱（" +customer.getEmail() +"): ");
        String email = CMUtility.readString(30, customer.getEmail());

        Customer newCustomer = new Customer(name,gender,age,phone,email);
        boolean bIsReplaced = customerList.replaceCustomer(number - 1, newCustomer);
        if (bIsReplaced){
            System.out.println("--------------修改成功--------------");
        }else {
            System.out.println("--------------修改失败--------------");
        }

    }

    private void DeleteCustomer() {
        System.out.println("--------------删除客户--------------");
        Customer newCustomer;
        int number;
        for (; ; ) {
            System.out.println("请选择待删除客户编号(-1退出)");
            number = CMUtility.readInt();
            if (number == -1) {
                return;
            }
            newCustomer = customerList.getCustomer(number - 1);
            if (newCustomer == null) {
                System.out.println("无法找到指定客户！");
            } else {
                break;
            }
            System.out.println("确认是否删除Y/N:");
            char isDelete = CMUtility.readConfirmSelection();
            if (isDelete == 'Y') {
                boolean bIsDelete = customerList.deleteCustomer(number - 1);
                if (bIsDelete) {
                    System.out.println("--------------删除成功--------------");
                } else {
                    System.out.println("--------------删除失败--------------");
                }
            } else {
                break;
            }
        }
    }

    private void ListAllCustomers() {
        System.out.println("--------------客户列表--------------");
        int total = customerList.getTotal();
        if (total == 0) {
            System.out.println("没有客户记录！");
        } else {
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t邮箱");
            Customer[] allCustomers = customerList.getALLCustomers();
            for (int i = 0; i < total; i++) {
                Customer customer = allCustomers[i];
                System.out.println((i + 1) + "\t" + customer.getName() + "\t"
                        + customer.getGender() + "\t" + customer.getAge() + "\t" +
                        customer.getPhone() + "\t" + customer.getEmail());
            }
        }
        System.out.println("--------------客户列表完成--------------");
    }

    public static void main(String[] args) {
        CustomerView customerView = new CustomerView();
        customerView.EnterMainMenu();
    }
}
