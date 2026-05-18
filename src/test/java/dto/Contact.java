package dto;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String accountName;
    private final String phone;
    private final String email;

    private Contact(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.accountName = builder.accountName;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String accountName;
        private String phone;
        private String email;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAccountName() { return accountName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Contact{firstName='" + firstName + "', lastName='" + lastName + "', accountName='" + accountName + "'}";
    }
}