package sample1

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ParentController {

    ParentService parentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond parentService.list(params), model:[parentCount: parentService.count()]
    }

    def show(Long id) {
        respond parentService.get(id)
    }

    def create() {
        respond new Parent(params)
    }

    def save(Parent parent) {
        if (parent == null) {
            notFound()
            return
        }

        try {
            parentService.save(parent)
        } catch (ValidationException e) {
            respond parent.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'parent.label', default: 'Parent'), parent.id])
                redirect parent
            }
            '*' { respond parent, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond parentService.get(id)
    }

    def update(Parent parent) {
        if (parent == null) {
            notFound()
            return
        }

        try {
            parentService.save(parent)
        } catch (ValidationException e) {
            respond parent.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'parent.label', default: 'Parent'), parent.id])
                redirect parent
            }
            '*'{ respond parent, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        parentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'parent.label', default: 'Parent'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'parent.label', default: 'Parent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
