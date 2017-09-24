package sample1

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ChildController {

    ChildService childService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond childService.list(params), model:[childCount: childService.count()]
    }

    def show(Long id) {
        respond childService.get(id)
    }

    def create() {
        respond new Child(params)
    }

    def save(Child child) {
        if (child == null) {
            notFound()
            return
        }

        try {
            childService.save(child)
        } catch (ValidationException e) {
            respond child.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'child.label', default: 'Child'), child.id])
                redirect child
            }
            '*' { respond child, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond childService.get(id)
    }

    def update(Child child) {
        if (child == null) {
            notFound()
            return
        }

        try {
            childService.save(child)
        } catch (ValidationException e) {
            respond child.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'child.label', default: 'Child'), child.id])
                redirect child
            }
            '*'{ respond child, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        childService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'child.label', default: 'Child'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'child.label', default: 'Child'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
