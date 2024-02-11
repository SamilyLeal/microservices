package com.ssl.cards.utils;

public class CardConstants {
    private CardConstants() {}

    public final static double NEW_CARD_LIMIT = 1_000;
    public final static String CREDIT_CARD = "Credit";
    public final static String DEBIT_CARD = "Debit";
    public final static String STATUS_200 = "200";
    public final static String MESSAGE_200 = "Request processed successfully!";
    public final static String STATUS_201 = "201";
    public final static String MESSAGE_201 = "Card created successfully!";
    public final static String STATUS_417 = "417";
    public final static String MESSAGE_417_UPDATE = "Update operation failed. Please try again.";
    public final static String MESSAGE_417_DELETE = "Delete operation failed. Please try again.";
}
