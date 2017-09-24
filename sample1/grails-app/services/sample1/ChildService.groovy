package sample1

import grails.gorm.services.Service

@Service(Child)
interface ChildService {

    Child get(Serializable id)

    List<Child> list(Map args)

    Long count()

    void delete(Serializable id)

    Child save(Child child)

}