package com.cabfessions

class CabfessionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cabfessionInstanceList: Cabfession.list(params), cabfessionInstanceTotal: Cabfession.count()]
    }

    def create = {
        def cabfessionInstance = new Cabfession()
        cabfessionInstance.properties = params
        return [cabfessionInstance: cabfessionInstance]
    }

    def save = {
        def cabfessionInstance = new Cabfession(params)
        if (cabfessionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), cabfessionInstance.id])}"
            redirect(action: "show", id: cabfessionInstance.id)
        }
        else {
            render(view: "create", model: [cabfessionInstance: cabfessionInstance])
        }
    }

    def show = {
        def cabfessionInstance = Cabfession.get(params.id)
        if (!cabfessionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), params.id])}"
            redirect(action: "list")
        }
        else {
            [cabfessionInstance: cabfessionInstance]
        }
    }

    def edit = {
        def cabfessionInstance = Cabfession.get(params.id)
        if (!cabfessionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [cabfessionInstance: cabfessionInstance]
        }
    }

    def update = {
        def cabfessionInstance = Cabfession.get(params.id)
        if (cabfessionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cabfessionInstance.version > version) {
                    
                    cabfessionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cabfession.label', default: 'Cabfession')] as Object[], "Another user has updated this Cabfession while you were editing")
                    render(view: "edit", model: [cabfessionInstance: cabfessionInstance])
                    return
                }
            }
            cabfessionInstance.properties = params
            if (!cabfessionInstance.hasErrors() && cabfessionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), cabfessionInstance.id])}"
                redirect(action: "show", id: cabfessionInstance.id)
            }
            else {
                render(view: "edit", model: [cabfessionInstance: cabfessionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def cabfessionInstance = Cabfession.get(params.id)
        if (cabfessionInstance) {
            try {
                cabfessionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cabfession.label', default: 'Cabfession'), params.id])}"
            redirect(action: "list")
        }
    }
}
