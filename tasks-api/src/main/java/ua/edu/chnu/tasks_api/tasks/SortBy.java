package ua.edu.chnu.tasks_api.tasks;

public enum SortBy {
    NAME("name"),
    COURSE_ID("courseId"),
    DEADLINE("deadline"),
    GRADE("grade");

    private final String property;

    SortBy(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
