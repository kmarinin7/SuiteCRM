package dto;

public class Task {
    private final String subject;
    private final String status;
    private final String priority;
    private final String description;
    private final String startDate;
    private final String dateDue;
    private final String assignedTo;
    private final String contactName;  // ← НОВОЕ ПОЛЕ

    private Task(Builder builder) {
        this.subject = builder.subject;
        this.status = builder.status;
        this.priority = builder.priority;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.dateDue = builder.dateDue;
        this.assignedTo = builder.assignedTo;
        this.contactName = builder.contactName;  // ← НОВОЕ
    }

    public static class Builder {
        private String subject;
        private String status;
        private String priority;
        private String description;
        private String startDate;
        private String dateDue;
        private String assignedTo;
        private String contactName;  // ← НОВОЕ

        public Builder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withPriority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withDateDue(String dateDue) {
            this.dateDue = dateDue;
            return this;
        }

        public Builder withAssignedTo(String assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public Builder withContactName(String contactName) {  // ← НОВЫЙ МЕТОД
            this.contactName = contactName;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    public String getSubject() { return subject; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }
    public String getDescription() { return description; }
    public String getStartDate() { return startDate; }
    public String getDateDue() { return dateDue; }
    public String getAssignedTo() { return assignedTo; }
    public String getContactName() { return contactName; }  // ← НОВЫЙ ГЕТТЕР

    @Override
    public String toString() {
        return "Task{subject='" + subject + "', status='" + status + "', priority='" + priority +
                "', startDate='" + startDate + "', dateDue='" + dateDue + "', contactName='" + contactName + "'}";
    }
}
