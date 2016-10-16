package semester_project

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RestaurantController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Restaurant.list(params), model:[restaurantCount: Restaurant.count()]
    }

    def show(Restaurant restaurant) {
        respond restaurant
    }

    def create() {
        respond new Restaurant(params)
    }

    @Transactional
    def save(Restaurant restaurant) {
        if (restaurant == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (restaurant.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond restaurant.errors, view:'create'
            return
        }

        restaurant.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), restaurant.id])
                redirect restaurant
            }
            '*' { respond restaurant, [status: CREATED] }
        }
    }

    def edit(Restaurant restaurant) {
        respond restaurant
    }

    @Transactional
    def update(Restaurant restaurant) {
        if (restaurant == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (restaurant.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond restaurant.errors, view:'edit'
            return
        }

        restaurant.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), restaurant.id])
                redirect restaurant
            }
            '*'{ respond restaurant, [status: OK] }
        }
    }

    @Transactional
    def delete(Restaurant restaurant) {

        if (restaurant == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        restaurant.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), restaurant.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'restaurant.label', default: 'Restaurant'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
