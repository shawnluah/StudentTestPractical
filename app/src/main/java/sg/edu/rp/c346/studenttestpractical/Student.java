package sg.edu.rp.c346.studenttestpractical;

public class Student {
    private int id;
    private String name;
    private Integer gpa;

    public Student(int id, String name, int gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public  int getGpa () {
        return gpa;
    }
}
