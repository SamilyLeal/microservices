package com.ssl.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class CardDTO {
    @NotEmpty(message = "Phone number can not be a null or empty!")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @NotEmpty(message = "Card number can not be a null or empty!")
    @Pattern(regexp = "\\d{12}", message = "Card number invalid!")
    private String cardNumber;
    @NotEmpty(message = "Car type can not be a null or empty!")
    private String cardType;
    @PositiveOrZero(message = "Total limit should be greater than zero")
    private Double totalLimit;
    @PositiveOrZero(message = "Total amount used should be equal or greater than zero!")
    private Double amountUsed;
    @PositiveOrZero(message = "Available amount should be equal or greater than zero!")
    private Double availableAmount;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Double getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Double totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Double getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Double amountUsed) {
        this.amountUsed = amountUsed;
    }

    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }

}
