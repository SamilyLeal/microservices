package com.ssl.accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    @Column(name = "created_at",updatable = false) // column not included in updates
    private LocalDateTime createdAt;
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @Column(name = "update_at", insertable = false) // column not included in inserts
    private LocalDateTime updateAt;
    @Column(name = "update_by", insertable = false)
    private String updateBy;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
