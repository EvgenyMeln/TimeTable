package com.example.timetable.table.weekdays;

public enum Weekdays {
    MONDAY("0", "ПН", "Понедельник"),
    TUESDAY("1", "ВТ", "Вторник"),
    WEDNESDAY("2", "СР", "Среда"),
    THURSDAY("3", "ЧТ", "Четверг"),
    FRIDAY("4", "ПТ", "Пятница"),
    SATURDAY("5", "СБ", "Суббота"),
    SUNDAY("6", "ВС", "Воскресенье");

    private final String value;
    private final String shortName;
    private final String fullName;

    Weekdays(String value, String shortName, String fullName) {
        this.value = value;
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getInt(){return Integer.parseInt(value);}
}