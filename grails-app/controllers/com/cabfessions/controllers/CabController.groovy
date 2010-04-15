package com.cabfessions.controllers

import com.cabfessions.Cab;

class CabController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cabInstanceList: Cab.list(params), cabInstanceTotal: Cab.count()]
    }

    def create = {
        def cabInstance = new Cab()
        cabInstance.properties = params
        return [cabInstance: cabInstance]
    }

    def save = {
        def cabInstance = new Cab(params)
        if (cabInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cab.label', default: 'Cab'), cabInstance.id])}"
            redirect(action: "show", id: cabInstance.id)
        }
        else {
            render(view: "create", model: [cabInstance: cabInstance])
        }
    }

    def show = {
        def cabInstance = Cab.get(params.id)
        if (!cabInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cab.label', default: 'Cab'), params.id])}"
            redirect(action: "list")
        }
        else {
            [cabInstance: cabInstance]
        }
    }

    def edit = {
        def cabInstance = Cab.get(params.id)
        if (!cabInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cab.label', default: 'Cab'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [cabInstance: cabInstance]
        }
    }

    def update = {
        def cabInstance = Cab.get(params.id)
        if (cabInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cabInstance.version > version) {
                    
                    cabInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cab.label', default: 'Cab')] as Object[], "Another user has updated this Cab while you were editing")
                    render(view: "edit", model: [cabInstance: cabInstance])
                    return
                }
            }
            cabInstance.properties = params
            if (!cabInstance.hasErrors() && cabInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cab.label', default: 'Cab'), cabInstance.id])}"
                redirect(action: "show", id: cabInstance.id)
            }
            else {
                render(view: "edit", model: [cabInstance: cabInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cab.label', default: 'Cab'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def cabInstance = Cab.get(params.id)
        if (cabInstance) {
            try {
                cabInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cab.label', default: 'Cab'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cab.label', default: 'Cab'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cab.label', default: 'Cab'), params.id])}"
            redirect(action: "list")
        }
    }
}
