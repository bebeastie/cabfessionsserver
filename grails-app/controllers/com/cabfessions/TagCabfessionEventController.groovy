package com.cabfessions

class TagCabfessionEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tagCabfessionEventInstanceList: TagCabfessionEvent.list(params), tagCabfessionEventInstanceTotal: TagCabfessionEvent.count()]
    }

    def create = {
        def tagCabfessionEventInstance = new TagCabfessionEvent()
        tagCabfessionEventInstance.properties = params
        return [tagCabfessionEventInstance: tagCabfessionEventInstance]
    }

    def save = {
        def tagCabfessionEventInstance = new TagCabfessionEvent(params)
        if (tagCabfessionEventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), tagCabfessionEventInstance.id])}"
            redirect(action: "show", id: tagCabfessionEventInstance.id)
        }
        else {
            render(view: "create", model: [tagCabfessionEventInstance: tagCabfessionEventInstance])
        }
    }

    def show = {
        def tagCabfessionEventInstance = TagCabfessionEvent.get(params.id)
        if (!tagCabfessionEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tagCabfessionEventInstance: tagCabfessionEventInstance]
        }
    }

    def edit = {
        def tagCabfessionEventInstance = TagCabfessionEvent.get(params.id)
        if (!tagCabfessionEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tagCabfessionEventInstance: tagCabfessionEventInstance]
        }
    }

    def update = {
        def tagCabfessionEventInstance = TagCabfessionEvent.get(params.id)
        if (tagCabfessionEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tagCabfessionEventInstance.version > version) {
                    
                    tagCabfessionEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent')] as Object[], "Another user has updated this TagCabfessionEvent while you were editing")
                    render(view: "edit", model: [tagCabfessionEventInstance: tagCabfessionEventInstance])
                    return
                }
            }
            tagCabfessionEventInstance.properties = params
            if (!tagCabfessionEventInstance.hasErrors() && tagCabfessionEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), tagCabfessionEventInstance.id])}"
                redirect(action: "show", id: tagCabfessionEventInstance.id)
            }
            else {
                render(view: "edit", model: [tagCabfessionEventInstance: tagCabfessionEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tagCabfessionEventInstance = TagCabfessionEvent.get(params.id)
        if (tagCabfessionEventInstance) {
            try {
                tagCabfessionEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagCabfessionEvent.label', default: 'TagCabfessionEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
