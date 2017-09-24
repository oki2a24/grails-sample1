package sample1

class Parent {

    String name
    Date birthday
    String adress
    //List<Child> children

    static hasMany = [children: Child]
    static constraints = {
    }
}
