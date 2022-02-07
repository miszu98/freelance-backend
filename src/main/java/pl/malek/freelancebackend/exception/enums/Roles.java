package pl.malek.freelancebackend.exception.enums;

public enum Roles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String fullName;

    Roles(String role) {
        this.fullName = role;
    }

    public String getFullName() {
        return this.fullName;
    }

}
