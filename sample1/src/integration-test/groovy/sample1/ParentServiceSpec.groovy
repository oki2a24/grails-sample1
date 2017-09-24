package sample1

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ParentServiceSpec extends Specification {

    ParentService parentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Parent(...).save(flush: true, failOnError: true)
        //new Parent(...).save(flush: true, failOnError: true)
        //Parent parent = new Parent(...).save(flush: true, failOnError: true)
        //new Parent(...).save(flush: true, failOnError: true)
        //new Parent(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //parent.id
    }

    void "test get"() {
        setupData()

        expect:
        parentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Parent> parentList = parentService.list(max: 2, offset: 2)

        then:
        parentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        parentService.count() == 5
    }

    void "test delete"() {
        Long parentId = setupData()

        expect:
        parentService.count() == 5

        when:
        parentService.delete(parentId)
        sessionFactory.currentSession.flush()

        then:
        parentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Parent parent = new Parent()
        parentService.save(parent)

        then:
        parent.id != null
    }
}
