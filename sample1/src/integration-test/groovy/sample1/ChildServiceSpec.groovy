package sample1

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChildServiceSpec extends Specification {

    ChildService childService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Child(...).save(flush: true, failOnError: true)
        //new Child(...).save(flush: true, failOnError: true)
        //Child child = new Child(...).save(flush: true, failOnError: true)
        //new Child(...).save(flush: true, failOnError: true)
        //new Child(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //child.id
    }

    void "test get"() {
        setupData()

        expect:
        childService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Child> childList = childService.list(max: 2, offset: 2)

        then:
        childList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        childService.count() == 5
    }

    void "test delete"() {
        Long childId = setupData()

        expect:
        childService.count() == 5

        when:
        childService.delete(childId)
        sessionFactory.currentSession.flush()

        then:
        childService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Child child = new Child()
        childService.save(child)

        then:
        child.id != null
    }
}
