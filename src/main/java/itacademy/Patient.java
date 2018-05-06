package itacademy;

import java.util.Date;
import java.util.Objects;

public class Patient {
    private String name;
    private String secondName;
    private Date birthday;
    private boolean healthy;

    public Patient(String name, String secondName, Date birthday, boolean healthy) {
        this.name = name;
        this.secondName = secondName;
        this.birthday = birthday;
        this.healthy = healthy;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public boolean isHealthy() {
        return healthy;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthday=" + birthday +
                ", healthy=" + healthy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(name, patient.name) &&
                Objects.equals(secondName, patient.secondName) &&
                Objects.equals(birthday, patient.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, birthday);
    }
}