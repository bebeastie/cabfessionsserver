package com.cabfessions

class NeighborhoodController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [neighborhoodInstanceList: Neighborhood.list(params), neighborhoodInstanceTotal: Neighborhood.count()]
    }

    def create = {
        def neighborhoodInstance = new Neighborhood()
        neighborhoodInstance.properties = params
        return [neighborhoodInstance: neighborhoodInstance]
    }

    def save = {
        def neighborhoodInstance = new Neighborhood(params)
        if (neighborhoodInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), neighborhoodInstance.id])}"
            redirect(action: "show", id: neighborhoodInstance.id)
        }
        else {
            render(view: "create", model: [neighborhoodInstance: neighborhoodInstance])
        }
    }

    def show = {
        def neighborhoodInstance = Neighborhood.get(params.id)
        if (!neighborhoodInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), params.id])}"
            redirect(action: "list")
        }
        else {
            [neighborhoodInstance: neighborhoodInstance]
        }
    }

    def edit = {
        def neighborhoodInstance = Neighborhood.get(params.id)
        if (!neighborhoodInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [neighborhoodInstance: neighborhoodInstance]
        }
    }

    def update = {
        def neighborhoodInstance = Neighborhood.get(params.id)
        if (neighborhoodInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (neighborhoodInstance.version > version) {
                    
                    neighborhoodInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'neighborhood.label', default: 'Neighborhood')] as Object[], "Another user has updated this Neighborhood while you were editing")
                    render(view: "edit", model: [neighborhoodInstance: neighborhoodInstance])
                    return
                }
            }
            neighborhoodInstance.properties = params
            if (!neighborhoodInstance.hasErrors() && neighborhoodInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), neighborhoodInstance.id])}"
                redirect(action: "show", id: neighborhoodInstance.id)
            }
            else {
                render(view: "edit", model: [neighborhoodInstance: neighborhoodInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def neighborhoodInstance = Neighborhood.get(params.id)
        if (neighborhoodInstance) {
            try {
                neighborhoodInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), params.id])}"
            redirect(action: "list")
        }
    }
}
