package sample1

import grails.gorm.services.Service

@Service(Parent)
interface ParentService {

    Parent get(Serializable id)

    List<Parent> list(Map args)

    Long count()

    void delete(Serializable id)

    Parent save(Parent parent)

}