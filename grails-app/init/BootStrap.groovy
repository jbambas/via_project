import cvut.fel.via.Role
import cvut.fel.via.User
import cvut.fel.via.UserRole

class BootStrap {

    def init = {
        if(User.countByUsername("via")<1){
            def adminRole = new Role(authority: 'ROLE_ADMIN').save()
            def testUser = new User(username: 'via', password: 'via').save()
            UserRole.create testUser, adminRole
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }
    }
    def destroy = {
    }
}
