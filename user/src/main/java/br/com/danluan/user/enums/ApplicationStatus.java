package br.com.danluan.user.enums;

public enum ApplicationStatus {
    P("Pending"),
    A("Approved"),
    R("Rejected");

    private String description;

    ApplicationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
