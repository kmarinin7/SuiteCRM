package dto;

public class Account {
    private final String name;
    private final String type;
    private final String phone;
    private final String website;

    private Account(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.phone = builder.phone;
        this.website = builder.website;
    }

    public static class Builder {
        private String name;
        private String type;
        private String phone;
        private String website;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withWebsite(String website) {
            this.website = website;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getPhone() { return phone; }
    public String getWebsite() { return website; }

    @Override
    public String toString() {
        return "Account{name='" + name + "', type='" + type + "', phone='" + phone + "', website='" + website + "'}";
    }
}