package com.example.ecommerce.dto;

public class CustomerPreferenceDTO {
    private Long id;
    private Long userId;
    private Boolean emailNotifications;
    private Boolean smsNotifications;
    private Boolean newsletterSubscription;
    private String preferredLanguage;
    private String preferredCurrency;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Boolean getEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    
    public Boolean getSmsNotifications() { return smsNotifications; }
    public void setSmsNotifications(Boolean smsNotifications) { this.smsNotifications = smsNotifications; }
    
    public Boolean getNewsletterSubscription() { return newsletterSubscription; }
    public void setNewsletterSubscription(Boolean newsletterSubscription) { this.newsletterSubscription = newsletterSubscription; }
    
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    
    public String getPreferredCurrency() { return preferredCurrency; }
    public void setPreferredCurrency(String preferredCurrency) { this.preferredCurrency = preferredCurrency; }
}

