package bctc.cabinet.util;

public enum ServiceError {
    USER_NOT_FOUND("User not found");

    final String errorName;

    ServiceError(String errorName) {
        this.errorName = errorName;
    }

    @Override
    public String toString() {
        return errorName;
    }
}
