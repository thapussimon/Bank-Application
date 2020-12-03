package com.bankapp.services;

import com.bankapp.dtos.Transaction;

import java.lang.annotation.Target;

public class EmailNotificationService implements NotificationService, Observer {
    private static EmailNotificationService instance = new EmailNotificationService();
    private ServiceFactory serviceFactory;
    private Subject accountServiceSubject;

    public EmailNotificationService() {
        serviceFactory = new ServiceFactory();
        accountServiceSubject = (Subject) serviceFactory.getAccountService();
        accountServiceSubject.registerObserver(this);

    }

    public static EmailNotificationService getInstance() {
        if (instance == null) {
            instance = new EmailNotificationService();
        }
        return instance;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println(message);

    }

    @Override
    public void update(Object data) {
        if (data instanceof Transaction) {
            Transaction transaction = (Transaction) data;
            String message = "Email: " + transaction.toString();
            sendNotification(message);
        }

    }
}
